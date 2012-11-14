package kuhna.ejb;

import kuhna.lang.KuhnaException;

/**
 * EJBProvider에서 발생하는 Exception.<BR> 
 * JDK1.4부터는 Exception이 기본적으로 cause(Throwable)을 가질수 있으나, 1.3이하 버젼을 지원하기 위해 cause Throwable을 갖는다. 
 *
 * @version 0.4, 2005/03/21, changed super class Exception to KuhnaException by A.J.Kuhn<BR><!--
 * @version -->0.3, 2004/10/01, changed _cause attribute class Exception to Throwable, for JDK1.4 compatible by A.J.Kuhn<BR><!--
 * @version -->0.2, 2004/05/18, refactoring by A.J.Kuhn<BR><!--
 * @version -->0.1, 2003/09/08, initial version by A.J.Kuhn
 *
 * @author <a href="http://ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class EJBProviderException extends KuhnaException {

  /**
   * 생성자.<BR>
   * 발생원인이 되는 Exception을 argument로 받는다.
   *
   * @param cause 발생원인 Exception
   */
  public EJBProviderException(Throwable cause) {
    super(null, cause); 
  }

  /**
   * 생성자.<BR>
   * 발생원인을 분석한 메시지를 argument로 받는다.
   *
   * @param msg 발생원인 분석 메시지
   */
  public EJBProviderException(String msg) {
    super(null, msg);  
  }

  /**
   * 생성자.<BR>
   * 발생원인이 되는 Exception과, 발생원인을 분석한 메시지를 argument로 받는다.
   *
   * @param cause 발생원인 Exception
   * @param msg    발생원인 분석 메시지
   */
  public EJBProviderException(Throwable cause, String msg) {
    super(null, cause, msg);  
  }
}
