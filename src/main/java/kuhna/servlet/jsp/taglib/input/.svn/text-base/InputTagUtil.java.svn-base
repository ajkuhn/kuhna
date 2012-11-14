package kuhna.servlet.jsp.taglib.input;

import java.util.*;

import kuhna.util.*;

/**
 * Input Tag들의 보조유틸리티
 *
 * @version 1.0
 * @author  A.J.Kuhn
 */
public class InputTagUtil extends Object {

  /**
   * @param c c내부의 객체는 모두 DataModel이어야 한다.
   */
  public static Collection convertForSelectTag(Collection c, String valueName, String textName) {
    Vector v = new Vector();

    Iterator itr = c.iterator();
    while(itr.hasNext()) {
      DataModel dm = (DataModel)itr.next();
      v.add(dm.getString(valueName) + "," + dm.getString(textName));
    }

    return v;
  }

  /**
   * textName은 deli로 구분되는 DataModel값을 bridge로 연결한 String이 된다
   *
   * @param c c내부의 객체는 모두 DataModel이어야 한다.
   */
  public static Collection convertForSelectTag(Collection c, String bridge, String valueName, String textName1, String textName2) {
    Vector v = new Vector();

    Iterator itr = c.iterator();
    while(itr.hasNext()) {
      DataModel dm = (DataModel)itr.next();
      v.add(dm.getString(valueName) + "," + dm.getString(textName1) + bridge + dm.getString(textName2));
    }

    return v;
  }
}

