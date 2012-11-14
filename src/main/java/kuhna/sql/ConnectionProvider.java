package kuhna.sql;

import java.sql.Connection;

import javax.naming.Context;

import kuhna.naming.ContextProvider;

/**
 * Database Connection을 얻는 가장 간단한 메쏘드를 가진 인터페이스 
 *
 * @version 0.4, 2004/12/29, added getConnection(ContextProvider, String) method by A.J.Kuhn<BR><!--
 * @version -->0.3, 2004/09/30, changed Exception to ConnectionProviderException by A.J.Kuhn<BR><!--
 * @version -->0.2, 2002/09/30, added getConnection(x) method by A.J.Kuhn<BR><!--
 * @version -->0.1, 2002/03/21, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public interface ConnectionProvider {

  /**
   * Database Connection을 얻는 가장 간단한 메쏘드. Application마다 Customizing 하게 이를 구현한 클래스를 만들어 사용한다.
   *
   * @return java.sql.Connection
   */
  public Connection getConnection() throws ConnectionProviderException; 

  /**
   * 특정조건의 Database Connection을 얻는다. '특정조건'은 Application의 성격에따라 따로이 규정하여 구현한다.
   *
   * @return java.sql.Connection
   */
  public Connection getConnection(Object condition) throws ConnectionProviderException; 

  /**
   * ContextProvider와 DataSource이름을 가지고 Connection을 얻는다.
   * 
   * @param	cp     Context를 공급하는 ContextProvider
   * @param	dsName DataSource 이름
   * @return       java.sql.Connection
   */
  public Connection getConnection(ContextProvider cp, String dsName) throws ConnectionProviderException;

  /**
   * Context와 DataSource이름을 가지고 Connection을 얻는다.
   * 
   * @param	ctx    DataSource resource가 있는 Context
   * @param	dsName DataSource 이름
   * @return       java.sql.Connection
   */
  public Connection getConnection(Context ctx, String dsName) throws ConnectionProviderException;

  /**
   * Driver이름, DB URL, ID, Password 를 가지고 Connection을 얻는다.
   * 
   * @param	name JDBC Driver이름
   * @param	url  Database URL
   * @param	id   접근 ID
   * @param	pw   비밀번호
   * @return     java.sql.Connection
   */
  public Connection getConnection(String name, String url, String id, String pw) throws ConnectionProviderException;
}

