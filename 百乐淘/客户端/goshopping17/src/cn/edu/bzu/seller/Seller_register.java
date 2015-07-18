package cn.edu.bzu.seller;

import cn.edu.bzu.bean.Storesregister;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.SysApplication;
import cn.edu.bzu.map.Map_register;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Seller_register extends Activity{
	
	 EditText edittext1;
  	 EditText edittext2;
  	 EditText edittext3;
  	 EditText edittext4;
  	 EditText edittext5;
  	 EditText edittext6;
 	 EditText edittext7;
 	 
 	 Storesregister storesregister=new Storesregister();
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.seller_register);
        SysApplication.getInstance().addActivity(this);
        
         edittext1 = (EditText)findViewById(R.id.EditText1);
		 edittext2 = (EditText)findViewById(R.id.EditText2);
		 edittext3 = (EditText)findViewById(R.id.EditText3);
		 edittext4 = (EditText)findViewById(R.id.EditText4);
		 edittext5 = (EditText)findViewById(R.id.EditText5);
		 edittext6 = (EditText)findViewById(R.id.EditText6);
		 edittext7 = (EditText)findViewById(R.id.EditText7);
    	
        Button btn=(Button)findViewById(R.id.btn1);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Storesregister.setStoreName(edittext1.getText().toString());				
				Storesregister.setType(edittext2.getText().toString());			
				Storesregister.setStoreApproval(edittext3.getText().toString());				
				Storesregister.setProvince(edittext4.getText().toString());			
				Storesregister.setCity(edittext5.getText().toString());
				Storesregister.setTown(edittext6.getText().toString());
				Storesregister.setAddress(edittext7.getText().toString());
				
				Intent intent1=new Intent(Seller_register.this,Map_register.class);		
				startActivity(intent1);	
			}
		});
        }
	
}
