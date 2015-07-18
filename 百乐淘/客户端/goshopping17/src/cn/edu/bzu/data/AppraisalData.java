package cn.edu.bzu.data;

import java.io.IOException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class AppraisalData {
	
	/*
	 * 好评或差评
	 */
  public String appraise(String account,String storeid,String app){
	  SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "appraisal");
		 request.addProperty("account", account);
		 request.addProperty("storeid", storeid);
		 request.addProperty("app", app);		 
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
   * 客户评价内容
   */
  public String appraisalContent(String storeid,String account,String content){	 
	  SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "appraisalContent");
		 request.addProperty("storeid", storeid);
		 request.addProperty("account", account);
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
	 * 显示评价内容
	 */
	public String[] viewAppraisal(String storeid){
		String[] result=null;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "viewAppraisal");
		 request.addProperty("storeid", storeid);
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
			result=new String[length];
		
			for(int i=0;i<length;i++){
				result[i]=(String)tempresult.getProperty(i).toString();			
			}
			return result;
	}
  
}
