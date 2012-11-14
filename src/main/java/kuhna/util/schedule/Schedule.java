package kuhna.util.schedule;

import java.util.*;

import kuhna.lang.*;
import kuhna.util.*;

/**
 * 스케쥴정보 객체
 *
 * @version 0.3, 2008/04/02, added _meta member variable and setter, getter by A.J.Kuhn<BR><!--
 * @version 0.2, 2005/03/10, changed TimerTask to SchedulerTask by A.J.Kuhn<BR><!--
 * @version -->0.1, 2004/07/28, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class Schedule extends Object {

  public static String TYPE_SINGLE_DATE = "SINGLE_DATE";
  public static String TYPE_SINGLE_DELAY = "SINGLE_DELAY";
  public static String TYPE_REPETITIVE_DATE = "REPETITIVE_DATE";
  public static String TYPE_REPETITIVE_DELAY = "REPETITIVE_DELAY";
  public static String TYPE_REPETITIVE_FIXED_RATE_DATE = "REPETITIVE_FIXED_RATE_DATE";
  public static String TYPE_REPETITIVE_FIXED_RATE_DELAY = "REPETITIVE_FIXED_RATE_DELAY";

  /** 스케쥴 이름 */
  private String _name;

  /** 유형 */
  private String _type;

  private SchedulerTask _task;

  private String _firstTime;

  private String _delay;

  private String _period;

  private boolean _rightNow = false;
  
  private Object _meta;

  protected Schedule() {
    super();
  }

  public Schedule(String name, String type, String task, String firstTime, String delay, String period, String rightNow) {
    this();

    setName(name);
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

    _rightNow = new Boolean(rightNow).booleanValue();
  }

  public Schedule(String name, String type, SchedulerTask task, Date firstTime, long delay, long period, boolean rightNow) {
    this();

    setName(name);
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

    _rightNow = rightNow;
  }


  public String getName() {
    return _name;
  }

  public String getType() {
    return _type;
  }

  public SchedulerTask getTask() {
    return _task;
  }

  public String getFirstTime() {
    return _firstTime;
  }

  public String getDelay() {
    return _delay;
  }

  public String getPeriod() {
    return _period;
  }

  public boolean rightNow() {
    return _rightNow;
  }
  
  public void setMeta(Object meta) {
    _meta = meta;  
  }
  
  public Object getMeta() {
    return _meta;
  }

  protected void setName(String name) {
    if(name == null)
      throw new IllegalArgumentException("name arg is null");

    _name = name;
  }

  protected void setType(String type) {
    if(type == null)
      throw new IllegalArgumentException("type arg is null");

    if(type.equals(TYPE_SINGLE_DATE) || 
       type.equals(TYPE_SINGLE_DELAY) ||
       type.equals(TYPE_REPETITIVE_DATE) ||
       type.equals(TYPE_REPETITIVE_DELAY) ||
       type.equals(TYPE_REPETITIVE_FIXED_RATE_DATE) ||
       type.equals(TYPE_REPETITIVE_FIXED_RATE_DELAY))
      _type = type;
    else
      throw new IllegalArgumentException("type arg value is not available(" + type + ")");
  }

  protected void setTask(String task) {
    if(task == null)
      throw new IllegalArgumentException("task arg is null");

    SchedulerTask temp = null;

    try {
      temp = (SchedulerTask)Class.forName(task).newInstance();
    } catch(ClassCastException ex) {
      throw new IllegalArgumentException("[" + task + "] is not SchedulerTask");
    } catch(Exception ex) {
      throw new IllegalArgumentException("[" + task + "] can not be instantiated");
    }

    _task = temp;
  }

  protected void setTask(SchedulerTask task) {
    if(task == null)
      throw new IllegalArgumentException("task arg is null");

    _task = task;
  }

  protected void setFirstTime(String firstTime) {
    if(firstTime == null)
      throw new IllegalArgumentException("firstTime arg is null");

    String temp = firstTime.toUpperCase();
    String num = null;
    if(temp.endsWith("NEXT_MIN")) {
      num = temp.substring(0, temp.length() - 8);
    } else if(temp.endsWith("NEXT_HOUR")) {
      num = temp.substring(0, temp.length() - 9);
    } else if(temp.endsWith("NEXT_DAY")) {
      num = temp.substring(0, temp.length() - 8);
    } else if(temp.endsWith("NEXT_MONTH")) {
      num = temp.substring(0, temp.length() - 10);
    } else if(temp.endsWith("NEXT_YEAR")) {
      num = temp.substring(0, temp.length() - 9);
    }

    if(num != null) {
      try {
        num = StringUtil.decode(num.trim(), "", "1");
        Integer.parseInt(num);
      } catch(Exception ex) {
        throw new IllegalArgumentException("firstTime arg value is not available(" + firstTime + ")");
      }
    } else {
      try {
        DateUtil.getUtilDate(temp);
      } catch(Exception ex) {
        throw new IllegalArgumentException("firstTime arg value is not available(" + firstTime + ")");
      }
    }

    _firstTime = firstTime;
  }

  protected void setFirstTime(Date firstTime) {
    if(firstTime == null)
      throw new IllegalArgumentException("firstTime arg is null");

    _firstTime = DateUtil.getString(firstTime, "yyyyMMddHHmmss");
  }

  protected void setDelay(String delay) {
    if(delay == null)
      throw new IllegalArgumentException("delay arg is null");

    String temp = delay.toUpperCase();
    String num = null;
    if(temp.endsWith("SECOND")) {
      num = temp.substring(0, temp.length() - 6);
    } else if(temp.endsWith("MINUTE")) {
      num = temp.substring(0, temp.length() - 6);
    } else if(temp.endsWith("HOUR")) {
      num = temp.substring(0, temp.length() - 4);
    } else if(temp.endsWith("DAY")) {
      num = temp.substring(0, temp.length() - 3);
    } else if(temp.endsWith("WEEK")) {
      num = temp.substring(0, temp.length() - 4);
    }

    if(num != null) {
      try {
        num = StringUtil.decode(num.trim(), "", "1");
        Integer.parseInt(num);
      } catch(Exception ex) {
        throw new IllegalArgumentException("delay arg value is not available(" + delay + ")");
      }
    } else {
      try {
        Long.parseLong(temp);
      } catch(Exception ex) {
        throw new IllegalArgumentException("delay arg value is not available(" + delay + ")");
      }
    }

    _delay = delay;
  }

  protected void setDelay(long delay) {
    _delay = String.valueOf(delay);
  }

  protected void setPeriod(String period) {
    if(period == null)
      throw new IllegalArgumentException("period arg is null");

    String temp = period.toUpperCase();
    String num = null;
    if(temp.endsWith("SECOND")) {
      num = temp.substring(0, temp.length() - 6);
    } else if(temp.endsWith("MINUTE")) {
      num = temp.substring(0, temp.length() - 6);
    } else if(temp.endsWith("HOUR")) {
      num = temp.substring(0, temp.length() - 4);
    } else if(temp.endsWith("DAY")) {
      num = temp.substring(0, temp.length() - 3);
    } else if(temp.endsWith("WEEK")) {
      num = temp.substring(0, temp.length() - 4);
    }

    if(num != null) {
      try {
        num = StringUtil.decode(num.trim(), "", "1");
        Integer.parseInt(num);
      } catch(Exception ex) {
        throw new IllegalArgumentException("period arg value is not available(" + period + ")");
      }
    } else {
      try {
        Long.parseLong(temp);
      } catch(Exception ex) {
        throw new IllegalArgumentException("period arg value is not available(" + period + ")");
      }
    }

    _period = period;
  }

  protected void setPeriod(long period) {
    _period = String.valueOf(period);
  }

  protected void setRightNow(String rightNow) {
    _rightNow = new Boolean(rightNow).booleanValue();
  }
}

