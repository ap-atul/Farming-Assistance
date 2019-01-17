package com.project.agriculturalapp.utilities;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project.agriculturalapp.modals.ItemHealthCard;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class SqliteHelper  extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "soil_lab.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static String DB_PATH = "/data/data/com.project.agriculturalapp/databases";

    private SQLiteDatabase mDataBase;
    private Context ctx;

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DB_PATH = context.getDatabasePath(DATABASE_NAME).getPath();
        this.ctx = context;
    }

    public ArrayList<String> getDistinctStates(){
        ArrayList<String> list = new ArrayList<String>();
        String selectQuery = "SELECT distinct Column3 FROM 'Table 1'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        list.add("Select State");

        if (cursor.move(3)) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public ArrayList<String> getDistricts(String state){
        ArrayList<String> list = new ArrayList<String>();
        String selectQuery = "SELECT  distinct Column4 FROM 'Table 1' where Column3='"+state+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        list.add("Select District");

        if (cursor.moveToFirst()) {
            do {
                Log.v("district",cursor.getString(0));
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public ArrayList<ItemHealthCard> getHealthCard(String state, String district){
        ArrayList<ItemHealthCard> list = new ArrayList<>();
        String selectQuery = "SELECT Column2,Column5 FROM 'Table 1' where Column3='"+state+"' and Column4='"+district+"'" ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ItemHealthCard item=new ItemHealthCard();
                item.setId(cursor.getString(0));
                item.setName(cursor.getString(1));

                list.add(item);
            } while (cursor.moveToNext());
        }

        return list;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private void copyDataBase() throws IOException {
        InputStream mInput =  ctx.getAssets().open(DATABASE_NAME);
        String outfileName = DB_PATH;
        OutputStream mOutput = new FileOutputStream(outfileName);
        byte[] buffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(buffer))>0) {
            mOutput.write(buffer, 0, mLength);
        }
        mOutput.flush();
        mInput.close();
        mOutput.close();
    }

    public void createDataBase() throws IOException {
        if(!checkDataBase()){
            this.getReadableDatabase();
            copyDataBase();
            this.close();
        }
    }

    public boolean checkDataBase() {
        File DbFile = new File(DB_PATH + DATABASE_NAME);
        return DbFile.exists();
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    public synchronized void close(){
        if(mDataBase != null)
            mDataBase.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }
}
