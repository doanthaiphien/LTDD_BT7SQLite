package com.example.bt10sqlite.Control;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt10sqlite.ListComputer;
import com.example.bt10sqlite.Model.Computer;
import com.example.bt10sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class ComputerAdapter extends RecyclerView.Adapter<ComputerAdapter.ViewHolder> {
    //Dữ liệu hiện thị là danh sách sinh viên
    private List<Computer> mComputer246;
    // Lưu Context để dễ dàng truy cập
    private Context mContext246;

    public ComputerAdapter(List<Computer> computers, Context mContext) {
        this.mComputer246 = computers;
        this.mContext246 = mContext;
    }

    @NonNull
    @Override
    public ComputerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_computeritem, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComputerAdapter.ViewHolder holder, int position) {
        Computer computer =mComputer246.get(position);
        holder.studentname246.setText(computer.getIdComputer());
        holder.birthyear246.setText(computer.getNameComputer());
    }

    @Override
    public int getItemCount() {
        if (mComputer246!=null)
        return mComputer246.size();
        else return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview246;
        public TextView studentname246;
        public TextView birthyear246;
        public Button btnDel246,btnEdit246;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview246 = itemView;
            studentname246 = itemView.findViewById(R.id.studentname246);
            birthyear246 = itemView.findViewById(R.id.birthyear246);
            btnDel246 = itemView.findViewById(R.id.btnDel246);
            btnEdit246 = itemview246.findViewById(R.id.btnEdit246);


            //Xử lý khi nút Chi tiết được bấm
            btnDel246.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alertDiaLog = new AlertDialog.Builder(view.getContext());
                    alertDiaLog.setTitle("Thông báo");
                    alertDiaLog.setMessage("Bạn có muốn xóa "+studentname246.getText().toString().trim()+" ?"    );
                    alertDiaLog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ListComputer.db246.QueryData("delete from Computer where idComputer ='"+studentname246.getText().toString().trim()+"'");
                            ListComputer.computers246 = new ArrayList<Computer>();
                            Cursor cursor = ListComputer.db246.GetData("Select * from Computer where idCategory = '"+ListComputer.idTruyen246.toString().trim() +"'");
                            while (cursor.moveToNext()){
                                @SuppressLint("Range") String idC =cursor.getString(cursor.getColumnIndex("idComputer"));
                                @SuppressLint("Range") String nameC = cursor.getString(cursor.getColumnIndex("nameComputer"));
                                @SuppressLint("Range") String idCategory = cursor.getString(cursor.getColumnIndex("idCategory"));
                                System.out.println(idC+" "+nameC+" "+idCategory);
                                ListComputer.computers246.add(new Computer(idC,nameC,idCategory));
//            computers.add(new Computer(cursor.getString(cursor.getColumnIndex("idComputer")),cursor.getString(cursor.getColumnIndex("nameComputer")),cursor.getString(cursor.getColumnIndex("idCategory"))));
                            }
                            ListComputer.adapter246 = new ComputerAdapter(ListComputer.computers246, view.getContext());
                            GridLayoutManager linearLayoutManager = new GridLayoutManager(view.getContext(), 1);
                            ListComputer.recyclerView246.setAdapter(ListComputer.adapter246);
                            ListComputer.recyclerView246.setLayoutManager(linearLayoutManager);
                        }
                    });
                    alertDiaLog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alertDiaLog.show();
                }
            });
            btnEdit246.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(view.getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialogcomputercustom);
                    dialog.show();
                    TextView tv1 = (TextView) dialog.findViewById(R.id.isIDC246);
                    TextView tv2 = (TextView) dialog.findViewById(R.id.isNameC246);
                    TextView tv3 = (TextView) dialog.findViewById(R.id.isIDCate246);
                    tv3.setVisibility(View.GONE);
                    Button btok = (Button) dialog.findViewById(R.id.btn_okC246);
                    Button btcancel = (Button) dialog.findViewById(R.id.btn_cancelC246);

                    btok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ListComputer.db246.QueryData("update Computer set idComputer = '"+tv1.getText().toString().trim()+ "',nameComputer='"+ tv2.getText().toString().trim()+ "'  where idComputer ='"+studentname246.getText().toString().trim()+"'");
                            ListComputer.computers246 = new ArrayList<Computer>();
                            Cursor cursor = ListComputer.db246.GetData("Select * from Computer where idCategory = '"+ListComputer.idTruyen246.toString().trim() +"'");
                            while (cursor.moveToNext()){
                                @SuppressLint("Range") String idC =cursor.getString(cursor.getColumnIndex("idComputer"));
                                @SuppressLint("Range") String nameC = cursor.getString(cursor.getColumnIndex("nameComputer"));
                                @SuppressLint("Range") String idCategory = cursor.getString(cursor.getColumnIndex("idCategory"));
                                System.out.println(idC+" "+nameC+" "+idCategory);
                                ListComputer.computers246.add(new Computer(idC,nameC,idCategory));
//            computers.add(new Computer(cursor.getString(cursor.getColumnIndex("idComputer")),cursor.getString(cursor.getColumnIndex("nameComputer")),cursor.getString(cursor.getColumnIndex("idCategory"))));
                            }
                            ListComputer.adapter246 = new ComputerAdapter(ListComputer.computers246, view.getContext());
                            GridLayoutManager linearLayoutManager = new GridLayoutManager(view.getContext(), 1);
                            ListComputer.recyclerView246.setAdapter(ListComputer.adapter246);
                            ListComputer.recyclerView246.setLayoutManager(linearLayoutManager);
                            dialog.dismiss();

                        }
                    });
                    btcancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                }
            });
        }
    }


}
