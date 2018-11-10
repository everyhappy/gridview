package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

public class MarkViewList extends ListView {

    public MarkViewList(Context context) {
        super(context);
    }

    public MarkViewList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarkViewList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.d("huangkun","MarkViewList onDraw");
    }

}
