package com.example.mob204_gd1.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.mob204_gd1.Adapter.PhieuMuonAdapter;
import com.example.mob204_gd1.Adapter.ThuThuAdapter;
import com.example.mob204_gd1.Model.PhieuMuon;
import com.example.mob204_gd1.Model.ThuThu;
import com.example.mob204_gd1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Fragment_ThemNguoiDung extends Fragment {
    ListView lv;
    ThuThuAdapter adapter;
    List<ThuThu> list;
    FloatingActionButton fab;

    public Fragment_ThemNguoiDung() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__them_nguoi_dung, container, false);

        // anh xa floating action button
        fab = view.findViewById(R.id.fab_themThuThu);
        // anh xa listview
        lv = view.findViewById(R.id.lv_thuthu);

        // khoi tao list va do du lieu vao
        list = new ArrayList<>();
        list.add(new ThuThu("TT01","Iron man","123"));
        list.add(new ThuThu("TT02","Thor","123"));
        list.add(new ThuThu("TT03","Spider man","123"));
        list.add(new ThuThu("TT04","Captain America","123"));

        // Khoi tao adapter
        adapter = new ThuThuAdapter(getContext(),R.layout.item_thuthu,list);

        // set adapter cho listview
        lv.setAdapter(adapter);

        // set click cho fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_them_thu_thu);

                Button button_luu = dialog.findViewById(R.id.button_dialogThemTT_luu);
                button_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                Button button_huy = dialog.findViewById(R.id.button_dialogThemTT_huy);
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