package kuhna.lang;

import java.io.*;

/**
 * Kuhna Framework의 RuntimeException location기능을 지원<BR>
 * JDK1.3 지원을 위해 내부에 Cause Exception을 갖는다.
 *
 * @version 0.6, 2005/03/21, added printStackTrace() methods by A.J.Kuhn<BR><!--
 * @version -->0.5, 2004/12/29, modified constructos by A.J.Kuhn<BR><!--
 * @version -->0.4, 2004/10/01, modified getCause() method by A.J.Kuhn<BR><!--
 * @version -->0.3, 2004/05/18, modified getCause() return type Throwable to Exception, added two constructor KuhnaRuntimeException(String,String,Exception,String), KuhnaRuntimeException(String,Exception,String) by A.J.Kuhn<BR><!--
 * @version -->0.2, 2004/04/29, added KuhnaRuntimeException(String, String) constructor by A.J.Kuhn<BR><!--
 * @version -->0.1, 2004/04/28, initial version by A.J.Kuhn
 *
 * @author  A.J.Kuhn
 */
public class KuhnaRuntimeException extends RuntimeException {

  protected Throwable _cause; 
  protected String _msgCode;  
  protected String _location; 

  public KuhnaRuntimeException(String msgCode, String location, String message) {
    super(message);
    _msgCode = msgCode;
    _location = location;
  }

  public KuhnaRuntimeException(String msgCode, String location, Throwable cause) {
    super(cause.getMessage());
    _cause = cause;
    _msgCode = msgCode;
    _location = location;
  }

  public KuhnaRuntimeException(String msgCode, String location, Throwable cause, String message) {
    super(message);
    _cause = cause;
    _msgCode = msgCode;
    _location = location;
  }

  public KuhnaRuntimeException(String msgCode, String message) {
    super(message);
    _msgCode = msgCode;
  }

  public KuhnaRuntimeException(String msgCode, Throwable cause) {
    super(cause.getMessage());
    _cause = cause;
    _msgCode = msgCode;
  }

  public KuhnaRuntimeException(String msgCode, Throwable cause, String message) {
    super(message);
    _cause = cause;
    _msgCode = msgCode;
  }

  /** 
   * 메시지코드를 리턴한다.
   *
   * @return 메세지 코드
   */
  public String getCode() {
    return _msgCode;
  }

  /** 
   * 발생위치를 리턴한다.<BR>
   * 임의로 지정한경우는 지정한값을, 지정한 값이 없는경우는 KuhnaException객체가 생성된 지점을 제공한다. 
   *
   * @return Exception 발생위치
   */
  public String getLocation() {
    if(_location == null)
      _location = getBirthClassDotMethodName(this);

    return _location;
  }

  /**
   * Throwable이 생성된 클래스.메쏘드이름을 리턴한다.<BR>
   * ThrowableUtil의 static메쏘드를 사용할경우, synchronized로 인한 문제발생 우려가있어 non static으로 재선언
   *
   * @param t 분석대상 Throwable
   * @return  클래스이름.메쏘드이름
   */
  private String getBirthClassDotMethodName(Throwable t) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    String s = null;

    synchronized(sw) {
      t.printStackTrace(pw);
      s = sw.toString();
      sw.getBuffer().setLength(0);
    }

    try {
      pw.close();
      sw.close();
    } catch(IOException ex) {
      ;
    }

    int begin = s.indexOf("at ") + 3;
    int end = s.indexOf("(", begin);

    return s.substring(begin, end);
  }

  /**
   * 본 Exception의 발생원인 분석 메시지를 프린트하고, 발생원인이 되는 Exception을 trace한다.<BR>
   * System.err 스트림에 프린트하게 된다.
   */
  public void printStackTrace() {
    printStackTrace(System.err);
  }

  /**
   * 본 Exception의 발생원인 분석 메시지를 프린트하고, 발생원인이 되는 Exception을 trace한다.
   *
   * @param ps 프린트할 스트림
   */
  public void printStackTrace(PrintStream ps) {
    synchronized(ps) {
      if(_cause != null) { 
        ps.println(getClass().getName() + ": " + getMessage()); 
        _cause.printStackTrace(ps);
      } else {
        super.printStackTrace(ps);
      }
    }
  }

  /**
   * 본 Exception의 발생원인 분석 메시지를 프린트하고, 발생원인이 되는 Exception을 trace한다.
   *
   * @param pw 프린트할 Writer
   */
  public void printStackTrace(PrintWriter pw) {
    synchronized(pw) {
      if(_cause != null) { 
        pw.println(getClass().getName() + ": " + getMessage()); 
        _cause.printStackTrace(pw);
      } else {
        super.printStackTrace(pw);
      }
    }
  }

  public Throwable getCause() {
    return _cause;
  }
}

