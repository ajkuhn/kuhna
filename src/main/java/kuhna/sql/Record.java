package kuhna.sql;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import kuhna.util.DataModel;


/**
 * DB Record 객체
 *
 * @version 0.3, 2004/03/23, 2004/03/23, add _row attribute, getRow(), getIndex() methods by A.J.Kuhn<BR><!--
 * @version -->0.2, 2003/11/03, implements Serializable by A.J.Kuhn<BR><!--
 * @version -->0.1, 2003/10/23, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class Record extends DataModel implements Serializable {

  private String _sql;

  private int _row;

  public Record() {
    super();
  }

  public Record(String sql, int row) {
    this();
    _sql = sql;
    _row = row;
  }

  public Record(Map t) {
    super(t);
  }

  public String getSQL() {
    return _sql;
  }

  public int getRow() {
    return _row;
  }

  public int getIndex() {
    return _row - 1;
  }
}

