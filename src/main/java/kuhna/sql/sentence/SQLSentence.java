package kuhna.sql.sentence;

import java.io.*;

/**
 * SQL문장, 최종적으로 String형태의 SQL문을 만들어주는 toString()메쏘드를 갖는다. 
 *
 * @author  A.J.Kuhn
 * @version 0.3, 2004/04/29, changed to interface to class, added _sql attribute
 *
 * @author  A.J.Kuhn
 * @version 0.2, 2002/05/17, changed to class to interface
 *
 * @author  A.J.Kuhn
 * @version 0.1.1, 2002/04/02, change package..
 *
 * @author  A.J.Kuhn
 * @version 0.1, 2002/03/06, unfinished..
 */
public class SQLSentence extends Object implements Serializable {

  private String _sql;

  public SQLSentence() {
    super();
  }

  public SQLSentence(String sql) {
    this();
    _sql = sql;
  }

  /**
   * String 형태의 SQL문장을 리턴한다.
   *
   * @return  SQL문장
   */
  public String toString() {
    return _sql;
  }
}

