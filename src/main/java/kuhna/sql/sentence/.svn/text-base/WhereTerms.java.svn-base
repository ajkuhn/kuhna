package kuhna.sql.sentence;

import java.io.*;
import java.util.*;

/**
 * Where 조건들, 다양한 메쏘드를 통해 Where 문을 구성할 수 있다. 
 *
 * @version 0.9.2, 2006/05/17, modified getWhereTerm() methods by A.J.Kuhn<BR><!-- 
 * @version -->0.9.1, 2006/05/10, added setAnd() method and modified getWhereTerm() methods by A.J.Kuhn<BR><!-- 
 * @version -->0.9, 2004/05/12, refactoring. Super class is changed Object to Pairs. by A.J.Kuhn<BR><!-- 
 * @version -->0.8, 2003/11/17, add toInsertValues(), toUpdateSets methods by A.J.Kuhn<BR><!-- 
 * @version -->0.7.4, 2003/09/20, implements Serializable by A.J.Kuhn<BR><!-- 
 * @version -->0.7.3, 2002/11/07, setRejectTerm() methods added. by A.J.Kuhn<BR><!-- 
 * @version -->0.7, 2002/05/20, size() method added. by A.J.Kuhn<BR><!-- 
 * @version -->0.6, 2002/05/03, clear() method added. by A.J.Kuhn<BR><!-- 
 * @version -->0.5, 2002/04/22, Serializable interface implememted, add(x,x) method series added. by A.J.Kuhn<BR><!-- 
 * @version -->0.4, 2002/04/02, change class name from 'WhereTerm' to 'WhereTerms' and renewal by A.J.Kuhn<BR><!-- 
 * @version -->0.3, 2002/03/29, bug fix and implement 'LIKE%' option by A.J.Kuhn<BR><!-- 
 * @version -->0.2, 2002/03/28, renewal by A.J.Kuhn<BR><!-- 
 * @version -->0.1, 2002/03/06, initial version by A.J.Kuhn 
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class WhereTerms extends Pairs implements Serializable {

  private boolean _and = true;

  public WhereTerms() {
    super();
  }

  public WhereTerms(Pairs pairs) {
    super(pairs);
  }

  public boolean add(String field, String term, Object value) {
    return add(new WhereTerm(field, term, value));
  }

  public boolean add(String field, String term, byte value) {
    return add(new WhereTerm(field, term, value));
  }

  public boolean add(String field, String term, double value) {
    return add(new WhereTerm(field, term, value));
  }

  public boolean add(String field, String term, float value) {
    return add(new WhereTerm(field, term, value));
  }

  public boolean add(String field, String term, int value) {
    return add(new WhereTerm(field, term, value));
  }

  public boolean add(String field, String term, long value) {
    return add(new WhereTerm(field, term, value));
  }

  public boolean add(String field, String term, short value) {
    return add(new WhereTerm(field, term, value));
  }

  public boolean set(String field, String term, Object value) {
    return set(new WhereTerm(field, term, value));
  }

  public boolean set(String field, String term, byte value) {
    return set(new WhereTerm(field, term, value));
  }

  public boolean set(String field, String term, double value) {
    return set(new WhereTerm(field, term, value));
  }

  public boolean set(String field, String term, float value) {
    return set(new WhereTerm(field, term, value));
  }

  public boolean set(String field, String term, int value) {
    return set(new WhereTerm(field, term, value));
  }

  public boolean set(String field, String term, long value) {
    return set(new WhereTerm(field, term, value));
  }

  public boolean set(String field, String term, short value) {
    return set(new WhereTerm(field, term, value));
  }

  public WhereTerm getWhereTerm(int index) {
    Pair pair = get(index);
    if(pair instanceof WhereTerm)
      return (WhereTerm)pair;
    else
      return pair.toWhereTerm();
  }

  public WhereTerm getWhereTerm(String fieldName, int index) {
    Pair pair = get(fieldName, index);

    if(pair instanceof WhereTerm)
      return (WhereTerm)pair;
    else
      return pair.toWhereTerm();
  }

  public WhereTerm getWhereTerm(String fieldName) {
    Pair pair = get(fieldName);

    if(pair != null) {
      if(pair instanceof WhereTerm)
        return (WhereTerm)pair;
      else
        return pair.toWhereTerm();
    } else {
      return null;
    }
  }

  public WhereTerms toWhereTerms() {
    return this;
  }

  public void setAnd(boolean and) {
    _and = and;
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();

    for(int i=0; i<_pairs.size(); i++) {
      WhereTerm term = getWhereTerm(i);

      buffer.append(term.toString());

      if(i != _pairs.size() - 1)
        buffer.append(" " + (_and == true ? "AND" : "OR") + " ");
    }

    return buffer.toString();
  }
}

