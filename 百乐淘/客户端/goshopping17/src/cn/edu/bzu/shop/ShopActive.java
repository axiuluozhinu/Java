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
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import cn.edu.bzu.activity.ActivityShow;
import cn.edu.bzu.bean.Active;
import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.data.ActiveData;
import cn.edu.bzu.data.ConsumerData;
import cn.edu.bzu.main.Activitys;
import cn.edu.bzu.main.Index;
import cn.edu.bzu.main.More;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.StoresType;
import cn.edu.bzu.main.SysApplication;
import cn.edu.bzu.main.Type;
import cn.edu.bzu.map.Maps;

public class ShopActive extends ListActivity implements OnItemClickListener{
    /** Called when the activity is first created. */    
    String data=null;  
    String account=null;
    ConsumerData consumerdata=new ConsumerData();
    ActiveData activeData=new ActiveData();
    String[] BOYTYPE = null;
    int length=0;
    int[] Id=null;
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.more);
    	SysApplication.getInstance().addActivity(this);
    	String data1=null;
   	 	Bundle extras1 = getIntent().getExtras();
          if (extras1 != null) {
               data1 = extras1.getString("name");
          }
          Bundle extras = getIntent().getExtras();
          if (extras != null) {
               data = extras.getString("storesid");
          }
          Active[] active=activeData.RecentActivity(data);
          length=active.length;
          Id=new int[length];
      	for(int i=0;i<Id.length;i++){
  	    	Id[i]=Integer.parseInt(active[i].getId());
  	    }
      	BOYTYPE=new String[length];
      	for(int i=0;i<BOYTYPE.length;i++){
  			BOYTYPE[i]=active[i].getTopic();
  		}
          
          TextView textViewTitle = (TextView)findViewById(R.id.title);
       			textViewTitle.setText(data1);
       			ListView listViewAll= (ListView)findViewById(android.R.id.list);  	
       	    	setListAdapter(new SimpleAdapter(ShopActive.this, getDate(), 
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
			Intent intent1=new Intent(ShopActive.this,Index.class);
			intent1.putExtra("tltle", "首页");
			startActivity(intent1);				
			break;		
		case R.id.menu2:
			Intent intent2=new Intent(ShopActive.this,Type.class);
			intent2.putExtra("tltle", "分类");
			startActivity(intent2);					
			break;
		case R.id.menu3:
			Intent intent3=new Intent(ShopActive.this,StoresType.class);
			intent3.putExtra("tltle", "商家列表");
			startActivity(intent3);					
			break;
		case R.id.menu4:
			Intent intent4=new Intent(ShopActive.this,Maps.class);
			intent4.putExtra("tltle", "百乐淘");
			startActivity(intent4);					
			break;
		case R.id.menu5:
			Intent intent5=new Intent(ShopActive.this,More.class);
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
	

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		for (int i = 0; i < BOYTYPE.length; i++) {
			if(i==arg2){
				Intent intent1=new Intent(ShopActive.this,ActivityShow.class);
				intent1.putExtra("index", Id[i]);
				startActivity(intent1);		
			}}
		
	}
	
	 public boolean onCreateOptionsMenu(Menu menu) {

	        menu.add(0, 1, 1, "刷新");	    
	        menu.add(0, 2, 2, "关于");
	        menu.add(0,3,3,"退出");
	        return super.onCreateOptionsMenu(menu);
	    } public boolean onOptionsItemSelected(MenuItem item) {     
	    	      if(item.getItemId() == 1){         
	    	    		Intent intent1=new Intent(ShopActive.this,Activitys.class);
						
						startActivity(intent1);	
           finish();
     }    
	    	      else if(item.getItemId() == 2){       
 		Intent intent=new Intent(ShopActive.this,ActivityShow.class);
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
