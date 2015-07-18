package cn.edu.bzu.bean;

public class Consumer {
	private int clientId;
	private String realName;
	private String sex;
	private String age;
	private static String clientAccount;
	private String clientPassword;
	private String clientPhonenumber;
	private String address;
	private int login;
	private String Integral;
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public static String getClientAccount() {
		return clientAccount;
	}
	public static void setClientAccount(String clientAccount) {
		Consumer.clientAccount = clientAccount;
	}
	public String getClientPassword() {
		return clientPassword;
	}
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}
	public String getClientPhonenumber() {
		return clientPhonenumber;
	}
	public void setClientPhonenumber(String clientPhonenumber) {
		this.clientPhonenumber = clientPhonenumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getLogin() {
		return login;
	}
	public void setLogin(int login) {
		this.login = login;
	}
	public String getIntegral() {
		return Integral;
	}
	public void setIntegral(String integral) {
		Integral = integral;
	}
}
