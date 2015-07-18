package cn.edu.bzu.seller;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.edu.bzu.bean.Business;
import cn.edu.bzu.bean.Stores;
import cn.edu.bzu.data.StoresData;
import cn.edu.bzu.main.R;

public class Up_seller extends Activity{
	
	EditText edittext1;
	EditText edittext2;
	EditText edittext3;
	EditText edittext4;
	EditText edittext5;
	EditText edittext6;
	EditText edittext7;
	EditText edittext8;
	
	String goodsName="";
	String type="";
	String model="";
	String brand="";
	String unit="";
	String material="";
	String color="";
	String imagename="";
	
	StoresData storedata=new StoresData();
	Stores stores=new Stores();
	Business business=new Business();
	
	public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
   super.onCreate(savedInstanceState);
    	setContentView(R.layout.up_shop);
    	
    	edittext1=(EditText)findViewById(R.id.EditText1);
    	edittext2=(EditText)findViewById(R.id.EditText2);
    	edittext3=(EditText)findViewById(R.id.EditText3);
    	edittext4=(EditText)findViewById(R.id.EditText4);
    	edittext5=(EditText)findViewById(R.id.EditText5);
    	edittext6=(EditText)findViewById(R.id.EditText6);
    	edittext7=(EditText)findViewById(R.id.EditText7);
    	edittext8=(EditText)findViewById(R.id.EditText8);
    	
    	Button Btn1 = (Button)findViewById(cn.edu.bzu.main.R.id.btn1);
   	 Btn1. setOnClickListener(listener); 	
    	
    	
}
	
private OnClickListener listener=new OnClickListener() {
    	
		@Override
		public void onClick(View v) {	
			goodsName=edittext1.getText().toString();
			type=edittext2.getText().toString();
			model=edittext3.getText().toString();
			brand=edittext4.getText().toString();
			unit=edittext5.getText().toString();
			material=edittext6.getText().toString();
			color=edittext7.getText().toString();
			imagename=edittext8.getText().toString();
			
			if(storedata.Shelves(Business.getBusinessAccount(), goodsName, type, model, brand, unit, material, color,imagename).equals("1")){
				dialog();
			}
			else{
				 Toast.makeText(Up_seller.this, "上架失败，请稍后再试", Toast.LENGTH_LONG).show();
			}
		}
   	
    };
    
    protected void dialog() {
  	  Builder builder = new Builder(Up_seller.this);
  	  builder.setMessage("上架已成功是否继续上架？");

  	  builder.setTitle("提示");

  	  builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

  	  
  	   public void onClick(DialogInterface dialog, int whichButton) {
  		Intent intent2=new Intent(Up_seller.this,Up_seller.class);
  		startActivity(intent2);	
  	   }
  	  });

  	  builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

  	   
  	   public void onClick(DialogInterface dialog, int whichButton) {
  		   finish();
  	   }
  	  });

  	  builder.create().show();
  	 }
}