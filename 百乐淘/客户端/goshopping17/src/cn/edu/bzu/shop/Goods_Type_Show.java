package cn.edu.bzu.shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.edu.bzu.activity.ActivityShow;
import cn.edu.bzu.bean.Consumer;
import cn.edu.bzu.bean.Goods;
import cn.edu.bzu.data.ConsumerData;
import cn.edu.bzu.data.Goodsdata;
import cn.edu.bzu.main.StoresType;
import cn.edu.bzu.main.Index;
import cn.edu.bzu.main.More;
import cn.edu.bzu.main.R;
import cn.edu.bzu.main.SysApplication;
import cn.edu.bzu.main.Type;
import cn.edu.bzu.map.Maps;

public class Goods_Type_Show extends Activity{
	/** Called when the activity is first created. */
	Goodsdata goodsdata = new Goodsdata();
	ConsumerData consumerdata = new ConsumerData();
	String[] image = new String[50];
	int[] goodsid = null;
	String data = null;
	String account = null;
	int number;
	int t = 0;
	private ViewGroup layoutshow;
	private ViewGroup layoutshownext;
	private Rotate3D lQuest1Animation;
	private Rotate3D lQuest2Animation;
	private Rotate3D rQuest1Animation;
	private Rotate3D rQuest2Animation;
	private int mCenterX = 160;
	private int mCenterY = 240;
	ImageView imageView1;
	ImageView imageView2;
	ImageView imageView3;
	ImageView imageView4;
	ImageView imageView5;
	ImageView imageView6;
	ImageView imageView7;
	ImageView imageView8;
	ImageView imageView9;
	ImageView imageView10;
	ImageView imageView11;
	ImageView imageView12;
	ImageView imageView13;
	ImageView imageView14;
	ImageView imageView15;
	ImageView imageView16;
	ImageView imageView17;
	ImageView imageView18;

	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		initAnimation();
		initMain();
		SysApplication.getInstance().addActivity(this);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			data = extras.getString("index");
		}
		 Goods[] goods=goodsdata.goodsImage(data);
		number = goods.length;
		goodsid = new int[number];
		for (int i = 0; i < number; i++) {
			image[i] = new String();
			goodsid[i] = Integer.parseInt(goods[i].getGoodsId());
			image[i] = goods[i].getCode();
		}

		this.goodsshow();

		LinearLayout menu1 = (LinearLayout) findViewById(R.id.menu1);
		menu1.setOnClickListener(menu);
		LinearLayout menu2 = (LinearLayout) findViewById(R.id.menu2);
		menu2.setOnClickListener(menu);
		LinearLayout menu3 = (LinearLayout) findViewById(R.id.menu3);
		menu3.setOnClickListener(menu);
		LinearLayout menu4 = (LinearLayout) findViewById(R.id.menu4);
		menu4.setOnClickListener(menu);
		LinearLayout menu5 = (LinearLayout) findViewById(R.id.menu5);
		menu5.setOnClickListener(menu);
	}

	private void initMain() {
		// TODO Auto-generated method stub
		setContentView(R.layout.show);
		this.goodsshowmain();
		layoutshow = (LinearLayout) findViewById(R.id.layout_show);
		Button button1 = (Button) findViewById(R.id.button_1);
		button1.setOnClickListener(button);
		Button button2 = (Button) findViewById(R.id.button_2);
		button2.setOnClickListener(button);
	}

	private void initNext() {
		setContentView(R.layout.shownext);
		this.goodsshownext();
		layoutshownext = (LinearLayout) findViewById(R.id.layout_shownext);
		Button button3 = (Button) findViewById(R.id.button_3);
		button3.setOnClickListener(button2);
		Button button4 = (Button) findViewById(R.id.button_4);
		button4.setOnClickListener(button2);
	}

	private void initAnimation() {
		// TODO Auto-generated method stub
		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		mCenterX = dm.widthPixels / 2;
		mCenterY = dm.heightPixels / 2;
		int duration = 1000;
		lQuest1Animation = new Rotate3D(0, 90, mCenterX, mCenterY);
		lQuest1Animation.setFillAfter(true);
		lQuest1Animation.setDuration(duration);

		lQuest2Animation = new Rotate3D(-90, 0, mCenterX, mCenterY);
		lQuest2Animation.setFillAfter(true);
		lQuest2Animation.setDuration(duration);

		rQuest1Animation = new Rotate3D(0, -90, mCenterX, mCenterY);
		rQuest1Animation.setFillAfter(true);
		rQuest1Animation.setDuration(duration);

		rQuest2Animation = new Rotate3D(90, 0, mCenterX, mCenterY);
		rQuest2Animation.setFillAfter(true);
		rQuest2Animation.setDuration(duration);
	}

	public void goodsshow() {

		imageView1 = (ImageView) findViewById(R.id.imageview1);
		imageView2 = (ImageView) findViewById(R.id.imageview2);
		imageView3 = (ImageView) findViewById(R.id.imageview3);
		imageView4 = (ImageView) findViewById(R.id.imageview4);
		imageView5 = (ImageView) findViewById(R.id.imageview5);
		imageView6 = (ImageView) findViewById(R.id.imageview6);
		imageView7 = (ImageView) findViewById(R.id.imageview7);
		imageView8 = (ImageView) findViewById(R.id.imageview8);
		imageView9 = (ImageView) findViewById(R.id.imageview9);

		imageView1.setImageBitmap(goodsdata.bit(image[t + 0]));
		imageView2.setImageBitmap(goodsdata.bit(image[t + 1]));
		imageView3.setImageBitmap(goodsdata.bit(image[t + 2]));
		imageView4.setImageBitmap(goodsdata.bit(image[t + 3]));
		imageView5.setImageBitmap(goodsdata.bit(image[t + 4]));
		imageView6.setImageBitmap(goodsdata.bit(image[t + 5]));
		imageView7.setImageBitmap(goodsdata.bit(image[t + 6]));
		imageView8.setImageBitmap(goodsdata.bit(image[t + 7]));
		imageView9.setImageBitmap(goodsdata.bit(image[t + 8]));

		imageView1.setOnClickListener(goodsIntroduce);
		imageView2.setOnClickListener(goodsIntroduce);
		imageView3.setOnClickListener(goodsIntroduce);
		imageView4.setOnClickListener(goodsIntroduce);
		imageView5.setOnClickListener(goodsIntroduce);
		imageView6.setOnClickListener(goodsIntroduce);
		imageView7.setOnClickListener(goodsIntroduce);
		imageView8.setOnClickListener(goodsIntroduce);
		imageView9.setOnClickListener(goodsIntroduce);
	}

	public void goodsshowmain() {

		imageView1 = (ImageView) findViewById(R.id.imageview1);
		imageView2 = (ImageView) findViewById(R.id.imageview2);
		imageView3 = (ImageView) findViewById(R.id.imageview3);
		imageView4 = (ImageView) findViewById(R.id.imageview4);
		imageView5 = (ImageView) findViewById(R.id.imageview5);
		imageView6 = (ImageView) findViewById(R.id.imageview6);
		imageView7 = (ImageView) findViewById(R.id.imageview7);
		imageView8 = (ImageView) findViewById(R.id.imageview8);
		imageView9 = (ImageView) findViewById(R.id.imageview9);

		imageView1.setImageBitmap(goodsdata.bit(image[t + 0]));
		imageView2.setImageBitmap(goodsdata.bit(image[t + 1]));
		imageView3.setImageBitmap(goodsdata.bit(image[t + 2]));
		imageView4.setImageBitmap(goodsdata.bit(image[t + 3]));
		imageView5.setImageBitmap(goodsdata.bit(image[t + 4]));
		imageView6.setImageBitmap(goodsdata.bit(image[t + 5]));
		imageView7.setImageBitmap(goodsdata.bit(image[t + 6]));
		imageView8.setImageBitmap(goodsdata.bit(image[t + 7]));
		imageView9.setImageBitmap(goodsdata.bit(image[t + 8]));

		imageView1.setOnClickListener(goodsIntroduce);
		imageView2.setOnClickListener(goodsIntroduce);
		imageView3.setOnClickListener(goodsIntroduce);
		imageView4.setOnClickListener(goodsIntroduce);
		imageView5.setOnClickListener(goodsIntroduce);
		imageView6.setOnClickListener(goodsIntroduce);
		imageView7.setOnClickListener(goodsIntroduce);
		imageView8.setOnClickListener(goodsIntroduce);
		imageView9.setOnClickListener(goodsIntroduce);
	}

	public void goodsshownext() {

		imageView10 = (ImageView) findViewById(R.id.imageview10);
		imageView11 = (ImageView) findViewById(R.id.imageview11);
		imageView12 = (ImageView) findViewById(R.id.imageview12);
		imageView13 = (ImageView) findViewById(R.id.imageview13);
		imageView14 = (ImageView) findViewById(R.id.imageview14);
		imageView15 = (ImageView) findViewById(R.id.imageview15);
		imageView16 = (ImageView) findViewById(R.id.imageview16);
		imageView17 = (ImageView) findViewById(R.id.imageview17);
		imageView18 = (ImageView) findViewById(R.id.imageview18);

		imageView10.setImageBitmap(goodsdata.bit(image[t + 0]));
		imageView11.setImageBitmap(goodsdata.bit(image[t + 1]));
		imageView12.setImageBitmap(goodsdata.bit(image[t + 2]));
		imageView13.setImageBitmap(goodsdata.bit(image[t + 3]));
		imageView14.setImageBitmap(goodsdata.bit(image[t + 4]));
		imageView15.setImageBitmap(goodsdata.bit(image[t + 5]));
		imageView16.setImageBitmap(goodsdata.bit(image[t + 6]));
		imageView17.setImageBitmap(goodsdata.bit(image[t + 7]));
		imageView18.setImageBitmap(goodsdata.bit(image[t + 8]));

		imageView10.setOnClickListener(goodsIntroduce2);
		imageView11.setOnClickListener(goodsIntroduce2);
		imageView12.setOnClickListener(goodsIntroduce2);
		imageView13.setOnClickListener(goodsIntroduce2);
		imageView14.setOnClickListener(goodsIntroduce2);
		imageView15.setOnClickListener(goodsIntroduce2);
		imageView16.setOnClickListener(goodsIntroduce2);
		imageView17.setOnClickListener(goodsIntroduce2);
		imageView18.setOnClickListener(goodsIntroduce2);

	}

	private OnClickListener goodsIntroduce = new OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ImageView image = (ImageView) v;
			switch (image.getId()) {
			case R.id.imageview1:
				Intent intent1 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent1.putExtra("index", goodsid[t + 0]);
				startActivity(intent1);
				break;
			case R.id.imageview2:
				Intent intent2 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent2.putExtra("index", goodsid[t + 1]);
				startActivity(intent2);
				break;
			case R.id.imageview3:
				Intent intent3 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent3.putExtra("index", goodsid[t + 2]);
				startActivity(intent3);
				break;
			case R.id.imageview4:
				Intent intent4 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent4.putExtra("index", goodsid[t + 3]);
				startActivity(intent4);
				break;
			case R.id.imageview5:
				Intent intent5 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent5.putExtra("index", goodsid[t + 4]);
				startActivity(intent5);
				break;
			case R.id.imageview6:
				Intent intent6 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent6.putExtra("index", goodsid[t + 5]);
				startActivity(intent6);
				break;
			case R.id.imageview7:
				Intent intent7 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent7.putExtra("index", goodsid[t + 6]);
				startActivity(intent7);
				break;
			case R.id.imageview8:
				Intent intent8 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent8.putExtra("index", goodsid[t + 7]);
				startActivity(intent8);
				break;
			case R.id.imageview9:
				Intent intent9 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent9.putExtra("index", goodsid[t + 8]);
				startActivity(intent9);
				break;
			default:
				break;
			}
		}
	};
	private OnClickListener goodsIntroduce2 = new OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ImageView image = (ImageView) v;
			switch (image.getId()) {
			case R.id.imageview10:
				Intent intent1 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent1.putExtra("index", goodsid[t + 0]);
				startActivity(intent1);
				break;
			case R.id.imageview11:
				Intent intent2 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent2.putExtra("index", goodsid[t + 1]);
				startActivity(intent2);
				break;
			case R.id.imageview12:
				Intent intent3 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent3.putExtra("index", goodsid[t + 2]);
				startActivity(intent3);
				break;
			case R.id.imageview13:
				Intent intent4 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent4.putExtra("index", goodsid[t + 3]);
				startActivity(intent4);
				break;
			case R.id.imageview14:
				Intent intent5 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent5.putExtra("index", goodsid[t + 4]);
				startActivity(intent5);
				break;
			case R.id.imageview15:
				Intent intent6 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent6.putExtra("index", goodsid[t + 5]);
				startActivity(intent6);
				break;
			case R.id.imageview16:
				Intent intent7 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent7.putExtra("index", goodsid[t + 6]);
				startActivity(intent7);
				break;
			case R.id.imageview17:
				Intent intent8 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent8.putExtra("index", goodsid[t + 7]);
				startActivity(intent8);
				break;
			case R.id.imageview18:
				Intent intent9 = new Intent(Goods_Type_Show.this,
						GoodsIntroduce.class);
				intent9.putExtra("index", goodsid[t + 8]);
				startActivity(intent9);
				break;
			default:
				break;
			}
		}
	};
	private OnClickListener button = new OnClickListener() {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Button button = (Button) v;
			switch (button.getId()) {
			case R.id.button_1:
				if (t == 0) {
					break;
				} else {
					layoutshow.startAnimation(lQuest1Animation);
					t = t - 9;
					initNext();
					layoutshownext.startAnimation(lQuest2Animation);
					break;
				}

			case R.id.button_2:
				layoutshow.startAnimation(rQuest1Animation);
				t = t + 9;
				initNext();
				layoutshownext.startAnimation(rQuest2Animation);
				break;
			default:
				break;
			}
		}
	};
	private OnClickListener button2 = new OnClickListener() {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Button button = (Button) v;
			switch (button.getId()) {
			case R.id.button_3:
				if (t == 0) {
					break;
				} else {
					layoutshownext.startAnimation(lQuest1Animation);
					t = t - 9;
					initMain();
					layoutshow.startAnimation(lQuest2Animation);
					break;
				}

			case R.id.button_4:
				layoutshownext.startAnimation(rQuest1Animation);
				t = t + 9;
				initMain();
				layoutshow.startAnimation(rQuest2Animation);
				break;

			default:
				break;
			}
		}
	};
	private OnClickListener menu = new OnClickListener() {

		public void onClick(View v) {
			LinearLayout menu = (LinearLayout) v;
			switch (menu.getId()) {
			case R.id.menu1:
				Intent intent1 = new Intent(Goods_Type_Show.this, Index.class);
				intent1.putExtra("tltle", "首页");
				startActivity(intent1);
				break;
			case R.id.menu2:
				Intent intent2 = new Intent(Goods_Type_Show.this, Type.class);
				intent2.putExtra("tltle", "分类");
				startActivity(intent2);
				break;
			case R.id.menu3:
				Intent intent3 = new Intent(Goods_Type_Show.this,
						StoresType.class);
				intent3.putExtra("tltle", "商家列表〃");
				startActivity(intent3);
				break;
			case R.id.menu4:
				Intent intent4 = new Intent(Goods_Type_Show.this, Maps.class);
				intent4.putExtra("tltle", "百乐淘");
				startActivity(intent4);
				break;
			case R.id.menu5:
				Intent intent5 = new Intent(Goods_Type_Show.this, More.class);
				intent5.putExtra("tltle", "更多");
				startActivity(intent5);
				break;
			default:
				break;
			}
		}
	};

	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(0, 1, 1, "刷新");
		menu.add(0, 2, 2, "关于");
		menu.add(0, 3, 3, "退出");
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 1) {
			Intent intent1 = new Intent(Goods_Type_Show.this,
					Goods_Rank_Show.class);

			startActivity(intent1);
			finish();
		} else if (item.getItemId() == 2) {
			Intent intent = new Intent(Goods_Type_Show.this, ActivityShow.class);
			startActivity(intent);
		} else if (item.getItemId() == 3) {
			account = Consumer.getClientAccount();
			if (account != null) {
				consumerdata.exit(account);
				SysApplication.getInstance().exit();
			} else {
				SysApplication.getInstance().exit();
			}
		}

		return true;
	}
       
}
