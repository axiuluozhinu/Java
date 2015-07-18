package cn.edu.bzu.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.edu.bzu.main.R;

public class Aaa extends Activity{  
	public void onCreate(Bundle savedInstanceState) {    	

super.onCreate(savedInstanceState);
this.setContentView(R.layout.index);
Intent intent1=new Intent( Aaa.this,Maps.class);
startActivity(intent1);
finish();
}
}