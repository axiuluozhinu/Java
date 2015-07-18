package cn.edu.bzu.main;

import cn.edu.bzu.activity.ActivityShow;
import cn.edu.bzu.bean.Business;
import cn.edu.bzu.data.BusinessData;
import cn.edu.bzu.data.RegisterData;
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
import android.widget.Toast;

public class BusinessRegister extends Activity {
	BusinessData businessdata=new BusinessData();
	String account=null;
    RegisterData registerdata=new RegisterData();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register); 
        
        SysApplication.getInstance().addActivity(this);
        
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                EditText mEditText = (EditText) findViewById(R.id.username);
	                    	String businessAccount=mEditText.getText().toString();
                EditText EditText1 = (EditText) findViewById(R.id.password);
		                    String businessPassword1=EditText1.getText().toString();
		       EditText EditText2 = (EditText) findViewById(R.id.password2);
		                    String businessPassword2=EditText2.getText().toString();
		        EditText EditText3= (EditText) findViewById(R.id.mobilenumber);
		                    String businessPhonenumber=EditText3.getText().toString();
		              
		                    if(businessAccount.equals("")||businessPassword1.equals("")||businessPassword2.equals("")||businessPhonenumber.equals("")){
		                    	Toast.makeText(BusinessRegister.this, "注册信息不能为空，请确认", Toast.LENGTH_LONG).show();
		                    }
		                    else if(!businessPassword1.equals(businessPassword2)){
		                    	Toast.makeText(BusinessRegister.this, "密码2次输入不相同，请确认", Toast.LENGTH_LONG).show();	
		                    	
		                    }
		                    else if(registerdata.register(businessAccount, businessPassword1, businessPhonenumber).equals("0")){
		                    	 Toast.makeText(BusinessRegister.this, "该账号已被注册，请更换新账号进行注册", Toast.LENGTH_LONG).show();	
		                    }				                    				                   
		                    
		                    else if(registerdata.register(businessAccount, businessPassword1, businessPhonenumber).equals("1")){
		                    	
			                    Toast.makeText(BusinessRegister.this, "恭喜您，注册成功", Toast.LENGTH_LONG).show();
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
    	    		Intent intent1=new Intent(BusinessRegister.this,ConsumerRegister.class);
					
					startActivity(intent1);	
          finish();
    }    
    	      else if(item.getItemId() == 2){       
		Intent intent=new Intent(BusinessRegister.this,ActivityShow.class);
		startActivity(intent);
    }     
    	      else if(item.getItemId() == 3){       
    	    	  account=Business.getBusinessAccount();
    	    	  if(account!=null){		    	    		  
    	    		  businessdata.businessExit(account);
    	    		  SysApplication.getInstance().exit();
    	    	  }
    	    	  else{
    	      SysApplication.getInstance().exit();
    	    	  }
    	    	         }    
    	     
    	      return true;    }

}
