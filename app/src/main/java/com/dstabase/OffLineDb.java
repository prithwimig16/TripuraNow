package com.dstabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.models.News;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Prithwi on 08/04/18.
 */

public class OffLineDb extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TripuraNow.db";
    public static final String TABLE_NAME = "news";

    public static final String COL_1 = "newsId";
    public static final String COL_2 = "headline";
    public static final String COL_3 = "content";
    public static final String COL_4 = "date";
    public static final String COL_5 = "image_url";
    public static final String COL_6 = "video_url";

    private HashMap hp;


    public OffLineDb(Context context) {
        super(context, DATABASE_NAME , null, 1);

    }

    public OffLineDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " +TABLE_NAME + " (newsId TEXT PRIMARY KEY,headline text,content text,date text,image_url text,video_url text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String newsId,String headline,String content,String date,String image_url,String video_url){
        SQLiteDatabase db=this.getWritableDatabase();
        long rows = 0;
        ContentValues cv=new ContentValues();
        cv.put(COL_1,newsId);
        cv.put(COL_2,headline);
        cv.put(COL_3,content);
        cv.put(COL_4,date);
        cv.put(COL_5,image_url);
        cv.put(COL_6,video_url);

        rows = db.insertWithOnConflict(TABLE_NAME, null, cv,SQLiteDatabase.CONFLICT_REPLACE);
        if(rows ==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getallData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }
//    public JSONObject getallData1(){
//
//        JSONObject jobj=new JSONObject();
//        SQLiteDatabase db=this.getReadableDatabase();
//        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
//        if(cursor.getCount()>0){
//            while (cursor.moveToNext()){
//                try {
//                    jobj.put("newsId",cursor.getString(0));
//                    jobj.put("headline",cursor.getString(1));
//                    jobj.put("content",cursor.getString(2));
//                    jobj.put("date",cursor.getString(3));
//                    jobj.put("image_url",cursor.getString(4));
//                    jobj.put("video_url",cursor.getString(5));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return  jobj;
//    }
}
