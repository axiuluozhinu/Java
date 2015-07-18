package cn.edu.bzu.data;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import cn.edu.bzu.mysql.DatabaseConnection;
import cn.edu.bzu.bean.Active;
import cn.edu.bzu.bean.Business;
import cn.edu.bzu.bean.Consumer;

public class ActiveData {		
	/*
	 * ������ҳ�����ͼƬ
	 */
	public String[] activityScroll()throws IOException{
		String[] arr=new String[5];
		String sql="select imagePath from activity_business where activityStart<(select curdate()) and activityEnd>(select curdate()) limit 5;";	
        String[] image=new String[5];
		Connection conn;
		Statement st;	
		String[] uploadBuffer =new String[5];
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			//��ѯ���
			st=(Statement) conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			int i=0;
			while(rs.next()&&i<5){
				arr[i]=rs.getString("imagePath");
				i++;
			}
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("��ѯʧ��");
			e.printStackTrace();
		}
		for(int i=0;i<5;i++){
			image[i] =new String(); 
			 FileInputStream fis = new FileInputStream(arr[i]);
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 byte[] buffer = new byte[1024];
			 int count = 0; 
			 while((count = fis.read(buffer)) >= 0){  
		         baos.write(buffer, 0, count);  
		     }  
			  uploadBuffer[i] = new String(Base64.encode(baos.toByteArray()));
			  image[i]=uploadBuffer[i];
			 fis.close();
			 }
		return    image;
	}
	/*
	 * ��ѯ���ϸ��Ϣ
	 */
	public Active activityIntroduction(int id){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String sql1="select storeName from store where businessId=(" +
				"select businessId from activity_business where activityId="+id+");";
		String sql2="select * from activity_business where activityId="+id;
		Active active=new Active();
		try{
			conn=DatabaseConnection.getConnection();
			//��ѯ���
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				active.setStoreName(rs.getString(1));
			}
			rs=st.executeQuery(sql2);
			while(rs.next()){
				active.setId(rs.getString("activityId"));
				active.setTopic(rs.getString("activityTopic"));
				active.setAddress(rs.getString("activityAddress"));
				active.setContent(rs.getString("activityContent"));
				active.setStartTime(rs.getString("activityStart"));
				active.setEndTime(rs.getString("activityEnd"));
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("��ѯʧ��");
			e.printStackTrace();
		}
		return active;
	}
	/*
	 * ���ػ��Ϣ�б�
	 */
	public Active[] activityTopic(){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String sql1="select count(activityId) from activity_business;";
		String sql2="select activityId,activityTopic from activity_business";
		int i=0,length=0;
		Active[] active = null;
		try{
			conn=DatabaseConnection.getConnection();
			//��ѯ���
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				length=rs.getInt(1);
			}
			active=new Active[length];
			rs=st.executeQuery(sql2);
			while(rs.next()&&i<length){
				active[i]=new Active();
				active[i].setId(rs.getString("activityId"));
				active[i].setTopic(rs.getString("activityTopic"));
				if(active[i]==null){break;}
				i++;
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("��ѯʧ��");
			e.printStackTrace();
		}
		
		return active;
	}
	
	/*
	 * ����
	 */
	public void joinActivity(int activityid,String account){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		String sql1="select participantId from activity_business where activityId="+activityid+";";
		String sql3="update information_client set clientIntegral=clientIntegral+1 where clientAccount='"+account+"';";		
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				str=rs.getString(1);
			}
			String sql2="update activity_business set participantId='"+str+account+",' where activityId="+activityid+";";
			st.executeUpdate(sql2);
			st.executeUpdate(sql3);
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�μӻʧ��");
			e.printStackTrace();
		}
	}
	/*
	 * ȡ������
	 */
	public void cancelJoin(int activityid,String account){
		Connection conn=null;
		Statement st=null;
		String sql1="update activity_business set participantId=replace(participantId,'"+account+",','') " +
				"where activityId="+activityid+";";
		String sql2="update information_client set clientIntegral=clientIntegral-2 where clientAccount='"+account+"';";		
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			st.executeUpdate(sql1);
			st.executeUpdate(sql2);
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("ȡ���μӻʧ��");
			e.printStackTrace();
		}
	}

      
	/*
	 * �ͻ���֯�
	 */
	public void clientOrganizeActivity(String account,String topic,String address,String starttime,String endtime,String content){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		Consumer consumer=new Consumer();
		String sql1="select clientId from information_client where clientAccount='"+account+"';";	
		String sql3="update information_client set clientIntegral=clientIntegral+10 where clientAccount='"+account+"';";		
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				consumer.setClientId(rs.getInt("clientId"));
			}
			String sql2="insert into activity_client (clientId,activityTopic,activityAddress,activityStart,activityEnd,activityContent) values " +
			"("+consumer.getClientId()+",'"+topic+"','"+address+"','"+starttime+"','"+endtime+"','"+content+"');";
			st.executeUpdate(sql2);
			st.executeUpdate(sql3);
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�ͻ���֯�ʧ��");
			e.printStackTrace();
		}
	}

	
	/*
	 * �鿴����Ļ�б�  1�̼һ 2�ͻ��
	 */
	public Active[] viewJoinActivity(int mark,String account){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String sql1="select activityId,activityTopic from activity_business where participantId like '%"+account+"%' and cancel=0;";
		String sql2="select activityId,activityTopic from activity_client where participantId like '%"+account+"%' and cancel=0;";
		int i=0,length=0;
		Active[] active = null;
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			if(mark==1){
				rs=st.executeQuery(sql1);
				rs.last();
				length=rs.getRow();
			}else if(mark==2){
				rs=st.executeQuery(sql2);
				rs.last();
				length=rs.getRow();
			}
			active=new Active[length];
			rs.beforeFirst();
			while(rs.next()&&i<length){
				active[i]=new Active();
				active[i].setId(rs.getString("activityId"));
				active[i].setTopic(rs.getString("activityTopic"));
				i++;
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�鿴����Ļ�б�ʧ��");
			e.printStackTrace();
		}
		return active;
	}
	
	/*
	 * �̵���ڻ
	 */
	public Active[] RecentActivity(String storesid){
		Active[] active=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String str=null;
		int i=0,length=0;
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			String sql1="select businessId from store where storeId="+storesid+";";
			rs=st.executeQuery(sql1);
			while(rs.next()){
				str=rs.getString(1);
			}
			String sql2="select activityId,activityTopic from activity_business where activityStart<(select curdate()) " +
					"and activityEnd>(select curdate()) and businessId="+str+";";
			rs=st.executeQuery(sql2);
			rs.last();
			length=rs.getRow();
			active=new Active[length];
			rs.beforeFirst();
			while(rs.next()&&i<length){
				active[i]=new Active();
				active[i].setTopic(rs.getString("activityTopic"));
				active[i].setId(rs.getString("activityId"));
				i++;
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�̵���ڻ��ʾʧ��");
			e.printStackTrace();
		}
		return active;
	}
	
	/*
	 * ���زμӻ�ĺ����б�
	 */
	public String[] friendActivity(String account){
		String[] result=null;
		String[] str1=null;
		String[] str2=null;
		String[] str3=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String friendid=null;
		String[] friendAccount=null;
		int i=0,length=0;
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			String sql1="select friendId from friend_client where clientId=" +
					"(select clientId from information_client where clientAccount='"+account+"');";
			
			rs=st.executeQuery(sql1);
			while(rs.next()){
				friendid=rs.getString(1);
			}
			if(friendid==null){
				str1=new String[1];
				str1[0]="0";
			}else{
				str1=friendid.split(",");
			}
			length=str1.length;
			friendAccount=new String[length];
			for(i=0;i<length;i++){
				String sql2="select clientAccount from information_client where clientId="+str1[i]+";";
				rs=st.executeQuery(sql2);
				while(rs.next()){
					
					friendAccount[i]=rs.getString(1);
				}
			}
			if(friendAccount.length==0){
				friendAccount=new String[1];
				friendAccount[0]="";
			}
			result=new String[length];
			for(i=0;i<length;i++){
				String sql3="select activityId from activity_business where participantId like '%"+friendAccount[i]+",%';";
				String sql4="select activityId from activity_client where participantId like '%"+friendAccount[i]+",%';";
				rs=st.executeQuery(sql3);
				while(rs.next()){
					str2=new String[length];
					str2[i]=rs.getString(1);
				}
				rs=st.executeQuery(sql4);
				while(rs.next()){
					str3=new String[length];
					str3[i]=rs.getString(1);
				}
				if(str2==null&&str3==null){
					result=new String[1];
					result[0]="���ĺ���δ�μ��κλ";
				}else{
					result[i]=friendAccount[i];
				}
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("���زμӻ�ĺ����б�ʧ��");
			e.printStackTrace();
		}
		return result;
	}
	/*
	 * ���غ��ѲμӵĻ�б�
	 */
	public Active[]  friendJoinActivity(String account){
		Active[] active=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		int i=0,length=0;
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			String sql1="select activityId,activityTopic from activity_client where participantId like '%"+account+",%';";
			String sql2="select activityId,activityTopic from activity_business where participantId like '%"+account+",%';";
			rs=st.executeQuery(sql1);
			rs.last();
			length=rs.getRow();
			rs=st.executeQuery(sql2);
			rs.last();
			length+=rs.getRow();
			active=new Active[length];
			rs.beforeFirst();
			while(rs.next()&&i<length){
				active[i]=new Active();
				active[i].setTopic(rs.getString("activityTopic"));
				active[i].setId(rs.getString("activityId"));
				active[i].setMark(1);
				if(active[i]==null){
					break;
				}
				i++;
			}
			rs=st.executeQuery(sql1);
			while(rs.next()&&i<length){
				active[i]=new Active();
				active[i].setTopic(rs.getString("activityTopic"));
				active[i].setId(rs.getString("activityId"));
				active[i].setMark(2);
				i++;
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�̵���ڻ��ʾʧ��");
			e.printStackTrace();
		}
		return active;
	}
	/*
	 * �̼Ҳ鿴�Լ������Ļ
	 */
	public Active[] businessViewActivity(String account){
		Active[] active=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String businessId=null;
		int i=0,length=0;
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			String sql1="select businessId from information_business where businessAccount='"+account+"';";
			rs=st.executeQuery(sql1);
			while(rs.next()){
				businessId=rs.getString(1);	
			}
			String sql2="select activityId,activityTopic from activity_business where businessId="+businessId+";";
			rs=st.executeQuery(sql2);
			rs.last();
			length=rs.getRow();
			if(length==0){
				active=new Active[1];
				active[0]=new Active();
				active[0].setTopic("����δ�����κλ");
				return active;
			}
			active=new Active[length];
			rs.beforeFirst();
			while(rs.next()&&i<length){
				active[i]=new Active();
				active[i].setTopic(rs.getString("activityTopic"));
				active[i].setId(rs.getString("activityId"));
				active[i].setMark(1);
				if(active[i]==null){
					break;
				}
				i++;
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�̼Ҳ鿴�Լ������Ļʧ��");
			e.printStackTrace();
		}
		return active;
	}
	/*
	 * �ͻ��鿴�Լ������Ļ
	 */
	public Active[] clientViewActivity(String account){
		Active[] active=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String clientId=null;
		int i=0,length=0;
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			String sql1="select clientId from information_client where clientAccount='"+account+"';";
			rs=st.executeQuery(sql1);
			while(rs.next()){
				clientId=rs.getString(1);	
			}
			String sql2="select activityId,activityTopic from activity_client where clientId="+clientId+";";
			rs=st.executeQuery(sql2);
			rs.last();
			length=rs.getRow();
			if(length==0){
				active=new Active[1];
				active[0]=new Active();
				active[0].setTopic("����δ�����κλ");
				return active;
			}
			active=new Active[length];
			rs.beforeFirst();
			while(rs.next()&&i<length){
				active[i]=new Active();
				active[i].setTopic(rs.getString("activityTopic"));
				active[i].setId(rs.getString("activityId"));
				active[i].setMark(2);
				if(active[i]==null){
					break;
				}
				i++;
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�ͻ��鿴�Լ������Ļʧ��");
			e.printStackTrace();
		}
		return active;
	}
	
	/*
	 * �̼ҷ����
	 */
	public void businessOrganizeActivity(String account,String topic,String address,String starttime,String endtime,String content){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		Business business=new Business();
		String sql1="select businessId from information_business where businessAccount='"+account+"';";	
		String sql3="update information_business set businessCredibility=businessCredibility+50 where businessAccount='"+account+"';";
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			rs=st.executeQuery(sql1);
			while(rs.next()){
				business.setBusinessId(rs.getInt("businessId"));
			}
			String sql2="insert into activity_business (businessId,activityTopic,activityAddress,activityStart,activityEnd,activityContent) values " +
			"("+business.getBusinessId()+",'"+topic+"','"+address+"','"+starttime+"','"+endtime+"','"+content+"');";
			st.executeUpdate(sql2);
			st.executeUpdate(sql3);
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�̼ҷ����ʧ��");
			e.printStackTrace();
		}
	}

	
	/*
	 * ���ػ��Ϣ�б�   1�̼һ 2�ͻ��(�ڶ���)
	 */
	public Active[] activityTopic(int mark){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String sql1="select activityId,activityTopic from activity_business where cancel=0;";
		String sql2="select activityId,activityTopic from activity_client where cancel=0;";
		int i=0,length=0;
		Active[] active = null;
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			if(mark==1){
				rs=st.executeQuery(sql1);
			}else if(mark==2){
				rs=st.executeQuery(sql2);
			}
			rs.last();
			length=rs.getRow();
			active=new Active[length];
			rs.beforeFirst();
			while(rs.next()&&i<length){
				active[i]=new Active();
				active[i].setId(rs.getString("activityId"));
				active[i].setTopic(rs.getString("activityTopic"));
				if(active[i]==null){break;}
				i++;
			}
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("��ѯ���Ϣʧ��");
			e.printStackTrace();
		}
		return active;
	}
	/*
	 * �ͻ��޸Ļ
	 */
	public void alterClientActyivity(String account,String activityid,String topic,String address,String starttime,String endtime,String content){
		Connection conn=null;
		Statement st=null;
		String sql1="update information_client set clientIntegral=clientIntegral-30 where clientAccount='"+account+"';";
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			String sql2="update activity_client set activityTopic='"+topic+"',activityAddress='"+address+"',activityStart='"+starttime+"'," +
					"activityEnd='"+endtime+"',activityContent='"+content+"',modify=1 where activityId="+activityid+";";
			st.executeUpdate(sql1);
			st.executeUpdate(sql2);
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�ͻ��޸Ļʧ��");
			e.printStackTrace();
		}
	}

	/*
	 * �ͻ�ȡ���
	 */
	public void cancelClientActivity(String account,int activityid){
		String id=String.valueOf(activityid);
		Connection conn=null;
		Statement st=null;
		String sql1="update information_client set clientIntegral=clientIntegral-30 where clientAccount='"+account+"';";
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			String sql2="update activity_business set cancel=1 where activityId="+id+";";
			st.executeUpdate(sql1);
			st.executeUpdate(sql2);
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�ͻ�ȡ���ʧ��");
			e.printStackTrace();
		}
	}

	/*
	 * �̼��޸Ļ��Ϣ
	 */
	public void alterBusinessActyivity(String account,String activityid,String topic,String address,String starttime,String endtime,String content){
		Connection conn=null;
		Statement st=null;
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			String sql1="update activity_business set activityTopic='"+topic+"',activityAddress='"+address+"',activityStart='"+starttime+"'," +
					"activityEnd='"+endtime+"',activityContent='"+content+"',modify=1 where activityId="+activityid+";";
			String sql2="update information_business set businessCredibility=businessCredibility-100 where businessAccount='"+account+"';";
			st.executeUpdate(sql1);
			st.executeUpdate(sql2);
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�̼��޸Ļʧ��");
			e.printStackTrace();
		}
	}
	/*
	 * �̼�ȡ���
	 */
	public void cancelBusinessActivity(String account,String activityid){
		Connection conn=null;
		Statement st=null;
		try{
			conn=(Connection) DatabaseConnection.getConnection();
			st=(Statement) conn.createStatement();
			String sql1="update activity_business set cancel=1 where activityId="+activityid+";";
			String sql2="update information_business set businessCredibility=businessCredibility-100 where businessAccount='"+account+"';";
			st.executeUpdate(sql1);
			st.executeUpdate(sql2);
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�̼�ȡ���ʧ��");
			e.printStackTrace();
		}
	}
	/*
	 * �ж��Ƿ������
	 */
	/*
	 * �ͻ��֪ͨ(�̼һ)
	 */
	public Active[] businessAciveMessage(String account){
		Active[] active=null;
		String[] id=null;
		String[] topic=null;
		int[] modify=null;
		int[] cancel=null;
		String[] str1=null;
		String[] str2=null;
		int count=0;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String sql="select activityId,activityTopic,modify,cancel from activity_business where participantId like '%"+account+"%' and activityStart<(select curdate()) and activityEnd>(select curdate());";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			rs.last();
			int length=rs.getRow();
			if(length==0){
				active=new Active[1];
				active[0]=new Active();
				active[0].setTopic("������Ϣ");
				return active;
			}
			id=new String[length];
			topic=new String[length];
			modify=new int[length];
			cancel=new int[length];
			str1=new String[length];
			str2=new String[length];
			rs.beforeFirst();
			int i=0;
			while(rs.next()&&i<length){
				id[i]=rs.getString("activityId");
				topic[i]=rs.getString("activityTopic");
				modify[i]=rs.getInt("modify");
				cancel[i]=rs.getInt("cancel");
				if(modify[i]==1){
					str1[count]="������"+topic[i]+"��ѱ��޸ģ������������ݡ�";
					str2[count]=id[i];
					count++;
				}
				if(cancel[i]==1){
					str1[count]="���������ȡ��"+topic[i]+"��ķ���";
					str2[count]=id[i];
					count++;
				}
				i++;
			}
			active=new Active[count];
			for(i=0;i<count;i++){
				active[i]=new Active();
				active[i].setId(str2[i]);
				active[i].setTopic(str1[i]);
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("���ػ֪ͨʧ��");
			e.printStackTrace();
		}
		return active;
	}
	/*
	 * �ͻ��֪ͨ(�ͻ��)
	 */
	public Active[] clientAciveMessage(String account){
		Active[] active=null;
		String[] id=null;
		String[] topic=null;
		int[] modify=null;
		int[] cancel=null;
		String[] str1=null;
		String[] str2=null;
		int count=0;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String sql="select activityId,activityTopic,modify,cancel from activity_client where participantId like '%"+account+"%' and activityStart<(select curdate()) and activityEnd>(select curdate());";
		try{
			conn=DatabaseConnection.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			rs.last();
			int length=rs.getRow();
			if(length==0){
				active=new Active[1];
				active[0]=new Active();
				active[0].setTopic("������Ϣ");
				return active;
			}
			id=new String[length];			
			topic=new String[length];
			modify=new int[length];
			cancel=new int[length];
			str1=new String[length];
			str2=new String[length];
			rs.beforeFirst();
			int i=0;
			while(rs.next()&&i<length){
				id[i]=rs.getString("activityId");
				topic[i]=rs.getString("activityTopic");
				modify[i]=rs.getInt("modify");
				cancel[i]=rs.getInt("cancel");
				if(id[i]==null){
					active=new Active[1];
					active[0]=new Active();
					active[0].setTopic("������Ϣ");
					return active;
				}
				if(modify[i]==1){
					str1[count]="������"+topic[i]+"��ѱ��޸ģ������������ݡ�";
					str2[count]=id[i];
					count++;
				}
				if(cancel[i]==1){
					str1[count]="���������ȡ��"+topic[i]+"��ķ���";
					str2[count]=id[i];
					count++;
				}
				i++;
			}
			active=new Active[count];
			for(i=0;i<count;i++){
				active[i]=new Active();
				active[i].setId(str2[i]);
				active[i].setTopic(str1[i]);
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("���ػ֪ͨʧ��");
			e.printStackTrace();
		}
		return active;
	}
	/*
	 * ����   1�̼һ 2�ͻ��
	 */
	public void joinActivity(int mark,int activityid,String account){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String str1=null;
		String str2=null;
		String sql="update information_client set clientIntegral=clientIntegral+1 where clientAccount='"+account+"';";
		String sql1="select participantId from activity_business where activityId="+activityid+";";
		String sql2="select participantId from activity_client where activityId="+activityid+";";
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
			String sql3="update activity_business set participantId='"+str1+account+",' where activityId="+activityid+";";
			String sql4="update activity_client set participantId='"+str2+account+",' where activityId="+activityid+";";
			if(mark==1){
				st.executeUpdate(sql3);
			}else if(mark==2){
				st.executeUpdate(sql4);
			}	
			st.executeUpdate(sql);
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("�μӻʧ��");
			e.printStackTrace();
		}
	}
}
