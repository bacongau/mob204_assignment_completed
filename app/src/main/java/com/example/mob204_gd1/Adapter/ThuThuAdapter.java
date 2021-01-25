package com.example.mob204_gd1.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mob204_gd1.DAO.ThuThuDAO;
import com.example.mob204_gd1.Model.Sach;
import com.example.mob204_gd1.Model.ThuThu;
import com.example.mob204_gd1.R;

import java.util.List;

public class ThuThuAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ThuThu> list;

    public ThuThuAdapter(Context context, int layout, List<ThuThu> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView tv_maThuThu,tv_hoTenThuThu,tv_matKhau;
        ImageView imageView_edit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_thuthu,null);

            viewHolder.tv_hoTenThuThu = convertView.findViewById(R.id.textView_hoTenThuThu);
            viewHolder.tv_maThuThu = convertView.findViewById(R.id.textView_idThuThu);
            viewHolder.tv_matKhau = convertView.findViewById(R.id.textView_matKhau);
            viewHolder.imageView_edit = convertView.findViewById(R.id.imageView_edit_thuthu);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_hoTenThuThu.setText(list.get(position).getHoTen());
        viewHolder.tv_maThuThu.setText(list.get(position).getId());
        viewHolder.tv_matKhau.setText(list.get(position).getMatKhau());

        ThuThuDAO thuThuDAO = new ThuThuDAO(context);

//        viewHolder.imageView_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // khoi tao dialog
//                Dialog dialog = new Dialog(context);
//                dialog.setContentView(R.layout.dialog_thongtin_thuthu);
//
//                // anh xa view
//                EditText edt_maTT = dialog.findViewById(R.id.edt_dialogThongTinTT_maTT);
//                EditText edt_hotenTT = dialog.findViewById(R.id.edt_dialogThongTinTT_hoTenTT);
//                EditText edt_matKhau = dialog.findViewById(R.id.edt_dialogThongTinTT_matKhau);
//                Button button_capNhat = dialog.findViewById(R.id.button_dialogThongTinTT_capnhat);
//                Button button_huy = dialog.findViewById(R.id.button_dialogThongTinTT_huy);
//                Button button_xoa = dialog.findViewById(R.id.button_dialogThongTinTT_xoa);
//
//                // lay thong tin 1 item
//                ThuThu thuThu1 = list.get(position);
//
//                // set text cho edittext
//                edt_maTT.setText(thuThu1.getId());
//                edt_hotenTT.setText(thuThu1.getHoTen());
//                edt_matKhau.setText(thuThu1.getMatKhau());
//
//                button_huy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.cancel();
//                    }
//                });
//
//                button_capNhat.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // hien thong tin len cac edittext
//                        thuThu1.setId(edt_maTT.getText().toString());
//                        thuThu1.setMatKhau(edt_matKhau.getText().toString());
//                        thuThu1.setHoTen(edt_hotenTT.getText().toString());
//
//                        // kiem tra va cap nhat
//                        if (edt_maTT.getText().length() == 0 || edt_matKhau.getText().length() == 0 || edt_hotenTT.getText().length() == 0){
//                            Toast.makeText(context, "Khong duoc de trong thong tin", Toast.LENGTH_SHORT).show();
//                        }else {
//                            if (thuThuDAO.updatePass(thuThu1) > 0){
//                                Toast.makeText(context, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
//                                notifyDataSetChanged();
//                                dialog.cancel();
//                            }else {
//                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                    }
//                });
//
//                button_xoa.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                        builder.setTitle("Xoa thu thu");
//                        builder.setMessage("Ban chac chan muon xoa");
//                        builder.setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog1, int which) {
//                                if (thuThuDAO.delete(thuThu1.getId()) > 0){
//                                    Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
//                                    notifyDataSetChanged();
//                                    dialog.dismiss();
//                                }else {
//                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                        builder.setNegativeButton("Huy",null);
//                        AlertDialog alertDialog = builder.create();
//                        builder.show();
//
//                    }
//                });
//
//                dialog.show();
//            }
//        });

        return convertView;
    }
}
