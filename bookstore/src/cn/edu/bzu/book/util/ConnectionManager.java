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
 * ���ݿ⹤����
 * @author ����ϼ
 *
 */
public class ConnectionManager {
	
	public static  Connection conn=null;
	public static PreparedStatement pstmt=null;
	public static ResultSet rs=null;
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 */	
	public static Connection getConnection(){
		
		//ʹ��JNDI������ȡ����Դ���ٴ�����Դ�л�ȡ���ӣ�context.xml�����ã�web.xml�����ã�����jar��
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
	 * �ر���Դ����
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
