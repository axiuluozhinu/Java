package cn.edu.bzu.map;

import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import cn.edu.bzu.bean.Stores;
import cn.edu.bzu.data.StoresData;
import cn.edu.bzu.main.R;
import cn.edu.bzu.shop.ShopActive;
import cn.edu.bzu.shop.ShopInformation;
import cn.edu.bzu.shop.Stores_Map_Show;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.OverlayItem;

public class Maps extends MapActivity {
	BMapManager mBMapMan;
	private List<OverlayItem> GeoList = new ArrayList<OverlayItem>();
	StoresData storesData = new StoresData();
	Stores[] stores = storesData.storesInformation();
	int q, i, p, jl2 = 0;
	int[] w = new int[10000];
	EditText edt;
	MKDrivingRouteResult result;
	MapView mMapView;
	GeoPoint[] point = new GeoPoint[stores.length];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baidumap);
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("7951C41C3A83986C42C7BD06AD83C33BEE7CB616", null);
		super.initMapActivity(mBMapMan);
		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);
		MapController mMapController = mMapView.getController(); 
		GeoPoint point = new GeoPoint((int) (37.387754 * 1e6),
				(int) (117.99144 * 1e6)); 
		mMapController.setCenter(point); 
		mMapController.setZoom(16);
		Drawable marker = getResources().getDrawable(R.drawable.biao);
		mMapView.getOverlays().add(new OverItemT(marker, this));
		TextView textViewTitle = (TextView) findViewById(R.id.title);
		textViewTitle.setText("地图");
	}

	class OverItemT extends ItemizedOverlay<OverlayItem> {
	
		private Context mContext;
		double mLat1;
		double mLon1;

		public OverItemT(Drawable marker, Context context) {
			super(boundCenterBottom(marker));
			this.mContext = context;
			GeoPoint[] point = new GeoPoint[stores.length];
			for (i = 0; i < stores.length; i++) {
			
				double lat_a = 37.387754;
				double lng_a = 117.99144;
				double EARTH_RADIUS = 6378137.0;
				double lat_b = Double.parseDouble(stores[i].getLatitude());
				double lng_b = Double.parseDouble(stores[i].getLongitude());
				double radLat1 = (lat_a * Math.PI / 180.0);
				double radLat2 = (lat_b * Math.PI / 180.0);
				double a = radLat1 - radLat2;
				double b = (lng_a - lng_b) * Math.PI / 180.0;
				double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
						+ Math.cos(radLat1) * Math.cos(radLat2)
						* Math.pow(Math.sin(b / 2), 2)));
				s = s * EARTH_RADIUS;
				s = Math.round(s * 10000) / 10000;

				String jl;
				jl = Stores.getDistance();
				if (jl == null) {
					jl = "3000";
					jl2 = Integer.parseInt(jl);
				} else {
					jl2 = Integer.parseInt(Stores.getDistance());
				}

				if (s <= jl2) {
					point[i] = new GeoPoint(
							(int) (Double.parseDouble(stores[i].getLatitude()) * 1E6),
							(int) (Double.parseDouble(stores[i].getLongitude()) * 1E6));
					GeoList.add(new OverlayItem(point[i], "", ""));
					populate();
					w[p] = i;
					p++;
				}
			}
		}

		protected OverlayItem createItem(int i) {
			// TODO Auto-generated method stub
			return GeoList.get(i);
		}

		public int size() {
			// TODO Auto-generated method stub
			return GeoList.size();
		}

		// 处理当点击事件
		protected boolean onTap(int i) {
			showListDialog(i);
			return true;
		}
	}

	protected void onDestroy() {
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
	}

	protected boolean isRouteDisplayed() {
		return false;
	}

	String[] provinces = new String[] { "进入商店", "商家信息", "近期活动", "行车路线" };

	private void showListDialog(int i) {
		final String id;
		q = w[i];
		id = stores[q].getStoreId();
		new AlertDialog.Builder(this).setTitle("请选择：")
				.setItems(provinces, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (provinces[which] == "进入商店") {
							Intent intent1 = new Intent(Maps.this,
									Stores_Map_Show.class);
							intent1.putExtra("storesid", id);
							startActivity(intent1);
						}
						if (provinces[which] == "商家信息") {
							Intent intent1 = new Intent(Maps.this,
									ShopInformation.class);
							intent1.putExtra("name", "进入商店");
							intent1.putExtra("storesid", id);
							startActivity(intent1);
						}
						if (provinces[which] == "近期活动") {
							Intent intent1 = new Intent(Maps.this,
									ShopActive.class);
						
							intent1.putExtra("storesid", id);
							startActivity(intent1);
						}
						if (provinces[which] == "行车路线") {

							Stores.setAlongitude(Double.parseDouble(stores[q]
									.getLongitude()));
							Stores.setAlatitude(Double.parseDouble(stores[q]
									.getLatitude()));
							Intent intent1 = new Intent(Maps.this,
									Map_Driver.class);

							System.out.println("dasdas"
									+ Double.parseDouble(stores[q]
											.getLatitude()));
							startActivity(intent1);
						}
					}
				}).show();

	}

	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(0, 1, 1, "设定距离");
		menu.add(0, 2, 2, "条件搜索");
		menu.add(0, 3, 3, "刷新");
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		edt = new EditText(this);
		if (item.getItemId() == 1) {
			new AlertDialog.Builder(this).setTitle("请输入搜索范围（单位：米）")
					.setIcon(android.R.drawable.ic_dialog_info).setView(edt)
					.setPositiveButton("确定", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String et1str = edt.getText().toString().trim();
							Stores.setDistance(et1str);
							Intent intent1 = new Intent(Maps.this, Aaa.class);
							startActivity(intent1);
						}
					}).show();
		}
		if (item.getItemId() == 2) {
			condition1(i);
			// 弹出选项
		}
		if (item.getItemId() == 3) {
			Intent intent1 = new Intent(Maps.this, Maps.class);
			startActivity(intent1);
		}
		return true;
	}

	String[] condition = new String[] { "一星级", "二星级", "三星级" };
	private void condition1(int t) {
		
		final String id;
		q = w[t];
		id = stores[q].getStoreId();
		new AlertDialog.Builder(this).setTitle("请选择：")
				.setItems(condition, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (provinces[which] == "一星级") {
							System.out.println("asdasfsa");
							String et1str = "1";
							Stores.setJinpai(et1str);
							Intent intent1 = new Intent(Maps.this, Aaa.class);
							startActivity(intent1);
							
						}
						if (provinces[which] == "二星级") {
							String et1str = "2";
							Stores.setJinpai(et1str);
							Intent intent1 = new Intent(Maps.this, Aaa.class);
							startActivity(intent1);
			}

					}
				}).show();

	}

}
