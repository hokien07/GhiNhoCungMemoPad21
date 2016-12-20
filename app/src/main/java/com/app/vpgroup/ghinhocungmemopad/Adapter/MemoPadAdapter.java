package com.app.vpgroup.ghinhocungmemopad.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.app.vpgroup.ghinhocungmemopad.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MemoPadAdapter extends CursorAdapter{
    public MemoPadAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_ghi_chu,viewGroup, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTitle, txtTime;
        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtTime = (TextView) view.findViewById(R.id.txtTime);

        txtTitle.setText(cursor.getString(1));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm");
        Calendar calendar = Calendar.getInstance();
        String time = simpleDateFormat.format(calendar.getTime());
        txtTime.setText(time);
    }
}
