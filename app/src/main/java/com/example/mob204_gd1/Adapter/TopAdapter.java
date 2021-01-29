package com.example.mob204_gd1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mob204_gd1.DAO.ThongKeDAO;
import com.example.mob204_gd1.Model.TOP;
import com.example.mob204_gd1.R;

import java.util.List;

public class TopAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<TOP> topList;

    public TopAdapter(Context context, int layout, List<TOP> topList) {
        this.context = context;
        this.layout = layout;
        this.topList = topList;
    }

    @Override
    public int getCount() {
        return topList.size();
    }

    @Override
    public Object getItem(int position) {
        return topList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        TextView tv_sach, tv_soluong;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.tv_sach = convertView.findViewById(R.id.textView_Top10_tenSach);
            viewHolder.tv_soluong = convertView.findViewById(R.id.textView_top10_soLuong);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ThongKeDAO thongKeDAO = new ThongKeDAO(context);
        topList = thongKeDAO.getTop10();
        viewHolder.tv_sach.setText("Sách: " + topList.get(position).getTenSach());
        viewHolder.tv_soluong.setText("Số lần mượn: "+topList.get(position).getSoluong() + "");

        return convertView;
    }
}
