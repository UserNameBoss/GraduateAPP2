package com.example.yangxiaolong.graduateapp.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by yangxiaolong on 2016/11/7.
 */
public class MyLinearLayout_rectangle extends LinearLayout {
    Paint paint=new Paint();
    public MyLinearLayout_rectangle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.WHITE);
        System.out.println("==============Width="+getWidth()+"================Height="+getHeight());
        canvas.drawRoundRect(new RectF(2+this.getScrollX(), 2+this.getScrollY(), this.getWidth()-3+this.getScrollX(), this.getHeight()+this.getScrollY()-1),10,10,paint);

    }
}
