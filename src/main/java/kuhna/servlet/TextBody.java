package kuhna.servlet;

import java.io.*;
import java.util.*;

import kuhna.lang.*;

/**
 * text(ASCII)형태의 Body객체
 * 
 * @version 0.4, 2004/11/14, refactoring by A.J.Kuhn<BR><!-- 
 * @version -->0.3, 2004/08/13, added writeTo(Writer), method for tomcat's OutputStream bug, modified writeTo(OutputStream, String) method by A.J.Kuhn<BR><!--
 * @version -->0.2, 2004/05/24, changed "\n" to System.getProperty("line.separator") and improved performance TextBody(InputStream, String) constructor by A.J.Kuhn<BR><!-- 
 * @version -->0.1.1, 2004/05/10, changed Writer.close() to Writer.flush() in writeTo() method for Some JDK Version by A.J.Kuhn<BR><!-- 
 * @version -->0.1, 2004/04/22 initial version by A.J.Kuhn
 *
 * @author  A.J.Kuhn
 */
public class TextBody extends Body {

  protected String _text;

  /** Default 인코딩 */
  protected static final String DEFAULT_ENCODING = "ISO-8859-1";

  /** 설정 인코딩 */
  protected String _encoding = DEFAULT_ENCODING;

  public TextBody() {
    super();

    _text = new String();
  }

  public TextBody(String encoding) {
    this();
    setEncoding(encoding);
  }

  /**
   * 생성자.
   *
   * @param in       Body의 InputStream
   */
  public TextBody(InputStream in) throws IOException {
    this(in, DEFAULT_ENCODING);
  }

  /**
   * 생성자. 일단 대충 구현함.
   *
   * @param in       Body의 InputStream
   * @param encoding 인코딩
   */
  public TextBody(InputStream in, String encoding) throws IOException {
    this(encoding);

    _in = in;

    StringBuffer buffer = new StringBuffer();

    String line = null;
    while(true) {
      line = readLine();

      if(line == null)
        break;

      buffer.append(line);
      buffer.append(System.getProperty("line.separator"));
    }

    if(buffer.length() == 0)
      setText("");
    else
      setText(buffer.substring(0, buffer.length() - 1));
  }

  public String getText() {
    return _text;
  }

  public void setText(String text) {
    _text = text;
  }

  public void setEncoding(String encoding) {
    if(encoding == null)
      return;

    _encoding = encoding;
  }

  public String getEncoding() {
    return _encoding;
  }

  protected String readLine() throws IOException {
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
    out.write(_text.getBytes(getEncoding()));
    out.flush();
  }

  public void writeTo(Writer out) throws IOException {
    BufferedWriter writer = new BufferedWriter(out);
    writer.write(_text, 0, _text.length());
    writer.flush();
  }

  public String toString() {
    return getText(); 
  }
}

