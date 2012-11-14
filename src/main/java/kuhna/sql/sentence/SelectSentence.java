package kuhna.sql.sentence;

import java.util.ArrayList;
import java.util.List;

/**
 * Select문장, 다양한 메쏘드를 통해 Select문장을 구성할 수 있다. 
 *
 * @version 0.9.1, 2011/11/13, added setOrderBys, setGroupBys method by A.J.Kuhn<BR><!-- 
 * @version -->0.9, 2009/10/18, added method about group by and member variable changed Vector to List by A.J.Kuhn<BR><!-- 
 * @version -->0.8.4, 2006/05/10, added setWhereTermsAnd() method by A.J.Kuhn<BR><!-- 
 * @version -->0.8.3, 2004/05/13, added term relative methods by A.J.Kuhn<BR><!--  
 * @version -->0.8, 2004/04/29, this class extended SQLSentence not implemented by A.J.Kuhn<BR><!--  
 * @version -->0.7, 2003/11/03, addTerm(x,x,x) methods by A.J.Kuhn<BR><!--  
 * @version -->0.6, 2003/11/01, addTerm(x,x) methods by A.J.Kuhn<BR><!--  
 * @version -->0.5, 2003/09/20, implements Serializable by A.J.Kuhn<BR><!--  
 * @version -->0.4, 2002/05/27, added setTerms(x) method. by A.J.Kuhn<BR><!--  
 * @version -->0.3, 2002/05/20, toString() method was modified. by A.J.Kuhn<BR><!--  
 * @version -->0.2, 2002/05/17, all methods were implemented.. by A.J.Kuhn<BR><!--  
 * @version -->0.1.1, 2002/04/02, change package.. by A.J.Kuhn<BR><!--  
 * @version -->0.1, 2002/03/06, initial version by A.J.Kuhn  
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class SelectSentence extends SQLSentence {

  private final String _select = "select ";
  private final String _from = " from ";
  private final String _where = " where ";
  private final String _orderBy = " order by ";
  private final String _groupBy = " group by ";
  private List _fields;
  private List _tables;
  private WhereTerms _terms;
  private List _orderBys;
  private List _groupBys;

  public SelectSentence() {
    clear();
  }

  public void clear() {
    _fields = new ArrayList();
    _tables = new ArrayList();
    _terms = new WhereTerms();
    _orderBys = new ArrayList();
    _groupBys = new ArrayList();
  }

  public void addField(String field) {
    _fields.add(field);
  }

  public void addTable(String table) {
    _tables.add(table);
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

  public void addOrderBy(String orderBy) {
    _orderBys.add(orderBy);
  }

  public void setOrderBys(List orderBys) {
    _orderBys = orderBys;
  }

  public void addGroupBy(String groupBy) {
    _groupBys.add(groupBy);
  }
  
  public void setGroupBys(List groupBys) {
    _groupBys = groupBys;
  }

  public void setWhereTermsAnd(boolean and) {
    _terms.setAnd(and);
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();

    buffer.append(_select);
    int fieldSize = _fields.size();
    for(int i=0; i<fieldSize; i++) {
      buffer.append((String)_fields.get(i));
      if(i < fieldSize - 1)
        buffer.append(", ");
    }

    buffer.append(_from);
    int tableSize = _tables.size();
    for(int i=0; i<tableSize; i++) {
      buffer.append((String)_tables.get(i));
      if(i < tableSize - 1)
        buffer.append(", ");
    }

    if(_terms != null && _terms.size() != 0) {
      buffer.append(_where);
      buffer.append(_terms.toString());
    }

    if(_orderBys != null && _orderBys.size() != 0) {
      buffer.append(_orderBy);
      int orderBySize = _orderBys.size();
      for(int i=0; i<orderBySize; i++) {
        buffer.append((String)_orderBys.get(i));
        if(i < orderBySize - 1)
          buffer.append(", ");
      }
    }
    
    if(_orderBys != null && _groupBys.size() != 0) {
      buffer.append(_groupBys);
      int _groupBySize = _groupBys.size();
      for(int i=0; i<_groupBySize; i++) {
        buffer.append((String)_groupBys.get(i));
        if(i < _groupBySize - 1)
          buffer.append(", ");
      }
    }

    return buffer.toString();
  }
}
