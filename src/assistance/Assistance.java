package assistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Assistance {

    public void addStudent() {
        Scanner sc = new Scanner(System.in);
        config co = new config();
  
      
        System.out.println("Student Applicant First Name: ");
        String fname = sc.next();
        System.out.println("Student Applicant Last Name: ");
        String lname = sc.next();

        System.out.println("Student Applicant Email: ");
        String email = sc.next();
        System.out.println("Student Applicant Status: ");
        String status = sc.next();

        
       
        String sql = "INSERT INTO Student (s_fname, s_lname, s_email, s_status )"
                + "VALUES (?, ?, ?, ?)";

        co.addRecord(sql, fname, lname, email, status); 
    }

    private void viewStudents() {
        String studentsQuery = "SELECT * FROM Student";
        String[] studentsHeaders = {"ID", "First Name", "Last Name", "Email", "Status"};
        String[] studentsColumns = {"s_id", "s_fname", "s_lname", "s_email", "s_status"}; 

        config co = new config();
        co.viewRecords(studentsQuery, studentsHeaders, studentsColumns);
    }

    private void deleteStudent() {
        Scanner sc = new Scanner(System.in);
        config co = new config();
        System.out.println("Enter Student ID to delete: ");
        int studentId = sc.nextInt();

        String sqlDelete = "DELETE FROM Student WHERE s_id = ?"; 
        co.deleteRecord(sqlDelete, studentId);
    }

    private void updateStudent() {
        Scanner sc = new Scanner(System.in);
        config co = new config();
        System.out.println("Enter Student Applicant ID to update: ");
        int studentId = sc.nextInt();
        System.out.println("Enter new First Name: ");
        String fname = sc.next();
        System.out.println("Enter new Last Name: ");
        String lname = sc.next();
        System.out.println("Enter new Email: ");
        String email = sc.next();
        System.out.println("Enter new Status: ");
        String status = sc.next();

        String sqlUpdate = "UPDATE Student SET s_fname = ?, s_lname = ?, s_email = ?, s_status = ? WHERE s_id = ?"; 
        co.updateRecord(sqlUpdate, fname, lname, email, status, studentId);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Assistance as = new Assistance();

        int choice;
        boolean continueLoop = true;
        
        do {
            System.out.println("_________STUDENT CASH ASSISTANCE_________");
            System.out.println("=========1. Add Student Applicant     ===========");
            System.out.println("=========2. View Student Applicant    ===========");
            System.out.println("=========3. Delete Student Applicant  ===========");
            System.out.println("=========4. Update Student Applicant  ===========");
            System.out.println("=========5. Exit                       ===========");
            System.out.println("Enter Choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    as.addStudent();
                    break;
                case 2:
                    as.viewStudents();
                    break;
                case 3:
                    as.deleteStudent();
                    break;
                case 4:
                    as.updateStudent();
                    break;
                case 5:
                    continueLoop = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (continueLoop);
    }

}