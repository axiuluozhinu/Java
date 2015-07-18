package cn.edu.bzu.map;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import cn.edu.bzu.bean.Stores;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.Overlay;
import com.baidu.mapapi.Projection;

public class GetOverlay extends Overlay{
    GeoPoint geo;
    Stores stores=new Stores();  
    Canvas canvas; MapView  mMapView; boolean arg2;

    public void draw(Canvas canvas, MapView  mMapView, boolean arg2) {
            super.draw(canvas,  mMapView, arg2);
            if(geo==null){     return;  }
            Log.d(toString(), arg2+"-------draw--");
            Projection projection =  mMapView.getProjection();      
            Point point = projection.toPixels(geo, null);         
            Paint paintText = new Paint();
            paintText.setColor(Color.RED);
            paintText.setTextSize(35);
            canvas.drawText("¡ñ", point.x-9, point.y+13, paintText); 
            }
    public boolean onTap(GeoPoint geo, MapView arg1) {
    draw(canvas, arg1, arg2);
     Log.d(this.toString(), geo.getLongitudeE6()/1E6+"----------"+geo.getLatitudeE6()/1E6);
Stores.setAlongitude(geo.getLongitudeE6()/1E6);
Stores.setAlatitude(geo.getLatitudeE6()/1E6);
            return super.onTap(geo, arg1);

    }
   

}
