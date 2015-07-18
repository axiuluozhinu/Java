package cn.edu.bzu.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.bzu.activity.ActivityShow;
import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.bean.Stores;
import cn.edu.bzu.data.AppraisalData;
import cn.edu.bzu.data.ConsumerData;
import cn.edu.bzu.data.StoresData;
import cn.edu.bzu.main.ConsumerLogin;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.SysApplication;
import cn.edu.bzu.personal.Discuss;

public class ShopInformation  extends ListActivity{

ListView listViewAll;
StoresData storedata=new StoresData();
AppraisalData appraisaldata=new AppraisalData();
Consumer consumer=new Consumer();
ConsumerData consumerdata=new ConsumerData();
Stores stores=new Stores();
int data = 0;
String storesid=null;
String account=null;
String[] BOYTYPE=null;
int length;
public void onCreate(Bundle savedInstanceState) {
	requestWindowFeature(Window.FEATURE_NO_TITLE);
super.onCreate(savedInstanceState);
	setContentView(R.layout.shopinformation);
	 SysApplication.getInstance().addActivity(this);	 	
	 Bundle extras = getIntent().getExtras();
       if (extras != null) {  	   
            data = Integer.parseInt(extras.getString("storesid")); 
            storesid=extras.getString("storesid");
       }
       BOYTYPE=appraisaldata.viewAppraisal(storesid);      
       length=BOYTYPE.length;      
                Stores stores = storedata.getStoresIntroduction(data);
                TextView textView1 = (TextView)findViewById(R.id.title);
    			textView1.setText(stores.getStoreName());
                TextView textView2 = (TextView)findViewById(R.id.value_storesname);
    			textView2.setText(stores.getStoreName());
    			TextView textView3 = (TextView)findViewById(R.id.value_storestype);
    			textView3.setText(stores.getType());
    			TextView textView4 = (TextView)findViewById(R.id.value_storesaddress);
    			textView4.setText(stores.getProvince()+stores.getCity()+stores.getTown()+stores.getAddress().trim());
    			TextView textView5 = (TextView)findViewById(R.id.value_storesphone);
    			textView5.setText(stores.getStoreApproval());
    			
    			listViewAll= (ListView)findViewById(android.R.id.list);   
    			setListAdapter(new SimpleAdapter(ShopInformation.this, getDate(), 
    	    			R.layout.common_listview_text,     			
    	    			new String[]{"text","img_pre"},
    	    			new int[]{R.id.text,R.id.img_pre}));
    			
    			
    			
    			Button button1=(Button) findViewById(R.id.button1);
    		   button1.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					
					account=Consumer.getClientAccount();
					 if(account==null)
					 {  
						 Intent intent1=new Intent(ShopInformation.this,ConsumerLogin.class);
						 startActivity(intent1);
					 }
					 else{
					Intent intent1=new Intent(ShopInformation.this,Discuss.class);	
					intent1.putExtra("discuss",storesid);
	            	startActivity(intent1);	            	            	
					 }
				}
			});
    	    	
    	
    		   Button button2=(Button) findViewById(R.id.button2);
    		   button2.setOnClickListener(new OnClickListener() {
				
    			  
				public void onClick(View v) {
					 String result="好";
					 account=Consumer.getClientAccount();
					if(account==null)
					 {  
						 Intent intent1=new Intent(ShopInformation.this,ConsumerLogin.class);
						 startActivity(intent1);
					 }
					
					else if(appraisaldata.appraise(Consumer.getClientAccount(), storesid, result).equals("1")){
						Toast.makeText(ShopInformation.this, "评论成功", Toast.LENGTH_LONG).show();
						finish();
					}
					else {
						Toast.makeText(ShopInformation.this, "网络异常，请稍后再试", Toast.LENGTH_LONG).show();
						finish();
					}
				}
			});
    		   
    		   //差评按钮及监听
    		   Button button3=(Button) findViewById(R.id.button3);
    		   button3.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
				
					 String result="差";
					 account=Consumer.getClientAccount();
					 if(account==null)
					 {  
						 Intent intent1=new Intent(ShopInformation.this,ConsumerLogin.class);
						 startActivity(intent1);
					 }
					 else if(appraisaldata.appraise(Consumer.getClientAccount(), storesid, result).equals("1")){
							Toast.makeText(ShopInformation.this, "评论成功", Toast.LENGTH_LONG).show();
							finish();
						}
						else{
							Toast.makeText(ShopInformation.this, "网络异常，请稍后再试", Toast.LENGTH_LONG).show();
							finish();
						}
				}
			});
    		   
    		   
    		   
	}
private List<Map<String,Object>> getDate(){
	List<Map<String,Object>> listBoy = new ArrayList<Map<String,Object>>();
	for (int i = 0; i < length; i++) {
		Map<String,Object> mapBoy = new HashMap<String, Object>();
		mapBoy.put("text",BOYTYPE[i]);
	
		mapBoy.put("img_pre",R.drawable.paopao);
		listBoy.add(mapBoy);
	}
	return listBoy;
}
public boolean onCreateOptionsMenu(Menu menu) {

    menu.add(0, 1, 1, "刷新");	    
    menu.add(0, 2, 2, "关于");
    menu.add(0,3,3,"退出");
    return super.onCreateOptionsMenu(menu);
} public boolean onOptionsItemSelected(MenuItem item) {     
	      if(item.getItemId() == 1){         
	    		Intent intent1=new Intent(ShopInformation.this,ShopInformation.class);
	    		intent1.putExtra("storesid", storesid);
				startActivity(intent1);	
      finish();
}    
	      else if(item.getItemId() == 2){       
	Intent intent=new Intent(ShopInformation.this,ActivityShow.class);
	startActivity(intent);
}     
	      else if(item.getItemId() == 3){       
	    	  account=Consumer.getClientAccount();
	    	  if(account!=null){		    	    		  
	    		  consumerdata.exit(account);
	    		  SysApplication.getInstance().exit();
	    	  }
	    	  else{
	      SysApplication.getInstance().exit();
	    	  }
	    	         }    
	     
	      return true;    }


}