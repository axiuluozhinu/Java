package cn.edu.bzu.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.edu.bzu.activity.ActivityShow;
import cn.edu.bzu.bean.Business;
import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.data.ConsumerData;
import cn.edu.bzu.friends.Chat;
import cn.edu.bzu.friends.FriendList;
import cn.edu.bzu.friends.FriendsActiveList;
import cn.edu.bzu.map.Maps;
import cn.edu.bzu.personal.Personal;
import cn.edu.bzu.seller.Seller;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class More extends ListActivity implements OnItemClickListener {
    /** Called when the activity is first created. */
 ListView listViewAll;
 String account=null;
 ConsumerData consumerdata=new ConsumerData();
  public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);

    	ListView listViewAll;
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.more); 
    	
    	SysApplication.getInstance().addActivity(this);
    	
    	listViewAll= (ListView)findViewById(android.R.id.list);   	
    	
    	String data=null;
		 Bundle extras = getIntent().getExtras();
	        if (extras != null) {
	             data = extras.getString("tltle");
	        }
	        TextView textViewTitle = (TextView)findViewById(R.id.title);
			textViewTitle.setText(data);
    	
    	setListAdapter(new SimpleAdapter(More.this, getDate(), 
    			R.layout.common_listview_text, 
    			new String[]{"img","text","img_pre"},
    			new int[]{R.id.img,R.id.text,R.id.img_pre}));
	     listViewAll.setOnItemClickListener(this);
    	  
    LinearLayout menu1 = (LinearLayout)findViewById(R.id.menu1);          	
    	 menu1. setOnClickListener(menu); 
    LinearLayout menu2 = (LinearLayout)findViewById(R.id.menu2);          	
    	 menu2. setOnClickListener(menu); 
    LinearLayout menu3 = (LinearLayout)findViewById(R.id.menu3);          	
    	 menu3. setOnClickListener(menu); 
    LinearLayout menu4 = (LinearLayout)findViewById(R.id.menu4);          	
    	 menu4. setOnClickListener(menu); 
    LinearLayout menu5 = (LinearLayout)findViewById(R.id.menu5);          	
    	 menu5. setOnClickListener(menu); 	
    }

    private OnClickListener menu=new OnClickListener() {
		@Override
		public void onClick(View v) {
			LinearLayout menu=(LinearLayout) v;
		switch (menu.getId()) {
		case R.id.menu1:
			Intent intent1=new Intent(More.this,Index.class);
			intent1.putExtra("tltle", "首页");
			startActivity(intent1);				
			break;		
		case R.id.menu2:
			Intent intent2=new Intent(More.this,Type.class);
			intent2.putExtra("tltle", "分类");
			startActivity(intent2);					
			break;
		case R.id.menu3:
			Intent intent3=new Intent(More.this,StoresType.class);
			intent3.putExtra("tltle", "商家列表");
			startActivity(intent3);					
			break;
		case R.id.menu4:
			Intent intent4=new Intent(More.this,Maps.class);
			intent4.putExtra("tltle", "百乐淘");
			startActivity(intent4);					
			break;
		case R.id.menu5:
			Intent intent5=new Intent(More.this,More.class);
			intent5.putExtra("tltle", "更多");
			startActivity(intent5);					
			break;
		default:
			break;
		}	}		
	};
	
	private List<Map<String,Object>> getDate(){
		List<Map<String,Object>> listBoy = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < BOYTYPE.length; i++) {
			Map<String,Object> mapBoy = new HashMap<String, Object>();
			mapBoy.put("text",BOYTYPE[i]);
			mapBoy.put("img",R.drawable.go);
			mapBoy.put("img_pre",R.drawable.paopao);
			listBoy.add(mapBoy);
		}
		return listBoy;
	}
	static final String[] BOYTYPE = {"个人中心","商家活动","好友活动","好友","消费者登陆","商家登陆","商铺管理"};
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg5) {
		
			if(arg2==0){
				account=Consumer.getClientAccount();
				if(account==null)
				 {  
					 Intent intent0=new Intent( More.this,ConsumerLogin.class);
					 startActivity(intent0);
				 }
				 else 
				 {
				Intent intent0=new Intent(More.this,Personal.class);
				startActivity(intent0);	
				 }
		}
	    	if(arg2==1){	    		
				Intent intent1=new Intent(More.this,Activitys.class);
				startActivity(intent1);				 
		}
			if(arg2==2){
				account=Consumer.getClientAccount();
				if(account==null)
				 {  
					 Intent intent2=new Intent( More.this,ConsumerLogin.class);
					 startActivity(intent2);
				 }
				 else {
				 Intent intent2 = new Intent(More.this,FriendsActiveList.class);
				startActivity(intent2);	
			}
			}
			
				
				if(arg2==3){
					account=Consumer.getClientAccount();
					if(account==null)
					 {  
						 Intent intent3=new Intent( More.this,ConsumerLogin.class);
						 startActivity(intent3);
					 }
					 else {
					 Intent intent3 = new Intent(More.this,Chat.class);
					startActivity(intent3);	
				}	
				
				}
				
				if(arg2==4)	{						
					 Intent intent3=new Intent( More.this,ConsumerLogin.class);
					 startActivity(intent3);											
			}
				if(arg2==5){					
					 Intent intent3=new Intent( More.this,BusinessLogin.class);
					 startActivity(intent3);							
			}
			
				if(arg2==6){					
					account=Business.getBusinessAccount();
					if(account==null)
					 {  
						 Intent intent3=new Intent( More.this,BusinessLogin.class);
						 startActivity(intent3);
					 }
					 else {
					 Intent intent3 = new Intent(More.this,Seller.class);
					startActivity(intent3);	
				}							
			}
			
	
		
	}
	 public boolean onCreateOptionsMenu(Menu menu) {

	        menu.add(0, 1, 1, "刷新");	    
	        menu.add(0, 2, 2, "关于");
	        menu.add(0,3,3,"退出");
	        return super.onCreateOptionsMenu(menu);
	    } public boolean onOptionsItemSelected(MenuItem item) {     
	    	      if(item.getItemId() == 1){         
	    	    		Intent intent1=new Intent(More.this,More.class);
						
						startActivity(intent1);	
           finish();
     }    
	    	      else if(item.getItemId() == 2){       
 		Intent intent=new Intent(More.this,ActivityShow.class);
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