package assistance;

import java.util.Scanner;

public class Application {
    public void addForm() {
        Scanner sc = new Scanner(System.in);
        config co = new config();
      
        System.out.print("Enter Student First Name: ");
        String fname = sc.nextLine();
        System.out.print("Enter Student Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Enter Date of Birth (mm/dd/yy): ");
        String db = sc.nextLine();
        System.out.print("Enter Gender: ");
        String gen = sc.nextLine();
        System.out.print("Enter Status: ");
        String status = sc.nextLine();
        System.out.print("Enter Address: ");
        String add = sc.nextLine();
        System.out.print("Enter Student Applicant Email: ");
        String email =sc.nextLine();
        System.out.print("Enter Contact Number: ");
        int cn = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Program/Course Enrolled: ");
        String pce = sc.nextLine();
        System.out.print("Enter Year Level: ");
        String yrl = sc.nextLine();
        
        System.out.println("\n------- GUARDIAN INFORMATION --------");
        System.out.print("Enter Guardian First Name: ");
        String gfname = sc.nextLine();
        System.out.print("Enter Guardian Last Name: ");
        String glname = sc.nextLine();
        System.out.print("Enter Contact Number: ");
        int gcn = sc.nextInt();
       
        String sql = "INSERT INTO Student (s_fname, s_lname, s_db, s_gen, s_status, s_add, s_email, s_cn, s_pce, s_yrl, s_gfname, s_glname, s_gcn)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        co.addRecord(sql, fname, lname, db, gen, add, email, cn, pce, yrl, status, gfname, glname, gcn); 
    }
    
 public void viewForm() {
    String studentsQuery = "SELECT * FROM Student";
    
    String[] studentsHeaders = {
        "ID", "First Name", "Last Name", "Birth Date", "Gender", "Address", "Status", "Email", "Contact Number", "Program/Course Enrolled", "Year Level"
    };
    
    String[] studentsColumns = {
        "s_id", "s_fname", "s_lname", "s_db", "s_gen", "s_status", "s_add", "s_email", "s_cn", "s_pce", "s_yrl",       
    };
    
    config co = new config();
    co.viewRecords(studentsQuery, studentsHeaders, studentsColumns);
}
    
    public void viewGurdian(){
        String qry = "SELECT * FROM Student";
        String[] hdrs = {"Guardian First Name", "Guardian Last Name", "Guardian Contact Number"};
        String[] clms = {"s_gfname", "s_glname", "s_gcn"};
        
        config co = new config();
        co.viewRecords(qry, hdrs, clms);
    }
    
    public void updateForm() {
        Scanner sc = new Scanner(System.in);
        config co = new config();
        
        System.out.print("Enter Student Applicant ID to update: ");
        int studentId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new Address: ");
        String add = sc.nextLine();
        System.out.print("Enter new Email: ");
        String email = sc.nextLine();
        System.out.print("Enter new Contact Number: ");
        int cn = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new Program/Course Enrolled: ");
        String pce = sc.nextLine();
        System.out.print("Enter new Year Level: ");
        String yrl = sc.nextLine();
        System.out.println("Enter new Status: ");
        String status = sc.nextLine();

        String sqlUpdate = "UPDATE Student SET s_add = ?, s_email = ?, s_cn = ?, s_pce = ?, s_yrl, s_status WHERE s_id = ?"; 
        co.updateRecord(sqlUpdate, add, email, cn, pce, yrl, status);
    }
    
    
    public void deleteForm() {
        Scanner sc = new Scanner(System.in);
        config co = new config();
        
        System.out.println("Enter Student ID to delete: ");
        int studentId = sc.nextInt();

        String sqlDelete = "DELETE FROM Student WHERE s_id = ?"; 
        co.deleteRecord(sqlDelete, studentId);
    }
    
    public void studentForm(){
        Scanner sc = new Scanner(System.in);
        Application ap = new Application();
        
        int choice;
        boolean exit = true;
        
        do {
            System.out.println("\n------------ STUDENT CASH ASSISTANCE -------------");
            System.out.println("1. Apply Assistance");
            System.out.println("2. View Form");
            System.out.println("3. View Application Result");
            System.out.println("4. Update Form");
            System.out.println("5. Delete Form");
            System.out.println("6. Exit");
            System.out.println("---------------------------------------------------");
            System.out.print("Enter Choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    ap.addForm();
                    break;
                    
                case 2:
                    System.out.println("\n----- STUDENT INFORMATION ------");
                    ap.viewForm();
                    System.out.println("\n----- GUARDIAN INFORMATION --------");
                    ap.viewGurdian();
                    break;
                    
                case 3:
                    AssistanceProgran asp = new AssistanceProgran();
                    asp.viewResult();
                    break;
                    
                case 4:
                    ap.updateForm();
                    break;
                    
                case 5:
                    ap.deleteForm();
                    break;
                    
                case 6:
                    System.out.print("Exit selected...type yes to continue: ");
                        String resp = sc.next();
                        if(resp.equalsIgnoreCase("yes")){
                        exit = false;
                        }
                    break;

                    default:
                        System.out.println("Action Error, There's no such number");
            }
        }while(exit); 
    }
}

