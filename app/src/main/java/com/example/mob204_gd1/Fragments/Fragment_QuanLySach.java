package com.example.mob204_gd1.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


        // Khoi tao adapter
        adapter = new SachAdapter(getContext(),R.layout.item_sach,list);

        // set adapter cho listview
        lv.setAdapter(adapter);

        // set click cho fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_them_sach);

                Button button_luu = dialog.findViewById(R.id.button_dialogThemSach_luu);
                button_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                Button button_huy = dialog.findViewById(R.id.button_dialogThemSach_huy);
                button_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });

        return view;
    }
}