package kuhna.servlet.jsp.taglib.iterator;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;

/**
 * HTML Table내에서 body loop를 Java code 없이 표현할수 있도록 만들어진 JSP Custom Tag
 *
 * @version 2.3, 2002/04/18, class name changed from 'RepeatTableTag' to 'IterateBodyTag', member variable name changed. 
 * @author  A.J.Kuhn
 *
 * @version 2.2, 2002/04/03, index, count variable bug fix 
 * @author  A.J.Kuhn
 *
 * @version 2.1, 2002/03/11, index, count variable is available in Body 
 * @author  A.J.Kuhn
 *
 * @version 2.0, 2002/03/08, renewal 
 * @author  A.J.Kuhn
 *
 * @version 1.0
 * @author  ?
 */
public class IterateBodyTag extends BodyTagSupport {
  private Object _value = null;
  private String _id = null;
  private String _className = null;
  private int _columnCount = 0;
  private String _indexVarName = null;
  private String _countVarName = null;

  private int _index = 0;
  private Iterator _itr = null;

  public void setValue(Object value) {
    _value = value;
  }

  public void setId(String id) {
    _id = id;
  }

  public void setClassName(String name) {
    _className = name;
  }

  public void setColumnCount(int count) {
    _columnCount = count;
  }

  public void setIndex(String name) {
    _indexVarName = name;
    if(_indexVarName == null) {
      _indexVarName = "index";
    }
  }

  public void setCount(String name) {
    _countVarName = name;
    if(_countVarName == null) {
      _countVarName = "count";
    }
  }

  public int doStartTag() throws JspTagException {
    _index = 0;

    if(_value == null)
      return SKIP_BODY;

    if(_value instanceof Collection)
      _itr = ((Collection)_value).iterator();
    else
      return SKIP_BODY;

    if(_indexVarName == null)
      _indexVarName = "index";
    if(_countVarName == null)
      _countVarName = "count";

    if(_itr.hasNext()) {
      pageContext.setAttribute(_indexVarName, new Integer(_index));
      pageContext.setAttribute(_countVarName, new Integer(_index + 1));
      setTableColumnElement();
      return EVAL_BODY_AGAIN;
    }

    return SKIP_BODY;
  }

  public int doAfterBody() throws JspTagException {
    try {
      getBodyContent().writeOut(getPreviousOut());
      getBodyContent().clearBody();

      if(_itr.hasNext()) {
        _index++;
        pageContext.setAttribute(_indexVarName, new Integer(_index));
        pageContext.setAttribute(_countVarName, new Integer(_index + 1));
        setTableColumnElement();
        return EVAL_BODY_AGAIN;
      } else {
        if(bodyContent != null)
          bodyContent.writeOut(bodyContent.getEnclosingWriter());
      }
    } catch(IOException ex) {
      throw new JspTagException(ex.getMessage());
    }

    return SKIP_BODY;
  }

  /**
   * value값의 Type에 따라 적절한 변수와 값을 설정한다.
   */
  private void setTableColumnElement() throws JspTagException {
    try {
      Object element = _itr.next();
      if(element instanceof String) {
        int cnt = 1;
        StringTokenizer st = new StringTokenizer((String)element, ",");
        while(st.hasMoreTokens()) {
          if(cnt <= _columnCount)
            pageContext.setAttribute("column" + cnt++, st.nextToken());
        }
      } else if(element instanceof Collection) {
        Iterator recordItr = ((Collection)element).iterator();

        int cnt = 1;
        while(recordItr.hasNext()) {
          if(cnt <= _columnCount)
            pageContext.setAttribute("column" + cnt++, recordItr.next().toString());
        }
      } else {
        pageContext.setAttribute(_id, element);
      }
    } catch(Exception ex) {
      throw new JspTagException(ex.getMessage());
    }
  }

  public void release() {
    super.release();
  }
}

