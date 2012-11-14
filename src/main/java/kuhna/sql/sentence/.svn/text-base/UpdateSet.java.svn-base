package kuhna.sql.sentence;

import java.io.*;

/**
 * update 구문의 set 값, 다양한 메쏘드를 통해 하나의 set을 구성할 수 있다. 
 *
 * @author  A.J.Kuhn
 * @version 0.4, 2004/05/12, refactoring. Super class is changed Object to Pair.
 *
 * @author  A.J.Kuhn
 * @version 0.3, 2002/05/31, modified toString() method, make compatible less than JDK1.4
 *
 * @author  A.J.Kuhn
 * @version 0.2, 2002/05/27, modified toString() method, function is available, quato is available 
 *
 * @author  A.J.Kuhn
 * @version 0.1, 2002/04/22
 */
public class UpdateSet extends Pair implements Serializable {

  public UpdateSet(String field, Object value) {
    super(field, value);
  }

  public UpdateSet(String field, byte value) {
    super(field, value);
  }

  public UpdateSet(String field, double value) {
    super(field, value);
  }

  public UpdateSet(String field, float value) {
    super(field, value);
  }

  public UpdateSet(String field, int value) {
    super(field, value);
  }

  public UpdateSet(String field, long value) {
    super(field, value);
  }

  public UpdateSet(String field, short value) {
    super(field, value);
  }

  public UpdateSet toUpdateSet() {
    return this;    
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();

    buffer.append(getField());
    buffer.append(" = ");
    buffer.append(getValueString());

    return buffer.toString();
  }
}

