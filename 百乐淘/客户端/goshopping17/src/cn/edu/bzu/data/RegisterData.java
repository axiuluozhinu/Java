package cn.edu.bzu.data;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class RegisterData {

	
	/*
	 * 消费者注册信息
	 */
	
	public String register(String clientAccount,String clientPassword,String clientPhonenumber ){
		
		String reslut=null;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "register");
		 request.addProperty("clientAccount", clientAccount);
		 request.addProperty("clientPassword", clientPassword);
		 request.addProperty("clientPhonenumber", clientPhonenumber);
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
	 * 商家信息注册
	 */
	public String businessRegister(String account,String password,String phonenumber){
		String reslut=null;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "businessRegister");
		 request.addProperty("account", account);
		 request.addProperty("password", password);
		 request.addProperty("phonenumber", phonenumber);
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
}
