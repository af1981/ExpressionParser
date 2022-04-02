package oracletest;

import data.Expression;
import data.ExpressionMap;
import data.exceptions.NotAssegnableException;
import data.exceptions.NotCalculableException;
import data.nodevaluestypes.NumValue;
import data.nodevaluestypes.OperationValue;
import data.nodevaluestypes.VariableValue;
import data.operations.Operation;
import data.utils.Utils;

import java.util.List;

/***
 * This is a simple Java test class to test that the data structure work fine, as indicated by Oracle in the exam
 */
public class OracleTest {

    /*  THE ESPRESSION IS:

     -
   /   \
  3     * (48)
      /   \
     + (8)  + (6)
    / \    /|\
   5   x  x y 1

   WITH VARIABLES x as (1 + y) and y = abs(1 - 3) SO THE RESULT IS -45
     */

    /**
     * Simple function to build input related to the expression above requested by Oracle
     * @param expressionMap
     * @return
     * @throws NotAssegnableException
     * @throws NotCalculableException
     */
    private static Expression buildExamExpression(ExpressionMap expressionMap) throws NotAssegnableException, NotCalculableException {
        Expression retVal = null;
        if(expressionMap!=null){
            String x = "X";
            String y = "Y";
            Expression e1 = new Expression(new OperationValue(Operation.PLUS), List.of(new Expression(new NumValue(5.0d)),new Expression(new VariableValue(x))));
            Expression e2 = new Expression(new OperationValue(Operation.PLUS), List.of(new Expression(new VariableValue(x)), new Expression(new VariableValue(y)), new Expression(new NumValue(1.0d))));
            Expression e3 = new Expression(new OperationValue(Operation.MULTIPLICATION), List.of(e1,e2));
            retVal = new Expression(new OperationValue(Operation.MINUS), List.of(new Expression(new NumValue(3.0d)),e3));

            expressionMap.putExpression(x, new Expression(new OperationValue(Operation.PLUS), List.of(new Expression(new NumValue(1.0d)), new Expression(new VariableValue(y)))));
            //expressionMap.putExpression(y, new Expression(new OperationValue(Operation.PLUS), List.of(new Expression(new NumValue(1.0d)), new Expression(new VariableValue(x)))));

            Expression e4 = new Expression(new OperationValue(Operation.MINUS), List.of(new Expression(new NumValue(1.0d)),new Expression(new NumValue(3.0d))));
            expressionMap.putExpression(y, new Expression(new OperationValue(Operation.ABSOLUTE), List.of(e4)));
        }
        else{
            throw new NotCalculableException("The expressionMap is empty: please, you should create it empty");
        }
        return retVal;
    }

    /**
     * Run it so you can print the results of the Oracle expression, that is X,Y and -45.0
     *
     * Oracle Questions:
     * 1. Is it possible that in some cases calculation is not finite?
     * Yes, but obviously it went into a java.lang.StackOverflowError. It happens when ExpressionMap contains some variables that creates a cycle.
     * For example: X=Y-1 AND Y=X+1.
     * Another example: X=Y+1, Y=Z+1, Z=X-2.
     * Try to comment raw 54 and de-comment raw 51
     *
     * 2. If so, how would you adjust your code to avoid such a situation?
     * We should create a function boolean checkCycles(ExpressionMap expressionMap) to analyze the expressions here to detect cycles inside. It could be called before
     * to calculate the final result of the expression
     *
     * @param args
     */
    public static void main(String... args) {
        try{
            ExpressionMap expressionMap = new ExpressionMap();
            Expression buildExamExpression = buildExamExpression(expressionMap);
            Utils.log(new StringBuilder("All variables in the expression: ").append(buildExamExpression.getReferencedSymbols()));
            Utils.log(new StringBuilder("Expression result: ").append(buildExamExpression.calc(expressionMap)));
        }
        catch(NotAssegnableException| NotCalculableException e){
            Utils.log(new StringBuilder("There is an proper error of the application: ").append(e.getMessage()).toString());
        }
        catch(Exception e){
            Utils.log(e.getMessage());
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }

}
