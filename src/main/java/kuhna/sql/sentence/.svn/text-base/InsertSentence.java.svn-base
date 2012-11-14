package kuhna.sql.sentence;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import kuhna.util.*;

/**
 * Insert문장, 다양한 메쏘드를 통해 Insert문장을 구성할 수 있다. 
 *
 * @author  A.J.Kuhn
 * @version 0.7, 2004/05/13, added value relative methods
 *
 * @author  A.J.Kuhn
 * @version 0.6, 2004/05/03, added setValue() series methods
 *
 * @author  A.J.Kuhn
 * @version 0.5, 2004/04/29, this class extended SQLSentence not implemented
 *
 * @author  A.J.Kuhn
 * @version 0.4, 2003/11/03, added addValue(x,x) methods
 *
 * @author  A.J.Kuhn
 * @version 0.3, 2003/09/20, implements Serializable
 *
 * @author  A.J.Kuhn
 * @version 0.2, 2002/11/08, add setValues(), getValues() methods
 *
 * @author  A.J.Kuhn
 * @version 0.1, 2002/05/20
 */
public class InsertSentence extends SQLSentence {

  private final String _insert = "insert into ";
  private String _table;
  private InsertValues _values;

  public InsertSentence() {
    clear();
  }

  public void clear() {
    _table = null;
    _values = new InsertValues();
  }

  public void setTable(String table) {
    _table = table;
  }

  public boolean addValue(InsertValue value) {
    return _values.add(value);
  }

  public boolean addValue(String field, Object value) {
    return addValue(new InsertValue(field, value));
  }

  public boolean addValue(String field, byte value) {
    return addValue(new InsertValue(field, value));
  }

  public boolean addValue(String field, double value) {
    return addValue(new InsertValue(field, value));
  }

  public boolean addValue(String field, float value) {
    return addValue(new InsertValue(field, value));
  }

  public boolean addValue(String field, int value) {
    return addValue(new InsertValue(field, value));
  }

  public boolean addValue(String field, long value) {
    return addValue(new InsertValue(field, value));
  }

  public boolean addValue(String field, short value) {
    return addValue(new InsertValue(field, value));
  }

  public boolean setValue(InsertValue value) {
    return _values.set(value);
  }

  public boolean setValue(String field, Object value) {
    return setValue(new InsertValue(field, value));
  }

  public boolean setValue(String field, byte value) {
    return setValue(new InsertValue(field, value));
  }

  public boolean setValue(String field, double value) {
    return setValue(new InsertValue(field, value));
  }

  public boolean setValue(String field, float value) {
    return setValue(new InsertValue(field, value));
  }

  public boolean setValue(String field, int value) {
    return setValue(new InsertValue(field, value));
  }

  public boolean setValue(String field, long value) {
    return setValue(new InsertValue(field, value));
  }

  public boolean setValue(String field, short value) {
    return setValue(new InsertValue(field, value));
  }

  public InsertValue getValue(int index) {
    return _values.getInsertValue(index);
  }

  public InsertValue getValue(String field, int index) {
    return _values.getInsertValue(field, index);
  }

  public InsertValue getValue(String field) {
    return _values.getInsertValue(field);
  }

  public void setValues(InsertValues values) {
    _values = values;
  }

  public InsertValues getValues() {
    return _values;
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();

    buffer.append(_insert);
    buffer.append(_table);
    buffer.append(_values.toString());

    return buffer.toString();
  }
}

