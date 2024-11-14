package motorcycle.system.management;

import java.util.Scanner;

public class Motorcycle {

    public void mTransaction() {
    Scanner sc = new Scanner(System.in);
    String response;

    do {
        System.out.println("             /------------------------------------------------------/");
        System.out.println("            /                 MOTORCYCLE PANEL                     /");
        System.out.println("           / -----------------------------------------------------/");
        System.out.println("          /  1. ADD MOTORCYCLE                                   /");
        System.out.println("         /   2. VIEW MOTORCYCLE                                 /");
        System.out.println("        /    3. UPDATE MOTORCYCLE                              /");
        System.out.println("       /     4. DELETE MOTORCYCLE                             /");
        System.out.println("      /      5. EXIT                                         /");
        System.out.println("     /------------------------------------------------------/");
        System.out.print("Enter Selection: ");

        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            sc.next(); 
        }

        int act = sc.nextInt();
        Motorcycle mt = new Motorcycle();

        switch (act) {
            case 1:
                mt.addMotorcycle();
                mt.viewMotorcycle();
                break;

            case 2:
                mt.viewMotorcycle();
                break;

            case 3:
                mt.viewMotorcycle();
                mt.updateMotorcycle();
                mt.viewMotorcycle();
                break;

            case 4:
                mt.viewMotorcycle();
                mt.deleteMotorcycle();
                mt.viewMotorcycle();
                break;

            case 5:
                break;

            default:
                System.out.println("Invalid selection. Please try again.");
        }

        System.out.print("Do you want to continue? (yes to cancel / no to go back to main menu): ");
        response = sc.next();

    } while (response.equalsIgnoreCase("yes"));
    
    sc.close(); 
}

    public void addMotorcycle() {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Motorcycle Brand: ");
        String mbrand = sc.next();
        System.out.print("Engine Size: ");
        int enginesize = sc.nextInt();
        System.out.print("Type: ");
        String mtype = sc.next();
        System.out.print("Price: ");
        double mprice = sc.nextDouble();
        System.out.print("Stocks: ");
        double stocks = sc.nextDouble();
        System.out.print("Status: ");
        String mstatus = sc.next();
        
        String qry = "INSERT INTO tbl_motorcycle (m_brand, m_enginesize, m_type, m_price, m_stocks, m_status) VALUES (?,?,?,?,?,?)";
        config conf = new config();
        conf.addRecord(qry, mbrand, enginesize, mtype, mprice, stocks, mstatus);
    }

    public void viewMotorcycle() {
        String qry = "SELECT * FROM tbl_motorcycle";
        String[] hrds = {"ID","Brand","Engine size","Type","Price","Stocks","Status"};
        String[] clms = {"m_id","m_brand","m_enginesize","m_type","m_price","m_stocks","m_status"};
        config conf = new config(); 
        conf.viewRecords(qry, hrds, clms);
    }

    private void updateMotorcycle() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id = 0;

        while (true) {
            System.out.print("Enter ID to Update: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                if (conf.getSingleValue("SELECT m_id FROM tbl_motorcycle WHERE m_id=?", id) != 0) {
                    break;
                } else {
                    System.out.println("Selected ID doesn't exist! Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer ID.");
                sc.next();
            }
        }

        System.out.print("New Motorcycle Brand: ");
        String mbrand = sc.next();
        System.out.print("New Motorcycle Engine Size: ");
        int enginesize = sc.nextInt();
        System.out.print("New Motorcycle Type: ");
        String mtype = sc.next();
        System.out.print("New Motorcycle Price: ");
        double mprice = sc.nextDouble();
        System.out.print("New Motorcycle Stocks: ");
        double mstocks = sc.nextDouble();
        System.out.print("New Motorcycle Status: ");
        String mstatus = sc.next();

        String qry = "UPDATE tbl_motorcycle SET m_brand=?, m_enginesize=?, m_type=?, m_price=?, m_stocks=?, m_status=? WHERE m_id = ?";
        conf.updateRecord(qry, mbrand, enginesize, mtype, mprice, mstocks, mstatus, id);
    }

    private void deleteMotorcycle() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id = 0;

        while (true) {
            System.out.print("Enter ID to delete: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                if (conf.getSingleValue("SELECT m_id FROM tbl_motorcycle WHERE m_id=?", id) != 0) {
                    break;
                } else {
                    System.out.println("Selected ID doesn't exist! Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer ID.");
                sc.next();
            }
        }

        String qry = "DELETE FROM tbl_motorcycle WHERE m_id=?";
        conf.deleteRecord(qry, id);
    }
}