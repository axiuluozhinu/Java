package cn.edu.bzu.seller;

import cn.edu.bzu.activity.ActivityShow;
import cn.edu.bzu.bean.Business;
import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.bean.Goods;
import cn.edu.bzu.data.ConsumerData;
import cn.edu.bzu.data.Goodsdata;
import cn.edu.bzu.data.StoresData;
import cn.edu.bzu.main.Index;
import cn.edu.bzu.main.More;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.StoresType;
import cn.edu.bzu.main.SysApplication;
import cn.edu.bzu.main.Type;
import cn.edu.bzu.map.Maps;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SellGoodsIntroduce extends Activity {

    Goodsdata goodsdata=new Goodsdata();
    StoresData storesData=new StoresData();
    Business business = new Business();
    ConsumerData consumerdata=new ConsumerData();
    int goodsid;
    String account=null;   
       
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
   super.onCreate(savedInstanceState);
   setContentView(R.layout.sellgoodsintroduce);
   SysApplication.getInstance().addActivity(this);
   
   SysApplication.getInstance().addActivity(this);
   
   Bundle extras = getIntent().getExtras();
  if (extras != null) {
	   goodsid = extras.getInt("index");
  }
  else{
	  System.out.println("extras=null");
  }
   Goods goods=goodsdata.goodsIntroduce(goodsid);
   ImageView imageView1=(ImageView) findViewById(R.id.imageView1);
   imageView1.setImageBitmap(goodsdata.bit(goods.getCode()));
   
   TextView textView1=(TextView)findViewById(R.id.value_goodsname);
   TextView textView2=(TextView)findViewById(R.id.value_goodsbrand);
   TextView textView3=(TextView)findViewById(R.id.value_goodsmodel);
   TextView textView4=(TextView)findViewById(R.id.value_goodshot);
   TextView textView6=(TextView)findViewById(R.id.value_goodsunit);
   TextView textView7=(TextView)findViewById(R.id.value_goodsscheduled);
   TextView textView8=(TextView)findViewById(R.id.value_goodsmaterial);
   TextView textView9=(TextView)findViewById(R.id.value_goodscolor);
   TextView textView10=(TextView)findViewById(R.id.title);
    
   textView1.setText(goods.getGoodsName());
   textView2.setText(goods.getBrand());
   textView3.setText(goods.getModel());
   textView4.setText(goods.getHot());
   textView6.setText(goods.getUnit());
   textView7.setText(goods.getScheduled());
   textView8.setText(goods.getMaterial());
   textView9.setText(goods.getColor());
   textView10.setText(goods.getGoodsName());
   
     LinearLayout LinearLayout1 = (LinearLayout)findViewById(R.id.lineatlayout1);          	
     LinearLayout1. setOnClickListener(lineatlayout);
    
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
			Intent intent1=new Intent(SellGoodsIntroduce.this,Index.class);
			intent1.putExtra("tltle", "首页");
			startActivity(intent1);				
			break;		
		case R.id.menu2:
			Intent intent2=new Intent(SellGoodsIntroduce.this,Type.class);
			intent2.putExtra("tltle", "分类");
			startActivity(intent2);					
			break;
		case R.id.menu3:
			Intent intent3=new Intent(SellGoodsIntroduce.this,StoresType.class);
			intent3.putExtra("tltle", "商家列表");
			startActivity(intent3);					
			break;
		case R.id.menu4:
			Intent intent4=new Intent(SellGoodsIntroduce.this,Maps.class);
			intent4.putExtra("tltle", "百乐淘");
			startActivity(intent4);					
			break;
		case R.id.menu5:
			Intent intent5=new Intent(SellGoodsIntroduce.this,More.class);
			intent5.putExtra("tltle", "更多");
			startActivity(intent5);					
			break;
		default:
			break;
		}	}		
	};
	 private OnClickListener lineatlayout=new OnClickListener() {

			@Override
				public void onClick(View v) {
				LinearLayout Lin=(LinearLayout) v;
				switch (Lin.getId()) {
				
				case R.id.lineatlayout1:
				
					if(storesData.CancelShelves(goodsid,Business.getBusinessAccount()).equals("1")){
						Toast.makeText(SellGoodsIntroduce.this, "下架成功", Toast.LENGTH_LONG).show();
					}
					else{
						Toast.makeText(SellGoodsIntroduce.this, "网络异常，请稍后再试", Toast.LENGTH_LONG).show();
					}
					break;						
				case R.id.lineatlayout2:
																							
					call_offReserve();					
					break;					
				default:
					break;
			}}	};
			
		protected void reserve() {
		new AlertDialog.Builder(this).setTitle("提示").setMessage("下架成功").setIcon(
			     R.drawable.biao).setNegativeButton("确定",
			     new DialogInterface.OnClickListener() {
			      public void onClick(DialogInterface dialog, int whichButton) {   }  }).show();
		goodsdata.getScheduled(goodsid, account);

		}
		protected void call_offReserve() {
			new AlertDialog.Builder(this).setTitle("提示").setMessage("网络问题").setIcon(
				     R.drawable.biao).setNegativeButton("确定",
				     new DialogInterface.OnClickListener() {
				      public void onClick(DialogInterface dialog, int whichButton) {   }  }).show();
			goodsdata.getCancellation(goodsid, account);

			}
		
		  public boolean onCreateOptionsMenu(Menu menu) {

		        menu.add(0, 1, 1, "刷新");	    
		        menu.add(0, 2, 2, "关于");
		        menu.add(0,3,3,"退出");
		        return super.onCreateOptionsMenu(menu);
		    } public boolean onOptionsItemSelected(MenuItem item) {     
		    	      if(item.getItemId() == 1){         
		    	    		Intent intent1=new Intent(SellGoodsIntroduce.this,SellGoodsIntroduce.class);
							
							startActivity(intent1);	
	              finish();
	        }    
		    	      else if(item.getItemId() == 2){       
	    		Intent intent=new Intent(SellGoodsIntroduce.this,ActivityShow.class);
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
