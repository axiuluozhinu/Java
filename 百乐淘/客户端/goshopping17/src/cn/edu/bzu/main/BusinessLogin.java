package cn.edu.bzu.main;

import cn.edu.bzu.activity.ActivityShow;
import cn.edu.bzu.bean.Business;
import cn.edu.bzu.data.BusinessData;
import cn.edu.bzu.data.LoginData;
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

public class BusinessLogin extends Activity {
	  LoginData logindata=new LoginData();
	  Business budiness=new Business();
	  BusinessData businessdata=new BusinessData();
	  String account=null;
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.login);
	        SysApplication.getInstance().addActivity(this);

	       Button button1=(Button)findViewById(R.id.signin_button);
	        
	        button1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					EditText mEditText = (EditText) findViewById(R.id.username_edit);					
					String businessaccount=mEditText.getText().toString();
	                EditText EditText = (EditText) findViewById(R.id.password_edit);					
					String businesspassword=EditText.getText().toString();	 
			if(logindata.businessLoad(businessaccount, businesspassword).equals("1")){
				 Toast.makeText(BusinessLogin.this, "µÇÂ¼³É¹¦", Toast.LENGTH_LONG).show();
				 Business.setBusinessAccount(businessaccount);
				 finish();
			}
					
					
			else {
				Toast.makeText(BusinessLogin.this, "µÇÂ¼Ê§°Ü£¬ÕËºÅÃÜÂë´íÎó£¡", Toast.LENGTH_LONG).show();
			}
					 
				
				}

			});
	        TextView textviev=(TextView) findViewById(R.id.register_link);
	        textviev.setOnClickListener(new OnClickListener() {			
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent4=new Intent(BusinessLogin.this,BusinessRegister.class);
					startActivity(intent4);
				}
			});
	         }

		protected static void putExtra(String string, String name) {
			// TODO Auto-generated method stub
			
		}
		
		 public boolean onCreateOptionsMenu(Menu menu) {

		        menu.add(0, 1, 1, "Ë¢ÐÂ");	    
		        menu.add(0, 2, 2, "¹ØÓÚ");
		        menu.add(0,3,3,"ÍË³ö");
		        return super.onCreateOptionsMenu(menu);
		    } public boolean onOptionsItemSelected(MenuItem item) {     
		    	      if(item.getItemId() == 1){         
		    	    		Intent intent1=new Intent(BusinessLogin.this,ConsumerLogin.class);
							
							startActivity(intent1);	
	           finish();
	     }    
		    	      else if(item.getItemId() == 2){       
	 		Intent intent=new Intent(BusinessLogin.this,ActivityShow.class);
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
		    	     
		    	      return true;    
		    	      }

}
