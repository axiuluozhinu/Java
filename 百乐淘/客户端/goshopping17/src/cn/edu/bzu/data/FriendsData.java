package cn.edu.bzu.data;

import java.io.IOException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import cn.edu.bzu.bean.Active;
import cn.edu.bzu.bean.Friends;
import cn.edu.bzu.bean.Goods;



public class FriendsData {
 
	/*
	 * ��ʾ�����б�
	 */
	public Friends[] viewFriend(String account){
		int length=0;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "viewFriend");
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
			Friends[] friends=new Friends[length];
			for(int i=0;i<length;i++){
			vct1[i]=new SoapObject(null, null);
			vct1[i]=(SoapObject)tempresult.getProperty(i);
			if(vct1[i]==null){
				break;
			}
			friends[i] = new Friends();
			friends[i].setClientAccount(vct1[i].getProperty(2).toString());						
			}
			return friends;
	}
	
	/*
	 * ��ʾ������Ϣ
	 */
	public Friends friendsinformation(String clientAccount){
		Friends friends=new Friends();
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
			friends.setAddress(tempresult.getProperty(0).toString());
			friends.setAge(tempresult.getProperty(1).toString());
			friends.setClientPhonenumber(tempresult.getProperty(5).toString());
			friends.setRealName(tempresult.getProperty(8).toString());
			friends.setSex(tempresult.getProperty(9).toString());		
		return friends;
	}
	
	/*
	 *�鿴 ���ѷ���
	 */
	public Goods[] viewShareGooods(String account){
		int length=0;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "viewShareGooods");
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
	 * ��Ӻ���
	 */
	public Boolean addFriend(String account,String friendAccount){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "addFriend");
		 request.addProperty("account", account);
		 request.addProperty("friendAccount", friendAccount);
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
			Boolean bl=Boolean.parseBoolean((String)tempresult.toString());
			return bl;
	}
	/*
	 * ͬ��/��ͬ�����Ϊ����
	 */
	public void agreeAdd(String account,String agree){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "agreeAdd");
		 request.addProperty("account", account);
		 request.addProperty("agree", agree);
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
	 * �鿴��������
	 */
	public String[] viewMessage(String account){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "viewMessage");
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
	 * �鿴���ѻ�б�
	 */
	public String[] friendActivity(String account){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "friendActivity");
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
	 * ���Ѳμӻ�б�
	 */
	public Active[]  friendJoinActivity(String account){
		int length=0;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "friendJoinActivity");
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
}
