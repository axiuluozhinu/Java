package cn.edu.bzu.friends;

import cn.edu.bzu.main.StoresType;
import cn.edu.bzu.main.Index;
import cn.edu.bzu.main.More;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.Type;
import cn.edu.bzu.map.Maps;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;

public class ChatRoom extends Activity{
	/** Called when the activity is first 

created. */
    @Override
  public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);

    	super.onCreate(savedInstanceState);
    	this.setContentView(R.layout.inform);
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
		Intent intent1=new Intent(ChatRoom.this,Index.class);
		intent1.putExtra("tltle", "首页");
		startActivity(intent1);				
		break;		
	case R.id.menu2:
		Intent intent2=new Intent(ChatRoom.this,Type.class);
		intent2.putExtra("tltle", "分类");
		startActivity(intent2);					
		break;
	case R.id.menu3:
		Intent intent3=new Intent(ChatRoom.this,StoresType.class);
		intent3.putExtra("tltle", "商家列表");
		startActivity(intent3);					
		break;
	case R.id.menu4:
		Intent intent4=new Intent(ChatRoom.this,Maps.class);
		intent4.putExtra("tltle", "百乐淘");
		startActivity(intent4);					
		break;
	case R.id.menu5:
		Intent intent5=new Intent(ChatRoom.this,More.class);
		intent5.putExtra("tltle", "更多");
		startActivity(intent5);					
		break;
   default:
	break;
}	}		
};
}
