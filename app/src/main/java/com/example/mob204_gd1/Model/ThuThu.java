package com.example.mob204_gd1.Model;

public class ThuThu {
    private String id;
    private String hoTen;
    private String matKhau;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public ThuThu(String id, String hoTen, String matKhau) {
        this.id = id;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public ThuThu() {
    }
}
