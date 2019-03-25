package com.example.abc.waitinglistmanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "students_db";
    private static final String TABLE_NAME = "students";
    private static final String COURSE_TABLE = "courses";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "COURSE";
    private static final String COL_4 = "PRIORITY";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + "ID" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME" + " TEXT,"
                + "COURSE" + " TEXT,"
                + "PRIORITY" + " TEXT"
                + ")");
        db.execSQL("CREATE TABLE " + COURSE_TABLE + "("
                + "ID" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "COURSE" + " TEXT"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+COURSE_TABLE);
        onCreate(db);
    }

    public boolean insertStudent(String name, String course, String priority)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("COURSE", course);
        contentValues.put("PRIORITY", priority);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean insertCourse(String course)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("COURSE", course);

        long result = db.insert(COURSE_TABLE, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(String selectedCourse)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE COURSE = '"+selectedCourse+"'", null);
        return cursor;
    }

    public Cursor getCourse()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+COURSE_TABLE, null);
        return cursor;
    }

    public Cursor getItemId(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT ID FROM "+ TABLE_NAME +" WHERE NAME ='"+ name+"'", null );
        return cursor;
    }

    public void updateStudent(int id, String name, String course, String priority)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET NAME = '"+name+"', COURSE = '"+course+"', PRIORITY = '"+priority+"' WHERE ID = '"+id+"'" );
    }

    public void deleteStudent(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE ID = '"+id+"'");
    }

    public Cursor getDataAt(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE ID = '" + id+ "'" , null );
        return cursor;
    }

}
