package cn.edu.bzu.personal;

import cn.edu.bzu.activity.ActivityShow;
import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.data.AppraisalData;
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

public class Write_leaveword extends Activity{

AppraisalData appraisldata=new AppraisalData();
	
	String friendid=null;
	  String account=null;
	Consumer consumer=new Consumer();
	 ConsumerData consumerdata=new ConsumerData();
	  public void onCreate(Bundle savedInstanceState) {    	
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.leave_word);
	    
	    SysApplication.getInstance().addActivity(this);
	    
	    Bundle extras = getIntent().getExtras();
	       if (extras != null) {
	    	   friendid =extras.getString("clientAccount");
	       }
	    
	    
	    TextView textViewTitle = (TextView)findViewById(R.id.title);
		textViewTitle.setText("留言内容");
	  
	   Button button1=(Button) findViewById(R.id.button1);
	   button1.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
			EditText edittext = (EditText)findViewById(R.id.Write_leaveword);
			String word=edittext.getText().toString();
			if(consumerdata.leaveMessage( Consumer.getClientAccount(),friendid, word).equals("1")){
				Toast.makeText(Write_leaveword.this, "留言成功", Toast.LENGTH_LONG).show();
				finish();
			}
			else{
				Toast.makeText(Write_leaveword.this, "网络异常，请稍后再试", Toast.LENGTH_LONG).show();
				finish();
			}
		}
	});
	   
	   Button button2=(Button) findViewById(R.id.button2);
	   button2.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {				
				finish();		
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
	    	    		Intent intent1=new Intent(Write_leaveword.this,Discuss.class);
						
						startActivity(intent1);	
            finish();
      }    
	    	      else if(item.getItemId() == 2){       
  		Intent intent=new Intent(Write_leaveword.this,ActivityShow.class);
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
