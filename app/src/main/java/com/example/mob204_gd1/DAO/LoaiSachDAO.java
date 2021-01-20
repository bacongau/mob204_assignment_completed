package com.example.mob204_gd1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob204_gd1.Database.DbHelper;
import com.example.mob204_gd1.Model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {

    private SQLiteDatabase db;

    public LoaiSachDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiSach  obj){

        ContentValues values = new ContentValues();
        values.put("tenLoai",obj.getTenLoai());

        return db.insert("LoaiSach",null,values);
    }

    public int update(LoaiSach obj){

        ContentValues values = new ContentValues();
        values.put("tenLoai",obj.getTenLoai());

        return db.update("LoaiSach",values,"maLoai=?",new String[]{
                String.valueOf(obj.getId())
        });
    }

    public int delete(String id){
        return db.delete("LoaiSach","maLoai=?",new String[]{id});
    }

    // get all data
    public List<LoaiSach> getAllData(){
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }

    // get data theo id
    public LoaiSach getId(String id){
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql,id);
        return list.get(0);
    }

    // get data voi cac tham so
    private List<LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            LoaiSach obj = new LoaiSach();
            obj.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            obj.setTenLoai(String.valueOf(cursor.getString(cursor.getColumnIndex("tenLoai"))));
            list.add(obj);
        }
        return list;
    }
}
