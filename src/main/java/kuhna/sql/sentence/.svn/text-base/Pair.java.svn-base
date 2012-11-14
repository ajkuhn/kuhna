package kuhna.sql.sentence;

import java.io.*;
import java.util.*;
import kuhna.util.*;
import kuhna.sql.*;

/**
 * Field - Value 형태 객체의 Super Class<BR>
 * InsertValue, UpdateSet, WhereTerm들이 상속받게 된다.
 *
 * @version 0.2.1, 2011/10/21, modified getValueString() method. |FUNCTION| keyword was removed by A.J.Kuhn<BR><!--
 * @version -->0.2, 2005/03/16, modified getValueString() method by A.J.Kuhn<BR><!--
 * @version -->0.1, 2004/05/12, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class Pair extends Object implements Serializable {

  private String _field;
  private Object _value;

  public Pair(String field, Object value) {
    super();

    _field = field;
    _value = value;
  }

  public Pair(String field, byte value) {
    this(field, new Byte(value));
  }

  public Pair(String field, double value) {
    this(field, new Double(value));
  }

  public Pair(String field, float value) {
    this(field, new Float(value));
  }

  public Pair(String field, int value) {
    this(field, new Integer(value));
  }

  public Pair(String field, long value) {
    this(field, new Long(value));
  }

  public Pair(String field, short value) {
    this(field, new Short(value));
  }
  
  public void setField(String field) {
    _field = field;
  }

  public String getField() {
    return _field;
  }

  public void setValue(Object value) {
    _value = value;
  }

  public void setValue(byte value) {
    _value = new Byte(value);
  }

  public void setValue(double value) {
    _value = new Double(value);
  }

  public void setValue(float value) {
    _value = new Float(value);
  }

  public void setValue(int value) {
    _value = new Integer(value);
  }

  public void setValue(long value) {
    _value = new Long(value);
  }

  public void setValue(short value) {
    _value = new Short(value);
  }

  public Object getValue() {
    return _value;
  }

  public String getValueString() {
    String str = null;

    if(_value == null) {
      str = "NULL";
    } else if(_value instanceof String) {
      str = SQLUtil.toSQLString(_value.toString(), true);
    } else {
      str = _value.toString();
    }

    return str;
  }

  public InsertValue toInsertValue() {
    return new InsertValue(_field, _value);
  }

  public UpdateSet toUpdateSet() {
    return new UpdateSet(_field, _value);
  }

  public WhereTerm toWhereTerm() {
    return new WhereTerm(_field, _value);
  }
}

