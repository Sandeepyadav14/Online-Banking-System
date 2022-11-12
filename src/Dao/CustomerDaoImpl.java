package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Bean.Customer;
import Bean.Money_transfer;
import Exception.CustomerException;
import Exception.Money_transferException;
import Util.Demo;
public class CustomerDaoImpl implements CustomerDao {

	@Override
	public Customer loginCustomer(String email, String password)throws CustomerException {
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
			throw new CustomerException();
		}
		return customer;
	}

	@Override
	public String MoneyTransfer(String name,String account_number, int amount, Customer customer) throws CustomerException , Money_transferException {
	  String message="Transsection Failed";
	  try(Connection conn=Demo.GetUtilConnection()){
		  PreparedStatement ps=conn.prepareStatement("update customer set balance=balance+? where name=? AND Account_Number=?");
		  ps.setInt(1, amount);
		  ps.setString(2, name);
		  ps.setString(3, account_number);
		  
		  int x=ps.executeUpdate();
		  if(x>0) {
			  message="Money Transfer Successful";
			  try(Connection conn4=Demo.GetUtilConnection()){
				  PreparedStatement ps4=conn4.prepareStatement("update customer set balance=balance-? where Account_Number=?");
				  ps4.setInt(1, amount);
				 
				  ps4.setString(2,customer.getAccount_number());
				  int z=ps4.executeUpdate();
			  }catch(SQLException e) {
				  e.printStackTrace();
			  }		 		  
			  try (Connection con=Demo.GetUtilConnection()){
				  PreparedStatement ps1=con.prepareStatement("insert into transfer_history values(?,?,?,?,?)");
				  int id=customer.getCustomer_id();
				  String reciever_name=name;
				  String reciever_account_number=account_number;
				  int total_amount=amount;
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

	@Override
	public List<Money_transfer> Transaction_history()throws CustomerException {
		List<Money_transfer> list=new ArrayList<>();
		Money_transfer mt=null;
		
		try(Connection conn=Demo.GetUtilConnection()){
			PreparedStatement ps=conn.prepareStatement("select * from transfer_history");
		  ResultSet rs=ps.executeQuery();
		  while(rs.next()) {
			  int id=rs.getInt("cus_id");
			  String name=rs.getString("Reciever_Name");
			  String re_acc_number=rs.getString("Reciever_account_number");
			  int rec_amount=rs.getInt("Amount");
			  Date rec_date=rs.getDate("curr_date");
			  mt=new Money_transfer(id,name,re_acc_number,rec_amount,(java.sql.Date) rec_date);
			  list.add(mt);
			  
		  }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw  new CustomerException();
		}
		return list;
		
	}

	@Override
	public int Check_balance(Customer customer) {
    
		int amount_check=customer.getCustomer_balance();
		return amount_check;
	}
	public String insert_customer(String name,String city,String mobile,String cus_email,String gender,String acc_type,String acc_number,int amount,String cus_password) throws CustomerException {
		String message="Data is not inserted";
		try(Connection conn=Demo.GetUtilConnection()){
			PreparedStatement ps=conn.prepareStatement("insert into customer (name,city,mobile,email,gender,account_type,account_number,balance,password)values(?,?,?,?,?,?,?,?,?)");
	        ps.setString(1,name);
	        ps.setString(2,city);
	        ps.setString(3,mobile);
	        ps.setString(4,cus_email);
	        ps.setString(5,gender);
	        ps.setString(6,acc_type);
	        ps.setString(7,acc_number);
	        ps.setInt(8,amount);
	        ps.setString(9,cus_password);
	        int x=ps.executeUpdate();
	        if(x>0) {
	        	message="Data Is Inserted....";
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CustomerException();
		}
		 return message;

	}

	@Override
	public String customer_Update_Name(String update_name,String acc_update)throws CustomerException {
		String msg="Name is not updated...";
		try(Connection conn=Demo.GetUtilConnection()){
			PreparedStatement ps=conn.prepareStatement("update customer name set name=? where  Account_Number=? ");
			ps.setString(1,update_name);
			ps.setString(2,acc_update);
			int x=ps.executeUpdate();
			if(x>0) {
				msg="Name is Updated";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CustomerException();
		}
		return msg;
	}

	@Override
	public String customer_Update_Mobie(String update_mobile, String acc_number) throws CustomerException {
		String message="Mobile Number is not updated...";
		try(Connection conn=Demo.GetUtilConnection()){
			PreparedStatement ps=conn.prepareStatement("update customer name set mobile=? where  Account_Number=?");
			ps.setString(1,update_mobile);
			ps.setString(2,acc_number);
			int x=ps.executeUpdate();
			if(x>0) {
				message="Mobile is Updated";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	@Override
	public String customer_Update_Email(String update_Email, String acc_number) throws CustomerException {
		String message="Email is not updated...";
		try(Connection conn=Demo.GetUtilConnection()){
			PreparedStatement ps=conn.prepareStatement("update customer name set email=? where  Account_Number=?");
			ps.setString(1,update_Email);
			ps.setString(2,acc_number);
			int x=ps.executeUpdate();
			if(x>0) {
				message="Email is Updated";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String customer_Update_Password(String update_password, String acc_number) throws CustomerException {
		String message="Password is not Changed...";
		try(Connection conn=Demo.GetUtilConnection()){
			PreparedStatement ps=conn.prepareStatement("update customer name set password=? where  Account_Number=?");
			ps.setString(1,update_password);
			ps.setString(2,acc_number);
			int x=ps.executeUpdate();
			if(x>0) {
				message="Password is Updated";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	

	@Override
	public String Delete_Account(String acc_number) throws CustomerException {
		String message="Account is not Deleted...";
		try(Connection conn=Demo.GetUtilConnection()){
			PreparedStatement ps=conn.prepareStatement("delete from customer where Account_Number=?");
			ps.setString(1,acc_number);
			int x=ps.executeUpdate();
			if(x>0) {
				message="Account is deleted...";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public Customer View_Account(String acc_number) throws CustomerException {
		
		Customer customer=null;
		try(Connection conn=Demo.GetUtilConnection()){
			PreparedStatement ps=conn.prepareStatement("select * from customer where Account_Number=?");
			ps.setString(1, acc_number);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("Id");
				String name=rs.getString("name");
				String city=rs.getString("city");
				String mobile=rs.getString("Mobile");
				String email=rs.getString("Email");
				String gender=rs.getString("gender");
				String account_type=rs.getString("Account_type");
				String account_number=rs.getString("Account_number");
				int balance=rs.getInt("balance");
				String password=rs.getString("password");
				customer=new Customer(id,name,city,mobile,email,gender,account_type,account_number,balance,password);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CustomerException();
		}
		return customer;
	}

	@Override
	public List<Customer> View_All_Account() throws CustomerException {
		List<Customer> customer=new ArrayList<>();
		Customer c=null;
		try(Connection conn=Demo.GetUtilConnection()){
			PreparedStatement ps=conn.prepareStatement("select * from customer ");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("Id");
				String name=rs.getString("name");
				String city=rs.getString("city");
				String mobile=rs.getString("Mobile");
				String email=rs.getString("Email");
				String gender=rs.getString("gender");
				String account_type=rs.getString("Account_type");
				String account_number=rs.getString("Account_number");
				int balance=rs.getInt("balance");
				String password=rs.getString("password");
				c=new Customer(id,name,city,mobile,email,gender,account_type,account_number,balance,password);
				customer.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CustomerException();
		}
		return customer;
	}
	
	
}
