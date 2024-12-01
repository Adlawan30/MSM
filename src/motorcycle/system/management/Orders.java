package motorcycle.system.management;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Orders {

    public void oTransaction() {
    Scanner sc = new Scanner(System.in);
    String response;

    do {
        System.out.println("             /------------------------------------------------------/");
        System.out.println("            /                     ORDER PANEL                      /");
        System.out.println("           / -----------------------------------------------------/");
        System.out.println("          /  1. ADD ORDER                                        /");
        System.out.println("         /   2. VIEW ORDER                                      /");
        System.out.println("        /    3. UPDATE ORDER                                   /");
        System.out.println("       /     4. DELETE ORDER                                  /");
        System.out.println("      /      5. EXIT                                         /");
        System.out.println("     /------------------------------------------------------/");
        System.out.print("Enter Selection: ");

        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            sc.next(); 
        }

        int act = sc.nextInt();
        Orders od = new Orders();

        switch (act) {
            case 1:
                od.addOrder();
                od.viewOrders();
                break;
            case 2:
                od.viewOrders();
                break;
            case 3:
                od.viewOrders();
                od.updateOrder();
                od.viewOrders();
                break;
            case 4:
                od.viewOrders();
                od.deleteOrder();
                od.viewOrders();
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
        }

         System.out.println("Do you want to continue? (yes/no):");
            response = sc.next();
            
                         while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
        response = sc.next();
        
 }
        
        }while(response.equalsIgnoreCase("yes"));    
}

    public void addOrder() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        Customer cs = new Customer();
        cs.viewCustomer();
        
        System.out.print("Enter the ID of the selected Customer: ");
        int cid = sc.nextInt();
        
        String csql = "SELECT c_id from tbl_customer WHERE c_id=?";
        while(conf.getSingleValue(csql, cid) == 0) {
            System.out.print("Customer does not exist, Please select again: ");
            cid = sc.nextInt();
        }
        
        Motorcycle mt = new Motorcycle();
        mt.viewMotorcycle();
        System.out.print("Enter the ID of the selected Motorcycle: ");
        int mid = sc.nextInt();
        
        String msql = "SELECT m_id from tbl_motorcycle WHERE m_id=?";
        while(conf.getSingleValue(msql, mid) == 0) {
            System.out.print("Motorcycle does not exist, Please select again: ");
            mid = sc.nextInt();
        }
        
        System.out.print("Enter Quantity: ");
        double quantity = 0; 

        while (true) {
        if (sc.hasNextDouble()) { 
        quantity = sc.nextDouble(); 
        if (quantity > 0) {
            break; 
        } else {
            System.out.println("Invalid quantity. Please enter a valid quantity.");
        }
         } else {
        System.out.println("Invalid input. Please enter a numeric value.");
        sc.next(); 
    }
}
        
        
        
        String priceqry = "SELECT m_price FROM tbl_motorcycle WHERE m_id=?";
        double price = conf.getSingleValue(priceqry, mid);
        double due = price * quantity;
        
        System.out.println("------------------------------------");
        System.out.println("Total Due: " + due);
        System.out.println("------------------------------------");
        
        System.out.print("Enter the Received Cash: ");
        double rcash = sc.nextDouble();
        
        while (rcash < due) {
            System.out.println("Invalid amount, please try again: ");
            rcash = sc.nextDouble();
        }

        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        
        String status = "Approve";
        String orderqry = "INSERT INTO tbl_order (c_id, m_id, o_quantity, o_due, o_rcash, o_date, o_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        conf.addRecord(orderqry, cid, mid, quantity, due, rcash, date, status);
    }

    public void viewOrders() {
        String qry = "SELECT o_id, c_lname, m_brand, o_due, o_date, o_status FROM tbl_order " 
                + " LEFT JOIN tbl_customer ON tbl_customer.c_id = tbl_order.c_id"
                + " LEFT JOIN tbl_motorcycle ON tbl_motorcycle.m_id = tbl_order.m_id";
        String[] hrds = {"OID", "Customer", "Motorcycle", "Due", "Date", "Status"};
        String[] clms = {"o_id", "c_lname", "m_brand", "o_due", "o_date", "o_status"};
        config conf = new config(); 
        conf.viewRecords(qry, hrds, clms);
    }

    private void updateOrder() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int orderId = 0;

        while (true) {
            System.out.print("Enter Order ID to Update: ");
            if (sc.hasNextInt()) {
                orderId = sc.nextInt();
                if (conf.getSingleValue("SELECT o_id FROM tbl_order WHERE o_id=?", orderId) != 0) {
                    break;
                } else {
                    System.out.println("Selected ID doesn't exist!");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer ID.");
                sc.next();
            }
        }

        System.out.print("Enter New Quantity: ");
        double quantity = sc.nextDouble();
        
        System.out.print("Enter New Received Cash: ");
        double rcash = sc.nextDouble();
        
        String qry = "UPDATE tbl_order SET o_quantity=?, o_rcash=? WHERE o_id=?";
        conf.updateRecord(qry, quantity, rcash, orderId);
        
        System.out.println("Order updated successfully.");
    }

    private void deleteOrder() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int orderId = 0;

        while (true) {
            System.out.print("Enter Order ID to Delete: ");
            if (sc.hasNextInt()) {
                orderId = sc.nextInt();
                if (conf.getSingleValue("SELECT o_id FROM tbl_order WHERE o_id=?", orderId) != 0) {
                    break;
                } else {
                    System.out.println("Selected ID doesn't exist!");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer ID.");
                sc.next();
            }
        }

        String qry = "DELETE FROM tbl_order WHERE o_id=?";
        conf.deleteRecord(qry, orderId);
        
        System.out.println("Order deleted successfully.");
    }
}