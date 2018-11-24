package com.example.amatullah.applock1;

/**
 * Created by amatullah on 6/4/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by vinayak on 4/4/17.
 */

public class lockscreen extends Activity {

    Button b1;
    EditText ed1;

    public static final String ANSWER = "answer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lockscreen);

        b1 = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.editText);

        b1.setOnClickListener(new View.OnClickListener() {


            /*public String getpwd() {
                //return this._sharedPrefs.getString(SetPass.MyPREFERENCES, MODE_PRIVATE);
                SharedPreference prefs = getSharedPreferences(SetPass.MyPREFERENCES, MODE_PRIVATE);

                String pwd = prefs.getString(SetPass.PASSWORD, enteredPassword);
            }*/

            @Override
            public void onClick (View view){

                SharedPreferences sharedPreferences = getSharedPreferences("AppLock", MODE_PRIVATE);
                String pass = sharedPreferences.getString("password", "");

                /*SharedPreference prefs = getSharedPreferences(SetPass.MyPREFERENCES, MODE_PRIVATE);

                String pwd = prefs.getString(SetPass.PASSWORD, enteredPassword);*/
                /*String s = "";
                try {
                    FileInputStream fIn = openFileInput("pass.txt");
                    InputStreamReader isr = new InputStreamReader(fIn);
                    char[] inputBuffer = new char[SetPass.r_b_s];
                    //String s = "";
                    int charRead;
                    while((charRead = isr.read(inputBuffer)) > 0) {
                        String readString = String.copyValueOf(inputBuffer,0,charRead);
                        s += readString;
                        inputBuffer = new char[SetPass.r_b_s];
                    }
                }

                catch (IOException ioe) {
                    ioe.printStackTrace();
                }*/

                //if (SharedPreferences.getString(ANSWER, "").matches(ed1.getText().toString())) {
                //Toast.makeText(getApplicationContext(),"Redirecting...",Toast.LENGTH_SHORT).show();
                //if(s.equals(ed1.getText().toString())) {
                if(ed1.getText().toString().equals(pass)) {
                    Toast.makeText(lockscreen.this, "Redirecting...", Toast.LENGTH_SHORT).show();
                /*Intent i = new Intent(MainActivity.this, list.class);
                startActivity(i);
                finish();*/

                    /*Intent intent = getPackageManager().getLaunchIntentForPackage("com.muffins.mobile");
                    if(intent != null){
                        startActivity(intent);
                    }else {
                        Toast.makeText(lockscreen.this,"com.muffins.mobile" + " Launch Error.",Toast.LENGTH_SHORT).show();
                    }*/

                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

