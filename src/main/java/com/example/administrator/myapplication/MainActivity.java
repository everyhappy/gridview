package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private GridView gridView;

    private List<StringBean> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = (TableLayout) findViewById(R.id.tab_layout);
        data = new ArrayList<>();
        data.add(new StringBean("1111111111asdfasdfsdf", "22"));
        data.add(new StringBean("11", "22"));
        data.add(new StringBean("11adfasdfdsfasdfasdfsdaf", "22"));
        data.add(new StringBean("1163456345weased63sdfasdfasfd456", "22"));
        data.add(new StringBean("11", "22"));
        data.add(new StringBean("123423445sdafasdfasdfasdf", "22"));
        data.add(new StringBean("11", "22"));
        data.add(new StringBean("11", "22"));
        gridView = (GridView) findViewById(R.id.grid_view);
        MyAdapter myAdapter = new MyAdapter(this);
        gridView.setAdapter(myAdapter);
    }

    private void initData() {

    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;

        private MyAdapter(Context context) {
            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            //How many items are in the data set represented by this Adapter.
            //在此适配器中所代表的数据集中的条目数
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            // Get the data item associated with the specified position in the data set.
            //获取数据集中与指定索引对应的数据项
            return position;
        }

        @Override
        public long getItemId(int position) {
            //Get the row id associated with the specified position in the list.
            //获取在列表中与指定索引对应的行id
            return position;
        }

        //Get a View that displays the data at the specified position in the data set.
        //获取一个在数据集中指定索引的视图来显示数据
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            //如果缓存convertView为空，则需要创建View
            if (convertView == null) {
                holder = new ViewHolder();
                //根据自定义的Item布局加载布局
                convertView = mInflater.inflate(R.layout.grid_view_item, null);
                holder.title = (TextView) convertView.findViewById(R.id.text1);
                holder.info = (TextView) convertView.findViewById(R.id.text2);
                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                Rect text1Rect1 = new Rect();
                holder.title.getPaint().getTextBounds(data.get(position).getTxt1(),0,data.get(position).getTxt1().length(),text1Rect1);
                Log.d("huangkun","holder.info.getLineCount(); ="+holder.info.getLineCount());
                holder.info.getLineCount();

                Rect text1Rec2t = new Rect();
                holder.info.getPaint().getTextBounds(data.get(position).getTxt2(),0,data.get(position).getTxt2().length(),text1Rec2t);
                Log.d("huangkun","info heigh ="+text1Rec2t.height());

//                if ((position / 3)%2 == 0) {
//                    AbsListView.LayoutParams params = new AbsListView.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, dip2px(MainActivity.this,text1Rect1.height())+dip2px(MainActivity.this,text1Rec2t.height()));
//                    convertView.setLayoutParams(params);
//                } else {
//                    AbsListView.LayoutParams params = new AbsListView.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 200);
//                    convertView.setLayoutParams(params);
//                }

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.title.setText(data.get(position).getTxt1());
            holder.info.setText(data.get(position).getTxt2());
            return convertView;
        }

    }

    static class ViewHolder {
        public TextView title;
        public TextView info;
    }

    class StringBean {
        String txt1;
        String txt2;

        public StringBean(String txt1, String txt2) {
            this.txt1 = txt1;
            this.txt2 = txt2;
        }

        public String getTxt1() {
            return txt1;
        }

        public void setTxt1(String txt1) {
            this.txt1 = txt1;
        }

        public String getTxt2() {
            return txt2;
        }

        public void setTxt2(String txt2) {
            this.txt2 = txt2;
        }
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
