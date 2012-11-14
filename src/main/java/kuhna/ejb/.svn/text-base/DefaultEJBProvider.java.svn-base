package kuhna.ejb;

import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.naming.Context;
import javax.naming.NamingException;

import kuhna.naming.ContextProvider;
import kuhna.naming.DefaultContextProvider;

/**
 * EJBProvider를 구현한 기본클래스, 단하나의 ContextProvider에서 EJB를 공급할때 사용된다.<BR> 
 * 여러곳에서 EJB를 공급받는 경우는 ContextProvider를 새로구현하거나 EJBProvider를 새로구현하는 두가지중 하나를 택해야한다. 
 *
 * @version 0.3, 2004/07/01, added getContext() method and refactoring by A.J.Kuhn<BR><!-- 
 * @version -->0.2, 2004/05/18, refactoring by A.J.Kuhn<BR><!--
 * @version -->0.1, 2003/09/08, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class DefaultEJBProvider extends AbstractEJBProvider {

  /** Context를 공급할 ContextProvider */
  protected ContextProvider _contextProvider;

  /**
   * 생성자.<BR>
   * DefaultContextProvider를 생성하여 ContextProvider로 등록한다.
   */
  public DefaultEJBProvider() {
    this(new DefaultContextProvider());
  }

  /**
   * 생성자.<BR>
   * ContextProvider를 argument로 받아 ContextProvider로 등록한다.
   *
   * @param contextProvider 내부에 등록할 ContextProvider
   */
  public DefaultEJBProvider(ContextProvider contextProvider) {
    super();
    setContextProvider(contextProvider);
  }

  /**
   * ContextProvider를 등록한다.
   *
   * @param contextProvider 내부에 등록할 ContextProvider
   */
  public void setContextProvider(ContextProvider contextProvider) {
    _contextProvider = contextProvider;
  }

  /**
   * EJB Home을 얻는 가장 간단한 메쏘드.<BR>
   * 내부에 설정된 ContextProvider가 공급하는 Context의 EJBHome을 리턴한다.
   *
   * @param jndiName EJB Component의 JNDI Name  
   * @return         EJB Home Object
   */
  public EJBHome getEJBHome(String jndiName) throws EJBProviderException {
    return getEJBHome(getContext(), jndiName);
  }

  /**
   * EJB Object를 얻는 가장 간단한 메쏘드.<BR>
   * 내부에 설정된 ContextProvider가 공급하는 Context를 사용하여 EJBObject를 리턴한다.
   *
   * @param jndiName EJB Component의 JNDI Name  
   * @return         EJB Object
   */
  public EJBObject getEJBObject(String jndiName) throws EJBProviderException {
    return getEJBObject(getContext(), jndiName);
  }

  /**
   * 내장된 ContextProvider로부터 Context를 얻는다. 
   *
   * @return Context
   */
  private Context getContext() throws EJBProviderException {
    Context ctx = null;

    try {
      ctx = _contextProvider.getContext();
    } catch(NamingException ex) {
      throw new EJBProviderException(ex, "Could not getting Context by _contextProvider.getContext()");
    }

    return ctx;
  }
}
