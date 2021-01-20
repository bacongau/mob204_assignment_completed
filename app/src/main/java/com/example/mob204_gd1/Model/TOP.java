package com.example.mob204_gd1.Model;

public class TOP {
    public int soluong;
    public String tenSach;

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public TOP(int soluong, String tenSach) {
        this.soluong = soluong;
        this.tenSach = tenSach;
    }

    public TOP() {
    }
}
