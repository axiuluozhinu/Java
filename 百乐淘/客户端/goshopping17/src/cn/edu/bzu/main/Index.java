package cn.edu.bzu.main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;

import com.baidu.mapapi.GeoPoint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.bzu.activity.ActivityShow;
import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.bean.Goods;
import cn.edu.bzu.data.ActiveData;
import cn.edu.bzu.data.ConsumerData;
import cn.edu.bzu.data.Goodsdata;
import cn.edu.bzu.map.Maps;
import cn.edu.bzu.shop.GoodsIntroduce;
import cn.edu.bzu.shop.Goods_Rank_Show;
import cn.edu.bzu.shop.Goods_Type_Show;

public class Index extends Activity {	 
		/** Called when the activity is first created. */
	    private EditText et;
	    ActiveData activedata=new ActiveData();
	    final Goodsdata goodsdata=new Goodsdata();
	    Goods[] goods=null;
	    final String[] image=new String[6];
	    String[] activityimage= activedata.activityimage();
	    ConsumerData consumerdata=new ConsumerData();
	    Consumer consumer=new Consumer();
	    String account=null;
	    String latLongString=null;
	    String add=null;
	    String address=null;
	    double longitude;
	    double altitude;
	    
	    int[] goodsid=new int[6];
	  public void onCreate(Bundle savedInstanceState) {    	
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.index);
	    SysApplication.getInstance().addActivity(this);	    
	    LocationManager loctionManager; 
        String contextService=Context.LOCATION_SERVICE;
        loctionManager=(LocationManager) getSystemService(contextService);
       String provider=LocationManager.GPS_PROVIDER; 
        Location location = loctionManager.getLastKnownLocation(provider);
        longitude=location.getLongitude();
        altitude=location.getAltitude();
        List<Address> addList = null;
        Geocoder ge = new Geocoder(this);
        try {
            addList = ge.getFromLocation(longitude, altitude, 1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		if(addList!=null && addList.size()>0){
            for(int i=0; i<addList.size(); i++){
               Address ad = addList.get(i);
               latLongString += "\n";
                latLongString += ad.getCountryName() + ";" + ad.getLocality();
                
            }
            
        }
	     add = geocodeAddr("37.387754", "117.15144");
	    goods=goodsdata.LocalAreaGoodsImage(address);
	    address=add.substring(add.indexOf("省")+1,add.lastIndexOf("市")+1);
	    for(int i=0;i<6;i++){
	    	image[i]=new String();
	    	goodsid[i]=Integer.parseInt(goods[i].getGoodsId());
	    	image[i]= goods[i].getCode();
	    }
	    
	    TextView textViewTitle = (TextView)findViewById(R.id.title);
		textViewTitle.setText("百乐淘");
	    Gallery gallery = ((Gallery) findViewById(R.id.gallery1)); 
	    gallery.setAdapter(new ImageAdapter(this)); 
	    gallery.setSelection(200); 
	    gallery.setOnItemSelectedListener(new OnItemSelectedListener() 
	    { 
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub			
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub			
		} 
	    }); 


	    ImageView imageView1=(ImageView) findViewById(R.id.imageView1);
	    imageView1.setOnClickListener(show);   
	    ImageView imageView2=(ImageView) findViewById(R.id.imageView2);
	    imageView2.setOnClickListener(show);
	    final ImageView imageView3=(ImageView) findViewById(R.id.imageView3);
	    imageView3.setOnClickListener(show);
	    final ImageView imageView4=(ImageView) findViewById(R.id.imageView4); 
	    imageView4.setOnClickListener(show);
	    final ImageView imageView5=(ImageView) findViewById(R.id.imageView5); 
	    imageView5.setOnClickListener(show);
	    final ImageView imageView6=(ImageView) findViewById(R.id.imageView6);
	    imageView6.setOnClickListener(show);
	    final ImageView imageView7=(ImageView) findViewById(R.id.imageView7);
	    imageView7.setOnClickListener(show);
	    
	    LinearLayout linerlayout8=( LinearLayout) findViewById(R.id.linear1);
	    linerlayout8.setOnClickListener(show1);   
	    LinearLayout linerlayout9=( LinearLayout) findViewById(R.id.linear2);
	    linerlayout9.setOnClickListener(show1);   
	    LinearLayout linerlayout10=( LinearLayout) findViewById(R.id.linear3);
	    linerlayout10.setOnClickListener(show1);   
	    LinearLayout linerlayout11=( LinearLayout) findViewById(R.id.linear4);
	    linerlayout11.setOnClickListener(show1);   
	    
	    TextView textViewTitle2 = (TextView)findViewById(R.id.goodsname2);
		textViewTitle2.setText(goods[0].getGoodsName());
		TextView textViewTitle3 = (TextView)findViewById(R.id.goodsname3);
		textViewTitle3.setText(goods[1].getGoodsName());
	    TextView textViewTitle4 = (TextView)findViewById(R.id.goodsname4);
		textViewTitle4.setText(goods[2].getGoodsName());
		TextView textViewTitle5 = (TextView)findViewById(R.id.goodsname5);
		textViewTitle5.setText(goods[3].getGoodsName());
		TextView textViewTitle6= (TextView)findViewById(R.id.goodsname6);
		textViewTitle6.setText(goods[4].getGoodsName());
		TextView textViewTitle7= (TextView)findViewById(R.id.goodsname7);
		textViewTitle7.setText(goods[5].getGoodsName());
	    	    	    
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
	    	
		 		  	// 将bitmap显示到界面上 
	    	imageView2.setImageBitmap(goodsdata.bit(image[0]));	 	
		 	imageView3.setImageBitmap(goodsdata.bit(image[1]));	 		  	
			imageView4.setImageBitmap(goodsdata.bit(image[2])); 
			imageView5.setImageBitmap(goodsdata.bit(image[3])); 
			imageView6.setImageBitmap(goodsdata.bit(image[4])); 
			imageView7.setImageBitmap(goodsdata.bit(image[5])); 
	    }
	  
	 
      public static String geocodeAddr(String latitude, String longitude) { 
          String addr = ""; 
         
          String url = String.format("http://ditu.google.cn/maps/geo?output=csv&key=abcdef&q=%s,%s",latitude, longitude); 
          URL myURL = null; 
          URLConnection httpsConn = null; 
          try { 
              myURL = new URL(url); 
          } catch (MalformedURLException e) { 
              e.printStackTrace(); 
              return null; 
          } 
          
          try { 
              httpsConn = (URLConnection) myURL.openConnection(); 
              if (httpsConn != null) {
                  InputStreamReader insr = new InputStreamReader(httpsConn 
                  .getInputStream(), "UTF-8"); 
                  BufferedReader br = new BufferedReader(insr); 
                  String data = null; 
                  if ((data = br.readLine()) != null) { 
                      System.out.println(data); 
                      String[] retList = data.split(","); 
                      if (retList.length > 2 && ("200".equals(retList[0]))) { 
                          addr = retList[2]; 
                          addr = addr.replace("\"", ""); 
                      } else { 
                          addr = ""; 
                      } 
                  } 
                  insr.close(); 
              } 
          } catch (IOException e) { 
              e.printStackTrace(); 
              return null; 
          } 
          return addr; 
      }         
	  public void bitmap(){
		  final Goodsdata getimage=new Goodsdata();
		    Bitmap[] bit=new Bitmap[6];
		  for(int i=0;i<6;i++){
			  bit[i]=getimage.bit(image[i]);
		  }
	  }
	
	    private OnClickListener show=new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ImageView image=(ImageView) v;
				switch (image.getId()) {
				case R.id.imageView1:
					et=(EditText)findViewById(R.id.find);
					String et1str=et.getText().toString().trim();
					if(et1str==null){
						break;
					}
					else{
					Intent intent1=new Intent(Index.this,Goods_Rank_Show.class);
					intent1.putExtra("index",et1str);
					startActivity(intent1);	
					}
					break;	
				case R.id.imageView2:
					Intent intent2=new Intent(Index.this,GoodsIntroduce.class);
					intent2.putExtra("index",goodsid[0]);
					startActivity(intent2);					
					break;		
				case R.id.imageView3:
					Intent intent3=new Intent(Index.this,GoodsIntroduce.class);
					intent3.putExtra("index",goodsid[1]);
					startActivity(intent3);					
					break;		
				case R.id.imageView4:
					Intent intent4=new Intent(Index.this,GoodsIntroduce.class);
					intent4.putExtra("index",goodsid[2]);
					startActivity(intent4);					
					break;
				case R.id.imageView5:
					Intent intent5=new Intent(Index.this,GoodsIntroduce.class);
					intent5.putExtra("index",goodsid[3]);
					startActivity(intent5);					
					break;
				case R.id.imageView6:
					Intent intent6=new Intent(Index.this,GoodsIntroduce.class);
					intent6.putExtra("index",goodsid[4]);
					startActivity(intent6);					
					break;
				case R.id.imageView7:
					Intent intent7=new Intent(Index.this,GoodsIntroduce.class);
					intent7.putExtra("index",goodsid[5]);
					startActivity(intent7);					
					break;
				}	}	
		};
					 private OnClickListener show1=new OnClickListener(){
							public void onClick(View v) {
								// TODO Auto-generated method stub
								LinearLayout linear=(LinearLayout) v;
								switch (linear.getId()) {
					
				case R.id.linear1:
					Intent intent8=new Intent(Index.this,Goods_Type_Show.class);
					intent8.putExtra("index","服装");
					startActivity(intent8);					
					break;
				case R.id.linear2:
					Intent intent9=new Intent(Index.this,Goods_Type_Show.class);
					intent9.putExtra("index","化妆品");
					startActivity(intent9);					
					break;
				case R.id.linear3:
					Intent intent10=new Intent(Index.this,Goods_Type_Show.class);
					intent10.putExtra("index","数码");
					startActivity(intent10);					
					break;
				case R.id.linear4:
					Intent intent11=new Intent(Index.this,Goods_Type_Show.class);
					intent11.putExtra("index","食品");
					startActivity(intent11);					
					break;
				default:
					break;
				}	}	
			};
		private OnClickListener menu=new OnClickListener() {
			@Override
			public void onClick(View v) {
				LinearLayout menu=(LinearLayout) v;
			switch (menu.getId()) {
			case R.id.menu1:
				Intent intent1=new Intent(Index.this,Index.class);
				intent1.putExtra("tltle", "首页");
				startActivity(intent1);				
				break;		
			case R.id.menu2:
				Intent intent2=new Intent(Index.this,Type.class);
				intent2.putExtra("tltle", "分类");
				startActivity(intent2);					
				break;
			case R.id.menu3:
				Intent intent3=new Intent(Index.this,StoresType.class);
				intent3.putExtra("tltle", "商家列表");
				startActivity(intent3);					
				break;
			case R.id.menu4:
				Intent intent4=new Intent(Index.this,Maps.class);
				intent4.putExtra("tltle", "百乐淘");
				startActivity(intent4);					
				break;
			case R.id.menu5:
				Intent intent5=new Intent(Index.this,More.class);
				intent5.putExtra("tltle", "更多");
				startActivity(intent5);					
				break;
			default:
				break;
			}	}		
		};
		  public class ImageAdapter extends BaseAdapter 
		  { 
			  final Goodsdata getimage=new Goodsdata();
			    Bitmap bit0=getimage.bit(activityimage[0]);
			    Bitmap bit1=getimage.bit(activityimage[1]);
			    Bitmap bit2=getimage.bit(activityimage[2]);
			    Bitmap bit3=getimage.bit(activityimage[3]);
			    Bitmap bit4=getimage.bit(activityimage[4]);
			    Bitmap bit5=getimage.bit(activityimage[4]);
			  			 		     
		    private Context myContext; 

		    int mGalleryItemBackground;
		  
		    private Bitmap[] myImageIds = 
		    {  bit0,bit1, bit2, bit3, 
		    		bit4 };   
		    public ImageAdapter(Context c) 
		    { 
		      myContext = c; 
		     
		      TypedArray a = obtainStyledAttributes(R.styleable.Gallery); 
		      mGalleryItemBackground = a.getResourceId( 
		          R.styleable.Gallery_android_galleryItemBackground, 0); 
		      a.recycle(); 
		    } 
		    public int getCount() 
		    { 
		      return Integer.MAX_VALUE; 
		    } 
		    public Object getItem(int position) 
		    { 
		      return position; 
		   } 

		   public long getItemId(int position) 
		    { 
		     return position; 
		   } 
		    public View getView(int position, View convertView, ViewGroup parent) 
		    { 
		      ImageView i = new ImageView(this.myContext); 
		      i.setImageBitmap(this.myImageIds[position% myImageIds.length]); 
		      i.setScaleType(ImageView.ScaleType.FIT_XY); 
		      i.setBackgroundResource(mGalleryItemBackground); 
		      i.setLayoutParams(new Gallery.LayoutParams(230, 150)); 
		      return i; 
		    } 
		    public float getScale(boolean focused, int offset) 
		    { 
		      return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset))); 
		    } 
		  } 

		  public boolean onCreateOptionsMenu(Menu menu) {

		        menu.add(0, 1, 1, "刷新");	    
		        menu.add(0, 2, 2, "关于");
		        menu.add(0,3,3,"退出");
		        return super.onCreateOptionsMenu(menu);
		    } public boolean onOptionsItemSelected(MenuItem item) {     
		    	      if(item.getItemId() == 1){         
		    	    		Intent intent1=new Intent(Index.this,Index.class);
							
							startActivity(intent1);	
	              finish();
	        }    
		    	      else if(item.getItemId() == 2){       
	    		Intent intent=new Intent(Index.this,ActivityShow.class);
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

