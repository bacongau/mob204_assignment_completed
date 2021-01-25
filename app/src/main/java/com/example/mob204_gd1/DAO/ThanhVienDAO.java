package com.example.mob204_gd1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob204_gd1.Database.DbHelper;
import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.Model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {

    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThanhVien obj){

        ContentValues values = new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("namSinh",obj.getNamSinh());

        return db.insert("ThanhVien",null,values);
    }

    public int update(ThanhVien obj){

        ContentValues values = new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("namSinh",obj.getNamSinh());

        return db.update("ThanhVien",values,"maThanhVien=?",new String[]{
                String.valueOf(obj.getMaThanhVien())
        });
    }

    public int delete(String id){
        return db.delete("ThanhVien","maThanhVien=?",new String[]{id});
    }

    // get all data
    public List<ThanhVien> getAllData(){
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    // get data theo id
    public ThanhVien getId(String id){
        String sql = "SELECT * FROM ThanhVien WHERE maThanhVien=?";
        List<ThanhVien> list = getData(sql,id);
        return list.get(0);
    }

    // get data voi cac tham so
    private List<ThanhVien> getData(String sql, String...selectionArgs){
        List<ThanhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThanhVien obj = new ThanhVien();
            obj.setMaThanhVien(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maThanhVien"))));
            obj.setHoTen(String.valueOf(cursor.getString(cursor.getColumnIndex("hoTen"))));
            obj.setNamSinh(Integer.parseInt(cursor.getString(cursor.getColumnIndex("namSinh"))));
            list.add(obj);
        }
        return list;
    }
}
