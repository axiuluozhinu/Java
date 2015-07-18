package cn.edu.bzu.bean;

public class Stores {
	private String storeId;
	private String storeName;
	private String type;
	private String storeApproval;
	private String province;
	private String city;
	private String town;
	private String address;
	private String longitude;
	private String latitude;
	private static Double alongitude;
	private static Double alatitude;
	private static String distance;
	private static String jinpai;
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStoreApproval() {
		return storeApproval;
	}
	public void setStoreApproval(String storeApproval) {
		this.storeApproval = storeApproval;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public static Double getAlongitude() {
		return alongitude;
	}
	public static void setAlongitude(Double alongitude) {
		Stores.alongitude = alongitude;
	}
	public static Double getAlatitude() {
		return alatitude;
	}
	public static void setAlatitude(Double alatitude) {
		Stores.alatitude = alatitude;
	}
	public static String getDistance() {
		return distance;
	}
	public static void setDistance(String distance) {
		Stores.distance = distance;
	}
	public static String getJinpai() {
		return jinpai;
	}
	public static void setJinpai(String jinpai) {
		Stores.jinpai = jinpai;
	}
     
	
}
