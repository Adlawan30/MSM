
package motorcycle.system.management;

import java.util.Scanner;

public class MotorcycleSystemManagement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        do {
            System.out.println("\n======================== WELCOME TO MY SYSTEM ========================");
            System.out.println("                                                                    ");
            System.out.println("1. CUSTOMER");
            System.out.println("2. MOTORCYCLE");
            System.out.println("3. ORDER");
            System.out.println("4. REPORTS");
            System.out.println("5. EXIT");
            System.out.println("----------------------------------------------------------------------");
            System.out.print("Enter Action: ");

            int action = -1;
            
            while (true) {
                try {
                    action = Integer.parseInt(sc.nextLine());
                    if (action < 1 || action > 5) {
                        throw new NumberFormatException(); 
                    }
                    break; 
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a number between 1 and 5: ");
                }
            }

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
                    Reports report = new Reports();
                    report.showReports();
                    break;
                case 5:
                    System.out.println("Exit Selected.... Type 'yes' to confirm and exit.");
                    String resp = sc.nextLine();
                    if (resp.equalsIgnoreCase("yes")) {
                        exit = false;
                    }
                    break;
            }

        } while (exit);
        
        System.out.println("Thank you for using the system! Goodbye!");
        sc.close();
    }
}
