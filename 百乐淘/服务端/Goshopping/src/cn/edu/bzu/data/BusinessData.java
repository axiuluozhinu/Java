package cn.edu.bzu.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.edu.bzu.bean.Goods;
import cn.edu.bzu.bean.Stores;
import cn.edu.bzu.mysql.DatabaseConnection;

public class BusinessData {
	/*
	 * 商家退出
	 */
	public void businessExit(String account){
		Connection conn=null;
		Statement st=null;
		String sql="update information_business set login=0 where businessAccount='"+account+"';";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			st.executeUpdate(sql);
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("商家退出失败");
			e.printStackTrace();
		}
	}
	/*
	 * 商家注册信息录入
	 */
	public String businessRegister(String account,String password,String phonenumber){
		Connection conn=null;
		Statement st=null;	
		ResultSet rs=null;
		String[] str=null;
		int length = 0,i=0;
		String sql1="select count(businessAccount) from information_business;";
		String sql2="select businessAccount from information_business;";
		String sql3="insert into information_business (businessAccount,businessPassword,businessPhonenumber) " +
				"values ('"+account+"','"+password+"','"+phonenumber+"');";		
		String sql4="update information_business set businessCredibility=100 where businessAccount='"+account+"';";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			st.executeUpdate(sql3);
			rs=st.executeQuery(sql1);
			while(rs.next()){
				length=rs.getInt(1);
			}
			str=new String[length];
			rs=st.executeQuery(sql2);
			while(rs.next()&&i<length){
				str[i]=rs.getString("businessAccount");
				i++;
			}
			for(i=0;i<length;i++){
				if(account.equals(str[i])){
					return "0";
				}
				if(i==length-1){
					st.executeUpdate(sql3);
					return "1";
				}
			}
			st.executeUpdate(sql4);
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("商家注册失败");
			e.printStackTrace();
		}
		return "0";
	}
	/*
	 * 商家登陆时身份验证   1准许登陆  0不准许登陆
	 */
	public String businessLoad(String account,String password){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		int length = 0,i=0;
		String[] str=null;
		String sql1="select count(businessAccount) from information_business;";
		String sql2="select businessPassword from information_business where businessAccount='"+account+"';";
		String sql3="update information_business set login=1 where businessAccount='"+account+"';";
		String sql4="select businessAccount from information_business;";
		String passWord=null;
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				length=rs.getInt(1);
			}
			rs=st.executeQuery(sql2);
			while(rs.next()){
			passWord=rs.getString("businessPassword");
			}
			rs=st.executeQuery(sql4);
			 str=new String[length];
			while(rs.next()&&i<length){
				str[i]=rs.getString("businessAccount");
				i++;
			}
			for(i=0;i<length;i++){
				if(account.equals(str[i])){
					if(password.equals(passWord)){
						st.executeUpdate(sql3);
						return "1";
					}
				}else{
					continue;
				}		
			}
			
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("商家登录失败");
			e.printStackTrace();
		}
		return "0";
	} 
	
	/*
	 * 返回商家信誉度分数
	 */
	public int businessCredibility(String goodsid,String account){
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		int result=0;
		String businessid=null;
		String sql1="select businessCredibility from information_business where businessAccount='"+account+"';";
		String sql2="select businessId from information_business where supplyGoods like '"+goodsid+"';";		
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			if(account==null){			
				rs=st.executeQuery(sql2);
				while(rs.next()){
					businessid=rs.getString(1);
				}
				String sql3="select businessCredibility from information_business where businessId="+businessid+";";
				rs=st.executeQuery(sql3);
				while(rs.next()){
					result=rs.getInt(1);
				}
			}else{
				rs=st.executeQuery(sql1);
				while(rs.next()){
					result=rs.getInt(1);
				}
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("返回商家信誉度失败");
			e.printStackTrace();
		}
		return result;
	}
	/*
	 * 查看预订本店商品的客户
	 */
	public String[] viewScheduledClient(String account){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String[] str=null;
		String[] arr=null;
		String[] viewResult=null;
		String result1=null;
		String result2="";
		String sql1="select supplyGoods from information_business where businessAccount='"+account+"';";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				result1=rs.getString(1);
			}
			if(result1==null){
				viewResult=new String[1];
				viewResult[0]="暂无预订客户";
				return viewResult;
			}
			str=result1.split(","); 
			arr=new String[str.length];
			for(int i=0;i<str.length;i++){
				String sql2="select clientAccount from information_client where goodsScheduled like '%"+str[i]+",%';";
				rs=st.executeQuery(sql2);
				while(rs.next()){
					arr[i]=rs.getString(1);					
				}
				if(arr[i]==null){
					continue;
				}
				if(i>0&&arr[i].equals(arr[i-1])){
					continue;
				}
				result2+=arr[i]+",";
			}
			if(result2.equals("")){
				viewResult=new String[1];
				viewResult[0]="暂无预订客户";
				return viewResult;
			}
			viewResult=result2.split(",");
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("查看预订本店商品的客户失败");
			e.printStackTrace();
		}
		return viewResult;
	}
	/*
	 * 返回客户预订的本店商品
	 */
	public Goods[] localClientScheduledGoods(String businessAccount,String clientAccount){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		Goods[] goods=null;
		String[] arr=null;
		String[] goodsid=null;
		String[] str=null;
		String result=null;
		String str1=null;
		String str2="";
		String str3="";
		String sql1="select goodsScheduled from information_client where clientAccount='"+clientAccount+"';";		
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				str1=rs.getString(1);
			}
			goodsid=str1.split(",");
			arr=new String[goodsid.length];
			for(int i=0;i<goodsid.length;i++){
				String sql2="select businessid from information_business where supplyGoods like '%"+goodsid[i]+",%' and businessAccount='"+businessAccount+"';";
				rs=st.executeQuery(sql2);
				while(rs.next()){
					result=rs.getString(1);
				}
				if(result==null||result.equals("")){
					continue;
				}else{
					str2+=goodsid[i]+",";
				}
			}
			if(str2.equals("")){
				arr=new String[1];
				arr[0]="0";
			}
			str=str2.split(",");
			arr=new String[str.length];
			for(int i=0;i<str.length;i++){
				if(str[i].equals("")){
					continue;
				}
				if(str3.equals("")&&i==0){
					str3=str[i]+",";continue;
				}
				str3+=str[i]+",";
			}
			arr=str3.split(",");
			goods=new Goods[arr.length];
			for(int i=0;i<arr.length;i++){
				if(arr[i].equals("0")||arr[i].equals("")){
					continue;
				}
				String sql3="select goodsName from goods where goodsId="+arr[i]+";";
				rs=st.executeQuery(sql3);
				while(rs.next()){
					goods[i]=new Goods();
					goods[i].setGoodsName(rs.getString(1));
					goods[i].setGoodsId(Integer.parseInt(arr[i]));
				}
			}
			if(goods==null){
				goods=new Goods[1];
				goods[0].setGoodsName("该用户未在本店预订商品");
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("返回客户预订的本店商品失败");
			e.printStackTrace();
		}
		return goods;
	}
	/*
	 * 按星级返回当地商家
	 */
	public Stores[] localBusinessCredibility(String star,String city){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		Stores[] stores=null;
		String[] businessid=null;
		int[] Credibility=null;
		String[] temp=null;
		String sql1="select businessId from store where city like '%"+city+"%';";	
		int i=0,length=0;
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			rs.last();
			length=rs.getRow();
			businessid=new String[length];
			Credibility=new int[length];
			temp=new String[length];
			rs.beforeFirst();
			while(rs.next()&&i<length){
				businessid[i]=rs.getString(1);
				i++;
			}
			for(i=0;i<length;i++){
				String sql2="select businessCredibility from information_business where businessId="+businessid[i]+";";
				rs=st.executeQuery(sql2);
				while(rs.next()){					
					Credibility[i]=rs.getInt(1);
				}
				if(star.equals("1")&&Credibility[i]>=0&&Credibility[i]<1000){
					temp[i]=businessid[i];
				}
				if(star.equals("2")&&Credibility[i]>=1000&&Credibility[i]<2000){
					temp[i]=businessid[i];
				}
				if(star.equals("3")&&Credibility[i]>=2000&&Credibility[i]<3000){
					temp[i]=businessid[i];
				}
			}
			for(i=0;i<length;i++){
				String sql3="select storeId,longitude,latitude from store where businessid="+temp[i]+";";
				rs=st.executeQuery(sql3);
				rs.last();
				length=rs.getRow();
				stores=new Stores[length];
				rs.beforeFirst();
				while(rs.next()){
					stores[i]=new Stores();
					stores[i].setStoreId(rs.getInt("storeId"));
					stores[i].setLongitude(rs.getString("longitude"));
					stores[i].setLatitude(rs.getString("latitude"));
				}
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

}
