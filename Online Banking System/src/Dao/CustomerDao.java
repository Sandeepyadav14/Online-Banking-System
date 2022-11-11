package Dao;

import Bean.Customer;
import Exception.CustomerException;
import Exception.Money_transferException;

public interface CustomerDao {
	
   public  Customer loginCustomer( String email,String password);
   public String MoneyTransfer (String account_number,int amount,Customer customer) throws CustomerException, Money_transferException;
}
