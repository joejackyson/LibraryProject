/*
    This is the superclass for all classes which contain data to be stored:
    - student
    - book
    sets and returns associated values
    implements Withdrawable
 */
package dataStorage;

import java.util.Hashtable;


/**
 *
 * @author cahough
 */
public class dataStorage implements Withdrawable{
    
    // Type: student or book
    private String type;

    // public constructor: specify subclass type
    public dataStorage(String type){
        type = type;
    }
        
    // Set String type = subclass name
    public void setType(String type){
        type = type;
    }
    
    // Return name of subclass
    public String getType(){
        return type;
    }	
    
    // return all keys and values formatted as array of strings
    public String[] format(){
        Hashtable data = getData();
        String strData = data.toString();
        strData = strData.substring(1, strData.length()-1);
        String[] arrData = strData.split(",");
        return arrData;
    }
    
    // Override in subclass: Return all keys in data
    public String[] getKeys(){
        String[] string = {""};
        return string;
    }
    
    // Override in subclass: Return hashtable of instance variables and their values
    public Hashtable getData(){
        Hashtable empty = new Hashtable();
        return empty;
    }

    // Override in subclass: return bool true if can withdraw
    @Override 
    public boolean canWithdraw() {
        return false;
    }
    
}
