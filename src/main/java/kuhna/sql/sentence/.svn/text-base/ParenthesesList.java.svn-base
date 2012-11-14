package kuhna.sql.sentence;

import java.util.Collection;
import java.util.Iterator;

import kuhna.sql.SQLUtil;

/**
 * ('a', 'b', 'c'), (1, 3, 5) 형태의 문장을 만들기 위한 객체<BR>
 *
 * @version 0.1, 2011/10/21, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class ParenthesesList extends Function {
  
  public ParenthesesList(Collection c) {
    super();

    if(c == null) {
      setFunctionString("()");
    } else {
      StringBuffer buffer = new StringBuffer();
      buffer.append("(");
      Iterator itr = c.iterator();
      int count = 0;
      while(itr.hasNext()) {
        if(count != 0)
          buffer.append(", ");

        Object o = itr.next();
        if(o instanceof String) {
          buffer.append(SQLUtil.toSQLString(o.toString(), true));
        } else {
          buffer.append(o.toString());
        }

        count++;
      }
      buffer.append(")");

      setFunctionString(buffer.toString());
    }
  }
}
