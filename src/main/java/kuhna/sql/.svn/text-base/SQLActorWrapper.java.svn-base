package kuhna.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import kuhna.sql.sentence.SQLSentence;

/**
 * SQLActor의 Wrapper 클래스.<BR>
 *
 * @version 0.2, 2009/10/12, changed all return types Collection to List by A.J.Kuhn<BR><!--
 * @version -->0.1.1, 2005/04/07, added getConnection() method by A.J.Kuhn<BR><!--
 * @version -->0.1, 2004/03/29, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class SQLActorWrapper extends Object implements SQLActor {

  protected SQLActor _sqlActor;

  protected SQLActorWrapper() {

  }

  public SQLActorWrapper(SQLActor actor) {
    super();
    setSQLActor(actor);
  }

  public SQLActorWrapper(SQLActor actor, int caseMode) {
    super();
    setSQLActor(actor);
    setCaseMode(caseMode);
  }

  public void setSQLActor(SQLActor actor) {
    _sqlActor = actor;
  }

  public void setCaseMode(int caseMode) {
    _sqlActor.setCaseMode(caseMode);
  }

  public int getCaseMode() {
    return _sqlActor.getCaseMode();
  }

  public Connection getConnection() throws SQLException {
    return _sqlActor.getConnection();
  }

  public List executeQuery(Connection con, String sql) throws SQLException {
    return _sqlActor.executeQuery(con, sql);
  }

  public List executeQuery(Connection con, SQLSentence sentence) throws SQLException {
    return _sqlActor.executeQuery(con, sentence);
  }

  public Record getRecord(Connection con, String sql, int index) throws SQLException {
    return _sqlActor.getRecord(con, sql, index);
  }

  public Record getRecord(Connection con, SQLSentence sentence, int index) throws SQLException {
    return _sqlActor.getRecord(con, sentence, index);
  }

  public List executeQueries(Connection con, Collection sqls) throws SQLException {
    return _sqlActor.executeQueries(con, sqls);
  }

  public int executeUpdate(Connection con, String sql) throws SQLException {
    return _sqlActor.executeUpdate(con, sql);
  }

  public int executeUpdate(Connection con, SQLSentence sentence) throws SQLException {
    return _sqlActor.executeUpdate(con, sentence);
  }

  public int executeUpdates(Connection con, Collection sqls) throws SQLException {
    return _sqlActor.executeUpdates(con, sqls);
  }

  public List executeQuery(String sql) throws SQLException {
    return _sqlActor.executeQuery(sql);
  }

  public List executeQuery(SQLSentence sentence) throws SQLException {
    return _sqlActor.executeQuery(sentence);
  }

  public Record getRecord(String sql, int index) throws SQLException {
    return _sqlActor.getRecord(sql, index);
  }

  public Record getRecord(SQLSentence sentence, int index) throws SQLException {
    return _sqlActor.getRecord(sentence, index);
  }

  public List executeQueries(Collection sqls) throws SQLException {
    return _sqlActor.executeQueries(sqls);
  }

  public int executeUpdate(String sql) throws SQLException {
    return _sqlActor.executeUpdate(sql);
  }

  public int executeUpdate(SQLSentence sentence) throws SQLException {
    return _sqlActor.executeUpdate(sentence);
  }

  public int executeUpdates(Collection sqls) throws SQLException {
    return _sqlActor.executeUpdates(sqls);
  }
}

