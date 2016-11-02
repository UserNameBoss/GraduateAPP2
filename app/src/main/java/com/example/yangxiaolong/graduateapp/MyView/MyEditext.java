package com.example.yangxiaolong.graduateapp.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/11/2.
 */
public class MyEditext extends EditText {
    public MyEditext(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //void android.graphics.Canvas.drawRoundRect(RectF rect, float rx, float ry, Paint paint)
        //float rx : x轴的圆角半径
        //float ry : y轴的圆角半径


        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.GRAY);
        canvas.drawRoundRect(new RectF(2+this.getScrollX(), 2+this.getScrollY(), this.getWidth()-3+this.getScrollX(), this.getHeight()+this.getScrollY()-1),10,10,paint);
    }
}
