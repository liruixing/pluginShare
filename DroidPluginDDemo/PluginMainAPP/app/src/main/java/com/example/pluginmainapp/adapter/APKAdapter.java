package com.example.pluginmainapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pluginmainapp.R;
import com.example.pluginmainapp.bean.ApkItem;

import java.util.List;

/**
 * create Time ： 2019-10-17.
 * Author:lrx
 * dec:
 */
public class APKAdapter extends BaseAdapter {
    private Context ctx;
    private List<ApkItem> dataList;
    private LayoutInflater mLayoutInflater;
    private View.OnClickListener mOnClickListener;

    public APKAdapter(Context ctx, List<ApkItem> dataList, View.OnClickListener clickListener) {
        this.ctx = ctx;
        this.dataList = dataList;
        this.mLayoutInflater = LayoutInflater.from(ctx);
        this.mOnClickListener = clickListener;
    }

    public void setDataList(List<ApkItem> dataList) {
        this.dataList = dataList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList==null?0:dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_apk_list,null);

            holder.imLogo = convertView.findViewById(R.id.im_logo);
            holder.tvName = convertView.findViewById(R.id.tv_name);
            holder.btnInstall = convertView.findViewById(R.id.btn_install);
            holder.btnUninstall = convertView.findViewById(R.id.btn_uninstall);
            holder.btnOpen = convertView.findViewById(R.id.btn_open);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ApkItem apkItem = dataList.get(position);

        holder.imLogo.setImageDrawable(apkItem.icon);
        holder.tvName.setText(apkItem.title);
        holder.btnInstall.setTag(apkItem);
        holder.btnOpen.setTag(apkItem);
        holder.btnUninstall.setTag(apkItem);
        holder.btnInstall.setOnClickListener(mOnClickListener);
        holder.btnUninstall.setOnClickListener(mOnClickListener);
        holder.btnOpen.setOnClickListener(mOnClickListener);


        return convertView;
    }

    final class ViewHolder{
        ImageView imLogo;
        TextView tvName;
        Button btnInstall;
        Button btnUninstall;
        Button btnOpen;
    }

}
