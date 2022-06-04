package com.example.uts_10119113;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "noteit";
    private static final String DATABASE_TABLE = "primarytable";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_BODY = "body";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    public DatabaseHelper (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+ DATABASE_TABLE +"("+ KEY_ID +" INT PRIMARY KEY,"+
                KEY_TITLE +" TEXT,"+
                KEY_BODY +" TEXT,"+
                KEY_CATEGORY +" TEXT,"+
                KEY_DATE +" TEXT,"+
                KEY_TIME +" TEXT"+
                ")";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long addNote(Notes note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_TITLE, note.getTitle());
        contentValues.put(KEY_BODY, note.getBody());
        contentValues.put(KEY_CATEGORY, note.getCategory());
        contentValues.put(KEY_DATE, note.getDate());
        contentValues.put(KEY_TIME, note.getTime());

        long ID = db.insert(DATABASE_TABLE, null, contentValues);
        Log.d("Inserted!", "ID : "+ID);

        return ID;
    }

    public Notes getNote(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_TITLE, KEY_BODY,
                KEY_CATEGORY, KEY_DATE, KEY_TIME}, KEY_ID+"=?",
                new String[]{String.valueOf(id)}, null, null, null);

        if(cursor != null)
        {
            cursor.moveToFirst();
        }

        Notes note = new Notes(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return note;
    }

    public List<Notes> getNotes()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Notes> index = new ArrayList<>();

        String query = "SELECT * FROM "+DATABASE_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Notes note = new Notes();
                note.setId(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setBody(cursor.getString(2));
                note.setCategory(cursor.getString(3));
                note.setDate(cursor.getString(4));
                note.setTime(cursor.getString(5));

                index.add(note);

            }while(cursor.moveToNext());
        }

        return index;
    }
}
