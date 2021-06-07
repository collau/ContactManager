package com.fishnco.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.fishnco.contactmanager.data.DatabaseHandler;
import com.fishnco.contactmanager.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

        //create contact object first
        Contact jeremy = new Contact();
        jeremy.setName("Jeremy");
        jeremy.setPhoneNo("9985747374");
//        db.addContact(jeremy);

        Contact jason = new Contact();
        jason.setName("Jason");
        jason.setPhoneNo("0877744");
//        db.addContact(jason);

        List<Contact> contactList = db.getAllContacts();

        for (Contact contact : contactList) {
            Log.e("MainActivity", "onCreate: " + contact.getId());
            Log.e("MainActivity", "onCreate: " + contact.getName());
            Log.e("MainActivity", "onCreate: " + contact.getPhoneNo());
        }

        /*
        Contact c = db.getContact(1);
        c.setName("Jeremy");
        c.setPhoneNo("99678254");
        */

//        int updateRow = db.updateContact(c);

        contactList = db.getAllContacts();

        for (Contact contact : contactList) {
            Log.e("MainActivity", "onCreate: " + contact.getId());
            Log.e("MainActivity", "onCreate: " + contact.getName());
            Log.e("MainActivity", "onCreate: " + contact.getPhoneNo());
        }

//        db.deleteContact(c);

        contactList = db.getAllContacts();

        for (Contact contact : contactList) {
            Log.e("MainActivity", "onCreate: " + contact.getId());
            Log.e("MainActivity", "onCreate: " + contact.getName());
            Log.e("MainActivity", "onCreate: " + contact.getPhoneNo());
        }

        Log.e("Count", "onCreate: " + db.getCount());
    }
}