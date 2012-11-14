package kuhna.servlet.http;

/**
 * HTML을 Managing하는 메쏘드를 담은 클래스
 *
 * @author  A.J.Kuhn
 * @version 0.1, 2002/04/03
 */
public class HtmlUtil {

  /**
   * 일반 특수문자를 HTML Tag로 변환한다. 
   *
   * @param   str 일반 String       ex) <, >, &, \n, \t, \" 등이 포함됨
   * @return    HTML로 변환된 String  ex) &lt;, &gt;, &amp;, &nbsp;, &quot; 등이 포함됨
   */
  public static String toHtml(String str) {
    if(str == null)
      return "";

    char[] arr = str.toCharArray();
    StringBuffer sb = new StringBuffer();

    for(int i=0; i<arr.length; i++) {
      switch(arr[i]) {
        case '<' :
          sb.append("&lt;");
          break;
        case '>' :
          sb.append("&gt;");
          break;
        case '&' :
          sb.append("&amp;");
          break;
        case '\n' :
          sb.append("<br>");
          break;
        case '\t' :
          sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
          break;
        case ' ' :
          sb.append("&nbsp;");
          break;
        case '\"':
          sb.append("&quot;");
          break;
        case '\'':
          sb.append("&#39;");
          break;
        default :
          sb.append(arr[i]);
      }
    }

    return sb.toString();
  }

  /**
   * "http://"로 시작하는 문장을 링크문으로 바꾼다.
   *
   * @param   str 일반 String
   * @return    변환된 String
   */
  public static String toLink(String str) {
    return toLink(str, "_self");
  }

  /**
   * "http://"로 시작하는 문장을 링크문으로 바꾼다.
   *
   * @param   str   일반 String
   * @param   target  target Frame 이름
   * @return      변환된 String
   */
  public static String toLink(String str, String target) {
    if(str == null)
      return "";

    // 구현 할것.

    return str;
  }

  /**
   * Input 으로 변환
   *
   * @param   str 일반 String
   * @return    변환된 String
   */
  public static String toInput(String str) {
    if(str == null)
      return "";

    char[] arr = str.toCharArray();
    StringBuffer sb = new StringBuffer();

    for(int i=0; i<arr.length; i++) {
      switch(arr[i]) {
        case '\"':
          sb.append("&quot;");
          break;
        case '\'':
          sb.append("&#39;");
          break;
        default :
          sb.append(arr[i]);
      }
    }

    return sb.toString();
  }
}

