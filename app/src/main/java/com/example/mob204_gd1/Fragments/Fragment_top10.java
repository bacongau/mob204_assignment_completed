package com.example.mob204_gd1.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mob204_gd1.Adapter.TopAdapter;
import com.example.mob204_gd1.DAO.ThongKeDAO;
import com.example.mob204_gd1.Model.TOP;
import com.example.mob204_gd1.R;

import java.util.ArrayList;
import java.util.List;


public class Fragment_top10 extends Fragment {
    List<TOP> topList;
    TopAdapter topAdapter;
    ListView listView;
    ThongKeDAO thongKeDAO;

    public Fragment_top10() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top10, container, false);

        // anh xa
        listView = view.findViewById(R.id.lv_top10Sach);

        // khoi tao
        topList = new ArrayList<>();
        thongKeDAO = new ThongKeDAO(getContext());
        topList = thongKeDAO.getTop10();
        topAdapter = new TopAdapter(getContext(),R.layout.item_top,topList);

        // set adapter
        listView.setAdapter(topAdapter);

        return view;
    }
}