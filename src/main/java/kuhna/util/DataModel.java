package kuhna.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;

/**
 * 다양한 형태의 데이터를 저장하는 Data Container.
 * getUtilDate(strDate) 메쏘드의 구현변화에 따라 getUtilDate(s), getSQLDate(s), getSQLTime(s), getTimestamp(s)메쏘드의 구현이 변경되어야한다.

 * @version 0.7.1, 2011/10/11, fixed toString() methods by A.J.Kuhn<BR><!--
 * @version -->0.7, 2009/10/14, added getUtilDate/getSQLDate/getSQLTime/getTimestamp(String, String) methods, inner Hashtable data to HashMap, changed getAttributeKeys() to getAttributeKeySet() by A.J.Kuhn<BR><!--
 * @version -->0.6.2, 2007/07/08, modified DataModel(ServletRequest) constructor, modified getCollection(String) method by A.J.Kuhn<BR><!--
 * @version -->0.6.1, 2007/07/06, added DataModel(ServletRequest) constructor by A.J.Kuhn<BR><!--
 * @version -->0.6, 2007/05/31, modified get{DateType}(String) methods, added getbytes(String, String) method by A.J.Kuhn<BR><!--
 * @version -->0.5.4, 2007/05/28, modified getString(String) method. support char[] type by A.J.Kuhn<BR><!--
 * @version -->0.5.3, 2007/05/25, modified getList(String) method by A.J.Kuhn<BR><!--
 * @version -->0.5.2, 2006/05/24, added getchars(String) method by A.J.Kuhn<BR><!--
 * @version -->0.5.1, 2006/05/19, added getStrings(String) method by A.J.Kuhn<BR><!--
 * @version -->0.5, 2006/05/18, added getbytes(String) method deprecated getbyteArray(String) method by A.J.Kuhn<BR><!--
 * @version -->0.4.9, 2005/07/15, added getCollection(String), getList(String) methods by A.J.Kuhn<BR><!--
 * @version -->0.4.7, 2005/03/21, modified toString() method by A.J.Kuhn<BR><!--
 * @version -->0.4.5, 2005/03/17, added getbyteArray(String) method by A.J.Kuhn<BR><!--
 * @version -->0.4.2, 2005/03/08, added DataModel(Map), DataModel(DataModel) constructors by A.J.Kuhn<BR><!--
 * @version -->0.4.1, 2004/05/24, changed "\n" to System.getProperty("line.separator") and modified toString() method by A.J.Kuhn<BR><!-- 
 * @version -->0.4, 2004/04/28, added getDataTable(), getAttributeKeys() methods by A.J.Kuhn<BR><!--
 * @version -->0.3.5, 2003/10/23, added getDataModel method by A.J.Kuhn<BR><!--
 * @version -->0.3.1, 2002/11/07, changed member variable name by A.J.Kuhn<BR><!--
 * @version -->0.3, 2002/10/12, added toString() method by A.J.Kuhn<BR><!--
 * @version -->0.2, 2002/05/09, added getUtilDate(x), getSQLDate(x), getSQLTime(x) methods by A.J.Kuhn<BR><!--
 * @version -->0.1, 2002/01/17, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class DataModel implements Serializable {

  /** Data들의 저장고... */
  private HashMap _map;

  /**
   * 생성자
   */
  public DataModel() {
    _map = new HashMap();
  }

  /**
   * 생성자
   */
  public DataModel(Map t) {
    _map = new HashMap(t);
  }

  protected DataModel(DataModel dm) {
    _map = (HashMap)((HashMap)dm.getMap()).clone();
  }

  public DataModel(ServletRequest request) {
    this();

    Enumeration enumeration = request.getParameterNames();
    while(enumeration.hasMoreElements()) {
      String key = enumeration.nextElement().toString();
      if(request.getParameterValues(key).length > 1) {
        set(key, Arrays.asList(request.getParameterValues(key)));
      } else {
        set(key, request.getParameter(key));
      }
    }
  }
  
  public Map getMap() {
    return _map;
  }

  /**
   * @deprecated
   */
  public HashMap getDataTable() {
    return _map;
  }

  /**
   * Object Type의 데이터를 set한다.
   *
   * @param key   데이터의 key
   * @param value 데이터의 value
   */
  public void set(String key, Object value) {
    if(value == null)
      _map.remove(key);
    else
      _map.put(key, value);
  }

  /**
   * byte Type의 데이터를 set한다.
   *
   * @param key   데이터의 key
   * @param value 데이터의 value
   */
  public void set(String key, byte value) {
    _map.put(key, new Byte(value));
  }

  /**
   * double Type의 데이터를 set한다.
   *
   * @param key   데이터의 key
   * @param value 데이터의 value
   */
  public void set(String key, double value) {
    _map.put(key, new Double(value));
  }

  /**
   * float Type의 데이터를 set한다.
   *
   * @param key   데이터의 key
   * @param value 데이터의 value
   */
  public void set(String key, float value) {
    _map.put(key, new Float(value));
  }

  /**
   * int Type의 데이터를 set한다.
   *
   * @param key   데이터의 key
   * @param value 데이터의 value
   */
  public void set(String key, int value) {
    _map.put(key, new Integer(value));
  }

  /**
   * long Type의 데이터를 set한다.
   *
   * @param key   데이터의 key
   * @param value 데이터의 value
   */
  public void set(String key, long value) {
    _map.put(key, new Long(value));
  }

  /**
   * short Type의 데이터를 set한다.
   *
   * @param key   데이터의 key
   * @param value 데이터의 value
   */
  public void set(String key, short value) {
    _map.put(key, new Short(value));
  }

  /**
   * 데이터를 Object타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public Object get(String key) {
    return _map.get(key);
  }

  /**
   * 데이터를 String타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public String getString(String key) {
  Object value = _map.get(key);

  if(value == null)
    return null;
    else if(value instanceof char[])
      return new String((char[])value);
    else
      return value.toString();
  }

  /**
   * 데이터를 String[] 타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public String[] getStrings(String key) {
    Object value = _map.get(key);

    if(value == null)
      return null;
    else if(value instanceof String[])
      return (String[])value;
    else {
      String[] temp = new String[1];
      temp[0] = value.toString();
      return temp;
    }
  }
  
  /**
   * 데이터를 byte타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public byte getbyte(String key) {
    byte rValue = 0;

    try {
      Object obj = _map.get(key);

      if(obj == null)
        return rValue;
      else if(obj instanceof Number)
        return ((Number)obj).byteValue();
      else
        return Byte.parseByte(obj.toString());
    } catch(NumberFormatException ex) {
      // TODO obj의 값도 message에 함께 넣어줄것
      throw new NumberFormatException("value is not number format");
    }
  }

  /**
   * 데이터를 byte[]타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public byte[] getbytes(String key) {
    Object obj = _map.get(key);

    if(obj == null)
      return null;
    else if(obj instanceof byte[])
      return (byte[])obj;
    else
      return obj.toString().getBytes();
  }
  
  /**
   * 데이터를 byte[]타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public byte[] getbytes(String key, String charsetName) {
    Object obj = _map.get(key);

    try {
      if(obj == null)
        return null;
      else if(obj instanceof byte[])
        return (byte[])obj;
      else
        return obj.toString().getBytes(charsetName);
    } catch(UnsupportedEncodingException ex) {
      throw new IllegalArgumentException("charsetName(" + charsetName + ") is unsupported");
    }
  }

  /**
   * 데이터를 char[]타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public char[] getchars(String key) {
    Object obj = _map.get(key);

    if(obj == null)
      return null;

    if(obj instanceof char[])
      return (char[])obj;
    else
      return obj.toString().toCharArray();
  }

  /**
   * 데이터를 byte[]타입으로 받는다.
   *
   * @deprecated
   * @param key 데이터의 key
   * @return  테이터
   */
  public byte[] getbyteArray(String key) {
    return getbytes(key);
  }

  /**
   * 데이터를 double타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public double getdouble(String key) {
    double rValue = 0;

    try {
      Object obj = _map.get(key);

      if(obj == null)
        return rValue;
      else if(obj instanceof Number)
        return ((Number)obj).doubleValue();
      else
        return Double.parseDouble(obj.toString());
    } catch(NumberFormatException ex) {
      throw new NumberFormatException("value is not number format");
    }
  }

  /**
   * 데이터를 float타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public float getfloat(String key) {
    float rValue = 0.0f;

    try {
      Object obj = _map.get(key);

      if(obj == null)
        return rValue;
      else if(obj instanceof Number)
        return ((Number)obj).floatValue();
      else
        return Float.parseFloat(obj.toString());
    } catch(NumberFormatException ex) {
      throw new NumberFormatException("value is not number format");
    }
  }

  /**
   * 데이터를 int타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public int getint(String key) {
    int rValue = 0;

    try {
      Object obj = _map.get(key);

      if(obj == null)
        return rValue;
      else if(obj instanceof Number)
        return ((Number)obj).intValue();
      else
        return Integer.parseInt(obj.toString());
    } catch(NumberFormatException ex) {
      throw new NumberFormatException("value is not number format");
    }
  }

  /**
   * 데이터를 long타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public long getlong(String key) {
    long rValue = 0;

    try {
      Object obj = _map.get(key);

      if(obj == null)
        return rValue;
      else if(obj instanceof Number)
        return ((Number)obj).longValue();
      else
        return Long.parseLong(obj.toString());
    } catch(NumberFormatException ex) {
      throw new NumberFormatException("value is not number format");
    }
  }

  /**
   * 데이터를 short타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public short getshort(String key) {
    short rValue = 0;

    try {
      Object obj = _map.get(key);

      if(obj == null)
        return rValue;
      else if(obj instanceof Number)
        return ((Number)obj).shortValue();
      else
        return Short.parseShort(obj.toString());
    } catch(NumberFormatException ex) {
      throw new NumberFormatException("value is not number format");
    }
  }

  /**
   * 데이터를 Byte타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public Byte getByte(String key) {
    return new Byte(getbyte(key));
  }

  /**
   * 데이터를 Double타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public Double getDouble(String key) {
    return new Double(getdouble(key));
  }

  /**
   * 데이터를 Float타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public Float getFloat(String key) {
    return new Float(getfloat(key));
  }

  /**
   * 데이터를 Integer타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public Integer getInteger(String key) {
    return new Integer(getint(key));
  }

  /**
   * 데이터를 Long타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public Long getLong(String key) {
    return new Long(getlong(key));
  }

  /**
   * 데이터를 Short타입으로 받는다.
   *
   * @param key 데이터의 key
   * @return  테이터
   */
  public Short getShort(String key) {
    return new Short(getshort(key));
  }
  
  public java.util.Date getUtilDate(String key, String inPattern) {
    Object obj = _map.get(key);

    try {
      if(obj == null)
        return null;
      else if(obj instanceof java.util.Date)
        return (java.util.Date)obj;
      else
        return DateUtil.getUtilDate(inPattern, obj.toString());
    } catch(IllegalArgumentException ex) {
      throw new IllegalArgumentException("'" + key + "' value can not convert to java.util.Date(" + obj.toString() + ") by inPattern(" + inPattern + ")");
    }
  }
  
  public java.util.Date getUtilDate(String key) {
    Object obj = _map.get(key);

    try {
      if(obj == null)
        return null;
      else if(obj instanceof java.util.Date)
        return (java.util.Date)obj;
      else
        return DateUtil.getUtilDate(obj.toString());
    } catch(IllegalArgumentException ex) {
      throw new IllegalArgumentException("'" + key + "' value can not convert to java.util.Date(" + obj.toString() + ")");
    }
  }
  
  public Timestamp getTimestamp(String key, String inPattern) {
    Object obj = get(key);
      
    if(obj == null)
      return null;
    else if(obj instanceof Timestamp)
      return (Timestamp)obj;
    else
      return new Timestamp(getUtilDate(key, inPattern).getTime());
  }
  
  public Timestamp getTimestamp(String key) {
    Object obj = get(key);
      
    if(obj == null)
      return null;
    else if(obj instanceof Timestamp)
      return (Timestamp)obj;
    else
      return new Timestamp(getUtilDate(key).getTime());
  }

  public java.sql.Date getSQLDate(String key, String inPattern) {
    Object obj = get(key);
      
    if(obj == null)
      return null;
    else if(obj instanceof java.sql.Date)
      return (java.sql.Date)obj;
    else
      return new java.sql.Date(getUtilDate(key, inPattern).getTime());
  }

  public java.sql.Date getSQLDate(String key) {
    Object obj = get(key);
      
    if(obj == null)
      return null;
    else if(obj instanceof java.sql.Date)
      return (java.sql.Date)obj;
    else
      return new java.sql.Date(getUtilDate(key).getTime());
  }

  public java.sql.Time getSQLTime(String key, String inPattern) {
    Object obj = get(key);
      
    if(obj == null)
      return null;
    else if(obj instanceof java.sql.Time)
      return (java.sql.Time)obj;
    else
      return new java.sql.Time(getUtilDate(key, inPattern).getTime());
  }

  public java.sql.Time getSQLTime(String key) {
    Object obj = get(key);
      
    if(obj == null)
      return null;
    else if(obj instanceof java.sql.Time)
      return (java.sql.Time)obj;
    else
      return new java.sql.Time(getUtilDate(key).getTime());
  }

  public DataModel getDataModel(String key) {
    DataModel rValue = null;

    Object obj = _map.get(key);

    try {
      // Hashtable등을 convert시키는 로직 삽입할것.
      rValue = (DataModel)obj;
    } catch(ClassCastException ex) {
      throw new ClassCastException(key + "'s value can not convert to kuhna.util.DataModel(" + obj.toString() + ")");
    }

    return rValue;
  }

  public Collection getCollection(String key) {
    Object obj = _map.get(key);

    if(obj == null)
      return null;

    if(obj instanceof Collection) {
      return (Collection)obj;
    } else if(obj instanceof Object[]) {
      return Arrays.asList((Object[])obj);
    } else {
      ArrayList list = new ArrayList();
      list.add(obj);
      return list;
    }
  }

  public List getList(String key) {
    Object obj = _map.get(key);

    if(obj == null)
      return null;

    if(obj instanceof List) {
      return (List)obj;
    } else {
      return new ArrayList(getCollection(key));
    }
  }

  /**
   * attribute의 갯수를 리턴한다.
   *
   * @return  이 DataModel이 가진 attribute 갯수
   */
  public int getAttributeCount() {
    return _map.size();
  }

  /**
   * attribute의 key의 Enumeration을 리턴한다.
   *
   * @return  이 DataModel이 가진 attribute의 key enumeration
   */
  public Set getAttributeKeySet() {
    return _map.keySet();
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();

    buffer.append(getClass().getName() + " {");
    buffer.append(System.getProperty("line.separator"));
    Set set = _map.keySet();
    Iterator itr = set.iterator();
    while(itr.hasNext()) {
      String key = itr.next().toString();
      buffer.append(" (" + key + ", " + (_map.get(key) == null ? null : _map.get(key).toString()) + ")");
      buffer.append(System.getProperty("line.separator"));
    }

    buffer.append("}");

    return buffer.toString();
  }
}
