package kuhna.lang;

/**
 * 수학관련 유틸리티클래스
 *
 * @author  A.J.Kuhn
 * @version 0.1, 2004/04/07
 */
public class MathUtil extends Object {

  /**
   * 가장 작은값을 리턴한다.
   *
   * @param  n 정수배열
   * @return 가장작은 정수
   */
  public static int min(int[] n) {
    int value = n[0];

    for(int j=1; j<n.length; j++)
      value = Math.min(value, n[j]);

    return value;
  }
}

