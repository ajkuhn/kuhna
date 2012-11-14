package kuhna.servlet.http;

import java.net.IDN;
import java.net.MalformedURLException;
import java.net.URL;

import kuhna.lang.*;

/**
 * Http Protocol의 Parsing과 관련된 메쏘드를 담은 클래스
 * 
 * @version 0.3, 2012/02/07, added toPunnyUrl() method by A.J.Kuhn<BR><!--
 * @version -->0.2.1, 2004/05/24, changed "\n" to System.getProperty("line.separator") by A.J.Kuhn<BR><!--
 * @version -->0.2, 2004/04/13, added getNoPathFilename() by A.J.Kuhn<BR><!--
 * @version -->0.1, 2004/04/07, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class HttpUtil {

  /**
   * 특정문자열로부터 boundary값을 얻어낸다. 
   *
   * @param  str parse할 대상
   * @return     boundary값. 문자열내부에 값이 없으면 null을 리턴
   */
  public static String getBoundary(String str) {
    return getValue("boundary", "=", str);
  }

  /**
   * 특정문자열로부터 filename값을 얻어낸다. 
   *
   * @param  str parse할 대상
   * @return     filename값. 문자열내부에 값이 없으면 null을 리턴
   */
  public static String getPathFilename(String str) {
    return getValue(" filename", "=", str);
  }

  /**
   * Path를 제외한 filename값을 얻어낸다. 
   *
   * @param  str parse할 대상
   * @return     filename값. 문자열내부에 값이 없으면 null을 리턴
   */
  public static String getFilename(String str) {
    String filename = getPathFilename(str);

    int slash = Math.max(filename.lastIndexOf('/'), filename.lastIndexOf('\\'));
    if(slash > -1)
      filename = filename.substring(slash + 1);

    return filename;
  }

  /**
   * 문자열로부터 해당하는 값을 얻어낸다.<BR> 
   * 가장 마지막의 값을 리턴한다. case-insensitive 부분까지 처리한다.
   *
   * @param  key   key값
   * @param  token key와 value의 구분자
   * @param  str   parse할 대상
   * @return       값. str 내부에 값이 없으면 null을 리턴
   */
  public static String getValue(String key, String token, String str) {
    key = key.toLowerCase();
    String tempStr = str.toLowerCase();
    
    int index = tempStr.lastIndexOf(key + token);

    if(index == -1)
      return null;

    String value = str.substring(index + key.length() + token.length());
    if(value.charAt(0) == '"') { // 값이 ""로 싸여져 있을경우.. 
      index = value.indexOf('"', 1);
      value = value.substring(1, index);
    } else if(value.charAt(0) == '\'') { // 값이 ''로 싸여져 있을경우... 이러한 경우가 있는가? HTTP SPEC에서 더 찾아볼것.
      index = value.indexOf('\'', 1);
      value = value.substring(1, index);
    } else {
      index = value.indexOf(' ');
      int index2 = value.indexOf(';');
      int index3 = value.indexOf(System.getProperty("line.separator"));

      int[] n = {IntegerUtil.decode(index, -1, Integer.MAX_VALUE), IntegerUtil.decode(index2, -1, Integer.MAX_VALUE), IntegerUtil.decode(index3, -1, Integer.MAX_VALUE)};
      index = MathUtil.min(n);

      if(index != Integer.MAX_VALUE)
        value = value.substring(0, index);
    }

    return value;
  }
  
  /**
   * url문자열을 받아 host부분을 punny코드 처리하여 리턴한다.
   * 
   * @param urlString http://이재현.com/가나다/index.html
   * @return http://xn--hu5b2ddy27b.com/가나다/index.html
   * @throws MalformedURLException urlString이 비정상적일 경우 throw
   */
  public static String toPunnyUrl(String urlString) throws MalformedURLException {
    URL url = new URL(urlString);
    
    return url.getProtocol() + "://" + IDN.toASCII(url.getHost()) + (url.getPort() == -1 ? "" : ":" + url.getPort()) + url.getFile();
  }
}

