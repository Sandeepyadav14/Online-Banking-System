package UseCases;

import java.util.ArrayList;
import java.util.List;
import Bean.Money_transfer;
import java.util.Scanner;

import Bean.Customer;
import Dao.CustomerDao;
import Dao.CustomerDaoImpl;
import Exception.CustomerException;
import Exception.Money_transferException;

public class Main {
	
	public static int login() throws CustomerException {
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
				Scanner sc1=new Scanner(System.in);
				System.out.println("Please Enter The Reciever Name");
				String name=sc1.nextLine();
				System.out.println("Plaese Enter The Reciever Account Number");
				String account=sc1.nextLine();
				System.out.println("Please Enter The Ammount");
				int amount=sc1.nextInt();
				if(amount<=user.getCustomer_balance()) {
					try {
						String msg=cd.MoneyTransfer( name,account, amount, user);
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
			
			case 2:{
				  CustomerDao cdt=new CustomerDaoImpl();
				 List<Money_transfer> list=cdt.Transaction_history();
				 list.forEach(s->System.out.println(s));
			}
			case 3:{
				 int total_amount= cd.Check_balance(user);
				 System.out.println("Total Amount is:"+total_amount);
				 System.out.println("*********************");
			}
			}
		}
	  
		
	}
	
	public static int Accountant() {
		CustomerDao cd=new CustomerDaoImpl();
		Scanner sca=new Scanner(System.in);
		System.out.println("Please Enter The Email ID:");
		String email=sca.nextLine();
		System.out.println("Please Enter The The Password");
		String password=sca.nextLine();
		if(email.equals("sandy@gmail.com") && password.equals("1234")) {
			System.out.println("Log In Succesful....");
			System.out.println("*****************");
			
		
		while(true) {
			Scanner scanner=new Scanner(System.in);
			System.out.println("Enter Your Choice....");
			System.out.println("1).Add new account");
			System.out.println("2).Edit account using account number");
			System.out.println("3).Remove account using account number");
			System.out.println("4).View account details using account number");
			System.out.println("5).View all account details");
			System.out.println("6).Exit");
			int choice=scanner.nextInt();
			if(choice==6) {
				System.out.println("Thank You.....");
				System.exit(1);
			}
			switch(choice) {
			
			case 1:{
				Scanner scanner1=new Scanner(System.in);
				System.out.println("Please Enter Your Name");
				String name=scanner1.nextLine();
				System.out.println("Please Enter Your City");
				String city=scanner1.nextLine();
				System.out.println("Please Enter Mobile");
				String mobile=scanner1.nextLine();
				System.out.println("Please Enter Your Email ID");
				String cus_email=scanner1.nextLine();
				System.out.println("Please Enter The Gender");
				String gender=scanner1.nextLine();
				System.out.println("Please Enter Account_Type");
				String acc_type=scanner1.nextLine();
				System.out.println("Please Enter The Account Number");
				String acc_number=scanner1.nextLine();
				System.out.println("Please Enter Balance ");
				int amount=scanner1.nextInt();
				System.out.println("PLease Enter The Password");
				String cus_password=scanner1.next();
			
				try {
					String msg = cd.insert_customer(name,city,mobile,cus_email,gender,acc_type,acc_number,amount,cus_password);
					System.out.println(msg);
					
				} catch (CustomerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			  }
			case 2:{
				while(true) {
				Scanner d=new Scanner(System.in);
				System.out.println("What detail you want to edit?");
				System.out.println("1). Name update");
				System.out.println("2). Mobile number update");
				System.out.println("3). Email-ID update");
				System.out.println("4). Change password");
				System.out.println("5). Exit");
				int x=d.nextInt();
				if(x==5) {
					System.out.println("Thank You..");
					System.exit(1);
				}
					Scanner c=new Scanner(System.in);
					switch(x) {
					case 1:{
						System.out.println("Enter Account Number");
						String acc_update=c.nextLine();
						System.out.println("Enter The Name ");
						String update_name=c.nextLine();
						String msg;
						try {
							msg = cd.customer_Update_Name(update_name,acc_update);
							System.out.println(msg);
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					case 2:{
						System.out.println("Enter Account Number");
						String acc_number=c.nextLine();
						System.out.println("Enter The Mobile Number");
						String update_mobile=c.nextLine();
						String msg_mobile;
						try {
							msg_mobile = cd.customer_Update_Mobie(update_mobile,acc_number);
							System.out.println(msg_mobile);
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					case 3:{
						System.out.println("Enter Account Number");
						String acc_number=c.nextLine();
						System.out.println("Enter new E-mail Id");
						String update_email=c.nextLine();
						String msg_mobile;
						try {
							msg_mobile = cd.customer_Update_Email(update_email,acc_number);
							System.out.println(msg_mobile);
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						

					}
					case 4:{
						System.out.println("Enter Account Number");
						String acc_number=c.nextLine();
						System.out.println("Enter new Password");
						String update_password=c.nextLine();
						String msg_password;
						try {
							msg_password = cd.customer_Update_Password(update_password,acc_number);
							System.out.println(msg_password);
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				   }
				
				}
			}
			case 3:{
				Scanner c=new Scanner(System.in);
				System.out.println("Enter Account Number");
				String acc_number=c.nextLine();
				String msg_delete;
				try {
					msg_delete = cd.Delete_Account(acc_number);
					System.out.println(msg_delete);
				} catch (CustomerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			case 4:{
				Scanner c=new Scanner(System.in);
				System.out.println("Enter Account Number");
				String acc_number=c.nextLine();
				try {
		
				Customer customers=cd.View_Account(acc_number);
				if(customers==null) {
					System.out.println("nulll");
				}
				System.out.println(customers);
				
				} catch (CustomerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			case 5:{
			   List<Customer> customer=new ArrayList<>();
			   try {
				customer=cd.View_All_Account();
				customer.forEach(s->System.out.println(s));
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 }
		   }
		}
		}
		else {
			System.out.println("Email-Id or Password is Wrong...");
			System.out.println("****************************");
		}
		return 0;
		
	}
	public static void main (String[] args) throws CustomerException {
		
		 Scanner s=new Scanner(System.in);
		
		boolean query=true;
		while(query) {
			 System.out.println("Enter Your Choice");
			 
				System.out.println("1) Log In As Customer");
				System.out.println("2) Log In As Accountant");
				System.out.println("3) Exit");
				
				int choice =s.nextInt();
				
				if(choice==3) {
					System.out.println("Thank You:");
					System.exit(1);
					
				}
				switch(choice) {
				   case 1: {
					     
					   System.out.println("Login as customer");
					   login();
					   break;
				   }
				   case 2:{
					   System.out.print("Log in as Accountant:");
					   Accountant();
					   break;
						
				   }
		     }
		}
		
	}

}
