package data.operations;

import data.Expression;
import data.utils.Utils;

import java.util.List;

/**
 *  The functional interface to check a list of Expression, that are children of a OperationValue
 *  It contains some basis implementations
 */
public interface CheckOperators {

    CheckOperators ONE_OPERATOR = (a) ->(!Utils.isEmpty(a) && a.size()==1);
    CheckOperators TWO_OPERATORS = (a) -> (!Utils.isEmpty(a) && a.size()==2);
    CheckOperators TWO_OR_MORE_OPERATORS = (a) -> (!Utils.isEmpty(a) && a.size()>1);

    boolean check(List<Expression> expressions);

}
