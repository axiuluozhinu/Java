package cn.edu.bzu.data;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class LoginData {

	/*
	 * 消费者登陆信息验证
	 */
	public String LoginiaInformation(String clientAccount,String clientPassword){
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "loginChecking");
		 request.addProperty("clientAccount",clientAccount);
		 request.addProperty("clientPassword",clientPassword);
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
		
			String reslut=(String) tempresult.toString();
			return reslut;
	}
	
	/*
	 * 商家登陆信息验证
	 */
	public String businessLoad(String account,String password){
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "businessLoad");
		 request.addProperty("account",account);
		 request.addProperty("password",password);
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
		
			String reslut=(String) tempresult.toString();
			return reslut;
	}
}
