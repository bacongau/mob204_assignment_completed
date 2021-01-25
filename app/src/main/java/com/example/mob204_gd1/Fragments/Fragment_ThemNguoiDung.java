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

import com.example.mob204_gd1.Adapter.ThuThuAdapter;
import com.example.mob204_gd1.DAO.ThuThuDAO;
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
    EditText edt_ten,edt_mk1,edt_mk2,edt_maTT;
    ThuThuDAO thuThuDAO;

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

        // khoi tao ThuThuDAO
        thuThuDAO = new ThuThuDAO(getContext());

        // khoi tao list va do du lieu vao
        list = new ArrayList<>();

        // lay du lieu + do vao list
        list = thuThuDAO.getAllData();

        // Khoi tao adapter
        adapter = new ThuThuAdapter(getContext(),R.layout.item_thuthu,list);

        // set adapter cho listview
        lv.setAdapter(adapter);

        // set click cho item listview
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // khoi tao dialog
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_thongtin_thuthu);

                // anh xa view
                EditText edt_maTT = dialog.findViewById(R.id.edt_dialogThongTinTT_maTT);
                EditText edt_hotenTT = dialog.findViewById(R.id.edt_dialogThongTinTT_hoTenTT);
                EditText edt_matKhau = dialog.findViewById(R.id.edt_dialogThongTinTT_matKhau);
                Button button_capNhat = dialog.findViewById(R.id.button_dialogThongTinTT_capnhat);
                Button button_huy = dialog.findViewById(R.id.button_dialogThongTinTT_huy);
                Button button_xoa = dialog.findViewById(R.id.button_dialogThongTinTT_xoa);

                // lay thong tin 1 item
                ThuThu thuThu1 = list.get(position);

                // set text cho edittext
                edt_maTT.setText(thuThu1.getId());
                edt_hotenTT.setText(thuThu1.getHoTen());
                edt_matKhau.setText(thuThu1.getMatKhau());

                button_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                button_capNhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // gan thong tin moi object
                        thuThu1.setId(edt_maTT.getText().toString());
                        thuThu1.setMatKhau(edt_matKhau.getText().toString());
                        thuThu1.setHoTen(edt_hotenTT.getText().toString());

                        // kiem tra va cap nhat
                        if (edt_maTT.getText().length() == 0 || edt_matKhau.getText().length() == 0 || edt_hotenTT.getText().length() == 0){
                            Toast.makeText(getContext(), "Khong duoc de trong thong tin", Toast.LENGTH_SHORT).show();
                        }else {
                            if (thuThuDAO.updatePass(thuThu1) > 0){
                                Toast.makeText(getContext(), "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                                CapNhatListView();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

                button_xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Xoa thu thu");
                        builder.setMessage("Ban chac chan muon xoa");
                        builder.setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog1, int which) {
                                if (thuThuDAO.delete(thuThu1.getId()) > 0){
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



        // set click cho fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_them_thu_thu);

                ThuThuDAO thuThuDAO = new ThuThuDAO(getContext());

                edt_ten = dialog.findViewById(R.id.edt_dialog_tenThuThu);
                edt_mk1 = dialog.findViewById(R.id.edt_dialogThemTT_matKhau1);
                edt_mk2 = dialog.findViewById(R.id.edt_dialogThemTT_matKhau2);
                edt_maTT = dialog.findViewById(R.id.edt_dialogThemTT_maTT);

                Button button_luu = dialog.findViewById(R.id.button_dialogThemTT_luu);
                button_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ThuThu thuThu = new ThuThu();
                        thuThu.setId(edt_maTT.getText().toString());
                        thuThu.setHoTen(edt_ten.getText().toString());
                        thuThu.setMatKhau(edt_mk1.getText().toString());
                        if (validate() > 0){
                            if (thuThuDAO.insert(thuThu) > 0){
                                Toast.makeText(getContext(), "Them thu thu moi thanh cong", Toast.LENGTH_SHORT).show();
                                CapNhatListView();
                                dialog.cancel();
                            }else {
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
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

    public int validate(){
        int check = 1;
        if (edt_ten.getText().length() == 0 || edt_maTT.getText().length() == 0 || edt_mk1.getText().length() == 0 || edt_mk2.getText().length() == 0){
            Toast.makeText(getContext(), "Khong duoc de trong thong tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            String a = edt_mk1.getText().toString();
            String b = edt_mk2.getText().toString();
            if (!a.equals(b)){
                Toast.makeText(getContext(), "Mat khau khong trung khop", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

    public void CapNhatListView(){
        // lay du lieu + do vao list
        list = thuThuDAO.getAllData();

        // Khoi tao adapter
        adapter = new ThuThuAdapter(getContext(),R.layout.item_thuthu,list);

        // set adapter cho listview
        lv.setAdapter(adapter);
    }
}