package com.example.mob204_gd1.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mob204_gd1.Adapter.SachAdapter;
import com.example.mob204_gd1.Adapter.ThanhVienAdapter;
import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.Model.ThanhVien;
import com.example.mob204_gd1.R;

import java.util.ArrayList;
import java.util.List;


public class Fragment_QuanLyThanhVien extends Fragment {
    ListView lv;
    ThanhVienAdapter adapter;
    List<ThanhVien> list;


    public Fragment_QuanLyThanhVien() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__quan_ly_thanh_vien, container, false);

        // anh xa listview
        lv = view.findViewById(R.id.lv_quanlythanhvien);

        // khoi tao list va do du lieu vao
        list = new ArrayList<>();
        list.add(new ThanhVien(1,"Ga", 2000));
        list.add(new ThanhVien(2,"Cho", 2000));
        list.add(new ThanhVien(3,"Meo", 2000));
        list.add(new ThanhVien(4,"Voi", 2000));
        list.add(new ThanhVien(5,"Ngua", 2000));
        list.add(new ThanhVien(6,"Khi", 2000));
        list.add(new ThanhVien(7,"Ong", 2000));
        list.add(new ThanhVien(8,"Trau", 2000));
        list.add(new ThanhVien(9,"Bo", 2000));
        list.add(new ThanhVien(10,"Cu meo", 2000));

        // Khoi tao adapter
        adapter = new ThanhVienAdapter(getContext(),R.layout.item_thanhvien,list);

        // set adapter cho listview
        lv.setAdapter(adapter);

        return view;
    }
}