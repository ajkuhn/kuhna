package kuhna.sql;

import java.sql.Connection;

import kuhna.naming.ContextProvider;

/**
 * 생성자로 ConnectionProvider, DataSource 이름을 받고 이를통하여 Connection을 공급한다<BR>
 * getConnection(Object)메쏘드는 항상 같은 Connection을 리턴한다.
 *
 * @version 0.1, 2005/02/22, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class DefaultConnectionProvider extends AbstractConnectionProvider {

  private ContextProvider _cp;
  private String _dsName;

  public DefaultConnectionProvider(ContextProvider cp, String dsName) {
    super();

    _cp = cp;
    _dsName = dsName;
  }

  /**
   * Connection을 얻는 가장 간단한 메쏘드. 생성자에서 입력된 ContextProvider와 DataSource이름에 해당하는 Connection을 리턴
   *
   * return	java.sql.Connection
   */
  public Connection getConnection() throws ConnectionProviderException {
    return getConnection(_cp, _dsName); 
  }

  /**
   * condition과 관계없이 getConnection()과 같은 결과를 얻는다.
   *
   * @return java.sql.Connection
   */
  public Connection getConnection(Object condition) throws ConnectionProviderException {
    return getConnection();
  }
}

