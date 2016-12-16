package com.app.vpgroup.ghinhocungmemopad;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<MemoPad> mangMemoPad;
    MemoPadAdapter adapter = null;
    MemoPadModify memoPadModify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddControl();
        display();

        registerForContextMenu(lv);
    }

    public void display(){
        adapter = new MemoPadAdapter(this, memoPadModify.GetMemoPadList(), true);
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
//            final Dialog dialog = new Dialog(this);
//            dialog.setTitle("Ghi Chú Mới");
//            dialog.setContentView(R.layout.show_dialog);
//            final EditText edtTitle, edtContent;
//            Button btnThem, btnHuy;
//
//            edtTitle = (EditText) dialog.findViewById(R.id.edtTitleNew);
//            edtContent = (EditText) dialog.findViewById(R.id.edtContentNew);
//            btnThem = (Button) dialog.findViewById(R.id.btnThemNew);
//            btnHuy = (Button) dialog.findViewById(R.id.btnCapNhatNew);
//
//            btnHuy.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.dismiss();
//                }
//            });
//
//            btnThem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    MemoPad memoPad = new MemoPad(edtTitle.getText().toString(), edtContent.getText().toString());
//                    memoPadModify.insert(memoPad);
//                    display();
//                    dialog.dismiss();
//                }
//            });
//            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void AddControl() {
        lv = (ListView) findViewById(R.id.listview);
        memoPadModify = new MemoPadModify(this);
        mangMemoPad = new ArrayList<MemoPad>();
    }
    //context menu

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Cursor cursor = (Cursor) lv.getItemAtPosition(info.position);
        int id = cursor.getInt(0);

        switch (item.getItemId()){
            case R.id.action_edit:
                final Dialog dialog = new Dialog(this);
                dialog.setTitle("Ghi Chú Mới");
                dialog.setContentView(R.layout.show_dialog);
                final EditText edtTitle, edtContent;
                Button btnUpdate, btnHuy;

                edtTitle = (EditText) dialog.findViewById(R.id.edtTitleNew);
                edtContent = (EditText) dialog.findViewById(R.id.edtContentNew);
                btnUpdate = (Button) dialog.findViewById(R.id.btnThemNew);
                btnHuy = (Button) dialog.findViewById(R.id.btnCapNhatNew);

                edtTitle.setText(cursor.getString(1));
                edtContent.setText(cursor.getString(2));

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MemoPad memoPad = new MemoPad(edtTitle.getText().toString(), edtContent.getText().toString());
                        memoPadModify.update(memoPad);
                        display();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;

            case R.id.action_delete:
                memoPadModify.delete(id);
                Toast.makeText(this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                display();
                return true;
        }

        return super.onContextItemSelected(item);

    }
}
