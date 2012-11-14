package kuhna.lang;

import java.io.*;

/**
 * Throwable 클래스의 보조클래스
 *
 * @version 0.1, 2003/11/03
 * @author  A.J.Kuhn
 */
public class ThrowableUtil {

  /**
   * Throwable이 생성된 클래스.메쏘드이름을 리턴한다.
   *
   * @param t 분석대상 Throwable
   * @return  클래스이름.메쏘드이름
   */
  public static String getBirthClassDotMethodName(Throwable t) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    String s = null;

    synchronized(sw) {
      t.printStackTrace(pw);
      s = sw.toString();
      sw.getBuffer().setLength(0);
    }

    try {
      pw.close();
      sw.close();
    } catch(IOException ex) {
      ;
    }

    int begin = s.indexOf("at ") + 3;
    int end = s.indexOf("(", begin);

    return s.substring(begin, end);
  }

  /**
   * Throwable이 생성된 클래스이름을 리턴한다.
   *
   * @param t 분석대상 Throwable
   * @return  클래스이름
   */
  public static String getBirthClassName(Throwable t) {
    String s = getBirthClassDotMethodName(t);

    return s.substring(0, s.lastIndexOf("."));
  }

  /**
   * Throwable이 생성된 메쏘드이름을 리턴한다.
   *
   * @param t 분석대상 Throwable
   * @return  메쏘드이름
   */
  public static String getBirthMethodName(Throwable t) {
    String s = getBirthClassDotMethodName(t);

    return s.substring(s.lastIndexOf(".") + 1);
  }
}

