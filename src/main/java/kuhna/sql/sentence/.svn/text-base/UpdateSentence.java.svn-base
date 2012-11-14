package kuhna.sql.sentence;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import kuhna.util.*;

/**
 * Update문장, 다양한 메쏘드를 통해 Update문장을 구성할 수 있다. 
 *
 * @version 0.4.1, 2006/05/10, added setWhereTermsAnd() method by A.J.Kuhn<BR><!-- 
 * @version -->0.4, 2004/05/13, added set, term relative methods by A.J.Kuhn<BR><!--  
 * @version -->0.3, 2004/04/29, this class extended SQLSentence not implemented by A.J.Kuhn<BR><!--  
 * @version -->0.2.3, 2003/09/29, add setSets(x), setTerms(x) methods by A.J.Kuhn<BR><!--  
 * @version -->0.2, 2003/09/20, implements Serializable by A.J.Kuhn<BR><!--  
 * @version -->0.1, 2002/05/20, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class UpdateSentence extends SQLSentence {

  private final String _update = "update ";
  private final String _set = " set ";
  private final String _where = " where ";
  private String _table;
  private UpdateSets _sets;
  private WhereTerms _terms;

  public UpdateSentence() {
    clear();
  }

  public void clear() {
    _table = null;
    _sets = new UpdateSets();
    _terms = new WhereTerms();
  }

  public void setTable(String table) {
    _table = table;
  }

  public boolean addSet(UpdateSet value) {
    return _sets.add(value);
  }

  public boolean addSet(String field, Object value) {
    return addSet(new UpdateSet(field, value));
  }

  public boolean addSet(String field, byte value) {
    return addSet(new UpdateSet(field, value));
  }

  public boolean addSet(String field, double value) {
    return addSet(new UpdateSet(field, value));
  }

  public boolean addSet(String field, float value) {
    return addSet(new UpdateSet(field, value));
  }

  public boolean addSet(String field, int value) {
    return addSet(new UpdateSet(field, value));
  }

  public boolean addSet(String field, long value) {
    return addSet(new UpdateSet(field, value));
  }

  public boolean addSet(String field, short value) {
    return addSet(new UpdateSet(field, value));
  }

  public boolean setSet(UpdateSet value) {
    return _sets.set(value);
  }

  public boolean setSet(String field, Object value) {
    return setSet(new UpdateSet(field, value));
  }

  public boolean setSet(String field, byte value) {
    return setSet(new UpdateSet(field, value));
  }

  public boolean setSet(String field, double value) {
    return setSet(new UpdateSet(field, value));
  }

  public boolean setSet(String field, float value) {
    return setSet(new UpdateSet(field, value));
  }

  public boolean setSet(String field, int value) {
    return setSet(new UpdateSet(field, value));
  }

  public boolean setSet(String field, long value) {
    return setSet(new UpdateSet(field, value));
  }

  public boolean setSet(String field, short value) {
    return setSet(new UpdateSet(field, value));
  }

  public UpdateSet getSet(int index) {
    return _sets.getUpdateSet(index);
  }

  public UpdateSet getSet(String field, int index) {
    return _sets.getUpdateSet(field, index);
  }

  public UpdateSet getSet(String field) {
    return _sets.getUpdateSet(field);
  }

  public void setSets(UpdateSets sets) {
    _sets = sets;
  }

  public UpdateSets getSets() {
    return _sets;
  }

  public boolean addTerm(WhereTerm term) {
    return _terms.add(term);
  }

  public boolean addTerm(String field, Object value) {
    return addTerm(new WhereTerm(field, value));
  }

  public boolean addTerm(String field, byte value) {
    return addTerm(new WhereTerm(field, value));
  }

  public boolean addTerm(String field, double value) {
    return addTerm(new WhereTerm(field, value));
  }

  public boolean addTerm(String field, float value) {
    return addTerm(new WhereTerm(field, value));
  }

  public boolean addTerm(String field, int value) {
    return addTerm(new WhereTerm(field, value));
  }

  public boolean addTerm(String field, long value) {
    return addTerm(new WhereTerm(field, value));
  }

  public boolean addTerm(String field, short value) {
    return addTerm(new WhereTerm(field, value));
  }

  public boolean addTerm(String field, String term, Object value) {
    return addTerm(new WhereTerm(field, term, value));
  }

  public boolean addTerm(String field, String term, byte value) {
    return addTerm(new WhereTerm(field, term, value));
  }

  public boolean addTerm(String field, String term, double value) {
    return addTerm(new WhereTerm(field, term, value));
  }

  public boolean addTerm(String field, String term, float value) {
    return addTerm(new WhereTerm(field, term, value));
  }

  public boolean addTerm(String field, String term, int value) {
    return addTerm(new WhereTerm(field, term, value));
  }

  public boolean addTerm(String field, String term, long value) {
    return addTerm(new WhereTerm(field, term, value));
  }

  public boolean addTerm(String field, String term, short value) {
    return addTerm(new WhereTerm(field, term, value));
  }

  public boolean setTerm(WhereTerm term) {
    return _terms.set(term);
  }

  public boolean setTerm(String field, Object value) {
    return setTerm(new WhereTerm(field, value));
  }

  public boolean setTerm(String field, byte value) {
    return setTerm(new WhereTerm(field, value));
  }

  public boolean setTerm(String field, double value) {
    return setTerm(new WhereTerm(field, value));
  }

  public boolean setTerm(String field, float value) {
    return setTerm(new WhereTerm(field, value));
  }

  public boolean setTerm(String field, int value) {
    return setTerm(new WhereTerm(field, value));
  }

  public boolean setTerm(String field, long value) {
    return setTerm(new WhereTerm(field, value));
  }

  public boolean setTerm(String field, short value) {
    return setTerm(new WhereTerm(field, value));
  }

  public boolean setTerm(String field, String term, Object value) {
    return setTerm(new WhereTerm(field, term, value));
  }

  public boolean setTerm(String field, String term, byte value) {
    return setTerm(new WhereTerm(field, term, value));
  }

  public boolean setTerm(String field, String term, double value) {
    return setTerm(new WhereTerm(field, term, value));
  }

  public boolean setTerm(String field, String term, float value) {
    return setTerm(new WhereTerm(field, term, value));
  }

  public boolean setTerm(String field, String term, int value) {
    return setTerm(new WhereTerm(field, term, value));
  }

  public boolean setTerm(String field, String term, long value) {
    return setTerm(new WhereTerm(field, term, value));
  }

  public boolean setTerm(String field, String term, short value) {
    return setTerm(new WhereTerm(field, term, value));
  }

  public WhereTerm getTerm(int index) {
    return _terms.getWhereTerm(index);
  }

  public WhereTerm getTerm(String field, int index) {
    return _terms.getWhereTerm(field, index);
  }

  public WhereTerm getTerm(String field) {
    return _terms.getWhereTerm(field);
  }

  public void setTerms(WhereTerms terms) {
    _terms = terms;
  }

  public WhereTerms getTerms() {
    return _terms;
  }

  public void setWhereTermsAnd(boolean and) {
    _terms.setAnd(and);
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();

    buffer.append(_update);
    buffer.append(_table);
    buffer.append(_set);
    buffer.append(_sets.toString());
    buffer.append(_where);
    buffer.append(_terms.toString());

    return buffer.toString();
  }
}

