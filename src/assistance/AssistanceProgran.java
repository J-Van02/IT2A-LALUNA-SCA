package assistance;

import java.util.Scanner;

public class AssistanceProgran {
    
    public void assProg(){
        Scanner sc = new Scanner (System.in);
        config con = new config();
        
        System.out.print("Enter Program Name: ");
        String pname = sc.nextLine();
        System.out.print("Enter Eligibility Criteria: ");
        String ec = sc.nextLine();
        System.out.print("Enter Assistance Amount: ");
        int assm = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Application Deadline (mm/dd/yy): ");
        String ad = sc.nextLine();
        System.out.print("Enter Disbursement Schedule: ");
        String ds = sc.nextLine();
        
        String sql = "INSERT INTO ass_prog (ass_pname, ass_ec, ass_assm, ass_ad, ass_ds) VALUES (?, ?, ?, ?, ?)";

        con.addRecord(sql, pname, ec, assm, ad, ds); 
    }
    
    public void Application(){
        Scanner sc = new Scanner(System.in);
        config con = new config();
        Application ap = new Application();
        AssistanceProgran asp = new AssistanceProgran();
        
        ap.viewForm();
        System.out.print("Select Student ID: ");
        int id = sc.nextInt();
        asp.viewProg();
        System.out.print("Select Program ID: ");
        int id2 = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Application Date (mm/dd/yy): ");
        String ad = sc.nextLine();
        System.out.print("Enter Status: ");
        String stat = sc.nextLine();
        System.out.print("Enter Approval/Denial Date (mm/dd/yy): ");
        String add = sc.nextLine();
        
        System.out.print("Is Application Denied? (yes/no): ");
        String res = sc.nextLine();
        
        String rd = "Not Denied";
        
        while(res.equalsIgnoreCase("yes")){
            System.out.print("Enter Reason for Denial: ");
            rd = sc.nextLine();
        }
            
        String sql = "INSERT INTO application (s_id, adprog_id, ap_date, ap_status, ap_add, ap_rd) VALUES (?, ?, ?, ?, ?, ?)";

        con.addRecord(sql, id, id2, ad, stat, add, rd); 
    }
    
    public void disbursement(){
        Scanner sc = new Scanner(System.in);
        config con = new config();
        AssistanceProgran asp = new AssistanceProgran();
        
        asp.viewApp();
        System.out.print("Enter Application ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Disbursement Date (mm/dd/yy): ");
        String dd = sc.nextLine();
        System.out.print("Enter Amount Disbursed: ");
        int ad = sc.nextInt();
        System.out.print("Enter Payment Method: ");
        String pm = sc.nextLine();
        
        String sql = "INSERT INTO dissbursement (ap_id, ds_dd, ds_ad, ds_pm) VALUES (?, ?, ?, ?, ?)";

        con.addRecord(sql, id, dd, ad, pm); 
    }
    
    public void viewProg(){
        String qry = "SELECT * FROM ass_prog";
        String[] hdrs = {"Program ID", "Program Name", "Elibility Criteria", "Assistance Amount", "Application Deadline", "Disbursement Schedule"};
        String[] clms = {"adprog_id","ass_pname", "ass_ec", "ass_assm", "ass_ad", "ass_ds"};
        
        config co = new config();
        co.viewRecords(qry, hdrs, clms);
    }
    
    public void viewApp(){
        String qry = "SELECT Student.s_id, adprog_id FROM application INNER JOIN Student ON Student.s_id = application.s_id "
                + "INNER JOIN ass_prog ON ass_prog.adprog_id = application.adprog_id";
        String[] hdrs = {"Student ID", "Program ID", "Application Date", "Status", "Approval/Denial Date", "Reason for Denial"};
        String[] clms = {"s_id","adprog_id", "ap_date", "ap_status", "ap_add", "ap_rd"};
        
        config co = new config();
        co.viewRecords(qry, hdrs, clms);
    }
    
   public void viewResult() {
    String qry = "SELECT Student.s_lname, ass_prog.ass_pname, ass_prog.ass_assm, ass_prog.ass_ds, application.ap_status, dissbursement.ds_ad "
                + "FROM report "
                + "INNER JOIN Student ON Student.s_lname = report.s_lname "
                + "INNER JOIN ass_prog ON ass_prog.ass_pname = report.ass_pname AND ass_prog.ass_assm = report.ass_assm AND ass_prog.ass_ds = report.ass_ds "
                + "INNER JOIN application ON application.ap_status = report.ap_status "
                + "INNER JOIN dissbursement ON dissbursement.ds_ad = report.ds_ad";
    
    String[] hdrs = {
        "Student Name", 
        "Program Name", 
        "Assistant Amount", 
        "Disbursement Schedule", 
        "Status", 
        "Disbursement Amount" 
    };
    
    String[] clms = {
        "s_lname", 
        "ass_pname", 
        "ass_assm", 
        "ass_ds", 
        "ap_status", 
        "ds_ad"
    };
    
    // Assuming config is a utility class that handles database connection and display logic
    config co = new config();
    
    // Call to viewRecords method to display the data
    co.viewRecords(qry, hdrs, clms);
}
    
    public void updateProg(){
        Scanner sc = new Scanner(System.in);
        config con = new config();
        
        System.out.print("Enter Program ID to Update: ");
        int id = sc.nextInt();
        
        System.out.print("Enter new Application Deadline (mm/dd/yy): ");
        String ad = sc.nextLine();
        System.out.print("Enter new Disbursement Schedule: ");
        String ds = sc.nextLine();
        
        String sql = "UPDATE ass_prog SET ss_ad, ass_ds WHERE adprog_id = ?";
        con.updateRecord(sql, ad, ds, id);
    }
    
    public void deleteProg(){
        Scanner sc = new Scanner(System.in);
        config con = new config();
        
        System.out.print("Enter Program ID to Delete: ");
        int id = sc.nextInt();
        
        while((con.getSingleValue("SELECT adprog_id FROM ass_prog WHERE adprog_id = ?", id)) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Enter Application ID again: ");
        id = sc.nextInt();
    }

        String sql = "DELETE FROM ass_prog WHERE adprog_id = ?";
        con.deleteRecord(sql, id);
    }
    
    public void deleteApp(){
        Scanner sc = new Scanner(System.in);
        config con = new config();
        
        System.out.print("Enter Application ID to Delete: ");
        int id = sc.nextInt();
        
        while((con.getSingleValue("SELECT ap_id FROM application WHERE ap_id = ?", id)) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Enter Account ID again: ");
        id = sc.nextInt();
    }

        String sql = "DELETE FROM application WHERE ap_id = ?";
        con.deleteRecord(sql, id);
    }
        
    public void deleteDis(){
        Scanner sc = new Scanner(System.in);
        config con = new config();
        
        System.out.print("Enter disbursement ID to Delete: ");
        int id = sc.nextInt();
        
        while((con.getSingleValue("SELECT dis_id FROM disbursement WHERE dis_id = ?", id)) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Enter Disbursement ID again: ");
        id = sc.nextInt();
    }

        String sql = "DELETE FROM disbursement WHERE ap_id = ?";
        con.deleteRecord(sql, id);
    }
    
    public void sForm(){
        Scanner sc = new Scanner(System.in);
        Application ap = new Application();
        AssistanceProgran asp = new AssistanceProgran();
        
        int choice;
        boolean exit = true;
        
        do {
            System.out.println("------------ STUDENT CASH ASSISTANCE -------------");
            System.out.println("1. Add Assistance Program");
            System.out.println("2. Add Application");
            System.out.println("3. Add Disbursement");
            System.out.println("4. View Record and Result");
            System.out.println("5. Update Program");
            System.out.println("6. Delete Record");
            System.out.println("7. Exit");
            System.out.println("---------------------------------------------------");
            System.out.println("Enter Choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    asp.assProg();
                    break;
                    
                case 2:
                    asp.Application();
                    break;
                    
                case 3:
                    asp.disbursement();
                    break;
                    
                case 4:
                    System.out.println("\nList Program");
                    asp.viewProg();
                    System.out.println("\nList Application");
                    asp.viewApp();
                    System.out.println("\nResult");
                    asp.viewResult();
                    break;
                    
                case 5:
                    asp.updateProg();
                    break;
                    
                case 6:
                    System.out.println("Delete Program? (yes/no): ");
                    String res = sc.next();
                    while(res.equalsIgnoreCase("yes")){
                        asp.deleteProg();
                        break;
                    }
                    
                    System.out.println("Delete Application (yes/no): ");
                    String res2 = sc.next();
                    while(res2.equalsIgnoreCase("yes")){
                        asp.deleteApp();
                        break;
                    }
                    
                    System.out.println("Delete Disbursement? (yes/no): ");
                    String res3 = sc.next();
                    while(res3.equalsIgnoreCase("yes")){
                        asp.deleteDis();
                        break;
                    }
                    break;
                    
                case 7:
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
