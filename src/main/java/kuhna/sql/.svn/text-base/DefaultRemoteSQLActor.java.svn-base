package kuhna.sql;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

/**
 * Remote로 작동가능한 SQLActor의 기본구현 클래스.<BR>
 * ConnectionProvider를 생성자에서 argument로 제공받으며, 이것으로부터 커넥션을 얻는 내부구현이다.<BR>
 * 커넥션의 해제는 connection.close()로 처리한다. 따라서 커넥션풀을 사용할경우 JDBC2.0스펙의 DataSource방식일 경우만 유효하며
 * 다른방식의 커넥션풀을 사용할경우는 AbstractRemoteSQLActor를 따로이 구현하여 사용해야한다.
 *
 * @version 0.2, 2007/05/28, modified getRecord methods' CLOB section by A.J.Kuhn<BR><!--
 * @version -->0.1, 2005/03/17, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class DefaultRemoteSQLActor extends DefaultSQLActor {

  public DefaultRemoteSQLActor(ConnectionProvider connectionProvider) {
    super(connectionProvider);
  }

  public DefaultRemoteSQLActor(ConnectionProvider connectionProvider, int caseMode) {
    super(connectionProvider, caseMode);
  }

  /**
   * SQLCoActor의 Override<BR>
   * Remote로 SQLActor가 될때, Record의 내부객체를 Serializable객체로 만든다<BR>
   *
   * @param rs       java.sql.ResultSet
   * @param typeMap  접속한 Database가 가진 Field Type 정보
   * @param caseMode Record의 필드이름의 대소문자 구분값
   * @return         ResultSet의 데이터가 들어가 있는 kuhna.sql.Record 객체 
   */
  protected Record getRecord(Record record, ResultSet rs, ResultSetMetaData rsmd, Map typeMap, int caseMode) throws SQLException {
    for(int i=1; i<=rsmd.getColumnCount(); i++) {
      String columnName = rsmd.getColumnName(i);

      if(caseMode == SQLUtil.UPPER_CASE)
        columnName.toUpperCase();
      else if(caseMode == SQLUtil.LOWER_CASE)
        columnName.toLowerCase();

      int columnType = rsmd.getColumnType(i);

      if(columnType == Types.NULL) {
        ;
      } else if(columnType == Types.BLOB) {
        Blob blob = (Blob)rs.getObject(i, typeMap);

        long length = blob.length();
        if(length > Integer.MAX_VALUE)
          throw new SQLException("Blob size is over " + Integer.MAX_VALUE + "(" + length + ")");

        byte[] bytes = new byte[(int)length];
        InputStream in = blob.getBinaryStream();

        try {
          in.read(bytes, 0, (int)length);
        } catch(IOException ex) {
          throw new SQLException("Blob read failed : " + ex.getMessage());
        } finally {
          try {
            in.close();
          } catch(IOException ex) {
            
          }
        }

        record.set(columnName, bytes);
      } else if(columnType == Types.CLOB) {
        Clob clob = (Clob)rs.getObject(i, typeMap);

        long length = clob.length();
        if(length > Integer.MAX_VALUE)
          throw new SQLException("Clob size is over " + Integer.MAX_VALUE + "(" + length + ")");

        char[] chars = new char[(int)length];
        Reader reader = clob.getCharacterStream();
        
        
        try {
          reader.read(chars);
        } catch(IOException ex) {
          throw new SQLException("Clob read failed : " + ex.getMessage());
        } finally {
          try {
            reader.close();
          } catch(IOException ex) {
            
          }
        }

        record.set(columnName, chars);
      // 추가적인 Type 구현이 필요할 수 있다.
      } else {
        Object obj = rs.getObject(i, typeMap);
        if(obj != null) {
          if(obj instanceof Serializable)
            record.set(columnName, obj);
          else
            record.set(columnName, obj.toString());
        }
      }
    }

    return record;
  }  
}
