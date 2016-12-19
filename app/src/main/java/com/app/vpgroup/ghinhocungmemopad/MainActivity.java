package com.app.vpgroup.ghinhocungmemopad;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ListView lv;
    ArrayList<MemoPad> mangMemoPad;
    static MemoPadAdapter adapter = null;
    static MemoPadModify memoPadModify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddControl();
        display();

        Intent myIntent = getIntent();

        Bundle myBundle = myIntent.getBundleExtra("memopad");
        if(myBundle != null){
            String title  = myBundle.getString("title");
            String content = myBundle.getString("content");

            MemoPad memoPad = new MemoPad(title, content);
            memoPadModify.insert(memoPad);
            display();
            Toast.makeText(this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Lỗi thêm!", Toast.LENGTH_SHORT).show();
        }
        registerForContextMenu(lv);
    }

    public void display(){
        adapter = new MemoPadAdapter(MainActivity.this, memoPadModify.GetMemoPadList(), true);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.btnAdd){
            Intent intent = new Intent(MainActivity.this, ThemMoiActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void AddControl() {
        lv = (ListView) findViewById(R.id.listview);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                Cursor cursor = (Cursor) lv.getItemAtPosition(i);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                bundle.putString("title", title);
                bundle.putString("content", content);
                intent.putExtra("xem", bundle);
                startActivity(intent);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Cursor cursor = (Cursor) lv.getItemAtPosition(i);
                final int id = cursor.getInt(0);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xác nhận xóa!");
                builder.setMessage("bạn có muốn xóa ghi chú này không?");

                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        memoPadModify.delete(id);
                        display();
                        Toast.makeText(MainActivity.this, "xóa thành công!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });

                builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();

                return false;
            }
        });
        memoPadModify = new MemoPadModify(this);
        mangMemoPad = new ArrayList<MemoPad>();
    }

}
