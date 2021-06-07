package com.fishnco.contactmanager.data;

/*
 * Create, Read, Update, Delete (CRUD)
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.fishnco.contactmanager.model.Contact;
import com.fishnco.contactmanager.util.Utility;

import java.util.ArrayList;
import java.util.List;

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

    /*
     * CRUD Operations
     * Create
     * Read
     * Update
     * Delete
     */
    //Add Contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //Id will be automatically populated
        values.put(Utility.KEY_NAME, contact.getName());
        values.put(Utility.KEY_PHONENO, contact.getPhoneNo());

        //Insert to row
        db.insert(Utility.TABLE_NAME, null, values);

        //Close db connection, prevent things like memory leaks
        db.close();
    }

    //Get a contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor - iterate through and point to our database item
        Cursor cursor = db.query(Utility.TABLE_NAME,
                new String[]{Utility.KEY_ID, Utility.KEY_NAME, Utility.KEY_PHONENO},
                Utility.KEY_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNo(cursor.getString(2));

        cursor.close();

        return contact;
    }

    //Get all contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        //Select all contacts
        String selectAll = "SELECT * FROM " + Utility.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through data
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNo(cursor.getString(2));

                //add contact object to our list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return contactList;
    }

    //Update contact
    //System will return index/id of row that was updated
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utility.KEY_NAME, contact.getName());
        values.put(Utility.KEY_PHONENO, contact.getPhoneNo());

        //update the row
        //update (tablename, values, where id = ?)
        return db.update(Utility.TABLE_NAME, values, Utility.KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
    }
}
