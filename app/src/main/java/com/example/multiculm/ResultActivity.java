package com.example.multiculm;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    int value;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mydb = new DBHelper(this);
        ListView resList= (ListView) findViewById(R.id.listView);

        final ArrayList<String> getData
                =getIntent().getExtras().getStringArrayList("fetchAll");
        Log.v("georgeLog", "getData created");
        for (String a:getData)
            Log.v("georgeLog dataToString:", a.toString());

        // we have to populate this...
        CustomListView newList = new CustomListView(this,getData);



        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        getData);
        Log.v("georgeLog", "arrayAdapter created");
        resList.setAdapter(arrayAdapter);
        Log.v("georgeLog:", "resList called");

        // make the list clickable



        resList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final int item = position;
                new AlertDialog.Builder(ResultActivity.this)
                        .setIcon(android.R.drawable.ic_menu_delete).setTitle("Remove")
                        .setMessage("Are You Sure You Want To Remove It?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mydb.deleteContact(1);
                        getData.remove(item);
                        arrayAdapter.notifyDataSetChanged();
                    }
                })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }
}