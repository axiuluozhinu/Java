package cn.edu.bzu.friends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.edu.bzu.activity.ActivityShow;
import cn.edu.bzu.bean.Active;
import cn.edu.bzu.data.FriendsData;
import cn.edu.bzu.main.Index;
import cn.edu.bzu.main.More;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.StoresType;
import cn.edu.bzu.main.SysApplication;
import cn.edu.bzu.main.Type;
import cn.edu.bzu.map.Maps;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class FriendsPart extends ListActivity implements OnItemClickListener{

	   /** Called when the activity is first created. */
	 ListView listViewAll;
	 int length=0;
	 String[] BOYTYPE = null;	
	 FriendsData friendsdata=new FriendsData();
	 int[] activeid=null;
	 String data=null;
	    @Override
	  public void onCreate(Bundle savedInstanceState) {
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);

	    	ListView listViewAll;
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.more);  
	    	
	    	SysApplication.getInstance().addActivity(this);
	    	
	    	
	    	Bundle extras = getIntent().getExtras();
	           if (extras != null) {
	                data = extras.getString("index");
	           }
	    	
	    	Active[] active=friendsdata.friendJoinActivity(data);
	    	length=active.length;
	      	 BOYTYPE=new String[length];
	      	activeid=new int[length];
	      	 for(int i=0;i<BOYTYPE.length;i++){
	       		
	   			BOYTYPE[i]=active[i].getTopic();
	   			activeid[i]=Integer.parseInt(active[i].getId());
	   		}
	    	
	    	listViewAll= (ListView)findViewById(android.R.id.list);    	
	    	setListAdapter(new SimpleAdapter(FriendsPart.this, getDate(), 
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
				Intent intent1=new Intent(FriendsPart.this,Index.class);
				intent1.putExtra("tltle", "首页");
				startActivity(intent1);				
				break;		
			case R.id.menu2:
				Intent intent2=new Intent(FriendsPart.this,Type.class);
				intent2.putExtra("tltle", "分类");
				startActivity(intent2);					
				break;
			case R.id.menu3:
				Intent intent3=new Intent(FriendsPart.this,StoresType.class);
				intent3.putExtra("tltle", "商家列表");
				startActivity(intent3);					
				break;
			case R.id.menu4:
				Intent intent4=new Intent(FriendsPart.this,Maps.class);
				intent4.putExtra("tltle", "百乐淘");
				startActivity(intent4);					
				break;
			case R.id.menu5:
				Intent intent5=new Intent(FriendsPart.this,More.class);
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
			for(int i=0;i<=BOYTYPE.length;i++){
				if(i==arg2){
					Intent intent5=new Intent(FriendsPart.this,ActivityShow.class);
					intent5.putExtra("index",activeid[i]);
					startActivity(intent5);

					
				}
				
			}
		}
		
}
