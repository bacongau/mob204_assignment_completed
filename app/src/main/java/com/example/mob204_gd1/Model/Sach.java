package com.example.mob204_gd1.Model;

public class Sach {
    private int id;
    private String tenSach;
    private int giaThue;
    private int maLoai;

    public Sach() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public Sach(int id, String tenSach, int giaThue, int maLoai) {
        this.id = id;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
    }
}
