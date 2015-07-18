package cn.edu.bzu.map;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;
import cn.edu.bzu.main.R;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.MyLocationOverlay;
import com.baidu.mapapi.OverlayItem;
import com.baidu.mapapi.PoiOverlay;
public class Maps_location extends MapActivity {
 
        private BMapManager mapManager;
        private MapView mMapView;
        private MapController mapController;

        LocationListener mLocationListener = null;
        MyLocationOverlay mLocationOverlay = null; 
        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.baidumap);             
                mapManager = new BMapManager(getApplication());            
                mapManager.init("7951C41C3A83986C42C7BD06AD83C33BEE7CB616", null);
                super.initMapActivity(mapManager);
                mMapView = (MapView) findViewById(R.id.bmapsView);     
                mMapView.setBuiltInZoomControls(true);
                mMapView.setDrawOverlayWhenZooming(true);
                mLocationOverlay = new MyLocationOverlay(this, mMapView);
                mMapView.getOverlays().add(mLocationOverlay);
                mLocationListener = new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                                if (location != null) {
                                        GeoPoint geoPoint = new GeoPoint((int) (location.getLatitude() * 1e6),
                                                        (int) (location.getLongitude() * 1e6));
                                        mMapView.getController().animateTo(geoPoint);
                                        mapController = mMapView.getController();
                                        mapController.setCenter(geoPoint);
                                        mapController.setZoom(17);                                 
                                }
                        }
                };
                Drawable marker= getResources().getDrawable(R.drawable.ic_launcher); 
    	        mMapView.getOverlays().add(new OverItemT(marker, this)); 
        }
        class OverItemT extends ItemizedOverlay<OverlayItem> {
        	private List<OverlayItem> GeoList = new ArrayList<OverlayItem>();
    	    private Context mContext;
    	    String name="aaaa";
    	    private double mLat1 = 37.387777; 
    	    private double mLon1 = 117.99155; 
    	    private double mLat2 = 37.385555;
    	    private double mLon2 = 117.995555; 
    	    public OverItemT(Drawable marker, Context context) {
    		    super(boundCenterBottom(marker));
    		     this.mContext = context;
    	        
    	        GeoPoint p1 = new GeoPoint((int) (mLat1 * 1E6), (int) (mLon1 * 1E6));
    	        GeoPoint p2 = new GeoPoint((int) (mLat2 * 1E6), (int) (mLon2 * 1E6));
    	        GeoList.add(new OverlayItem(p1, "P1", name));
    	        GeoList.add(new OverlayItem(p2, "ccccc", "bbbb"));
    	   
    	        populate(); 
    	    }	     	 
    	    protected OverlayItem createItem(int i) {
    	        return GeoList.get(i);
    	    }	 
    	    @Override
    	    public int size() {
    	        return GeoList.size();
    	    }	 
    	    @Override   	
    	    protected boolean onTap(int i) {
    	        Toast.makeText(this.mContext, GeoList.get(i).getSnippet(),
    	        Toast.LENGTH_SHORT).show();	   
    	        return true;
    	    }
    	}
        protected boolean isRouteDisplayed() {
                return false;
        }
        protected void onDestroy() {
                if (mapManager != null) {
                        mapManager.destroy();
                        mapManager = null;
                }
                super.onDestroy();
        }
        @Override
        protected void onPause() {
                if (mapManager != null) {
                        mapManager.getLocationManager().removeUpdates(mLocationListener);
                        mLocationOverlay.disableMyLocation();
                        mLocationOverlay.disableCompass();
                        mapManager.stop();
                }
                super.onPause();
        }

        @Override
        protected void onResume() {
                if (mapManager != null) {
                        mapManager.getLocationManager().requestLocationUpdates(mLocationListener);
                        mLocationOverlay.enableMyLocation();
                        mLocationOverlay.enableCompass();
                        mapManager.start();
                }
                super.onResume();
        }
        public class MySearchListener implements MKSearchListener {
             
                @Override
                public void onGetAddrResult(MKAddrInfo result, int iError) {
                }
            
                @Override
                public void onGetDrivingRouteResult(MKDrivingRouteResult result, int iError) {
                }
   
                @Override
                public void onGetPoiResult(MKPoiResult result, int type, int iError) {
                        if (result == null) {
                                return;
                        }
                      
                        PoiOverlay poioverlay = new PoiOverlay(Maps_location.this, mMapView);
                     
                        poioverlay.setData(result.getAllPoi());
                     
                        mMapView.getOverlays().add(poioverlay);
                }
              
                @Override
                public void onGetTransitRouteResult(MKTransitRouteResult result, int iError) {
                }

             
                @Override
                public void onGetWalkingRouteResult(MKWalkingRouteResult result, int iError) {
                }

				@Override
				public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
					// TODO Auto-generated method stub					
				}
				@Override
				public void onGetSuggestionResult(MKSuggestionResult arg0,
						int arg1) {
					// TODO Auto-generated method stub					
				}
        }
}