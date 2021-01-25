package com.example.mob204_gd1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mob204_gd1.Model.LoaiSach;
import com.example.mob204_gd1.R;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> arrayList;
    TextView tv_maLoaiSach, tv_tenLoaiSach;

    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> arrayList) {
        super(context, 0, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_loaisach, null);
        }

        LoaiSach loaiSach = arrayList.get(position);

        if (loaiSach != null) {
            tv_maLoaiSach = convertView.findViewById(R.id.textView_idLoaiSach);
            tv_tenLoaiSach = convertView.findViewById(R.id.textView_tenLoaiSach);

            tv_tenLoaiSach.setText(loaiSach.getTenLoai());
            tv_maLoaiSach.setText(loaiSach.getId() + "");
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_loaisach, null);
        }

        LoaiSach loaiSach = arrayList.get(position);

        if (loaiSach != null) {
            tv_maLoaiSach = convertView.findViewById(R.id.textView_idLoaiSach);
            tv_tenLoaiSach = convertView.findViewById(R.id.textView_tenLoaiSach);

            tv_tenLoaiSach.setText(loaiSach.getTenLoai());
            tv_maLoaiSach.setText(loaiSach.getId() + "");
        }

        return convertView;
    }
}
