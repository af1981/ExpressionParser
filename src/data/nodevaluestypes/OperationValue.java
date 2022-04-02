package data.nodevaluestypes;

import data.exceptions.NotAssegnableException;
import data.operations.Operation;

/**
 * This class represents a NodeValue that contains an Operation value
 */
public class OperationValue extends NodeValue {

    private Operation value = null;

    public OperationValue(Operation value) throws NotAssegnableException {
        this.setValue(value);
    }

    @Override
    public Operation getValue() {
        return value;
    }

    /**
     *
     * @param value It is a Operation istance not null
     * @throws NotAssegnableException If the input is not as required
     */
    @Override
    public void setValue(Object value) throws NotAssegnableException {
        if(value instanceof Operation){
            Operation operation = (Operation) value;
            if(operation!=null){
                this.value = operation;
            }
            else{
                throw new NotAssegnableException("It's impossible to assign a null value to a OperationValue");

            }
        }
        else{
            throw new NotAssegnableException("It's impossible to assign a no-Operation value to a OperationValue");
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
            retVal=retVal.append(value.name());
        }
        return retVal.toString();
    }

}
