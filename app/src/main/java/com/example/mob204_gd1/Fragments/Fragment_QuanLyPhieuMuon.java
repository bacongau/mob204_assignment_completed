package com.example.mob204_gd1.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mob204_gd1.Adapter.LoaiSachAdapter;
import com.example.mob204_gd1.Adapter.PhieuMuonAdapter;
import com.example.mob204_gd1.Adapter.SachAdapter;
import com.example.mob204_gd1.Adapter.SachSpinnerAdapter;
import com.example.mob204_gd1.Adapter.ThanhVienAdapter;
import com.example.mob204_gd1.DAO.LoaiSachDAO;
import com.example.mob204_gd1.DAO.PhieuMuonDAO;
import com.example.mob204_gd1.DAO.SachDAO;
import com.example.mob204_gd1.DAO.ThanhVienDAO;
import com.example.mob204_gd1.Model.LoaiSach;
import com.example.mob204_gd1.Model.PhieuMuon;
import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.Model.ThanhVien;
import com.example.mob204_gd1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Fragment_QuanLyPhieuMuon extends Fragment {
    ListView lv;
    PhieuMuonAdapter phieuMuonAdapter;
    FloatingActionButton fab;

    List<PhieuMuon> phieuMuonList;
    PhieuMuonDAO phieuMuonDAO;

    List<Sach> sachList;
    SachDAO sachDAO;

    List<LoaiSach> loaiSachList;
    LoaiSachDAO loaiSachDAO;

    List<ThanhVien> thanhVienList;
    ThanhVienDAO thanhVienDAO;

    EditText edt_ngayTra;
    Spinner spinner_chonSach,spinner_chonThanhVien;

    int maThanhVienDuocChon,maSachDuocChon,indexSach;

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

        // khoi tao DAO
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        sachDAO = new SachDAO(getContext());
        thanhVienDAO = new ThanhVienDAO(getContext());
        loaiSachDAO = new LoaiSachDAO(getContext());

        // khoi tao list va do du lieu vao
        phieuMuonList = new ArrayList<>();
        phieuMuonList = phieuMuonDAO.getAllData();
        sachList = sachDAO.getAllData();
        thanhVienList = new ArrayList<>();
        loaiSachList = new ArrayList<>();
        loaiSachList = loaiSachDAO.getAllData();

        // Khoi tao adapter
        phieuMuonAdapter = new PhieuMuonAdapter(getContext(),R.layout.item_phieumuon,phieuMuonList);

        // set adapter cho listview
        lv.setAdapter(phieuMuonAdapter);

        // set click cho fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_them_phieu_muon);

                // anh xa
                edt_ngayTra = dialog.findViewById(R.id.edt_dialogThemPhieuMuon_chonNgayTra);
                spinner_chonSach = dialog.findViewById(R.id.spinner_dialogThemPhieuMuon_ChonSach);
                spinner_chonThanhVien = dialog.findViewById(R.id.spinner_dialogThemPhieuMuon_ChonThanhVien);

                // setup spinner chon thanh vien
                // tao list va lay thanh vien
                thanhVienList = thanhVienDAO.getAllData();
                // khoi tao adapter
                ThanhVienAdapter thanhVienAdapter = new ThanhVienAdapter(getContext(),R.layout.item_thanhvien,thanhVienList);
                // set Adapter
                spinner_chonThanhVien.setAdapter(thanhVienAdapter);

                // set click
                spinner_chonThanhVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maThanhVienDuocChon = thanhVienList.get(position).getMaThanhVien();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                // setup spinner chon sach
                // tao list va lay du lieu sach
                sachList = sachDAO.getAllData();
                // khoi tao adapter
                SachSpinnerAdapter sachSpinnerAdapter = new SachSpinnerAdapter(getContext(),R.layout.item_spinner_sach,sachList,loaiSachList);
                // set Adapter
                spinner_chonSach.setAdapter(sachSpinnerAdapter);

                // set click
                spinner_chonSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maSachDuocChon = sachList.get(position).getId();
                        indexSach = sachList.indexOf(sachList);
                        Log.e("maSachDuocChon: ", maSachDuocChon + "");
                        Log.e("indexSach: ", indexSach + "");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                // chon ngay
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                int thisYear = calendar.get(Calendar.YEAR);
                int thisMonth = calendar.get(Calendar.MONTH);
                int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
                edt_ngayTra.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(year,month,dayOfMonth);
                                edt_ngayTra.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        },thisYear,thisMonth,thisDay);
                        datePickerDialog.show();

                    }
                });

                Button button_luu = dialog.findViewById(R.id.button_dialogThemPhieuMuon_luu);
                button_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PhieuMuon phieuMuon = new PhieuMuon();
                        phieuMuon.setIdSach(maSachDuocChon);
                        phieuMuon.setIdThanhVien(maThanhVienDuocChon);
                        phieuMuon.setIdThuThu("admin");

                        String ngayTra = edt_ngayTra.getText().toString();
                        phieuMuon.setNgay(ngayTra);

                        // tinh tien thue = so ngay thue * gia thue
                        Calendar calendar1 = Calendar.getInstance();
                        int soNgayThue = (int) ((calendar.getTimeInMillis() - calendar1.getTimeInMillis()) / (1000*60*60*24));

                        int soTienThue = soNgayThue * sachList.get(maSachDuocChon).getGiaThue();
                        Log.e("aaaaa", "" + soTienThue);

                        phieuMuon.setTienThue(soTienThue);
                        phieuMuon.setTraSach(1);

                        if (edt_ngayTra.getText().length() == 0){
                            Toast.makeText(getContext(), "Khong duoc de trong thong tin", Toast.LENGTH_SHORT).show();
                        }else {
                            if (phieuMuonDAO.insert(phieuMuon) > 0){
                                Toast.makeText(getContext(), "Them moi thanh cong", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(), "Them moi that bai", Toast.LENGTH_SHORT).show();
                            }
                        }

                        CapNhatListView();
                        dialog.dismiss();

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

    public void CapNhatListView(){
        // lay du lieu + do vao list
        phieuMuonList = phieuMuonDAO.getAllData();

        // Khoi tao adapter
        phieuMuonAdapter = new PhieuMuonAdapter(getContext(),R.layout.item_phieumuon,phieuMuonList);

        // set adapter cho listview
        lv.setAdapter(phieuMuonAdapter);
    }

}