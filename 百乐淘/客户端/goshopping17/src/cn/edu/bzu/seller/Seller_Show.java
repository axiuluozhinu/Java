package cn.edu.bzu.seller;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.edu.bzu.activity.ActivityShow;
import cn.edu.bzu.activity.CancelBusinessActivity;
import cn.edu.bzu.bean.Business;
import cn.edu.bzu.bean.Goods;
import cn.edu.bzu.data.BusinessData;
import cn.edu.bzu.data.Goodsdata;
import cn.edu.bzu.data.StoresData;
import cn.edu.bzu.main.Index;
import cn.edu.bzu.main.More;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.StoresType;
import cn.edu.bzu.main.SysApplication;
import cn.edu.bzu.main.Type;
import cn.edu.bzu.map.Maps;

public class Seller_Show extends Activity{
	StoresData storesdata=new StoresData();
	Goodsdata goodsdata=new Goodsdata();
    BusinessData businessdata=new BusinessData();
    String[] image=new String[50];
    int[] goodsid=null;
    String data=null;
    String account=null;
    int number;
    int t=0;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    
	public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
   super.onCreate(savedInstanceState);
    	setContentView(R.layout.seller_show);
    	
    	 Bundle extras = getIntent().getExtras();
         if (extras != null) {
              data = extras.getString("index");
         }
         Goods[] goods=storesdata.storeGoodsImage(data);
         number=goods.length;
         goodsid=new int[number];         
        for(int i=0;i<number;i++){
	    	image[i]=new String();
 	    	goodsid[i]=Integer.parseInt(goods[i].getGoodsId());
 	    	image[i]= goods[i].getCode();
	    }
        
        
        this.goodsshow();
    	
    	Button btn1=(Button) findViewById(R.id.button_1);
    	btn1.setOnClickListener(button);
    	Button btn2=(Button) findViewById(R.id.button_2);
    	btn2.setOnClickListener(button);
    	Button btn3=(Button) findViewById(R.id.button_3);
    	btn3.setOnClickListener(button);
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
	public void goodsshow(){
 		
 		
	       imageView1=(ImageView) findViewById(R.id.imageview1);
	       imageView2=(ImageView) findViewById(R.id.imageview2);
	       imageView3=(ImageView) findViewById(R.id.imageview3);
	       imageView4=(ImageView) findViewById(R.id.imageview4);
	       imageView5=(ImageView) findViewById(R.id.imageview5);
	       imageView6=(ImageView) findViewById(R.id.imageview6);
	       imageView7=(ImageView) findViewById(R.id.imageview7);
	       imageView8=(ImageView) findViewById(R.id.imageview8);
	       imageView9=(ImageView) findViewById(R.id.imageview9); 
	       
	        imageView1.setOnClickListener(goodsIntroduce);   	   
		    imageView2.setOnClickListener(goodsIntroduce);   	  
		    imageView3.setOnClickListener(goodsIntroduce);   	   
		    imageView4.setOnClickListener(goodsIntroduce);   	  
		    imageView5.setOnClickListener(goodsIntroduce);   	   
		    imageView6.setOnClickListener(goodsIntroduce);   	    
		    imageView7.setOnClickListener(goodsIntroduce);   	  
		    imageView8.setOnClickListener(goodsIntroduce);   	    
		    imageView9.setOnClickListener(goodsIntroduce);
	        
	        
	        
	 		imageView1.setImageBitmap(goodsdata.bit(image[t+0]));	 		  	
			imageView2.setImageBitmap(goodsdata.bit(image[t+1])); 
			imageView3.setImageBitmap(goodsdata.bit(image[t+2])); 
			imageView4.setImageBitmap(goodsdata.bit(image[t+3])); 
			imageView5.setImageBitmap(goodsdata.bit(image[t+4])); 
			imageView6.setImageBitmap(goodsdata.bit(image[t+5]));	 		  	
			imageView7.setImageBitmap(goodsdata.bit(image[t+6])); 
			imageView8.setImageBitmap(goodsdata.bit(image[t+7])); 
			imageView9.setImageBitmap(goodsdata.bit(image[t+8])); 
			
	 	}
	
	  private OnClickListener goodsIntroduce=new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ImageView image=(ImageView) v;
				switch (image.getId()) {
				case R.id.imageview1:
					Intent intent1=new Intent(Seller_Show.this,SellGoodsIntroduce.class);
					intent1.putExtra("index",goodsid[t+0]);
					startActivity(intent1);	
					break;	
				case R.id.imageview2:
					Intent intent2=new Intent(Seller_Show.this,SellGoodsIntroduce.class);
					intent2.putExtra("index",goodsid[t+1]);
					startActivity(intent2);					
					break;		
				case R.id.imageview3:
					Intent intent3=new Intent(Seller_Show.this,SellGoodsIntroduce.class);
					intent3.putExtra("index",goodsid[t+2]);
					startActivity(intent3);					
					break;		
				case R.id.imageview4:
					Intent intent4=new Intent(Seller_Show.this,SellGoodsIntroduce.class);
					intent4.putExtra("index",goodsid[t+3]);
					startActivity(intent4);					
					break;
				case R.id.imageview5:
					Intent intent5=new Intent(Seller_Show.this,SellGoodsIntroduce.class);
					intent5.putExtra("index",goodsid[t+4]);
					startActivity(intent5);					
					break;
				case R.id.imageview6:
					Intent intent6=new Intent(Seller_Show.this,SellGoodsIntroduce.class);
					intent6.putExtra("index",goodsid[t+5]);
					startActivity(intent6);					
					break;
				case R.id.imageview7:
					Intent intent7=new Intent(Seller_Show.this,SellGoodsIntroduce.class);
					intent7.putExtra("index",goodsid[t+6]);
					startActivity(intent7);					
					break;			
				case R.id.imageview8:
					Intent intent8=new Intent(Seller_Show.this,SellGoodsIntroduce.class);
					intent8.putExtra("index",goodsid[t+7]);
					startActivity(intent8);					
					break;
				case R.id.imageview9:
					Intent intent9=new Intent(Seller_Show.this,SellGoodsIntroduce.class);
					intent9.putExtra("index",goodsid[t+8]);
					startActivity(intent9);					
					break;
				default:
					break;
				}	
				}	
			};
	
	 private OnClickListener button=new OnClickListener() {

		@Override
		public void onClick(View v) {
			Button btn=(Button) v;
			switch (btn.getId()) {
			case R.id.button_1:
				if(t==0){
					break;
				}
				else{					
					t=t-9;					
					imageView1.setImageBitmap(goodsdata.bit(image[t+0]));	 		  	
					imageView2.setImageBitmap(goodsdata.bit(image[t+1])); 
					imageView3.setImageBitmap(goodsdata.bit(image[t+2])); 
					imageView4.setImageBitmap(goodsdata.bit(image[t+3])); 
					imageView5.setImageBitmap(goodsdata.bit(image[t+4])); 
					imageView6.setImageBitmap(goodsdata.bit(image[t+5]));	 		  	
					imageView7.setImageBitmap(goodsdata.bit(image[t+6])); 
					imageView8.setImageBitmap(goodsdata.bit(image[t+7])); 
					imageView9.setImageBitmap(goodsdata.bit(image[t+8])); 															
				}
				break;		
			case R.id.button_2:
				t=t+9;
				imageView1.setImageBitmap(goodsdata.bit(image[t+0]));	 		  	
				imageView2.setImageBitmap(goodsdata.bit(image[t+1])); 
				imageView3.setImageBitmap(goodsdata.bit(image[t+2])); 
				imageView4.setImageBitmap(goodsdata.bit(image[t+3])); 
				imageView5.setImageBitmap(goodsdata.bit(image[t+4])); 
				imageView6.setImageBitmap(goodsdata.bit(image[t+5]));	 		  	
				imageView7.setImageBitmap(goodsdata.bit(image[t+6])); 
				imageView8.setImageBitmap(goodsdata.bit(image[t+7])); 
				imageView9.setImageBitmap(goodsdata.bit(image[t+8])); 						
				break;
			default:
				break;
				case R.id.button_3:
				  	showListDialog() ;		
					break;					
		}
		}
		 
		 
		 
	 };
	
	
	
	
	String[] provinces = new String[] { "发起活动", "查看预定","商品上铺",}; 
	private void showListDialog()  {
		    	
	 new AlertDialog.Builder(this).setTitle("请选择：")
		 .setItems(provinces, new DialogInterface.OnClickListener() {
			
		public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			
			if(provinces[which]=="发起活动"){
					Intent intent1=new Intent(Seller_Show.this,SellerPublishActive.class);					      	
					startActivity(intent1);
				}	
			if(provinces[which]=="查看预定"){
				Intent intent1=new Intent(Seller_Show.this,SellerViewScheduledClient.class);					      	
				startActivity(intent1);
			}	
			if(provinces[which]=="活动"){
				Intent intent1=new Intent(Seller_Show.this,CancelBusinessActivity.class);					      	
				startActivity(intent1);
			}
//			if(provinces[which]=="查看预定"){
//				Intent intent1=new Intent(Seller_Show.this,SellerViewScheduledClient.class);					      	
//				startActivity(intent1);
//			}
			if(provinces[which]=="商品上铺"){
				Intent intent1=new Intent(Seller_Show.this,Up_seller.class);					     
				startActivity(intent1);
			}	
		} }). show();
	}
	 private OnClickListener menu=new OnClickListener() {
			@Override
			public void onClick(View v) {
				LinearLayout menu=(LinearLayout) v;
			switch (menu.getId()) {
			case R.id.menu1:
				Intent intent1=new Intent(Seller_Show.this,Index.class);
				intent1.putExtra("tltle", "首页");
				startActivity(intent1);				
				break;		
			case R.id.menu2:
				Intent intent2=new Intent(Seller_Show.this,Type.class);
				intent2.putExtra("tltle", "分类");
				startActivity(intent2);					
				break;
			case R.id.menu3:
				Intent intent3=new Intent(Seller_Show.this,StoresType.class);
				intent3.putExtra("tltle", "商家列表");
				startActivity(intent3);					
				break;
			case R.id.menu4:
				Intent intent4=new Intent(Seller_Show.this,Maps.class);
				intent4.putExtra("tltle", "百乐淘");
				startActivity(intent4);					
				break;
			case R.id.menu5:
				Intent intent5=new Intent(Seller_Show.this,More.class);
				intent5.putExtra("tltle", "更多");
				startActivity(intent5);					
				break;
			default:
				break;
			}	}		
		};
		
		  public boolean onCreateOptionsMenu(Menu menu) {

		        menu.add(0, 1, 1, "刷新");	    
		        menu.add(0, 2, 2, "关于");
		        menu.add(0,3,3,"退出");
		        return super.onCreateOptionsMenu(menu);
		    } public boolean onOptionsItemSelected(MenuItem item) {     
		    	      if(item.getItemId() == 1){         
		    	    		Intent intent1=new Intent(Seller_Show.this,Seller_Show.class);
							
							startActivity(intent1);	
	            finish();
	      }    
		    	      else if(item.getItemId() == 2){       
	  		Intent intent=new Intent(Seller_Show.this,ActivityShow.class);
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