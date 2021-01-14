package com.example.mob204_gd1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.Model.ThuThu;
import com.example.mob204_gd1.R;

import java.util.List;

public class ThuThuAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ThuThu> list;

    public ThuThuAdapter(Context context, int layout, List<ThuThu> list) {
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
        TextView tv_maThuThu,tv_hoTenThuThu,tv_matKhau;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_thuthu,null);

            viewHolder.tv_hoTenThuThu = convertView.findViewById(R.id.textView_hoTenThuThu);
            viewHolder.tv_maThuThu = convertView.findViewById(R.id.textView_idThuThu);
            viewHolder.tv_matKhau = convertView.findViewById(R.id.textView_matKhau);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_hoTenThuThu.setText(list.get(position).getHoTen());
        viewHolder.tv_maThuThu.setText(list.get(position).getId());
        viewHolder.tv_matKhau.setText(list.get(position).getMatKhau());

        return convertView;
    }
}
