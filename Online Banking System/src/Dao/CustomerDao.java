package Dao;

import java.util.List;
import Bean.Money_transfer;

import Bean.Customer;
import Exception.CustomerException;
import Exception.Money_transferException;

public interface CustomerDao {
	
   public  Customer loginCustomer( String email,String password)throws CustomerException;
   public String MoneyTransfer (String name,String account_number,int amount,Customer customer) throws CustomerException, Money_transferException;
   public List<Money_transfer> Transaction_history()throws CustomerException;
   public int Check_balance(Customer customer)throws CustomerException;
   public String insert_customer(String name,String city,String mobile,String cus_email,String gender,String acc_type,String acc_number,int amount,String cus_password)throws CustomerException;
   public String customer_Update_Name(String update_name,String acc_update) throws CustomerException;
   public String customer_Update_Mobie(String update_mobile,String acc_number)throws CustomerException;
   public String customer_Update_Email(String update_Email,String acc_number)throws CustomerException;
   public String customer_Update_Password(String update_Password,String acc_number)throws CustomerException;
   public String Delete_Account(String acc_number)throws CustomerException;
   public Customer View_Account(String acc_number)throws CustomerException;
   public List<Customer> View_All_Account()throws CustomerException;


}
