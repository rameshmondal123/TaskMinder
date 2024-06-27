package com.example.taskminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskService {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_TITLE,
            DatabaseHelper.COLUMN_DESCRIPTION,
            DatabaseHelper.COLUMN_DUE_DATE,
            DatabaseHelper.COLUMN_IS_COMPLETED,
            DatabaseHelper.COLUMN_COMPLETE_PERCENTAGE,
            DatabaseHelper.COLUMN_CREATED_AT
    };

    public TaskService(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public TaskModel createTask(String title, String description, Date dueDate) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE, title);
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
        values.put(DatabaseHelper.COLUMN_DUE_DATE, dueDate.getTime());
        values.put(DatabaseHelper.COLUMN_IS_COMPLETED, 0);
        values.put(DatabaseHelper.COLUMN_COMPLETE_PERCENTAGE, 0);
        values.put(DatabaseHelper.COLUMN_CREATED_AT, new Date().getTime());

        long insertId = database.insert(DatabaseHelper.TABLE_TASKS, null, values);

        Cursor cursor = database.query(DatabaseHelper.TABLE_TASKS,
                allColumns, DatabaseHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        TaskModel newTask = cursorToTask(cursor);
        cursor.close();
        return newTask;
    }

    public void deleteTask(TaskModel task) {
        int id = task.getId();
        database.delete(DatabaseHelper.TABLE_TASKS, DatabaseHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<TaskModel> getAllTasks() {
        List<TaskModel> tasks = new ArrayList<>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_TASKS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TaskModel task = cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return tasks;
    }

    public TaskModel getTaskById(int id) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_TASKS,
                allColumns, DatabaseHelper.COLUMN_ID + " = " + id, null,
                null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        TaskModel task = cursorToTask(cursor);
        cursor.close();
        return task;
    }
    public int updateTask(TaskModel task) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE, task.getTitle());
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, task.getDescription());
        values.put(DatabaseHelper.COLUMN_DUE_DATE, task.getDueDate().getTime());
        values.put(DatabaseHelper.COLUMN_IS_COMPLETED, task.isCompleted() ? 1 : 0);
        values.put(DatabaseHelper.COLUMN_COMPLETE_PERCENTAGE, task.getCompletePercentage());

        return database.update(DatabaseHelper.TABLE_TASKS, values, DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(task.getId())});
    }

    private TaskModel cursorToTask(Cursor cursor) {
        TaskModel task = new TaskModel(
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION)),
                new Date(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DUE_DATE))));
        task.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)));
        task.setCompleted(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_IS_COMPLETED)) > 0);
        task.setCompletePercentage(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_COMPLETE_PERCENTAGE)));
        task.setCreatedAt(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CREATED_AT))));
        return task;
    }
}
