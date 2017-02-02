package com.example.deepakrattan.internalstorage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText edtName, edtPasswd;
    private Button btnSaveInternal, btnSaveCache, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewByID
        edtName = (EditText) findViewById(R.id.edtName);
        edtPasswd = (EditText) findViewById(R.id.edtPasswd);
        btnSaveInternal = (Button) findViewById(R.id.btnSaveInternal);
        btnSaveCache = (Button) findViewById(R.id.btnSaveCache);
        btnNext = (Button) findViewById(R.id.btnNext);

        //On clicking this button the data should be store in internal storage
        btnSaveInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String name1 = name + " ";
                String password = edtPasswd.getText().toString();
                String file_name = "MyFile.txt";
                FileOutputStream fout = null;

                try {
                    fout = openFileOutput(file_name, Context.MODE_PRIVATE);
                    fout.write(name1.getBytes());
                    fout.write(password.getBytes());
                    fout.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this, "Data written to " + getFilesDir() + "/" + file_name, Toast.LENGTH_LONG).show();

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        btnSaveCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName = "MyCache";
                String data = " Welcome to Android programming";
                File file = new File(getCacheDir(), fileName);
                try {
                    FileOutputStream fout = new FileOutputStream(file);
                    fout.write(data.getBytes());
                    fout.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this, "Data written to " + getCacheDir() + "/" + fileName, Toast.LENGTH_LONG).show();

            }
        });

    }
}
