package data.nodevaluestypes;

import data.exceptions.NotAssegnableException;

/**
 * This class represents a NodeValue that contains a numeric value: Double because division are needed too.
 */
public class NumValue extends NodeValue {

    private Double value = null;

    public NumValue(Double value) throws NotAssegnableException {
        this.setValue(value);
    }

    @Override
    public Double getValue() {
        return value;
    }

    /**
     *
     * @param value A Double instance not null
     * @throws NotAssegnableException If the input is not as required
     */
    @Override
    public void setValue(Object value) throws NotAssegnableException {
        if(value instanceof Double){
            Double doubleVal = (Double) value;
            if(doubleVal!=null){
                this.value = doubleVal;
            }
            else{
                throw new NotAssegnableException("It's impossible to assign a null value to a NumValue");
            }
        }
        else{
            throw new NotAssegnableException("It's impossible to assign a no-Double value to a NumValue");
        }
    }

    /**
     * This overrides the Object method: String builder is better than classical "+" operator between strings
     * @return
     */
    @Override
    public String toString(){
        StringBuilder retVal=new StringBuilder("");
        if(value!=null){
            retVal=retVal.append(value);
        }
        return retVal.toString();
    }

}
