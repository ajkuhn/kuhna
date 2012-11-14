package kuhna.lang;

/**
 * Short, short를 Manage하는 메쏘드들을 담은 유틸리티클래스
 *
 * @version 0.3, 2004/11/04, added toHex(short) method by A.J.Kuhn<BR><!--
 * @version -->0.2, 2004/10/27, modified toBytes(short) method by A.J.Kuhn<BR><!--
 * @version -->0.1, 2004/09/21, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class ShortUtil {

  /**
   * input값이 radix와 같으면 output1을, 아니면 output2를 리턴한다.
   *
   * @param input   비교하고픈 short 
   * @param radix   변환을 원하는 경우의 조건
   * @param output1
   * @param output2
   * @return        변환된 short
   */
  public static short decode(short input, short radix, short output1, short output2) {
    if(input == radix)
      return output1;
    else
      return output2;
  }

  /**
   * input값이 radix와 같으면 output을, 아니면 input을 리턴한다.
   *
   * @param input  비교하고픈 short
   * @param radix  변환을 원하는 경우의 조건
   * @param output 변환되어질 값
   * @return       변환된 short
   */
  public static short decode(short input, short radix, short output) {
    return decode(input, radix, output, input);
  }

  /**
   * input값이 radix와 같으면 output1을, 아니면 output2를 리턴한다.
   *
   * @param input   비교하고픈 Short
   * @param radix   변환을 원하는 경우의 조건
   * @param output1
   * @param output2
   * @return        변환된 Short
   */
  public static Short decode(Short input, Short radix, Short output1, Short output2) {
    return (Short)ObjectUtil.decode(input, radix, output1, output2);
  }

  /**
   * input값이 radix와 같으면 output을, 아니면 input을 리턴한다.
   *
   * @param input  비교하고픈 Short
   * @param radix  변환을 원하는 경우의 조건
   * @param output 변환되어질 값
   * @return       변환된 Short
   */
  public static Short decode(Short input, Short radix, Short output) {
    return decode(input, radix, output, input);
  }

  /**
   * 숫자가 from과 to사이인지 비교한다.
   *
   * @param input 비교할 short
   * @param from  from값
   * @param to    to값
   * @return      사이에 위치하면 true, 아니면 false
   */
  public static boolean compareTo(short input, short from, short to) {
    if(input >= from && input <= to)
      return true;
    else
      return false;
  }

  /**
   * Short가 from과 to사이인지 비교한다.
   *
   * @param value 비교할 Short
   * @param from  from값
   * @param to    to값
   * @return      사이에 위치하면 true, 아니면 false
   */
  public static boolean compareTo(Short value, Short from, Short to) {
    if(value == null)
      throw new IllegalArgumentException("str arg is null");
    if(from == null)
      throw new IllegalArgumentException("from arg is null");
    if(to == null)
      throw new IllegalArgumentException("to arg is null");

    return compareTo(value.shortValue(), from.shortValue(), to.shortValue());
  }

  /**
   * 스트링값을 int로 변환한다.
   * Space는 제거하고, "001" 형태의 스트링 값을 1 int 값으로 변환한다.
   *
   * @param str
   * @return  parsing된 int
   */
  public static short parseShort(String str) {
    if(str == null)
      return 0;

    String temp = str.trim();

    if(temp.length() == 0)
      return 0;

    return Short.parseShort(temp);
  }

  public static byte[] toBytes(short s) {
    byte[] value = new byte[2];

    value[0] = (byte)(0xff & (s >>> 8));
    value[1] = (byte)(0xff & (s >>> 0));

    return value;
  }

  public static String toHex(short s) {
    return ByteUtil.toHex(toBytes(s));
  }
}

