package com.example.task9r;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    crudoperation mydb;
    Button b1, b2, b3, b4,b6;
    EditText ed1, ed2, ed3, ed4;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new crudoperation(this);
        ed1 = findViewById(R.id.editText6);
        //ed1.setInputType(InputType.TYPE_CLASS_NUMBER);
        ed2 = findViewById(R.id.editText7);
        ed2.setInputType(InputType.TYPE_CLASS_TEXT);
        ed3 = findViewById(R.id.editText8);
        ed3.setInputType(InputType.TYPE_CLASS_NUMBER);
        ed4 = findViewById(R.id.editText9);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b6 = findViewById(R.id.button6);
       // b5 = findViewById(R.id.button5);
        radioGroup = findViewById(R.id.radioG);

        ed4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer n = Integer.parseInt(ed3.getText().toString());
                Integer price = 100 * n;
                ed4.setText(Integer.toString(price));

            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                boolean res = mydb.insertData(ed1.getText().toString(),ed2.getText().toString(), ed3.getText().toString(),radioButton.getText().toString(),
                        ed4.getText().toString());
                if (res == true) {
                    Toast.makeText(MainActivity.this, "Booked", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Booking unsuccessfull", Toast.LENGTH_LONG).show();
                }
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
                //ed1.setHint("enter id");
                //ed2.setHint("enter username ");
                //ed3.setHint("enter movie name");
                //ed4.setHint(" enter number of seats");


            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                boolean res = mydb.updatetData(ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString(),radioButton.getText().toString(),
                        ed4.getText().toString());
                if (res == true) {
                    Toast.makeText(MainActivity.this, "updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "not updated", Toast.LENGTH_LONG).show();
                }
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cursor result = mydb.readData();

                    if (result.getCount() == 0) {
                        showMessage("error", "no bookings made");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (result.moveToNext()) {
                        buffer.append("user ID :" + result.getString(0) + "\n");
                        buffer.append("Movie name :" + result.getString(1) + "\n");
                        buffer.append("Seats :" + result.getString(2) + "\n");
                        buffer.append("Showtype :" + result.getString(3) + "\n");
                        buffer.append("total Price :" + result.getString(4) + "\n\n\n");
                    }
                    showMessage("data", buffer.toString());
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                    ed4.setText("");
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,"no bookings so far",Toast.LENGTH_LONG).show();
                }

            }
        });
        /*b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = mydb.readData();
                if (result.getCount() == 0) {
                    showMessage("error", "no records found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()) {
                    buffer.append("id :" + result.getString(0) + "\n");
                    buffer.append("name :" + result.getString(1) + "\n");
                    buffer.append("model :" + result.getString(2) + "\n");
                    buffer.append("description :" + result.getString(3) + "\n");
                }
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"));
                i.putExtra(Intent.EXTRA_EMAIL, "bbranjitha06@gmail.com");
                i.putExtra(Intent.EXTRA_SUBJECT,"message");
                i.putExtra(Intent.EXTRA_TEXT,buffer.toString());
                startActivity(Intent.createChooser(i,""));

            }
        });*/
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer del = mydb.deleteData(ed1.getText().toString());
                if(del > 0){
                    Toast.makeText(MainActivity.this, "cancelled", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "cancellation not done", Toast.LENGTH_LONG).show();
                }
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
            }
        });
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
