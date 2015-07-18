package cn.edu.bzu.map;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.edu.bzu.bean.Business;
import cn.edu.bzu.bean.Stores;
import cn.edu.bzu.bean.Storesregister;
import cn.edu.bzu.data.StoresData;
import cn.edu.bzu.main.R;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.OverlayItem;

public class Map_register1 extends MapActivity {
	BMapManager mBMapMan ;
	double mLat1 ; 
    double mLon1 ;
    StoresData storesdata=new StoresData();
	    
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_check);
		  mBMapMan = new BMapManager(getApplication());
			mBMapMan.init("7951C41C3A83986C42C7BD06AD83C33BEE7CB616", null);
			super.initMapActivity(mBMapMan);		 
			MapView mMapView = (MapView) findViewById(R.id.bmapsView);
			mMapView.setBuiltInZoomControls(true);
			MapController mMapController = mMapView.getController(); 
		    
		   
		             mLat1 = Stores.getAlatitude();
		             mLon1 = Stores.getAlongitude();
		             Storesregister.setLongitude(Stores.getAlatitude().toString());
		 	   		Storesregister.setLatitude(Stores.getAlongitude().toString());

			GeoPoint point = new GeoPoint((int) (mLat1* 1E6), (int) (mLon1* 1E6)); 
			mMapController.setCenter(point); 
			mMapController.setZoom(21);    
			Drawable marker = getResources().getDrawable(R.drawable.biao);
			mMapView.getOverlays().add(new OverItemT(marker, this));
		   
		
	}
	class OverItemT extends ItemizedOverlay<OverlayItem> {
	    private List<OverlayItem> GeoList = new ArrayList<OverlayItem>();
	    private Context mContext;
	 
	    public OverItemT(Drawable marker, Context context) {
	        super(boundCenterBottom(marker));
	      //确认按钮  
	        Button btn=(Button) findViewById(R.id.btn1);
	        btn.setOnClickListener(new OnClickListener() {       	
	   	@Override
	   	public void onClick(View v) {
	   	storesdata.storeRegister(Business.getBusinessAccount(),Storesregister.getStoreName(), Storesregister.getType(), Storesregister.getStoreApproval(), Storesregister.getProvince(), Storesregister.getCity(), Storesregister.getTown(), Storesregister.getAddress(), Storesregister.getLongitude(),Storesregister.getLatitude());
	    Toast.makeText(Map_register1.this, "创建成功", Toast.LENGTH_LONG).show();
	   	}
	        });  
	        this.mContext=context; 	   
	        GeoPoint p1 = new GeoPoint((int) (mLat1 * 1E6), (int) (mLon1 * 1E6));
	        GeoList.add(new OverlayItem(p1, "P1", "point1"));
	        populate(); 
	}
	    protected OverlayItem createItem(int i) {
	        return GeoList.get(i);
	    } 
	    @Override
	    public int size() {
	        return GeoList.size();
	    }	 
	    }
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
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
}

