package kuhna.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import kuhna.sql.sentence.SQLSentence;

/**
 * SQLActor의 기본구현 클래스.<BR>
 * ConnectionProvider를 생성자에서 argument로 제공받으며, 이것으로부터 커넥션을 얻는 내부구현이다.<BR>
 * 커넥션의 해제는 connection.close()로 처리한다. 따라서 커넥션풀을 사용할경우 JDBC2.0스펙의 DataSource방식일 경우만 유효하며
 * 다른방식의 커넥션풀을 사용할경우는 AbstractSQLActor를 따로이 구현하여 사용해야한다.
 *
 * @version 0.5, 2009/10/12, changed all return types Collection to List and modified connection close Exception handling by A.J.Kuhn<BR><!--
 * @version -->0.4.1, 2005/04/07, modified getConnection() method protected to public, by A.J.Kuhn<BR><!--
 * @version -->0.4, 2004/10/02, modified getConnection() method by A.J.Kuhn<BR><!--
 * @version -->0.3, 2004/03/24, add 2 constructor, add setConnectionProvider() method by A.J.Kuhn<BR><!--
 * @version -->0.2, 2004/03/23, refactoring by A.J.Kuhn<BR><!--
 * @version -->0.1, 2003/09/23, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class DefaultSQLActor extends AbstractSQLActor {

  protected ConnectionProvider _connectionProvider;

  public DefaultSQLActor(ConnectionProvider connectionProvider) {
    super();
    _connectionProvider = connectionProvider;
  }

  public DefaultSQLActor(ConnectionProvider connectionProvider, int caseMode) {
    super(caseMode);
    _connectionProvider = connectionProvider;
  }

  public void setConnectionProvider(ConnectionProvider provider) {
    _connectionProvider = provider;
  }
  
  public Connection getConnection() throws SQLException {
    Connection con = null;
    try {
      con = _connectionProvider.getConnection();
    } catch(ConnectionProviderException ex) {
      throw new SQLException(ex.getMessage());
    }

    return con;
  }

  public List executeQuery(String sql) throws SQLException {
    List retValue = null;

    Connection con = getConnection();
    try {
      retValue = executeQuery(con, sql);
    } catch(SQLException ex) {
      // Exception이 발생한경우 connection close Exception은 무시하고 쿼리 Exception을 throw한다.
      if(con != null) {
        try {
          con.close(); 
        } catch(SQLException ex2) {
          ;
        }
      }
      throw ex;
    }
    // 쿼리 완료 후 발생한 connection close Exception 은 throw한다.
    con.close();

    return retValue;
  }

  public List executeQuery(SQLSentence sentence) throws SQLException {
    return executeQuery(sentence.toString());
  }

  public Record getRecord(String sql, int index) throws SQLException {
    Record record = null;

    Connection con = getConnection();
    try {
      record = getRecord(con, sql, index);
    } catch(SQLException ex) {
      // Exception이 발생한경우 connection close Exception은 무시하고 쿼리 Exception을 throw한다.
      if(con != null) {
        try {
          con.close(); 
        } catch(SQLException ex2) {
          ;
        }
      }
      throw ex;
    }
    // 쿼리 완료 후 발생한 connection close Exception 은 throw한다.
    con.close();

    return record;
  }

  public Record getRecord(SQLSentence sentence, int index) throws SQLException {
    return getRecord(sentence.toString(), index);
  }

  public List executeQueries(Collection sqls) throws SQLException {
    List retValue = null;

    Connection con = getConnection();
    try {
      retValue = executeQueries(con, sqls);
    } catch(SQLException ex) {
      // Exception이 발생한경우 connection close Exception은 무시하고 쿼리 Exception을 throw한다.
      if(con != null) {
        try {
          con.close(); 
        } catch(SQLException ex2) {
          ;
        }
      }
      throw ex;
    }
    // 쿼리 완료 후 발생한 connection close Exception 은 throw한다.
    con.close();

    return retValue;
  }

  public int executeUpdate(String sql) throws SQLException {
    int retValue = 0;

    Connection con = getConnection();
    try {
      retValue = executeUpdate(con, sql);
    } catch(SQLException ex) {
      // Exception이 발생한경우 connection close Exception은 무시하고 쿼리 Exception을 throw한다.
      if(con != null) {
        try {
          con.close(); 
        } catch(SQLException ex2) {
          ;
        }
      }
      throw ex;
    }
    // 쿼리 완료 후 발생한 connection close Exception 은 throw한다.
    con.close();

    return retValue;
  }

  public int executeUpdate(SQLSentence sentence) throws SQLException {
    return executeUpdate(sentence.toString());
  }

  public int executeUpdates(Collection sqls) throws SQLException {
    int retValue = 0;

    Connection con = getConnection();
    try {
      retValue = executeUpdates(con, sqls);
    } catch(SQLException ex) {
      // Exception이 발생한경우 connection close Exception은 무시하고 쿼리 Exception을 throw한다.
      if(con != null) {
        try {
          con.close(); 
        } catch(SQLException ex2) {
          ;
        }
      }
      throw ex;
    }
    // 쿼리 완료 후 발생한 connection close Exception 은 throw한다.
    con.close();

    return retValue;
  }
}

