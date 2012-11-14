package kuhna.ejb;

import javax.ejb.SessionContext;

/**
 * SQL문을 수행하는 로직을 가진 SessionBean의 기본 Bean Class.<BR>?
 * SessionBean 인터페이스에 대한 기본구현을 갖는다. setSQLActor() 메쏘드는 구현해야한다
 *
 * @version 0.6.2, 2005/02/14, changed to abstract class by A.J.Kuhn<BR><!--
 * @version -->0.6, 2004/10/01, changed class name DefaultKuhnaSessionBean to DefaultSQLSessionBean by A.J.Kuhn<BR><!--
 * @version -->0.5, 2004/03/29, extends class changed DefaultSQLActor to AbstractKuhnaSessionBean, It's revolution! by A.J.Kuhn<BR><!--
 * @version -->0.4, 2004/03/24, refactoring from BaseSLEJB by A.J.Kuhn<BR><!--
 * @version -->0.3, 2003/10/23, add getRecord() methods by A.J.Kuhn<BR><!--
 * @version -->0.2, 2003/10/22, override DefaultSQLActor's methods by A.J.Kuhn<BR><!--
 * @version -->0.1, 2003/09/20, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public abstract class DefaultSQLSessionBean extends AbstractSQLSessionBean {

  /** SessionBean 객체를 구분하는 고유값 */
  public SessionContext _context;

/********************************* SessionBean Interface의 구현 *****************************************/

  public void ejbActivate() {

  }

  public void ejbPassivate() {

  }

  public void ejbRemove() {

  }

  public void setSessionContext(SessionContext context) {
    _context = context;
  }

/********************************************************************************************************/
}
