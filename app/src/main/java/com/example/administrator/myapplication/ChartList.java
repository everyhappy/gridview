package com.example.administrator.myapplication;

import android.content.Context;
import android.gesture.Gesture;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ChartList extends LinearLayout {
    Context context;


    private Paint markPain;
    //右边view的 宽度
    private float rightViewWidth = -1;
    //item的高度 只存在一样高的情况
    private float itemHeight = -1;
    //整个listView 的高度
    private float listHeight = -1;

    private int mX;
    private int mY;


    private int mPosition = -1;
    PopupWindow window;
    private TextView popTxt;
    private LinearLayout ll;
    private MarkViewList listView;
    List<String> data;
    private MyAdapter myAdapter;
    private GestureDetector gestureDetector;

    public ChartList(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ChartList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ChartList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {

        LayoutInflater.from(context).inflate(R.layout.chart_list_layout, this);

        View view = View.inflate(context, R.layout.powindow, null);
        popTxt = view.findViewById(R.id.pop_txt);
        window = new PopupWindow(view, 200, 300, true);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setTouchable(false);



        ll = (LinearLayout) findViewById(R.id.title);
        listView = (MarkViewList) findViewById(R.id.list_chart);
        gestureDetector = new GestureDetector(context, new Gesturelistener());
        markPain = new Paint();
        markPain.setColor(Color.parseColor("#FF0000FF"));
        data = new ArrayList<>();
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        data.add("22222222222222");
        data.add("22222222222222");
        data.add("22222222222222");
        data.add("22222222222222");
        data.add("22222222222222");
        data.add("22222222222222");
        data.add("22222222222222");
        data.add("22222222222222");
        data.add("22222222222222");
        data.add("22222222222222");
        myAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("huangkun", "click position =" + position);
                mPosition = position;
                int offsetyinde = data.size() - mPosition;
//            showPopWindow(listView,(int)x,(int)y);
                popTxt.setText(mPosition+"");
                Log.d("huangkun", "-----------offsetyinde*itemHeight-----------------=" +offsetyinde*itemHeight);
                window.dismiss();
                window.showAsDropDown(listView,(int)mX,-(int) (offsetyinde*itemHeight));
                view.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return  gestureDetector.onTouchEvent(event);
                    }
                });
            }
        });
        listView.post(new Runnable() {
            @Override
            public void run() {
                if (listHeight == -1) {
                    listHeight = listView.getHeight();
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    //    public void showPopWindow(View anchor,int offsetX,int offsetY) {
//
//        window.showAsDropDown(anchor,offsetX,offsetY);
////        window.showAtLocation(anchor,Gravity.TOP | Gravity.LEFT, 0, 0);
//    }
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
                viewHolder.text0 = convertView.findViewById(R.id.text);
                viewHolder.text = convertView.findViewById(R.id.text2);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.text.post(new Runnable() {
                @Override
                public void run() {
                    if (rightViewWidth == -1) {
                        rightViewWidth = viewHolder.text.getWidth();
                        Log.d("huangkun", " viewHolder.text0 height=" + viewHolder.text.getWidth());
                    }
                    if (itemHeight == -1) {
                        itemHeight = viewHolder.text.getHeight();
                        Log.d("huangkun", " viewHolder.text0 height=" + viewHolder.text.getHeight());
                    }

                }
            });
            viewHolder.text.setText(data.get(position));

//            convertView.setOnTouchListener(new OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    return gestureDetector.onTouchEvent(event);
//                }
//            });
            return convertView;
        }

        class ViewHolder {
            TextView text0;
            TextView text;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.d("huangkun", "getHeight and getWidth =" + getHeight() + " " + getWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMarkView(canvas);
    }

    private void drawMarkView(Canvas c) {
//        c.drawRoundRect();
    }

    class Gesturelistener implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            float x = e.getX();
            float y = e.getY();
//            if(mPosition == -1)
//                return;
            mX =(int) e.getX();
//            showPopWindow(listView,(int)x,(int) (offsetyinde*itemHeight));
//            window.showAtLocation(listView, Gravity.TOP | Gravity.START, 0, 0);
            Log.d("huangkun", "----------------x and y ------------------=" + x + " " + y);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }
}
