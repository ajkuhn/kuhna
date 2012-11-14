package kuhna.util.schedule;

import java.util.*;

/**
 * 스케쥴러가 수행하는 작업
 *
 * @version 0.1, 2005/02/28, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public abstract class SchedulerTask extends TimerTask implements Cloneable {

  protected SchedulerTask() {
    super();
  }

  public Object clone() {
    try {
      return super.clone();
    } catch(CloneNotSupportedException ex) {
      return null;
    }
  }
}

