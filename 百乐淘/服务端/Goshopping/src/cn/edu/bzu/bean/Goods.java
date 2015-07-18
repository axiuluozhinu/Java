package cn.edu.bzu.bean;

public class Goods {
private int goodsId;
private String goodsName;
private int hot;
private String model;
private String brand;
private String unit;
private String material;
private String color;
private int scheduled;
private String imagePath;
private String code;
//bean  entity 
public int getGoodsId() {
	return goodsId;
}
public void setGoodsId(int goodsId) {
	this.goodsId = goodsId;
}
public String getGoodsName() {
	return goodsName;
}
public void setGoodsName(String goodsName) {
	this.goodsName = goodsName;
}

public int getHot() {
	return hot;
}
public void setHot(int hot) {
	this.hot = hot;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}

public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}
public String getMaterial() {
	return material;
}
public void setMaterial(String material) {
	this.material = material;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public int getScheduled() {
	return scheduled;
}
public void setScheduled(int scheduled) {
	this.scheduled = scheduled;
}
public String getImagePath() {
	return imagePath;
}
public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
}
