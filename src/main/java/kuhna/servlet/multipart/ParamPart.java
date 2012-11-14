package kuhna.servlet.multipart;

import java.io.*;
import java.util.*;

import kuhna.lang.*;

/**
 * Multipart문서의 POST Parameter형태의 Part객체
 * 
 * @version 0.3, 2004/04/22, addParam()'s bug fixed
 * @author  A.J.Kuhn
 * 
 * @version 0.2, 2004/04/18, added constructors ParamPart(Collection), ParamPart(Map), ParamPart(Part)
 * @author  A.J.Kuhn
 * 
 * @version 0.1, 2004/04/07
 * @author  A.J.Kuhn
 */
public class ParamPart extends TextPart {
  
  /**
   * 생성자
   * 
   */
  public ParamPart() {
    super();
  }

  public ParamPart(String name) {
    super();

    setHeader("content-disposition", "form-data; name=\"" + name + "\""); // 디폴트로 form-data 사용
  }

  public ParamPart(Collection headers) {
    this();
    setHeaders(headers);
  }
 
  public ParamPart(Map headers) {
    this();
    setHeaders(headers);
  }
 
  public ParamPart(Part part) {
    this();
    setHeaders(part.getHeaders());
  }

  /**
   * 생성자
   * 
   */
  public ParamPart(Collection headers, InputStream in, String boundary, String encoding) throws IOException {
    super(headers, in, boundary, encoding);
  }

  public String getParam(String key) {
    String value = null;

    key = key.toLowerCase();
    String temp = _text.toLowerCase();

    int index = temp.lastIndexOf(key);
    if(index == -1)
      return value;
    
    value = _text.substring(index + 1);
    index = value.indexOf("&");
    if(index != -1)
      value = value.substring(0, index);
    
    return value;
  }

  public void addParam(String key, String value) {
    _text = _text + StringUtil.decode(_text, "", "", "&") + key + "=" + value;
  }

  public boolean isParamPart() {
    return true;
  }
}

