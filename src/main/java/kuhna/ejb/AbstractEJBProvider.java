package kuhna.ejb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * EJBProvider를 구현한 추상클래스
 *
 * @version 0.3, 2004/07/01, added getEJBObject(Context,String,Class[],Object[]), getEJBObject(String,String,String,String,String,Class[],Object[]), getClasses(Object[]), getContext(String,String,String,String) methods by A.J.Kuhn<BR><!-- 
 * @version -->0.2, 2004/05/18, refactoring by A.J.Kuhn<BR><!--
 * @version -->0.1, 2003/09/08, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public abstract class AbstractEJBProvider implements EJBProvider {

  /**
   * EJB Home을 얻는 가장 간단한 메쏘드.<BR>
   * Application마다 Customizing 하게 이를 구현한 클래스를 만들어 사용한다.
   *
   * @param jndiName EJB Component의 JNDI Name  
   * @return         EJB Home Object
   */
  public abstract EJBHome getEJBHome(String jndiName) throws EJBProviderException;

  /**
   * EJB Home을 얻는 메쏘드
   *
   * @param ctx      EJB Component가 위치한곳의 Context  
   * @param jndiName EJB Component의 JNDI Name  
   * @return         EJB Home Object
   */
  public EJBHome getEJBHome(Context ctx, String jndiName) throws EJBProviderException {
    EJBHome ejbHome = null;

    try {
      ejbHome = (EJBHome)ctx.lookup(jndiName);
    } catch(NamingException ex) {
      throw new EJBProviderException(ex, "Could not get [" + jndiName + "]'s EJBHome.");
    }

    return ejbHome;
  }

  /**
   * EJB Home을 얻는메쏘드
   *
   * @param icf      Initial Context Factory 이름
   * @param url      EJB Component가 등록된 Context URL
   * @param id       접근 ID
   * @param pw       접근 비밀번호
   * @param jndiName EJB Component의 JNDI Name
   * @return         EJB Home Object 
   */
  public EJBHome getEJBHome(String icf, String url, String id, String pw, String jndiName) throws EJBProviderException {
    return getEJBHome(getContext(icf, url, id, pw), jndiName);
  }

  /**
   * EJB Object를 얻는 가장 간단한 메쏘드.<BR>
   * Application마다 Customizing 하게 이를 구현한 클래스를 만들어 사용한다.
   *
   * @param jndiName EJB Component의 JNDI Name  
   * @return         EJB Object
   */
  public abstract EJBObject getEJBObject(String jndiName) throws EJBProviderException;

  /**
   * EJB Object를 얻는 메쏘드<BR>
   * argument가 없는 EJBHome의 create()메쏘드로 EJBObject를 생성하여 리턴한다. 필요에 따라 Overriding할 수도 있다.
   *
   * @param ctx      EJB Component가 위치한곳의 Context  
   * @param jndiName EJB Component의 JNDI Name  
   * @return         EJB Object
   */
  public EJBObject getEJBObject(Context ctx, String jndiName) throws EJBProviderException {
    return getEJBObject(ctx, jndiName, null);
  }

  /**
   * EJB Object를 얻는 메쏘드<BR>
   * EJBHome의 create()메쏘드의 argument를 받아 생성하여 리턴한다.
   *
   * @param ctx      EJB Component가 위치한곳의 Context  
   * @param jndiName EJB Component의 JNDI Name  
   * @param args     EJBHome의 create메쏘드의 argument의 배열
   * @return         EJB Object
   */
  public EJBObject getEJBObject(Context ctx, String jndiName, Object[] args) throws EJBProviderException {
    return getEJBObject(ctx, jndiName, getClasses(args), args);
  }

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
  public EJBObject getEJBObject(Context ctx, String jndiName, Class[] argTypes, Object[] args) throws EJBProviderException {
    EJBHome ejbHome = getEJBHome(ctx, jndiName);
    EJBObject ejbObject = null;

    StringBuffer argsBuffer = new StringBuffer();
    if(argTypes != null) {
      for(int i=0; i<argTypes.length; i++) {
        argsBuffer.append(",");
        argsBuffer.append(argTypes[i].getName());
      }

      argsBuffer = new StringBuffer(argsBuffer.substring(1));
    }

    try {
      Method createMethod = ejbHome.getClass().getMethod("create", argTypes);
      ejbObject = (EJBObject)createMethod.invoke(ejbHome, args);
    } catch(NoSuchMethodException ex) {
      throw new EJBProviderException(ex, "[" + jndiName + "]'s EJBHome has no create(" + argsBuffer.toString() + ") method");
    } catch(SecurityException ex) {
      throw new EJBProviderException(ex, "Access to the information is denied [" + jndiName + "]");
    } catch(IllegalAccessException ex) {
      throw new EJBProviderException(ex, "Can't Access [" + jndiName + "]'s EJBHome's create(" + argsBuffer.toString() + ") method");
    } catch(IllegalArgumentException ex) {
      throw new EJBProviderException(ex, "When invoke [" + jndiName + "]'s EJBHome's create(" + argsBuffer.toString() + ") method, IllegalArgumentException occured");
    } catch(InvocationTargetException ex) {
      throw new EJBProviderException(ex);
    } catch(Exception ex) {
      throw new EJBProviderException(ex);
    }

    return ejbObject;
  }

  /**
   * EJB Object를 얻는 메쏘드
   * argument가 없는 EJBHome의 create()메쏘드로 EJBObject를 생성하여 리턴한다. 필요에 따라 Overriding할 수도 있다.
   *
   * @param icf      Initial Context Factory 이름
   * @param url      EJB Component가 등록된 Host URL
   * @param id       접근 ID
   * @param pw       접근 비밀번호
   * @param jndiName EJB Component의 JNDI Name
   * @return         EJB Object 
   */
  public EJBObject getEJBObject(String icf, String url, String id, String pw, String jndiName) throws EJBProviderException {
    return getEJBObject(icf, url, id, pw, jndiName, null);
  }

  /**
   * EJB Object를 얻는 메쏘드
   * EJBHome의 create()메쏘드의 argument를 받아 생성하여 리턴한다.
   *
   * @param icf      Initial Context Factory 이름
   * @param url      EJB Component가 등록된 Host URL
   * @param id       접근 ID
   * @param pw       접근 비밀번호
   * @param jndiName EJB Component의 JNDI Name
   * @param args     EJBHome의 create메쏘드의 argument의 배열
   * @return         EJB Object 
   */
  public EJBObject getEJBObject(String icf, String url, String id, String pw, String jndiName, Object[] args) throws EJBProviderException {
    return getEJBObject(icf, url, id, pw, jndiName, getClasses(args), args);
  }

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
  public EJBObject getEJBObject(String icf, String url, String id, String pw, String jndiName, Class[] argTypes, Object[] args) throws EJBProviderException {
    return getEJBObject(getContext(icf, url, id, pw), jndiName, argTypes, args);
  }

  /**
   * Object의 배열을 가지고 클래스타입의 배열을 리턴한다.
   *
   * @param objects Object배열
   * @return        objects의 각 클래스타입
   */
  private Class[] getClasses(Object[] objects) {
    Class[] classes = null;
    if(objects != null) {
      int length = objects.length;
      classes = new Class[length];

      for(int i=0; i<length; i++) {
        classes[i] = objects[i].getClass();
      }
    }

    return classes;
  }

  /**
   * 기본정보들을 가지고 Context를 얻는다
   *
   * @param icf Initial Context Factory 이름
   * @param url Context Provider URL
   * @param id  Context 접근 ID
   * @param pw  Context 접근 ID의 패스워드
   * @return  Context
   */
  private Context getContext(String icf, String url, String id, String pw) throws EJBProviderException {
    Context ctx = null;

    try {
      Properties p = new Properties();
      p.put(Context.INITIAL_CONTEXT_FACTORY, icf);
      p.put(Context.PROVIDER_URL, url);
      p.put(Context.SECURITY_PRINCIPAL, id);
      p.put(Context.SECURITY_CREDENTIALS, pw);

      ctx = new InitialContext(p);
    } catch(Exception ex) {
      throw new EJBProviderException(ex, "Could not get Context[icf:" + icf + "][url:" + url + "][id:" + id + "][pw:" + pw + "]");
    }

    return ctx;
  }
}
