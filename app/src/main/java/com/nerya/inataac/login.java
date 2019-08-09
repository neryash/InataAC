package com.nerya.inataac;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class login extends AppCompatActivity {
    EditText passl, namel;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        passl = findViewById(R.id.passl);
        namel = findViewById(R.id.namel);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(namel.getText().toString(), passl.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e == null) {
                            Toast.makeText(login.this, "logged in successfully!", Toast.LENGTH_SHORT);
                            Intent intent = new Intent(login.this, social.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT);
                        }
                    }
                });
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}