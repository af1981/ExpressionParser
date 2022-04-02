package data.operations;

import data.Expression;
import data.ExpressionMap;
import data.exceptions.NotCalculableException;

import java.util.List;

import static data.operations.CheckOperators.*;

/**
 * This class is design in this way to centralize all type of computation algorithms. It contains all operation like an enum and for each one:
 *
 *  - the function to check if its operator are correct or not;
 *  - the computation itself related to the specified algorithm
 *
 *  If you need to add other operation, you can change only this class
 */
public enum Operation {

    PLUS(TWO_OR_MORE_OPERATORS,
            (List<Expression> expressions, ExpressionMap map) -> {
                Number retVal = 0.0d;
                for (Expression expression : expressions) {
                    //if a child is null, we count it as zero
                    if(expression!=null){
                        retVal = retVal.doubleValue() + expression.calc(map).doubleValue();
                    }
                }
                return retVal;
            }
            /* An different implementation by stream but the exception must be handled so....
            (List<Expression> expressions, ExpressionMap map) -> (expressions.stream().map(
                    (Expression e) -> (e.calc(map).doubleValue())
            ).reduce(0D,Double::sum))*/
    ),
    MINUS(TWO_OPERATORS,
            (List<Expression> expressions, ExpressionMap map) -> {
                Number retVal = 0.0d;
                Expression firstExpression = expressions.get(0);
                Expression secondExpression = expressions.get(1);
                if(firstExpression!=null && secondExpression!=null){
                    retVal = firstExpression.calc(map).doubleValue() - secondExpression.calc(map).doubleValue();
                }
                else{
                    throw  new NotCalculableException("Found at least a null operator for a minus operation so the complete expression cannot be calculated");
                }
                return retVal;
            }
    ),
    DIVISION( TWO_OPERATORS,
            (List<Expression> expressions, ExpressionMap map) -> {
                Number retVal = 0.0d;
                Expression firstExpression = expressions.get(0);
                Expression secondExpression = expressions.get(1);
                if(firstExpression!=null && secondExpression!=null){
                    retVal = firstExpression.calc(map).doubleValue() / secondExpression.calc(map).doubleValue();
                }
                else{
                    throw  new NotCalculableException("Found at least a null operator for a division operation so the complete expression cannot be calculated");
                }
                return retVal;
            }
    ),
    MULTIPLICATION(TWO_OR_MORE_OPERATORS,
            (List<Expression> expressions, ExpressionMap map) -> {
                Number retVal = 1.0d;
                boolean atLeastOnePresent = false;
                for (Expression expression : expressions) {
                    if(expression!=null){
                        retVal = retVal.doubleValue() * expression.calc(map).doubleValue();
                        atLeastOnePresent = true;
                    }
                }
                //if all operators are null we can count the result as zero, not 1.0d
                if(!atLeastOnePresent){
                    retVal = 0.0d;
                }
                return retVal;
            }
    ),
    ABSOLUTE(ONE_OPERATOR,
            (List<Expression> expressions, ExpressionMap map) -> {
                Number retVal = 0.0d;
                Expression firstExpression = expressions.get(0);
                //we count Math.abs(null) as zero in this way
                if(firstExpression!=null){
                    retVal = Math.abs(firstExpression.calc(map).doubleValue());
                }
                return retVal;
            }
    );

    private CheckOperators checkOperators = null;
    private Computation computation = null;

    /**
     * The constructor has got the function to check the operators and the algorithm itself between them
     * @param checkOperators
     * @param computation
     */
    private Operation(CheckOperators checkOperators, Computation computation){
        this.checkOperators = checkOperators;
        this.computation = computation;
    }

    public CheckOperators getCheckOperators() {
        return checkOperators;
    }

    public Computation getComputation() {
        return computation;
    }

}
