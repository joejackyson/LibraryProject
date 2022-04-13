/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInput;

/**
 *
 * @author Joe
 */
public class User {
    private String state; // state holds the command being enter ex. search or displayall etc.
    private String key;
    private String value;
    
    
    public String SearchBook(String key, String value){
        
        return "This would be a list of books" + "/n Enter the next desired function or enter 'Home' to return to main page";//searchMethod(key, value)
    }
    
    
    public String HomePage(){
        return "Available functions:Input to select function \nSearch for a book:search \nDisplay all books:display \nLogin as admin:adminlogin";
    }
    
    public User(String state){
        this.state = state;
    }
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state.toUpperCase();
    }
    
            
    }
