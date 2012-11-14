package kuhna.util;

import java.io.*;
import java.util.*;

/**
 * java.util.Properties를 확장한 클래스
 *            
 * @version 0.2, 2004/02/12, add getHashtable() method
 * @author  A.J.Kuhn
 *            
 * @version 0.1, 2003/10/23
 * @author  A.J.Kuhn
 */
public class KuhnaProperties extends Properties {

  public KuhnaProperties() {
    super();
  }

  public KuhnaProperties(Properties defaults) {
    super(defaults);
  }

  public KuhnaProperties(String filename) throws IOException, FileNotFoundException {
    super();
    load(filename);
  }

  public synchronized void load(String filename) throws IOException, FileNotFoundException {
    FileInputStream fis = null;

    fis = new FileInputStream(filename);
    load(fis);

    if(fis != null)
      fis.close();
  }

  public synchronized void store(String filename, String header) throws IOException, FileNotFoundException {
    FileOutputStream fos = null;

    fos = new FileOutputStream(filename);
    super.store(fos, header);

    if(fos != null)
      fos.close();
  }

  public String getString(String key) {
    return getProperty(key);
  }

  public String getString(String key, String defaultValue) {
    return getProperty(key, defaultValue);
  }

  /**
   * 특정 property의 값이 "," delimeter로 되어진 경우 이것을 parsing하여 Hashtable로 만들어 리턴한다.
   */
  public Hashtable getHashtable(String key) {
    Hashtable table = new Hashtable();
    String value = getString(key);
    
    if(value == null) {
      return table;
    } else {
      StringTokenizer st = new StringTokenizer(value, ",");
      int cnt = st.countTokens();

    if(cnt == 1) {
        String token = st.nextToken().trim();
        if(token.indexOf("=") == -1) {
          table.put(key, token);
        } else {
          String k = token.substring(0, token.indexOf("="));
          String v = token.substring(token.indexOf("=") + 1, token.length());
          table.put(k, v);
        }
      } else {
        while(st.hasMoreTokens()) {
          String token = st.nextToken().trim();
          String k = token.substring(0, token.indexOf("="));
          String v = token.substring(token.indexOf("=") + 1, token.length());
          table.put(k, v);
        }
      }
    }

    return table;
  }

  // 계속확장해야함.
}
