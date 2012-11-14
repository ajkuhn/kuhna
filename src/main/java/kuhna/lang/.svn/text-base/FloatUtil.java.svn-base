package kuhna.lang;

import java.text.*;

/**
 * Float, float를 Manage하는 메쏘드들을 담은 유틸리티클래스
 *
 * @author  A.J.Kuhn
 * @version 0.3, 2002/08/06, parseFloat(x) method modified.. bug fix..
 *
 * @author  A.J.Kuhn
 * @version 0.2, 2002/05/09, parseFloat(x) method modified.. so simply...
 *
 * @author  A.J.Kuhn
 * @version 0.1, 2002/05/06
 */
public class FloatUtil {

  /**
   * input값이 radix와 같으면 output1을, 아니면 output2를 리턴한다.
   *
   * @param input 비교하고픈 float 
   * @param radix 변환을 원하는 경우의 조건
   * @param output1 
   * @param output2 
   * @return    변환된 float
   */
  public static float decode(float input, float radix, float output1, float output2) {
    if(input == radix)
      return output1;
    else
      return output2;
  }

  /**
   * input값이 radix와 같으면 output을, 아니면 input을 리턴한다.
   *
   * @param input 비교하고픈 float
   * @param radix 변환을 원하는 경우의 조건
   * @param output  변환되어질 값
   * @return    변환된 float
   */
  public static float decode(float input, float radix, float output) {
    return decode(input, radix, output, input);
  }

  /**
   * input값이 radix와 같으면 output1을, 아니면 output2를 리턴한다.
   *
   * @param input 비교하고픈 Float
   * @param radix 변환을 원하는 경우의 조건
   * @param output1 
   * @param output2 
   * @return    변환된 Float
   */
  public static Float decode(Float input, Float radix, Float output1, Float output2) {
    return (Float)ObjectUtil.decode(input, radix, output1, output2);
  }

  /**
   * input값이 radix와 같으면 output을, 아니면 input을 리턴한다.
   *
   * @param input 비교하고픈 Float
   * @param radix 변환을 원하는 경우의 조건
   * @param output  변환되어질 값
   * @return    변환된 Float
   */
  public static Float decode(Float input, Float radix, Float output) {
    return decode(input, radix, output, input);
  }

  /**
   * 숫자가 from과 to사이인지 비교한다.
   *
   * @param input 비교할 float
   * @param from  from값
   * @param to    to값
   * @return    사이에 위치하면 true, 아니면 false
   */
  public static boolean compareTo(float input, float from, float to) {
    if(input >= from && input <= to)
      return true;
    else
      return false;
  }

  /**
   * Float가 from과 to사이인지 비교한다.
   *
   * @param value 비교할 Float
   * @param from  from값
   * @param to    to값
   * @return    사이에 위치하면 true, 아니면 false
   */
  public static boolean compareTo(Float value, Float from, Float to) {
    if(value == null)
      throw new IllegalArgumentException("value arg is null");
    if(from == null)
      throw new IllegalArgumentException("from arg is null");
    if(to == null)
      throw new IllegalArgumentException("to arg is null");

    return compareTo(value.floatValue(), from.floatValue(), to.floatValue());
  }

  /**
   * 스트링값을 float로 변환한다.
   * Space는 제거하고, "001.45" 형태의 스트링 값을 1.45 float 값으로 변환한다.
   *
   * @param str
   * @return  parsing된 float
   */
  public static float parseFloat(String str) {
    if(str == null)
      return 0.0f;

    String temp = str.trim();

    if(temp.trim().length() == 0)
      return 0.0f;

    return Float.parseFloat(temp);
  }
}

