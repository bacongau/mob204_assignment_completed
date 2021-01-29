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

import com.example.mob204_gd1.DAO.ThongKeDAO;
import com.example.mob204_gd1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Fragment_DoanhThu extends Fragment {
    EditText edt_ngayBatDau,edt_ngayKetThuc;
    Button button;
    TextView tv_doanhThu;
    ThongKeDAO thongKeDAO;

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
                int c = thongKeDAO.getDoanhThu(a,b);
                tv_doanhThu.setText("Doanh Thu: " + c);
            }
        });

        return view;
    }

    private void chonNgayKetThuc() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);
        int thisMonth = calendar.get(Calendar.MONTH);
        int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                edt_ngayKetThuc.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },thisYear,thisMonth,thisDay);
        datePickerDialog.show();
    }

    private void chonNgayBatDau() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);
        int thisMonth = calendar.get(Calendar.MONTH);
        int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                edt_ngayBatDau.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },thisYear,thisMonth,thisDay);
        datePickerDialog.show();
    }


}