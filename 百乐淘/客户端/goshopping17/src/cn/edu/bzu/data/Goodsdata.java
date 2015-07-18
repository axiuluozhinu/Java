package cn.edu.bzu.data;

import java.io.IOException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import cn.edu.bzu.bean.Goods;
import com.rt.BASE64Decoder;

public class Goodsdata {
	/*
	 * 接受首页排名前五名的商品图片
	 */
	public Goods[] LocalAreaGoodsImage(String address){
		Goods[] goods=new Goods[6];
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "LocalAreaGoodsImage");
		 request.addProperty("address", address);
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
				
//				System.out.println(vct.size()+"ad");
			} catch (SoapFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SoapObject[] vct1=new SoapObject[6];
			for(int i=0;i<6;i++){
				vct1[i]=new SoapObject(null, null);
			 vct1[i]=(SoapObject)tempresult.getProperty(i);
			goods[i] = new Goods();
			goods[i].setCode(vct1[i].getProperty(1).toString());
			goods[i].setGoodsId(vct1[i].getProperty(3).toString());
			goods[i].setGoodsName(vct1[i].getProperty(4).toString());
			}
			return goods;
	}
	/*
	 * 搜索商品
	 */
	public Goods[] goods_rank_show(String name){
		
		int length=0;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "goods_rank_show");
		 request.addProperty("name", name);
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
				
//				System.out.println(vct.size()+"ad");
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
			goods[i].setCode(vct1[i].getProperty(1).toString());
			}
			return goods;
	}
	
	/*
	 * 商品详细信息介绍
	 */
	public Goods goodsIntroduce(int goodsid){
		Goods goods=new Goods();
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "goodsIntroduce");
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
			SoapObject tempresult = null;
			try {
				tempresult = (SoapObject)envelope.getResponse();
				


			} catch (SoapFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
			goods = new Goods();
			
			goods.setBrand(tempresult.getProperty(0).toString());
			goods.setCode(tempresult.getProperty(1).toString());
			goods.setColor(tempresult.getProperty(2).toString());
			goods.setGoodsId(tempresult.getProperty(3).toString());
			goods.setGoodsName(tempresult.getProperty(4).toString());
			goods.setHot(tempresult.getProperty(5).toString());
			goods.setMaterial(tempresult.getProperty(7).toString());
			goods.setModel(tempresult.getProperty(8).toString());
			goods.setUnit(tempresult.getProperty(10).toString());
			goods.setScheduled(tempresult.getProperty(9).toString());
			return goods;
			
	}
	
	
	/*
	 * 预定商品
	 */
	public void getScheduled(int goodsid,String account){
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "scheduled");
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
			SoapObject tempresult = null;
			try {
				tempresult = (SoapObject)envelope.getResponse();

			} catch (SoapFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	/*
	 * 取消预定商品
	 */
	public void getCancellation(int goodsid,String account){
		SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "Cancellation");
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
			SoapObject tempresult = null;
			try {
				tempresult = (SoapObject)envelope.getResponse();

			} catch (SoapFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/*
	 * 商品类别
	 */
	public String[] goodsType(){
		int length=0;
		String goodstype[]=null;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "goodsType");
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
				
//				System.out.println(vct.size()+"ad");
			} catch (SoapFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			    length=tempresult.getPropertyCount();
			    goodstype=new String[length];
			    for(int i=0;i<length;i++){
			    	goodstype[i]=tempresult.getProperty(i).toString();
			    }
			    return goodstype;
			    
	}
	
	/*
	 * 按分类显示商品图片
	 */
	public Goods[] goodsImage(String type){		
		 int length=0;
		 SoapObject request = new SoapObject("http://pox.bzu.edu.cn" , "goodsImage");
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
				
//				System.out.println(vct.size()+"ad");
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
			goods[i].setCode(vct1[i].getProperty(1).toString());
			}
			return goods;
	}
	
	
	/*
	 * 把传过来的编码转变成图片
	 */

	public Bitmap bit(String  code){
		if(code==null){
			return null;
		}
		else{
		 byte[] data = null;
		 try {
				data = new BASE64Decoder().decodeBuffer(code);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 Bitmap bitmap =BitmapFactory.decodeByteArray(data, 0, data.length);
			  return bitmap;
		}
	}
	
	
}
