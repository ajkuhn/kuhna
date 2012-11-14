package kuhna.lang;

import java.io.*;
import java.text.*;

/**
 * String을 Manage하는 메쏘드들을 담은 유틸리티클래스
 *
 * @version 0.5.8, 2004/11/04, added reverse method by A.J.Kuhn<BR><!--
 * @version -->0.5.7, 2004/10/08, added toCString method by A.J.Kuhn<BR><!--
 * @version -->0.5.6, 2004/05/03, byteSubstring(x,x,x) method bug fixed by A.J.Kuhn<BR><!--
 * @version -->0.5.5, 2004/04/22, added encodingTo(x,x) method by A.J.Kuhn<BR><!--
 * @version -->0.5, 2003/01/23, added substring(x,x), substring(x,x,x), byteSubstring(x,x,x), byteSubstring(x,x) methods by A.J.Kuhn<BR><!--
 * @version -->0.4, 2002/12/11, added byteLength(x) method by A.J.Kuhn<BR><!--
 * @version -->0.3, 2002/09/06, added replace(x,x,x) method, but since JDK1.4, String class has replace method by A.J.Kuhn<BR><!--
 * @version -->0.2, 2002/05/27, added decode(x,x,x,x,x,x), decode(x,x,x,x,x) methods by A.J.Kuhn<BR><!--
 * @version -->0.1, 2002/01/17, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class StringUtil {

  /**
   * String을 특정 인코딩 타입으로 부터 특정 인코딩 타입으로 변환한다.<BR>
   * getBytes로는 NullPointException이 발생할 수 있으므로 이를 예방한다.
   *
   * @param str   Original String
   * @param inEnc Original String의 Encoding Type
   * @param outEnc  변환하고자 하는 Encoding Type
   * @return    변환된 String
   */
  public static String encodingTo(String str, String inEnc, String outEnc) throws UnsupportedEncodingException {
    if(str == null) {
      return null;
    } else {
      if(inEnc == null || outEnc == null)
        throw new IllegalArgumentException("Encoding type argument is null");

      return new String (str.getBytes(inEnc), outEnc);
    }
  }

  /**
   * String을 기본 인코딩 타입으로 부터 특정 인코딩 타입으로 변환한다.<BR>
   * getBytes로는 NullPointException이 발생할 수 있으므로 이를 예방한다.
   *
   * @param str   Original String
   * @param outEnc  변환하고자 하는 Encoding Type
   * @return    변환된 String
   */
  public static String encodingTo(String str, String outEnc) throws UnsupportedEncodingException {
    if(str == null) {
      return null;
    } else {
      if(outEnc == null)
        throw new IllegalArgumentException("Encoding type argument is null");

      return new String (str.getBytes(), outEnc);
    }
  }

  /**
   * input값이 radix와 같으면 output1을, 아니면 output2를 리턴한다.
   *
   * @param input 비교하고픈 문자열
   * @param radix 변환을 원하는 경우의 조건
   * @param output1 
   * @param output2 
   * @return    변환된 문자열
   */
  public static String decode(String input, String radix, String output1, String output2) {
    return (String)ObjectUtil.decode(input, radix, output1, output2);
  }

  public static String decode(String input, String radix1, String output1, String radix2, String output2, String output3) {
    return (String)ObjectUtil.decode(input, radix1, output1, radix2, output2, output3);
  }

  /**
   * input값이 radix와 같으면 output을, 아니면 input을 리턴한다.
   *
   * @param input 비교하고픈 문자열
   * @param radix 변환을 원하는 경우의 조건
   * @param output  변환되어질 값
   * @return    변환된 문자열
   */
  public static String decode(String input, String radix, String output) {
    return decode(input, radix, output, input);
  }

  public static String decode(String input, String radix1, String output1, String radix2, String output2) {
    return decode(input, radix1, output1, radix2, output2, input);
  }

  /**
   * str의 String적 서열이 from과 to사이인지 비교한다.
   *
   * @param str   비교할 String
   * @param from    from값
   * @param to    to값
   * @return      사이에 위치하면 true, 아니면 false
   */
  public static boolean compareTo(String str, String from, String to) {
    if(str == null)
      throw new IllegalArgumentException("str arg is null");
    if(from == null)
      throw new IllegalArgumentException("from arg is null");
    if(to == null)
      throw new IllegalArgumentException("to arg is null");

    if(str.compareTo(from) < 0 || str.compareTo(to) > 0)
      return false;
    else
      return true;
  }

  /**
   * 스트링을 일정문자의 갯수로 fix시킨다.
   *
   * @param source
   * @param length
   * @param fill
   * @return    변환된 문자열
   */
  public static String stringLPad(String source, int length, char fill) {
    String forFill = String.valueOf(fill);

    if(source == null) {
      String temp = "";
      for(int i=0; i<length; i++) {
        temp = temp + forFill;
      }

      return temp;
    } else { 
      if(source.length() >= length) {
        return source.substring(source.length() - length);
      } else {
        String temp = source;
        for(int i=0; i<(length - source.length()); i++) {
          temp = forFill + temp;
        }

        return temp;
      }
    }
  }

  /**
   * 스트링을 일정문자의 갯수로 fix시킨다. 오른쪽에 fix를 위한 문자열들이 들어간다.
   *
   * @param source
   * @param length
   * @param fill
   * @return  변환된 문자열
   */
  public static String stringRPad(String source, int length, char fill) {
    String forFill = String.valueOf(fill);

    if(source == null) {
      String temp = "";
      for(int i=0; i<length; i++) {
        temp = temp + forFill;
      }

      return temp;
    } else { 
      if(source.length() >= length) {
        return source.substring(0, length);
      } else {
        String temp = source;
        for(int i=0; i<(length - source.length()); i++) {
          temp = temp + forFill;
        }

        return temp;
      }
    }
  }

  public static int byteLength(String source) {
    if(source == null)
      return 0;

    return source.getBytes().length;
  }

  /**
   * 스트링을 일정문자의 Byte갯수로 fix시킨다.
   *
   * @param source
   * @param length
   * @param fill
   * @return  변환된 문자열
   */
  public static String byteLPad(String source, int length, char fill) {
    String forFill = String.valueOf(fill);

    if(source == null) {
      String temp = "";
      for(int i=0; i<length; i++) {
        temp = temp + forFill;
      }

      return temp;
    } else { 
      byte[] sourceByte = source.getBytes();
      int sourceLength = sourceByte.length;
      if(sourceLength >= length) {
        byte[] temp = new byte[length]; 
        for(int i=0; i<length; i++) {
          temp[i] = sourceByte[sourceLength - length + i];
        }

        return new String(temp);
      } else {
        String temp = source;
        for(int i=0; i<(length - sourceLength); i++) {
          temp = forFill + temp;
        }

        return temp;
      }
    }
  }

  /**
   * 스트링을 일정 Byte수로 fix시킨다. 오른쪽에 fix를 위한 문자열들이 들어간다. 
   *
   * @param source
   * @param length
   * @param fill
   * @return  변환된 문자열
   */
  public static String byteRPad(String source, int length, char fill) {
    String forFill = String.valueOf(fill);

    if(source == null) {
      String temp = "";
      for(int i=0; i<length; i++) {
        temp = temp + forFill;
      }

      return temp;
    } else { 
      byte[] sourceByte = source.getBytes();
      int sourceLength = sourceByte.length;
      if(sourceLength >= length) {
        byte[] temp = new byte[length]; 
        for(int i=0; i<length; i++) {
          temp[i] = sourceByte[i];
        }

        return new String(temp);
      } else {
        String temp = source;
        for(int i=0; i<(length - sourceLength); i++) {
          temp = temp + forFill;
        }

        return temp;
      }
    }
  }

  /**
   * 일부스트링을 취한다. endIndex가 문자열 길이를 초과해도 IndexOutOfBoundsException을 throw하지 않고 그대로 리턴한다. 
   *
   * @param source
   * @param beginIndex
   * @param endIndex
   * @return  변환된 문자열
   */
  public static String substring(String source, int beginIndex, int endIndex) {
    if(source == null) {
      return null;
    } else { 
      if(source.length() > endIndex)
        return source.substring(beginIndex, endIndex);
      else 
        return source.substring(beginIndex);
    }
  }

  /**
   * 일부스트링을 취한다. beginIndex가 문자열 길이를 초과해도 IndexOutOfBoundsException을 throw하지 않고 그대로 리턴한다. 
   *
   * @param source
   * @param beginIndex
   * @return  변환된 문자열
   */
  public static String substring(String source, int beginIndex) {
    if(source == null) {
      return null;
    } else { 
      if(source.length() > beginIndex)
        return source.substring(beginIndex);
      else 
        return "";
    }
  }

  /**
   * 일부스트링을 취한다. endIndex가 문자열 길이를 초과해도 IndexOutOfBoundsException을 throw하지 않고 그대로 리턴한다. 
   *
   * @param source
   * @param beginIndex
   * @param endIndex
   * @return  변환된 문자열
   */
  public static String byteSubstring(String source, int beginIndex, int endIndex) {
    if(beginIndex < 0)
      throw new IndexOutOfBoundsException("beginIndex is negative");
    if(beginIndex > endIndex)
      throw new IndexOutOfBoundsException("beginIndex is lager than the endIndex");

    if(source == null) {
      return null;
    } else { 
      if(beginIndex == endIndex)
        return "";

      byte[] sourceByte = source.getBytes();
      int sourceLength = sourceByte.length;

      if(sourceLength > beginIndex) {
        if(sourceLength > endIndex) {
          byte[] temp = new byte[endIndex - beginIndex]; 
          int index = 0;
          for(int i=beginIndex; i<endIndex; i++) {
            temp[index] = sourceByte[i];
            index++;
          }

          return new String(temp);
        } else {
          byte[] temp = new byte[sourceLength - beginIndex]; 
          int index = 0;
          for(int i=beginIndex; i<sourceLength; i++) {
            temp[index] = sourceByte[i];
            index++;
          }

          return new String(temp);
        }
      } else {
        return "";
      }
    }
  }

  /**
   * 일부스트링을 취한다. beginIndex가 문자열 길이를 초과해도 IndexOutOfBoundsException을 throw하지 않고 그대로 리턴한다. 
   *
   * @param source
   * @param beginIndex
   * @return  변환된 문자열
   */
  public static String byteSubstring(String source, int beginIndex) {
    return byteSubstring(source, beginIndex, Integer.MAX_VALUE);
  }

  /**
   * Trim한다. null객체도 유연히 처리한다.
   *
   * @param str Trim할 대상
   * @return    Trim후의 문자열
   */
  public static String trim(String str) {
    if(str == null)
      return null;

    return str.trim();
  }

  /**
   * String내의 특정 String을 새로운 스트링으로 대체한다.<BR>
   * JDK1.3 이하를 위해 존재
   *
   * @param str   대상 문자열
   * @param oldStr  변환할 문자열
   * @param newStr  변환될 문자열
   * @return    결과 문자열
   */
  public static String replace(String str, String oldStr, String newStr) {
    if(str == null)
      return null;

    if(oldStr == null || newStr == null)
      return str;

    StringBuffer buffer = new StringBuffer();

    while(true) {
      int i = str.indexOf(oldStr);

      if(i == -1) {
        buffer.append(str);
        break;
      }

      buffer.append(str.substring(0, i));
      buffer.append(newStr);
      str = str.substring(i + oldStr.length());
    }

    return buffer.toString();
  }

  /**
   * String의 순서를 뒤집는다
   *
   * @param source 원문 String
   * @return       reverse된 String
   */
  public static String reverse(String source) {
    if(source == null)
      return null;

    StringBuffer buffer = new StringBuffer();

    for(int i=0; i<source.length(); i++) {
      buffer.append(source.charAt(source.length() - i - 1));
    }

    return buffer.toString();
  }

  /**
   * String내의 null문자(ascii = 0) 까지만 substring한다<BR>
   *
   * @param source 대상 문자열
   * @return       결과 문자열
   */
  public static String toCString(String source) {
    if(source == null)
      return source;

    int pos = source.indexOf(0);
    if(pos >= 0)
      source = source.substring(0, pos);

    return source;
  }
}

