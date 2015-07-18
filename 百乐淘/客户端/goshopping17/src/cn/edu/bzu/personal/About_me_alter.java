package cn.edu.bzu.personal;
//修改个人信息
import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.data.ConsumerData;
import cn.edu.bzu.main.ConsumerLogin;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.SysApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class About_me_alter extends Activity {
    /** Called when the activity is first created. */
	private Button Btn1=null;
	ConsumerData consumerData=new ConsumerData();
	EditText edittext1;
	EditText edittext2;
	EditText edittext3;
	EditText edittext4;
	EditText edittext5;
	String account="";
	String name="";
	String sex="";
	String age="";
	String phonenumber="";
	String address="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);

    	super.onCreate(savedInstanceState);
    	setContentView(cn.edu.bzu.main.R.layout.about_me_alter); 
    	
    	SysApplication.getInstance().addActivity(this);
    	
    	edittext1=(EditText)findViewById(R.id.text2);
    	edittext2=(EditText)findViewById(R.id.text3);
    	edittext3=(EditText)findViewById(R.id.text4);
    	edittext4=(EditText)findViewById(R.id.text6);
    	edittext5=(EditText)findViewById(R.id.text8);
    	account=Consumer.getClientAccount();
    	
    	
    	 Btn1 = (Button)findViewById(cn.edu.bzu.main.R.id.Btn1);
    	 Btn1. setOnClickListener(listener); 	
    	
    }
    private OnClickListener listener=new OnClickListener() {
    	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			name=edittext1.getText().toString();
	    	sex=edittext2.getText().toString();
	    	age=edittext3.getText().toString();
	    	phonenumber=edittext4.getText().toString();
	    	address=edittext5.getText().toString();
	    	if(name.equals("")||sex.equals("")||age.equals("")||phonenumber.equals("")||address.equals("")){
	    		Toast.makeText(About_me_alter.this, "您有未填写的信息，修改失败！", Toast.LENGTH_LONG).show();
	    		//finish();
	    	}else{
	    		consumerData.modification(account, phonenumber, sex, name, age, address);
				Intent intent=new Intent(About_me_alter.this,About_me.class);
				startActivity(intent);	
				finish();
	    	}
		}
    };
}

