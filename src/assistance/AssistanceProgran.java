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
        System.out.print("Enter Release Schedule (mm/dd/yy): ");
        String ds = sc.nextLine();
        
        String sql = "INSERT INTO ass_prog (ass_pname, ass_ec, ass_assm, ass_ad, ass_ds) VALUES (?, ?, ?, ?, ?)";

        con.addRecord(sql, pname, ec, assm, ad, ds); 
    }
    
    public void Application(){
        Scanner sc = new Scanner(System.in);
        Application ap =  new Application();
        config con = new config();
        
        System.out.print("Enter Application ID: ");
        int id = sc.nextInt();
        
        while((con.getSingleValue("SELECT ap_id FROM application WHERE ap_id = ?", id)) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Enter application ID again: ");
        id = sc.nextInt();
    }
        System.out.print("Enter Status: ");
        String stat = sc.next();
        
        String sqlUpdate = "UPDATE application SET ap_status = ? WHERE ap_id = ?"; 
        con.updateRecord(sqlUpdate, stat, id);
    }
    
    public void release(){
        Scanner sc = new Scanner(System.in);
        config con = new config();
        AssistanceProgran asp = new AssistanceProgran();
        
        System.out.println("\n ---- LIST OF PROGRAM ----");
        asp.viewProg();
        System.out.print("Enter Program Name: ");
        String pname = sc.nextLine();
        
        System.out.println("\n---- LIST OF APPLICANT -----");
        asp.viewApp();
        System.out.print("Enter Applicant Name: ");
        String aname = sc.nextLine();
        
        sc.nextLine();
        System.out.print("Enter Release Date (mm/dd/yy): ");
        String dd = sc.nextLine();
        System.out.print("Enter Amount Release: ");
        int ad = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Payment Method: ");
        String pm = sc.nextLine();
        
        String sql = "INSERT INTO release (s_fname, ass_pname, ds_dd, ds_ad, ds_pm) VALUES (?, ?, ?, ?, ?)";

        con.addRecord(sql, pname, aname, dd, ad, pm); 
    }
    
    public void viewProg(){
        String qry = "SELECT * FROM ass_prog";
        String[] hdrs = {"Program ID", "Program Name", "Elibility Criteria", "Assistance Amount", "Application Deadline", "Release Schedule"};
        String[] clms = {"adprog_id","ass_pname", "ass_ec", "ass_assm", "ass_ad", "ass_ds"};
        
        config co = new config();
        co.viewRecords(qry, hdrs, clms);
    }
    
    public void viewApp(){
        String qry = "SELECT * FROM application";
        String[] hdrs = {"Application ID", "Program ID", "Student ID", "Application Date", "Application Status"};
        String[] clms = {"ap_id","adprog_id", "s_id", "ap_date", "ap_status"};
        
        config co = new config();
        co.viewRecords(qry, hdrs, clms);
    }
    
    public void viewRelease(){
        String qry = "Select * From release";
        String[] hdrs = {"Applicant Name", "Program Name", "Release Date", "Amount Release", "Payment Method"}; 
        String[] clms = {"s_fname", "ass_pname", "ds_dd", "ds_ad", "ds_pm"};
   
    config co = new config();
    co.viewRecords(qry, hdrs, clms);
}
    
    
    public void updateProg(){
        Scanner sc = new Scanner(System.in);
        config con = new config();
        
        System.out.print("Enter Program ID to Update: ");
        int id = sc.nextInt();
        
       while((con.getSingleValue("SELECT adprog_id FROM ass_prog WHERE adprog_id = ?", id)) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Enter Program ID again: ");
        id = sc.nextInt();
    }
        
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
        System.out.print("Enter Application ID again: ");
        id = sc.nextInt();
    }

        String sql = "DELETE FROM application WHERE ap_id = ?";
        con.deleteRecord(sql, id);
    }
        
    public void deleteRelease(){
        Scanner sc = new Scanner(System.in);
        config con = new config();
        
        System.out.print("Enter Release ID to Delete: ");
        int id = sc.nextInt();
        
        while((con.getSingleValue("SELECT ds_id FROM release WHERE ds_id = ?", id)) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Enter Release ID again: ");
        id = sc.nextInt();
    }

        String sql = "DELETE FROM release WHERE ds_id = ?";
        con.deleteRecord(sql, id);
    }
    
    public void sForm(){
        Scanner sc = new Scanner(System.in);
        Application ap = new Application();
        AssistanceProgran asp = new AssistanceProgran();
        
        int choice;
        boolean exit = true;
        
        do {
            System.out.println("\n------------ STUDENT CASH ASSISTANCE -------------");
            System.out.println("1. Add Assistance Program");
            System.out.println("2. Student Application");
            System.out.println("3. Add Release");
            System.out.println("4. View Record");
            System.out.println("5. Update Program");
            System.out.println("6. Delete Record");
            System.out.println("7. Exit");
            System.out.println("---------------------------------------------------");
            System.out.print("Enter Choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    asp.assProg();
                    break;
                    
                case 2:
                    System.out.println("\n----- LIST OF STUDENT APPLICATION ------ ");
                    ap.viewApply();
                    asp.Application();
                    break;
                    
                case 3:
                    asp.release();
                    break;
                    
                case 4:
                    System.out.println("\n ----- LIST OF PROGRAM -------");
                    asp.viewProg();
                    System.out.println("\n------ LIST OF APPLICATION -------");
                    asp.viewApp();
                    System.out.println("\n-------- LIST OF RELEASE ---------");
                    asp.viewRelease();
                   
                    break;
                    
                case 5:
                    asp.updateProg();
                    break;
                    
                case 6:
                    System.out.println("\n ----- LIST OF PROGRAM -------");
                    asp.viewProg();
                    System.out.print("Delete Program? (yes/no): ");
                    String res = sc.next();
                    while(res.equalsIgnoreCase("yes")){
                        asp.deleteProg();
                        break;
                    }
                    
                    System.out.println("\n------ LIST OF APPLICATION -------");
                    asp.viewApp();
                    System.out.print("Delete Application (yes/no): ");
                    String res2 = sc.next();
                    while(res2.equalsIgnoreCase("yes")){
                        asp.deleteApp();
                        break;
                    }
                    
                    System.out.println("\n-------- LIST OF RELEASE ---------");
                    asp.viewRelease();
                    System.out.print("Delete Disbursement? (yes/no): ");
                    String res3 = sc.next();
                    while(res3.equalsIgnoreCase("yes")){
                        asp.deleteRelease();
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
