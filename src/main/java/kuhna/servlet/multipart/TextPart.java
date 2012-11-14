package kuhna.servlet.multipart;

import java.io.*;
import java.util.*;

import kuhna.lang.*;

/**
 * Multipart문서의 text(ASCII)형태의 Part객체
 * 
 * @version 0.4.1, 2005/02/01, modified writeTo() methods by A.J.Kuhn<BR><!-- 
 * @version -->0.4, 2004/11/14, refactoring by A.J.Kuhn<BR><!-- 
 * @version -->0.3, 2004/08/13, added writeTo(Writer), method for tomcat's OutputStream bug, modified writeTo(OutputStream, String) method by A.J.Kuhn<BR><!--
 * @version -->0.2.5, 2004/05/24, changed "\n" to System.getProperty("line.separator") and removing line separator bug fix by A.J.Kuhn<BR><!-- 
 * @version -->0.2.1, 2004/05/10, added IOException process(No part Boundary) in TextPart(x,x,x,x) constructor, changed Writer.close() to Writer.flush() in writeTo() method for Some JDK Version.   by A.J.Kuhn<BR><!--
 * @version -->0.2, 2004/04/18, added constructors TextPart(Collection), TextPart(Map), TextPart(Part) by A.J.Kuhn<BR><!--
 * @version -->0.1, 2004/04/06, initial version by A.J.Kuhn<BR>
 *
 * @author  A.J.Kuhn
 */
public class TextPart extends Part {
  
  protected String _text;

  public TextPart() {
    super();
    
    _text = new String();
  }

  public TextPart(String name) {
    this();
    
    setHeader("content-disposition", "form-data; name=\"" + name + "\"");
  }

  public TextPart(Collection headers) {
    this();
    setHeaders(headers);
  }
 
  public TextPart(Map headers) {
    this();
    setHeaders(headers);
  }
 
  public TextPart(Part part) {
    this();
    setHeaders(part.getHeaders());
  }

  /**
   * 생성자
   * 
   */
  public TextPart(Collection headers, InputStream in, String boundary, String encoding) throws IOException {
    super(headers);

    StringBuffer buffer = new StringBuffer(); 
    do {
      String temp = readLine(in, encoding);
      if(temp == null)
        throw new IOException("No part boundary, part must be end with boundary");
      if(temp.equals("--" + boundary))
        break;
      
      buffer.append(temp);
      buffer.append(System.getProperty("line.separator"));
    } while(true);

    _text = buffer.toString(); 
  }

  public String getText() {
    return _text;
  }

  public void setText(String text) {
    _text = text;
  }

  public boolean isTextPart() {
    return true;
  }

  public void writeTo(OutputStream out, String encoding) throws IOException {
    writeHeaderTo(out, encoding);

    BufferedOutputStream os = new BufferedOutputStream(out);
    os.write(getText().getBytes(encoding));
    os.flush();
  }

  public void writeTo(Writer out) throws IOException {
    writeHeaderTo(out);

    BufferedWriter writer = new BufferedWriter(out);
    writer.write(getText(), 0, getText().length());
    writer.flush();
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append(getHeaderString());
    buffer.append(System.getProperty("line.separator"));
    buffer.append(getText());

    return buffer.toString(); 
  }
}

