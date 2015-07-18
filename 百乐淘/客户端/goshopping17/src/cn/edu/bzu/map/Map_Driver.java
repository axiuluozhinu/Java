package cn.edu.bzu.map;

import android.os.Bundle;
import android.view.Window;
import cn.edu.bzu.bean.Stores;
import cn.edu.bzu.main.R;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKPlanNode;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.RouteOverlay;

public class Map_Driver extends MapActivity{
	BMapManager mBMapMan ;
	 MKSearch mKSearch; 
	  Double Lat;
		Double Lon;
		MapView mMapView;
		MapController mapController;   

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
			GeoPoint point = new GeoPoint((int) (37.387754*1e6), (int) (117.99144*1e6));
			mMapController.setCenter(point);
			mMapController.setZoom(16);   
	        mKSearch = new MKSearch(); 
	    
	        mKSearch.init(mBMapMan, new MySearchListener());       	      
	    	MKPlanNode start = new MKPlanNode();
			start.pt = new GeoPoint((int) (37.387754*1e6), (int) (117.99144*1e6));		
			MKPlanNode end = new MKPlanNode();
	    	     Lat= Stores.getAlatitude();
	             Lon = Stores.getAlongitude();
	             System.out.println(Lat+"sfa"+"4214124");
	        end.pt = new GeoPoint((int)(Lat* 1E6),(int)(Lon* 1E6));   	                
	        mKSearch.setDrivingPolicy(MKSearch.ECAR_TIME_FIRST); 	       
	        mKSearch.drivingSearch(null, start, null, end); 
			}
	
	public class MySearchListener implements MKSearchListener {
	    @Override
	    public void onGetAddrResult(MKAddrInfo result, int iError) {
	    }
	 
	    @Override
	    	public void onGetDrivingRouteResult(MKDrivingRouteResult result, int iError) {
	    	    if (result == null) {
	    	        return;
	    	    }
	    	    RouteOverlay routeOverlay = new RouteOverlay(Map_Driver.this, mMapView);
	    	    routeOverlay.setData(result.getPlan(0).getRoute(0));
	    	    mMapView.getOverlays().add(routeOverlay);
	    	
	    }
	 
	    @Override
	    public void onGetPoiResult(MKPoiResult result, int type, int iError) {
	    }
	 
	    @Override
	    public void onGetTransitRouteResult(MKTransitRouteResult result, int iError) {
	    }
	 
	    @Override
	    public void onGetWalkingRouteResult(MKWalkingRouteResult result, int iError) {
	    }
	 
	    @Override
	    public void onGetBusDetailResult(MKBusLineResult result, int iError) {
	    }
	 
	    @Override
	    public void onGetSuggestionResult(MKSuggestionResult result, int iError) {
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
}
