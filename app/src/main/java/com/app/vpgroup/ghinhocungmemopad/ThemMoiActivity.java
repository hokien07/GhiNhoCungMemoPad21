package com.app.vpgroup.ghinhocungmemopad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class ThemMoiActivity extends AppCompatActivity {
    EditText edtTitle, edtContent;

    int MY_REQUEST_CODE = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_moi);
        AddControl();

    }

    private void AddControl(){
        edtTitle = (EditText) findViewById(R.id.titleNew);
        edtContent = (EditText) findViewById(R.id.contentNew);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.btnSave){
            Intent i = new Intent();

            String title = edtTitle.getText().toString();
            if(title != null){
                i.putExtra("title", title);
            }else{
                Toast.makeText(this, "Title Trống!!", Toast.LENGTH_SHORT).show();
            }

            String content = edtContent.getText().toString();
            if(content != null){
                i.putExtra("content", content);
            }else {
                content = title;
                i.putExtra("content", content);
            }
            setResult(MY_REQUEST_CODE, i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
