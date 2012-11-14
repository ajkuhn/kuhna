package kuhna.net;

import java.net.*;

/**
 * Kuhna의 ContentHandlerFactory. JAVA URL Framework 의 Kuhna구현이다.<BR>
 * <BR>
 * 현재지원 MIME Type은<BR>
 * - multipart~<BR>
 * <BR>
 * 이다.
 *
 * @version 0.1, 2004/04/06
 * @author  A.J.Kuhn 
 */
public class KuhnaContentHandlerFactory extends Object implements ContentHandlerFactory {

  /**
   * 기본 생성자.
   */
  public KuhnaContentHandlerFactory() {
    super();
  }

  /**
   * 본 Factory가 정의하는 MIME type별 ContentHandler를 리턴한다.<BR>
   * 지원하는 MIME type이 없는 경우 Null을 리턴한다.<BR>
   * <BR>
   * 현재지원 MIME Type은<BR>
   * - multipart~ (return kuhna.net.MultipartContentHandler)<BR>
   * <BR>
   * 이다.
   *
   * @param mimetype MIME type 문자열
   * @return         MIME type별 ContentHandler
   */
  public ContentHandler createContentHandler(String mimetype) {
    ContentHandler handler = null;

    if(mimetype == null)
      return null;

    if(mimetype.length() >= 9 && mimetype.toLowerCase().startsWith("multipart"))
      handler = new MultipartContentHandler();
    // 추후 다른 Type도 구현한다.
    
    return handler;
  }
}

