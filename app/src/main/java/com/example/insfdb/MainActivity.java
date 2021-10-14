package com.example.insfdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText name,no;
    Button save;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText) findViewById(R.id.name);
        no=(EditText) findViewById(R.id.number);
        name=(EditText) findViewById(R.id.name);
        save=(Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
                }
                else{

                    try {
                        String name1=name.getText().toString();
                        String no1=no.getText().toString();
                        File file=new File("/sdcard/insf");
                        file.mkdirs();

                        String csv="/sdcard/insf/db.csv";
                        CSVWriter csvWriter = new CSVWriter(new FileWriter(csv, true));
                        String row[]=new String[]{name1,no1};
                        csvWriter.writeNext(row);
                        csvWriter.close();
                        Toast.makeText(MainActivity.this,"File Successfully Created",Toast.LENGTH_LONG).show();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}