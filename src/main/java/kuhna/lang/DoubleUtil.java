package kuhna.lang;

import java.text.*;

/**
 * Double, double타입 변수를 다루는 유틸리티클래스
 *
 * @version 0.3, 2002/08/06, bug fixed parseDouble(String) method by A.J.Kuhn<BR><!-- 
 * @version -->0.2, 2002/05/09, modified parseDouble(String) method by A.J.Kuhn<BR><!--
 * @version -->0.1, 2002/05/06, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class DoubleUtil {

  /**
   * input값이 radix와 같으면 output1을, 아니면 output2를 리턴한다.
   *
   * @param input   비교하고픈 double 
   * @param radix   변환을 원하는 경우의 조건
   * @param output1 input이 radix와 같을경우 변환값
   * @param output2 input이 radix와 다를경우 변환값
   * @return        변환된 double
   */
  public static double decode(double input, double radix, double output1, double output2) {
    if(input == radix)
      return output1;
    else
      return output2;
  }

  /**
   * input값이 radix와 같으면 output을, 아니면 input을 리턴한다.
   *
   * @param input  비교하고픈 double
   * @param radix  변환을 원하는 경우의 조건
   * @param output input이 radix와 같을경우 변환값
   * @return       변환된 double
   */
  public static double decode(double input, double radix, double output) {
    return decode(input, radix, output, input);
  }

  /**
   * input값이 radix와 같으면 output1을, 아니면 output2를 리턴한다.
   *
   * @param input   비교하고픈 double 
   * @param radix   변환을 원하는 경우의 조건
   * @param output1 input이 radix와 같을경우 변환값
   * @param output2 input이 radix와 다를경우 변환값
   * @return        변환된 Double
   */
  public static Double decode(Double input, Double radix, Double output1, Double output2) {
    return (Double)ObjectUtil.decode(input, radix, output1, output2);
  }

  /**
   * input값이 radix와 같으면 output을, 아니면 input을 리턴한다.
   *
   * @param input  비교하고픈 double
   * @param radix  변환을 원하는 경우의 조건
   * @param output input이 radix와 같을경우 변환값
   * @return       변환된 Double
   */
  public static Double decode(Double input, Double radix, Double output) {
    return decode(input, radix, output, input);
  }

  /**
   * double값이 특정범위 사이인지 비교한다.
   *
   * @param input 비교할 double
   * @param from  from값
   * @param to    to값
   * @return      사이에 위치하면 true, 아니면 false
   */
  public static boolean compareTo(double input, double from, double to) {
    if(input >= from && input <= to)
      return true;
    else
      return false;
  }

  /**
   * Double값이 특정범위 사이인지 비교한다.
   *
   * @param input 비교할 double
   * @param from  from값
   * @param to    to값
   * @return      사이에 위치하면 true, 아니면 false
   */
  public static boolean compareTo(Double input, Double from, Double to) {
    if(input == null)
      throw new IllegalArgumentException("input arg is null");
    if(from == null)
      throw new IllegalArgumentException("from arg is null");
    if(to == null)
      throw new IllegalArgumentException("to arg is null");

    return compareTo(input.doubleValue(), from.doubleValue(), to.doubleValue());
  }

  /**
   * 스트링값을 double로 변환한다.
   * Space는 제거하고, "001.45" 형태의 스트링 값을 1.45 double 값으로 변환한다.
   *
   * @param str
   * @return  parsing된 double
   */
  public static double parseDouble(String str) {
    if(str == null)
      return 0.0;

    String temp = str.trim();

    if(temp.trim().length() == 0)
      return 0.0;

    return Double.parseDouble(temp);
  }

  /**
   * 올림한다.
   *
   * @param src source double
   * @param pos 올림할 위치. 2 : 10의 자리에서 올림, -2 : 소숫점 셋째자리에서 올림?
   * @return  올림된 double
   */
  public static double raise(double src, int pos) {
    double value = 0.0;

    if(pos >= 0) {
      double temp = 1.0;
      for(int i=0; i<pos; i++)
        temp = temp * 10;

      double temp2 = src % temp;

      value = src + temp - temp2; 
    } else {
      double temp = 1.0;
      for(int i=0; i<-pos; i++)
        temp = temp * 0.1;

      double temp2 = src % temp;

      value = src + temp - temp2; 
    }
      
    return round(value, pos);
  }

  /**
   * 내림한다.
   *
   * @param src source double
   * @param pos 내림할 위치. 2 : 10의 자리에서 내림, -2 : 소숫점 셋째자리에서 내림?
   * @return  내림된 double
   */
  public static double cut(double src, int pos) {
    double value = 0.0d;

    if(pos >= 0) {
      double temp = 1.0d;
      for(int i=0; i<pos; i++)
        temp = temp * 10d;

      double temp2 = src % temp;

      value = src - temp2;
    } else {
      double temp = 1.0d;
      for(int i=0; i<-pos; i++)
        temp = temp * 0.1d;

      double temp2 = src % temp;

      value = src - temp2;
    }
 
    return round(value, pos);
  }

  /**
   * 반올림한다.
   *
   * @param src source double
   * @param pos 반올림할 위치. 2 : 10의 자리에서 반올림, -2 : 소숫점 셋째자리에서 반올림?
   * @return  반올림된 double
   */
  public static double round(double src, int pos) {
    double value = 0.0;

    if(pos == 0) {
      DecimalFormat df = new DecimalFormat("#####");

      value = parseDouble(df.format(src).toString());
    } else if(pos > 0) {
      double temp = 1.0;
      for(int i=0; i<pos; i++)
        temp = temp * 10;

      double temp2 = src % temp;

      if(temp2 >= temp / 2.0)
        value = src + temp - temp2; 
      else
        value = src - temp2;
    } else {
      StringBuffer buffer = new StringBuffer();
      for(int i=0; i<-pos; i++)
        buffer.append("0");

      DecimalFormat df = new DecimalFormat("####0." + buffer.toString());

      value = parseDouble(df.format(src).toString());
    }
      
    return value;
  }
}

