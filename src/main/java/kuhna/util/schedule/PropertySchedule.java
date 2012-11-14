package kuhna.util.schedule;

import java.util.*;

import kuhna.lang.*;
import kuhna.util.*;

/**
 * Property값으로 구성된 하나의 스케쥴
 *
 * @version 0.2, 2005/03/10, changed TimerTask to SchedulerTask by A.J.Kuhn<BR><!--
 * @version -->0.1, 2004/07/29, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class PropertySchedule extends Schedule {

  public PropertySchedule(String key, String value) {
    super();

    setName(key);

    String type = null;
    String task = null;
    String firstTime = null;
    String delay = null;
    String period = null;
    String rightNow = null;
    try {
      StringTokenizer st = new StringTokenizer(value, ";");
      type = (String)st.nextToken();
      task = (String)st.nextToken();
      firstTime = (String)st.nextToken();
      delay = (String)st.nextToken();
      period = (String)st.nextToken();
      rightNow = (String)st.nextToken();
    } catch(Exception ex) {
      throw new IllegalArgumentException("value arg is not available(" + value + ")");
    }

    setType(type);
    setTask(task);

    if(type.equals(TYPE_SINGLE_DATE)) {
      setFirstTime(firstTime);
    } else if(type.equals(TYPE_SINGLE_DELAY)) {
      setDelay(delay);
    } else if(type.equals(TYPE_REPETITIVE_DATE)) {
      setFirstTime(firstTime);
      setPeriod(period);
    } else if(type.equals(TYPE_REPETITIVE_DELAY)) {
      setDelay(delay);
      setPeriod(period);
    } else if(type.equals(TYPE_REPETITIVE_FIXED_RATE_DATE)) {
      setFirstTime(firstTime);
      setPeriod(period);
    } else if(type.equals(TYPE_REPETITIVE_FIXED_RATE_DELAY)) {
      setDelay(delay);
      setPeriod(period);
    }

    setRightNow(rightNow);
  }
}

