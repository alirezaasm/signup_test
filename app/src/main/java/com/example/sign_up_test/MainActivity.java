package com.example.sign_up_test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    HashMap<String,String> Info_for_signup;
    String url;

    TextView tv;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Info_for_signup.put("firstname","firstname");
        Info_for_signup.put("lastname","lastname");
        Info_for_signup.put("socialnumber","socialnumbrt");
        Info_for_signup.put("phonenumber","phonenumber");
        Info_for_signup.put("grade","grade");
        Info_for_signup.put("city","city");
        Info_for_signup.put("gender","gender");
        Info_for_signup.put("address","address");

        Exchange_of_information exchange_of_information=new Exchange_of_information(Info_for_signup,"");
        exchange_of_information.Send_information();
        tv=findViewById(R.id.textView);

        tv.setText(exchange_of_information.feedback);


    }
}
