package Bean;

public class Customer {
  private int customer_id;
  private String customer_name;
  private String customer_city;
  private String customer_mobile;
  private String customer_email;
  private String customer_gender;
  private String Account_type;
  private String Account_number;
  private int customer_balance;
  private String customer_password;

  @Override
public String toString() {
	return "Customer [customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_city="
			+ customer_city + ", customer_mobile=" + customer_mobile + ", customer_email=" + customer_email
			+ ", customer_gender=" + customer_gender + ", Account_type=" + Account_type + ", Account_number="
			+ Account_number + ", customer_balance=" + customer_balance + ", customer_password=" + customer_password
			+ "]";
}
public int getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(int customer_id) {
	this.customer_id = customer_id;
}
public String getCustomer_name() {
	return customer_name;
}
public void setCustomer_name(String customer_name) {
	this.customer_name = customer_name;
}
public String getCustomer_city() {
	return customer_city;
}
public void setCustomer_city(String customer_city) {
	this.customer_city = customer_city;
}
public String getCustomer_mobile() {
	return customer_mobile;
}
public void setCustomer_mobile(String customer_mobile) {
	this.customer_mobile = customer_mobile;
}
public String getCustomer_email() {
	return customer_email;
}
public void setCustomer_email(String customer_email) {
	this.customer_email = customer_email;
}
public String getCustomer_gender() {
	return customer_gender;
}
public void setCustomer_gender(String customer_gender) {
	this.customer_gender = customer_gender;
}
public String getAccount_type() {
	return Account_type;
}
public void setAccount_type(String account_type) {
	Account_type = account_type;
}
public String getAccount_number() {
	return Account_number;
}
public void setAccount_number(String account_number) {
	Account_number = account_number;
}
public int getCustomer_balance() {
	return customer_balance;
}
public void setCustomer_balance(int customer_balance) {
	this.customer_balance = customer_balance;
}
public String getCustomer_password() {
	return customer_password;
}
public void setCustomer_password(String customer_password) {
	this.customer_password = customer_password;
}
public Customer(int customer_id, String customer_name, String customer_city, String customer_mobile,
		String customer_email, String customer_gender, String account_type, String account_number, int customer_balance,
		String customer_password) {
	super();
	this.customer_id = customer_id;
	this.customer_name = customer_name;
	this.customer_city = customer_city;
	this.customer_mobile = customer_mobile;
	this.customer_email = customer_email;
	this.customer_gender = customer_gender;
	Account_type = account_type;
	Account_number = account_number;
	this.customer_balance = customer_balance;
	this.customer_password = customer_password;
}
  
}
