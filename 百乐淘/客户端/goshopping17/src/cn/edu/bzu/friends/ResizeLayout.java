package cn.edu.bzu.friends;
/**
 * ResizeLayout
 * @author ÷”º“–À
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ResizeLayout extends LinearLayout {

	public ResizeLayout(Context context) {
		super(context);
	}

	public ResizeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
