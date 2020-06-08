package com.example.appcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView ;
    Button buttonAddContact ;
    ArrayList<Contact>  list_contact ;
    ContactAdpter  adpter ;
    DbContact db ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.id_listview);
        buttonAddContact = findViewById(R.id.button_addcontact);
        db = new DbContact(this);


        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2ActivityAddContact.class);
                startActivity(intent);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contactId = (Contact) parent.getItemAtPosition(position);
                //Toast.makeText(MainActivity.this,">>>>> :"+contactId.getId(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,Main2Activity_update.class) ;
                intent.putExtra("id",contactId.getId());

                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


        list_contact = db.getAllContact() ;

        adpter = new ContactAdpter(this,R.layout.item_contact,list_contact);

        listView.setAdapter(adpter);

    }
}
