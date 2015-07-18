package cn.edu.bzu.map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import cn.edu.bzu.bean.Stores;
import cn.edu.bzu.main.R;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;

public class Map_register extends MapActivity {
	BMapManager mBMapMan ;
	GeoPoint geo;

	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_register);
		  mBMapMan = new BMapManager(getApplication());
			mBMapMan.init("7951C41C3A83986C42C7BD06AD83C33BEE7CB616", null);
			super.initMapActivity(mBMapMan);		 
			 MapView mMapView = (MapView) findViewById(R.id.bmapsView);
			mMapView.setBuiltInZoomControls(true);
			MapController mMapController = mMapView.getController(); 
			GeoPoint point = new GeoPoint((int) (37.387754*1e6), (int) (117.99144*1e6));
			mMapController.setCenter(point);
			mMapController.setZoom(17);    
	      mMapView.getOverlays().add(new GetOverlay()); 
	     
       Button btn=(Button) findViewById(R.id.btn1);
     btn.setOnClickListener(new OnClickListener() {       	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
Double lat=null;
lat=Stores.getAlatitude();
if(lat==null){
	Toast.makeText(Map_register.this, "您未获取经纬度，请点击地图获取", Toast.LENGTH_LONG).show();
}
else{
	Intent intent1=new Intent(Map_register.this,Map_register1.class);
	
	startActivity(intent1);	
}
	}
});
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
