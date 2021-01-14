package com.example.mob204_gd1.Model;

public class LoaiSach {
    private int id;
    private String tenLoai;

    public LoaiSach() {
    }

    public LoaiSach(int id, String tenLoai) {
        this.id = id;
        this.tenLoai = tenLoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
