package cn.edu.bzu.data;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.misc.BASE64Decoder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import cn.edu.bzu.bean.Goods;
import cn.edu.bzu.mysql.DatabaseConnection;

public class GoodsData {

//	WSDL URL 
//	http://localhost:8080/Goshopping/services/WebServices?wsdl 
	
	/*
	 * 显示当地的商品图片
	 */
	public Goods[] LocalAreaGoodsImage(String address)  throws IOException{
		String[] arr=null;
		String[] businessid=null;
		String supplyGoods=null;
		String str="";
		String[] goodsid=null;
		int length=0;
		String sql1="select businessId from store where city like '%"+address+"%';";
		Goods[] goods=null;
		Connection conn;
		Statement st;
		ResultSet rs;
		String[] uploadBuffer=null;
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			rs=st.executeQuery(sql1);
			rs.last();
			length=rs.getRow();
			rs.beforeFirst();
			businessid=new String[length];
			int i=0;
			while(rs.next()&&i<length){
				businessid[i]=rs.getString(1);
				i++;
			}
			for(i=0;i<length;i++){
				String sql2="select supplyGoods from information_business where businessId="+businessid[i]+";";
				rs=st.executeQuery(sql2);
				while(rs.next()){
					supplyGoods=rs.getString(1);
				}
				str+=supplyGoods;
			}
			goodsid=str.split(",");
			arr=new String[goodsid.length];
			uploadBuffer=new String[goodsid.length];
			goods=new Goods[goodsid.length];
			for(i=0;i<goodsid.length;i++){
				goods[i]=new Goods();			
				String sql3="select * from goods where goodsId="+goodsid[i]+";";
				rs=st.executeQuery(sql3);			
				while(rs.next()){
					goods[i].setGoodsId(rs.getInt("goodsId"));
					goods[i].setImagePath(rs.getString("imagePath"));
					goods[i].setGoodsName(rs.getString("goodsName"));				
					arr[i]="D:\\image\\"+goods[i].getImagePath()+".jpg";
					if(arr[i]==null){
						break;
					}
				}
			}			
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("显示当地的商品图片失败");
			e.printStackTrace();
		}
		
		for(int t=0;t<goodsid.length;t++){
			if(arr[t]==null){
				 break;
			}
		else{
			 FileInputStream fis = new FileInputStream(arr[t]);
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 byte[] buffer = new byte[1024];
			 int count = 0; 
			 while((count = fis.read(buffer)) >= 0){  
		         baos.write(buffer, 0, count);  
		     }  
			  uploadBuffer[t] = new String(Base64.encode(baos.toByteArray()));
			  goods[t].setCode(uploadBuffer[t]);
			 fis.close();
			}
			
			 }
		return goods;
	}

	/*
	 * 返回首页排名前五名商品图片
	 */
	public Goods[] goodsIndex()  throws IOException{
		String[] arr=new String[10];
		String sql="select goodsId,imagePath,goodsName from goods order by hot desc limit 6";	
        Goods[] goods=new Goods[6];
		Connection conn;
		Statement st;
		
		String[] uploadBuffer=new String[6];
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			//查询语句
			st=(Statement) conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			int i=0;
			while(rs.next()&&i<6){
				goods[i]=new Goods();
				goods[i].setGoodsId(rs.getInt("goodsId"));
				goods[i].setImagePath(rs.getString("imagePath"));
				goods[i].setGoodsName(rs.getString("goodsName"));
				arr[i]="D:\\image\\"+goods[i].getImagePath()+".jpg";
				if(arr[i]==null){
					break;
				}
				i++;
			}
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("查询失败");
			e.printStackTrace();
		}
		for(int t=0;t<6;t++){
			if(arr[t]==null){
				 break;
			}
		else{
			 FileInputStream fis = new FileInputStream(arr[t]);
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 byte[] buffer = new byte[1024];
			 int count = 0; 
			 while((count = fis.read(buffer)) >= 0){  
		         baos.write(buffer, 0, count);  
		     }  
			  uploadBuffer[t] = new String(Base64.encode(baos.toByteArray()));
			  goods[t].setCode(uploadBuffer[t]);
			 fis.close();
			}
			
			 }
		return goods;
	}
	/*
	 * 返回搜索的商品货架商品图片
	 */
	public Goods[] rankgoods(String name)throws IOException{
		String[] arr=null;
		String sql1="select count(*) from goods  where goodsName like '%"+name+"%'";
		String sql2="select goodsId,imagePath from goods  where goodsName like '%"+name+"%'";
        int  length = 0;
		Connection conn;
		Statement st;
		Goods[]  goods=null;
		String[] uploadBuffer=null;
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			//查询语句
			st=(Statement) conn.createStatement();
			ResultSet rs=st.executeQuery(sql1);					
			int i=0;
			while(rs.next()){
				length=rs.getInt(1);
			}			
		     goods=new Goods[length];
			 rs=st.executeQuery(sql2);
			 arr=new String[length];
			while(rs.next()&&i<length){
				goods[i]=new Goods();
				goods[i].setGoodsId(rs.getInt("goodsId"));
				goods[i].setImagePath(rs.getString("imagePath"));
				arr[i]="D:\\image\\"+goods[i].getImagePath()+".jpg";
				if(arr[i]==null){
					break;
				}
				i++;
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("查询失败");
			e.printStackTrace();
		}
		for(int i=0;i<length;i++){
			if(arr[i]==null){
				 break;
			}
		else{
			 uploadBuffer=new String[length];
			 FileInputStream fis = new FileInputStream(arr[i]);
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 byte[] buffer = new byte[1024];
			 int count = 0; 
			 while((count = fis.read(buffer)) >= 0){  
		         baos.write(buffer, 0, count);  
		     }  
			  uploadBuffer[i] = new String(Base64.encode(baos.toByteArray()));
			  goods[i].setCode(uploadBuffer[i]);
			 fis.close();
			}			
			 }
		return  goods; 
	}
	/*
	 * 返回商品详细信息介绍
	 */
	public Goods   goodsIntroduce(int goodsid) throws IOException{
        String sql="select * from goods where goodsId="+goodsid;
        String arr=null;
        Goods goods=new Goods();
		Connection conn;
		Statement st;
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			//查询语句
			st=(Statement) conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setHot(rs.getInt("hot"));
				goods.setModel(rs.getString("model"));
				goods.setBrand(rs.getString("brand"));
				goods.setUnit(rs.getString("unit"));
				goods.setMaterial(rs.getString("material"));
				goods.setColor(rs.getString("color"));
				goods.setImagePath(rs.getString("imagePath"));
				goods.setScheduled(rs.getInt("scheduled"));				
			}
			arr="D:\\image\\"+goods.getImagePath()+".jpg";
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("查询失败");
			e.printStackTrace();
		}
		String uploadBuffer;
		 FileInputStream fis = new FileInputStream(arr);
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 byte[] buffer = new byte[1024];
		 int count = 0; 
		 while((count = fis.read(buffer)) >= 0){  
	         baos.write(buffer, 0, count);  
	     }  
		  uploadBuffer = new String(Base64.encode(baos.toByteArray()));
		  goods.setCode(uploadBuffer);
		 fis.close();
			 
		return goods; 
	}
	
	/*
	 * 返回商品分类
	 */
	public String[] goodsType(){
		String[] str=null;
		Connection conn;
		Statement st;
		ResultSet rs;
		int i=0;
		String sql="select distinct type from goods;";
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			rs=st.executeQuery(sql);
			rs.last();
			int length=rs.getRow();
			str=new String[length];
			rs.beforeFirst();
			while(rs.next()&&i<length){
				str[i]=rs.getString(1);
				i++;
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("返回商品分类失败");
			e.printStackTrace();
		}
		return str;
	}
	
	/*
	 * 根据分类返回商品图片
	 */
	public Goods[] goodsImage(String type)throws IOException{
		String[] arr=null;
		String sql1="select count(*) from goods where type='"+type+"';";
		String sql2="select goodsId,imagePath from goods where type='"+type+"';";	
        Goods[] goods=null;
		Connection conn;
		Statement st;
		int i=0,length=0;
		String[] uploadBuffer=null;
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			ResultSet rs=st.executeQuery(sql1);
			while(rs.next()){
				length=rs.getInt(1);
			}
			arr=new String[length];
			uploadBuffer=new String[length];
			goods=new Goods[length];
			rs=st.executeQuery(sql2);
			while(rs.next()&&i<length){
				goods[i]=new Goods();
				goods[i].setGoodsId(rs.getInt("goodsId"));
				goods[i].setImagePath(rs.getString("imagePath"));
				arr[i]="D:\\image\\"+goods[i].getImagePath()+".jpg";
				if(arr[i]==null){
					break;
				}
				i++;
			}
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("根据分类返回商品图片失败");
			e.printStackTrace();
		}
		for(i=0;i<length;i++){
			if(arr[i]==null){
				 break;
			}
		else{
			 FileInputStream fis = new FileInputStream(arr[i]);
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 byte[] buffer = new byte[1024];
			 int count = 0; 
			 while((count = fis.read(buffer)) >= 0){  
		         baos.write(buffer, 0, count);  
		     }  
			  uploadBuffer[i] = new String(Base64.encode(baos.toByteArray()));
			  goods[i].setCode(uploadBuffer[i]);
			 fis.close();
			}
		}
		return goods; 
	}
	
	/*
	 * 商品上架
	 */
	public void Shelves(String account,String goodsName,String type,String model,String brand,String unit,String material,String color,String goodsimagecode){
		Date today=new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyyMMdd");
		String time=f.format(today);
		String filename = null;
		Connection conn;
		Statement st;
		ResultSet rs;
		String str1=null;
		String str2=null;
		String sql1="insert into goods (goodsName,type,model,brand,unit,material,color) values " +
		"('"+goodsName+"','"+type+"','"+model+"','"+brand+"',"+unit+",'"+material+"','"+color+"');";
		String sql2="select max(goodsId) from goods;";
		String sql3="select supplyGoods from information_business where businessAccount='"+account+"';";
		try{
		conn=(Connection) DatabaseConnection.getConnection();
		st=(Statement) conn.createStatement();
		st.executeUpdate(sql1);
		rs=st.executeQuery(sql2);
		while(rs.next()){
		str1=rs.getString(1);
		}
		rs=st.executeQuery(sql3);
		while(rs.next()){
		str2=rs.getString(1);
		}
		String sql4=null;
		String sql5="update goods set imagePath='"+time+str1+"' where goodsId="+str1+";";
		if(str2==null){
		sql4="update information_business set supplyGoods='"+str1+",' where businessAccount='"+account+"';";
		}else{
		sql4="update information_business set supplyGoods='"+str2+str1+",' where businessAccount='"+account+"';";
		}
		String sql6="update information_business set businessCredibility=businessCredibility+1 where businessAccount='"+account+"';";
		st.executeUpdate(sql4);
		st.executeUpdate(sql5);
		st.executeUpdate(sql6);
		String sql16="select * from goods where goodsId="+str1+";";
		rs=st.executeQuery(sql16);
		while(rs.next()){
			filename=rs.getString("imagePath")+".jpg";
		}
		st.close();
		rs.close();
		conn.close();
		}catch(SQLException e){
		System.out.println("商品上架失败");
		e.printStackTrace();
		}
		FileOutputStream fos = null;  
	    try{  
	        String toDir = "D:\\image";   //存储路径  
	          
	        byte[] buffer = new BASE64Decoder().decodeBuffer(goodsimagecode);
	        File destDir = new File(toDir);    
	        if(!destDir.exists()) destDir.mkdir();  
	        fos = new FileOutputStream(new File(destDir,filename));   //保存图片  
	        fos.write(buffer);  
	        fos.flush();  
	        fos.close();  	     
	    }catch (Exception e){  
	        e.printStackTrace();  
	    }  
		}

	/*
	 * 商品下架
	 */
	public void CancelShelves(int goodsid,String account){
		Connection conn;
		Statement st;
		ResultSet rs;
		String fileName = null;
		String name=null;
		String sql1="delete from goods where goodsId="+goodsid+";";
		String sql2="update information_business set supplyGoods=replace(supplyGoods,'"+goodsid+",','') " +
				"where businessAccount='"+account+"';";		
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();			
			String sql16="select * from goods where goodsId="+goodsid+";";
			rs=st.executeQuery(sql16);
			while(rs.next()){
				name=rs.getString("imagePath");
			}
			fileName="D:\\image\\"+name+".jpg";
			st.executeUpdate(sql1);
			st.executeUpdate(sql2);
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("商品下架失败");
			e.printStackTrace();
		}
		
		 File file = new File(fileName);     
	        if(file.isFile() && file.exists()){     
	            file.delete();     
	            System.out.println("删除单个文件"+fileName+"成功！");               
	        }else{     
	            System.out.println("删除单个文件"+fileName+"失败！");     	         
	        }     

	}
		}

