/*
 * this exception is thrown when a withdrawl or return which has been requested is impossible
 */
package Exceptions;

/**
 *
 * @author Celia
 */
public class InvalidAction extends Exception {
    
    public InvalidAction(String action, String reason){
        super(String.format("%s failed. %s", action, reason));
    }
    
}
