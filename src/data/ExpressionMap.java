package data;

import data.utils.Utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * This class represent a map of Expression where the VariableValue are specified
 *
 */
public class ExpressionMap {

    private Map<String,Expression> expressionMap = new HashMap<String,Expression>();

    /**
     * It returns the Expression related to the input as requested by Oracle
     * @param variableName It must be not null and not empty otherwise return null
     * @return
     */
    public Expression getExpression(String variableName){
        Expression retVal = null;
        if(!Utils.isEmpty(variableName)){
            retVal=expressionMap.get(variableName);
        }
        return retVal;
    }

    /**
     * This function put a new (or substitutes an old) Expression for a specified variable
     * @param variableName It must be not null and not empty otherwise return false
     * @param expression It return true if the insert is successfully
     * @return
     */
    public boolean putExpression(String variableName, Expression expression){
        boolean retVal = false;
        if(!Utils.isEmpty(variableName)){
            expressionMap.put(variableName.toUpperCase(Locale.ROOT).trim(), expression);
            retVal= true;
        }
        return retVal;
    }

}
