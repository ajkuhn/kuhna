package kuhna.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kuhna.naming.ContextProvider;

/**
 * ConnectionProvider 구현을 위한 몇몇 메쏘드를 지원한다.<BR>
 * Abstract Class 이므로 자체로 사용되지는 못하며, 이를 상속한 클래스를 만들어야 한다.
 *
 * @version 0.4, 2004/12/29, added getConnection(ContextProvider, String) method by A.J.Kuhn<BR><!--
 * @version -->0.3, 2004/09/30, changed Exception to ConnectionProviderException by A.J.Kuhn<BR><!--
 * @version -->0.2, 2002/09/30, added getConnection(x) method by A.J.Kuhn<BR><!--
 * @version -->0.1, 2002/03/21, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public abstract class AbstractConnectionProvider extends Object implements ConnectionProvider {

  /**
   * 생성자. 서브클래스의 생성자에서 호출한다.
   */
  protected AbstractConnectionProvider() {
    super();
  }

  /**
   * Connection을 얻는 가장 간단한 메쏘드로. 서브클래스에서 Overriding하여야 한다.
   *
   * return	java.sql.Context
   */
  public abstract Connection getConnection() throws ConnectionProviderException; 

  /**
   * 특정조건의 Database Connection을 얻는다. '특정조건'은 Application의 성격에따라 따로이 규정하여 구현한다.
   *
   * @return java.sql.Connection
   */
  public abstract Connection getConnection(Object condition) throws ConnectionProviderException; 

  /**
   * ContextProvider와 DataSource이름을 가지고 Connection을 얻는다.<BR>
   * ContextProvider.getContext()로 Context를 얻어 Connection을 얻는데 사용한다. 
   * 
   * @param	cp     Context를 공급하는 ContextProvider
   * @param	dsName DataSource 이름
   * @return       java.sql.Connection
   */
  public Connection getConnection(ContextProvider cp, String dsName) throws ConnectionProviderException {
    Connection con = null;

    try {
      con = getConnection(cp.getContext(), dsName);
    } catch(NamingException ex) {
      throw new ConnectionProviderException(ex, "Can't lookup [" + dsName + "] DataSource");
    }

    return con;
  }

  /**
   * Context와 DataSource이름을 가지고 Connection을 얻는다.
   * 
   * @param	ctx    DataSource resource가 있는 Context
   * @param	dsName DataSource 이름
   * @return       java.sql.Connection
   */
  public Connection getConnection(Context ctx, String dsName) throws ConnectionProviderException {
    Connection con = null;

    try {
      DataSource ds = (DataSource)ctx.lookup(dsName);
      con = ds.getConnection();
    } catch(NamingException ex) {
      throw new ConnectionProviderException(ex, "Can't lookup [" + dsName + "] DataSource");
    } catch(SQLException ex) {
      throw new ConnectionProviderException(ex, "Database access fail[" + dsName + "]");
    } catch(Throwable t) {
      throw new ConnectionProviderException(t);
    }

    return con;
  }

  /**
   * Driver이름, DB URL, ID, Password 를 가지고 Connection을 얻는다.
   * 
   * @param	name JDBC Driver이름
   * @param	url  Database URL
   * @param	id   접근 ID
   * @param	pw   비밀번호
   * @return     java.sql.Connection
   */
  public Connection getConnection(String name, String url, String id, String pw) throws ConnectionProviderException {
    Connection con = null;

    try {
      Class.forName(name);
      con = DriverManager.getConnection(url, id, pw);
    } catch(ClassNotFoundException ex) {
      throw new ConnectionProviderException(ex, "Can't load [" + name + "] class");
    } catch(SQLException ex) {
      throw new ConnectionProviderException(ex, "Database access fail URL[" + url + "], ID[" + id + "], PW[" + pw + "]");
    } catch(Throwable t) {
      throw new ConnectionProviderException(t);
    }

    return con;
  }
}

