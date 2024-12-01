package motorcycle.system.management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Reports {
    public void showReports() {
        Scanner sc = new Scanner(System.in); 

        boolean continueReports = true;

        while (continueReports) {
            System.out.println("            /------------------------------------------------------/");
            System.out.println("           /                 REPORTS PANEL                        /");
            System.out.println("          / -----------------------------------------------------/");
            System.out.println("         /  1. VIEW CUSTOMER TRANSACTIONS                       /");
            System.out.println("        /   2. VIEW INDIVIDUAL CUSTOMER TRANSACTION            /");
            System.out.println("       /    3. VIEW FULL CUSTOMER TRANSACTION                 /");
            System.out.println("      /     4. EXIT                                          /");
            System.out.println("     /------------------------------------------------------/");
            System.out.print("Enter Selection: ");
            
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next(); 
            }
            
            int selection = sc.nextInt(); 

            switch (selection) {
                case 1:
                    viewCustomerTransactions();
                    break;
                case 2:
                    viewIndividualCustomerTransaction();
                    break;
                case 3:
                    viewFullTransactionReport();
                    break;
                case 4:
                     System.out.println("Returning to Main Menu...");
                    continueReports = false;  
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
                    
                    
            }
        }
        sc.close(); 
    }

    private void viewCustomerTransactions() {
        String qry = "SELECT c.c_id, c.c_fname, c.c_lname, o.o_id, o.o_due, o.o_date " +
                      "FROM tbl_customer AS c " +
                      "INNER JOIN tbl_order AS o ON c.c_id = o.c_id";
        String[] hrds = {"Customer ID", "First Name", "Last Name", "Order ID", "Due Amount", "Order Date"};
        String[] clms = {"c_id", "c_fname", "c_lname", "o_id", "o_due", "o_date"};
        config conf = new config();
        conf.viewRecords(qry, hrds, clms);
    }

        private void viewIndividualCustomerTransaction() {
        Scanner sc = new Scanner(System.in);
        int customerId = 0;

        while (true) {
            System.out.print("Enter Customer ID to view transactions: ");
            if (sc.hasNextInt()) {
                customerId = sc.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer ID.");
                sc.next();
            }
        }

        String qry = "SELECT c.c_id, c.c_fname, c.c_lname, o.o_id, o.o_due, o.o_date " +
                     "FROM tbl_customer AS c " +
                     "INNER JOIN tbl_order AS o ON c.c_id = o.c_id " +
                     "WHERE c.c_id = ?";
        String[] hrds = {"Customer ID", "First Name", "Last Name", "Order ID", "Due Amount", "Order Date"};
        String[] clms = {"c_id", "c_fname", "c_lname", "o_id", "o_due", "o_date"};
        config conf = new config();
        
        conf.viewRecordsWithParameter(qry, hrds, clms, customerId);
    }

    private void viewFullTransactionReport() {
        String qry = "SELECT c.c_id, c.c_fname, c.c_lname, m.m_brand, o.o_id, o.o_due, o.o_date, o.o_status " +
                     "FROM tbl_customer AS c " +
                     "INNER JOIN tbl_order AS o ON c.c_id = o.c_id " +
                     "INNER JOIN tbl_motorcycle AS m ON o.m_id = m.m_id";

        String[] hrds = {"Customer ID", "First Name", "Last Name", "Motorcycle Brand", "Order ID", "Due Amount", "Order Date", "Status"};
        String[] clms = {"c_id", "c_fname", "c_lname", "m_brand", "o_id", "o_due", "o_date", "o_status"};

        config conf = new config();

        System.out.println("\n======================== FULL TRANSACTION REPORT ============================================================================");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

        for (String header : hrds) {
            System.out.printf("%-15s ", header);
        }
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

        ResultSet rs = conf.executeQuery(qry);
        try {
            while (rs.next()) {
                for (String colName : clms) {
                    String value = rs.getString(colName);
                    System.out.printf("%-15s ", value != null ? value : "N/A");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving records: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close(); 
            } catch (SQLException e) {
                System.out.println("Error closing ResultSet: " + e.getMessage());
            }
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("============================ END OF REPORT ==================================================================================");
    }
}