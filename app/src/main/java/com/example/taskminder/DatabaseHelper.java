package com.example.taskminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "taskminder.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DUE_DATE = "dueDate";
    public static final String COLUMN_IS_COMPLETED = "isCompleted";
    public static final String COLUMN_COMPLETE_PERCENTAGE = "completePercentage";
    public static final String COLUMN_CREATED_AT = "createdAt";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_TASKS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_DUE_DATE + " INTEGER, " + // Store dates as long
                    COLUMN_IS_COMPLETED + " INTEGER, " + // Store boolean as integer (0 or 1)
                    COLUMN_COMPLETE_PERCENTAGE + " INTEGER, " +
                    COLUMN_CREATED_AT + " INTEGER" + // Store dates as long
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }
}
