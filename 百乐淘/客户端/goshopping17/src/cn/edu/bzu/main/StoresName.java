package cn.edu.bzu.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import cn.edu.bzu.bean.Stores;
import cn.edu.bzu.data.StoresData;
import cn.edu.bzu.map.Maps;
import cn.edu.bzu.shop.ShopInformation;

public class StoresName extends ListActivity implements OnItemClickListener {
    /** Called when the activity is first created. */
 ListView listViewAll;
 StoresData storesdata=new StoresData();
 int length=0;
 
 String data=null;
 String[] storesid=null;
 String[] BOYTYPE = null;
    @Override
  public void onCreate(Bundle savedInstanceState) {
    	
    	Bundle extras = getIntent().getExtras();
        if (extras != null) {
             data = extras.getString("index");
        }
    	
        Stores[] stores=storesdata.getStoresName(data);        
    	length=stores.length;
    	storesid=new String[length];
    	BOYTYPE=new String[length];
    	for(int i=0;i<storesid.length;i++){
    		storesid[i]=stores[i].getStoreId();
    	}
    	for(int i=0;i<BOYTYPE.length;i++){
    		
			BOYTYPE[i]=stores[i].getStoreName();
		}
    	
    	requestWindowFeature(Window.FEATURE_NO_TITLE);

    	ListView listViewAll;
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.more);
    	
    	
	        TextView textViewTitle = (TextView)findViewById(R.id.title);
			textViewTitle.setText(data); 	
    	
    	
    	listViewAll= (ListView)findViewById(android.R.id.list);	
    	setListAdapter(new SimpleAdapter(StoresName.this, getDate(), 
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
			Intent intent1=new Intent(StoresName.this,Index.class);
			intent1.putExtra("tltle", "首页");
			startActivity(intent1);				
			break;		
		case R.id.menu2:
			Intent intent2=new Intent(StoresName.this,Type.class);
			intent2.putExtra("tltle", "分类");
			startActivity(intent2);					
			break;
		case R.id.menu3:
			Intent intent3=new Intent(StoresName.this,StoresType.class);
			intent3.putExtra("tltle", "商家列表");
			startActivity(intent3);					
			break;
		case R.id.menu4:
			Intent intent4=new Intent(StoresName.this,Maps.class);
			intent4.putExtra("tltle", "百乐淘");
			startActivity(intent4);					
			break;
		case R.id.menu5:
			Intent intent5=new Intent(StoresName.this,More.class);
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
				Intent intent1=new Intent(StoresName.this,ShopInformation.class);
				intent1.putExtra("storesid", storesid[i]);
				startActivity(intent1);		
		}}

		
	}	
}