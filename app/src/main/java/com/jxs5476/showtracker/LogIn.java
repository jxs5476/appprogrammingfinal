package com.jxs5476.showtracker;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LogIn extends AppCompatActivity {
    logindatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void onLogInClick(View view){
        db= logindatabase.getInstance(getApplicationContext());
        EditText userView = findViewById(R.id.user_in);
        EditText passView = findViewById(R.id.pass_in);
        User temp = db.userDao().getUser(String.valueOf(userView.getText()));
        TextView dne = findViewById(R.id.textView4);
        dne.setVisibility(View.GONE);
        TextView inc = findViewById(R.id.textView3);
        inc.setVisibility(View.GONE);


        if(temp == null || temp.getUsername() == null || temp.getPassword() == null){
            dne = findViewById(R.id.textView4);
            dne.setVisibility(View.VISIBLE);
        }
        else if(temp.getPassword().equals(passView.getText().toString())){
            this.finish();
        }
        else{
            inc = findViewById(R.id.textView3);
            inc.setVisibility(View.VISIBLE);

        }
    }

    public void onRegClick(View view) {
        db= logindatabase.getInstance(getApplicationContext());
        EditText userView = findViewById(R.id.user_in);
        EditText passView = findViewById(R.id.pass_in);
        User temp = new User(userView.getText().toString(), passView.getText().toString());
        db.userDao().insertUser(temp);
        MainActivity.DataHolder.setData(temp.getUsername());
        this.finish();
    }
}