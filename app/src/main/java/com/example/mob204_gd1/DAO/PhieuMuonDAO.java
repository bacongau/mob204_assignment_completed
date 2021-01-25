package com.example.mob204_gd1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob204_gd1.Database.DbHelper;
import com.example.mob204_gd1.Model.PhieuMuon;
import com.example.mob204_gd1.Model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {

    private SQLiteDatabase db;

    public PhieuMuonDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhieuMuon obj){

        ContentValues values = new ContentValues();
        values.put("maSach",obj.getIdSach());
        values.put("maThanhVien",obj.getIdThanhVien());
        values.put("maThuThu",obj.getIdThuThu());
        values.put("ngay",obj.getNgay());
        values.put("tienThue",obj.getTienThue());
        values.put("traSach",obj.getTraSach());

        return db.insert("PhieuMuon",null,values);
    }

    public int update(PhieuMuon obj){

        ContentValues values = new ContentValues();
        values.put("maSach",obj.getIdSach());
        values.put("maThanhVien",obj.getIdThanhVien());
        values.put("maThuThu",obj.getIdThuThu());
        values.put("ngay",obj.getNgay());
        values.put("tienThue",obj.getTienThue());
        values.put("traSach",obj.getTraSach());

        return db.update("PhieuMuon",values,"maPhieuMuon=?",new String[]{
                String.valueOf(obj.getId())
        });
    }

    public int delete(String id){
        return db.delete("PhieuMuon","maPhieuMuon=?",new String[]{id});
    }

    // get all data
    public List<PhieuMuon> getAllData(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    // get data theo id
    public PhieuMuon getId(String id){
        String sql = "SELECT * FROM PhieuMuon WHERE maPhieuMuon=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }

    // get data voi cac tham so
    private List<PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            PhieuMuon obj = new PhieuMuon();
            obj.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maPhieuMuon"))));
            obj.setIdSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach"))));
            obj.setIdThanhVien(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maThanhVien"))));
            obj.setIdThuThu(String.valueOf(cursor.getString(cursor.getColumnIndex("maThuThu"))));
            obj.setNgay(String.valueOf(cursor.getString(cursor.getColumnIndex("ngay"))));
            obj.setTienThue(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tienThue"))));
            obj.setTraSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("traSach"))));
            list.add(obj);
        }
        return list;
    }
}
