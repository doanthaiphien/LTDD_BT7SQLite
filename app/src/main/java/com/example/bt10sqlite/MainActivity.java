package com.example.bt10sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bt10sqlite.Control.Database;
import com.example.bt10sqlite.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Database db246;
    List<Category> categories246 = new ArrayList<Category>();
    ArrayAdapter<Category> lopArrayAdapter246;
    boolean kt246=true;
    ListView lvCategory246;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db246 = new Database(this,"COMPUTER.sqlite",null,1);
        db246.QueryData("create table IF NOT EXISTS Category(idCategory VARCHAR(100),nameCategory nVARCHAR(100))");
        db246.QueryData("create table IF NOT EXISTS Computer(idComputer VARCHAR(100),nameComputer VARCHAR(100),idCategory VARCHAR(100))");

        Cursor cursor = db246.GetData("select * from Category");
        if (cursor.getCount()==0){
           db246.insertCa("Công nghệ số","Công nghệ thông tin");
           db246.insertCa("Điện - Điện tử","Tự động hóa");
           db246.insertCa("Cơ khí","Ô tô");
        }
        cursor = db246.GetData("select * from Computer");
        if (cursor.getCount()==0){
            db246.insertC("Đoàn Thái Phiên","2050531200246","Công nghệ số");
            db246.insertC("Phan Quang Nhã","20505312002","Điện - Điện tử");
            db246.insertC("Lê Văn Long","20505312002","Cơ khí");
        }

         lvCategory246= (ListView) findViewById(R.id.listviewCategory246);

        getDataCategory();
        lvCategory246.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity.this, ListComputer.class);
                    intent.putExtra("idCategory", categories246.get(i).getIdCategory());
                    System.out.println(categories246.get(i).getIdCategory());
                    kt246=true;
                    startActivity(intent);
            }
        });

        lvCategory246.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int j=i;
                AlertDialog.Builder alertDiaLog = new AlertDialog.Builder(MainActivity.this);
                alertDiaLog.setTitle("Thông báo");
                alertDiaLog.setMessage("Bạn có muốn xóa "+categories246.get(i).getIdCategory()+" ?"    );
                alertDiaLog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db246.QueryData("delete from Category where idCategory ='"+categories246.get(j).getIdCategory()+"'");
                        getDataCategory();
                        lopArrayAdapter246.notifyDataSetChanged();
                    }
                });
                alertDiaLog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDiaLog.show();
                return true;
            }
        });
        Button button = (Button) findViewById(R.id.btn_themCategory246);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialogcategorycustom);
                dialog.show();
                TextView tv1 = (TextView) dialog.findViewById(R.id.isIDCa246);
                TextView tv2 = (TextView) dialog.findViewById(R.id.isNameCa246);
                Button btok = (Button) dialog.findViewById(R.id.btn_okCa246);
                Button btcancel = (Button) dialog.findViewById(R.id.btn_cancelCa246);
                btok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db246.insertCa(tv1.getText().toString().trim(),tv2.getText().toString().trim());
                        dialog.dismiss();
                        getDataCategory();
                        lopArrayAdapter246.notifyDataSetChanged();
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

    public void getDataCategory() {
        categories246 = new ArrayList<Category>();
        Cursor cursor = db246.GetData("select * from Category");
        System.out.println("ABC " + cursor.getCount());
        if (cursor.getCount()>0){
            System.out.println("abc");
            while (cursor.moveToNext()){
                @SuppressLint("Range") String idCa = cursor.getString(cursor.getColumnIndex("idCategory"));
                @SuppressLint("Range") String nameCa = cursor.getString(cursor.getColumnIndex("nameCategory"));
                categories246.add(new Category(idCa,nameCa));
            }
        }
        lopArrayAdapter246 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,categories246);
        lvCategory246.setAdapter(lopArrayAdapter246);
        lopArrayAdapter246.notifyDataSetChanged();
    }
}