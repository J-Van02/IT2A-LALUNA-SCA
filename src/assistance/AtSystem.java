package assistance;

import java.util.Scanner;

public class AtSystem {
        public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int choice, act;
        boolean exit = true;
do{ 
        System.out.println("\n-------- Select Option -----------");
        System.out.println("1. Application");
        System.out.println("2. Assistance Program");
        System.out.println("3. Exit");
        System.out.println("-------------------------------------");
        System.out.print("Enter Choice: ");
        
        if(sc.hasNextInt()){
            choice = sc.nextInt();
            
            switch(choice){
            case 1:
                Application ap = new Application();
                ap.studentForm();
            break;
            
            case 2:
                AssistanceProgran asp = new AssistanceProgran();
                asp.sForm();
                break;
                
            case 3:
                System.out.print("Do you want to exit? (yes/no): ");
                    String resp = sc.next();
                    if(resp.equalsIgnoreCase("yes")){
                    exit = false;
                    }
                break;
                
                default:
                    System.out.println("Action Error, There's no such number");
            }
            
        }else{
            System.out.println("Invalid input. Please enetr a valid number");
            }
        } while(exit);
        System.out.println("Thank you for using the System :)");
    }
}