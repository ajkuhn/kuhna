package kuhna.sql.sentence;

import java.io.*;
import java.util.*;

import kuhna.util.*;
import kuhna.sql.*;

/**
 * Where 조건, 다양한 메쏘드를 통해 하나의 Where 조건을 구성할 수 있다. 
 *
 * @version 0.6, 2011/10/21, modified toString() method, changed 'LIKE' term policy by A.J.Kuhn<BR><!--
 * @version -->0.5, 2004/05/12, refactoring. Super class is changed Object to Pair. by A.J.Kuhn<BR><!--
 * @version -->0.4.1, 2003/11/20, LIKE% term bug fix by A.J.Kuhn<BR><!--
 * @version -->0.4, 2002/05/31, modified toString() method, make compatible less than JDK1.4 by A.J.Kuhn<BR><!--
 * @version -->0.4, 2002/05/27, modified toString() method, function is available, quato is available. by A.J.Kuhn<BR><!--
 * @version -->0.3, 2002/04/22, Serializable interface implemented, WhereTerm(x,x) constructor series added. by A.J.Kuhn<BR><!--
 * @version -->0.2, 2002/04/19, toString() method bug fixed. by A.J.Kuhn<BR><!--
 * @version -->0.1, 2002/04/02, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class WhereTerm extends Pair implements Serializable {

  private String _term;

  public WhereTerm(String field, Object value) {
    this(field, "=", value);
  }

  public WhereTerm(String field, byte value) {
    this(field, "=", value);
  }

  public WhereTerm(String field, double value) {
    this(field, "=", value);
  }

  public WhereTerm(String field, float value) {
    this(field, "=", value);
  }

  public WhereTerm(String field, int value) {
    this(field, "=", value);
  }

  public WhereTerm(String field, long value) {
    this(field, "=", value);
  }

  public WhereTerm(String field, short value) {
    this(field, "=", value);
  }

  public WhereTerm(String field, String term, Object value) {
    super(field, value);
    _term = term;
  }

  public WhereTerm(String field, String term, byte value) {
    super(field, value);
    _term = term;
  }

  public WhereTerm(String field, String term, double value) {
    super(field, value);
    _term = term;
  }

  public WhereTerm(String field, String term, float value) {
    super(field, value);
    _term = term;
  }

  public WhereTerm(String field, String term, int value) {
    super(field, value);
    _term = term;
  }

  public WhereTerm(String field, String term, long value) {
    super(field, value);
    _term = term;
  }

  public WhereTerm(String field, String term, short value) {
    super(field, value);
    _term = term;
  }

  public void setTerm(String term) {
    _term = term;
  }

  public String getTerm() {
    return _term;
  }

  public WhereTerm toWhereTerm() {
    return this;    
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();

    buffer.append(getField());
    buffer.append(" ");
    buffer.append(_term);
    buffer.append(" ");
    buffer.append(getValueString());

    return buffer.toString();
  }
}

