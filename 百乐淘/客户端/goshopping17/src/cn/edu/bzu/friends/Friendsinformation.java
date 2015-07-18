package cn.edu.bzu.friends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.edu.bzu.bean.Friends;
import cn.edu.bzu.data.FriendsData;
import cn.edu.bzu.main.Index;
import cn.edu.bzu.main.More;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.StoresType;
import cn.edu.bzu.main.Type;
import cn.edu.bzu.map.Maps;
import cn.edu.bzu.personal.Write_leaveword;

public class Friendsinformation  extends  Activity {
	
	 FriendsData friendsdata=new FriendsData();
	 String clientAccount=null;
	 
	 public void onCreate(Bundle savedInstanceState) {
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);
	  
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.friendinformation);
	    	
	    	Bundle extras = getIntent().getExtras();
	        if (extras != null) {
	        	clientAccount = extras.getString("index");
	        }
	    	
	    	Friends friends=friendsdata.friendsinformation(clientAccount);
	    	
	    	TextView textview1=(TextView)findViewById(R.id.textView1);
	    	textview1.setText(clientAccount);
	    	TextView textview2=(TextView)findViewById(R.id.textView2);
	    	textview2.setText(friends.getRealName());
	    	TextView textview3=(TextView)findViewById(R.id.textView3);
	    	textview3.setText(friends.getSex());
	    	TextView textview4=(TextView)findViewById(R.id.textView4);
	    	textview4.setText(friends.getAge());
	    	TextView textview5=(TextView)findViewById(R.id.textView5);
	    	textview5.setText(friends.getClientPhonenumber());
	    	TextView textview6=(TextView)findViewById(R.id.textView6);
	    	textview6.setText(friends.getAddress());
	    	
	    	 Button button1=(Button) findViewById(R.id.btn1) ;
	    	 button1.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {																							
						Intent intent1=new Intent(Friendsinformation.this,Write_leaveword.class);	
						intent1.putExtra("clientAccount",clientAccount);
		            	startActivity(intent1);	            	            							 
					}
				});
	    	    	
	    	
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
				Intent intent1=new Intent(Friendsinformation.this,Index.class);
				intent1.putExtra("tltle", "首页");
				startActivity(intent1);				
				break;		
			case R.id.menu2:
				Intent intent2=new Intent(Friendsinformation.this,Type.class);
				intent2.putExtra("tltle", "分类");
				startActivity(intent2);					
				break;
			case R.id.menu3:
				Intent intent3=new Intent(Friendsinformation.this,StoresType.class);
				intent3.putExtra("tltle", "商家列表");
				startActivity(intent3);					
				break;
			case R.id.menu4:
				Intent intent4=new Intent(Friendsinformation.this,Maps.class);
				intent4.putExtra("tltle", "百乐淘");
				startActivity(intent4);					
				break;
			case R.id.menu5:
				Intent intent5=new Intent(Friendsinformation.this,More.class);
				intent5.putExtra("tltle", "更多");
				startActivity(intent5);					
				break;
			default:
				break;
			}	}		
		};
}
