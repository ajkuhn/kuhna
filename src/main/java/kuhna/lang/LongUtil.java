package kuhna.lang;

/**
 * Long, long를 Manage하는 메쏘드들을 담은 유틸리티클래스
 *
 * @version 0.1, 2007/06/29, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class LongUtil {

  /**
   * input값이 radix와 같으면 output1을, 아니면 output2를 리턴한다.
   *
   * @param input 비교하고픈 long
   * @param radix 변환을 원하는 경우의 조건
   * @param output1 
   * @param output2 
   * @return      변환된 long
   */
  public static long decode(long input, long radix, long output1, long output2) {
    if(input == radix)
      return output1;
    else
      return output2;
  }

  /**
   * input값이 radix와 같으면 output을, 아니면 input을 리턴한다.
   *
   * @param input 비교하고픈 long
   * @param radix 변환을 원하는 경우의 조건
   * @param output  변환되어질 값
   * @return      변환된 long
   */
  public static long decode(long input, long radix, long output) {
    return decode(input, radix, output, input);
  }

  /**
   * input값이 radix와 같으면 output1을, 아니면 output2를 리턴한다.
   *
   * @param input 비교하고픈 Long
   * @param radix 변환을 원하는 경우의 조건
   * @param output1 
   * @param output2 
   * @return      변환된 Long
   */
  public static Long decode(Long input, Long radix, Long output1, Long output2) {
    return (Long)ObjectUtil.decode(input, radix, output1, output2);
  }

  /**
   * input값이 radix와 같으면 output을, 아니면 input을 리턴한다.
   *
   * @param input 비교하고픈 Long
   * @param radix 변환을 원하는 경우의 조건
   * @param output  변환되어질 값
   * @return      변환된 Long
   */
  public static Long decode(Long input, Long radix, Long output) {
    return decode(input, radix, output, input);
  }

  /**
   * 숫자가 from과 to사이인지 비교한다.
   *
   * @param input   비교할 long
   * @param from    from값
   * @param to    to값
   * @return      사이에 위치하면 true, 아니면 false
   */
  public static boolean compareTo(long input, long from, long to) {
    if(input >= from && input <= to)
      return true;
    else
      return false;
  }

  /**
   * Long가 from과 to사이인지 비교한다.
   *
   * @param value   비교할 Long
   * @param from    from값
   * @param to    to값
   * @return      사이에 위치하면 true, 아니면 false
   */
  public static boolean compareTo(Long value, Long from, Long to) {
    if(value == null)
      throw new IllegalArgumentException("str arg is null");
    if(from == null)
      throw new IllegalArgumentException("from arg is null");
    if(to == null)
      throw new IllegalArgumentException("to arg is null");

    return compareTo(value.longValue(), from.longValue(), to.longValue());
  }

  /**
   * 스트링값을 long으로 변환한다.
   * Space는 제거하고, "001" 형태의 스트링 값을 1 long 값으로 변환한다.
   *
   * @param str
   * @return  parsing된 long
   */
  public static long parseLong(String str) {
    if(str == null)
      return 0;

    String temp = str.trim();

    if(temp.length() == 0)
      return 0;

    return Long.parseLong(temp);
  }

  public static long toUnsignedLong(long l) {
    long value = 0;

    byte[] temp = new byte[8];

    temp[0] = (byte)(0xff & l);
    temp[1] = (byte)(0xff & (l >> 8));
    temp[2] = (byte)(0xff & (l >> 16));
    temp[3] = (byte)(0xff & (l >> 24));
    temp[4] = (byte)(0xff & (l >> 32));
    temp[5] = (byte)(0xff & (l >> 40));
    temp[6] = (byte)(0xff & (l >> 48));
    temp[7] = (byte)(0xff & (l >> 56));

    value = ((temp[0] << 56) + (temp[1] << 48) + (temp[2] << 40) + (temp[3] << 32) + (temp[4] << 24) + (temp[5] << 16) + (temp[6] << 8) + (temp[7] << 0)); 

    return value;
  }

  public static byte[] toBytes(long l) {
    byte[] value = new byte[8];

    value[0] = (byte)((l >>> 56) & 0xff);
    value[1] = (byte)((l >>> 48) & 0xff);
    value[2] = (byte)((l >>> 40) & 0xff);
    value[3] = (byte)((l >>> 32) & 0xff);
    value[4] = (byte)((l >>> 24) & 0xff);
    value[5] = (byte)((l >>> 16) & 0xff);
    value[6] = (byte)((l >>> 8) & 0xff);
    value[7] = (byte)((l >>> 0) & 0xff);

    return value;
  }
  
  public static byte[] toUnsignedBytes(long l) {
    return toBytes(toUnsignedLong(l));
  }

  public static String toHex(long l) {
    return ByteUtil.toHex(toBytes(l));
  }
}

