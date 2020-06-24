package com.common.project.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.common.project.R;

public class UndigraphView extends View {
    private static final String TAG = "UndigraphView";
    private Paint linePaint;
    private Paint bitmapPaint;
    public UndigraphView(Context context) {
        this(context, null);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UndigraphView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public UndigraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public UndigraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        linePaint=new Paint();
        linePaint.setColor(getResources().getColor(R.color.colorAccent));
        linePaint.setStrokeWidth(2);

        bitmapPaint=new Paint();
        bitmapPaint.setStrokeWidth(3);
        bitmapPaint.setColor(getResources().getColor(android.R.color.holo_red_dark));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(20,20,100,100,linePaint);

        Bitmap iconBitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        canvas.drawBitmap(iconBitmap,100,100,bitmapPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            Log.d(TAG, "onMeasure: widthMode!=MeasureSpec.EXACTLY");
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            Log.d(TAG, "onMeasure: heightMode!=MeasureSpec.EXACTLY");
        }
        Log.d(TAG, "onMeasure: width = "+width+"  height = "+height);
        setMeasuredDimension(width, height);
    }
}
