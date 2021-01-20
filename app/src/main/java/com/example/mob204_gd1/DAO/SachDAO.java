package com.example.mob204_gd1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob204_gd1.Database.DbHelper;
import com.example.mob204_gd1.Model.LoaiSach;
import com.example.mob204_gd1.Model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {


    private SQLiteDatabase db;

    public SachDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Sach obj){

        ContentValues values = new ContentValues();
        values.put("tenSach",obj.getTenSach());
        values.put("giaThue",obj.getGiaThue());
        values.put("maLoai",obj.getMaLoai());

        return db.insert("Sach",null,values);
    }

    public int update(Sach obj){

        ContentValues values = new ContentValues();
        values.put("tenSach",obj.getTenSach());
        values.put("giaThue",obj.getGiaThue());
        values.put("maLoai",obj.getMaLoai());

        return db.update("Sach",values,"maSach=?",new String[]{
                String.valueOf(obj.getId())
        });
    }

    public int delete(String id){
        return db.delete("Sach","maSach=?",new String[]{id});
    }

    // get all data
    public List<Sach> getAllData(){
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }

    // get data theo id
    public Sach getId(String id){
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }

    // get data voi cac tham so
    private List<Sach> getData(String sql, String...selectionArgs){
        List<Sach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            Sach obj = new Sach();
            obj.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach"))));
            obj.setGiaThue(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaThue"))));
            obj.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            obj.setTenSach(String.valueOf(cursor.getString(cursor.getColumnIndex("tenSach"))));
            list.add(obj);
        }
        return list;
    }
}
