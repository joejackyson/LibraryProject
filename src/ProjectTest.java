/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import userInput.Admin;
import userInput.User;
import java.util.Scanner;

/**
 *
 * @author Joe
 */
public class ProjectTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        User user = new User("MAIN");
        Admin admin = new Admin("MAIN");
        boolean loggedIn = true;
        boolean adminIn = false;
        Scanner input = new Scanner(System.in);
        while(loggedIn == true){
        System.out.println(user.HomePage());
        System.out.println("Enter desired function: ");
        user.setState(input.nextLine());
        System.out.println("You entered " + user.getState());
        
        //Search input handling function
        
        if(user.getState().equals("SEARCH")){
            System.out.println("Enter a");
        }
        
        //displayall input function
        
        if(user.getState().equals("DISPLAY")){
            System.out.println("This would be a list of all the books");
            System.out.println("Enter the next desired function or enter 'Home' to return to main page");
            user.setState(input.nextLine());
        }
        
        // adminlogin function
        if(user.getState().equals("ADMINLOGIN")){
            System.out.println("Enter Admin password: ");
            String password = input.nextLine();
            if(password.equals(admin.getPassword())){
                System.out.println("You are now logged in as the admin");
                admin.setLoginStatus(true);
                admin.setState("MAIN");
            }
        }
        }
    }
    
}
