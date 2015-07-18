package cn.edu.bzu.shop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.bzu.activity.ActivityShow;
import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.bean.Goods;
import cn.edu.bzu.data.BusinessData;
import cn.edu.bzu.data.ConsumerData;
import cn.edu.bzu.data.Goodsdata;
import cn.edu.bzu.main.StoresType;
import cn.edu.bzu.main.Index;
import cn.edu.bzu.main.ConsumerLogin;
import cn.edu.bzu.main.More;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.SysApplication;
import cn.edu.bzu.main.Type;
import cn.edu.bzu.map.Maps;

public class GoodsIntroduce extends Activity{
    /** Called when the activity is first created. */
	int client;
    Goodsdata goodsdata=new Goodsdata();
    Consumer consumer = new Consumer();
    ConsumerData consumerdata=new ConsumerData();
    BusinessData businessdata=new BusinessData();
    int goodsid;
    String goodsid2;
    String account=null;          
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
   super.onCreate(savedInstanceState);
   setContentView(R.layout.goods);
   SysApplication.getInstance().addActivity(this);
   
   SysApplication.getInstance().addActivity(this);
   
   Bundle extras = getIntent().getExtras();
  if (extras != null) {
	   goodsid = extras.getInt("index");
   }
  goodsid2=String.valueOf(goodsid);
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
	 LinearLayout LinearLayout2 = (LinearLayout)findViewById(R.id.lineatlayout2);          	
	 LinearLayout2. setOnClickListener(lineatlayout);   
	 LinearLayout LinearLayout3 = (LinearLayout)findViewById(R.id.lineatlayout3);          	
	 LinearLayout3. setOnClickListener(lineatlayout);     
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
   ImageView view=(ImageView)findViewById(R.id.view);
   int b=Integer.parseInt(businessdata.businessCredibility(goodsid2, account));
   //a是信誉度 
   if(b<=1000&&b>=0){
		Resources resource = getResources();
		// 1. 使用BitmapDrawable 解析数据流
		BitmapDrawable bitmapDrawable1 = (BitmapDrawable) resource.getDrawable(R.drawable.aa);
		Bitmap bitmap1 = bitmapDrawable1.getBitmap();
  view.setImageBitmap(bitmap1);
   }
   if(b<=2000&&b>1000){
		Resources resource = getResources();
		// 1. 使用BitmapDrawable 解析数据流
		BitmapDrawable bitmapDrawable1 = (BitmapDrawable) resource.getDrawable(R.drawable.bb);
		Bitmap bitmap2 = bitmapDrawable1.getBitmap();
 view.setImageBitmap(bitmap2);
  }
   if(b>2000){
		Resources resource = getResources();
		// 1. 使用BitmapDrawable 解析数据流
		BitmapDrawable bitmapDrawable1 = (BitmapDrawable) resource.getDrawable(R.drawable.cc);
		Bitmap bitmap3 = bitmapDrawable1.getBitmap();
view.setImageBitmap(bitmap3);
 }
   }    
private OnClickListener menu=new OnClickListener() {
		@Override
		public void onClick(View v) {
			LinearLayout menu=(LinearLayout) v;
		switch (menu.getId()) {
		case R.id.menu1:
			Intent intent1=new Intent(GoodsIntroduce.this,Index.class);
			intent1.putExtra("tltle", "首页");
			startActivity(intent1);				
			break;		
		case R.id.menu2:
			Intent intent2=new Intent(GoodsIntroduce.this,Type.class);
			intent2.putExtra("tltle", "分类");
			startActivity(intent2);					
			break;
		case R.id.menu3:
			Intent intent3=new Intent(GoodsIntroduce.this,StoresType.class);
			intent3.putExtra("tltle", "商家列表");
			startActivity(intent3);					
			break;
		case R.id.menu4:
			Intent intent4=new Intent(GoodsIntroduce.this,Maps.class);
			intent4.putExtra("tltle", "百乐淘");
			startActivity(intent4);					
			break;
		case R.id.menu5:
			Intent intent5=new Intent(GoodsIntroduce.this,More.class);
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
					//分享
					account=Consumer.getClientAccount();
					 if(account==null)
					 {  
						 Intent intent1=new Intent(GoodsIntroduce.this,ConsumerLogin.class);
						 startActivity(intent1);
						 break;
					 }
					 else if( consumerdata.shareGoods(account, goodsid).equals("1")){
						 
						 Toast.makeText(GoodsIntroduce.this, "分享成功", Toast.LENGTH_LONG).show();
					
						 break;	
					 }					 											 				
				case R.id.lineatlayout2:
				//取消订购
					account=Consumer.getClientAccount();
					if(account==null)
					 {
						 Intent intent2=new Intent(GoodsIntroduce.this,ConsumerLogin.class);
						 startActivity(intent2);
						
					 }
					else if(account!=null)
					{
						client=Integer.parseInt(consumerdata.clientIntegral(Consumer.getClientAccount()));
						if(client<=10&&client>0){
							 Toast.makeText(GoodsIntroduce.this, "对不起，你的等级不够，一星以上用户才可取消预定。", Toast.LENGTH_LONG).show();
						}
						else{
							call_offReserve();		
						}						
					}
					break;	
				case R.id.lineatlayout3:
					//订购
					account=Consumer.getClientAccount();
					if(account==null)
					 {
						 Intent intent3=new Intent(GoodsIntroduce.this,ConsumerLogin.class);
						 startActivity(intent3);
					 }
					else if(account!=null)
					{    
						client=Integer.parseInt(consumerdata.clientIntegral(Consumer.getClientAccount()));
						if(client<=10&&client>0){
							 Toast.makeText(GoodsIntroduce.this, "对不起，你的等级不够，一星以上用户才可预定。", Toast.LENGTH_LONG).show();
						}
						else{
							 reserve();	
						}						
					}								
					break;		
				default:
					break;
			}}	};
			
		protected void reserve() {
		new AlertDialog.Builder(this).setTitle("提示").setMessage("预定成功").setIcon(
			     R.drawable.biao).setNegativeButton("确定",
			     new DialogInterface.OnClickListener() {
			      public void onClick(DialogInterface dialog, int whichButton) {   }  }).show();
		goodsdata.getScheduled(goodsid, account);

		}
		protected void call_offReserve() {
			new AlertDialog.Builder(this).setTitle("提示").setMessage("已取消预定").setIcon(
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
		    	    		Intent intent1=new Intent(GoodsIntroduce.this,GoodsIntroduce.class);
							
							startActivity(intent1);	
	              finish();
	        }    
		    	      else if(item.getItemId() == 2){       
	    		Intent intent=new Intent(GoodsIntroduce.this,ActivityShow.class);
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