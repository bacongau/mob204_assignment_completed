package com.example.mob204_gd1.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
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
    Spinner spinner_chonSach, spinner_chonThanhVien;

    int maThanhVienDuocChon, maSachDuocChon, indexSach;



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
        phieuMuonAdapter = new PhieuMuonAdapter(getContext(), R.layout.item_phieumuon, phieuMuonList);

        // set adapter cho listview
        lv.setAdapter(phieuMuonAdapter);

        // set click lv
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // khoi tao dialog
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_thongtin_phieumuon);

                // anh xa view
                Spinner spinner_thongtin_chonthanhvien = dialog.findViewById(R.id.spinner_dialog_thongtin_phieumuon_chonThanhVien);
                Spinner spinner_thongtin_chonsach = dialog.findViewById(R.id.spinner_dialog_thongtin_phieumuon_chonSach);
                EditText edt_thongtin_tenthanhvien = dialog.findViewById(R.id.edt_dialog_thongtin_phieumuon_tenThanhvien);
                EditText edt_thongtin_tensach = dialog.findViewById(R.id.edt_dialog_thongtin_phieumuon_tenSach);
                EditText edt_thongtin_ngaytra = dialog.findViewById(R.id.edt_dialog_thongtin_phieumuon_ngayTra);
                Button button_capNhat = dialog.findViewById(R.id.button_dialog_thongtin_phieumuon_capnhat);
                Button button_huy = dialog.findViewById(R.id.button_dialog_thongtin_phieumuon_huy);
                Button button_xoa = dialog.findViewById(R.id.button_dialog_thongtin_phieumuon_xoa);
                Switch swich_trangthai = dialog.findViewById(R.id.switch_dialog_thongtin_phieumuon_chonTrangThaiTraSach);

                // lay thong tin 1 item
                PhieuMuon phieuMuon = phieuMuonList.get(position);

                String maSachDangChon = String.valueOf(phieuMuon.getIdSach());
                Sach sach = sachDAO.getId(maSachDangChon);
                String maTvDangChon = String.valueOf(phieuMuon.getIdThanhVien());
                ThanhVien thanhVien = thanhVienDAO.getId(maTvDangChon);
                // set text cho edittext
                edt_thongtin_tensach.setText(sach.getTenSach());
                edt_thongtin_tenthanhvien.setText(thanhVien.getHoTen());
                edt_thongtin_ngaytra.setText(phieuMuon.getNgay());

                // setup spinner chon thanh vien
                // tao list va lay thanh vien
                thanhVienList = thanhVienDAO.getAllData();
                // khoi tao adapter
                ThanhVienAdapter thanhVienAdapter = new ThanhVienAdapter(getContext(), R.layout.item_thanhvien, thanhVienList);
                // set Adapter
                spinner_thongtin_chonthanhvien.setAdapter(thanhVienAdapter);

                // set click
                final int[] check = {1};
                spinner_thongtin_chonthanhvien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {
                        maThanhVienDuocChon = thanhVienList.get(position1).getMaThanhVien();
                        if (check[0] == 2) {
                            edt_thongtin_tenthanhvien.setText(thanhVienList.get(position1).getHoTen());
                        }
                        if (check[0] == 1) {
                            check[0]++;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                // setup spinner chon sach
                // tao list va lay du lieu sach
                sachList = sachDAO.getAllData();
                // khoi tao adapter
                SachSpinnerAdapter sachSpinnerAdapter = new SachSpinnerAdapter(getContext(), R.layout.item_spinner_sach, sachList);
                // set Adapter
                spinner_thongtin_chonsach.setAdapter(sachSpinnerAdapter);
                // set click
                final int[] check2 = {1};
                spinner_thongtin_chonsach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {
                        maSachDuocChon = phieuMuonList.get(position).getIdSach();
                        indexSach = position1;
                        if (check2[0] == 2) {
                            edt_thongtin_tensach.setText(sachList.get(position1).getTenSach());
                        }
                        if (check2[0] == 1) {
                            check2[0]++;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                //set chon ngay moi
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                int thisYear = calendar.get(Calendar.YEAR);
                int thisMonth = calendar.get(Calendar.MONTH);
                int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
                edt_thongtin_ngaytra.setText(phieuMuon.getNgay());
                edt_thongtin_ngaytra.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(year, month, dayOfMonth);
                                edt_thongtin_ngaytra.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        }, thisYear, thisMonth, thisDay);
                        datePickerDialog.show();
                    }
                });

                swich_trangthai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            phieuMuon.setTraSach(0);
                            swich_trangthai.setText("Đã trả");
                        } else {
                            phieuMuon.setTraSach(1);
                            swich_trangthai.setText("Chưa trả");
                        }
                    }
                });

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
                        phieuMuon.setIdThanhVien(maThanhVienDuocChon);
                        phieuMuon.setIdSach(maSachDuocChon);
                        phieuMuon.setIdThuThu("admin");
                        phieuMuon.setNgay((edt_thongtin_ngaytra.getText().toString()));



                        // tinh tien thue = so ngay thue * gia thue
                        Calendar calendar2 = Calendar.getInstance();

                        int soNgayThue = (int) ((calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / (1000 * 60 * 60 * 24));
                        int giaThue = sachList.get(indexSach).getGiaThue();
                        int soTienThue = soNgayThue * giaThue;
                        phieuMuon.setTienThue(soTienThue);

                        // kiem tra va cap nhat
                        if (edt_thongtin_tensach.getText().length() == 0 || edt_thongtin_tenthanhvien.getText().length() == 0 || edt_thongtin_ngaytra.getText().length() == 0) {
                            Toast.makeText(getContext(), "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            if (phieuMuonDAO.update(phieuMuon) > 0) {
                                Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            } else {
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
                        builder.setTitle("Xóa phiếu mượn");
                        builder.setMessage("Bạn chắc chắn muốn xóa");
                        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog1, int which) {
                                if (phieuMuonDAO.delete(String.valueOf(phieuMuon.getId())) > 0) {
                                    Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    CapNhatListView();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder.setNegativeButton("Hủy", null);
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
                dialog.setContentView(R.layout.dialog_them_phieu_muon);

                // anh xa
                edt_ngayTra = dialog.findViewById(R.id.edt_dialogThemPhieuMuon_chonNgayTra);
                spinner_chonSach = dialog.findViewById(R.id.spinner_dialogThemPhieuMuon_ChonSach);
                spinner_chonThanhVien = dialog.findViewById(R.id.spinner_dialogThemPhieuMuon_ChonThanhVien);

                // setup spinner chon thanh vien
                // tao list va lay thanh vien
                thanhVienList = thanhVienDAO.getAllData();
                // khoi tao adapter
                ThanhVienAdapter thanhVienAdapter = new ThanhVienAdapter(getContext(), R.layout.item_thanhvien, thanhVienList);
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
                SachSpinnerAdapter sachSpinnerAdapter = new SachSpinnerAdapter(getContext(), R.layout.item_spinner_sach, sachList);
                // set Adapter
                spinner_chonSach.setAdapter(sachSpinnerAdapter);

                // set click
                spinner_chonSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        indexSach = sachList.get(position).getId();
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
                                calendar.set(year, month, dayOfMonth);
                                edt_ngayTra.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        }, thisYear, thisMonth, thisDay);
                        datePickerDialog.show();

                    }
                });

                Button button_luu = dialog.findViewById(R.id.button_dialogThemPhieuMuon_luu);
                button_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PhieuMuon phieuMuon = new PhieuMuon();
                        phieuMuon.setIdSach(indexSach);
                        phieuMuon.setIdThanhVien(maThanhVienDuocChon);

                        String a = getArguments().getString("user_key");
                        phieuMuon.setIdThuThu(a);

                        String ngayTra = edt_ngayTra.getText().toString();
                        phieuMuon.setNgay(ngayTra);

                        // tinh tien thue = so ngay thue * gia thue
                        Calendar calendar1 = Calendar.getInstance();
                        int soNgayThue = (int) ((calendar.getTimeInMillis() - calendar1.getTimeInMillis()) / (1000 * 60 * 60 * 24));
                        Sach sach = sachDAO.getId(String.valueOf(indexSach));
                        int giaThue = sach.getGiaThue();
                        int soTienThue = soNgayThue * giaThue;
                        phieuMuon.setTienThue(soTienThue);

                        phieuMuon.setTraSach(1);

                        if (edt_ngayTra.getText().length() == 0) {
                            Toast.makeText(getContext(), "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            if (phieuMuonDAO.insert(phieuMuon) > 0) {
                                Toast.makeText(getContext(), "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
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
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return view;
    }

    public void CapNhatListView() {
        // lay du lieu + do vao list
        phieuMuonList = phieuMuonDAO.getAllData();

        // Khoi tao adapter
        phieuMuonAdapter = new PhieuMuonAdapter(getContext(), R.layout.item_phieumuon, phieuMuonList);

        // set adapter cho listview
        lv.setAdapter(phieuMuonAdapter);
    }

}