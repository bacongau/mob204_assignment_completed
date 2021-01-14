package com.example.mob204_gd1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.Model.ThanhVien;
import com.example.mob204_gd1.R;

import java.util.List;

public class ThanhVienAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ThanhVien> list;

    public ThanhVienAdapter(Context context, int layout, List<ThanhVien> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView tv_maTv,tv_hoTenTv,tv_namSinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_thanhvien,null);

            viewHolder.tv_hoTenTv = convertView.findViewById(R.id.textView_hoTen);
            viewHolder.tv_maTv = convertView.findViewById(R.id.textView_maTv);
            viewHolder.tv_namSinh = convertView.findViewById(R.id.textView_namSinh);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_hoTenTv.setText(list.get(position).getHoTen());
        viewHolder.tv_maTv.setText("" + list.get(position).getMaThanhVien());
        viewHolder.tv_namSinh.setText("" + list.get(position).getNamSinh());

        return convertView;
    }
}
