package cn.edu.bzu.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public static Connection getConnection() {
		Connection con = null;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/goshopping";
		String user="root";
		String password="7232027110abc";
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);		
		}catch(ClassNotFoundException e){
			System.out.println("��������ʧ�ܣ�");
			e.printStackTrace();
		}catch(Exception e){
			System.out.println("���ݿ�����ʧ�ܣ�");
			e.printStackTrace();
		}
		return con;
	}
}

