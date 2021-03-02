package com.example.mob204_gd1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mob204_gd1.DAO.ThuThuDAO;
import com.google.android.material.textfield.TextInputLayout;

public class LoginScreen extends AppCompatActivity {
    EditText edt_tenDangNhap,edt_matKhau;
    CheckBox chk_luuDangNhap;
    Button button_dangNhap,button_huy;
    ThuThuDAO thuThuDAO;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        Toast.makeText(this, "Tên đăng nhập: admin " + "\n" + "Mật khẩu: admin ", Toast.LENGTH_LONG).show();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#f22e3d"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("");

        edt_matKhau = findViewById(R.id.edt_login_matKhau);
        edt_tenDangNhap = findViewById(R.id.edt_login_tenDangNhap);
        button_dangNhap = findViewById(R.id.button_login_dangNhap);
        button_huy = findViewById(R.id.button_login_huy);
        chk_luuDangNhap = findViewById(R.id.checkBox_luuMatKhau);

        thuThuDAO = new ThuThuDAO(this);

        sharedPreferences = getSharedPreferences("ThongTinDangNhap",MODE_PRIVATE);
        edt_tenDangNhap.setText(sharedPreferences.getString("TENDANGNHAP",""));
        edt_matKhau.setText(sharedPreferences.getString("MATKHAU",""));
        chk_luuDangNhap.setChecked(sharedPreferences.getBoolean("REMEMBER",false));

        // button huy
        button_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_matKhau.setText("");
                edt_tenDangNhap.setText("");
            }
        });

        // button dang nhap
        button_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {
        String a = edt_tenDangNhap.getText().toString();
        String b = edt_matKhau.getText().toString();
        if (a.isEmpty() || b.isEmpty()){
            Toast.makeText(this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
        }else {
            if (thuThuDAO.checkLogin(a,b) > 0  ||  (a.equals("admin") && b.equals("admin"))){
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                luuThongTinDangNhap(a,b,chk_luuDangNhap.isChecked());

                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("tenTK",a);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void luuThongTinDangNhap(String ten,String matkhau,boolean remember){
        SharedPreferences sharedPreferences = getSharedPreferences("ThongTinDangNhap",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (remember == false){
            editor.clear();
        }else {
            editor.putString("TENDANGNHAP",ten);
            editor.putString("MATKHAU",matkhau);
            editor.putBoolean("REMEMBER",remember);
        }

        editor.commit();
    }
}