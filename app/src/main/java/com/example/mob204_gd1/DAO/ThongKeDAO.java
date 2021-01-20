package com.example.mob204_gd1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob204_gd1.Database.DbHelper;
import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.Model.TOP;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    SQLiteDatabase db;
    Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    public ThongKeDAO(Context context){
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // thong ke top 10
    public List<TOP> getTop10(){
        String sql = "SELECT maSach, COUNT(maSach) AS SoLuong FROM PhieuMuon GROUP BY maSach ORDER BY SoLuong DESC LIMIT 10";
        List<TOP> list = new ArrayList<>();
        SachDAO sachDAO = new SachDAO(context);
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            TOP top = new TOP();
            Sach sach = sachDAO.getId(cursor.getString(cursor.getColumnIndex("maSach")));
            top.tenSach = sach.getTenSach();
            top.soluong = Integer.parseInt(cursor.getString(cursor.getColumnIndex("SoLuong"))); // SoLuong or soluong
            list.add(top);
        }

        return list;
    }


    // thong ke doanh thu
    public int getDoanhThu(String tuNgay, String denNgay){
        String sql = "SELECT SUM(tienthue) as doanhthu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor cursor = db.rawQuery(sql,new String[]{tuNgay,denNgay});

        while (cursor.moveToNext()){
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhthu"))));
            }catch (Exception e){
                list.add(0);
            }
        }

        return list.get(0);
    }
}
