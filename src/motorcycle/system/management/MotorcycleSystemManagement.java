
package motorcycle.system.management;

import java.util.Scanner;

public class MotorcycleSystemManagement {
 
    public static void main(String[] args) {
        
     
     Scanner sc = new Scanner (System.in);
     boolean exit = true;
     do{
        System.out.println("WELCOME TO MY SYSTEM");
        System.out.println("");
        System.out.println("1. CUSTOMER");
        System.out.println("2. MOTORCYCLE");
        System.out.println("3. ORDER");
        System.out.println("4. REPORTS");
        System.out.println("5. EXIT");
        
        System.out.print("Enter Action: ");
        int action = sc.nextInt(); 
        
        switch(action){
        
            case 1:
                Customer cs = new Customer();
                cs.cTransaction();
                break;
            case 2:
                Motorcycle mt = new Motorcycle ();
                mt.mTransaction();
                break;
            case 3:
                Orders od = new Orders ();
                od.oTransaction();
                break;
            case 5:
                System.out.println("Exit Selected.... 'yes' to continue");
                String resp = sc.next();
                if(resp.equalsIgnoreCase("yes")){
                exit=false;
                }
                break;
        
        }
       
     }while(exit);
    }
    
}
