package com.example.sign_up_test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    HashMap<String,String> Info_for_signup=new HashMap<>();
    String url="https://tabeshma.000webhostapp.com/mysites/showparams.php";

    TextView tv;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Info_for_signup.put("a","a");

        final MyHttpUtils.RequestData requestData =
                new MyHttpUtils.RequestData(url, "POST");


        for (Map.Entry<String, String> entry : Info_for_signup.entrySet()) {
            requestData.setParameter(entry.getKey(),entry.getValue());
        }
        new MyTask().execute(requestData);

        tv=findViewById(R.id.textView);





    }

    public class MyTask extends AsyncTask<MyHttpUtils.RequestData, Void, String>{


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(MyHttpUtils.RequestData... params) {
            MyHttpUtils.RequestData reqData = params[0];

            return MyHttpUtils.getDataHttpUrlConnection(reqData);
        }

        @Override
        protected void onPostExecute(String result) {
            if(result == null) {
                result = "null";

            }

            tv.setText(result);

        }
    }


}
