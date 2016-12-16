package com.app.vpgroup.ghinhocungmemopad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ThemMoiActivity extends AppCompatActivity {
    EditText edtTitle, edtContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_moi);
        AddControl();



    }

    private void AddControl(){
        edtTitle = (EditText) findViewById(R.id.txtTitle);
        edtContent = (EditText) findViewById(R.id.contentNew);
    }
}
