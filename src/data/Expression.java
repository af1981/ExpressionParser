package data;

import data.exceptions.NotAssegnableException;
import data.exceptions.NotCalculableException;
import data.nodevaluestypes.NodeValue;
import data.nodevaluestypes.NumValue;
import data.nodevaluestypes.OperationValue;
import data.nodevaluestypes.VariableValue;
import data.utils.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represent an Expression that has a NodeValue and eventually some List<Expression> as children.
 * In particular, we inserted some line of codes to avoid that someone is modifying the instance from outside. The Expression class, as a data structure, must have
 * other "own" methods implementations like "addFirstChild", "moveChild" and so on but we missed here because too long.
 */
public class Expression {

    private List<Expression> children = null;

    private NodeValue nodeValue = null;

    public Expression(NodeValue nodeValue) throws NotAssegnableException {
        if(nodeValue!=null){
            this.nodeValue = nodeValue;
        }
        else{
            throw new NotAssegnableException("It's impossible to assign a null for NodeValue");
        }
    }

    /**
     * The contructor to specify the children
     * @param nodeValue
     * @param children It cannot be null and the list must contain at least one not-null element otherwise a NotAssegnableException will be thrown
     * @throws NotAssegnableException
     */
    public Expression(NodeValue nodeValue, List<Expression> children) throws NotAssegnableException {
        this.nodeValue = nodeValue;
        if(Utils.hasAtLeastOneElement(children)){
            //we create a new list to avoid the somebody changes the original pointer from outside
            this.children=new ArrayList<Expression>(children);
        }
        else{
            throw new NotAssegnableException("It's impossible to assign a null list of children");
        }
    }

    public List<Expression> getChildren() {
        //we create a new list to avoid the somebody changes the original pointer from outside so they cannot change this instance of Expression
        return new ArrayList(children);
    }

    public NodeValue getNodeValue() {
        return nodeValue;
    }

    /**
     * This signature is required by Oracle. It calculates the result of the current instance of Expression, specified with parameter the ExpressionMap
     * of the VariableValue
     * @param map All variables specified in the current instance od Expression
     * @return It returns null in the case the nodeValue is null
     * @throws NotCalculableException It returns when the expression is malformed or some item in map is missing
     */
    public Number calc(ExpressionMap map) throws NotCalculableException {
        Number retVal = null;
        if(nodeValue!=null){
            if(nodeValue instanceof NumValue){
                //if the current node contains a number, the result is the number itself: this is the base of the recursion
                retVal = ((NumValue)nodeValue).getValue();
            }
            else if(nodeValue instanceof VariableValue){
                //if the current node contains a variable...
                String strNodeValue = ((VariableValue)nodeValue).getValue();
                //...we retrieve it from the ExpressionMap and...
                Expression retrievedValue = map.getExpression(strNodeValue);
                if(retrievedValue!=null){
                    //...the final result is the evaluation of the new Expression retrieved from the ExpressionMap
                    retVal = retrievedValue.calc(map);
                }
                else{
                    throw new NotCalculableException(new StringBuilder("There is any value for the variable ").append(strNodeValue).append(" into the ExpressionMap so the expression cannot be calculated").toString());
                }
            }
            else if(nodeValue instanceof OperationValue){
                //if the current node contains an operation it must contain some children so...
                OperationValue operationValue= (OperationValue)nodeValue;
                if(operationValue!=null){
                    if(!Utils.isEmpty(children)){
                        //...we count if the number of children is correct for the specified operation and...
                        if(operationValue.getValue().getCheckOperators().check(children)){
                            //...the final result is the applying the right function 'Computation' onto all children
                            retVal = operationValue.getValue().getComputation().compute(children, map);
                        }
                        else{
                            throw new NotCalculableException(new StringBuilder("The expression with the operators ").append(children).append(" for the OperationValue ").append(operationValue).append(" is invalid so the expression cannot be calculated").toString());
                        }
                    }
                    else{
                        throw new NotCalculableException(new StringBuilder("There is any operator for the OperationValue ").append(operationValue).append(" so the expression cannot be calculated").toString());
                    }
                }
                else{
                    throw new NotCalculableException("There is any value for a OperationValue so the expression cannot be calculated");
                }
            }
        }
        return retVal;
    }

    /**
     * This signature is required by Oracle. It calculates all variables in the current Expression
     * @return
     * @throws NotCalculableException It raised when the Expression is not well-formed
     */
    public Set<String> getReferencedSymbols() throws NotCalculableException {
        Set<String> retVal = new HashSet<String>();
        if(nodeValue!=null){
            /* if(nodeValue instanceof NumValue){
                //if the current node contains a number, do nothing: this is the base of the recursion
            }*/
            if(nodeValue instanceof VariableValue){
                //if the current node contains a variable...
                String strNodeValue = ((VariableValue)nodeValue).getValue();
                //...we add it at the return value (base of the recursion)
                retVal.add(strNodeValue);
            }
            else if(nodeValue instanceof OperationValue){
                //if the current node contains an operation it must contain some children so...
                OperationValue operationValue= (OperationValue)nodeValue;
                if(operationValue!=null){
                    if(!Utils.isEmpty(children)){
                        //...we count if the number of children is correct for the specified operation and...
                        if(operationValue.getValue().getCheckOperators().check(children)){
                            //...we cycle all children collecting all referenced symbols
                            for(Expression child:children){
                                if(child!=null){
                                    retVal.addAll(child.getReferencedSymbols());
                                }
                            }
                        }
                        else{
                            throw new NotCalculableException(new StringBuilder("The expression with the operators ").append(children).append(" for the OperationValue ").append(operationValue).append(" is invalid so the expression is not well-formed").toString());
                        }
                    }
                    else{
                        throw new NotCalculableException(new StringBuilder("There is any operator for the OperationValue ").append(operationValue).append(" so the expression is not well-formed").toString());
                    }
                }
                else{
                    throw new NotCalculableException("There is any value for a OperationValue so the expression is not well-formed");
                }
            }
        }
        return retVal;
    }

    /**
     * This overrides the Object method: String builder is better than classical "+" operator between strings
     * @return
     */
    @Override
    public String toString(){
        StringBuilder retVal=new StringBuilder("");
        retVal.append("Expression[\nnodeValue=").append(nodeValue).append("\nchilds=").append(children).append("\n]");
        return retVal.toString();
    }

}

