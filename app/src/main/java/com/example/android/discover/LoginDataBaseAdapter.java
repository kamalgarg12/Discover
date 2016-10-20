package com.example.android.discover;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KAMAL on 20-10-2016.
 */

public class LoginDataBaseAdapter {

    private static final String DATABASE_NAME = "FreeWi";
    private static final int DATABASE_VERSION = 1;

    public static final String MAIN_TABLE = "mainpass";
    public static final String CONTACT_TABLE = "contactpass";

    public static final String MAC_COLUMN = "mac";
    public static final String CONTACT_COLUMN = "contact";
    public static final String MAIN_PASSWORD = "mpassword";
    public static final String MAIN_HINT = "mhint";
    public static final String CONTACT_PASSWORD = "cpassword";
    public static final String CONTACT_HINT = "chint";


    public static final String CREATE_CONTACT_TABLE = "CREATE TABLE IF NOT EXISTS "
            + CONTACT_TABLE + "(" + MAC_COLUMN + " INTEGER PRIMARY KEY autoincrement, "
            + CONTACT_COLUMN + " INT autoincrement, "
            + CONTACT_PASSWORD + " TEXT, "
            + CONTACT_HINT + " TEXT, )";

    public static final String CREATE_MAIN_TABLE = "CREATE TABLE IF NOT EXISTS"
            + MAIN_TABLE + "(" + MAIN_PASSWORD + " TEXT PRIMARY KEY,"
            + MAIN_HINT + "TEXT,)";

    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String password, String repassword, String securityhint) {
        ContentValues newValues = new ContentValues();
        newValues.put("CONTACT_PASSWORD", password);
       // newValues.put("REPASSWORD", repassword);
        newValues.put("CONTACT_HINT", securityhint);

        db.insert("contactpass", null, newValues);
    }
    public void insertEntry1(String password, String repassword, String securityhint) {
        ContentValues newValues = new ContentValues();
        newValues.put("MAIN_PASSWORD", password);
        // newValues.put("REPASSWORD", repassword);
        newValues.put("MAIN_HINT", securityhint);

        db.insert("mainpass", null, newValues);
    }

    public int deleteEntry(String password) {
        String where = "PASSWORD=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where, new String[]{password});
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String password) {
        Cursor cursor = db.query("LOGIN", null, " PASSWORD=?", new String[]{password}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String repassword = cursor.getString(cursor.getColumnIndex("REPASSWORD"));
        cursor.close();
        return repassword;
    }

    public String getAllTags(String a) {

        Cursor c = db.rawQuery("SELECT * FROM " + "LOGIN" + " where SECURITYHINT = '" + a + "'", null);
        String str = null;
        if (c.moveToFirst()) {
            do {
                str = c.getString(c.getColumnIndex("PASSWORD"));
            } while (c.moveToNext());
        }
        return str;
    }

    public void updateEntry(String password, String repassword) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("PASSWORD", password);
        updatedValues.put("REPASSWORD", repassword);
        updatedValues.put("SECURITYHINT", repassword);

        String where = "USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[]{password});
    }
}