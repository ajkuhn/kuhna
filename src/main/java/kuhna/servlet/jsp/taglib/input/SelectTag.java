package kuhna.servlet.jsp.taglib.input;

import java.util.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * HTML Select Tag를 유연하게 표현할수 있도록 만들어진 JSP Custom Tag
 * 
 * @version 2.1.2, 2004/05/24, changed "\n" to System.getProperty("line.separator"). 
 * @author  A.J.Kuhn
 *
 * @version 2.1.1, 2002/11/07, arrange source 
 * @author  A.J.Kuhn
 *
 * @version 2.1, 2002/08/16, modify doStartTag() method... no option select tag is available 
 * @author  A.J.Kuhn
 *
 * @version 2.0, 2002/03/08, renewal 
 * @author  A.J.Kuhn
 *
 * @version 1.1, 2001/08/31, selectedvalue bug fix, remove defaultvalue attribute
 * @author  A.J.Kuhn
 *
 * @version 1.0
 * @author  ?
 */
public class SelectTag extends TagSupport {

  private String _name;
  private Object _optionValue;
  private String _headValue;
  private String _headText;
  private int _size;
  private boolean _multiple = false;
  private String _selectedValue;
  private int _selectedIndex;
  private String _styleClass;
  private String _attribute;
  private String _javaScript;

  public void setName(String name) {
    _name = name;
  }

  public void setOptionValue(Object value) {
    _optionValue = value;
  }

  public void setHeadValue(String value) {
    _headValue = value;
  }

  public void setHeadText(String text) {
    _headText = text;
  }

  public void setSelectedValue(String value) {
    _selectedValue = value;
  }

  public void setSelectedIndex(int index) {
    _selectedIndex = index;
  }

  public void setSize(int size) {
    _size = size;
  }

  public void setMultiple(boolean flag) {
    _multiple = flag;
  }

  public void setStyleClass(String styleClass) {
    _styleClass = styleClass;
  }

  public void setAttribute(String attribute) {
    _attribute = attribute;
  }

  public void setJavaScript(String javaScript) {
    _javaScript = javaScript;
  }

  /**
   * obj의 내용을 분석, 변환하여 value의 array와 , text의 array를 만들어 리턴한다.
   *
   * @param obj String[], String, Collection Type의 Object가 와야한다.
   * @return  value의 array가 "value" key로, text의 array가 "text" key로 들어있는 Hashtable
   */
  private Hashtable makeArray(Object obj) {
    Hashtable table = new Hashtable();

    if(obj == null)
      return table;

    if(obj instanceof String[]) {
      table.put("value", obj);
      table.put("text", obj);
    } else if(obj instanceof String) {
      StringTokenizer st = new StringTokenizer((String)obj, ",");
      int count = st.countTokens();
      String[] arrayValue = new String[count];

      int i=0;
      while(st.hasMoreTokens()) {
        arrayValue[i] = st.nextToken();
        i++;
      }

      table.put("value", arrayValue);
      table.put("text", arrayValue);
    } else if(obj instanceof Collection) {
      Collection collection = (Collection)obj;
      String[] arrayValue = new String[collection.size()];
      String[] arrayText = new String[collection.size()];

      Iterator itr = collection.iterator();
      int i=0;
      while(itr.hasNext()) {
        Object element = itr.next();
        if(element instanceof String) {
          String strElement = (String)element;
          int pos = strElement.indexOf(",");
          if(pos != -1) {
            arrayValue[i] = strElement.substring(0, pos);
            arrayText[i] = strElement.substring(pos + 1, strElement.length());
          } else {
            arrayValue[i] = strElement;
            arrayText[i] = strElement;
          }
        } else {
          throw new IllegalArgumentException("obj arg is Collection Type, but one of the Collection element is not String Type");
        }

        i++;
      }

      table.put("value", arrayValue);
      table.put("text", arrayText);
    } else {
      throw new IllegalArgumentException("obj arg is not String[], or String, or Collection Type");
    }

    return table;
  }

  /**
   * Select Tag내의 Option Tag들을 만들어 리턴한다.
   *
   * @param arrayValue    option의 value에 해당하는 값들의 array
   * @param arrayText   option의 text에 해당하는 값들의 array
   * @param headValue 
   * @param headText
   * @param selectedIndex 
   * @param selectedValue 
   * @return          만들어진 Option Tag 
   */
  private String makeOption(String[] arrayValue, String[] arrayText, String headValue, String headText, int selectedIndex, String selectedValue) {
    StringBuffer sb = new StringBuffer();
    boolean selected = false;
    
    if(headValue != null && headText != null && headValue.length() > 0 && headText.length() > 0) {
      sb.append("<option value=\"");
      sb.append(headValue);
      sb.append("\"");

      if(selectedIndex == 0 && (selectedValue == null || selectedValue.length() == 0)) {
        sb.append(" selected");
        selected = true;
      }
      sb.append(">");
      sb.append(headText);
      sb.append("</option>");
      sb.append(System.getProperty("line.separator"));
    }

    if((arrayValue == null || arrayText == null) && sb.length() == 0)
      return null;

    for(int i=0; i<arrayValue.length; i++) {
      sb.append("<option value=\"");
      sb.append(arrayValue[i]);
      sb.append("\"");

      if(!selected) {
        if(selectedIndex != 0) {
          if(selectedIndex == i) {
            sb.append(" selected");
            selected = true;
          }
        } else {
          if(selectedValue != null && selectedValue.equals(arrayValue[i])) {
            sb.append(" selected");
            selected = true;
          }
        }
      }

      sb.append(">");
      sb.append(arrayText[i]);
      sb.append("</option>");
      sb.append(System.getProperty("line.separator"));
    }

    return sb.toString();
  }

  public int doStartTag() throws JspTagException {
    try {
      JspWriter out = pageContext.getOut();

      StringBuffer sb = new StringBuffer();
      sb.append("<select name=\"").append(_name).append("\"");

      if(_size > 0)
        sb.append(" size=\"").append(_size).append("\"");

      if(_multiple)
        sb.append(" multiple");

      if(_styleClass != null && _styleClass.length() > 0)
        sb.append(" class=\"").append(_styleClass).append("\"");

      if(_attribute != null && _attribute.length() > 0)
        sb.append(" ").append(_attribute);

      if(_javaScript != null && _javaScript.length() > 0)
        sb.append(" ").append(_javaScript);

      sb.append(">");
      sb.append(System.getProperty("line.separator"));

      Hashtable table = makeArray(_optionValue);
      String[] arrayValue = (String[])table.get("value");
      String[] arrayText = (String[])table.get("text");

      if((_headValue == null || _headText == null || _headValue.length() == 0 || _headText.length() == 0) && (arrayValue == null || arrayValue.length == 0)) {
        sb.append("</select>");
        out.println(sb.toString());

        return SKIP_BODY;
      }

      String strOption = makeOption(arrayValue, arrayText, _headValue, _headText, _selectedIndex, _selectedValue);

      if(strOption == null || strOption.length() == 0) {
        sb.append("</select>");
        out.println(sb.toString());

        return SKIP_BODY;
      }

      sb.append(strOption);

      sb.append("</select>");
      out.println( sb.toString() );
      
      return SKIP_BODY;
    } catch (Exception ex) {
      throw new JspTagException(ex.getMessage());
    }
  }

  public void release() {
    super.release();
  }
}

