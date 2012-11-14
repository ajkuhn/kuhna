package kuhna.util;

import java.util.*;

/**
 * Object Queue 클래스<BR> 
 * J2SE 5.0이하 버젼사용시를 위하여 제공한다. java.util.Stack을 모델로 하여 만들어졌다.
 *
 * @version 0.1, 2004/10/11, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class Queue extends Vector {

  /**
   * 비어있는 Queue를 생성한다
   */
  public Queue() {
    super();
  }

  /**
   * 큐의 첫번째 Object를 리턴한다. 큐에 여전히 Object를 남겨둔다. 큐가 비어있는경우 null을 리턴
   *
   * @return 첫번째 Object
   */
  public Object peek() {
    if(empty())
      return null;
    
    return get(0);
  }

  /**
   * 큐의 첫번째 Object를 리턴한다. 큐에서 Object를 제거한다. 큐가 비어있는경우 null을 리턴
   *
   * @return 첫번째 Object
   */
  public Object poll() {
    if(empty())
      return null;
    
    return remove(0);
  }

  /**
   * 큐의 마지막에 Object를 집어 넣는다. 성공적으로 들어가면 item을 실패한경우 null을 리턴
   *
   * @param item 큐에집어넣는객체
   * @return   성공한 경우 item, 실패한 경우 null을 리턴 
   */
  public Object push(Object item) {
    if(add(item))
      return item;
    
    return null;
  }

  /**
   * 큐가 비어있는지 확인한다
   *
   * @return 비어있으며 true, 아니면 false 
   */
  public boolean empty() {
    return isEmpty(); 
  }

  /**
   * 큐의 처음부터 해당 객체가 존재하는지 검색하여 첫번째 아이템의 1-based 위치를 리턴한다.
   *
   * @param o 검색할 객체
   * @return  1-based 위치, 없는경우 -1
   */
  public int search(Object o) {
    int index = indexOf(o);

    if(index == -1)
      return index;

    return index + 1;
  }
}
