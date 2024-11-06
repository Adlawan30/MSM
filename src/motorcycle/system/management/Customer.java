
package motorcycle.system.management;
import java.util.Scanner;

public class Customer {
    
    public void cTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
        
        System.out.println("\n---------------------");
        System.out.println("CUSTOMER PANEL");
        System.out.println("1. ADD CUSTOMER");
        System.out.println("2. VIEW CUSTOMER");
        System.out.println("3. UPDATE CUSTOMER");
        System.out.println("4. DELETE CUSTOMER");
        System.out.println("5. EXIT");
        
        System.out.print("Enter Selection: ");
        int act = sc.nextInt();
        Customer cs = new Customer ();
        switch (act){
            case 1:
                cs.addCustomer();
                cs.viewCustomer();
                break;
                
            case 2:
                cs.viewCustomer();
                break;
                
            case 3:
                cs.viewCustomer();
                cs.updateCustomer();
                cs.viewCustomer();
                break;
            case 4:
                cs.viewCustomer();
                cs.deleteCustomer();
                cs.viewCustomer();
                break;
            case 5:
                
                break;
            }   
            System.out.println("Do you want to continue? (yes/no):");
            response = sc.next();
        
        }while(response.equalsIgnoreCase("yes"));       
        
    
    }
    public void addCustomer (){
        
        Scanner sc = new Scanner (System.in);
        
        System.out.print("Customer First Name: ");
        String fname = sc.next();
        System.out.print("Customer Last Name:" );
        String lname = sc.next();
        System.out.print("Cutomer Email: ");
        String email = sc.next();
        System.out.print("Customer Status: ");
        String status = sc.next();
        
        String qry = "INSERT INTO tbl_customer(c_fname, c_lname, c_email, c_status) VALUES (?,?,?,?)";
        config conf = new config();
        conf.addRecord(qry, fname, lname, email, status);
        
    
    }
    public void viewCustomer(){
    
     String qry = "SELECT * FROM tbl_customer";
     String[] hrds = {"ID","First name","Lastname","Email","Status"};
     String[] clms = {"c_id","c_fname","c_lname","c_email","c_status"};
     config conf = new config(); 
     conf.viewRecords(qry,hrds,clms);
    
             
    }
    private void updateCustomer(){
        
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.println("Enter ID to Update");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id=?",id)==0){
            System.out.println("SELECTED ID doesn't exist!");
            System.out.println("SELECTED Customer ID Again");
            id = sc.nextInt();
        }
        
        
        System.out.print("New Customer First Name: ");
        String fname=sc.next();
        System.out.print("New Customer Last Name:" );
        String lname=sc.next();
        System.out.print("New Cutomer Email: ");
        String email=sc.next();
        System.out.print("New Customer Status: ");
        String status=sc.next();
   
        String qry = "UPDATE tbl_customer SET c_fname=?,c_lname=?,c_email=?,c_status=? WHERE c_id = ?";
        
        conf.updateRecord(qry, fname,lname,email,status,id);
    
    }
    private void deleteCustomer(){
        
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id=?",id)==0){
            System.out.println("Selected ID doesn't exist");
            System.out.print("Selected Customer ID Again: ");
            id = sc.nextInt();
        }
 
        String qry ="DELETE FROM tbl_customer WHERE c_id=?";
 
        conf.deleteRecord(qry,id);  
    }
            
  }
 
