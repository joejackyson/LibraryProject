/*
 * Class Student extends dataStorage, implements Withdrawable
 */
package dataStorage;

import java.util.Hashtable;
import java.util.ArrayList;

/**
 *
 * @author Celia
 */
public class Student extends dataStorage{
    
    // Keys associated with student subclass
    private final String[] keys = new String[]{"A Number","Name","Withdrawn Books","Fines Owed"};
    
    // Instance Variables
    private String aNumber = "";
    private String name = "";
    private ArrayList<Book> withdrawnBooks;
    private double fines=0.0;
    
    // Constructor sets type in super class
    public Student(){
        super("student");
    }
    
    @Override
    public String[] getKeys(){
        return keys;
    }
    
    // Setters and getters for instance variables
    public String getaNumber() {
        return aNumber;
    }

    public void setaNumber(String aNumber) {
        this.aNumber = aNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getWithdrawnBooks() {
        return withdrawnBooks;
    }

    public void setWithdrawnBooks(ArrayList<Book> withdrawnBooks) {
        this.withdrawnBooks = withdrawnBooks;
    }

    public double getFines() {
        return fines;
    }

    public void setFines(double fines) {
        this.fines = fines;
    }
    
    
    // Return hashtable of instance variables and their values
    @Override 
    public Hashtable getData(){
        
        Hashtable<String, String> studentData = new Hashtable<String, String>();
        
        for (String key: keys){
            
            switch(key){
                
                case "A Number": 
                    studentData.put(key, aNumber);
                    break;
                
                case "Name":    
                    studentData.put(key, name);
                    break;
                    
                case "Withdrawn Books": 
                    String bookIDs = "";
                    if (withdrawnBooks != null && withdrawnBooks.size()>0){
                        for(Book book: withdrawnBooks){
                            if (book != null){
                                bookIDs += book.getId();
                                bookIDs += " ";    
                            }
                        }
                    }
                    studentData.put(key, bookIDs);
                    break;
                
                case "Fines Owed":
                    String convertFines = ""+fines;
                    studentData.put(key, convertFines);
                    break;
                    
            }
            
        }
        
        return studentData;
        
    }
            
    
    // Return true if no fines are owed
    @Override 
    public boolean canWithdraw() {
        return fines == 0;
    }
    
    
}
