package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Bean.Customer;
import Exception.CustomerException;
import Exception.Money_transferException;
import Util.Demo;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public Customer loginCustomer(String email, String password) {
		Customer customer=null;
		
		try(Connection conn=Demo.GetUtilConnection()){
			PreparedStatement ps=conn.prepareStatement("select * from customer where email=? AND password=?");
			ps.setString(1, email);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String city=rs.getString("city");
				String mobile=rs.getString("Mobile");
				String email_id=rs.getString("Email");
				String gender =rs.getString("Gender");
				String accounttype =rs.getString("Account_type");
				String account_number=rs.getString("Account_Number");
				int balance =rs.getInt("Balance");
				String password_c =rs.getString("Password");
				

              customer =new Customer(id,name,city,mobile,email_id,gender,accounttype,account_number, balance, password_c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public String MoneyTransfer(String account_number, int amount, Customer customer) throws CustomerException , Money_transferException {
	  String message="Transsection Failed";
	  try(Connection conn=Demo.GetUtilConnection()){
		  PreparedStatement ps=conn.prepareStatement("update customer set balance=balance+? where Account_Number=?");
		  ps.setInt(1, amount);
		 
		  ps.setString(2, account_number);
		  
		  int x=ps.executeUpdate();
//		  int f= customer.getCustomer_balanc/e()-amount;
//		  customer.setCustomer_balance(f);
		  if(x>0) {
			  message="Money Transfer Successful";
			  try(Connection conn4=Demo.GetUtilConnection()){
				  PreparedStatement ps4=conn4.prepareStatement("update customer set balance=balance-? where Account_Number=?");
				  ps4.setInt(1, amount);
				 
				  ps4.setString(2,customer.getAccount_number());
				  System.out.println(customer.getAccount_number());
				  int z=ps4.executeUpdate();
				  if(z>0) {
					  System.out.print("hiii0");
				  }
			  }catch(SQLException e) {
				  e.printStackTrace();
			  }
////			 		  
			  try (Connection con=Demo.GetUtilConnection()){
				  PreparedStatement ps1=con.prepareStatement("insert into transfer_history values(?,?,?,?,?)");
				  int id=customer.getCustomer_id();
				  String reciever_name="sandy";
				  String reciever_account_number=account_number;
				  int total_amount=amount;
//				  customer.setCustomer_balance(customer.getCustomer_balance()-amount);
				  LocalDate date = LocalDate.now();
				  String curr_date = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

				  
				  ps1.setInt(1, id);
				  ps1.setString(2, reciever_name);
				  ps1.setString(3,reciever_account_number);
				  ps1.setInt(4, amount);
				  ps1.setString(5, curr_date);
				  int a=ps1.executeUpdate();
				  
			  }catch(SQLException e) {
				   e.printStackTrace();
				   throw new Money_transferException();
			  }
		  }
		  
	  } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new CustomerException();
	}
	  return message;
	}
	
	
}