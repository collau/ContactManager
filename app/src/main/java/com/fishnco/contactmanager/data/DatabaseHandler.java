package com.fishnco.contactmanager.data;

/*
 * Create, Read, Update, Delete (CRUD)
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.fishnco.contactmanager.util.Utility;

public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * Constructor for SQLite Database Handler
     * @param   context
     * @param   name    name of database created
     * @param   factory
     * @param   version
     */
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHandler(Context context) {
        super(context, Utility.DATABASE_NAME, null, Utility.DATABASE_VERSION);
    }

    //We create our table
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL - Structured Query Language

        /*
         * CREATE TABLE contacts (
         *      id INTEGER PRIMARY KEY,
         *      name TEXT,
         *      contacts TEXT
         * );
         */
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Utility.TABLE_NAME + "("
                + Utility.KEY_ID + " INTEGER PRIMARY KEY, "
                + Utility.KEY_NAME + " TEXT, "
                + Utility.KEY_PHONENO + " TEXT" + ")";

        //Creating our table
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Delete whole table and create again
        String DROP_TABLE = "DROP TABLE IF EXISTS " + Utility.DATABASE_NAME;
        db.execSQL(DROP_TABLE);

        //Create a table again
        onCreate(db);
    }
}
