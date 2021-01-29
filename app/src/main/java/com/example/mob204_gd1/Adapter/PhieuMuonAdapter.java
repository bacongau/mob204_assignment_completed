package com.example.mob204_gd1.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mob204_gd1.DAO.SachDAO;
import com.example.mob204_gd1.DAO.ThanhVienDAO;
import com.example.mob204_gd1.Model.PhieuMuon;
import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.Model.ThanhVien;
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

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_phieumuon,null);

            viewHolder.tv_maPhieuMuon = convertView.findViewById(R.id.textView_maPhieuMuon);
            viewHolder.tv_maSach = convertView.findViewById(R.id.textView_tenSach_FK);
            viewHolder.tv_maThuThu = convertView.findViewById(R.id.textView_maThuThu_FK);
            viewHolder.tv_maTv = convertView.findViewById(R.id.textView_TenTv_Fk);
            viewHolder.tv_ngay = convertView.findViewById(R.id.textView_ngay);
            viewHolder.tv_tienThue = convertView.findViewById(R.id.textView_tienThue);
            viewHolder.tv_traSach = convertView.findViewById(R.id.textView_traSach);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_maPhieuMuon.setText("ID: " + list.get(position).getId());

        String maSach = String.valueOf(list.get(position).getIdSach());
        SachDAO sachDAO = new SachDAO(context);
        Sach sach = sachDAO.getId(maSach);
        viewHolder.tv_maSach.setText(sach.getTenSach());

        viewHolder.tv_maThuThu.setText("ID thủ thư: " + list.get(position).getIdThuThu());

        String maTv = String.valueOf(list.get(position).getIdThanhVien());
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
        ThanhVien thanhVien = thanhVienDAO.getId(maTv);
        viewHolder.tv_maTv.setText(thanhVien.getHoTen());
        viewHolder.tv_ngay.setText("Ngay tra: " + list.get(position).getNgay());
        viewHolder.tv_tienThue.setText("Tien thue: " + list.get(position).getTienThue());

        String a = "";
        if (list.get(position).getTraSach() == 0){
            viewHolder.tv_traSach.setTextColor(0xFFfdc936);
            a = "Đã trả";
        }else{
            a = "Chưa trả";
            viewHolder.tv_traSach.setTextColor(0xFFf22e3d);
        }
        viewHolder.tv_traSach.setText("Trạng thái: " + a);

        return convertView;
    }
}
