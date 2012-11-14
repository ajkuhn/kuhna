package kuhna.sql;

/**
 * SQL을 Managing하는 메쏘드를 담은 클래스
 *
 * @version	0.4, 2004/03/23, moved getRecord() method to SQLCoActor, added case relative final attribute by A.J.Kuhn<BR><!--
 * @version	-->0.3, 2003/10/23, added getRecord(x,x,x,x) method by A.J.Kuhn<BR><!--
 * @version	-->0.2, 2002/05/31, added toSQLString(x,x) method, modified toSQLString(x) method by A.J.Kuhn<BR><!--
 * @version	-->0.1, 2002/04/09, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class SQLUtil {

  /** SQLActor군에서 사용하는 Field Case 모드. DB의 필드이름 그대로 */
  public final static int DEFAULT_CASE = 0;
  /** SQLActor군에서 사용하는 Field Case 모드. DB의 필드이름을 대문자로 */
  public final static int UPPER_CASE = 1;
  /** SQLActor군에서 사용하는 Field Case 모드. DB의 필드이름을 소문자로 */
  public final static int LOWER_CASE = 2;

  /**
   * '를 ''로 바꾼다.
   *
   * @param		str	일반 String
   * @return		변환된 String
   */
  public static String toSQLString(String str) {
    return toSQLString(str, false);
  }

  /**
   * '를 ''로 바꾸고...
   *
   * @param		str		일반 String
   * @param		flag	true : str의 시작과 끝에 quato를 넣음 
   * @return			변환된 String
   */
  public static String toSQLString(String str, boolean flag) {
    if(str == null)
      return null;

    char[] arr = str.toCharArray();
    StringBuffer sb = new StringBuffer();

    for(int i=0; i<arr.length; i++) {
      switch(arr[i]) {
        case '\'' :
          sb.append("''");
          break;
        default :
          sb.append(arr[i]);
      }
    }

    if(flag == true) {
      sb.insert(0, '\''); 
      sb.append('\''); 
    }

    return sb.toString();
  }
}

