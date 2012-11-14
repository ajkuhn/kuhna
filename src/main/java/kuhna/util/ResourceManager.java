package kuhna.util;

import java.util.*;

/**
 * 하나의 Application이 두루두루 사용할 인스탄스들을 저장하는 창고<BR>
 * 번거로운 Casting을 줄일려면 Application마다 이를 상속받은 클래스를 만들어 사용할것.
 *
 * @version 0.3, 2005/06/23, used static field for singleton intantiation by A.J.Kuhn. thanks to ledze!<BR><!--
 * @version -->0.2, 2005/02/16, changed to set(String, Object), get(String) methods non static by A.J.Kuhn<BR><!--
 * @version -->0.1, 2002/03/27, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class ResourceManager extends Object {

  /** static instance */
  protected static ResourceManager _instance = new ResourceManager();

  /** 사용자가 등록한 리소스들이 저장되는곳 */
  protected static Hashtable _table;

  /**
   * 생성자, 싱글레톤이므로 protected로 선언
   */
  protected ResourceManager() {
    _table = new Hashtable();
  }

  /**
   * static intance를 리턴한다.
   *
   * @return ResourceManager static instance
   */
  public static ResourceManager getInstance() {
    return _instance;
  }

  /**
   * 리소스(Object instance)를 얻는다.
   *
   * @param name  리소스 이름
   * @return    리소스 인스탄스
   */
  public Object get(String name) {
    if(name == null)
      return null;

    return _table.get(name);
  }

  /**
   * 리소스를 등록,갱신한다.
   *
   * @param name  리소스 이름
   * @param obj   리소스
   */
  public void set(String name, Object obj) {
    if(name == null || obj == null)
      return;

    _table.put(name, obj);
  }
}

