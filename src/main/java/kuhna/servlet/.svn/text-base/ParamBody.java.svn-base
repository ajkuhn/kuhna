package kuhna.servlet;

import java.io.*;
import java.util.*;

import kuhna.lang.*;

/**
 * Parameter형태의 Body객체
 * 
 * @version 0.1, 2004/04/22
 * @author  A.J.Kuhn
 */
public class ParamBody extends TextBody {
  
  public ParamBody() {
    super();
    _text = new String();
  }

  public ParamBody(String encoding) {
    super(encoding);
  }

  /**
   * 생성자.
   *
   * @param in       Body의 InputStream
   */
  public ParamBody(InputStream in) throws IOException {
    super(in);
  }

  /**
   * 생성자. 일단 대충 구현함.
   *
   * @param in       Body의 InputStream
   * @param encoding 인코딩
   */
  public ParamBody(InputStream in, String encoding) throws IOException {
    super(in, encoding);
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
}

