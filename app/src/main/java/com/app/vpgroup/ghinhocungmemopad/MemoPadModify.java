package com.app.vpgroup.ghinhocungmemopad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MemoPadModify {
    private DBHelper dbHelper;

    public MemoPadModify(Context context) {
        dbHelper = new DBHelper(context);
    }

    //Pt Thêm Ghi chú
    public void insert(MemoPad memoPad){
        //kết nối với database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //chứa dữ liệu cần truyền
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_TITLE, memoPad.getTitle());
        values.put(DBHelper.KEY_CONTENT, memoPad.getContent());

        db.insert(DBHelper.TABLE_NAME, null, values);
        db.close();
    }

    //Pt sửa Ghi Chú
    public void update(MemoPad memoPad){
        //kết nối với database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //chứa dữ liệu cần truyền
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_TITLE, memoPad.getTitle());
        values.put(DBHelper.KEY_CONTENT, memoPad.getContent());

        db.update(DBHelper.TABLE_NAME, values, DBHelper.KEY_ID +  "=?", new String[]{String.valueOf(memoPad.getId())});
        db.close();
    }

    //Pt xóa ghi chú
    public void delete(int memopad_id){
        //kết nối với database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(DBHelper.TABLE_NAME, DBHelper.KEY_ID + "=?",new String[]{String.valueOf(memopad_id)});
        db.close();
    }

    //lấy dữ liệ trong bảng
    public Cursor GetMemoPadList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_NAME, new String[]{DBHelper.KEY_ID, DBHelper.KEY_TITLE, DBHelper.KEY_CONTENT}, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();

        }
        return cursor;
    }

    //lay 1 du lieu
    public MemoPad fecthMemoPadByID(int memopad_id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_NAME, new String[]{DBHelper.KEY_ID, DBHelper.KEY_TITLE, DBHelper.KEY_CONTENT}, DBHelper.KEY_ID + "=?", new String[]{String.valueOf(DBHelper.KEY_ID)}, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return new MemoPad(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
    }
}
