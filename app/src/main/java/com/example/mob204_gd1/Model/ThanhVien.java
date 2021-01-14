package com.example.mob204_gd1.Model;

public class ThanhVien {
    private int maThanhVien;
    private String hoTen;
    private int namSinh;

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public ThanhVien(int maThanhVien, String hoTen, int namSinh) {
        this.maThanhVien = maThanhVien;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public ThanhVien() {
    }
}
