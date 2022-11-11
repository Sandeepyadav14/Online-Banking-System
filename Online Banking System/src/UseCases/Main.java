package UseCases;

import java.util.Scanner;

import Bean.Customer;
import Dao.CustomerDao;
import Dao.CustomerDaoImpl;
import Exception.CustomerException;
import Exception.Money_transferException;

public class Main {
	public static int login() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Your Email Address:");
		String email=sc.nextLine();
		System.out.println("Enter Your Password:");
		String password=sc.nextLine();
		  
		CustomerDao cd=new CustomerDaoImpl();
		Customer user=cd.loginCustomer(email, password);
		if(user==null) {
			System.out.println("*******************************************");
			System.out.println("Invalid Password OR Email.");
			System.out.println("*******************************************");
			return 0;
		}
		while(true) {
			System.out.println("Please select option from below");
			System.out.println("1).Transfer money to other account");
			System.out.println("2).Transaction history");
			System.out.println("3).Check Balance");
			System.out.println("4).Exit");
			int choice=sc.nextInt();
			if(choice==4) {
				System.out.println("Thank You");
				
			}
			switch(choice) {
			case 1:{
				System.out.println("Plaese Enter The Reciever Account Number");
				String account=sc.next();
				System.out.println("Please Enter The Ammount");
				int amount=sc.nextInt();
//				System.out.println("Please Enter The Reciever Name");
//				String name=sc.next();
				
				
				if(amount<=user.getCustomer_balance()) {
					try {
						String msg=cd.MoneyTransfer( account, amount, user);
						System.out.println(msg);
						
					} catch (CustomerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						e.getMessage();
					} catch (Money_transferException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						e.getMessage();
					}
					
				}else {
					System.out.println("You dont't Have No Suffiencient Balance");
					System.out.println("Thank You");
				}
			}
			}
		}
	  
		
	}
	public static void main (String[] args) {
		
		 Scanner s=new Scanner(System.in);
		
		boolean query=true;
		while(query) {
			 System.out.println("Enter Your Choice");
			 
				System.out.println("1) Log In As Customer");
				System.out.println("2) Log In As Accountant");
				System.out.println("3) New Customer");
				System.out.println("4) Exit");
				
				int choice =s.nextInt();
				
				if(choice==4) {
					System.out.println("Thank You:");
					System.exit(1);
					
				}
				switch(choice) {
				   case 1: {
					     
					   System.out.println("Login as customer");
					   login();
					   break;
				   }
		     }
		}
		
	}

}
