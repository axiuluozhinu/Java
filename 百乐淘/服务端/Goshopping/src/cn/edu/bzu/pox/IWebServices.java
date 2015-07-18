package cn.edu.bzu.pox;

//WSDL URL 
//http://localhost:8080/Goshopping/services/WebServices?wsdl 

import java.io.IOException;

import cn.edu.bzu.bean.Active;
import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.bean.Goods;
import cn.edu.bzu.bean.Stores;

public interface IWebServices {
	
	
	public Goods[] goodsIndex()throws IOException;
	public Goods[] goods_rank_show(String name) throws IOException;
	public Goods   goodsIntroduce(int goodsid) throws IOException;
	public Active activityIntroduction(int id);
	public Active[] activityTopic();
	public String[] activityImage()throws IOException;
	public String loginChecking(String clientAccount,String clientPassword);
	public String register(String clientAccount,String clientPassword,String clientPhonenumber);
	public void scheduled(int goodsid,String account);
	public void Cancellation(int goodsid,String account);
	public void joinActivity(int activityid,String account);
	public void cancelJoin(int activityid,String account);
	public Stores storesIntroduction(int storeid);
	public Stores[] storesType();
	public Stores[] storesName(String type);
	public String appraisalContent(String storeid,String account,String content);
	public String appraisal(String account,String storeid,String app);
	public void exit(String account);
	public String leaveMessage(String account,String friendid,String word);
	public String[] viewMessage(String account);
	public void modification(String account,String phonenumber,String sex,String name,String age,String address);
	public Consumer clientIntroduction(String clientAccount);
	public String shareGoods(String account,int goodsid);
	public Goods[] viewShareGooods(String account);
	public String[] goodsType();
	public String clientOrganizeActivity(String account,String topic,String address,String starttime,String endtime,String content);
	public Consumer[] viewFriend(String account);
	public Goods[] goodsImage(String type)throws IOException;
	public Goods[] viewScheduled(String account);
	public Active[] viewJoinActivity(int mark,String account);
	public Stores[] storesInformation();
	public Goods[] storeGoodsImage(String storesId)throws IOException;
	public Active[] RecentActivity(String storesid);
	public String[] viewAppraisal(String storeid);
	public Boolean addFriend(String account,String friendAccount);
	public void agreeAdd(String account,String agree);
	public String[] confirmFriend(String account);
	public String Shelves(String account,String goodsName,String type,String model,String brand,String unit,String material,String color,String goodsimagecode);
	public String CancelShelves(int goodsid,String account);
	public void businessExit(String account);
	public String businessRegister(String account,String password,String phonenumber);
	public String businessLoad(String account,String password);
	public Active[]  friendJoinActivity(String account);
	public String[] friendActivity(String account);
	public void storeRegister(String account,String storeName,String type,String storeApproval,String province,String city,String town,String address,String longitude,String latitude);
	public Stores[] mystoreName(String account);
	public Active[] businessViewActivity(String account);
	public Active[] clientViewActivity(String account);
	public String businessOrganizeActivity(String account,String topic,String address,String starttime,String endtime,String content);
	public int businessCredibility(String goodsid,String account);
	public int clientIntegral(String account);
	public String alterClientActyivity(String account,String activityid,String topic,String address,String starttime,String endtime,String content);
	public String cancelClientActivity(String account,int activityid);
	public String alterBusinessActyivity(String account,String activityid,String topic,String address,String starttime,String endtime,String content);
	public String cancelBusinessActivity(String account,String activityid);
	public Goods[] LocalAreaGoodsImage(String address)  throws IOException;
	public String[] viewScheduledClient(String account);
	public Goods[] localClientScheduledGoods(String businessAccount,String clientAccount);
	public Stores[] localBusinessCredibility(String star,String city);
	public Active[] businessAciveMessage(String account);
	public Active[] clientAciveMessage(String account);
}