package kuhna.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import kuhna.sql.sentence.SQLSentence;

/**
 * SQL문장의 실행기이다.<BR>
 * 프로젝트마다 적절한 구현 클래스를 만들어 사용한다. 보통은 AbstractSQLActor를 구현하여 이용한다.<BR>
 *
 * @version	0.2, 2009/10/12, changed all return types Collection to List by A.J.Kuhn<BR><!--
 * @version	-->0.1.1, 2005/04/07, added getConnection() method by A.J.Kuhn<BR><!--
 * @version	-->0.1, 2004/03/23, changed class to interface. initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public interface SQLActor {

  public void setCaseMode(int mode);

  public int getCaseMode();

  public Connection getConnection() throws SQLException;

  public List executeQuery(Connection connection, String sql) throws SQLException; 

  public List executeQuery(Connection connection, SQLSentence sentence) throws SQLException;

  public List executeQuery(String sql) throws SQLException; 

  public List executeQuery(SQLSentence sentence) throws SQLException;

  public Record getRecord(Connection connection, String sql, int index) throws SQLException;

  public Record getRecord(Connection connection, SQLSentence sentence, int index) throws SQLException;

  public Record getRecord(String sql, int index) throws SQLException;

  public Record getRecord(SQLSentence sentence, int index) throws SQLException;

  public List executeQueries(Connection connection, Collection sqls) throws SQLException;

  public List executeQueries(Collection sqls) throws SQLException;

  public int executeUpdate(Connection connection, String sql) throws SQLException;

  public int executeUpdate(Connection connection, SQLSentence sentence) throws SQLException;

  public int executeUpdates(Connection connection, Collection sqls) throws SQLException; 

  public int executeUpdate(String sql) throws SQLException;

  public int executeUpdate(SQLSentence sentence) throws SQLException;

  public int executeUpdates(Collection sqls) throws SQLException; 
}

