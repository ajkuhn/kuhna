package kuhna.lang;

/**
 * Integer, int를 Manage하는 메쏘드들을 담은 유틸리티클래스
 *
 * @version 0.6, 2007/06/27, added toUnsignedBytes(int) method by A.J.Kuhn<BR><!--
 * @version -->0.5.5, 2004/11/04, added toHex(int) method by A.J.Kuhn<BR><!--
 * @version -->0.5, 2004/09/21, added toBytes(x), toUnsignedInt(x) methods<BR><!--
 * @version -->0.4, 2002/08/06, parseInt(x) method modified.. bug fix..<BR><!--
 * @version -->0.3, 2002/05/09, parseInt(x) method modified.. so simply...<BR><!--
 * @version -->0.2, 2002/04/09, parseInt(x) method modified, implement trimming<BR><!--
 * @version -->0.1, 2002/03/19, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class IntegerUtil {

  /**
   * input값이 radix와 같으면 output1을, 아니면 output2를 리턴한다.
   *
   * @param input 비교하고픈 int
   * @param radix 변환을 원하는 경우의 조건
   * @param output1 
   * @param output2 
   * @return      변환된 int
   */
  public static int decode(int input, int radix, int output1, int output2) {
    if(input == radix)
      return output1;
    else
      return output2;
  }

  /**
   * input값이 radix와 같으면 output을, 아니면 input을 리턴한다.
   *
   * @param input 비교하고픈 int
   * @param radix 변환을 원하는 경우의 조건
   * @param output  변환되어질 값
   * @return      변환된 int
   */
  public static int decode(int input, int radix, int output) {
    return decode(input, radix, output, input);
  }

  /**
   * input값이 radix와 같으면 output1을, 아니면 output2를 리턴한다.
   *
   * @param input 비교하고픈 Integer
   * @param radix 변환을 원하는 경우의 조건
   * @param output1 
   * @param output2 
   * @return      변환된 Integer
   */
  public static Integer decode(Integer input, Integer radix, Integer output1, Integer output2) {
    return (Integer)ObjectUtil.decode(input, radix, output1, output2);
  }

  /**
   * input값이 radix와 같으면 output을, 아니면 input을 리턴한다.
   *
   * @param input 비교하고픈 Integer
   * @param radix 변환을 원하는 경우의 조건
   * @param output  변환되어질 값
   * @return      변환된 Integer
   */
  public static Integer decode(Integer input, Integer radix, Integer output) {
    return decode(input, radix, output, input);
  }

  /**
   * 숫자가 from과 to사이인지 비교한다.
   *
   * @param input   비교할 int
   * @param from    from값
   * @param to    to값
   * @return      사이에 위치하면 true, 아니면 false
   */
  public static boolean compareTo(int input, int from, int to) {
    if(input >= from && input <= to)
      return true;
    else
      return false;
  }

  /**
   * Integer가 from과 to사이인지 비교한다.
   *
   * @param value   비교할 Integer
   * @param from    from값
   * @param to    to값
   * @return      사이에 위치하면 true, 아니면 false
   */
  public static boolean compareTo(Integer value, Integer from, Integer to) {
    if(value == null)
      throw new IllegalArgumentException("str arg is null");
    if(from == null)
      throw new IllegalArgumentException("from arg is null");
    if(to == null)
      throw new IllegalArgumentException("to arg is null");

    return compareTo(value.intValue(), from.intValue(), to.intValue());
  }

  /**
   * 스트링값을 int로 변환한다.
   * Space는 제거하고, "001" 형태의 스트링 값을 1 int 값으로 변환한다.
   *
   * @param str
   * @return  parsing된 int
   */
  public static int parseInt(String str) {
    if(str == null)
      return 0;

    String temp = str.trim();

    if(temp.length() == 0)
      return 0;

    return Integer.parseInt(temp);
  }

  public static int toUnsignedInt(int n) {
    int value = 0;

    byte[] temp = new byte[4];

    temp[0] = (byte)(0xff & n);
    temp[1] = (byte)(0xff & (n >> 8));
    temp[2] = (byte)(0xff & (n >> 16));
    temp[3] = (byte)(0xff & (n >> 24));

    value = ((temp[0] << 24) + (temp[1] << 16) + (temp[2] << 8) + (temp[3] << 0)); 

    return value;
  }

  public static byte[] toBytes(int n) {
    byte[] value = new byte[4];

    value[0] = (byte)((n >>> 24) & 0xff);
    value[1] = (byte)((n >>> 16) & 0xff);
    value[2] = (byte)((n >>> 8) & 0xff);
    value[3] = (byte)((n >>> 0) & 0xff);

    return value;
  }
  
  public static byte[] toUnsignedBytes(int n) {
    return toBytes(toUnsignedInt(n));
  }

  public static String toHex(int n) {
    return ByteUtil.toHex(toBytes(n));
  }
}

