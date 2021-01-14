package com.example.mob204_gd1.Model;

public class PhieuMuon {
    private int id;
    private String idThuThu;
    private int idThanhVien;
    private int idSach;
    private int tienThue;
    private String ngay;
    private int traSach;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdThuThu() {
        return idThuThu;
    }

    public void setIdThuThu(String idThuThu) {
        this.idThuThu = idThuThu;
    }

    public int getIdThanhVien() {
        return idThanhVien;
    }

    public void setIdThanhVien(int idThanhVien) {
        this.idThanhVien = idThanhVien;
    }

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public PhieuMuon(int id, String idThuThu, int idThanhVien, int idSach, int tienThue, String ngay, int traSach) {
        this.id = id;
        this.idThuThu = idThuThu;
        this.idThanhVien = idThanhVien;
        this.idSach = idSach;
        this.tienThue = tienThue;
        this.ngay = ngay;
        this.traSach = traSach;
    }

    public PhieuMuon() {
    }
}
