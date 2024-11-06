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
        System.out.print("Date of Birth (mm/dd/yy): ");
        String db = sc.nextLine();
        System.out.print("Gender: ");
        String gen = sc.nextLine();
        System.out.print("Status: ");
        String status = sc.nextLine();
        System.out.print("Address: ");
        String add = sc.nextLine();
        System.out.print("Student Applicant Email: ");
        String email =sc.nextLine();
        System.out.print("Contact Number: ");
        String cn = sc.nextLine();
        System.out.print("Program/Course Enrolled: ");
        String pce = sc.nextLine();
        System.out.print("Year Level: ");
        String yrl = sc.nextLine();
        
        System.out.println("\n------- GUARDIAN INFORMATION --------");
        System.out.print("Enter Guardian First Name: ");
        String gfname = sc.nextLine();
        System.out.print("Enter Guardian Last Name: ");
        String glname = sc.nextLine();
        System.out.print("Contact Number: ");
        int gcn = sc.nextInt();
       
        String sql = "INSERT INTO Student (s_fname, s_lname, s_db, s_gen, s_status, s_add, s_email, s_cn, s_pce, s_yrl, s_gfname, s_glname, s_gcn)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        co.addRecord(sql, fname, lname, db, gen, status, add, email, cn, pce, yrl, gfname, glname, gcn); 
    }
    
    public void Apply(){
        Scanner sc = new Scanner(System.in);
        config con = new config();
        Application ap = new Application();
        AssistanceProgran asp = new AssistanceProgran();
        
        System.out.println("\n------ STUDENT INFORMATION ------");
        ap.viewForm();
        System.out.print("Select Student ID: ");
        int id = sc.nextInt();
        
        while((con.getSingleValue("SELECT s_id FROM Student WHERE s_id = ?", id)) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Enter Student ID again: ");
        id = sc.nextInt();
    }
        
        System.out.println("\n----- LIST OF PROGRAM -----");
        asp.viewProg();
        System.out.print("Select Program ID: ");
        int id2 = sc.nextInt();
        
        while((con.getSingleValue("SELECT adprog_id FROM ass_prog WHERE adprog_id = ?", id2)) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Enter Program ID again: ");
        id = sc.nextInt();
    }
        
        sc.nextLine();
        System.out.print("Enter Application Date (mm/dd/yy): ");
        String ad = sc.nextLine();
        
        String stat = "Pending..";
            
        String sql = "INSERT INTO application (s_id, adprog_id, ap_date, ap_status) VALUES (?, ?, ?, ?)";
        con.addRecord(sql, id, id2, ad, stat);
    }
    
    public void viewForm() {
    String studentsQuery = "SELECT * FROM Student";
    String[] studentsHeaders = {"Student ID", "First Name", "Last Name", "Birth Date", "Gender", "Status", "Address", "Email", "Contact Numer", "Program Enrolled", "Year Level"};
    String[] studentsColumns = {"s_id", "s_fname", "s_lname", "s_db", "s_gen", "s_status", "s_add", "s_email", "s_cn", "s_pce", "s_yrl"};
    
    config co = new config();
    co.viewRecords(studentsQuery, studentsHeaders, studentsColumns);
}
    
    public void viewApply() {
    String studentsQuery = "SELECT * FROM application";
    String[] studentsHeaders = {"Application ID", "Student ID", "Application Date", "Status"};
    String[] studentsColumns = {"ap_id", "s_id", "ap_date", "ap_Status"};
    
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
        int id = sc.nextInt();
        
        while((co.getSingleValue("SELECT s_id FROM Student WHERE s_id = ?", id)) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Enter Student ID again: ");
        id = sc.nextInt();
    }
        
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

        String sqlUpdate = "UPDATE Student SET s_add = ?, s_email = ?, s_cn = ?, s_pce = ?, s_yrl, s_status = ? WHERE s_id = ?"; 
        co.updateRecord(sqlUpdate, add, email, cn, pce, yrl, status);
    }
    
    
    public void deleteForm() {
        Scanner sc = new Scanner(System.in);
        config co = new config();
        
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        
        while((co.getSingleValue("SELECT s_id FROM Student WHERE s_id = ?", id)) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Enter Student ID again: ");
        id = sc.nextInt();
    }

        String sqlDelete = "DELETE FROM Student WHERE s_id = ?"; 
        co.deleteRecord(sqlDelete, id);
    }
    
        public void deleteApply() {
        Scanner sc = new Scanner(System.in);
        config co = new config();
        
        System.out.print("Enter Application ID to delete: ");
        int id = sc.nextInt();

        while((co.getSingleValue("SELECT ap_id FROM appliucation WHERE ap_id = ?", id)) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Enter Application ID again: ");
        id = sc.nextInt();
    }
        
        String sqlDelete = "DELETE FROM application WHERE ap_id = ?"; 
        co.deleteRecord(sqlDelete, id);
    }
    
    public void studentForm(){
        Scanner sc = new Scanner(System.in);
        Application ap = new Application();
        
        int choice;
        boolean exit = true;
        
        do {
            System.out.println("\n------------ STUDENT CASH ASSISTANCE -------------");
            System.out.println("1. Add Form");
            System.out.println("2. Apply Program");
            System.out.println("3. View Form and Application");
            System.out.println("4. Update Form");
            System.out.println("5. Delete Form and Application");
            System.out.println("6. Exit");
            System.out.println("---------------------------------------------------");
            System.out.print("Enter Choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    ap.addForm();
                    break;
                    
                case 2:
                    ap.Apply();
                    break;
                    
                case 3:
                    System.out.println("\n----- STUDENT INFORMATION ------");
                    ap.viewForm();
                    System.out.println("\n----- GUARDIAN INFORMATION --------");
                    ap.viewGurdian();
                    System.out.println("\n ------ LIST OF APPLICATION --------");
                    ap.viewApply();
                    break;
                    
                case 4:
                    System.out.println("\n----- STUDENT INFORMATION ------");
                    ap.viewForm();
                    System.out.println("\n----- GUARDIAN INFORMATION --------");
                    ap.viewGurdian();
                    ap.updateForm();
                    System.out.println("\n------ LIST OF APPLICATION ---------");
                    ap.Apply();
                    break;
                    
                case 5:
                    System.out.println("\n----- STUDENT INFORMATION ------");
                    ap.viewForm();
                    System.out.println("\n----- GUARDIAN INFORMATION --------");
                    ap.viewGurdian();
                    ap.deleteForm();
                    System.out.println("\n------ LIST OF APPLICATION ---------");
                    ap.viewApply();
                    ap.deleteApply();
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

