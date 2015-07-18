package cn.edu.bzu.friends;

import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.data.FriendsData;
import cn.edu.bzu.main.StoresType;
import cn.edu.bzu.main.Index;
import cn.edu.bzu.main.More;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.Type;
import cn.edu.bzu.map.Maps;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddFriends extends Activity{
	/** Called when the activity is first created. */
	FriendsData friendsData=new FriendsData();
	EditText et;
    @Override
  public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
    	this.setContentView(R.layout.addfriend);
    	TextView textViewTitle = (TextView)findViewById(R.id.title);
   		textViewTitle.setText("��Ӻ���");
   		Button button=(Button)findViewById(R.id.button1);
   		button.setOnClickListener(listener);
   		et=(EditText)findViewById(R.id.editText1);
   		
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
      }
    private OnClickListener menu=new OnClickListener() {
@Override
    public void onClick(View v) {
	LinearLayout menu=(LinearLayout) v;
    switch (menu.getId()) {
    case R.id.menu1:
		Intent intent1=new Intent(AddFriends.this,Index.class);
		intent1.putExtra("tltle", "��ҳ");
		startActivity(intent1);				
		break;		
	case R.id.menu2:
		Intent intent2=new Intent(AddFriends.this,Type.class);
		intent2.putExtra("tltle", "����");
		startActivity(intent2);					
		break;
	case R.id.menu3:
		Intent intent3=new Intent(AddFriends.this,StoresType.class);
		intent3.putExtra("tltle", "�̼��б�");
		startActivity(intent3);					
		break;
	case R.id.menu4:
		Intent intent4=new Intent(AddFriends.this,Maps.class);
		intent4.putExtra("tltle", "������");
		startActivity(intent4);					
		break;
	case R.id.menu5:
		Intent intent5=new Intent(AddFriends.this,More.class);
		intent5.putExtra("tltle", "����");
		startActivity(intent5);					
		break;
   default:
	break;
}	}		
};
private OnClickListener listener=new OnClickListener(){
	@Override
	public void onClick(View v) {
		Boolean bl=friendsData.addFriend(Consumer.getClientAccount(), et.getText().toString());

		if(bl==true){
			 Toast.makeText(AddFriends.this, "��������ͳɹ�,�ȴ��Է�ȷ��!", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(AddFriends.this, "�Է��������ĺ����б���,���������!", Toast.LENGTH_LONG).show();
		}
		 
	}
};
}
