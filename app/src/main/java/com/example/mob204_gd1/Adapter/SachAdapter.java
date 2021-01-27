package com.example.mob204_gd1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mob204_gd1.DAO.LoaiSachDAO;
import com.example.mob204_gd1.Model.LoaiSach;
import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.R;

import java.util.List;

public class SachAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Sach> list;

    public SachAdapter(Context context, int layout, List<Sach> list) {
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
        TextView tv_maSach,tv_tenSach,tv_giaThue,tv_tenLoai;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_sach,null);

            viewHolder.tv_giaThue = convertView.findViewById(R.id.textView_giaThue);
            viewHolder.tv_tenLoai = convertView.findViewById(R.id.textView_tenLoai_FK);
            viewHolder.tv_maSach = convertView.findViewById(R.id.textView_maSach);
            viewHolder.tv_tenSach = convertView.findViewById(R.id.textView_tenSach);

            convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

        viewHolder.tv_giaThue.setText("Gia thue: " + list.get(position).getGiaThue() + "");
        String maLoai = String.valueOf(list.get(position).getMaLoai());
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
        LoaiSach loaiSach = loaiSachDAO.getId(maLoai);
        viewHolder.tv_tenLoai.setText(loaiSach.getTenLoai());
        viewHolder.tv_maSach.setText("Ma sach: " + list.get(position).getId() +"");
        viewHolder.tv_tenSach.setText(list.get(position).getTenSach());

        return convertView;
    }
}
