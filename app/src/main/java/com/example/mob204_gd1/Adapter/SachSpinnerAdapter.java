package com.example.mob204_gd1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mob204_gd1.Model.LoaiSach;
import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.R;

import java.util.ArrayList;
import java.util.List;

public class SachSpinnerAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Sach> sachList;
    List<LoaiSach> loaiSachList;

    public SachSpinnerAdapter(Context context, int layout, List<Sach> sachList, List<LoaiSach> loaiSachList) {
        this.context = context;
        this.layout = layout;
        this.sachList = sachList;
        this.loaiSachList = loaiSachList;
    }

    @Override
    public int getCount() {
        return sachList.size();
    }

    @Override
    public Object getItem(int position) {
        return sachList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView tv_idSach,tv_tenSach,tv_tenLoaiSach;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);

            viewHolder.tv_idSach = convertView.findViewById(R.id.textView_spinner_maSach);
            viewHolder.tv_tenSach = convertView.findViewById(R.id.textView_spinner_tenSach);
            viewHolder.tv_tenLoaiSach = convertView.findViewById(R.id.textView_spinner_tenLoaiSach);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_idSach.setText("" + sachList.get(position).getId());
        viewHolder.tv_tenSach.setText(sachList.get(position).getTenSach());
        int a = sachList.get(position).getMaLoai();
        int b = a-1;
        viewHolder.tv_tenLoaiSach.setText(loaiSachList.get(b).getTenLoai());

        return convertView;
    }
}
