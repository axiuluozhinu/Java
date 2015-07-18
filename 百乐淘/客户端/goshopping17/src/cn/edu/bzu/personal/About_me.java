package cn.edu.bzu.personal;
//用户信息
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.edu.bzu.activity.ActivityShow;
import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.data.ConsumerData;
import cn.edu.bzu.main.More;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.Index;
import cn.edu.bzu.main.StoresType;
import cn.edu.bzu.main.SysApplication;
import cn.edu.bzu.main.Type;
import cn.edu.bzu.map.Maps;

public class About_me extends Activity{
    /** Called when the activity is first created. */
	 ConsumerData consumerdata=new ConsumerData();
	Consumer consumer=consumerdata.clientIntroduction(Consumer.getClientAccount());
	  String account=Consumer.getClientAccount();
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);

    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.about_me);
    	
    	SysApplication.getInstance().addActivity(this);
    	
    	Button a1 = (Button)findViewById(R.id.btn1);          	
    	 a1. setOnClickListener(listener); 
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
    	 
    	 TextView textview1=(TextView)findViewById(R.id.textView1);
    	 TextView textview2=(TextView)findViewById(R.id.textView2);
    	 TextView textview3=(TextView)findViewById(R.id.textView3);
    	 TextView textview4=(TextView)findViewById(R.id.textView4);
    	 TextView textview5=(TextView)findViewById(R.id.textView5);
    	 TextView textview6=(TextView)findViewById(R.id.textView6);
    	 TextView textview7=(TextView)findViewById(R.id.textView7);
    	 TextView textview8=(TextView)findViewById(R.id.textView8);
    	 textview1.setText(Consumer.getClientAccount());
    	 textview2.setText(consumer.getRealName());
    	 textview3.setText(consumer.getSex());
    	 textview4.setText(consumer.getAge());
    	 textview5.setText(consumer.getClientPassword());
    	 textview6.setText(consumer.getIntegral());
    	 textview7.setText(consumer.getClientPhonenumber());
    	 textview8.setText(consumer.getAddress());
    	 
    	 ImageView view=(ImageView)findViewById(R.id.view);
    	   int a=Integer.parseInt(consumerdata.clientIntegral(account));
    	
    	   if(a<=10&&a>0){
    			Resources resource = getResources();
    		
    			BitmapDrawable bitmapDrawable1 = (BitmapDrawable) resource.getDrawable(R.drawable.aa);
    			Bitmap bitmap1 = bitmapDrawable1.getBitmap();
    	  view.setImageBitmap(bitmap1);
    	   }
    	   if(a<=100&&a>10){
    			Resources resource = getResources();
    		
    			BitmapDrawable bitmapDrawable1 = (BitmapDrawable) resource.getDrawable(R.drawable.bb);
    			Bitmap bitmap2 = bitmapDrawable1.getBitmap();
    	 view.setImageBitmap(bitmap2);
    	  }
    	   if(a>100){
    			Resources resource = getResources();
    
    			BitmapDrawable bitmapDrawable1 = (BitmapDrawable) resource.getDrawable(R.drawable.cc);
    			Bitmap bitmap3 = bitmapDrawable1.getBitmap();
    	view.setImageBitmap(bitmap3);
    	 }
    }
    private OnClickListener listener=new OnClickListener() {
		@Override
		public void onClick(View v) {
			Button a=(Button) v;
		switch (a.getId()) {
		case R.id.btn1:
			Intent intent1=new Intent(About_me.this,About_me_alter.class);
			startActivity(intent1);	
			finish();
			break;
		default:
			break;
		}	}		
	};
	private OnClickListener menu=new OnClickListener() {
		@Override
		public void onClick(View v) {
			LinearLayout menu=(LinearLayout) v;
		switch (menu.getId()) {
		case R.id.menu1:
			Intent intent1=new Intent( About_me.this,Index.class);
			intent1.putExtra("tltle", "首页");
			startActivity(intent1);				
			break;		
		case R.id.menu2:
			Intent intent2=new Intent( About_me.this,Type.class);
			intent2.putExtra("tltle", "分类");
			startActivity(intent2);					
			break;
		case R.id.menu3:
			Intent intent3=new Intent( About_me.this,StoresType.class);
			intent3.putExtra("tltle", "商家列表");
			startActivity(intent3);					
			break;
		case R.id.menu4:
			Intent intent4=new Intent( About_me.this,Maps.class);
			intent4.putExtra("tltle", "百乐淘");
			startActivity(intent4);					
			break;
		case R.id.menu5:
			Intent intent5=new Intent( About_me.this,More.class);
			intent5.putExtra("tltle", "更多");
			startActivity(intent5);					
			break;
		default:
			break;
		}	}		
	};
	public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 1, 1, "刷新");	    
        menu.add(0, 2, 2, "关于");
        menu.add(0,3,3,"退出");
        return super.onCreateOptionsMenu(menu);
    }
 public boolean onOptionsItemSelected(MenuItem item) {     
    	      if(item.getItemId() == 1){         
    	    		Intent intent1=new Intent(About_me.this,About_me.class);
					
					startActivity(intent1);	
       finish();
 }    
    	      else if(item.getItemId() == 2){       
		Intent intent=new Intent(About_me.this,ActivityShow.class);
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
    	     
    	      return true;   
 }
}

