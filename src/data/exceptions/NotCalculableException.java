package data.exceptions;

/**
 * This class notify a situation when an operation cannot be performed
 */
public class NotCalculableException extends Exception{

    public NotCalculableException(){
        super();
    }
    public NotCalculableException(String exception){
        super(exception);
    }

}
