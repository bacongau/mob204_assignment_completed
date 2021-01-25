package com.example.mob204_gd1.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mob204_gd1.Adapter.LoaiSachAdapter;
import com.example.mob204_gd1.Adapter.ThanhVienAdapter;
import com.example.mob204_gd1.DAO.LoaiSachDAO;
import com.example.mob204_gd1.DAO.ThanhVienDAO;
import com.example.mob204_gd1.Model.LoaiSach;
import com.example.mob204_gd1.Model.ThanhVien;
import com.example.mob204_gd1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class Fragment_QuanLyLoaiSach extends Fragment {
    ListView lv;
    LoaiSachAdapter adapter;
    List<LoaiSach> list;
    FloatingActionButton fab;
    LoaiSachDAO loaiSachDAO;
    EditText edt_tenLoaiSach;

    public Fragment_QuanLyLoaiSach() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__quan_ly_loai_sach, container, false);

        // anh xa fab
        fab = view.findViewById(R.id.fab_themLoaiSach);
        // anh xa listview
        lv = view.findViewById(R.id.lv_quanlyloaisach);

        // khoi tao DAO
        loaiSachDAO = new LoaiSachDAO(getContext());

        // khoi tao list va do du lieu vao
        list = new ArrayList<>();
        list = loaiSachDAO.getAllData();

        // Khoi tao adapter
        adapter = new LoaiSachAdapter(getContext(),R.layout.item_loaisach,list);

        // set adapter cho listview
        lv.setAdapter(adapter);

        // set click cho fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_them_loai_sach);

                edt_tenLoaiSach = dialog.findViewById(R.id.edt_dialog_tenLoaiSach);

                Button button_luu = dialog.findViewById(R.id.button_dialogThemLoaiSach_luu);
                button_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LoaiSach loaiSach = new LoaiSach();
                        loaiSach.setTenLoai(edt_tenLoaiSach.getText().toString());
                        if (edt_tenLoaiSach.getText().length() == 0){
                            Toast.makeText(getContext(), "Khong duoc de trong thong tin", Toast.LENGTH_SHORT).show();
                        }else {
                            if (loaiSachDAO.insert(loaiSach) > 0){
                                Toast.makeText(getContext(), "Them moi thanh cong", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(), "Them moi that bai", Toast.LENGTH_SHORT).show();
                            }
                        }

                        CapNhatListView();
                        dialog.dismiss();

                    }
                });

                Button button_huy = dialog.findViewById(R.id.button_dialogThemLoaiSach_huy);
                button_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // khoi tao dialog
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_thongtin_loaisach);

                // anh xa view
                EditText edt_tenloaisach = dialog.findViewById(R.id.edt_dialogThongTinLoaiSach_tenLoaiSach);
                Button button_capNhat = dialog.findViewById(R.id.button_dialogThongTinLoaiSach_capnhat);
                Button button_huy = dialog.findViewById(R.id.button_dialogThongTinLoaiSach_huy);
                Button button_xoa = dialog.findViewById(R.id.button_dialogThongTinLoaiSach_xoa);

                // lay thong tin 1 item
                LoaiSach loaiSach = list.get(position);

                // set text cho edittext
                edt_tenloaisach.setText(loaiSach.getTenLoai());

                button_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                button_capNhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // gan thong tin moi object
                        loaiSach.setTenLoai(edt_tenloaisach.getText().toString());

                        // kiem tra va cap nhat
                        if (edt_tenloaisach.getText().length() == 0){
                            Toast.makeText(getContext(), "Khong duoc de trong thong tin", Toast.LENGTH_SHORT).show();
                        }else {
                            if (loaiSachDAO.update(loaiSach) > 0){
                                Toast.makeText(getContext(), "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        CapNhatListView();
                        dialog.dismiss();

                    }
                });

                button_xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Xoa Loai sach");
                        builder.setMessage("Ban chac chan muon xoa");
                        builder.setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog1, int which) {
                                if (loaiSachDAO.delete(String.valueOf(loaiSach.getId())) > 0){
                                    Toast.makeText(getContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                                    CapNhatListView();
                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder.setNegativeButton("Huy",null);
                        AlertDialog alertDialog = builder.create();
                        builder.show();

                    }
                });

                dialog.show();
            }
        });

        return view;
    }

    public void CapNhatListView(){
        // lay du lieu + do vao list
        list = loaiSachDAO.getAllData();

        // Khoi tao adapter
        adapter = new LoaiSachAdapter(getContext(),R.layout.item_loaisach,list);

        // set adapter cho listview
        lv.setAdapter(adapter);
    }

}