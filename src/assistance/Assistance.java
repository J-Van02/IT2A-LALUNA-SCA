
package assistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Assistance {
public void addStudent(){
    
    Scanner sc = new Scanner(System.in);
       config co = new config();
        System.out.println("Student First Name: ");
        String fname = sc.next();
        System.out.println("Student Last Name: ");
        String lname = sc.next();
      
        System.out.println("Student Email: ");
        String email = sc.next();
        System.out.println("Student Status: ");
        String status = sc.next();
         
        String sql = "INSERT INTO Student ( s_fname, s_lname, s_email, s_status )"
                + "VALUES (?, ?, ?, ?)";
        
        co.addRecord(sql, fname, lname, email, status);
}
    
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

        Assistance as = new Assistance();
        
        int choice;
        System.out.println("1. Add");
        System.out.println("Enter Choice : ");
        choice = sc.nextInt();
        
        
        switch (choice){
         
            case 1:
                as.addStudent();
                
                
                
                break;
        }
        
    }
    
}
