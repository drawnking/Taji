package com.zhangqing.taji.activities;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhangqing.taji.R;
import com.zhangqing.taji.view.FlexiListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/7.
 */
public class FragmentCircle extends Fragment {
    private ViewPager viewPager;
    private TextView indicator1;
    private TextView indicator2;


    private int currentPager = -1;

    public FragmentCircle() {
        super();
        Log.e("FragmentCircle", "###构造");
    }

    class CirclePagerAdapter extends PagerAdapter {
        ListView listView3;
        ListView listView2;
        List<View> viewList;
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        ListView listView1;

        public CirclePagerAdapter(Context context) {
            Log.e("CirclePagerAdapter", "###构造");

            viewList = new ArrayList<View>();
            listView1 = new FlexiListView(context);
            listView1.setDividerHeight(0);
            listView1.setAdapter(new CircleListViewAdapter(8));
            viewList.add(listView1);

            View v = LayoutInflater.from(getActivity()).inflate(R.layout.view_circle_second, null, false);

            listView2 = (ListView) v.findViewById(R.id.circle_second_listview1);
            listView3 = (ListView) v.findViewById(R.id.circle_second_listview2);

            listView2.setAdapter(new CircleListViewAdapter(4));
            listView3.setAdapter(new CircleListViewAdapter(3));
            viewList.add(v);


            // listView1.setAdapter();
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

    }

    private void updateIndicator(int newPager) {
        if (newPager == currentPager) return;
        currentPager = newPager;
        switch (currentPager) {
            case 0:
                indicator1.setBackgroundResource(R.drawable.home_circle_category_bar_indicator);
                indicator1.setTextColor(Color.parseColor("#443854"));

                indicator2.setBackgroundResource(0);
                indicator2.setTextColor(Color.parseColor("#99999B"));

                break;
            case 1:
                indicator2.setBackgroundResource(R.drawable.home_circle_category_bar_indicator);
                indicator2.setTextColor(Color.parseColor("#443854"));

                indicator1.setBackgroundResource(0);
                indicator1.setTextColor(Color.parseColor("#99999B"));
                break;
        }
        indicator1.setPadding(0, 25, 0, 25);
        indicator2.setPadding(0, 25, 0, 25);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("FragmentCircle", "###onActivityCreated");
        viewPager.setAdapter(new CirclePagerAdapter(getActivity()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        updateIndicator(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("FragmentCircle", "###onCreateView");
        View v = inflater.inflate(R.layout.fragment_circle, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.circle_viewpager);
        indicator1 = (TextView) v.findViewById(R.id.circle_indicator1_tv);
        indicator2 = (TextView) v.findViewById(R.id.circle_indicator2_tv);


        indicator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPager == 0) return;
                updateIndicator(0);
                viewPager.setCurrentItem(0, true);
            }
        });
        indicator2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPager == 1) return;
                updateIndicator(1);
                viewPager.setCurrentItem(1, true);
            }
        });


        return v;
    }


    class CircleListViewAdapter extends BaseAdapter {
        List<Map<String, Object>> itemMapList;

        public CircleListViewAdapter(int initCount) {
            Log.e("CircleListViewAdapter", "###构造");
            itemMapList = new ArrayList<Map<String, Object>>();
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.pic_loading_bg);
            for (int i = 0; i < initCount; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("icon", bm);
                map.put("title", "标题" + i);
                map.put("count_online", 10 + 12 * i);
                map.put("count_all", 123 + 23 * i);
                itemMapList.add(map);
            }


        }

        @Override
        public int getCount() {

           // Log.e("CircleListViewAdapter", "###getCount");
            return itemMapList.size();
        }

        @Override
        public Object getItem(int position) {
            Log.e("CircleListViewAdapter", "###getItem");

            return itemMapList.get(position);
        }

        @Override
        public long getItemId(int position) {
            Log.e("CircleListViewAdapter", "###getItemId");
            return 0;
        }

        class ViewHolder {
            ImageView imgViewIcon;
            TextView textViewTitle;
            TextView textViewCountOnline;
            TextView textViewCountAll;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Log.e("CircleListViewAdapter", "###getView" + position + " " + convertView);
            ViewHolder viewHolder;

            if (convertView == null) {
              //  Log.e("CircleListViewAdapter", "**nullconvertView " + position + " " + parent.toString());
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.view_circle_first_listview_item, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textViewTitle = (TextView) convertView.findViewById(R.id.circle_first_title);
                viewHolder.textViewCountOnline = (TextView) convertView.findViewById(R.id.circle_first_count_online_tv);
                viewHolder.textViewCountAll = (TextView) convertView.findViewById(R.id.circle_first_count_all_tv);
                viewHolder.imgViewIcon = (ImageView) convertView.findViewById(R.id.circle_first_icon_iv);

                convertView.setTag(viewHolder);

            } else {

                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.imgViewIcon.setImageBitmap((Bitmap) itemMapList.get(position).get("icon"));

            viewHolder.textViewTitle.setText((String) itemMapList.get(position).get("title"));
            viewHolder.textViewCountOnline.setText("" + itemMapList.get(position).get("count_online"));
            viewHolder.textViewCountAll.setText("在线 /" + itemMapList.get(position).get("count_all") + "人");
            return convertView;

        }
    }
}
