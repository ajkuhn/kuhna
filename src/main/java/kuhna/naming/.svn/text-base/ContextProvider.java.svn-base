package kuhna.naming;

import javax.naming.*;

/**
 * Context를 얻는 메쏘드를 가진 인터페이스<BR>
 * Application마다 서로다른 조건을 갖더라도, 본 인터페이스의 메쏘드를 사용함으로써 코드의 정규화를 유지할 수 있다.
 *
 * @version 0.2, 2002/09/30, added getContext(Object) method by A.J.Kuhn<BR><!--
 * @version -->0.1, 2002/03/21, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public interface ContextProvider {

  /**
   * Context를 얻는 가장 간단한 메쏘드. Application마다 Customizing 하게 이를 구현한 클래스를 만들어 사용한다.
   *
   * @return Context
   */
  public Context getContext() throws NamingException;

  /**
   * 특정조건의 Context를 얻는다. '특정조건'은 Application마다 별도로 규정하여 구현한다.
   *
   * @param condition 조건을 구분짓는 임의의 객체
   * @return          Context
   */
  public Context getContext(Object condition) throws NamingException;

  /**
   * 기본 정보들을 가지고 Context를 얻는다.
   *
   * @param icf Context Factory 이름
   * @param url Context Provider URL
   * @param id  Context 접근 ID
   * @param pw  Context 접근 ID의 패스워드
   * @return    Context
   */
  public Context getContext(String icf, String url, String id, String pw) throws NamingException; 
}

