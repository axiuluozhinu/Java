package cn.edu.bzu.data;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


import cn.edu.bzu.bean.Goods;
import cn.edu.bzu.bean.Stores;
import cn.edu.bzu.mysql.DatabaseConnection;

public class StoresData {
	/*
	 * 返回商家类别
	 */
	public Stores[] storesType(){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String sql1="select count(distinct type) from store;";
		String sql2="select distinct type from store;";
		int i=0,length=0;
		Stores[] stores = null;
		try{
			conn=DatabaseConnection.getConnection();
			//查询语句
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				length=rs.getInt(1);
			}
			stores=new Stores[length];
			rs=st.executeQuery(sql2);
			while(rs.next()&&i<length){
				stores[i]=new Stores();
				stores[i].setType(rs.getString("type"));
				if(stores[i]==null){break;}
				i++;
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("查询失败");
			e.printStackTrace();
		}
		
		return stores;
	}
	/*
	 * 返回对应类别的商家店名
	 */
	public Stores[] storesName(String type){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String sql1="select count(*) from store where type='"+type+"';";
		String sql2="select storeId,storeName from store where type='"+type+"';";
		int i=0,length=0;
		Stores[] stores = null;
		try{
			conn=DatabaseConnection.getConnection();
			//查询语句
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				length=rs.getInt(1);
			}
			stores=new Stores[length];
			rs=st.executeQuery(sql2);
			while(rs.next()&&i<length){
				stores[i]=new Stores();
				stores[i].setStoreId(rs.getInt("storeId"));
				stores[i].setStoreName(rs.getString("storeName"));
				if(stores[i]==null){break;}
				i++;
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("查询失败");
			e.printStackTrace();
		}
		
		return stores;
	}
	/*
	 * 查询对应商家的详细信息
	 */
	public Stores storesIntrodution(int storeid){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String sql1="select * from store where storeId="+storeid ;	
		Stores stores=new Stores();
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				stores.setStoreId(rs.getInt("storeId"));
				stores.setStoreName(rs.getString("storeName"));
				stores.setType(rs.getString("type"));
				stores.setStoreApproval(rs.getString("storeApproval"));
				stores.setProvince(rs.getString("province"));
				stores.setCity(rs.getString("city"));
				stores.setTown(rs.getString("town"));
				stores.setAddress(rs.getString("address"));
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("查询失败");
			e.printStackTrace();
		}
		return stores;
	}
	
	/*
	 * 返回所有商店经纬度
	 */
	public Stores[] storesInformation(){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String sql1="select * from store;";	
		String sql2="select count(*) from store;";
		Stores[] stores=null;
		int i=0,length=0;
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql2);
			while(rs.next()){
				length=rs.getInt(1);
			}
			stores=new Stores[length];
			rs=st.executeQuery(sql1);
			while(rs.next()&&i<length){
				stores[i]=new Stores();
				stores[i].setLatitude(rs.getString("latitude"));
				stores[i].setLongitude(rs.getString("longitude"));
				stores[i].setStoreId(rs.getInt("storeId"));
				i++;
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("查询失败");
			e.printStackTrace();
		}
		return stores;
	}
	/*
	 * 返回商店商品图片
	 */
	public Goods[] storeGoodsImage(String storesId)throws IOException{
		String[] arr=null;
		String[] str=null;
		String result=null;
		String sql1="select supplyGoods from information_business where businessId=" +
				"(select businessId from store where storeId="+storesId+");";	
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
				result=rs.getString(1);
			}
			str=result.split(",");
			length=str.length;
			goods=new Goods[length];
			arr=new String[length];
			uploadBuffer=new String[length];
			
			for(i=0;i<length;i++){
				String sql2="select goodsId,imagePath from goods where goodsId="+str[i]+";";
				rs=st.executeQuery(sql2);
				while(rs.next()){
					goods[i]=new Goods();
					goods[i].setGoodsId(rs.getInt("goodsId"));
					goods[i].setImagePath(rs.getString("imagePath"));
				}
				arr[i]="D:\\image\\"+goods[i].getImagePath()+".jpg";
				if(arr[i]==null){
					break;
				}
			}
			st.close();
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
	 * 店铺注册
	 */
	public void storeRegister(String account,String storeName,String type,String storeApproval,String province,String city,String town,String address,String longitude,String latitude){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String businessId=null;
		String sql1="select businessId from information_business where businessAccount='"+account+"';";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				businessId=rs.getString(1);
			}
			String sql2="insert into store (storeName,businessId,type,storeApproval,province,city,town,address,longitude,latitude) values " +
			"('"+storeName+"',"+businessId+",'"+type+"','"+storeApproval+"','"+province+"','"+city+"','"+town+"','"+address+"','"+longitude+"','"+latitude+"');";
			st.executeUpdate(sql2);
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("店铺注册失败");
			e.printStackTrace();
		}
	}
	/*
	 * 返回商家自己的店铺名称
	 */
	public Stores[] mystoreName(String account){
		Stores[] stores=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String businessId=null;
		String sql1="select businessId from information_business where businessAccount='"+account+"';";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				businessId=rs.getString(1);
			}
			String sql2="select storeId,storeName from store where businessId="+businessId+";";	
			rs=st.executeQuery(sql2);
			rs.last();
			int length=rs.getRow();
			int i=0;
			rs.beforeFirst();
			stores=new Stores[length];
			if(length==0){
				stores=new Stores[1];
				stores[0]=new Stores();
				stores[0].setStoreName("您尚未创建任何店铺");
				return stores;
			}
			while(rs.next()&&i<length){
				stores[i]=new Stores();
				stores[i].setStoreId(rs.getInt("storeId"));
				stores[i].setStoreName(rs.getString("storeName"));
				i++;
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("返回商家自己的店铺名称失败");
			e.printStackTrace();
		}
		return stores;
	}
	
}
