package com.example.amatullah.applock1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

public class MainActivity extends Activity {

    EditText pwd, confirmpwd;
    Button confirmButton, retryButton;
    boolean isEnteringFirstTime = true;
    boolean isEnteringSecondTime = false;
    String enteredPassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.password_set);
        pwd = (EditText) findViewById(R.id.pwd);
        confirmpwd = (EditText) findViewById(R.id.confirmpwd);
        confirmButton = (Button) findViewById(R.id.confirmButton);
        retryButton = (Button) findViewById(R.id.retryButton);
        confirmButton.setEnabled(true);
        retryButton.setEnabled(true);
        sharedPreferences = getSharedPreferences("AppLock", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("password", pwd.getText().toString());
                editor.apply();


                if (pwd.getText().toString().equals(confirmpwd.getText().toString())) {
                    //Toast.makeText(SetPass.this, "congrats", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, applist.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //timer.cancel();
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "retry", Toast.LENGTH_SHORT).show();
                    //confirmButton.setEnabled(false);
                    //retryButton.setEnabled(true);
                }
            }
        });
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEnteringFirstTime = true;
                isEnteringSecondTime = false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        //GoogleAnalytics.getInstance(context).reportActivityStart(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        //GoogleAnalytics.getInstance(context).reportActivityStop(this);
        super.onStop();
        super.onStop();
    }
}

