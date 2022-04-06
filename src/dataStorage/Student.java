/*
 * Class Student extends dataStorage, implements Withdrawable
 */
package dataStorage;

import datatester.Data;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private ArrayList<Book> withdrawnBooks = new ArrayList<Book>();
    private double fines=0.0;
    
    // Constructor sets type in super class
    public Student(String aNumber){
        super("student");
        this.aNumber = aNumber;
    }
    public Student(){
        super("student");
    }
    
    @Override
    public String[] getKeys(){
        return keys;
    }
    
    // Setters and getters for instance variables
    @Override
    public String getId() {
        return aNumber;
    }

    public void setaNumber(String aNumber) {
        this.aNumber = aNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
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
    
    // Set all instance variables according to keys and values
    public void setData(Hashtable data){
        
        Enumeration<String> dataKeys = data.keys();
        while(dataKeys.hasMoreElements()){
            String k = dataKeys.nextElement();
            
            switch(k){
                case "A Number":
                    setaNumber((String) data.get(k));
                    break;
                case "Name":
                    setName((String) data.get(k));
                    break;
                case "Withdrawn Books":
                    String bookID = (String) data.get(k);
                    String[] bookIDs = bookID.split(" ");
                    ArrayList<Book> books = new ArrayList<Book>();
                    for(String id:bookIDs){
                        try {
                            Book book = (Book) Data.writeToObject("book", id);
                            books.add(book);
                        } catch (FileNotFoundException ex) {}
                    }
                    setWithdrawnBooks(books);
                    break;
                case "Fines Owed":
                    setFines(Double.parseDouble((String) data.get(k)));
                    break;
            }      
        }
    }
    
    // Add a book to withdrawnBooks
    public void withdraw(Book book) throws InvalidAction {
        
        // throw error if book already withdrawn by this student
        if (withdrawnBooks.size()>0) {
            Enumeration<Book> b = Collections.enumeration(withdrawnBooks);
            while(b.hasMoreElements()){
                Book thisB = b.nextElement();            
                if (thisB.getId().equals(book.getId())){
                    // Throw new exception here
                    throw new InvalidAction("Withdrawl", "Student already has book.");
                }    
            }
        }

        
        withdrawnBooks.add(book);
        
    }
    
    public void returnBook(Book book) throws InvalidAction {

        boolean success = false;
        // throw error if book is not withdrawn by student
        if (withdrawnBooks.size()>0){
            Enumeration<Book> b = Collections.enumeration(withdrawnBooks);
            while(b.hasMoreElements()){
                Book thisB = b.nextElement();
                if (thisB.getId().equals(book.getId())){
                    withdrawnBooks.remove(b);
                    success = true;
                }
            }
        }
        
        if (!success){
            throw new InvalidAction("Return", "Student does not have book.");
        }
        
    }
    
    
    // Return true if no fines are owed
    @Override 
    public boolean canWithdraw() {
        return fines == 0;
    }
    
    
}
