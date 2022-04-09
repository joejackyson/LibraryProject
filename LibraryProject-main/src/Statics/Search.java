package Statics;

import Exceptions.FileOverwrite;
import dataStorage.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/*
 * Interacts with Data class to load files to objects, return object
 */
/**
 *
 * @author Celia
 */
public final class Search {

    private Search() {
    }
    
    // CALEBS METHODS
    
    // Return the IDs and names of all objects of a given type
    public static String view(String subtype) throws FileNotFoundException{ 
        //Store an array of DataStorage Objects:
        //DataStorage[] objects = getAll(subtype); --> subtype = "student" or "book"
        
        // Loop objects
            // Call object.getId(), object.getName()
            // Store these values in a formatted string
        
        // Return Formatted String
        
        return "";
    }
    
    // Return all data related to all objects of a given type
    public static String viewVerbose(String subtype){
        // Store an array or DataStorage Objects:
        // DataStorage[] objects = getAll(subtype);
        
        // Loop Objects
            /*Call object.format()
                 Returns An array of Strings, containing each of the variables
                 and their values
            */
            
        // Return formatted string
        
        return "";
    }
    

    // Return all objects of type
    public static DataStorage[] getAll(String subtype) throws FileNotFoundException {
        ArrayList<String> fileNames = Data.getFileNames(subtype);
        DataStorage[] allObjects = new DataStorage[fileNames.size()];

        for (int i = 0; i < fileNames.size(); i++) {
            DataStorage d = Data.writeToObject(subtype, fileNames.get(i));
            allObjects[i] = d;
        }
        return allObjects;
    }

    // Return students whose attributes and values match parameters
    public static ArrayList<Student> getStudent(String key, String value) throws FileNotFoundException {
        ArrayList<String> fileNames = Data.getFileNames("student");
        ArrayList<Student> students = new ArrayList<Student>();
        
        // If student is searched by ID, return object with that filename
        if(key.equals("A Number")){
            students.add((Student) Data.writeToObject("student", value));
            return students;
        }
        // Else, Loop all files in Student folder
        for (int i=0; i<fileNames.size(); i++){
            Student student = (Student) Data.writeToObject("student", fileNames.get(i));

            // Enumerate Student keys
            Hashtable<String, String> studentData = student.getData();
            Enumeration<String> studentKeys = studentData.keys();
            while(studentKeys.hasMoreElements()){
                // If the key matches param key
                if(key.equals(studentKeys.nextElement())){
                    // If the value matches param value
                    if(value.equals(studentData.get(key))){
                        // This Student Matches! Add them to the return list
                        students.add(student);
                    }
                }
            }
        }
        return students;
    }
    
    // Return books whose attributes and values match parameters
    public static ArrayList<Book> getBook(String key, String value) throws FileNotFoundException {
        ArrayList<String> fileNames = Data.getFileNames("book");
        ArrayList<Book> books = new ArrayList<Book>();
        
        // If book is searched by ID, return object with that filename
        if(key.equals("ID")){
            books.add((Book) Data.writeToObject("book", value));
            return books;
        }
        // Else, Loop all files in Book folder
        for (int i=0; i<fileNames.size(); i++){
            Book book = (Book) Data.writeToObject("book", fileNames.get(i));

            // Enumerate Book keys
            Hashtable<String, String> bookData = book.getData();
            Enumeration<String> bookKeys = bookData.keys();
            while(bookKeys.hasMoreElements()){
                // If the key matches param key
                if(key.equals(bookKeys.nextElement())){
                    // If the value matches param value
                    if(value.equals(bookData.get(key))){
                        // This Book Matches! Add it to the return list
                        books.add(book);
                    }
                }
            }
        }
        return books;
    }
}