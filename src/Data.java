/*
    Utility class:
    -   most methods take dataStorage object as argument
    -   write to files using dataStorage object methods
    -   store data in json files with key, value format

 */
package datatester;

import dataStorage.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author cahough
 */
public final class Data{
    
    // Instance variables are final
    final static String directory = "files/";
    final static String fileExt = ".txt";
    
    // private constructor to restrict object creation
    private Data(){
        
    }
    
    // All static methods
    // Read data from a file / write to a dataStorage object
   public static dataStorage writeToObject(String type, String fileName) throws FileNotFoundException {
        
        String filePath = getFilepath(type, fileName);
        // create a new dataStorage object: cast as student or book
        dataStorage d;
        if (type.equals("student")){
            d = new Student();
        } else{
            d = new Book();
        }
        
        // get formatted string of data from file
        ArrayList<String> lines = new ArrayList<String>();
        try {
            File readFile = new File(filePath);
            Scanner reader = new Scanner(readFile);
                while (reader.hasNextLine()){
                  lines.add(reader.nextLine());
                }
            reader.close();
        } catch (FileNotFoundException e) {
            // Insert reaction here
        }
        
        // write data to object
        d.reverseFormat(lines);
        return d;
        
    }
   
    // create a new file from object
    public static void createNew(dataStorage data) throws FileOverwrite, FileNotFoundException{
        String filePath = getFilepath(data.getType(), data.getId());
        PrintWriter out = new PrintWriter(filePath);
        String[] formatted = data.format();
        for(String f: formatted){
            out.println(f.trim());
        }
        out.close();
    }
    
    // delete a file
    public static void delete(dataStorage data) throws FileNotFoundException{
        String filePath = getFilepath(data.getType(), data.getId());
        File dFile = new File(filePath);
        dFile.delete();
        
    }
    
    // overwrite an existing file
    public static void overwrite(dataStorage data) throws FileNotFoundException, FileOverwrite{
        try{
            createNew(data);
        }
        catch(FileOverwrite excep){
            delete(data);
            createNew(data);
        }
    }
    
    // Return fully formatted filepath
    public static String getFilepath(String folder, String fileName){
        String filePath = directory + folder + "/" + fileName + fileExt;
        return filePath;
    }
}
