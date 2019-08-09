package com.nerya.inataac;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Signup extends AppCompatActivity {
    EditText passs, emails, names;
    Button signup, changelog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        passs = findViewById(R.id.passs);
        emails = findViewById(R.id.emails);
        names = findViewById(R.id.names);

        changelog = findViewById(R.id.changelog);
        signup = findViewById(R.id.signup);
        if(ParseUser.getCurrentUser() != null){
            Intent intent = new Intent(Signup.this, social.class);
            startActivity(intent);
        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emails.getText().toString().equals("") || names.getText().toString().equals("") || passs.getText().toString().equals("")) {
                    Toast.makeText(Signup.this, "ONE OF THE FIELDS ARE EMPTY!", Toast.LENGTH_LONG).show();
                } else {
                    ParseUser user = new ParseUser();
                    user.setUsername(names.getText().toString());
                    user.setPassword(passs.getText().toString());
                    user.setEmail(emails.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(Signup.this);
                    progressDialog.setMessage("Signing up...");
                    progressDialog.show();
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(Signup.this, "signed up successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Signup.this, social.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Signup.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });

        changelog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, login.class);
                startActivity(intent);
            }
        });
    }
    public void tapped(View v){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
