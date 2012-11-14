package kuhna.lang;

/**
 * Byte, byte를 Manage하는 메쏘드들을 담은 유틸리티클래스
 *
 * @version 0.4, 2007/07/02, added append(byte[], byte[]) method by A.J.Kuhn<BR><!--
 * @version -->0.4, 2007/06/29, added toBytes(byte) method by A.J.Kuhn<BR><!--
 * @version -->0.3, 2004/11/04, added toHex(byte[]), toHex(byte) methods by A.J.Kuhn<BR><!--
 * @version -->0.2, 2004/10/25, added reverse(byte[]) method by A.J.Kuhn<BR><!--
 * @version -->0.1, 2004/09/20, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class ByteUtil {

  /**
   * String을 일정숫자의 byte[]로 왼쪽으로 채워fix시킨다.
   *
   * @param source
   * @param length
   * @param fill
   * @return  변환된 문자열
   */
  public static byte[] stringLPad(String source, int length, byte fill) {
    if(source == null)
      return byteLPad(null, length, fill);

    return byteLPad(source.getBytes(), length, fill);
  }

  /**
   * byte[]를 일정숫자의 byte[]로 왼쪽으로 채워fix시킨다.
   *
   * @param source
   * @param length
   * @param fill
   * @return  변환된 문자열
   */
  public static byte[] byteLPad(byte[] source, int length, byte fill) {
    if(length < 1)
      new IllegalArgumentException("length must be more than 0");

    byte[] value = new byte[length];

    if(source == null || source.length == 0) {
      for(int i=0; i<length; i++) {
        value[i] = fill;
      }
    } else { 
      if(source.length >= length) {
        System.arraycopy(source, source.length - length, value, 0, length);
      } else {
        for(int i=0; i<(length - source.length); i++) {
          value[i] = fill;
        }
        System.arraycopy(source, 0, value, length - source.length, source.length);
      }
    }

    return value;
  }

  /**
   * String을 일정숫자의 byte[]로 오른쪽으로 채워 fix시킨다.
   *
   * @param source
   * @param length
   * @param fill
   * @return  변환된 문자열
   */
  public static byte[] stringRPad(String source, int length, byte fill) {
    if(source == null)
      return byteRPad(null, length, fill);

    return byteRPad(source.getBytes(), length, fill);
  }

  /**
   * byte[]를 일정숫자의 byte[]로 오른쪽으로 채워 fix시킨다.
   *
   * @param source
   * @param length
   * @param fill
   * @return  변환된 문자열
   */
  public static byte[] byteRPad(byte[] source, int length, byte fill) {
    if(length < 1)
      new IllegalArgumentException("length must be more than 0");

    byte[] value = new byte[length];

    if(source == null || source.length == 0) {
      for(int i=0; i<length; i++) {
        value[i] = fill;
      }
    } else { 
      if(source.length >= length) {
        System.arraycopy(source, 0, value, 0, length);
      } else {
        System.arraycopy(source, 0, value, 0, source.length);
        for(int i=source.length; i<length; i++) {
          value[i] = fill;
        }
      }
    }

    return value;
  }

  public static String toHex(byte source) {
    StringBuffer buf = new StringBuffer(2);

    if(((int)source & 0xff) < 0x10)
      buf.append("0");

    buf.append(Long.toString((int)source & 0xff, 16));

    return buf.toString();
  }

  public static String toHex(byte[] source) {
    if(source == null)
      return "";

    StringBuffer buf = new StringBuffer(source.length * 2);

    for(int i=0; i<source.length; i++) {
      if(((int)source[i] & 0xff) < 0x10)
        buf.append("0");

      buf.append(Long.toString((int)source[i] & 0xff, 16));
    }

    return buf.toString();
  }

  /**
   * byte[]의 순서를 뒤집는다
   *
   * @param source 원문 byte배열
   * @return       reverse된 byte배열
   */
  public static byte[] reverse(byte[] source) {
    if(source == null)
      return null;

    byte[] temp = new byte[source.length];

    for(int i=0; i<source.length; i++) {
      temp[i] = source[source.length - i - 1];
    }

    return temp;
  }
  
  public static byte[] toBytes(byte b) {
    byte[] bs = {b};
    
    return bs;
  }
  
  public static int toInt(byte[] b) {
    return toInt(b, 0);
  }

  public static int toInt(byte[] b, int offset) {
    int value = 0;
    for(int i=0; i<4; i++) {
      int shift = (4 - 1 - i) * 8;
      value += (b[i + offset] & 0x000000FF) << shift;
    }
    return value;
  }
  
  public static long toLong(byte[] b) {
    return ((((long) b[7]) & 0xFF)
         + ((((long) b[6]) & 0xFF) << 8)
         + ((((long) b[5]) & 0xFF) << 16)
         + ((((long) b[4]) & 0xFF) << 24)
         + ((((long) b[3]) & 0xFF) << 32)
         + ((((long) b[2]) & 0xFF) << 40)
         + ((((long) b[1]) & 0xFF) << 48)
         + ((((long) b[0]) & 0xFF) << 56));
  }
  
  public static byte[] append(byte[] a, byte[] b) {
      byte[] z = new byte[a.length + b.length];
      System.arraycopy(a, 0, z, 0, a.length);
      System.arraycopy(b, 0, z, a.length, b.length);
      return z;
  }
}

