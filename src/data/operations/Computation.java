package data.operations;

import data.Expression;
import data.ExpressionMap;
import data.exceptions.NotCalculableException;

import java.util.List;

/**
 *  The functional interface that calculates a result Number from a list of Expression, that are children of a OperationValue
 *  Obviously the type of computation will depend on the specified OperationValue
 */
public interface Computation {

    /**
     *
     * @param expressions
     * @param map It contains the variables (es. "X") defined into the List<Expression>
     * @return
     * @throws NotCalculableException
     */
    Number compute(List<Expression> expressions, ExpressionMap map)  throws NotCalculableException;

}
