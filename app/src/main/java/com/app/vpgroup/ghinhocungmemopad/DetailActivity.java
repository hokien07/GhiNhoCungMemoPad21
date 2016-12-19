package com.app.vpgroup.ghinhocungmemopad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView txtTitleDetail, txtContentDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtTitleDetail = (TextView) findViewById(R.id.txtTitleDetail);
        txtContentDetail = (TextView) findViewById(R.id.txtContentDetail);

        Intent myIntent = getIntent();
        Bundle myBundle = myIntent.getBundleExtra("xem");
        txtTitleDetail.setText(myBundle.getString("title"));
        txtContentDetail.setText(myBundle.getString("content"));
    }
}
