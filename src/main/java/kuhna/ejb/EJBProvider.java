package kuhna.ejb;

import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.naming.Context;

/**
 * EJB Component를 얻는 가장 간단한 메쏘드를 가진 인터페이스 
 *
 * @version 0.3, 2004/06/30, added getEJBObject(Context,String,Class[],Object[]), getEJBObject(String,String,String,String,String,Class[],Object[]) methods by A.J.Kuhn<BR><!-- 
 * @version -->0.2, 2004/05/18, refactoring by A.J.Kuhn<BR><!--
 * @version -->0.1, 2003/09/08, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public interface EJBProvider {

  /**
   * EJB Home을 얻는 가장 간단한 메쏘드. Application마다 Customizing 하게 이를 구현한 클래스를 만들어 사용한다.
   *
   * @param jndiName EJB Component의 JNDI Name  
   * @return         EJB Home Object
   */
  public EJBHome getEJBHome(String jndiName) throws EJBProviderException;

  /**
   * EJB Home을 얻는 메쏘드
   *
   * @param ctx      EJB Component가 위치한곳의 Context  
   * @param jndiName EJB Component의 JNDI Name  
   * @return         EJB Home Object
   */
  public EJBHome getEJBHome(Context ctx, String jndiName) throws EJBProviderException;

  /**
   * EJB Home을 얻는 메쏘드
   *
   * @param icf      Initial Context Factory 이름
   * @param url      EJB Component가 등록된 Host URL
   * @param id       접근 ID
   * @param pw       접근 비밀번호
   * @param jndiName EJB Component의 JNDI Name
   * @return         EJB Home Object 
   */
  public EJBHome getEJBHome(String icf, String url, String id, String pw, String jndiName) throws EJBProviderException; 

  /**
   * EJB Object를 얻는 가장 간단한 메쏘드. Application마다 Customizing 하게 이를 구현한 클래스를 만들어 사용한다.
   *
   * @param jndiName EJB Component의 JNDI Name  
   * @return         EJB Object
   */
  public EJBObject getEJBObject(String jndiName) throws EJBProviderException;

  /**
   * EJB Object를 얻는 메쏘드
   *
   * @param ctx      EJB Component가 위치한곳의 Context
   * @param jndiName EJB Component의 JNDI Name  
   * @return         EJB Object
   */
  public EJBObject getEJBObject(Context ctx, String jndiName) throws EJBProviderException;

  /**
   * EJB Object를 얻는 메쏘드<BR>
   * EJBHome의 create()메쏘드의 argument를 받아 생성하여 리턴한다.
   *
   * @param ctx      EJB Component가 위치한곳의 Context  
   * @param jndiName EJB Component의 JNDI Name  
   * @param args     EJBHome의 create메쏘드의 argument의 배열
   * @return         EJB Object
   */
  public EJBObject getEJBObject(Context ctx, String jndiName, Object[] args) throws EJBProviderException;

  /**
   * EJB Object를 얻는 메쏘드<BR>
   * EJBHome의 create()메쏘드의 argument type과 argument를 받아 생성하여 리턴한다.
   *
   * @param ctx      EJB Component가 위치한곳의 Context
   * @param jndiName EJB Component의 JNDI Name  
   * @param argTypes EJBHome의 create메쏘드의 argument type의 배열
   * @param args     EJBHome의 create메쏘드의 argument의 배열
   * @return         EJB Object
   */
  public EJBObject getEJBObject(Context ctx, String jndiName, Class[] argTypes, Object[] args) throws EJBProviderException;

  /**
   * EJB Object를 얻는 메쏘드
   *
   * @param icf      Initial Context Factory 이름
   * @param url      EJB Component가 등록된 Host URL
   * @param id       접근 ID
   * @param pw       접근 비밀번호
   * @param jndiName EJB Component의 JNDI Name
   * @return         EJB Object 
   */
  public EJBObject getEJBObject(String icf, String url, String id, String pw, String jndiName) throws EJBProviderException; 

  /**
   * EJB Object를 얻는 메쏘드
   * EJBHome의 create()메쏘드의 argument를 받아 생성하여 리턴한다.
   *
   * @param icf        Initial Context Factory 이름
   * @param url        EJB Component가 등록된 Host URL
   * @param id         접근 ID
   * @param pw         접근 비밀번호
   * @param jndiName   EJB Component의 JNDI Name
   * @param args       EJBHome의 create메쏘드의 argument의 배열
   * @return           EJB Object 
   */
  public EJBObject getEJBObject(String icf, String url, String id, String pw, String jndiName, Object[] args) throws EJBProviderException;

  /**
   * EJB Object를 얻는 메쏘드
   * EJBHome의 create()메쏘드의 argument type과 argument를 받아 생성하여 리턴한다.
   *
   * @param icf      Initial Context Factory 이름
   * @param url      EJB Component가 등록된 Host URL
   * @param id       접근 ID
   * @param pw       접근 비밀번호
   * @param jndiName EJB Component의 JNDI Name
   * @param argTypes EJBHome의 create메쏘드의 argument type의 배열
   * @param args     EJBHome의 create메쏘드의 argument의 배열
   * @return         EJB Object 
   */
  public EJBObject getEJBObject(String icf, String url, String id, String pw, String jndiName, Class[] argTypes, Object[] args) throws EJBProviderException;
}
