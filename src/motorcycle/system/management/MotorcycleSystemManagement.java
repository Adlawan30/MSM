package motorcycle.system.management;

import java.util.Scanner;

public class MotorcycleSystemManagement {
 
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);  
        boolean exit = true;  
        
        do {
           
            System.out.println("WELCOME TO MY SYSTEM");
            System.out.println("");
            System.out.println("1. CUSTOMER");
            System.out.println("2. MOTORCYCLE");
            System.out.println("3. ORDER");
            System.out.println("4. REPORTS");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            
            
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number (1-5).");
                sc.next(); 
            }
            
            int action = sc.nextInt(); 

            
            switch (action) {
                case 1:
                 
                    Customer cs = new Customer();
                    cs.cTransaction();
                    break;
                case 2:
                  
                    Motorcycle mt = new Motorcycle();
                    mt.mTransaction();
                    break;
                case 3:
                   
                    Orders od = new Orders();
                    od.oTransaction();
                    break;
                case 4:
                  
                    Reports reports = new Reports();
                    reports.showReports();
                    break;
                case 5:
                    
                    System.out.println("Exit Selected.... 'yes' to confirm exit, any other key to return.");
                    String resp = sc.next(); 
                    if (resp.equalsIgnoreCase("yes")) {
                        exit = true;  
                    }
                    break;
                default:
                    
                    System.out.println("Invalid selection. Please enter a number between 1 and 5.");
                    break;
            }
        } while (!exit);  
        
        System.out.println("System has exited. Goodbye!");
        sc.close(); 
    }
}