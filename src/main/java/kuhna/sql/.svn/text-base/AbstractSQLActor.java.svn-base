package kuhna.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import kuhna.sql.sentence.SQLSentence;

/**
 * Kuhna의 SQL Framework을 유도하기위한 추상클래스.
 * 실제로 필요는없다고 봐도 좋다. SQLActor를 SQLCoActor를 이용해 구현하면 되므로<BR>
 * 
 * @version 0.2, 2009/10/12, changed all return types Collection to List by A.J.Kuhn<BR><!--
 * @version -->0.1.1, 2005/04/07, added getConnection() method by A.J.Kuhn<BR><!--
 * @version -->0.1, 2004/03/23, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public abstract class AbstractSQLActor extends SQLCoActor implements SQLActor {

  protected AbstractSQLActor() {
    super();
  }

  protected AbstractSQLActor(int caseMode) {
    super(caseMode);
  }

  public abstract Connection getConnection() throws SQLException;

  public abstract List executeQuery(String sql) throws SQLException;

  public abstract List executeQuery(SQLSentence sentence) throws SQLException;

  public abstract Record getRecord(String sql, int index) throws SQLException;

  public abstract Record getRecord(SQLSentence sentence, int index) throws SQLException;

  public abstract List executeQueries(Collection sqls) throws SQLException;

  public abstract int executeUpdate(String sql) throws SQLException;

  public abstract int executeUpdate(SQLSentence sentence) throws SQLException;

  public abstract int executeUpdates(Collection sqls) throws SQLException;
}

