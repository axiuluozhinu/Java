package cn.edu.bzu.main;

import android.location.Location;
import android.util.Log;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;

public class Searchlocationgoods extends MapActivity{
    private MapView mMapView;
    private MapController mapController;
	public void onLocationChanged(Location location) {
        if (location != null) {
                GeoPoint geoPoint = new GeoPoint((int)(location.getLatitude() * 1e6),
                                (int) (location.getLongitude() * 1e6));
                mMapView.getController().animateTo(geoPoint);
                mapController = mMapView.getController();
                mapController.setCenter(geoPoint);
                mapController.setZoom(17);                                 
        }
}
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public void onGetAddrResult(MKAddrInfo result, int error) { 
    	Log.i("Merchants", error+"");
    	if (error != 0) {
    	return;
    	}
    	String arrd = result.strAddr;

    	Log.i("Merchants", "地址: " + arrd);	
    	String name = result.addressComponents.city;
    	Log.i("Merchants", "城市: " + name);	
    	String district = result.addressComponents.district;
    	Log.i("Merchants", "区县: " + district);	
    		
    		}
             

    public void onGetDrivingRouteResult(MKDrivingRouteResult result, int iError) { 
    } 
  
  
    public void onGetPoiResult(MKPoiResult result, int type, int iError) { 
    } 
 
    
    public void onGetTransitRouteResult(MKTransitRouteResult result, int iError) { 
    } 


    public void onGetWalkingRouteResult(MKWalkingRouteResult result, int iError) { 
    }

	public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
		// TODO Auto-generated method stub
		
	} 

}
