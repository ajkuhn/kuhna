package kuhna.sql.sentence;

import java.io.*;
import java.util.*;

/**
 * Field - Value 형태 객체의 복수로 구성된 객체들의 Super Class<BR>
 * InsertValues, UpdateSets, WhereTerms들이 상속받게 된다.
 *
 * @version 0.3, 2007/01/08, added add(Pairs) method by A.J.Kuhn<BR><!-- 
 * @version -->0.2, 2006/05/17, added remove() methods by A.J.Kuhn<BR><!-- 
 * @version -->0.1, 2004/05/12, initial version by A.J.Kuhn 
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public abstract class Pairs extends Object implements Serializable {

  public static final String DEFAULT_STRING_REJECT_TERM = "|";
  public static final byte DEFAULT_BYTE_REJECT_TERM = Byte.MIN_VALUE;
  public static final double DEFAULT_DOUBLE_REJECT_TERM = Double.MIN_VALUE;
  public static final float DEFAULT_FLOAT_REJECT_TERM = Float.MIN_VALUE;
  public static final int DEFAULT_INT_REJECT_TERM = Integer.MIN_VALUE;
  public static final long DEFAULT_LONG_REJECT_TERM = Long.MIN_VALUE;
  public static final short DEFAULT_SHORT_REJECT_TERM = Short.MIN_VALUE;

  private String _stringRejectTerm;
  private byte _byteRejectTerm;
  private double _doubleRejectTerm;
  private float _floatRejectTerm;
  private int _intRejectTerm;
  private long _longRejectTerm;
  private short _shortRejectTerm;

  protected Vector _pairs;

  protected Pairs() {
    super();

    _pairs = new Vector();

    _stringRejectTerm = DEFAULT_STRING_REJECT_TERM;
    _byteRejectTerm = DEFAULT_BYTE_REJECT_TERM;
    _doubleRejectTerm = DEFAULT_DOUBLE_REJECT_TERM;
    _floatRejectTerm = DEFAULT_FLOAT_REJECT_TERM;
    _intRejectTerm = DEFAULT_INT_REJECT_TERM;
    _longRejectTerm = DEFAULT_LONG_REJECT_TERM;
    _shortRejectTerm = DEFAULT_SHORT_REJECT_TERM;
  }

  protected Pairs(Pairs pairs) {
    super();

    _pairs = new Vector(pairs.getList());

    _stringRejectTerm = pairs.getStringRejectTerm();
    _byteRejectTerm = pairs.getByteRejectTerm();
    _doubleRejectTerm = pairs.getDoubleRejectTerm();
    _floatRejectTerm = pairs.getFloatRejectTerm();
    _intRejectTerm = pairs.getIntRejectTerm();
    _longRejectTerm = pairs.getLongRejectTerm();
    _shortRejectTerm = pairs.getShortRejectTerm();
  }

  public void setRejectTerm(String term) {
    _stringRejectTerm = term;
  }

  public void setRejectTerm(byte term) {
    _byteRejectTerm = term;
  }

  public void setRejectTerm(double term) {
    _doubleRejectTerm = term;
  }

  public void setRejectTerm(float term) {
    _floatRejectTerm = term;
  }

  public void setRejectTerm(int term) {
    _intRejectTerm = term;
  }

  public void setRejectTerm(long term) {
    _longRejectTerm = term;
  }

  public void setRejectTerm(short term) {
    _shortRejectTerm = term;
  }

  public String getStringRejectTerm() {
    return _stringRejectTerm;
  }

  public byte getByteRejectTerm() {
    return _byteRejectTerm;
  }

  public double getDoubleRejectTerm() {
    return _doubleRejectTerm;
  }

  public float getFloatRejectTerm() {
    return _floatRejectTerm;
  }

  public int getIntRejectTerm() {
    return _intRejectTerm;
  }

  public long getLongRejectTerm() {
    return _longRejectTerm;
  }

  public short getShortRejectTerm() {
    return _shortRejectTerm;
  }

  public void add(Pairs pairs) {
    _pairs.addAll(pairs.getList());
  }
  
  public boolean add(Pair pair) {
    if(!isRejected(pair)) {
      _pairs.add(pair);
      return true;
    }

    return false;
  }

  public boolean add(String field, Object value) {
    return add(new Pair(field, value));
  }

  public boolean add(String field, byte value) {
    return add(new Pair(field, value));
  }

  public boolean add(String field, double value) {
    return add(new Pair(field, value));
  }

  public boolean add(String field, float value) {
    return add(new Pair(field, value));
  }

  public boolean add(String field, int value) {
    return add(new Pair(field, value));
  }

  public boolean add(String field, long value) {
    return add(new Pair(field, value));
  }

  public boolean add(String field, short value) {
    return add(new Pair(field, value));
  }

  /**
   * 같은 필드이름의 Pair가 있을경우 이를 덮어쓴다.<BR>
   * add보다 Performance가 현저히 떨어지므로, 새로추가할때는 add메쏘드를 권장한다.
   *
   * @param pair 셋팅할 Pair객체 
   * @return     true면 덮어씀, false면 추가 혹은 Reject됨
   */
  public boolean set(Pair pair) {
    boolean flag = false;

    if(isRejected(pair))
      return flag;

    String field = pair.getField();
    for(int i=0; i<_pairs.size(); i++) {
      Pair temp = (Pair)_pairs.get(i);
      if(temp.getField().equals(field)) {
        _pairs.set(i, pair);
        flag = true;
      }
    }

    if(!flag)
      _pairs.add(pair);

    return flag;
  }

  public boolean set(String field, Object value) {
    return set(new Pair(field, value));
  }

  public boolean set(String field, byte value) {
    return set(new Pair(field, value));
  }

  public boolean set(String field, double value) {
    return set(new Pair(field, value));
  }

  public boolean set(String field, float value) {
    return set(new Pair(field, value));
  }

  public boolean set(String field, int value) {
    return set(new Pair(field, value));
  }

  public boolean set(String field, long value) {
    return set(new Pair(field, value));
  }

  public boolean set(String field, short value) {
    return set(new Pair(field, value));
  }

  public void clear() {
    _pairs.clear();
  }

  public int size() {
    return _pairs.size();
  }

  public Collection getList() {
    return _pairs;
  }

  private boolean isRejected(Pair pair) {
    return isRejected(pair.getValue());
  }

  private boolean isRejected(Object obj) {
    if(obj == null) {
      ;
    } else if(obj instanceof String) {
      if(obj.toString().equals(_stringRejectTerm))
        return true;
    } else if(obj instanceof Byte) {
      if(((Byte)obj).byteValue() == _byteRejectTerm)
        return true;
    } else if(obj instanceof Double) {
      if(((Double)obj).doubleValue() == _doubleRejectTerm)
        return true;
    } else if(obj instanceof Float) {
      if(((Float)obj).floatValue() == _floatRejectTerm)
        return true;
    } else if(obj instanceof Integer) {
      if(((Integer)obj).intValue() == _intRejectTerm)
        return true;
    } else if(obj instanceof Long) {
      if(((Long)obj).longValue() == _longRejectTerm)
        return true;
    } else if(obj instanceof Short) {
      if(((Short)obj).shortValue() == _shortRejectTerm)
        return true;
    }

    return false;
  }

  public Pair get(int index) {
    return (Pair)_pairs.get(index);
  }

  public Pair get(String fieldName, int index) {
    Vector v = new Vector();
    Iterator itr = _pairs.iterator();
    while(itr.hasNext()) {
      Pair pair = (Pair)itr.next();
      if(pair.getField().toLowerCase().equals(fieldName.toLowerCase())) {
        v.add(pair);
      }
    }

    return (Pair)v.get(index);
  }

  public Pair get(String fieldName) {
    Pair pair = null;

    try {
      pair = get(fieldName, 0);
    } catch(ArrayIndexOutOfBoundsException ex) {
      ;
    }

    return pair;
  }

  public Pair remove(int index) {
    return (Pair)_pairs.remove(index);
  }

  public Pair remove(String fieldName, int index) {
    Vector v = new Vector();
    Iterator itr = _pairs.iterator();
    for(int i=0; i<_pairs.size(); i++) {
      Pair pair = (Pair)_pairs.get(i);
      if(pair.getField().toLowerCase().equals(fieldName.toLowerCase())) {
        v.add(new Integer(i));
      }
    }

    return (Pair)_pairs.remove(((Integer)v.get(index)).intValue());
  }

  public Pair remove(String fieldName) {
    Pair pair = null;

    try {
      pair = remove(fieldName, 0);
    } catch(ArrayIndexOutOfBoundsException ex) {
      ;
    }

    return pair;
  }
 

  public InsertValues toInsertValues() {
    return new InsertValues(this);
  }

  public WhereTerms toWhereTerms() {
    return new WhereTerms(this);
  }

  public UpdateSets toUpdateSets() {
    return new UpdateSets(this);
  }

  public abstract String toString();
}

