package cn.edu.bzu.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.edu.bzu.main.StoresType;
import cn.edu.bzu.main.Index;
import cn.edu.bzu.main.ConsumerLogin;
import cn.edu.bzu.main.More;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.SysApplication;
import cn.edu.bzu.main.Type;
import cn.edu.bzu.map.Maps;
import cn.edu.bzu.bean.Active;
import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.data.ActiveData;
import cn.edu.bzu.data.ConsumerData;

public class ActivityShow extends Activity{
    /** Called when the activity is first created. */   
    Active active=new Active();
    ActiveData activedata=new ActiveData();
    Consumer consumer = new Consumer();
    ConsumerData consumerdata=new ConsumerData();
    int id;
    String account=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {   	 
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.show_activity);
    	SysApplication.getInstance().addActivity(this);
    	
    	Button button1 = (Button)findViewById(R.id.btn1);          	
   	 button1. setOnClickListener(button);
   	 Button button2 = (Button)findViewById(R.id.btn2);          	
   	 button2. setOnClickListener(button);  
    	
    	Bundle extras=getIntent().getExtras();
    	id=extras.getInt("index");
    	 active=activedata.getActivityIntroduction(id);
    	 
    	TextView textViewTitle = (TextView)findViewById(R.id.title);
   		textViewTitle.setText("活动详情");

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
   	   
   	    textview1.setText(active.getTopic());
   	    textview2.setText(active.getStartTime()+"至"+active.getEndTime());
   	    textview3.setText(active.getStoreName());
   	    textview4.setText(active.getAddress());
   	    textview5.setText(active.getContent());
   }
    
       private OnClickListener menu=new OnClickListener() {
		@Override
	     	public void onClick(View v) {
			    LinearLayout menu=(LinearLayout) v;
		   switch (menu.getId()) {
		   case R.id.menu1:
				Intent intent1=new Intent(ActivityShow.this,Index.class);
				intent1.putExtra("tltle", "首页");
				startActivity(intent1);				
				break;		
			case R.id.menu2:
				Intent intent2=new Intent(ActivityShow.this,Type.class);
				intent2.putExtra("tltle", "分类");
				startActivity(intent2);					
				break;
			case R.id.menu3:
				Intent intent3=new Intent(ActivityShow.this,StoresType.class);
				intent3.putExtra("tltle", "商家列表");
				startActivity(intent3);					
				break;
			case R.id.menu4:
				Intent intent4=new Intent(ActivityShow.this,Maps.class);
				intent4.putExtra("tltle", "百乐淘");
				startActivity(intent4);					
				break;
			case R.id.menu5:
				Intent intent5=new Intent(ActivityShow.this,More.class);
				intent5.putExtra("tltle", "更多");
				startActivity(intent5);					
				break;
		default:
			break;
		}	}		
	};
	
	private OnClickListener button=new OnClickListener() {

		@Override
			public void onClick(View v) {
				Button btn=(Button) v;
			switch (btn.getId()) {
			case R.id.btn1:
				account=Consumer.getClientAccount();
				
				 if(account==null)
				 {  
					 Intent intent1=new Intent(ActivityShow.this,ConsumerLogin.class);		
					 startActivity(intent1);
				 }
				 else 
				 {
			     reserve();	
			     btn.setEnabled(false);
				 }
			break;	
			case R.id.btn2:
			
				account=Consumer.getClientAccount();
				if(account==null)
				 {
					 Intent intent2=new Intent(ActivityShow.this,ConsumerLogin.class);
					 startActivity(intent2);
					
				 }
				else
				{
				call_offReserve();
				btn.setEnabled(false);
				}
				break;	
			
			default:
				break;
		}
			}
		};
		
		protected void reserve() {
			new AlertDialog.Builder(this).setTitle("提示").setMessage("参与成功").setIcon(
				     R.drawable.biao).setNegativeButton("确定",
				     new DialogInterface.OnClickListener() {
				      public void onClick(DialogInterface dialog, int whichButton) {   }  }).show();
			activedata.getJoinActivity(id, account);

			}
			protected void call_offReserve() {
				new AlertDialog.Builder(this).setTitle("提示").setMessage("已取消参与").setIcon(
					     R.drawable.biao).setNegativeButton("确定",
					     new DialogInterface.OnClickListener() {
					      public void onClick(DialogInterface dialog, int whichButton) {   }  }).show();
				activedata.getCancelJoin(id, account);

				}
			
			
			  public boolean onCreateOptionsMenu(Menu menu) {

			        menu.add(0, 1, 1, "刷新");	    
			        menu.add(0, 2, 2, "关于");
			        menu.add(0,3,3,"退出");
			        return super.onCreateOptionsMenu(menu);
			    } public boolean onOptionsItemSelected(MenuItem item) {     
			    	      if(item.getItemId() == 1){         
			    	    		Intent intent1=new Intent(ActivityShow.this,ActivityShow.class);
								
								startActivity(intent1);	
		              finish();
		        }    
			    	      else if(item.getItemId() == 2){       
		    		Intent intent=new Intent(ActivityShow.this,ActivityShow.class);
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
