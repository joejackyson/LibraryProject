/*
 * This exception is thrown when an existing file is to be written to
 */
package datatester;

import java.io.IOException;

/**
 *
 * @author Celia
 */
public class FileOverwrite extends IOException{
    
    public FileOverwrite(String filename, String path){
        super(String.format("A file called %s already exists in the path %s.\nDelete this file before proceeding.", filename, path));
    }
    
}
