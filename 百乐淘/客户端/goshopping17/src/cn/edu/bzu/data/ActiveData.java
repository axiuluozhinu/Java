package cn.edu.bzu.data;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import cn.edu.bzu.bean.Active;


public class ActiveData {
    /*
	 * 返回首页活动图片10.0.2.2
	 */
	public String[] activityimage(){
		int length=0;
		String[] activeimage=null;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "activityImage");
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
			length=tempresult.getPropertyCount();
			activeimage=new String[length];
			for(int i=0;i<length;i++){
				activeimage[i]=tempresult.getProperty(i).toString();
			}
			return activeimage;
	}	
	/*
	 * 活动详情
	 */	
	public Active getActivityIntroduction(int id){
		Active active=new Active();
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "activityIntroduction");
		 request.addProperty("id", id);
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
			active.setStoreName(tempresult.getProperty(6).toString());
			active.setTopic(tempresult.getProperty(7).toString());
			active.setAddress(tempresult.getProperty(0).toString());
			active.setContent(tempresult.getProperty(1).toString());
			active.setEndTime(tempresult.getProperty(2).toString());
			active.setStartTime(tempresult.getProperty(5).toString());
			active.setId(tempresult.getProperty(3).toString());
			return active;
	}	
	/*
	 * 活动列表
	 */
	public Active[] getActivityTopic(){
		int length=0;
		Active[] active=null;
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "activityTopic");
		
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
			 active=new Active[length];
			SoapObject[] vct1=new SoapObject[length];
			for(int i=0;i<vct1.length;i++){
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
	 * 参与活动
	 */
	public void getJoinActivity(int activityid,String account){
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "joinActivity");
		 request.addProperty("activityid", activityid);
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
	}
	/*
	 * 取消参与
	 */
	public void getCancelJoin(int activityid,String account){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "cancelJoin");
		 request.addProperty("activityid", activityid);
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
	}
	
	/*
	 * 商店近期活动
	 */
	public Active[] RecentActivity(String storesid){
		Active[] active=null;
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "RecentActivity");
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
			int length=tempresult.getPropertyCount();
			 active=new Active[length];
			SoapObject[] vct1=new SoapObject[length];
			for(int i=0;i<vct1.length;i++){
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
	 *  商家查看自己发布的活动
	 */
	public Active[] businessViewActivity(String account){
		Active[] active=null;
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
			int length=tempresult.getPropertyCount();
			 active=new Active[length];
			SoapObject[] vct1=new SoapObject[length];
			for(int i=0;i<vct1.length;i++){
			vct1[i]=new SoapObject(null, null);
			vct1[i]=(SoapObject)tempresult.getProperty(i);
			if(vct1[i]==null){
				break;
			}
			active[i] = new Active();
			active[i].setId(vct1[i].getProperty(3).toString());
			active[i].setTopic(vct1[i].getProperty(6).toString());
			}
			return active;
	}
}
