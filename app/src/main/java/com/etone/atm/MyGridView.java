package com.etone.atm;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridView;

/**
 * Created by Maggie on 2016/5/9.
 */
public class MyGridView extends GridView {
    public final static String TAG = "MyGridView";
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public Object getSelectedItem() {
        Log.d(TAG, "getSelectedItem");
        return super.getSelectedItem();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, widthMeasureSpec + "/"+ heightMeasureSpec );
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        Log.d(TAG, changed + "/" + l + "/" + t + "/" + r + "/" + b);
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public Object getItemAtPosition(int position) {
        Log.d(TAG, "getItemAtPosition" + position);
        return super.getItemAtPosition(position);
    }

}
