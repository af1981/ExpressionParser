package data.nodevaluestypes;

import data.exceptions.NotAssegnableException;
import data.utils.Utils;

import java.util.Locale;

/**
 * This class represents a NodeValue that contains an variable value identified by a string. Its cases and the left/right spaces do not change the value of the
 * variable. E.g. "x" or "X" or " X " are the same variable
 */
public class VariableValue extends NodeValue {

    private String value = null;

    public VariableValue(String value) throws NotAssegnableException {
        this.setValue(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value It's a String instance not empty (null or only whites spaces)
     * @throws NotAssegnableException If the input is not as required
     */
    @Override
    public void setValue(Object value) throws NotAssegnableException {
        if(value instanceof String){
            String string = (String)value;
            if(!Utils.isEmpty(string)){
                this.value = (string).toUpperCase(Locale.ROOT).trim();
            }
            else{
                throw new NotAssegnableException("It's impossible to assign an empty variable");
            }
        }
    }

    /**
     * This overrides the Object method: String builder is better than classical "+" operator between strings
     * @return
     */
    @Override
    public String toString(){
        StringBuilder retVal=new StringBuilder("");
        if(!Utils.isEmpty(value)){
            retVal=retVal.append(value);
        }
        return retVal.toString();
    }

}
