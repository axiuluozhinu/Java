package cn.edu.bzu.bean;

public class Business {

	private int businessId;
	private static String businessAccount;
	private String businessPassword;
	private String businessPhonenumber;
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public static String getBusinessAccount() {
		return businessAccount;
	}
	public static void setBusinessAccount(String businessAccount) {
		Business.businessAccount = businessAccount;
	}
	public String getBusinessPassword() {
		return businessPassword;
	}
	public void setBusinessPassword(String businessPassword) {
		this.businessPassword = businessPassword;
	}
	public String getBusinessPhonenumber() {
		return businessPhonenumber;
	}
	public void setBusinessPhonenumber(String businessPhonenumber) {
		this.businessPhonenumber = businessPhonenumber;
	}
	
	
}
