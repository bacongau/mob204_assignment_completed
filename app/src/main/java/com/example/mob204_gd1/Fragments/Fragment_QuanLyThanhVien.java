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

import com.example.mob204_gd1.Adapter.SachAdapter;
import com.example.mob204_gd1.Adapter.ThanhVienAdapter;
import com.example.mob204_gd1.Adapter.ThuThuAdapter;
import com.example.mob204_gd1.DAO.ThanhVienDAO;
import com.example.mob204_gd1.DAO.ThuThuDAO;
import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.Model.ThanhVien;
import com.example.mob204_gd1.Model.ThuThu;
import com.example.mob204_gd1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Fragment_QuanLyThanhVien extends Fragment {
    ListView lv;
    ThanhVienAdapter adapter;
    List<ThanhVien> list;
    FloatingActionButton fab;
    ThanhVienDAO thanhVienDAO;
    EditText edt_tenTv, edt_namSinh;

    public Fragment_QuanLyThanhVien() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__quan_ly_thanh_vien, container, false);

        // anh xa floating action button
        fab = view.findViewById(R.id.fab_themThanhVien);
        // anh xa listview
        lv = view.findViewById(R.id.lv_quanlythanhvien);

        // khoi tao DAO
        thanhVienDAO = new ThanhVienDAO(getContext());

        // khoi tao list va do du lieu vao
        list = new ArrayList<>();
        list = thanhVienDAO.getAllData();

        // Khoi tao adapter
        adapter = new ThanhVienAdapter(getContext(),R.layout.item_thanhvien,list);

        // set adapter cho listview
        lv.setAdapter(adapter);

        // set click cho listview
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // khoi tao dialog
                Dialog dialog2 = new Dialog(getContext());
                dialog2.setContentView(R.layout.dialog_thongtin_thanhvien);

                // anh xa view
                EditText edt_hotenThanhVien = dialog2.findViewById(R.id.edt_dialogThongTinTV_tenTV);
                EditText edt_namSinhThanhVien = dialog2.findViewById(R.id.edt_dialogThongTinTV_NamSinh);
                Button button_capNhat = dialog2.findViewById(R.id.button_dialogThongTinTV_capnhat);
                Button button_huy = dialog2.findViewById(R.id.button_dialogThongTinTV_huy);
                Button button_xoa = dialog2.findViewById(R.id.button_dialogThongTinTV_xoa);

                // lay thong tin 1 item
                ThanhVien thanhVien1 = list.get(position);

                // set text cho edittext
                edt_hotenThanhVien.setText(thanhVien1.getHoTen());
                edt_namSinhThanhVien.setText("" + thanhVien1.getNamSinh());

                button_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });

                button_capNhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // gan thong tin moi object
                        thanhVien1.setHoTen(edt_hotenThanhVien.getText().toString());
                        thanhVien1.setNamSinh(Integer.parseInt(edt_namSinhThanhVien.getText().toString()));

                        // kiem tra va cap nhat
                        if (edt_hotenThanhVien.getText().length() == 0 || edt_namSinhThanhVien.getText().length() == 0){
                            Toast.makeText(getContext(), "Khong duoc de trong thong tin", Toast.LENGTH_SHORT).show();
                        }else {
                            if (thanhVienDAO.update(thanhVien1) > 0){
                                Toast.makeText(getContext(), "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        CapNhatListView();
                        dialog2.dismiss();

                    }
                });

                button_xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Xoa Thanh vien");
                        builder.setMessage("Ban chac chan muon xoa");
                        builder.setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog1, int which) {
                                if (thanhVienDAO.delete(String.valueOf(thanhVien1.getMaThanhVien())) > 0){
                                    Toast.makeText(getContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                                    CapNhatListView();
                                    dialog2.dismiss();
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

                dialog2.show();
            }
        });

        // set click cho fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_them_thanh_vien);

                edt_tenTv = dialog.findViewById(R.id.edt_dialogThemTv_ten);
                edt_namSinh = dialog.findViewById(R.id.edt_dialogThemTv_namsinh);

                Button button_luu = dialog.findViewById(R.id.button_dialogThemTv_luu);
                button_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ThanhVien thanhVien = new ThanhVien();
                        thanhVien.setNamSinh(Integer.parseInt(edt_namSinh.getText().toString()));
                        thanhVien.setHoTen(edt_tenTv.getText().toString());
                        if (validate() > 0){
                            if (thanhVienDAO.insert(thanhVien) > 0){
                                Toast.makeText(getContext(), "Them moi thanh cong", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(), "Them moi that bai", Toast.LENGTH_SHORT).show();
                            }
                        }

                        CapNhatListView();
                        dialog.dismiss();
                    }
                });

                Button button_huy = dialog.findViewById(R.id.button_dialogThemTv_huy);
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

    public int validate(){
        int check = 1;
        if (edt_namSinh.getText().length() == 0 || edt_tenTv.getText().length() == 0){
            Toast.makeText(getContext(), "Khong duoc de trong thong tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void CapNhatListView(){
        // lay du lieu + do vao list
        list = thanhVienDAO.getAllData();

        // Khoi tao adapter
        adapter = new ThanhVienAdapter(getContext(),R.layout.item_thanhvien,list);

        // set adapter cho listview
        lv.setAdapter(adapter);
    }

}