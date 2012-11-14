package kuhna.naming;

import javax.naming.*;

/**
 * ContextProvider의 기본구현 클래스<BR>
 * 본 인스탄스를 생성한 Application의 Local Context를 제공하도록 구현되었다. 
 *
 * @version 0.2, 2002/09/30, added getContext(Object) method, modified getContext() method. by A.J.Kuhn<BR><!--
 * @version -->0.1, 2002/03/21, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class DefaultContextProvider extends AbstractContextProvider {

  /**
   * 기본 생성자
   */
  public DefaultContextProvider() {
    super();
  }

  /**
   * 본 인스탄스를 생성한 Application의 Local Context를 리턴한다.
   *
   * @return Context
   */
  public Context getContext() throws NamingException {
    return new InitialContext();
  }

  /**
   * 본 인스탄스를 생성한 Application의 Local Context를 리턴한다.<BR>
   * condition값에 상관없이 Local Context를 리턴한다.
   *
   * @param condition 조건을 구분짓는 임의의 객체. 본 클래스에서는 의미가 없다.
   * @return          Context
   */
  public Context getContext(Object condition) throws NamingException {
    return getContext();
  }
}

