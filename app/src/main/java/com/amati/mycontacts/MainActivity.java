package com.amati.mycontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHelper myDBHelper = new MyDBHelper(this);

//        myDBHelper.addDatabase("Aman", "7056222557");
//        myDBHelper.addDatabase("Mummy", "9582410759");

//        ContactModel cm = new ContactModel();
//        cm.id = 4;
//        cm.name = "Shivam";
//        cm.number = "7070262323";
//
//        myDBHelper.updateData(cm);

        myDBHelper.deleteData(3);

        ArrayList<ContactModel> arrayList = myDBHelper.fetchData();

        for (int i = 0; i < arrayList.size(); i++) {
            Log.d("CONTACTS", "id: " + arrayList.get(i).id+ " name: "+arrayList.get(i).name + " number: " + arrayList.get(i).number);
        }


    }
}