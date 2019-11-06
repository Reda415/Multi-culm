package com.example.multiculm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper mydb;

    Button bttnshow1;
    Button bttnshowall;
    Button bttnadd;

    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextStreet;
    EditText editTextPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);

        editTextName = (EditText)findViewById(R.id.editName);
        editTextPhone = (EditText)findViewById(R.id.editPhone);
        editTextEmail = (EditText)findViewById(R.id.editEmail);
        editTextStreet = (EditText)findViewById(R.id.editStreet);
        editTextPlace = (EditText)findViewById(R.id.editPlace);

        bttnadd = (Button) findViewById(R.id.bttnAdd);
        bttnshow1 = (Button) findViewById(R.id.bttnShow1);
        bttnshowall = (Button) findViewById(R.id.bttnShowAll);

        bttnadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //remove the following toast...
                Toast.makeText(getApplicationContext(),
                        "bttnOnClick Pressed", Toast.LENGTH_SHORT).show();

                String getName = editTextName.getText().toString();
                String getPhone = editTextPhone.getText().toString();
                String getEmail = editTextEmail.getText().toString();
                String getStreet = editTextStreet.getText().toString();
                String getPlace = editTextPlace.getText().toString();

                if (editTextName.getText().toString().isEmpty() ||editTextPhone.getText().toString().isEmpty()||
                        editTextEmail.getText().toString().isEmpty() ||editTextStreet.getText().toString().isEmpty()||
                        editTextPlace.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "bttnOnClick Pressed", Toast.LENGTH_SHORT).show();

                }
                if(getName.length() != 0 && getPhone.length() != 0 && getEmail.length() != 0&&  getStreet.length() != 0 && getPlace.length() != 0 ){
                    AddData(getName,getPhone, getEmail,getStreet,getPlace);
                    editTextPlace.setText("");
                    editTextStreet.setText("");
                    editTextEmail.setText("");
                    editTextPhone.setText("");
                    editTextName.setText("");
                } else
                    Toast.makeText(getApplicationContext(), "DID NOT insert to db :-(", Toast.LENGTH_SHORT).show();
            }
        });

        bttnshow1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("georgeLog", "clicked on fetch");
                Cursor getData=mydb.getData(1); //specific record (id=1)

                if (editTextName.getText().toString().isEmpty() ||editTextPhone.getText().toString().isEmpty()||
                        editTextEmail.getText().toString().isEmpty() ||editTextStreet.getText().toString().isEmpty()||
                        editTextPlace.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "bttnOnClick Pressed", Toast.LENGTH_SHORT).show();

                }
                else if (getData.moveToNext()) {// data?
                    Log.v("georgeLog", "data found in DB...");
                    String dName = getData.getString(getData.getColumnIndex("name"));
                    String dPhone = getData.getString(getData.getColumnIndex("phone"));
                    String dEmail = getData.getString(getData.getColumnIndex("email"));
                    String dStreet = getData.getString(getData.getColumnIndex("Street"));
                    String dPlace = getData.getString(getData.getColumnIndex("Place"));
                    Toast.makeText(getApplicationContext(),
                            "rec: " + dName + ", " + dPhone + ", " + dEmail+ ", " + dStreet+ ", " + dPlace, Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),
                            "did not get any data...:-(", Toast.LENGTH_LONG).show();
                getData.close();
            }
        });

        bttnshowall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewListContents.class);
                startActivity(intent);
            }
        });
    }
    public void AddData(String name,String phone, String email, String street, String place ) {
        boolean insertData = mydb.insertContact(name, phone, email, street, place);

        if (insertData == true) {
            Toast.makeText(MainActivity.this, "Successfully Entered Data!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }}
