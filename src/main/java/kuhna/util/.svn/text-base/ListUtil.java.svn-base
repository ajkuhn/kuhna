package kuhna.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * List관련 유틸리티 메쏘드 클래스
 *
 * @version 0.2, 2012/01/27, added isEmpty, union method. changed getIntersectionList to intersection by A.J.Kuhn<BR><!-- 
 * @version	-->0.1, 2010/05/26, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class ListUtil extends Object {

  public static boolean isEmpty(Collection c) {
    if(c == null || c.isEmpty())
      return true;
    else
      return false;
  }
  
  /**
   * 두개의 리스트내의 값들중 교집합 리스트를 뽑아낸다. 리스트 순서는 첫번째 파라미터 리스트의 순서를 따른다.
   * 
   * @param list1
   * @param list2
   * @return
   */
  public static List intersection(List list1, List list2) {
    if(isEmpty(list1) || isEmpty(list2))
      return new ArrayList();

    Map map = new HashMap();
    Map returnMap = new HashMap();
    
    for(int i=0; i<list1.size(); i++) {
      Object obj = list1.get(i);
      map.put(obj, obj);
    }
    for(int i=0; i<list2.size(); i++) {
      Object obj = list2.get(i);
      if(map.containsKey(obj))
        returnMap.put(obj, obj);
    }
    
    return new ArrayList(returnMap.values());
  }
  
  /**
   * 두개의 리스트내의 값들중 합집합 리스트를 뽑아낸다. 리스트 순서는 첫번째 파라미터 리스트의 순서를 따른다.
   * 
   * @param list1
   * @param list2
   * @return
   */
  public static List union(List list1, List list2) {
    if(isEmpty(list1)) {
      if(isEmpty(list2))
        return new ArrayList();
      else
        return new ArrayList(list2);
    } else {
      if(isEmpty(list2))
        return new ArrayList(list1);
    }
    
    Map map = new HashMap();
    
    for(int i=0; i<list1.size(); i++) {
      Object obj = list1.get(i);
      map.put(obj, obj);
    }

    for(int i=0; i<list2.size(); i++) {
      Object obj = list2.get(i);
      map.put(obj, obj);
    }
    
    return new ArrayList(map.values());
  }
}

