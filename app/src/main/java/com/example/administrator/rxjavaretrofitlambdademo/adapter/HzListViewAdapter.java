package com.example.administrator.rxjavaretrofitlambdademo.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.rxjavaretrofitlambdademo.R;
import com.example.administrator.rxjavaretrofitlambdademo.entity.HzDataClass;
import com.example.administrator.rxjavaretrofitlambdademo.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;


public class HzListViewAdapter extends BaseAdapter {
    private Context mContext;
    List<HzDataClass.DataBean> mDataArray = new ArrayList<HzDataClass.DataBean>();

    public HzListViewAdapter(Context mContext, List<HzDataClass.DataBean> array) {
        this.mContext = mContext;
        this.mDataArray = array;
    }

    @Override
    public int getCount() {
        if (mDataArray == null) {
            return 0;
        } else {
            return mDataArray.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (mDataArray == null) {
            return null;
        } else {
            return mDataArray.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.hz_lv_item, parent, false);
            holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tv_businessName = (TextView) convertView.findViewById(R.id.tv_businessName);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (!mDataArray.isEmpty() && mDataArray.size() != 0) {
            int businessType = mDataArray.get(position).getBusinessType();
            switch (businessType) {
                case 1:
                    holder.tv_businessName.setBackgroundResource(R.drawable.bg_corner_3dp_28acff);
                    break;
                case 2:
                    holder.tv_businessName.setBackgroundResource(R.drawable.bg_corner_3dp_e55995);
                    break;
            }
            GlideUtils.loadImageViewLodingCrop(mContext, mDataArray.get(position).getImage(), holder.iv_icon, R.drawable.defaultimg, R.drawable.defaultimg);
            holder.tv_title.setText(mDataArray.get(position).getTitle());
            holder.tv_address.setText(mDataArray.get(position).getAddress());
            holder.tv_businessName.setText(mDataArray.get(position).getBusinessName());
        }

        return convertView;
    }

    private class ViewHolder {
        private ImageView iv_icon;
        private TextView tv_businessName;
        private TextView tv_title;
        private TextView tv_address;
    }
}
