package cn.edu.bzu.activity;

import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.data.ConsumerData;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.SysApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ClientAmendActive extends Activity{
	String account=null;
	int id;
	String activeid=null;
	ConsumerData consumerdata=new ConsumerData();
	 
	 EditText edittext1;
	 EditText edittext2;
	 EditText edittext3;
	 EditText edittext4;
	 EditText edittext5;
	 	   
	 public void onCreate(Bundle savedInstanceState) {    	
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.amendactive);	    
	    SysApplication.getInstance().addActivity(this);
	    Bundle extras=getIntent().getExtras();
    	id=extras.getInt("index");
    	activeid=String.valueOf(id);
	    	    
	    TextView textViewTitle = (TextView)findViewById(R.id.title);
		textViewTitle.setText("发起活动");
		
		     edittext1 = (EditText)findViewById(R.id.text2);
			 edittext2 = (EditText)findViewById(R.id.text3);
			 edittext3 = (EditText)findViewById(R.id.text5);
			 edittext4 = (EditText)findViewById(R.id.text6);
			 edittext5 = (EditText)findViewById(R.id.text4);
						
		Button button1=(Button) findViewById(R.id.Btn1);
		   button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				String topic=edittext1.getText().toString();				
				String address=edittext2.getText().toString();			
				String starttime=edittext3.getText().toString();				
				String endtime=edittext4.getText().toString();			
				String content=edittext5.getText().toString();
												
				if(consumerdata.alterClientActyivity(Consumer.getClientAccount(), activeid, topic, address, starttime, endtime, content).equals("1")){
					Toast.makeText(ClientAmendActive.this, "修改成功", Toast.LENGTH_LONG).show();
					finish();
				}
				else{
					Toast.makeText(ClientAmendActive.this, "网络异常，请稍后再试", Toast.LENGTH_LONG).show();
					finish();
				}
			}
		});  
		
	 }
	 	 	 
	 public boolean onCreateOptionsMenu(Menu menu) {

	        menu.add(0, 1, 1, "刷新");	    
	        menu.add(0, 2, 2, "关于");
	        menu.add(0,3,3,"退出");
	        return super.onCreateOptionsMenu(menu);
	    } public boolean onOptionsItemSelected(MenuItem item) {     
	    	      if(item.getItemId() == 1){         
	    	    		Intent intent1=new Intent(ClientAmendActive.this,ClientAmendActive.class);
						
						startActivity(intent1);	
         finish();
   }    
	    	      else if(item.getItemId() == 2){       
		Intent intent=new Intent(ClientAmendActive.this,ActivityShow.class);
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
