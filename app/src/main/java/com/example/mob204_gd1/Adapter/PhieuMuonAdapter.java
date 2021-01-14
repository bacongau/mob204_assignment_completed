package com.example.mob204_gd1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mob204_gd1.Model.PhieuMuon;
import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.R;

import java.util.List;

public class PhieuMuonAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<PhieuMuon> list;

    public PhieuMuonAdapter(Context context, int layout, List<PhieuMuon> list) {
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
        TextView tv_maPhieuMuon,tv_maThuThu,tv_maTv,tv_maSach,tv_tienThue,tv_traSach,tv_ngay;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_phieumuon,null);

            viewHolder.tv_maPhieuMuon = convertView.findViewById(R.id.textView_maPhieuMuon);
            viewHolder.tv_maSach = convertView.findViewById(R.id.textView_maSach_FK);
            viewHolder.tv_maThuThu = convertView.findViewById(R.id.textView_maThuThu_FK);
            viewHolder.tv_maTv = convertView.findViewById(R.id.textView_maTv_Fk);
            viewHolder.tv_ngay = convertView.findViewById(R.id.textView_ngay);
            viewHolder.tv_tienThue = convertView.findViewById(R.id.textView_tienThue);
            viewHolder.tv_traSach = convertView.findViewById(R.id.textView_traSach);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_maPhieuMuon.setText("ID: " + list.get(position).getId());
        viewHolder.tv_maSach.setText("ID sach: " + list.get(position).getIdSach());
        viewHolder.tv_maThuThu.setText("ID thu thu: " + list.get(position).getIdThuThu());
        viewHolder.tv_maTv.setText("ID thanh vien: " + list.get(position).getIdThanhVien());
        viewHolder.tv_ngay.setText("Ngay tra: " + list.get(position).getNgay());
        viewHolder.tv_tienThue.setText("Tien thue: " + list.get(position).getTienThue());

        String a = "";
        if (list.get(position).getTraSach() == 0){
            a = "Da tra";
        }else{
            a = "Chua tra";
        }
        viewHolder.tv_traSach.setText("Trang thai: " + a);

        return convertView;
    }
}
