package kuhna.lang;

/**
 * Object를 Manage하는 메쏘드들을 담은 유틸리티클래스
 *
 * @author  A.J.Kuhn
 * @version 0.2, 2002/05/27, added decode(x,x,x,x,x,x), decode(x,x,x,x,x) methods
 *
 * @author  A.J.Kuhn
 * @version 0.1, 2002/03/19
 */
public class ObjectUtil {

  /**
   * input값이 radix와 같으면 output1을, 아니면 output2를 리턴한다.<BR>
   * equals()메쏘드가 적절히 Overriding 되어있는 Object들만 정상적인 결과를 출력한다.
   *
   * @param input 비교하고픈 객체
   * @param radix 변환을 원하는 경우의 조건
   * @param output1 
   * @param output2 
   * @return    변환된 객체
   */
  public static Object decode(Object input, Object radix, Object output1, Object output2) {
    if(input == null) {
      if(radix == null)
        return output1;
      else
        return output2;
    } else {
      if(input.equals(radix))
        return output1;
      else
        return output2;
    }
  }

  /**
   * input값이 radix1와 같으면 output1을, radix2와 같으면 output2를, 아니면 output3를 리턴한다.<BR>
   * equals()메쏘드가 적절히 Overriding 되어있는 Object들만 정상적인 결과를 출력한다.
   *
   * @param input 비교하고픈 객체
   * @param radix1  변환을 원하는 경우의 조건1
   * @param radix2  변환을 원하는 경우의 조건1
   * @param output1 
   * @param output2 
   * @param output3 
   * @return    변환된 객체
   */
  public static Object decode(Object input, Object radix1, Object output1, Object radix2, Object output2, Object output3) {
    if(input == null) {
      if(radix1 == null)
        return output1;
      if(radix2 == null)
        return output2;

      return output3;
    } else {
      if(input.equals(radix1))
        return output1;
      if(input.equals(radix2))
        return output2;

      return output3;
    }
  }

  /**
   * input값이 radix와 같으면 output을, 아니면 input을 리턴한다.<BR>
   * equals()메쏘드가 적절히 Overriding 되어있는 Object들만 정상적인 결과를 출력한다.
   *
   * @param input 비교하고픈 객체
   * @param radix 변환을 원하는 경우의 조건
   * @param output  변환되어질 값
   * @return    변환된 객체
   */
  public static Object decode(Object input, Object radix, Object output) {
    return decode(input, radix, output, input);
  }

  /**
   * input값이 radix1와 같으면 output1을, radix2와 같으면 output2를, 아니면 input을 리턴한다.<BR>
   * equals()메쏘드가 적절히 Overriding 되어있는 Object들만 정상적인 결과를 출력한다.
   *
   * @param input 비교하고픈 객체
   * @param radix1  변환을 원하는 경우의 조건1
   * @param radix2  변환을 원하는 경우의 조건2
   * @param output1 
   * @param output2 
   * @return    변환된 객체
   */
  public static Object decode(Object input, Object radix1, Object output1, Object radix2, Object output2) {
    return decode(input, radix1, output1, radix2, output2, input);
  }
}

