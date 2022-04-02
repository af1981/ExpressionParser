package data.exceptions;

/**
 * This class notify a situation when an assignment is not correct
 */
public class NotAssegnableException extends Exception{

    public NotAssegnableException(){
        super();
    }

    public NotAssegnableException(String exception){
        super(exception);
    }

}
