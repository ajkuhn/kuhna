package	kuhna.ejb;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.EJBObject;

import kuhna.sql.Record;
import kuhna.sql.sentence.SQLSentence;

/**
 * SQL문을 수행하는 로직을 가진 EJB의 EJBObject<BR>
 * Kuhna의 SQL EJB Framework을 사용하기 위해서는 EJBObject대신, 본 인터페이스를 상속받아 EJBObject를 정의해야한다.<BR>
 * EJB내에서 DB Access가 없다면 굳이 사용할필요 없다.
 *
 * @version 0.3, 2004/10/01, changed class name KuhnaEJBObject to SQLEJBObject and throw KuhnaEJbException to EJBException by A.J.Kuhn<BR><!--
 * @version -->0.2, 2004/03/23, refactoring from BaseSL<BR><!--
 * @version -->0.1, 2003/09/20, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public interface SQLEJBObject extends EJBObject {

  public void setCaseMode(int mode) throws EJBException, RemoteException;

  public int getCaseMode() throws EJBException, RemoteException;

  public List executeQuery(Connection connection, String sql) throws EJBException, RemoteException; 

  public List executeQuery(Connection connection, SQLSentence sentence) throws EJBException, RemoteException;

  public List executeQuery(String sql) throws EJBException, RemoteException; 

  public List executeQuery(SQLSentence sentence) throws EJBException, RemoteException;

  public Record getRecord(Connection connection, String sql, int index) throws EJBException, RemoteException;

  public Record getRecord(Connection connection, SQLSentence sentence, int index) throws EJBException, RemoteException;

  public Record getRecord(String sql, int index) throws EJBException, RemoteException;

  public Record getRecord(SQLSentence sentence, int index) throws EJBException, RemoteException;

  public List executeQueries(Connection connection, Collection sqls) throws EJBException, RemoteException;

  public List executeQueries(Collection sqls) throws EJBException, RemoteException;

  public int executeUpdate(Connection connection, String sql) throws EJBException, RemoteException;

  public int executeUpdate(Connection connection, SQLSentence sentence) throws EJBException, RemoteException;

  public int executeUpdates(Connection connection, Collection sqls) throws EJBException, RemoteException;

  public int executeUpdate(String sql) throws EJBException, RemoteException;

  public int executeUpdate(SQLSentence sentence) throws EJBException, RemoteException;

  public int executeUpdates(Collection sqls) throws EJBException, RemoteException; 
}
