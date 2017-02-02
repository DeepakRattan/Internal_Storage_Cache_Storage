package com.example.deepakrattan.internalstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondActivity extends AppCompatActivity {
    private TextView txtName, txtPasswd;
    private Button btnReadInternal, btnReadCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //findViewByID
        txtName = (TextView) findViewById(R.id.txtName);
        txtPasswd = (TextView) findViewById(R.id.txtPasswd);
        btnReadInternal = (Button) findViewById(R.id.btnReadInternal);
        btnReadCache = (Button) findViewById(R.id.btnReadCache);

        btnReadInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream fin;
                StringBuffer buffer = new StringBuffer();
                int read;

                try {
                    fin = openFileInput("MyFile.txt");
                    while ((read = fin.read()) != -1) {
                        buffer.append((char) read);
                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String name = buffer.substring(0, buffer.indexOf(" "));
                String password = buffer.substring(buffer.indexOf(" ") + 1);

                txtName.setText(name);
                txtPasswd.setText(password);


            }
        });

        //Reading data from Cache
        btnReadCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(getCacheDir(), "MyCache");
                String line;
                StringBuffer buffer = new StringBuffer();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    while ((line = bufferedReader.readLine()) != null) {
                        buffer.append(line);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(SecondActivity.this, "Data of Cache is " + buffer.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
