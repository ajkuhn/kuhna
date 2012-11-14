package kuhna.sql.sentence;

import java.io.*;
import java.util.*;
import kuhna.util.*;

/**
 * update구문의 set들, 다양한 메쏘드를 통해 set 구문을 구성할 수 있다. 
 *
 * @author  A.J.Kuhn
 * @version 0.5, 2004/05/12, refactoring. Super class is changed Object to Pairs.
 *
 * @author  A.J.Kuhn
 * @version 0.4, 2003/11/17, add toInsertValues(), toWhereTerms() methods
 *
 * @author  A.J.Kuhn
 * @version 0.3, 2002/05/29, added size() method.
 *
 * @author  A.J.Kuhn
 * @version 0.2, 2002/05/03, clear() method added.
 *
 * @author  A.J.Kuhn
 * @version 0.1, 2002/04/22
 */
public class UpdateSets extends Pairs implements Serializable {

  public UpdateSets() {
    super();
  }

  public UpdateSets(Pairs pairs) {
    super(pairs);
  }

  public UpdateSet getUpdateSet(int index) {
    return ((Pair)get(index)).toUpdateSet();
  }

  public UpdateSet getUpdateSet(String fieldName, int index) {
    return ((Pair)get(fieldName, index)).toUpdateSet();
  }

  public UpdateSet getUpdateSet(String fieldName) {
    Pair pair = get(fieldName);

    if(pair != null)
      return pair.toUpdateSet();
    else
      return null;
  }

  public UpdateSets toUpdateSets() {
    return this;
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();

    for(int i=0; i<_pairs.size(); i++) {
      UpdateSet set = ((Pair)_pairs.get(i)).toUpdateSet();

      buffer.append(set.toString());

      if(i != _pairs.size() - 1)
        buffer.append(", ");
    }

    return buffer.toString();
  }
}

