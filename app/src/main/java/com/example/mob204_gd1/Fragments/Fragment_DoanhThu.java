package com.example.mob204_gd1.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mob204_gd1.DAO.ThongKeDAO;
import com.example.mob204_gd1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Fragment_DoanhThu extends Fragment {
    EditText edt_ngayBatDau, edt_ngayKetThuc;
    Button button;
    TextView tv_doanhThu;
    ThongKeDAO thongKeDAO;
    Calendar calendar1, calendar2;

    public Fragment_DoanhThu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__doanh_thu, container, false);

        // anh xa
        edt_ngayBatDau = view.findViewById(R.id.edt_pickNgayBatDau);
        edt_ngayKetThuc = view.findViewById(R.id.edt_pickNgayKetThuc);
        button = view.findViewById(R.id.button_tinhDoanhThu);
        tv_doanhThu = view.findViewById(R.id.textView_doanhthu);

        // khoi tao
        thongKeDAO = new ThongKeDAO(getContext());

        // bat su kien click
        edt_ngayBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgayBatDau();
            }
        });

        edt_ngayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgayKetThuc();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = edt_ngayBatDau.getText().toString();
                String b = edt_ngayKetThuc.getText().toString();
                if (a.isEmpty() || b.isEmpty()) {
                    Toast.makeText(getContext(), "Bạn chưa chọn ngày", Toast.LENGTH_SHORT).show();
                } else if (calendar1.getTimeInMillis() - calendar2.getTimeInMillis() < 0) {
                    Toast.makeText(getContext(), "Chọn ngày không hợp lệ", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    int c = thongKeDAO.getDoanhThu(a, b);
                    tv_doanhThu.setText("Doanh Thu: " + c);
                    Toast.makeText(getContext(), "Doanh Thu: " + c, Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    private void chonNgayKetThuc() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        calendar1 = Calendar.getInstance();
        int thisYear = calendar1.get(Calendar.YEAR);
        int thisMonth = calendar1.get(Calendar.MONTH);
        int thisDay = calendar1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar1.set(year, month, dayOfMonth);
                edt_ngayKetThuc.setText(simpleDateFormat.format(calendar1.getTime()));
            }
        }, thisYear, thisMonth, thisDay);
        datePickerDialog.show();
    }

    private void chonNgayBatDau() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        calendar2 = Calendar.getInstance();
        int thisYear = calendar2.get(Calendar.YEAR);
        int thisMonth = calendar2.get(Calendar.MONTH);
        int thisDay = calendar2.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar2.set(year, month, dayOfMonth);
                edt_ngayBatDau.setText(simpleDateFormat.format(calendar2.getTime()));
            }
        }, thisYear, thisMonth, thisDay);
        datePickerDialog.show();
    }


}