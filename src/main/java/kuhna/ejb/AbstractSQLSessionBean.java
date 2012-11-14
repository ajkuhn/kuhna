package kuhna.ejb;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import kuhna.sql.Record;
import kuhna.sql.SQLActorWrapper;
import kuhna.sql.sentence.SQLSentence;

/**
 * SQL문을 수행하는 로직을 가진 SessionBean의 추상 Bean Class<BR>
 * SessionBean Interface와 setSQLActor()를 구현해야한다.
 *
 * @version 0.8, 2009/10/12, changed all return types Collection to List by A.J.Kuhn<BR><!--
 * @version -->0.7.2, 2005/02/14, removed ejbCreate(SQLActor) method, added ejbCreate() method, added setSQLActor() abstract method by A.J.Kuhn<BR><!--
 * @version -->0.7, 2004/10/01, changed class name AbstractKuhnaSessionBean to AbstractSQLSessionBean and throw KuhnaEJbException to EJBException by A.J.Kuhn<BR><!--
 * @version -->0.6, 2004/03/29, extends class changed AbstractSQLActor to SQLActorWrapper, It's revolution! by A.J.Kuhn<BR><!--
 * @version -->0.5, 2004/03/23, refactoring from BaseSLEJB by A.J.Kuhn<BR><!--
 * @version -->0.4, 2004/02/12, add _rm attribute by A.J.Kuhn<BR><!--
 * @version -->0.3, 2003/10/23, add getRecord() methods by A.J.Kuhn<BR><!--
 * @version -->0.2, 2003/10/22, override AbstractSQLActor's methods by A.J.Kuhn<BR><!--
 * @version -->0.1, 2003/09/20, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public abstract class AbstractSQLSessionBean extends SQLActorWrapper implements SessionBean {

  public void ejbCreate() {
    setSQLActor();
  }

/********************************* SessionBean Interface의 구현 *****************************************/

  public abstract void ejbActivate();

  public abstract void ejbPassivate();

  public abstract void ejbRemove();

  public abstract void setSessionContext(SessionContext context);

/********************************************************************************************************/

  protected abstract void setSQLActor();

  public void setCaseMode(int mode) throws EJBException {
    super.setCaseMode(mode);
  }

  public int getCaseMode() throws EJBException {
    return super.getCaseMode();
  }

  public List executeQuery(String sql) throws EJBException {
    List list = null;

    try {
      list = super.executeQuery(sql);
    } catch(Exception ex) {
      throw new EJBException(ex);
    }

    return list;
  }

  public List executeQuery(SQLSentence sentence) throws EJBException {
    return executeQuery(sentence.toString());
  }

  public Record getRecord(String sql, int index) throws EJBException {
    Record record = null;

    try {
      record = super.getRecord(sql, index);
    } catch(Exception ex) {
      throw new EJBException(ex);
    }

    return record;
  }

  public Record getRecord(SQLSentence sentence, int index) throws EJBException {
    return getRecord(sentence.toString(), index);
  }

  public List executeQueries(Collection sqls) throws EJBException {
    List list = null;

    try {
      list = super.executeQueries(sqls);
    } catch(Exception ex) {
      throw new EJBException(ex);
    }

    return list;
  }

  public int executeUpdate(String sql) throws EJBException {
    int n = 0;

    try {
      n = super.executeUpdate(sql);
    } catch(Exception ex) {
      throw new EJBException(ex);
    }

    return n;
  }

  public int executeUpdate(SQLSentence sentence) throws EJBException {
    return executeUpdate(sentence.toString());
  }

  public int executeUpdates(Collection sqls) throws EJBException {
    int n = 0;

    try {
      n = super.executeUpdates(sqls);
    } catch(Exception ex) {
      throw new EJBException(ex);
    }

    return n;
  }

  public List executeQuery(Connection con, String sql) throws EJBException {
    List list = null;

    try {
      list = super.executeQuery(con, sql);
    } catch(Exception ex) {
      throw new EJBException(ex);
    }

    return list;
  }

  public List executeQuery(Connection con, SQLSentence sentence) throws EJBException {
    return executeQuery(con, sentence.toString());
  }

  public Record getRecord(Connection con, String sql, int index) throws EJBException {
    Record record = null;

    try {
      record = super.getRecord(con, sql, index);
    } catch(Exception ex) {
      throw new EJBException(ex);
    }

    return record;
  }

  public Record getRecord(Connection con, SQLSentence sentence, int index) throws EJBException {
    return getRecord(con, sentence.toString(), index);
  }

  public List executeQueries(Connection con, Collection sqls) throws EJBException {
    List list = null;

    try {
      list = super.executeQueries(con, sqls);
    } catch(Exception ex) {
      throw new EJBException(ex);
    }

    return list;
  }

  public int executeUpdate(Connection con, String sql) throws EJBException {
    int n = 0;

    try {
      n = super.executeUpdate(con, sql);
    } catch(Exception ex) {
      throw new EJBException(ex);
    }

    return n;
  }

  public int executeUpdate(Connection con, SQLSentence sentence) throws EJBException {
    return executeUpdate(con, sentence.toString());
  }

  public int executeUpdates(Connection con, Collection sqls) throws EJBException {
    int n = 0;

    try {
      n = super.executeUpdates(con, sqls);
    } catch(Exception ex) {
      throw new EJBException(ex);
    }

    return n;
  }
}
