package kuhna.servlet;

import java.io.*;
import java.util.*;

import kuhna.lang.*;
import kuhna.servlet.http.*;
import kuhna.servlet.multipart.*;

/**
 * Multipart Body 객체<BR>
 * URLConnection에서 request message를 만들때 Multipart문서를 편하게 만들기위한 Framework을 제공하고, 
 * response로 날라온 Body InputStream으로 부터 MultipartBody를 얻게해준다.<BR>
 * 본 객체는 JAVA URL Framework의 Kuhna구현에서 MultipartContentHandler의 getContent()의 리턴타입이 된다.<BR> 
 * 본 클래스의 에러처리에 관한 일부소스코드는 com.oreily.servlet를 참조했다.
 * 
 * @version 0.5, 2005/08/15, refactoring by A.J.Kuhn<BR><!--
 * @version -->0.4, 2004/08/13, added writeTo(Writer) method for tomcat's OutputStream bug, modified writeTo(OutputStream, String) method by A.J.Kuhn<BR><!--
 * @version -->0.3.3, 2004/05/24, changed "\n" to System.getProperty("line.separator") by A.J.Kuhn<BR><!--
 * @version -->0.3.2, 2004/05/10, changed Writer.close() to Writer.flush() in writeTo() method for Some JDK Version by A.J.Kuhn<BR><!--
 * @version -->0.3.1, 2004/05/10, changed InputStream to BufferedInputStream for performance and avoiding WebLogic 6.1 ServletInpustStream bug by A.J.Kuhn<BR><!--
 * @version -->0.3, 2004/04/30, added setPart(int, Part) method by A.J.Kuhn<BR><!--
 * @version -->0.2, 2004/04/18, added getPart(String), getParts(), getParts(String) methods, changed createPart method private to protected by A.J.Kuhn<BR><!--
 * @version -->0.1, 2004/04/06, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class MultipartBody extends Body {

  private Vector _parts;

  private String _boundary;

  /** Default 인코딩 */
  private static final String DEFAULT_ENCODING = "ISO-8859-1";

  /** 설정 인코딩 */
  private String _encoding = DEFAULT_ENCODING;

  private static final int DEFAULT_MAX_FILE_SIZE = 1024 * 1024; // 1 mega bytes
  
  private int _maxFileSize = DEFAULT_MAX_FILE_SIZE;
  
  /**
   * 생성자.
   *
   * @param boundary 파트의 구분자
   */
  public MultipartBody(String boundary) throws IOException {
    this(boundary, null);
  }

  /**
   * 생성자.
   *
   * @param boundary 파트의 구분자
   * @param encoding 인코딩
   */
  public MultipartBody(String boundary, String encoding) throws IOException {
    super();
    _parts = new Vector();

    if(boundary == null)
      throw new IOException("boundary value is null, boundary must be specified");

    setBoundary(boundary);
    setEncoding(encoding);
  }
  
  /**
   * 생성자.
   *
   * @param in       Body의 InputStream
   * @param boundary 파트의 구분자
   */
  public MultipartBody(InputStream in, String boundary) throws IOException {
    this(in, boundary, null);
  }

  /**
   * 생성자.
   *
   * @param in       Body의 InputStream
   * @param boundary 파트의 구분자
   * @param encoding 인코딩
   */
  public MultipartBody(InputStream in, String boundary, String encoding) throws IOException {
    this(in, boundary, encoding, DEFAULT_MAX_FILE_SIZE);
  }
  
  /**
   * 생성자.
   *
   * @param in       Body의 InputStream
   * @param boundary 파트의 구분자
   */
  public MultipartBody(InputStream in, String boundary, int maxFileSize) throws IOException {
    this(in, boundary, null, maxFileSize);
  }

  /**
   * 생성자.
   *
   * @param in       Body의 InputStream
   * @param boundary 파트의 구분자
   * @param encoding 인코딩
   */
  public MultipartBody(InputStream in, String boundary, String encoding, int maxFileSize) throws IOException {
    this(boundary, encoding);

    _maxFileSize = maxFileSize;
//    _in = new BufferedInputStream(in);
    _in = in;
    readInputStream();
  }

  private void readInputStream() throws IOException {
    // 일부 클라이언트(ex. browser)에서 Body에 쓸데없는 전문(머리말)을 다는경우가 있어 이를 무시하기위한 부분 
    do {
      String line = readLine();
      if(line == null)
        throw new IOException("Corrupt form data: premature ending");

      if(line.startsWith(_boundary))
        break;
    } while(true);

    do {
      Part part = createPart();
      if(part != null)
        addPart(part);
      else
        break;
    } while(true);
  }

  protected Part createPart() throws IOException {
    String line = readLine();
    if(line == null || line.length() ==0) // 더이상 Part 없음, IE4 for Mac에서는 마지막에 길이가 0
      return null;

    // Header부분을 읽어온다. 길이가 0인 라인을 만날때까지 헤더를 구한다.
    Vector headers = new Vector();
    StringBuffer headerBuffer = new StringBuffer();
    while(line != null && line.length() > 0) {
      String nextLine = null;
      boolean getNextLine = true;
      while(getNextLine) { // 다른 라인이지만 ""로 감싸여져 하나의 헤더로 인식되는경우를 처리한다
        nextLine = readLine();
        if(nextLine != null && (nextLine.startsWith(" ") || nextLine.startsWith("\t"))) {
          line = line + nextLine;
        } else {
          getNextLine = false;
        }
      }
      headers.addElement(line);
      headerBuffer.append(line);
      headerBuffer.append(System.getProperty("line.separator"));
      line = nextLine;
    }

    // 길이가 0이어야지 null이어선 안됨 
    if(line == null)
      return null;

    String headerString = headerBuffer.toString();

    String filename = HttpUtil.getPathFilename(headerString);

    if(filename == null) {
      return new TextPart(headers, _in, getBoundary(), getEncoding());
    } else {
      return new FilePart(headers, _in, getBoundary(), getEncoding(), _maxFileSize);
    }
  }

  public void setPart(int index, Part part) {
    if(_parts.size() == 0)
      _parts.add(part);
    else
      _parts.set(index, part);
  }

  public void addPart(Part part) {
    _parts.add(part);
  }

  public Part getPart(int index) {
    return (Part)_parts.get(index);
  }

  public Part getPart(String name) {
    Part part = null;

    Vector v = (Vector)getParts(name);
    if(v.size() != 0)
      part = (Part)v.get(0);

    return part;
  }

  public Collection getParts() {
    return _parts;
  }

  public Collection getParts(String name) {
    Vector v = new Vector();

    Iterator itr = _parts.iterator();
    while(itr.hasNext()) {
      Part part = (Part)itr.next();
      String n = HttpUtil.getValue(" name", "=", part.getHeaderString());
      if(n != null && n.equals(name))
        v.add(part);
    }

    return v;
  }

  public int getPartCount() {
    return _parts.size();
  }

  public void setBoundary(String boundary) {
    _boundary = "--" + boundary;
  }

  public String getBoundary() {
    return _boundary.substring(2);
  }

  public void setEncoding(String encoding) {
    if(encoding == null)
      encoding = DEFAULT_ENCODING;

    _encoding = encoding;
  }

  public String getEncoding() {
    return _encoding;
  }

  private String readLine() throws IOException {
    StringBuffer result = new StringBuffer();

    int chr;
    while(((chr = _in.read()) != -1) && (chr != 10) && (chr != 13)) {
      result.append((char)chr);
    }
    
    if((chr == -1) && result.length() == 0)
      return null;

    if(chr == 13) {
      _in.mark(1);
      if(_in.read() != 10)
        _in.reset();
    }

    return StringUtil.encodingTo(result.toString(), getEncoding());
  }

  public void writeTo(OutputStream out) throws IOException {
  /*
    OutputStreamWriter writer = new OutputStreamWriter(out, getEncoding());
    writeTo(out);
  */

//    BufferedOutputStream os = new BufferedOutputStream(out);
    out.write(_boundary.getBytes(getEncoding()));
    out.write(System.getProperty("line.separator").getBytes(getEncoding()));
    out.flush();

    Iterator itr = _parts.iterator();
    while(itr.hasNext()) {
      Part part = (Part)itr.next();
      part.writeTo(out, getEncoding());
      out.write(System.getProperty("line.separator").getBytes(getEncoding()));
      out.write(_boundary.getBytes(getEncoding()));
      out.write(System.getProperty("line.separator").getBytes(getEncoding()));
      out.flush();
    }
  }

  public void writeTo(Writer out) throws IOException {
    BufferedWriter writer = new BufferedWriter(out);
    writer.write(_boundary, 0, _boundary.length());
    writer.newLine();
    writer.flush();

    Iterator itr = _parts.iterator();
    while(itr.hasNext()) {
      Part part = (Part)itr.next();
      part.writeTo(out);
      writer.newLine();
      writer.write(_boundary, 0, _boundary.length());
      writer.newLine();
      writer.flush();
    }
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append(_boundary);
    buffer.append(System.getProperty("line.separator"));
    Iterator itr = _parts.iterator();
    while(itr.hasNext()) {
      buffer.append(itr.next().toString());
      buffer.append(System.getProperty("line.separator"));
      buffer.append(_boundary);
      buffer.append(System.getProperty("line.separator"));
    }

    return buffer.toString(); 
  }
}

