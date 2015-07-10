package cn.edu.bzu.book.util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 数据库工具类
 * @author 刘春霞
 *
 */
public class ConnectionManager {
	
	public static  Connection conn=null;
	public static PreparedStatement pstmt=null;
	public static ResultSet rs=null;
	/**
	 * 获取数据库连接
	 * @return
	 */	
	public static Connection getConnection(){
		
		//使用JNDI技术获取数据源，再从数据源中获取连接，context.xml中配置，web.xml中配置，拷贝jar包
		try {
			Context ct=new InitialContext();
			DataSource ds=(DataSource) ct.lookup("java:comp/env/jdbc/book");
			conn=ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 关闭资源方法
	 * @param 
	 */
	public static void closeAll(){
		try{
			if(rs!=null){
				rs.close();
				rs=null;
			}		
			if(pstmt!=null){
				pstmt.close();
				pstmt=null;
			}			
			if(conn!=null){
				conn.close();
				conn=null;
			}			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}	
}
