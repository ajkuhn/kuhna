package kuhna.sql.sentence;

import java.io.*;

/**
 * insert 구문의 value 값, 다양한 메쏘드를 통해 하나의 value를 구성할 수 있다. 
 *
 * @author  A.J.Kuhn
 * @version 0.4, 2004/05/12, refactoring. Super class is changed Object to Pair.
 *
 * @author  A.J.Kuhn
 * @version 0.3, 2002/05/31, modified toString() method, make compatible less than JDK1.4
 *
 * @author  A.J.Kuhn
 * @version 0.2, 2002/05/27, modified getValueString() method, function is available, quato is available. 
 *
 * @author  A.J.Kuhn
 * @version 0.1, 2002/04/22
 */
public class InsertValue extends Pair implements Serializable {

  public InsertValue(String field, Object value) {
    super(field, value);
  }

  public InsertValue(String field, byte value) {
    super(field, value);
  }

  public InsertValue(String field, double value) {
    super(field, value);
  }

  public InsertValue(String field, float value) {
    super(field, value);
  }

  public InsertValue(String field, int value) {
    super(field, value);
  }

  public InsertValue(String field, long value) {
    super(field, value);
  }

  public InsertValue(String field, short value) {
    super(field, value);
  }

  public InsertValue toInsertValue() {
    return this;
  }
}

