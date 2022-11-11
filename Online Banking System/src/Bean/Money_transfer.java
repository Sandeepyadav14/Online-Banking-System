package Bean;

import java.sql.Date;

public class Money_transfer {
	private int cus_id;
	private String reciever_name;
	private String reciever_account_number;
	private int amount;
	private Date curr_date;
	public Money_transfer(){};
	@Override
	public String toString() {
		return "Money_transfer [cus_id=" + cus_id + ", reciever_name=" + reciever_name + ", reciever_account_number="
				+ reciever_account_number + ", amount=" + amount + ", curr_date=" + curr_date + "]";
	}
	public Money_transfer(int cus_id, String reciever_name, String reciever_account_number, int amount,
			Date curr_date) {
		super();
		this.cus_id = cus_id;
		this.reciever_name = reciever_name;
		this.reciever_account_number = reciever_account_number;
		this.amount = amount;
		this.curr_date = curr_date;
	}
	public int getCus_id() {
		return cus_id;
	}
	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}
	public String getReciever_name() {
		return reciever_name;
	}
	public void setReciever_name(String reciever_name) {
		this.reciever_name = reciever_name;
	}
	public String getReciever_account_number() {
		return reciever_account_number;
	}
	public void setReciever_account_number(String reciever_account_number) {
		this.reciever_account_number = reciever_account_number;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getCurr_date() {
		return curr_date;
	}
	public void setCurr_date(Date curr_date) {
		this.curr_date = curr_date;
	}
	

}
