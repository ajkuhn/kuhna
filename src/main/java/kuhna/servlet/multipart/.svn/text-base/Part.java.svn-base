package kuhna.servlet.multipart;

import java.io.*;
import java.util.*;

import kuhna.lang.*;
import kuhna.util.*;

/**
 * Multipart의 한 Part객체
 * 
 * @version 0.3, 2004/08/13, added writeTo(Writer), writeHeaderTo(Writer) methods for tomcat's OutputStream bug, modified writeHeaderTo(OutputStream, String) method by A.J.Kuhn<BR><!--
 * @version -->0.2.2, 2004/05/24, changed "\n" to System.getProperty("line.separator") by A.J.Kuhn<BR><!--
 * @version -->0.2.1, 2004/05/10, changed Writer.close() to Writer.flush() in writeHeaderTo() method for Some JDK Version by A.J.Kuhn<BR><!--
 * @version -->0.2, 2004/04/18, added constructors Part(Map), Part(Part), methods setHeaders(Map), getHeaders() by A.J.Kuhn<BR><!--
 * @version -->0.1, 2004/04/06, initial version by A.J.Kuhn
 *
 * @author  A.J.Kuhn
 */
public abstract class Part extends Object {

  protected Hashtable _headers;

  protected Vector _headerKeys;

  protected Part() {
    _headers = new Hashtable();
    _headerKeys = new Vector();
    
    setHeader("content-disposition", "form-data");    
    setHeader("content-type", "text/plain"); // RFC1867의 디폴트값, Header에 Content-Type을 입력 안할경우를 위함
  }

  protected Part(Collection headers) {
    this();
    setHeaders(headers);
  }
 
  protected Part(Map headers) {
    this();
    setHeaders(headers);
  }
 
  protected Part(Part part) {
    this();
    setHeaders(part.getHeaders());
  }

  public String getHeader(String key) {
    key = key.toLowerCase();
    return (String)_headers.get(key);
  }
  
  public void setHeader(String key, Object value) {
    key = key.toLowerCase();
    _headers.put(key, value);
    if(!hasHeader(key))
      _headerKeys.add(key);
  }

  public void setHeaders(Collection c) {
    Iterator itr = c.iterator();
    while(itr.hasNext()) {
      String line = (String)itr.next();
      int index = line.indexOf(": ");
      if(index != -1) {
        setHeader(line.substring(0, index), line.substring(index + 2));
      }
    }
  }

  public void setHeaders(Map map) {
    Set set = map.keySet();
    Iterator itr = set.iterator();
    while(itr.hasNext()) {
      String key = (String)itr.next();
      if(!hasHeader(key))
        _headerKeys.add(key);
      _headers.put(key, map.get(key));
    }
  }

  public Map getHeaders() {
    return _headers;
  }

  public String getHeaderString() {
    StringBuffer buffer = new StringBuffer();

    Iterator itr = _headerKeys.iterator();
    while(itr.hasNext()) {
      String key = (String)itr.next();
      String value = (String)_headers.get(key);
      buffer.append(key);
      buffer.append(": ");
      buffer.append(value);
      buffer.append(System.getProperty("line.separator"));
    }

    return buffer.toString();
  }

  public boolean hasHeader(String key) {
    if(_headerKeys.indexOf(key) == -1)
      return false;
    else
      return true;
  }

  public boolean isFilePart() {
    return false;
  }
  
  public boolean isTextPart() {
    return false;
  }

  public boolean isParamPart() {
    return false;
  }

  public abstract void writeTo(OutputStream out, String encoding) throws IOException; 

  public abstract void writeTo(Writer out) throws IOException; 

  protected void writeHeaderTo(OutputStream out, String encoding) throws IOException {
//    BufferedOutputStream os = new BufferedOutputStream(out);
    String headerString = getHeaderString();
    out.write(headerString.getBytes(encoding));
    out.write(System.getProperty("line.separator").getBytes(encoding));
    out.flush();
  }

  protected void writeHeaderTo(Writer out) throws IOException {
    BufferedWriter writer = new BufferedWriter(out);
    String headerString = getHeaderString();
    writer.write(headerString, 0, headerString.length());
    writer.newLine();
    writer.flush();
  }

  protected String readLine(InputStream in) throws IOException {
    StringBuffer result = new StringBuffer();

    int chr;
    while(((chr = in.read()) != -1) && (chr != 10) && (chr != 13)) {
      result.append((char)chr);
    }

    if((chr == -1) && result.length() == 0)
      return null;

    if(chr == 13) {
      in.mark(1);
      if(in.read() != 10)
        in.reset();
    }

    return result.toString();
  }

  protected String readLine(InputStream in, String encoding) throws IOException {
    return StringUtil.encodingTo(readLine(in), "ISO-8859-1", encoding);
  }
}

