package kuhna.sql.sentence;

import java.io.*;
import java.util.*;

import kuhna.util.*;

/**
 * insert 구문의 value들, 다양한 메쏘드를 통해 value 구문을 구성할 수 있다. 
 *
 * @author  A.J.Kuhn
 * @version 0.6, 2004/05/12, refactoring. Super class is changed Object to Pairs.
 *
 * @author  A.J.Kuhn
 * @version 0.5, 2004/05/02, added getInsertValue(), set() series methods
 *
 * @author  A.J.Kuhn
 * @version 0.4, 2003/11/17, added toWhereTerms(), toUpdateSets() methods
 *
 * @author  A.J.Kuhn
 * @version 0.3, 2002/05/07, StringBuffer.append(java.lang.StringBuffer) method is not used. it's since JDK 1.4
 *
 * @author  A.J.Kuhn
 * @version 0.2, 2002/05/03, clear() method added.
 *
 * @author  A.J.Kuhn
 * @version 0.1, 2002/04/22
 */
public class InsertValues extends Pairs implements Serializable {

  public InsertValues() {
    super();
  }

  public InsertValues(Pairs pairs) {
    super(pairs);
  }

  public InsertValue getInsertValue(int index) {
    return ((Pair)get(index)).toInsertValue();
  }

  public InsertValue getInsertValue(String fieldName, int index) {
    return ((Pair)get(fieldName, index)).toInsertValue();
  }

  public InsertValue getInsertValue(String fieldName) {
    Pair pair = get(fieldName);

    if(pair != null)
      return pair.toInsertValue();
    else
      return null;
  }

  public InsertValues toInsertValues() {
    return this;
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("(");
    StringBuffer buffer2 = new StringBuffer();
    buffer2.append(") VALUES (");

    for(int i=0; i<_pairs.size(); i++) {
      Pair pair = (Pair)_pairs.get(i); 
      buffer.append(pair.getField());
      buffer2.append(pair.getValueString());

      if(i != _pairs.size() - 1) {
        buffer.append(", ");
        buffer2.append(", ");
      } else {
        buffer2.append(")");
      }
    }

    buffer.append(buffer2.toString());

    return buffer.toString();
  }
}

