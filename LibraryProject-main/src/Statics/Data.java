/*
    Utility class:
    -   most methods take DataStorage object as argument
    -   write to files using DataStorage object methods
    -   store data in json files with key, value format

 */
package Statics;

import Exceptions.FileOverwrite;
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
    
    // Read data from a file / write to a DataStorage object
   public static DataStorage writeToObject(String type, String fileName) throws FileNotFoundException {
        
        String filePath = getFilepath(type, fileName);
        // create a new DataStorage object: cast as student or book
        DataStorage d;
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
   
   // Return all filenames in folder
   public static ArrayList<String> getFileNames(String type){
       
       String filepath = directory + type + "/";
       File folder = new File(filepath);
       File[] listedFiles = folder.listFiles();
       ArrayList<String> fileNames = new ArrayList<String>();
       
       for(File f : listedFiles){
           fileNames.add(f.getName());
       }
       
       return fileNames;
   }
   
    // create a new file from object
    public static void createNew(DataStorage data) throws FileOverwrite, FileNotFoundException{
        String filePath = getFilepath(data.getType(), data.getId());
        PrintWriter out = new PrintWriter(filePath);
        String[] formatted = data.format();
        for(String f: formatted){
            out.println(f.trim());
        }
        out.close();
    }
    
    // delete a file
    public static void delete(DataStorage data) throws FileNotFoundException{
        String filePath = getFilepath(data.getType(), data.getId());
        File dFile = new File(filePath);
        dFile.delete();
        
    }
    
    // overwrite an existing file
    public static void overwrite(DataStorage data) throws FileNotFoundException, FileOverwrite{
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
