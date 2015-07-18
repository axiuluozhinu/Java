package cn.edu.bzu.personal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import cn.edu.bzu.main.StoresType;
import cn.edu.bzu.main.Index;
import cn.edu.bzu.main.More;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.SysApplication;
import cn.edu.bzu.main.Type;
import cn.edu.bzu.map.Maps;

public class Personal extends ListActivity implements OnItemClickListener {
    /** Called when the activity is first created. */
 ListView listViewAll;
    @Override
  public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);

    	ListView listViewAll;
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.more);
    	
    	SysApplication.getInstance().addActivity(this);
    	
    	listViewAll= (ListView)findViewById(android.R.id.list); 	
    	setListAdapter(new SimpleAdapter(Personal.this, getDate(), 
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
			Intent intent1=new Intent(Personal.this,Index.class);
			intent1.putExtra("tltle", "首页");
			startActivity(intent1);				
			break;		
		case R.id.menu2:
			Intent intent2=new Intent(Personal.this,Type.class);
			intent2.putExtra("tltle", "分类");
			startActivity(intent2);					
			break;
		case R.id.menu3:
			Intent intent3=new Intent(Personal.this,StoresType.class);
			intent3.putExtra("tltle", "商家列表");
			startActivity(intent3);					
			break;
		case R.id.menu4:
			Intent intent4=new Intent(Personal.this,Maps.class);
			intent4.putExtra("tltle", "百乐淘");
			startActivity(intent4);					
			break;
		case R.id.menu5:
			Intent intent5=new Intent(Personal.this,More.class);
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
	static final String[] BOYTYPE = {"我的信息","我的预定","我的参与","我的消息","发起活动","我的活动"};
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
			if(arg2==0){
				Intent intent1=new Intent(Personal.this,About_me.class);
				startActivity(intent1);		
		}
	         if(arg2==1){
				Intent intent1=new Intent(Personal.this,Reserve.class);
				startActivity(intent1);		
		}
			if(arg2==2){
				Intent intent1=new Intent(Personal.this,Part.class);
				startActivity(intent1);		
		}
	        if(arg2==3){
		Intent intent5=new Intent(Personal.this,News.class);
		startActivity(intent5);
	}
		
	        if(arg2==4){
	    		Intent intent6=new Intent(Personal.this,PublishActive.class);
	    		startActivity(intent6);
	    	}

	        if(arg2==5){
	    		Intent intent7=new Intent(Personal.this,MyPublishActive.class);
	    		startActivity(intent7);
	    	}
	       
	}
	
}