package com.example.task9r;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class crudoperation extends SQLiteOpenHelper {
    public static final String DB_NAME = "crud";
    public static final String TB_NAME = "auto";
    public static final String AUTO_ID = "Id";
    public static final String AUTO_NAME = "Name";
    public static final String MODEL = "Model";
    public static final String SHOWTYPE = "Showtype";
    public static final String DESC = "Description";

    public crudoperation(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TB_NAME + "(Id text primary key,Name text,Model int,Showtype text,Description int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(db);
    }

    public boolean insertData( String id,String name, String model,String showtype, String desc) {
        SQLiteDatabase sdb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(AUTO_ID, id);
        cv.put(AUTO_NAME, name);
        cv.put(MODEL, model);
        cv.put(SHOWTYPE, showtype);
        cv.put(DESC,desc);
        long result = sdb.insert(TB_NAME, null, cv);
        if (result == -1) {
            return false;
        } else{
            return true;
        }

    }
    public boolean updatetData(String id,String name,String model,String showtype,String desc){
        SQLiteDatabase sdb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(AUTO_ID,id);
        cv.put(AUTO_NAME,name);
        cv.put(MODEL,model);
        cv.put(SHOWTYPE,showtype);
        cv.put(DESC,desc);
        sdb.update(TB_NAME,cv,"Id = ?",new String[]{id});
        return true;
    }
    public Cursor readData(){
        SQLiteDatabase sdb = this.getReadableDatabase();
        String query = ("select * from " + TB_NAME);
        Cursor result = sdb.rawQuery(query,null);
        return result;
    }
    public Integer deleteData(String id){
        SQLiteDatabase sdb = this.getWritableDatabase();
        return sdb.delete(TB_NAME,"Id = ?",new String[]{id});
    }

}