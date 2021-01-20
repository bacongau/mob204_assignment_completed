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

import com.example.mob204_gd1.Adapter.PhieuMuonAdapter;
import com.example.mob204_gd1.Adapter.SachAdapter;
import com.example.mob204_gd1.Model.PhieuMuon;
import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Fragment_QuanLyPhieuMuon extends Fragment {
    ListView lv;
    PhieuMuonAdapter adapter;
    List<PhieuMuon> list;
    FloatingActionButton fab;

    public Fragment_QuanLyPhieuMuon() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__quan_ly_phieu_muon, container, false);

        // anh xa floating action button
        fab = view.findViewById(R.id.fab_themPhieuMuon);
        // anh xa listview
        lv = view.findViewById(R.id.lv_quanlyphieumuon);

        // khoi tao list va do du lieu vao
        list = new ArrayList<>();
        list.add(new PhieuMuon(1,"TT01",1,2,3000,"02/02/2000",0));
        list.add(new PhieuMuon(2,"TT01",3,2,4000,"02/02/2000",1));
        list.add(new PhieuMuon(3,"TT02",4,3,10000,"02/02/2000",0));
        list.add(new PhieuMuon(4,"TT03",7,5,12000,"02/02/2000",1));
        list.add(new PhieuMuon(5,"TT03",9,6,12000,"02/02/2000",0));

        // Khoi tao adapter
        adapter = new PhieuMuonAdapter(getContext(),R.layout.item_phieumuon,list);

        // set adapter cho listview
        lv.setAdapter(adapter);

        // set click cho fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_them_phieu_muon);

                Button button_luu = dialog.findViewById(R.id.button_dialogThemPhieuMuon_luu);
                button_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                Button button_huy = dialog.findViewById(R.id.button_dialogThemPhieuMuon_huy);
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