package cn.edu.bzu.main;


import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Welcome extends Activity implements AnimationListener {
    /** Called when the activity is first created. */
    private ImageView  imageView = null;   
    private Animation alphaAnimation = null;   
     String SDPATH=null; 
    @Override  
    protected void onCreate(Bundle savedInstanceState) {   
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
  
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.welcome);      
        SDPATH=Environment.getExternalStorageDirectory()+"/";
        if(this.checkFileExists("goodsimage")==false){
        	 File dir=new File(SDPATH+"goodsimage");
        	   dir.mkdir();
        }       
        imageView = (ImageView)findViewById(R.id.welcome_image_view);   
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.welcome_alpha);   
        alphaAnimation.setFillEnabled(true);
        alphaAnimation.setFillAfter(true);  
        imageView.setAnimation(alphaAnimation);   
        alphaAnimation.setAnimationListener(this); 
    } 
    
    /**
     * 判断文件是否已经存在;
     */
    public boolean checkFileExists(String filepath) {
          File file=new File(SDPATH+filepath);
          return file.exists();
   }

    
    @Override  
    public void onAnimationStart(Animation animation) {   
           
    }   
       
    @Override  
    public void onAnimationEnd(Animation animation) {   
        Intent intent = new Intent(this, Index.class);   
        startActivity(intent);   
        this.finish();   
    }   
       
    @Override  
    public void onAnimationRepeat(Animation animation) {   
           
    }   
       
    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {   
        if(keyCode==KeyEvent.KEYCODE_BACK) {   
            return false;   
        }   
        return false;   
    }     
}