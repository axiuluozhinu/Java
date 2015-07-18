package cn.edu.bzu.bean;

public class Business {

	private int businessId;
	private String businessAccount;
	private String businessPassword;
	private String businessPhonenumber;
	private int login;
	private int Credibility;
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public String getBusinessAccount() {
		return businessAccount;
	}
	public void setBusinessAccount(String businessAccount) {
		this.businessAccount = businessAccount;
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
	public int getLogin() {
		return login;
	}
	public void setLogin(int login) {
		this.login = login;
	}
	public int getCredibility() {
		return Credibility;
	}
	public void setCredibility(int credibility) {
		Credibility = credibility;
	}
}

