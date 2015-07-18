package cn.edu.bzu.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.bean.Goods;
import cn.edu.bzu.mysql.DatabaseConnection;

public class ConsumerData {
	/*
	 * ������ע����Ϣ¼��
	 */
	public String consumerRegister(String clientAccount,String clientPassword,String clientPhonenumber){
		

		
		Connection conn=null;
		Statement st=null;	
		ResultSet rs=null;
        String reslut=null;
		int length = 0,i=0;
		String sql1="select count(clientAccount) from information_client;";
		String sql2="select clientAccount from information_client;";
		String sql3="insert into information_client (clientAccount,clientPassword,clientPhonenumber) " +
				"values ('"+clientAccount+"','"+clientPassword+"','"+clientPhonenumber+"');";		
		try{
			conn=DatabaseConnection.getConnection();
			//�������
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				length=rs.getInt(1);
			}
			rs=st.executeQuery(sql2);
			String[] str=new String[length];
			while(rs.next()&&i<length){
				str[i]=rs.getString("clientAccount");
				i++;
			}
			for(int t=0;t<length;t++){
				if(clientAccount.equals(str[t])){
					
					reslut="0";
				
				}
				if(t==length-1){
					st.executeUpdate(sql3);
					reslut="1";
					
				}
				
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("����ʧ��");
			e.printStackTrace();
		}
		
		return reslut;
	}
	/*
	 * �����ߵ�½ʱ�����֤
	 */
	public String consumerLoad(String account,String password){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		int length = 0,i=0;
		String sql1="select count(clientAccount) from information_client;";
		String sql2="select clientPassword from information_client where clientAccount='"+account+"'";
		String sql3="update information_client set login=1 where clientAccount='"+account+"'";
		String sql4="select clientAccount from information_client;";
		String passWord=null;
		try{
			conn=DatabaseConnection.getConnection();
			//�������
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				length=rs.getInt(1);
			}
			rs=st.executeQuery(sql2);
			while(rs.next()){
			passWord=rs.getString(1);
			}
			rs=st.executeQuery(sql4);
			String[] str=new String[length];
			while(rs.next()&&i<length){
				str[i]=rs.getString("clientAccount");
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
			System.out.println("��֤ʧ��");
			e.printStackTrace();
		}
		return "0";
	}
	
	/*
	 * Ԥ����Ʒ
	 */
	public void scheduled(int goodsid,String account){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		String sql1="update goods set hot=hot+1,scheduled=scheduled+1 where goodsId="+goodsid+";";		
		String sql3="select goodsScheduled from information_client where clientAccount='"+account+"';";
		String sql4="update information_client set clientIntegral=clientIntegral+5 where clientAccount='"+account+"';";		
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql3);
			while(rs.next()){
				str=rs.getString(1);
			}
			st.executeUpdate(sql1);
			String sql2="update information_client set goodsScheduled='"+str+goodsid+",' where clientAccount='"+account+"';";
			st.executeUpdate(sql2);
			st.executeUpdate(sql4);
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("Ԥ��ʧ��");
			e.printStackTrace();
		}
	}

	/*
	 * ȡ��Ԥ��
	 */
	public void Cancellation(int goodsid,String account){
		Connection conn=null;
		Statement st=null;
		String sql1="update goods set hot=hot-1,scheduled=scheduled-1 where goodsId="+goodsid+";";
		String sql2="update information_client set goodsScheduled=replace(goodsScheduled,'"+goodsid+",','') " +
				"where clientAccount='"+account+"';";
		String sql3="update information_client set clientIntegral=clientIntegral-10 where clientAccount='"+account+"';";		
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			st.executeUpdate(sql1);
			st.executeUpdate(sql2);
			st.executeUpdate(sql3);
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("ȡ��Ԥ��ʧ��");
			e.printStackTrace();
		}
	}

	
	/*
	 * �˳�
	 */
	public void exit(String account){
		Connection conn=null;
		Statement st=null;
		String sql="update information_client set login=0 where clientAccount='"+account+"';";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			st.executeUpdate(sql);
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("��¼ʧ��");
			e.printStackTrace();
		}
	}
	
	/*
	 * ����
	 */
	public void leaveMessage(String account,String friendid,String word){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		int id=0;
		String sql1="select clientId from information_client where clientAccount='"+account+"'";
		
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				id=rs.getInt(1);
			}
			String sql2="insert into leave_word (clientId,friendId,leaveWord) values ("+id+","+friendid+",'"+word+"')";
			st.executeUpdate(sql2);
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("����ʧ��");
			e.printStackTrace();
		}
	}
	/*
	 * �鿴����
	 */
	public String[] viewMessage(String account){
		String[] str=null;
		String[] friendid=null;
		String[] friendAccount=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		int length=0,i=0;
		String clientid = null;
		String sql2="select clientId from information_client where clientAccount='"+account+"';";
		
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql2);
			while(rs.next()){
				clientid=rs.getString(1);
			}
			String sql3="select friendId from leave_word where clientId="+clientid+";";
			rs=st.executeQuery(sql3);
			rs.last();
			length=rs.getRow();
			friendid=new String[length];
			friendAccount=new String[length];
			str=new String[length];
			rs.beforeFirst();
			if(length==0){
				str=new String[1];
				str[0]="��������";return str;
			}
			while(rs.next()&&i<length){
				friendid[i]=rs.getString(1);
				i++;
			}
			
			for(i=0;i<length;i++){
				String sql4="select clientAccount from information_client where clientId="+friendid[i]+";";
				rs=st.executeQuery(sql4);
				while(rs.next()){
					friendAccount[i]=rs.getString(1);
				}
				if(friendAccount[i]==null){
					friendAccount[i]="����";
				}
			}
			String sql5="select leaveWord from leave_word where clientId="+clientid+";";
			rs=st.executeQuery(sql5);
			
			i=0;
			while(rs.next()&&i<length){
				str[i]=rs.getString("leaveWord")+" --- "+friendAccount[i];
				i++;
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�鿴����ʧ��");
			e.printStackTrace();
		}
		return str;
	}




	/*
	 * �޸ĸ�����Ϣ
	 */
	public void modification(String account,String phonenumber,String sex,String name,String age,String address){
		Connection conn=null;
		Statement st=null;
		String sql="update information_client set clientPhonenumber='"+phonenumber+"',realName='"+name+"',sex='"+sex+"'," +
				"age="+age+",address='"+address+"' where clientAccount='"+account+"';";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			st.executeUpdate(sql);
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�޸���Ϣʧ��");
			e.printStackTrace();
		}
	}
	/*
	 * �鿴���˺ͺ�����Ϣ
	 */
	public Consumer clientIntroduction(String clientAccount){
		Consumer consumer=new Consumer();
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String sql="select * from information_client where clientAccount='"+clientAccount+"';";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				consumer.setAddress(rs.getString("address"));
				consumer.setAge(rs.getInt("age"));
				consumer.setClientPassword(rs.getString("clientPassword"));
				consumer.setClientPhonenumber(rs.getString("clientPhonenumber"));
				consumer.setRealName(rs.getString("realName"));
				consumer.setSex(rs.getString("sex"));
				consumer.setIntegral(rs.getInt("clientIntegral"));
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�鿴������Ϣʧ��");
			e.printStackTrace();
		}
		return consumer;
	}
	
	/*
	 * ����
	 */
	public void shareGoods(String account,int goodsid){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		String sql1="select shareGoods from information_client where clientAccount='"+account+"';";
		String sql3="update information_client set clientIntegral=clientIntegral+1 where clientAccount='"+account+"';";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				str=rs.getString(1);
			}
			String sql2="update information_client set shareGoods='"+str+goodsid+",' where clientAccount='"+account+"'";
			st.executeUpdate(sql2);
			st.executeUpdate(sql3);
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("����ʧ��");
			e.printStackTrace();
		}
	}

	/*
	 * �鿴���ѷ���
	 */
	public Goods[] viewShareGooods(String account){
		Goods[] goods=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String[] str=null;
		String result=null;
		String clientid=null;
		String sql1="select clientId from information_client where clientAccount='"+account+"';";
		
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				clientid=rs.getString(1);
			}
			String sql2="select shareGoods from information_client where clientId="+clientid+";";
			rs=st.executeQuery(sql2);
			while(rs.next()){
				result=rs.getString(1);
			}
			str=result.split(",");
			goods=new Goods[str.length];
			for(int i=0;i<str.length;i++){
				goods[i]=new Goods();
				String sql3="select goodsName,goodsId from goods where goodsId="+str[i]+";";
				rs=st.executeQuery(sql3);
				goods[i].setGoodsId(Integer.parseInt(str[i]));
				while(rs.next()){
					goods[i].setGoodsId(rs.getInt("goodsId"));
					goods[i].setGoodsName(rs.getString("goodsName"));
				}
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�鿴���ѷ���ʧ��");
			e.printStackTrace();
		}
		return goods;
	}

	/*
	 * �鿴�����б�
	 */
	public Consumer[] viewFriend(String account){
		Consumer[] consumer=null;
		Connection conn;
		Statement st;
		ResultSet rs;
		String[] str=null;
		String str1=null;
		String str2=null;
		String sql1="select clientId from information_client where clientAccount='"+account+"';";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				str1=rs.getString(1);
			}
			String sql2="select friendId from friend_client where clientId='"+str1+"';";
			rs=st.executeQuery(sql2);
			while(rs.next()){
				str2=rs.getString(1);
			}
			str=str2.split(",");
			consumer=new Consumer[str.length];
			for(int i=0;i<str.length;i++){
				consumer[i]=new Consumer();
				String sql3="select clientAccount from information_client where clientId="+str[i]+";";
				rs=st.executeQuery(sql3);
				while(rs.next()){
					consumer[i].setClientAccount(rs.getString(1));
				}
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�鿴�����б�ʧ��");
			e.printStackTrace();
		}
		return consumer;
	}
	
	
	/*
	 * �鿴Ԥ���б�
	 */
	public Goods[] viewScheduled(String account){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String[] str=null;
		String result=null;
		Goods[] goods=null;
		String sql1="select goodsScheduled from information_client where clientAccount='"+account+"';";	
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				result=rs.getString(1);
			}
			str=result.split(",");
			goods=new Goods[str.length];
			for(int i=0;i<str.length;i++){
				goods[i]=new Goods();
				String sql2="select goodsName from goods where goodsId="+str[i]+";";
				rs=st.executeQuery(sql2);
				goods[i].setGoodsId(Integer.parseInt(str[i]));
				while(rs.next()){
					goods[i].setGoodsName(rs.getString("goodsName"));
				}
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�鿴Ԥ���б�ʧ��");
			e.printStackTrace();
		}
		return goods;
	}
	
	/*
	 * ��Ӻ���
	 */
	
	public Boolean addFriend(String account,String friendAccount){
		Connection conn;
		Statement st;
		ResultSet rs;
		String[] friend;
		Boolean b=false;
		String result=null;
		String str1=null;
		String str2=null;
		String str3=null;
		String str4=null;
		String str5=null;
		String sql1="select clientId from information_client where clientAccount='"+account+"';";
		String sql2="select clientId from information_client where clientAccount='"+friendAccount+"';";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				str1=rs.getString(1);
			}
			rs=st.executeQuery(sql2);
			while(rs.next()){
				str2=rs.getString(1);
			}
			String sql3="select clientId from friend_client where clientId="+str1+";";
			String sql4="select clientId from friend_client where clientId="+str2+";";
			String sql5="insert into friend_client (clientId) values ('"+str1+"');";
			String sql6="insert into friend_client (clientId,invite) values ('"+str2+"','"+str1+",');";
			String sql8="select invite from friend_client where clientId="+str2+";";
			String sql9="select friendId from friend_client where clientId="+str2+";";
			rs=st.executeQuery(sql3);
			while(rs.next()){
				str3=rs.getString(1);
			}
			rs=st.executeQuery(sql4);
			while(rs.next()){
				str4=rs.getString(1);
			}
			rs=st.executeQuery(sql8);
			while(rs.next()){
				str5=rs.getString(1);
			}
			rs=st.executeQuery(sql9);
			while(rs.next()){
				result=rs.getString(1);
			}
			if(result==null){
				friend=new String[1];
				friend[0]="";
			}else{
				friend=result.split(",");
			}

			String sql7;
			Boolean bl=true;
			for(int i=0;i<friend.length;i++){
				if(friend[i].equals(str1)){bl=false;}
			}
			if(bl==true){
				if(str5==null){
					sql7="update friend_client set invite='"+str1+",' where clientId="+str2+";";
				}else{
					sql7="update friend_client set invite='"+str5+str1+",' where clientId="+str2+";";
				}
				if(str3==null&&str4==null){
					st.executeUpdate(sql5);
					st.executeUpdate(sql6);
				}else if(str3==null&&str4.equals(str2)){
					st.executeUpdate(sql5);
					st.executeUpdate(sql7);
				}else if(str4==null&&str3.equals(str1)){
					st.executeUpdate(sql6);
				}else{
					st.executeUpdate(sql7);
				}
				b=true;
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("��Ӻ�������ʧ��");
			e.printStackTrace();
		}
		return b;
	}

	/*
	 * ͬ��/��ͬ�����Ϊ����
	 */
	public void agreeAdd(String account,String agree){
		Connection conn;
		Statement st;
		ResultSet rs;
		String str1=null;
		String str2=null;
		String[] str=null;
		String sql1="select clientId from information_client where clientAccount='"+account+"';";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				str1=rs.getString(1);
			}
			String sql2="select invite from friend_client where clientId="+str1+";";
			rs=st.executeQuery(sql2);
			while(rs.next()){
				str2=rs.getString(1);
			}
			str=str2.split(",");
			String sql3="update friend_client set friendId='"+str[0]+",' where clientId="+str1+";";
			String sql4="update friend_client set friendId='"+str1+",' where clientId="+str[0]+";";
			String sql5="update friend_client set invite=replace(invite,'"+str[0]+",','') where clientId="+str1+";";
			if(agree.equals("Yes")){
				st.executeUpdate(sql3);
				st.executeUpdate(sql4);
				st.executeUpdate(sql5);
			}else if(agree.equals("No")){
				st.executeUpdate(sql5);
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("ͬ��/��ͬ����Ӻ���ʧ��");
			e.printStackTrace();
		}
	}
	/*
	 * ���ش�ȷ�Ϻ����û���
	 */
	public String[] confirmFriend(String account){
		String[] str=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String result=null;
		String sql1="select invite from friend_client where clientId=(select clientId from information_client where clientAccount='"+account+"');";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				result=rs.getString(1);
			}
			str=result.split(",");
			for(int i=0;i<str.length;i++){
				if(result.equals("")){
					str[i]="������Ϣ";break;
				}
				str[i]=str[i]+" ���������Ϊ����";
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("���ش�ȷ�Ϻ����û���ʧ��");
			e.printStackTrace();
		}
		return str;
	}
	/*
	 *���������߻���
	 */
	public int clientIntegral(String account){
		Connection conn;
		Statement st;
		ResultSet rs;
		int result=0;
		String sql="select clientIntegral from information_client where clientAccount='"+account+"';";
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				result=rs.getInt(1);
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("���������߻���ʧ��");
			e.printStackTrace();
		}
		return result;
	}
}
