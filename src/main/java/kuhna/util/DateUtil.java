package kuhna.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 날짜에 관한 메쏘드들을 담은 유틸리티클래스<BR>
 * getUtilDate(strDate) 메쏘드의 강제 yyyyMMddHHmmssSSS변환시도를 넣어야 말지 고민된다.
 *
 * @version 0.7.2, 2010/03/18, added addMillisecond() method by A.J.Kuhn<BR><!-- 
 * @version -->0.7.1, 2009/10/14, fixed getUtilDate(String) bug by A.J.Kuhn<BR><!-- 
 * @version -->0.7, 2007/05/31, refactoring by A.J.Kuhn<BR><!-- 
 * @version -->0.6, 2005/08/23, added Locale include methods by A.J.Kuhn<BR><!-- 
 * @version -->0.5, 2005/03/16, refactoring by A.J.Kuhn<BR><!-- 
 * @version -->0.4.2, 2004/07/28, added MILLIS_PER_WEEK value and changed 'MILLIS~' variable type int to long by A.J.Kuhn<BR><!-- 
 * @version -->0.4.1, 2002/09/06, fixed MILLIS_PER_SECOND value bug by A.J.Kuhn<BR><!--
 * @version -->0.4, 2002/05/14, added checkTime(x), checkDate(x) methods by A.J.Kuhn<BR><!--  
 * @version -->0.3, 2002/05/09, added checkTime(x), isTime(x), getSQLTime(), getSQLTime(x) methods by A.J.Kuhn<BR><!-- 
 * @version -->0.2, 2002/03/05, renewal by A.J.Kuhn<BR><!--
 * @version -->0.1, 2002/01/17, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class DateUtil {

  /** 디폴트 날짜 포맷 */
  private static String _pattern = new SimpleDateFormat().toPattern();

  /** 1초를 millisecond로 환산한 값 */
  public final static long MILLIS_PER_SECOND = 1000;

  /** 1분을 millisecond로 환산한 값 */
  public final static long MILLIS_PER_MINUTE = MILLIS_PER_SECOND * 60;

  /** 1시간을 millisecond로 환산한 값 */
  public final static long MILLIS_PER_HOUR = MILLIS_PER_MINUTE * 60;

  /** 1일을 millisecond로 환산한 값 */
  public final static long MILLIS_PER_DAY = MILLIS_PER_HOUR * 24;

  /** 1주를 millisecond로 환산한 값 */
  public final static long MILLIS_PER_WEEK = MILLIS_PER_DAY * 7;

  public static void setPattern(String pattern) {
    _pattern = pattern;
  }

  public static String getPattern() {
    return _pattern;
  }
  
  private static String getyyyyMMddHHmmssSSS(String strDate) {
    int length = strDate.length();

    if(!(length == 6 || length == 8 || length == 9 || length == 14 || length == 17))
      throw new IllegalArgumentException("strDate pattern is not available(" + strDate + "). must be HHmmss or HHmmssSSS or yyyyMMdd or yyyyMMddHHmmss or yyyyMMddHHmmssSSS");

    if(strDate.length() == 6)
      strDate = "19700101" + strDate + "000";
    else if(strDate.length() == 8)
      strDate += "000000000"; 
    else if(strDate.length() == 9)
      strDate = "19700101" + strDate;
    else if(strDate.length() == 14)
      strDate = strDate + "000"; 

    return strDate;
  }

  /**
   * 현재 시간에 대한 java.util.Date객체를 얻는다.
   *
   * @return 현재시간의 java.util.Date
   */
  public static java.util.Date getUtilDate() {
    return new java.util.Date(System.currentTimeMillis());
  }

  /**
   * 주어진 String에 대한 java.util.Date객체를 얻는다.
   *
   * @param strDate 본클래스의 default pattern의 문자열 혹은 yyyyMMddHHmmssSSS or yyyyMMddHHmmss or yyyyMMdd or HHmmssSSS or HHmmss
   * @return        java.util.Date
   */
  public static java.util.Date getUtilDate(String strDate) {
    try {
      return getUtilDate(_pattern, strDate);
    } catch(Exception ex) {
      try {
        return getUtilDate("yyyyMMddHHmmssSSS", getyyyyMMddHHmmssSSS(strDate));
      } catch(Exception ex2) {
        throw new IllegalArgumentException("strDate pattern is not available(" + strDate + "). pattern must be one of " + _pattern + "or yyyyMMddHHmmssSSS or yyyyMMddHHmmss or yyyyMMdd or HHmmssSSS or HHmmss");
      }
    }
  }

  /**
   * 주어진 String에 대한 java.util.Date객체를 얻는다.
   *
   * @param inPattern strDate의 parttern
   * @param strDate   변환할 문자열
   * @return          변환된 java.util.Date
   */
  public static java.util.Date getUtilDate(String inPattern, String strDate) {
    SimpleDateFormat sdf = new SimpleDateFormat(inPattern);
    
    try {
      return sdf.parse(strDate);
    } catch(ParseException ex) {
      throw new IllegalArgumentException("inPattern is '" + inPattern + "'. strDate pattern is not available(" + strDate + ") caused by " + ex.getMessage());
    }
  }

  /**
   * 현재 시간에 대한 java.sql.Date객체를 얻는다.
   *
   * @return 현재시간의 java.sql.Date
   */
  public static java.sql.Date getSQLDate() {
    return new java.sql.Date(System.currentTimeMillis());
  }

  /**
   * 주어진 String에 대한 java.sql.Date객체를 얻는다.
   *
   * @param strDate 본클래스의 default pattern의 문자열 혹은 yyyyMMddHHmmssSSS or yyyyMMddHHmmss or yyyyMMdd or HHmmssSSS or HHmmss
   * @return        java.sql.Date
   */
  public static java.sql.Date getSQLDate(String strDate) {
    return new java.sql.Date(getUtilDate(strDate).getTime());
  }

  /**
   * 주어진 String에 대한 java.sql.Date객체를 얻는다.
   *
   * @param inPattern strDate의 parttern
   * @param strDate   변환할 문자열
   * @return          java.sql.Date
   */
  public static java.sql.Date getSQLDate(String inPattern, String strDate) {
    return new java.sql.Date(getUtilDate(inPattern, strDate).getTime());
  }

  /**
   * 현재 시간에 대한 java.sql.Time객체를 얻는다.
   *
   * @return 현재시간의 java.sql.Time
   */
  public static java.sql.Time getSQLTime() {
    return new java.sql.Time(System.currentTimeMillis());
  }

  /**
   * 주어진 String에 대한 java.util.Time객체를 얻는다.
   *
   * @param strTime 본클래스의 default pattern의 문자열 혹은 yyyyMMddHHmmssSSS or yyyyMMddHHmmss or yyyyMMdd or HHmmssSSS or HHmmss
   * @return        java.sql.Time
   */
  public static java.sql.Time getSQLTime(String strTime) {
    return new java.sql.Time(getUtilDate(strTime).getTime());
  }

  /**
   * 주어진 String에 대한 java.sql.Time객체를 얻는다.
   *
   * @param inPattern strTime의 parttern
   * @param strTime   변환할 문자열
   * @return          java.sql.Time
   */
  public static java.sql.Time getSQLTime(String inPattern, String strTime) {
    return new java.sql.Time(getUtilDate(inPattern, strTime).getTime());
  }

  /**
   * 현재의 시간을 Default Format 스트링으로 얻는다.
   *
   * @return Default로 formatting된 날짜 스트링
   */
  public static String getString() {
    SimpleDateFormat sdf = new SimpleDateFormat(_pattern);

    return sdf.format(getUtilDate());
  }

  /**
   * 주어진 Date를 Default format 스트링으로  얻는다.
   *
   * @param date 변환할 date
   * @return     formatting된 날짜 스트링
   */
  public static String getString(java.util.Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat(_pattern);

    return sdf.format(date);
  }

  /**
   * 현재의 시간을 주어진 format 스트링으로 얻는다.
   *
   * @param outPattern 리턴받을 String의 pattern
   * @return           formatting된 날짜 스트링
   */
  public static String getString(String outPattern) {
    return getString(getUtilDate(), outPattern);
  }

  /**
   * 주어진 Date를 주어진 format 스트링으로  얻는다.
   *
   * @param date       변환할 date
   * @param outPattern 리턴받을 String의 pattern 
   * @return           formatting된 날짜 스트링
   */
  public static String getString(java.util.Date date, String outPattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(outPattern);

    return sdf.format(date);
  }

  /**
   * 현재의 시간을 Default Format 스트링으로 얻는다.
   *
   * @return Default로 formatting된 날짜 스트링
   */
  public static String getString(Locale locale) {
    SimpleDateFormat sdf = new SimpleDateFormat(_pattern, locale);

    return sdf.format(getUtilDate());
  }

  /**
   * 주어진 Date를 Default format 스트링으로  얻는다.
   *
   * @param date 변환할 date
   * @return     formatting된 날짜 스트링
   */
  public static String getString(java.util.Date date, Locale locale) {
    SimpleDateFormat sdf = new SimpleDateFormat(_pattern, locale);

    return sdf.format(date);
  }

  /**
   * 현재의 시간을 주어진 format 스트링으로 얻는다.
   *
   * @param outPattern 리턴받을 String의 pattern
   * @return           formatting된 날짜 스트링
   */
  public static String getString(String outPattern, Locale locale) {
    return getString(getUtilDate(), outPattern, locale);
  }

  /**
   * 주어진 Date를 주어진 format 스트링으로  얻는다.
   *
   * @param date       변환할 date
   * @param outPattern 리턴받을 String의 pattern 
   * @return           formatting된 날짜 스트링
   */
  public static String getString(java.util.Date date, String outPattern, Locale locale) {
    SimpleDateFormat sdf = new SimpleDateFormat(outPattern, locale);

    return sdf.format(date);
  }

  /**
   * 주어진 String을 주어진 format 스트링으로  얻는다.
   *
   * @param strDate    변환할 date String.
   * @param outPattern 리턴받을 String의 pattern
   * @return           formatting된 날짜 스트링
   */
  public static String getString(String strDate, String outPattern, Locale locale) {
    SimpleDateFormat sdf = new SimpleDateFormat(outPattern, locale);

    return sdf.format(getUtilDate(strDate));
  }

  /**
   * 주어진 pattern의 주어진 String을 주어진 format 스트링으로  얻는다.
   *
   * @param inPattern  strDate의 parttern
   * @param strDate    변환할 date String
   * @param outPattern 리턴받을 String의 pattern 
   * @return           formatting된 날짜 스트링
   */
  public static String getString(String inPattern, String strDate, String outPattern, Locale locale) {
    SimpleDateFormat sdf = new SimpleDateFormat(outPattern, locale);

    return sdf.format(getUtilDate(inPattern, strDate));
  }

  /**
   * 주어진 String을 주어진 format 스트링으로  얻는다.
   *
   * @param strDate    변환할 date String.
   * @param outPattern 리턴받을 String의 pattern
   * @return           formatting된 날짜 스트링
   */
  public static String getString(String strDate, String outPattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(outPattern);

    return sdf.format(getUtilDate(strDate));
  }

  /**
   * 주어진 pattern의 주어진 String을 주어진 format 스트링으로  얻는다.
   *
   * @param inPattern  strDate의 parttern
   * @param strDate    변환할 date String
   * @param outPattern 리턴받을 String의 pattern 
   * @return           formatting된 날짜 스트링
   */
  public static String getString(String inPattern, String strDate, String outPattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(outPattern);

    return sdf.format(getUtilDate(inPattern, strDate));
  }

  /**
   * 주어진 java.util.Date객체에 밀리초를 더한 java.util.Date객체를 얻는다.
   *
   * @param date   기준이 되는 Date 
   * @param second Date객체에 더할 밀리초 수치
   * @return       더해진 Date객체
   */
  public static java.util.Date addMillisecond(java.util.Date date, long millis) {
    return new java.util.Date(date.getTime() + millis);
  }
  
  /**
   * 주어진 java.util.Date객체에 초를 더한 java.util.Date객체를 얻는다.
   *
   * @param date   기준이 되는 Date 
   * @param second Date객체에 더할 초 수치
   * @return       더해진 Date객체
   */
  public static java.util.Date addSecond(java.util.Date date, int second) {
    return new java.util.Date(date.getTime() + MILLIS_PER_SECOND * (long)second);
  }

  /**
   * 주어진 java.util.Date객체에 분을 더한 java.util.Date객체를 얻는다.
   *
   * @param date   기준이 되는 Date 
   * @param minute Date객체에 더할 분 수치
   * @return       더해진 Date객체
   */
  public static java.util.Date addMinute(java.util.Date date, int minute) {
    return new java.util.Date(date.getTime() + MILLIS_PER_MINUTE * (long)minute);
  }

  /**
   * 주어진 java.util.Date객체에 시간을 더한 java.util.Date객체를 얻는다.
   *
   * @param date 기준이 되는 Date 
   * @param hour Date객체에 더할 시간 수치
   * @return     더해진 Date객체
   */
  public static java.util.Date addHour(java.util.Date date, int hour) {
    return new java.util.Date(date.getTime() + MILLIS_PER_HOUR * (long)hour);
  }

  /**
   * 주어진 java.util.Date객체에 날짜를 더한 java.util.Date객체를 얻는다.
   *
   * @param date 기준이 되는 Date 
   * @param day  Date객체에 더할 날짜 수치
   * @return     더해진 Date객체
   */
  public static java.util.Date addDay(java.util.Date date, int day) {
    return new java.util.Date(date.getTime() + MILLIS_PER_DAY * (long)day);
  }

  /**
   * 주어진 java.util.Date객체에 주를 더한 java.util.Date객체를 얻는다.
   *
   * @param date 기준이 되는 Date 
   * @param week Date객체에 더할 주 수치
   * @return     더해진 Date객체
   */
  public static java.util.Date addWeek(java.util.Date date, int week) {
    return new java.util.Date(date.getTime() + MILLIS_PER_WEEK * (long)week);
  }
}
