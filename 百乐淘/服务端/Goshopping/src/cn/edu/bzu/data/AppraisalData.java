package cn.edu.bzu.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cn.edu.bzu.mysql.DatabaseConnection;

public class AppraisalData {
	
	
	/*
	 * 客户评价商品内容
	 */
//	public void appraisalContent(String storeid,String clientid,String content){
//		Connection conn=null;
//		Statement st=null;
//		String sql="insert into appraisal (storeId,clientId,appraisalContent) values " +
//				"("+storeid+","+clientid+",'"+content+"');" ;	
//		try{
//			conn=DatabaseConnection.getConnection();
//			st=conn.createStatement();
//			st.executeUpdate(sql);
//			st.close();	
//			conn.close();
//		}catch(SQLException e){
//			System.out.println("评价失败");
//			e.printStackTrace();
//		}
//	}
	/*
	 * 客户评价内容
	 */
	public void appraisalContent(String storeid,String account,String content){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String clientid=null;
		String sql1="select clientId from information_client where clientAccount='"+account+"'";
		String sql3="update information_client set clientIntegral=clientIntegral+1 where clientAccount='"+account+"';";	
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				clientid=rs.getString(1);
			}
			String sql2="insert into appraisal (storeId,clientId,appraisalContent) values " +
			"("+storeid+","+clientid+",'"+content+"');" ;	
			st.executeUpdate(sql2);
			st.executeUpdate(sql3);
			st.close();	
			conn.close();
		}catch(SQLException e){
			System.out.println("评价失败");
			e.printStackTrace();
		}
	}

	/*
	 * 客户好评or差评     好 +1 差-1  积分都+1
	 */
	public void appraisal(String account,String storeid,String app){
		Connection conn=null;
		Statement st=null;
		String sql1="update information_business set businessCredibility=businessCredibility-1 " +
		"where businessId=(select businessId from store where storeId="+storeid+");";	
		String sql2="update information_business set businessCredibility=businessCredibility+1 " +
				"where businessId=(select businessId from store where storeId="+storeid+");";
		String sql3="update information_client set clientIntegral=clientIntegral+1 where clientAccount='"+account+"';";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			if(app.equals("好")){
				st.executeUpdate(sql2);
				st.executeUpdate(sql3);
			}else if(app.equals("差")){
				st.executeUpdate(sql1);
				st.executeUpdate(sql3);
			}
			st.close();	
			conn.close();
		}catch(SQLException e){
			System.out.println("评价失败");
			e.printStackTrace();
		}
	}
	
	/*
	 * 显示评价内容
	 */
	public String[] viewAppraisal(String storeid){
		String[] str1=null;
		String[] str2=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		int i=0,length=0;
		String sql1="select clientId from appraisal where storeId='"+storeid+"';";
		String sql2="select appraisalContent from appraisal where storeId='"+storeid+"';";	
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			rs.last();
			length=rs.getRow();
			str1=new String[length];
			str2=new String[length];
			rs.beforeFirst();
			while(rs.next()&&i<length){
				str1[i]=rs.getString(1);
				i++;
			}
			rs=st.executeQuery(sql2);
			i=0;
			while(rs.next()&&i<length){
				str2[i]=rs.getString(1)+" ---- "+str1[i];
				i++;
			}
			
			st.close();	
			conn.close();
		}catch(SQLException e){
			System.out.println("显示评价失败");
			e.printStackTrace();
		}
		return str2;
	}
}
