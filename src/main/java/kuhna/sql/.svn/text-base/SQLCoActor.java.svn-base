package kuhna.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kuhna.sql.sentence.SQLSentence;

/**
 * Connection을 필요로 하는 SQL문장 실행기이다. 독자적으로 사용도 가능하며,
 * SQLActor를 구현하기위한 Super Class로 사용하면 편리하다.<BR>
 *
 * @version 0.8, 2009/10/12, changed all return types Collection to List by A.J.Kuhn<BR><!--
 * @version	-->0.7.2, 2005/03/17, changed getRecord() methods private to protected by A.J.Kuhn<BR><!--
 * @version	-->0.7.1, 2005/03/16, modified getRecord(Record, ResultSet, ResultSetMetaData, Map, int) method by A.J.Kuhn<BR><!--
 * @version	-->0.7, 2004/03/23, Class name changed 'SQLActor' -> 'SQLCoActor', add _caseMode attribute, move from SQLUtil's getRecord() series for sync. by A.J.Kuhn<BR><!--
 * @version	-->0.6, 2003/10/23, added getRecord() methods, remove getDataModel method by A.J.Kuhn<BR><!--
 * @version	-->0.5, 2003/09/20, executeUpdates()'s argument is changed Vector to Collection, add executeQueries() method by A.J.Kuhn<BR><!--
 * @version	-->0.4, 2002/11/07, executeQuery(), executeUpdate method modified, close ResultSet, Statement Object in exception.. by A.J.Kuhn<BR><!--
 * @version	-->0.3, 2002/03/29, added getDataModel(x,x,x) method by A.J.Kuhn<BR><!--
 * @version	-->0.2, 2002/03/28, added Constructor, modify executeQuery(Connection, String) method, arrange indent by A.J.Kuhn<BR><!--
 * @version	-->0.1, 2002/03/06, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class SQLCoActor extends Object {

  protected int _caseMode;

  public SQLCoActor() {
    setCaseMode(SQLUtil.DEFAULT_CASE);
  }

  public SQLCoActor(int caseMode) {
    setCaseMode(caseMode);
  }

  public void setCaseMode(int mode) {
    _caseMode = mode;
  }

  public int getCaseMode() {
    return _caseMode;
  }

  public List executeQuery(Connection connection, String sql) throws SQLException {
    List list = new ArrayList();

    Statement st = null;
    ResultSet rs = null;
    SQLException sqlEx = null;

    try {
      st = connection.createStatement();

      // 임시 로그...
//      System.out.println(sql);

      rs = st.executeQuery(sql);
      ResultSetMetaData rsmd = rs.getMetaData();
      Map typeMap = connection.getTypeMap();

      int row = 1; // Performance 향상을위해 ResultSet에서 row를 가져오지 않고 직접연산
      while(rs.next()) {
        Record record = getRecord(rs, rsmd, typeMap, sql, row, _caseMode);
        list.add(record);
        row++;
      }
    } catch(SQLException ex) {
      sqlEx = ex;
    } finally {
      try {
        if(rs != null)
          rs.close();
        if(st != null)
          st.close();
      } catch(SQLException ex) {
        ;
      }
    }

    if(sqlEx != null)
      throw sqlEx;

    return list;
  }

  public List executeQuery(Connection connection, SQLSentence sentence) throws SQLException {
    return executeQuery(connection, sentence.toString());
  }

  public Record getRecord(Connection connection, String sql, int index) throws SQLException {
    Record record = null;

    List list = executeQuery(connection, sql);
    try {
      record = (Record)list.get(index);
    } catch(ArrayIndexOutOfBoundsException ex) {
      ;
    }

    return record;
  }

  public Record getRecord(Connection connection, SQLSentence sentence, int index) throws SQLException {
    return getRecord(connection, sentence.toString(), index);
  }

  /**
   * 리턴 List에 들어있는 Record들의 attribute구성은 서로 상이할수 있다.
   *
   */
  public List executeQueries(Connection connection, Collection sqls) throws SQLException {
    if(sqls == null)
      throw new IllegalArgumentException("sqls arg is null");

    List list = new ArrayList();
    Iterator itr = sqls.iterator();
    while(itr.hasNext()) {
      Object obj = itr.next();
      String sql = null;

      if(obj instanceof SQLSentence)
        sql = ((SQLSentence)obj).toString();
      else if(obj instanceof String)
        sql = (String)obj;
      else 
        throw new IllegalArgumentException("sqls's element must be SQLSentence or String");

      list.addAll(executeQuery(connection, sql));
    }

    return list;
  }

  public int executeUpdate(Connection connection, String sql) throws SQLException {
    int count = 0;

    Statement st = null;
    SQLException sqlEx = null;

    try {
      st = connection.createStatement();

      // 임시 로그...
//    System.out.println(sql);

      count = st.executeUpdate(sql);
    } catch(SQLException ex) {
      sqlEx = ex;
    } finally {
      try {
        if(st != null)
          st.close();
      } catch(SQLException ex) {
        ;
      }
    }

    if(sqlEx != null)
      throw sqlEx;

    return count;
  }

  public int executeUpdate(Connection connection, SQLSentence sentence) throws SQLException {
    return executeUpdate(connection, sentence.toString());
  }

  public int executeUpdates(Connection connection, Collection sqls) throws SQLException {
    int count = 0;

    if(sqls == null)
      throw new IllegalArgumentException("sqls arg is null");

    Iterator itr = sqls.iterator();
    while(itr.hasNext()) {
      Object obj = itr.next();
      String sql = null;

      if(obj instanceof SQLSentence)
        sql = ((SQLSentence)obj).toString();
      else if(obj instanceof String)
        sql = (String)obj;
      else 
        throw new IllegalArgumentException("sqls's element must be SQLSentence or String");

      count += executeUpdate(connection, sql);
    }

    return count;
  }

  /**
   * ResultSet Data를 Record Data로 변환한다.
   * ResultSet으로부터 ResultSetMetaData와 Row값을 추출하지 않으므로 최고의 Performance를 얻게된다. 여러 Record를 Retrieve하는 경우 사용하면 좋다.
   *
   * @param rs       java.sql.ResultSet
   * @param rsmd     java.sql.ResultSetMetaData
   * @param typeMap  접속한 Database가 가진 Field Type 정보
   * @param sql      SQL문장
   * @param row      ResultSet의 현재 row
   * @param caseMode Record의 필드이름의 대소문자 구분값
   * @return         ResultSet의 데이터가 들어가 있는 kuhna.sql.Record 객체 
   */
  protected Record getRecord(ResultSet rs, ResultSetMetaData rsmd, Map typeMap, String sql, int row, int caseMode) throws SQLException {
    Record record = new Record(sql, row);

    return getRecord(record, rs, rsmd, typeMap, caseMode);
  }

  /**
   * ResultSet Data를 Record Data로 변환한다.<BR>
   * ResultSet으로부터 Row값을 추출하지 않으므로 중간의 Performance를 얻게된다.
   *
   * @param rs       java.sql.ResultSet
   * @param typeMap  접속한 Database가 가진 Field Type 정보
   * @param sql      SQL문장
   * @param row      ResultSet의 현재 row
   * @param caseMode Record의 필드이름의 대소문자 구분값
   * @return         ResultSet의 데이터가 들어가 있는 kuhna.sql.Record 객체 
   */
  protected Record getRecord(ResultSet rs, Map typeMap, String sql, int row, int caseMode) throws SQLException {
    Record record = new Record(sql, row);
    ResultSetMetaData rsmd = rs.getMetaData();

    return getRecord(record, rs, rsmd, typeMap, caseMode);
  }

  /**
   * ResultSet Data를 Record Data로 변환한다.
   * ResultSet으로부터 ResultSetMetaData를 추출하지 않으므로 중간의 Performance를 얻게된다.
   *
   * @param rs       java.sql.ResultSet
   * @param rsmd     java.sql.ResultSetMetaData
   * @param typeMap  접속한 Database가 가진 Field Type 정보
   * @param sql      SQL문장
   * @param caseMode Record의 필드이름의 대소문자 구분값
   * @return         ResultSet의 데이터가 들어가 있는 kuhna.sql.Record 객체 
   */
  protected Record getRecord(ResultSet rs, ResultSetMetaData rsmd, Map typeMap, String sql, int caseMode) throws SQLException {
    Record record = new Record(sql, rs.getRow());

    return getRecord(record, rs, rsmd, typeMap, caseMode);
  }

  /**
   * ResultSet Data를 Record Data로 변환한다.<BR>
   * ResultSet으로부터 ResultSetMetaData와 Row를 모두추출하므로 최저의 Performance를 보인다. 단일 레코드의 Retrieve에 적당하다(Argument가 가장 적은 잇점이 있다).
   *
   * @param rs       java.sql.ResultSet
   * @param typeMap  접속한 Database가 가진 Field Type 정보
   * @param sql      SQL문장
   * @param caseMode Record의 필드이름의 대소문자 구분값
   * @return         ResultSet의 데이터가 들어가 있는 kuhna.sql.Record 객체 
   */
  protected Record getRecord(ResultSet rs, Map typeMap, String sql, int caseMode) throws SQLException {
    Record record = new Record(sql, rs.getRow());
    ResultSetMetaData rsmd = rs.getMetaData();

    return getRecord(record, rs, rsmd, typeMap, caseMode);
  }

  protected Record getRecord(Record record, ResultSet rs, ResultSetMetaData rsmd, Map typeMap, int caseMode) throws SQLException {
    for(int i=1; i<=rsmd.getColumnCount(); i++) {
      String columnName = rsmd.getColumnName(i);

      if(caseMode == SQLUtil.UPPER_CASE)
        columnName.toUpperCase();
      else if(caseMode == SQLUtil.LOWER_CASE)
        columnName.toLowerCase();

      int columnType = rsmd.getColumnType(i);

      if(columnType != Types.NULL) {
        Object obj = rs.getObject(i, typeMap);
        if(obj != null)
          record.set(columnName, obj);
      }
    }

    return record;
  }
}

