package kuhna.net;

import java.io.*;
import java.net.*;

import kuhna.servlet.*;
import kuhna.servlet.http.*;

/**
 * Kuhna의 Multipart 문서의 ContentHandler.<BR>
 * Content는 kuhna.servlet.MultipartBody객체를 사용한다.
 *
 * @version 0.1, 2004/04/06
 * @author  A.J.Kuhn 
 */
public class MultipartContentHandler extends ContentHandler {

  /**
   * 기본 생성자
   */
  public MultipartContentHandler() {
    super();
  }

  /**
   * Content를 리턴한다 
   *
   * @param urlc URLConnection
   * @return     MultipartBody객체
   */
  public Object getContent(URLConnection urlc) throws IOException {
    InputStream in = urlc.getInputStream();

    // type값을 얻어낸다. WebSphere의 요상한 동작땜시 두가지 방법으로 얻어내어 알맞은것을 취한다.
    String type = null;
    String type1 = urlc.getHeaderField("Content-Type");
    String type2 = urlc.getContentType();
    if(type1 == null && type2 != null)
      type = type2;
    else if(type2 == null && type1 != null)
      type = type1;
    else if (type1 != null && type2 != null)
      type = (type1.length() > type2.length() ? type1 : type2);

    // content-type이 'multipart'로 시작하지 않을경우 Exception을 throw한다.
    if(type == null || !type.toLowerCase().startsWith("multipart")) {
      throw new IOException("Posted content type must be starts with 'multipart'~");
    }

    return new MultipartBody(in, HttpUtil.getBoundary(type));
  }
}

