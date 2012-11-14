package kuhna.util.schedule;

import java.util.*;

import kuhna.lang.*;
import kuhna.util.*;

/**
 * Schedule을 기동하고 관리하는 클래스
 *
 * @version 0.6, 2008/06/18, added getScheduleList() method by A.J.Kuhn<BR><!--
 * @version -->0.5.1, 2005/03/16, changed rightnow work to threaded work in start() method by A.J.Kuhn<BR><!--
 * @version -->0.5, 2005/03/10, changed TimerTask to SchedulerTask by A.J.Kuhn<BR><!--
 * @version -->0.4, 2004/12/17, modified Timer instanciation by A.J.Kuhn<BR><!--
 * @version -->0.3, 2004/10/14, changed method name isStarted() to started() and modified removeSchedule() method by A.J.Kuhn<BR><!--
 * @version -->0.2, 2004/07/28, refactoring and changed package kuhna.util to kuhna.util.schedule by A.J.Kuhn<BR><!--
 * @version	-->0.1, 2002/01/30, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class Scheduler extends Object {

  /** 관리타이머 */
  private Timer _timer;

  /** 스케쥴목록 */
  private Vector _schedules;

  /** 기동중인지 여부 */
  private boolean _started;

  private boolean _daemon;

  /**
   * 생성자, 데몬모드로 생성한다.
   */
  public Scheduler() {
    this(true);
  }

  public Scheduler(boolean isDaemon) {
    super();

    _daemon = isDaemon;
    _schedules = new Vector();
    _started = false;
  }

  public List getScheduleList() {
    return new ArrayList(_schedules);	  
  }
  
  public void start() {
    if(started())
      throw new IllegalStateException("Scheduler is already started");

    _timer = new Timer(_daemon);

    Iterator itr = _schedules.iterator();
    while(itr.hasNext()) {
      Schedule schedule = (Schedule)itr.next();

      String type = schedule.getType();
      if(type.equals(Schedule.TYPE_SINGLE_DATE))
        _timer.schedule((SchedulerTask)schedule.getTask().clone(), parseFirstTime(schedule.getFirstTime()));
      else if(type.equals(Schedule.TYPE_SINGLE_DELAY))
        _timer.schedule((SchedulerTask)schedule.getTask().clone(), parseDelay(schedule.getDelay()));
      else if(type.equals(Schedule.TYPE_REPETITIVE_DATE))
        _timer.schedule((SchedulerTask)schedule.getTask().clone(), parseFirstTime(schedule.getFirstTime()), parsePeriod(schedule.getPeriod()));
      else if(type.equals(Schedule.TYPE_REPETITIVE_DELAY))
        _timer.schedule((SchedulerTask)schedule.getTask().clone(), parseDelay(schedule.getDelay()), parsePeriod(schedule.getPeriod()));
      else if(type.equals(Schedule.TYPE_REPETITIVE_FIXED_RATE_DATE))
        _timer.scheduleAtFixedRate((SchedulerTask)schedule.getTask().clone(), parseFirstTime(schedule.getFirstTime()), parsePeriod(schedule.getPeriod()));
      else if(type.equals(Schedule.TYPE_REPETITIVE_FIXED_RATE_DELAY))
        _timer.scheduleAtFixedRate((SchedulerTask)schedule.getTask().clone(), parseDelay(schedule.getDelay()), parsePeriod(schedule.getPeriod()));

      if(schedule.rightNow())
        new Thread(schedule.getTask()).start();
    }

    _started = true;
  }

  public void stop() {
    if(!started())
      throw new IllegalStateException("Scheduler is already stopped");

    if(_timer != null)
      _timer.cancel();

    _started = false;
  }

  public boolean started() {
    return _started;
  }

  public void addSchedule(Schedule schedule) {
    _schedules.add(schedule);
  }

  public void addSchedules(Collection c) {
    _schedules.addAll(c);
  }

  /**
   * 특정이름의 스케줄들을 삭제한다.<BR>
   * 동일한 이름은 모두 삭제한다.
   * 
   * @param name 삭제할 스케쥴이름
   * @return     삭제된 스케쥴 갯수
   */
  public int removeSchedule(String name) {
    Vector temp = new Vector();

    int count = 0;

    Iterator itr = _schedules.iterator();
    while(itr.hasNext()) {
      Schedule schedule = (Schedule)itr.next();

      if(name.equals(schedule.getName()))
        count++;
      else
        temp.add(schedule);
    }
    _schedules = temp;

    return count;
  }

  public Schedule getSchedule(String name) {
    Iterator itr = _schedules.iterator();
    while(itr.hasNext()) {
      Schedule schedule = (Schedule)itr.next();

      if(name.equals(schedule.getName()))
        return schedule;
    }

    return null;
  }

  public Timer getTimer() {
    return _timer;
  }

  private Date parseFirstTime(String firstTime) {
    Date value = null;

    Calendar calendar = Calendar.getInstance();

    firstTime = firstTime.toUpperCase();
    if(firstTime.endsWith("NEXT_MIN")) {
      String num = StringUtil.replace(firstTime, "NEXT_MIN", "");
      num = StringUtil.decode(num.trim(), "", "1");
      calendar.add(Calendar.MINUTE, Integer.parseInt(num));
      calendar.set(Calendar.SECOND, 0);
      calendar.set(Calendar.MILLISECOND, 0);
      value = calendar.getTime();
    } else if(firstTime.endsWith("NEXT_HOUR")) {
      String num = StringUtil.replace(firstTime, "NEXT_HOUR", "");
      num = StringUtil.decode(num.trim(), "", "1");
      calendar.add(Calendar.HOUR, Integer.parseInt(num));
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      calendar.set(Calendar.MILLISECOND, 0);
      value = calendar.getTime();
    } else if(firstTime.endsWith("NEXT_DAY")) {
      String num = StringUtil.replace(firstTime, "NEXT_DAY", "");
      num = StringUtil.decode(num.trim(), "", "1");
      calendar.add(Calendar.DATE, Integer.parseInt(num));
      calendar.set(Calendar.AM_PM, Calendar.AM);
      calendar.set(Calendar.HOUR, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      calendar.set(Calendar.MILLISECOND, 0);
      value = calendar.getTime();
    } else if(firstTime.endsWith("NEXT_MONTH")) {
      String num = StringUtil.replace(firstTime, "NEXT_MONTH", "");
      num = StringUtil.decode(num.trim(), "", "1");
      calendar.add(Calendar.MONTH, Integer.parseInt(num));
      calendar.set(Calendar.DATE, 1);
      calendar.set(Calendar.AM_PM, Calendar.AM);
      calendar.set(Calendar.HOUR, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      calendar.set(Calendar.MILLISECOND, 0);
      value = calendar.getTime();
    } else if(firstTime.endsWith("NEXT_YEAR")) {
      String num = StringUtil.replace(firstTime, "NEXT_YEAR", "");
      num = StringUtil.decode(num.trim(), "", "1");
      calendar.add(Calendar.YEAR, Integer.parseInt(num));
      calendar.set(Calendar.MONTH, 0);
      calendar.set(Calendar.DATE, 1);
      calendar.set(Calendar.AM_PM, Calendar.AM);
      calendar.set(Calendar.HOUR, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      calendar.set(Calendar.MILLISECOND, 0);
      value = calendar.getTime();
    } else {
      value = DateUtil.getUtilDate(firstTime); // 시작할 절대시간
    }

    return value;
  }

  private long parseDelay(String delay) {
    long value = 0;

    delay = delay.toUpperCase();
    if(delay.endsWith("SECOND")) {
      String num = StringUtil.replace(delay, "SECOND", "");
      num = StringUtil.decode(num.trim(), "", "1");
      value = Long.parseLong(num) * DateUtil.MILLIS_PER_SECOND;
    } else if(delay.endsWith("MINUTE")) {
      String num = StringUtil.replace(delay, "MINUTE", "");
      num = StringUtil.decode(num.trim(), "", "1");
      value = Long.parseLong(num) * DateUtil.MILLIS_PER_MINUTE;
    } else if(delay.endsWith("HOUR")) {
      String num = StringUtil.replace(delay, "HOUR", "");
      num = StringUtil.decode(num.trim(), "", "1");
      value = Long.parseLong(num) * DateUtil.MILLIS_PER_HOUR;
    } else if(delay.endsWith("DAY")) {
      String num = StringUtil.replace(delay, "DAY", "");
      num = StringUtil.decode(num.trim(), "", "1");
      value = Long.parseLong(num) * DateUtil.MILLIS_PER_DAY;
    } else if(delay.endsWith("WEEK")) {
      String num = StringUtil.replace(delay, "WEEK", "");
      num = StringUtil.decode(num.trim(), "", "1");
      value = Long.parseLong(num) * DateUtil.MILLIS_PER_WEEK;
    } else {
      value = Long.parseLong(delay);
    }

    return value;
  }

  private long parsePeriod(String period) {
    return parseDelay(period);
  }
}

