package com.example.mob204_gd1.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mob204_gd1.DAO.ThuThuDAO;
import com.example.mob204_gd1.Model.ThuThu;
import com.example.mob204_gd1.R;


public class    Fragment_DoiMatKhau extends Fragment {
    EditText edt_mkCu,edt_mkMoi,edt_mkMoi2;
    Button button_luu,button_huy;
    ThuThuDAO thuThuDAO;

    public Fragment_DoiMatKhau() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__doi_mat_khau, container, false);

        edt_mkCu = view.findViewById(R.id.edt_matKhauCu);
        edt_mkMoi = view.findViewById(R.id.edt_matKhauMoi1);
        edt_mkMoi2 = view.findViewById(R.id.edt_matKhauMoi2);
        button_huy = view.findViewById(R.id.button_huyLuuMatKhauMoi);
        button_luu = view.findViewById(R.id.button_luuMatKhauMoi);

        thuThuDAO = new ThuThuDAO(getContext());

        button_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_mkCu.setText("");
                edt_mkMoi.setText("");
                edt_mkMoi2.setText("");
            }
        });

        button_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
                String tenTK = sharedPreferences.getString("TENDANGNHAP","");
                if (validate() > 0){
                    ThuThu thuThu = thuThuDAO.getId(tenTK);
                    thuThu.setMatKhau(edt_mkMoi.getText().toString());

                    if (thuThuDAO.updatePass(thuThu) > 0){
                        Toast.makeText(getContext(), "Thay doi mat khau thanh cong", Toast.LENGTH_SHORT).show();
                        edt_mkCu.setText("");
                        edt_mkMoi.setText("");
                        edt_mkMoi2.setText("");
                    }else {
                        Toast.makeText(getContext(), "Khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    public int validate(){
        int check = 1;
        if (edt_mkCu.getText().toString().length() == 0 || edt_mkMoi2.getText().toString().length() == 0 || edt_mkMoi.getText().toString().length() == 0){
            Toast.makeText(getContext(), "Khong duoc de trong thong tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
            String mkCu = sharedPreferences.getString("MATKHAU","");
            String mkMoi = edt_mkMoi.getText().toString();
            String mkMoi2 = edt_mkMoi2.getText().toString();
            if (mkCu.equals(edt_mkCu.getText().toString()) == false){
                Toast.makeText(getContext(), "Mat khau cu sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (mkMoi.equals(mkMoi2) == false){
                Toast.makeText(getContext(), "Mat khau khong trung khop", Toast.LENGTH_SHORT).show();
            }
        }
        return check;
    }

}