package cn.edu.bzu.data;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import cn.edu.bzu.bean.Active;
import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.bean.Goods;

public class ConsumerData {
	/*
	 * 查看个人信息
	 */
	public Consumer clientIntroduction(String clientAccount){
		Consumer consumer=new Consumer();
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "clientIntroduction");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			consumer.setAddress(tempresult.getProperty(0).toString());
			consumer.setAge(tempresult.getProperty(1).toString());
			consumer.setClientPassword(tempresult.getProperty(4).toString());
			consumer.setClientPhonenumber(tempresult.getProperty(5).toString());
			consumer.setRealName(tempresult.getProperty(8).toString());
			consumer.setSex(tempresult.getProperty(9).toString());
			consumer.setIntegral(tempresult.getProperty(6).toString());
		return consumer;
	}
	/*
	 * 修改个人信息
	 */
	public void modification(String account,String phonenumber,String sex,String name,String age,String address){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "modification");
		 request.addProperty("account", account);
		 request.addProperty("phonenumber",phonenumber);
		 request.addProperty("sex",sex);
		 request.addProperty("name",name);
		 request.addProperty("age",age);
		 request.addProperty("address",address);
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
	 * 分享
	 */
	public String shareGoods(String account,int goodsid){
		  SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "shareGoods");
			 request.addProperty("account", account);
			 request.addProperty("goodsid", goodsid);
			 
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
	 * 退出
	 */
     public void  exit(String account){
    	 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "exit");
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
      * 留言
      */
     public String leaveMessage(String account,String friendid,String word){
    	 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "leaveMessage");		 
		 request.addProperty("account", account);
		 request.addProperty("friendid", friendid);
		 request.addProperty("word", word);		 
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
      * 消费者发起活动
      */
     public String  clientOrganizeActivity(String account,String topic,String address,String starttime,String endtime,String content){
    	 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "clientOrganizeActivity");
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
      * 查看个人预定
      */
     
     public Goods[] viewScheduled(String account){
    	 int length=0;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "viewScheduled");
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
			Goods[] goods=new Goods[length];
			for(int i=0;i<length;i++){
			vct1[i]=new SoapObject(null, null);
			vct1[i]=(SoapObject)tempresult.getProperty(i);
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
      * 查看消费者参与活动列表
      */
     public Active[] viewJoinActivity(int mark,String account){
    	 int length=0;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "viewJoinActivity");
		 
		 request.addProperty("mark",mark);
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
  	 * 返回待确认好友用户名
  	 */
  	public String[] confirmFriend(String account){
  		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "confirmFriend");
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
 	
 				e.printStackTrace();
 			}
 			  int length=tempresult.getPropertyCount();
 			  String[] str=new String[length];
 				for(int i=0;i<length;i++){
 					str[i]=tempresult.getProperty(i).toString();
 				}
 		return str;
  	}
  	/*
  	 * 查看自己发布的活动
  	 */
  	public Active[] clientViewActivity(String account){
  		 int length=0;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "clientViewActivity");				
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
			active[i] = new Active();
			active[i].setId(vct1[i].getProperty(3).toString());	
			active[i].setTopic(vct1[i].getProperty(7).toString());						
			}
			return active;
  	}
  	/*
	 *返回消费者积分
	 */
	public String clientIntegral(String account){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "clientIntegral");
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
	 * 客户修改活动
	 */
	public String alterClientActyivity(String account,String activityid,String topic,String address,String starttime,String endtime,String content){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "alterClientActyivity");
		 request.addProperty("account", account);
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
	 * 客户取消活动
	 */
	public String cancelClientActivity(String account,int activityid){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "cancelClientActivity");
		 request.addProperty("account", account);
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
}
