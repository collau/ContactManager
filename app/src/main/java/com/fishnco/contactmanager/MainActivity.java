package com.fishnco.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fishnco.contactmanager.data.DatabaseHandler;
import com.fishnco.contactmanager.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        contactArrayList = new ArrayList<>();

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

//        db.addContact(new Contact("Greg", "91645"));
//        db.addContact(new Contact("James", "098765"));
//        db.addContact(new Contact("Helena", "40678765"));
//        db.addContact(new Contact("Carimo", "768345"));
//        db.addContact(new Contact("Silo", "3445"));
//        db.addContact(new Contact("Santos", "6665"));
//        db.addContact(new Contact("Litos", "5344"));
//        db.addContact(new Contact("Karate", "96534"));
//        db.addContact(new Contact("Guerra", "158285"));
//        db.addContact(new Contact("Gema", "78130"));

//        db.addContact(new Contact("John", "7198237"));
//        db.addContact(new Contact("Jack", "17826382"));
//        db.addContact(new Contact("Jeffrey", "187263"));
//        db.addContact(new Contact("Bryce","1982731"));
//        db.addContact(new Contact("Fernando", "1283947"));
//        db.addContact(new Contact("Vlad", "91827398"));
//        db.addContact(new Contact("Kazuhiro", "10827380123"));
//        db.addContact(new Contact("Jurickson", "98672934"));

        List<Contact> contactList = db.getAllContacts();

        for (Contact contact : contactList) {
            Log.e("MainActivity", "onCreate: " + contact.getName());
            // create arrayList
            contactArrayList.add(contact.getName());
        }

        // create array adapter
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactArrayList);

        // add to our listview
        listView.setAdapter(arrayAdapter);

        // attach eventlistener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("List", "onItemClick: " + contactArrayList.get(position));
            }
        });

        Log.e("Count", "onCreate: " + db.getCount());
    }
}