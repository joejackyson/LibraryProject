/*
 * Class Book extends DataStorage, implements Withdrawable
 */
package dataStorage;

import Exceptions.InvalidAction;
import java.util.Calendar;
/**
 *
 * @author Celia
 */
public class Book extends DataStorage {
    
    // Keys associated with book subclass
    private final String[] keys = new String[]{"ID","Author","Title","Date Published", "Publisher", "Description"};    
    
    // Instance variables - Edited by user:Admin
    private String id;
    private String author;
    private String title;
    private String datePublished;
    private String publisher;
    private String description;
    
    // Instance variables - Not edited by user
    private boolean available = true;
    private double dateWithdrawn = 0.0;   // DATE.MONTH.YEAR
    
    public Book(String id){
        super("book");
        this.id = id;
    }
    
    public Book(){
        super("book");
    }

    
    // Setters + Getters for instance variables
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public void setName(String title) {
        this.title = title;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    // Return amount owed on this book
    public double getFine(){
        if (canWithdraw() && dateWithdrawn!=0.0){
            // Calculate fine based on days late
        }
        
        return 0.0;
    }
    
    // Mark this book as withdrawn
    public void withdraw() throws InvalidAction {
        if(!available){
            throw new InvalidAction("Withdrawl", "Book not available");
        } else{
            // Set dateWithdrawn to today's date
            available=false;
        }
        
    }
    
    // Return this book
    public void returnBook() throws InvalidAction{
        if (available){
            throw new InvalidAction("Return", "Book is already returned");
        } else {
            dateWithdrawn = 0.0;
            available = true;
        }
    }
        
    // Return true if book is available
    @Override 
    public boolean canWithdraw() {
        return available;
    }
    
    
}
