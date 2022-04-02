package data.nodevaluestypes;

import data.exceptions.NotAssegnableException;

/**
 * This class ideally represent a value of the node in the tree, it can be a value (e.g. 0d) or an operator (e.g. Operation.PLUS) or a variable (e.g. VariableValue as "X")
 */
public abstract class NodeValue {

    abstract public Object getValue();

    abstract public void setValue(Object value) throws NotAssegnableException;

}
