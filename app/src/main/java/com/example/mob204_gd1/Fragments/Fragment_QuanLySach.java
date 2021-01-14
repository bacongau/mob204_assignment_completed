package com.example.mob204_gd1.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mob204_gd1.Adapter.LoaiSachAdapter;
import com.example.mob204_gd1.Adapter.SachAdapter;
import com.example.mob204_gd1.Model.LoaiSach;
import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Fragment_QuanLySach extends Fragment {
    ListView lv;
    SachAdapter adapter;
    List<Sach> list;
    FloatingActionButton fab;

    public Fragment_QuanLySach() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__quan_ly_sach, container, false);

        // anh xa floating action button
        fab = view.findViewById(R.id.fab_themSach);
        // anh xa listview
        lv = view.findViewById(R.id.lv_quanlysach);

        // khoi tao list va do du lieu vao
        list = new ArrayList<>();
        list.add(new Sach(1,"De men phieu luu ky",1500, 1));
        list.add(new Sach(2,"Bo gia",1000, 1));
        list.add(new Sach(3,"Su im lang cua bay cuu",2000, 1));
        list.add(new Sach(4,"Java 1",4000, 2));
        list.add(new Sach(5,"Java 2",5000, 2));
        list.add(new Sach(6,"Marketing",3000, 3));
        list.add(new Sach(7,"Kinh te hoc",6000, 3));
        list.add(new Sach(8,"1000 cau hoi vi sao",2500, 4));
        list.add(new Sach(9,"Ban khong thong minh lam dau",3500, 4));
        list.add(new Sach(10,"Khong co cau hoi nao ngo ngan",4000, 4));

        // Khoi tao adapter
        adapter = new SachAdapter(getContext(),R.layout.item_sach,list);

        // set adapter cho listview
        lv.setAdapter(adapter);

        // set click cho fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Them Sach moi", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}