package kuhna.servlet.jsp.taglib.iterator;

import javax.servlet.jsp.tagext.*;

/**
 * IterateBodyTag의 TagExtraInfo 클래스 
 *
 * @version 2.2, 2002/04/18, class name changed from 'RepeatTableTEI' to 'IterateBodyTEI' 
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
public class IterateBodyTEI extends TagExtraInfo {

  public VariableInfo[] getVariableInfo(TagData data) {
    VariableInfo[] result = null;
  
    String varName = data.getId();
    String className = data.getAttributeString("className");
    String strColumnCount = data.getAttributeString("columnCount");
    int columnCount = (strColumnCount != null) ? Integer.parseInt(strColumnCount) : 0;
    String indexVarName = data.getAttributeString("index");
    if(indexVarName == null)
      indexVarName = "index";
    String countVarName = data.getAttributeString("count");
    if(countVarName == null)
      countVarName = "count";
    
    if(className == null) {
      result = new VariableInfo[columnCount + 2];
      for(int i=0; i<columnCount; i++)
        result[i] = new VariableInfo("column"+i, "java.lang.Object", true, VariableInfo.NESTED);

      result[columnCount] = new VariableInfo(indexVarName, "java.lang.Integer", true, VariableInfo.NESTED);
      result[columnCount + 1] = new VariableInfo(countVarName, "java.lang.Integer", true, VariableInfo.NESTED);
    } else {
      result = new VariableInfo[3];
      result[0] = new VariableInfo(varName, className, true, VariableInfo.NESTED);
      result[1] = new VariableInfo(indexVarName, "java.lang.Integer", true, VariableInfo.NESTED);
      result[2] = new VariableInfo(countVarName, "java.lang.Integer", true, VariableInfo.NESTED);
    }
    
    return result;
  }
  
  public boolean isValid(TagData data) {
    return true;
  }
}

