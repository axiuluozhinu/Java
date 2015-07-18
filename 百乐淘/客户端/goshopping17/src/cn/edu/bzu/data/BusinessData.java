package cn.edu.bzu.data;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import cn.edu.bzu.bean.Active;
import cn.edu.bzu.bean.Goods;
import cn.edu.bzu.bean.Stores;

public class BusinessData {
	/*
	 * 退出
	 */
	public void businessExit(String account){
    	 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "businessExit");
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
			Object tempresult = null;
			try {
				tempresult = (Object)envelope.getResponse();
				
			} catch (SoapFault e) {
	
				e.printStackTrace();
			}
     }

	/*
	 * 商家发起活动
	 */
	public String businessOrganizeActivity(String account,String topic,String address,String starttime,String endtime,String content){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "businessOrganizeActivity");
		 request.addProperty("account", account);
		 request.addProperty("topic", topic);
		 request.addProperty("address", address);
		 request.addProperty("starttime", starttime);
		 request.addProperty("endtime", endtime);
		 request.addProperty("content", content);
		 
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
	
				e.printStackTrace();
			}
			String result=(String) tempresult.toString();
			
			return result;
	}
	/*
	 * 返回商家信誉度分数
	 */
	public String businessCredibility(String goodsid,String account){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "businessCredibility");
		 request.addProperty("goodsid", goodsid);
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
			Object tempresult = null;
			try {
				tempresult = (Object)envelope.getResponse();
				
			} catch (SoapFault e) {
	
				e.printStackTrace();
			}
			String result=(String) tempresult.toString();
			
			return result;
	}
	/*
	 * 商家查看自己发布的活动
	 */
	public Active[] businessViewActivity(String account){
		 int length=0;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "businessViewActivity");		
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
			SoapObject tempresult = null;
			
			try {
				tempresult = (SoapObject)envelope.getResponse();							
			} catch (SoapFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			    length=tempresult.getPropertyCount();
			SoapObject[] vct1=new SoapObject[length];
			Active[] active=new Active[length];
			for(int i=0;i<length;i++){
			vct1[i]=new SoapObject(null, null);
			vct1[i]=(SoapObject)tempresult.getProperty(i);
			if(vct1[i]==null){
				break;
			}
			active[i] = new Active();
			active[i].setId(vct1[i].getProperty(3).toString());	
			active[i].setTopic(vct1[i].getProperty(7).toString());	
		
			
			}
			return active;
	}
	/*
	 * 商家修改活动
	 */
	public String alterBusinessActyivity(String activityid,String topic,String address,String starttime,String endtime,String content){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "alterBusinessActyivity");
		 request.addProperty("activityid", activityid);
		 request.addProperty("topic", topic);
		 request.addProperty("address", address);
		 request.addProperty("starttime", starttime);
		 request.addProperty("endtime", endtime);
		 request.addProperty("content", content);
		 
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
	
				e.printStackTrace();
			}
			String result=(String) tempresult.toString();
			
			return result;
	}
	/*
	 * 商家取消活动
	 */
	public String cancelBusinessActivity(String activityid){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "cancelBusinessActivity");
		 request.addProperty("activityid", activityid);				 
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
	
				e.printStackTrace();
			}
			String result=(String) tempresult.toString();
			
			return result;
	}
	/*
	 * 查看预订本店商品的客户
	 */
	public String[] viewScheduledClient(String account){
		String[] client=null;
		int length;
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "viewScheduledClient");
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
			Object tempresult = null;
			try {
				tempresult = (Object)envelope.getResponse();
				
			} catch (SoapFault e) {
	
				e.printStackTrace();
			}
			length=((SoapObject) tempresult).getPropertyCount();
			client=new String[length];
			for(int i=0;i<length;i++){
				client[i]=new String();
				if(((SoapObject) tempresult).getProperty(i)==null){
					break;
				}
				client[i]=((SoapObject) tempresult).getProperty(i).toString();
			}
		return client;
	}
	/*
	 * 返回客户预订的本店商品
	 */
	public Goods[] localClientScheduledGoods(String businessAccount,String clientAccount){
		Goods[] goods=null;
		int length;
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "localClientScheduledGoods");
		 request.addProperty("businessAccount", businessAccount);
		 request.addProperty("clientAccount", clientAccount);
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
	
				e.printStackTrace();
			}
			length=((SoapObject) tempresult).getPropertyCount();
			SoapObject[] vct1=new SoapObject[length];
		   goods=new Goods[length];
			for(int i=0;i<length;i++){
			vct1[i]=new SoapObject(null, null);
			vct1[i]=(SoapObject) tempresult.getProperty(i);
			if(vct1[i]==null){
				break;
			}
			goods[i] = new Goods();
			goods[i].setGoodsId(vct1[i].getProperty(3).toString());
			goods[i].setGoodsName(vct1[i].getProperty(4).toString());
			}
		return goods;
	}
	/*
	 * 按星级返回当地商家信誉度
	 */
	public Stores[] localBusinessCredibility(String star,String city){
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
	
}
