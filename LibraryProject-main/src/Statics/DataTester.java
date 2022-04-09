/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statics;

import Exceptions.InvalidAction;
import Exceptions.FileOverwrite;
import dataStorage.*;
import java.io.FileNotFoundException;

/**
 *
 * @author cahough
 */
public class DataTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileOverwrite, FileNotFoundException, InvalidAction {
        // TODO code application logic here
        
        
        // Write to Object using Data class
        
        DataStorage a = Data.writeToObject("student", "A00555");
        DataStorage b = new Book("010");
        System.out.println(a.getId()+ " "+ a.getName());    // Super class methods
        
        
        // After writing to object, cast object as subclass
        
        Student s = (Student) a;
        System.out.println(s.getWithdrawnBooks());          // Sub class method
        
        
        // Edit student subclass
        s.returnBook((Book) b);
        
        
        // Save Object to file
        
        Data.overwrite(s);
        Data.createNew(b);
        
    }
    
}
