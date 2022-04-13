/*
 * Class Book extends DataStorage, implements Withdrawable
 */
package dataStorage;

import Exceptions.InvalidAction;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Enumeration;
import java.util.Hashtable;
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
    private double timeWithdrawn = 0.0;   // Amount of days total that the book has been withdrawn
    private LocalDate dateWithdrawn;      // The date on which the book was withdrawn
    private static int withdrawWindow = 30;
    
    // Constructors
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

    
    // Return hashtable of instance variables and their values
    @Override
    public Hashtable getData(){
        
        Hashtable <String, String> bookData = new Hashtable<String, String>();
        
        for (String key: keys){
            
            switch(key){
                
                case "ID":
                    bookData.put(key, id);
                    break;
                
                case "Title":
                    bookData.put(key, title);
                    break;
                    
                case "Date Published":
                    bookData.put(key, datePublished);
                    break;
                    
                case "Publisher":
                    bookData.put(key, publisher);
                    break;
                    
                case "Description":
                    bookData.put(key, description);
                    break;     
            }
        }  
        bookData.put("Available", Boolean.toString(available));
        bookData.put("Date Withdrawn", dateWithdrawn.toString());
           
        return bookData;
    }
    
    // Set all instance variables according to keys and values
    public void setData(Hashtable data){
        
        Enumeration<String> dataKeys = data.keys();
        while(dataKeys.hasMoreElements()){
            String k = dataKeys.nextElement();
            
            switch(k){
                case "ID":
                    setId((String) data.get(k));
                    break;
                    
                case "Author":
                    setAuthor((String) data.get(k));
                    break;
                 
                case "Title":
                    setName((String) data.get(k));
                    break;
                    
                case "Date Published":
                    setDatePublished((String) data.get(k));
                    break;
                    
                case "Publisher":
                    setPublisher((String) data.get(k));
                    break;
                    
                case "Description":
                    setDescription((String) data.get(k));
                    break;
            }
        }
        // Set Available and Date Withdrawn
        this.available = data.get("Available").equals("true");
        if(data.get("Date Withdrawn")!=null){
            this.dateWithdrawn = LocalDate.parse((CharSequence) data.get("Date Withdrawn"));
        }
        // If not available, set Time withdrawn
        if(!available){
            this.timeWithdrawn = ChronoUnit.DAYS.between(LocalDate.now(), dateWithdrawn);
        }
    }
    
    
    // Return amount owed on this book
    public double getFine(){
        
        this.timeWithdrawn = ChronoUnit.DAYS.between(LocalDate.now(), dateWithdrawn);
        double fine = 0.0;
        
        // Calculate fine based on days late
        if (canWithdraw() && timeWithdrawn <= withdrawWindow){ 
            fine = 0.50 + 0.50*(timeWithdrawn-withdrawWindow);  
        }
        
        return fine;
    }
    
    // Mark this book as withdrawn
    public void withdraw() throws InvalidAction {
        if(!available){
            throw new InvalidAction("Withdrawl", "Book not available");
        } else{
            // Set dateWithdrawn to today's date  
            dateWithdrawn = LocalDate.now();
            this.available=false;
        }
    }
    
    // Return this book
    public void returnBook() throws InvalidAction{
        if (available){
            throw new InvalidAction("Return", "Book is already returned");
        } else {
            timeWithdrawn = 0.0;
            this.available = true;
        }
    }
        
    // Return true if book is available
    @Override 
    public boolean canWithdraw() {
        if(available){
            return true;
        }else{
            return false;
        }
    }
    
}
