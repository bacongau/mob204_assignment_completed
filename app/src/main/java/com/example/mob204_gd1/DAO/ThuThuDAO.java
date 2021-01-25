package com.example.mob204_gd1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob204_gd1.Database.DbHelper;
import com.example.mob204_gd1.Model.ThanhVien;
import com.example.mob204_gd1.Model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {

    private SQLiteDatabase db;

    public ThuThuDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThuThu obj){

        ContentValues values = new ContentValues();
        values.put("MaThuThu",obj.getId());
        values.put("hoTen",obj.getHoTen());
        values.put("matKhau",obj.getMatKhau());

        return db.insert("ThuThu",null,values);
    }

    public int updatePass(ThuThu obj){

        ContentValues values = new ContentValues();
        values.put("MaThuThu",obj.getId());
        values.put("hoTen",obj.getHoTen());
        values.put("matKhau",obj.getMatKhau());

        return db.update("ThuThu",values,"MaThuThu=?",new String[]{
                String.valueOf(obj.getId())
        });
    }

    public int delete(String id){
        return db.delete("ThuThu","MaThuThu=?",new String[]{id});
    }

    // get all data
    public List<ThuThu> getAllData(){
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    // get data theo id
    public ThuThu getId(String id){
        String sql = "SELECT * FROM ThuThu WHERE MaThuThu=?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }

    // get data voi cac tham so
    private List<ThuThu> getData(String sql, String...selectionArgs){
        List<ThuThu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThuThu obj = new ThuThu();
            obj.setId(String.valueOf(cursor.getString(cursor.getColumnIndex("MaThuThu"))));
            obj.setHoTen(String.valueOf(cursor.getString(cursor.getColumnIndex("hoTen"))));
            obj.setMatKhau(String.valueOf(cursor.getString(cursor.getColumnIndex("matKhau"))));
            list.add(obj);
        }
        return list;
    }

    //check login
    public int checkLogin(String id,String pass){
        String sql = "SELECT * FROM ThuThu WHERE MaThuThu=? AND matKhau=?";
        List<ThuThu> list = getData(sql,id,pass);
        if (list.size()==0){
            return -1;
        }
        return 1;
    }
}
