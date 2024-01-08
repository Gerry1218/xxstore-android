package com.wanris.xxshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wanris.xxshop.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<String> mDataList;
    public MyAdapter(ArrayList<String> dataList) {
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        }
        // 获取当前位置的数据项
        String item = (String) getItem(position); // 在列表项视图中显示数据
        TextView textView = convertView.findViewById(R.id.title);
        textView.setText(item);
        return convertView;
    }
}
