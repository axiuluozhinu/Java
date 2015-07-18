package cn.edu.bzu.data;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Environment;

import cn.edu.bzu.bean.Goods;
import cn.edu.bzu.bean.Stores;

public class StoresData {	
	/*
	 * 显示商家类别
	 */
	public Stores[] getStoresType(){
		int length=0;
		Stores[] stores=null;
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "storesType");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE("http://10.0.2.2:8080/Goshopping/services/WebServices");
		try {
			ht.call(null, envelope);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SoapObject tempresult = null;
		try {
			tempresult = (SoapObject)envelope.getResponse();
			
		} catch (SoapFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		length=tempresult.getPropertyCount();
		stores=new  Stores[length]; 
		SoapObject[] vct1=new SoapObject[length];
		for(int i=0;i<vct1.length;i++){
		vct1[i]=new SoapObject(null, null);
		vct1[i]=(SoapObject)tempresult.getProperty(i);
		if(vct1[i]==null){
			break;
		}
		stores[i]=new Stores();
		stores[i].setType(vct1[i].getProperty(9).toString());
		}
		return stores;
	}
	
	
	/*
	 * 显示商家名字
	 */
	public Stores[] getStoresName(String type){
		int length=0;
		Stores[] stores=null;
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "storesName");
		request.addProperty("type", type);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE("http://10.0.2.2:8080/Goshopping/services/WebServices");
		try {
			ht.call(null, envelope);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SoapObject tempresult = null;
		try {
			tempresult = (SoapObject)envelope.getResponse();
			
		} catch (SoapFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		length=tempresult.getPropertyCount();
		stores=new Stores[length];
		SoapObject[] vct1=new SoapObject[length];
		for(int i=0;i<vct1.length;i++){
		vct1[i]=new SoapObject(null, null);
		vct1[i]=(SoapObject)tempresult.getProperty(i);
		if(vct1[i]==null){
			break;
		}
		stores[i]=new Stores();
		stores[i].setStoreId(vct1[i].getProperty(6).toString());
		stores[i].setStoreName(vct1[i].getProperty(7).toString());
		}
		return stores;
	}
	
	/*
	 * 详细介绍商家
	 */
	public Stores getStoresIntroduction(int storesid){
		Stores stores=new Stores();
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "storesIntroduction");
		 request.addProperty("storesid", storesid);
	        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = request;
			envelope.setOutputSoapObject(request);
			HttpTransportSE ht = new HttpTransportSE("http://10.0.2.2:8080/Goshopping/services/WebServices");
			try {
				ht.call(null, envelope);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SoapObject tempresult = null;
			try {
				tempresult = (SoapObject)envelope.getResponse();
				
			} catch (SoapFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stores.setAddress(tempresult.getProperty(0).toString());
			stores.setCity(tempresult.getProperty(1).toString());
			stores.setProvince(tempresult.getProperty(4).toString());
			stores.setStoreApproval(tempresult.getProperty(5).toString());
			stores.setStoreId(tempresult.getProperty(6).toString());
			stores.setStoreName(tempresult.getProperty(7).toString());
			stores.setTown(tempresult.getProperty(8).toString());
			stores.setType(tempresult.getProperty(9).toString());
			return stores;
	}
	
	/*
	 * 商家经纬度位置
	 */
	public Stores[] storesInformation(){
		Stores[] stores=null;
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "storesInformation");
	        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = request;
			envelope.setOutputSoapObject(request);
			HttpTransportSE ht = new HttpTransportSE("http://10.0.2.2:8080/Goshopping/services/WebServices");
			try {
				ht.call(null, envelope);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SoapObject tempresult = null;
			try {
				tempresult = (SoapObject)envelope.getResponse();
				
			} catch (SoapFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int length=tempresult.getPropertyCount();
			stores =new Stores[length];
			SoapObject[] vct1=new SoapObject[length];
			for(int i=0;i<vct1.length;i++){
			vct1[i]=new SoapObject(null, null);
			vct1[i]=(SoapObject)tempresult.getProperty(i);
			if(vct1[i]==null){
				break;
			}
			stores[i]=new Stores();
			stores[i].setStoreId(vct1[i].getProperty(6).toString());
			stores[i].setLatitude(vct1[i].getProperty(2).toString());
			stores[i].setLongitude(vct1[i].getProperty(3).toString());
			}
		return stores;
	}
	/*
	 * 返回商店商品图片
	 */
	public Goods[] storeGoodsImage(String storesId){
		Goods[] goods=null;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "storeGoodsImage");
		 request.addProperty("storesId", storesId);
	        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = request;
			envelope.setOutputSoapObject(request);
			HttpTransportSE ht = new HttpTransportSE("http://10.0.2.2:8080/Goshopping/services/WebServices");
			try {
				ht.call(null, envelope);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SoapObject tempresult = null;
			try {
				tempresult = (SoapObject)envelope.getResponse();
			} catch (SoapFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int length=tempresult.getPropertyCount();
			goods=new Goods[length];
			SoapObject[] vct1=new SoapObject[length];
			for(int i=0;i<length;i++){
				vct1[i]=new SoapObject(null, null);
				vct1[i]=(SoapObject)tempresult.getProperty(i);
				goods[i] = new Goods();
				goods[i].setCode(vct1[i].getProperty(1).toString());
				goods[i].setGoodsId(vct1[i].getProperty(3).toString());
			}
			return goods;
	}
	/*
	 * 商品上架
	 */
	public String Shelves(String account,String goodsName,String type,String model,String brand,String unit,String material,String color,String imagename){
		String reslut=null;
		String goodsimagecode;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "Shelves");
		 request.addProperty("account", account);
		 request.addProperty("goodsName", goodsName);
		 request.addProperty("type", type);
		 request.addProperty("model", model);
		 request.addProperty("brand", brand);
		 request.addProperty("unit", unit);
		 request.addProperty("material",material);
		 request.addProperty("color", color);
		 goodsimagecode=this.up_goodsimages(imagename);	
		 request.addProperty("goodsimagecode", goodsimagecode);
	        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = request;
			envelope.setOutputSoapObject(request);
			HttpTransportSE ht = new HttpTransportSE("http://10.0.2.2:8080/Goshopping/services/WebServices");
			try {
				ht.call(null, envelope);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object tempresult = null;
			try {
				tempresult = (Object)envelope.getResponse();
				
			} catch (SoapFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reslut = (String) tempresult.toString();
			return reslut;
	}
	
	/*
	 * 搜索上传图片
	 */
	public String up_goodsimages(String imagename){
		String SDPATH; 
		String uploadBuffer = null;		
		SDPATH = Environment.getExternalStorageDirectory().getPath(); 
		 if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){			
			 try{  		           
		            FileInputStream fis = new FileInputStream(SDPATH + "/"+"goodsimage"+"/"+imagename+".jpg");  
		            ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		            byte[] buffer = new byte[1024];  
		            int count = 0;  
		            while((count = fis.read(buffer)) >= 0){  
		                baos.write(buffer, 0, count);  
		            }  	              
		             uploadBuffer = new String(Base64.encode(baos.toByteArray()));  //进行Base64编码  		           		              
		            fis.close();  		              
		        }catch(Exception e){  
		            e.printStackTrace();  
		        }  
			
		 }
		 return uploadBuffer;
	}
	/*
	 * 商品下架
	 */
	public String CancelShelves(int goodsid,String account){
		String reslut=null;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "CancelShelves");		
		 request.addProperty("goodsid",goodsid);
		 request.addProperty("account",account);
	        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = request;
			envelope.setOutputSoapObject(request);
			HttpTransportSE ht = new HttpTransportSE("http://10.0.2.2:8080/Goshopping/services/WebServices");
			try {
				ht.call(null, envelope);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object tempresult = null;
			try {
				tempresult = (Object)envelope.getResponse();
				
			} catch (SoapFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reslut = (String) tempresult.toString();
			return reslut;
	}
	
	/*
	 * 店铺注册
	 */
	public void storeRegister(String account,String storeName,String type,String storeApproval,String province,String city,String town,String address,String longitude,String latitude){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "storeRegister");
		request.addProperty("account", account);
		request.addProperty("storeName", storeName);
		request.addProperty("type", type);
		request.addProperty("storeApproval", storeApproval);
		request.addProperty("province", province);
		request.addProperty("city", city);
		request.addProperty("town", town);
		request.addProperty("address", address);
		request.addProperty("longitude", longitude);
		request.addProperty("latitude", latitude);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE("http://10.0.2.2:8080/Goshopping/services/WebServices");
		try {
			ht.call(null, envelope);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SoapObject tempresult = null;
		try {
			tempresult = (SoapObject)envelope.getResponse();
			
		} catch (SoapFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 返回商家自己的店铺名称
	 */
	public Stores[] mystoreName(String account){
		Stores[] stores=null;
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "mystoreName");
		request.addProperty("account", account);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE("http://10.0.2.2:8080/Goshopping/services/WebServices");
		try {
			ht.call(null, envelope);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SoapObject tempresult = null;
		try {
			tempresult = (SoapObject)envelope.getResponse();
			
		} catch (SoapFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int length=tempresult.getPropertyCount();
		stores=new  Stores[length]; 
		SoapObject[] vct1=new SoapObject[length];
		for(int i=0;i<vct1.length;i++){
		vct1[i]=new SoapObject(null, null);
		vct1[i]=(SoapObject)tempresult.getProperty(i);
		if(vct1[i]==null){
			break;
		}
		stores[i]=new Stores();
		stores[i].setStoreId(vct1[i].getProperty(6).toString());
		stores[i].setStoreName(vct1[i].getProperty(7).toString());
		}
		return stores;
	}
}
