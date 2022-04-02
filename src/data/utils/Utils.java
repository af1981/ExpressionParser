package data.utils;

import java.util.List;

/**
 *
 * This class is an utils class to maximize the reuse of the code and to centralize some functions (if there is a need to change, there's a only point to modify, like logs)
 */
public class Utils {

    /**
     * Return true when the list in input is null or when it's empty
     * @param list
     * @return
     */
    public static boolean isEmpty(List list){
        boolean retVal = true;
        if(list!=null){
            retVal=list.isEmpty();
        }
        return retVal;
    }

    /**
     * Return true when the list in input is not null and has got at least one element different than null
     * @param list
     * @return
     */
    public static boolean hasAtLeastOneElement(List list){
        if(!isEmpty(list)){
            for(Object item:list){
                if(item!=null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Return true when the string in input is null or composed of spaces
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        boolean retVal = true;
        if(str!=null){
            retVal=str.trim().isEmpty();
        }
        return retVal;
    }

    /**
     * Centralized function to log
     * @param log   Object to log
     */
    public static void log(Object log){
        System.out.println(log);
    }

}
