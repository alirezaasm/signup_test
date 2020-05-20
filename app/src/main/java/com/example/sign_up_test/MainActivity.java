package com.example.sign_up_test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    HashMap<String,String> Info_for_signup=new HashMap<>();
    String url="https://tabeshma.000webhostapp.com/mysites/showparams.php";
    ProgressBar pb;
    EditText fn, ln;
    String feedback=null;

    TextView tv;
    List<AsyncTask> tasks = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fn=findViewById(R.id.user);
        ln=findViewById(R.id.password);

        pb=findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        tv=findViewById(R.id.show);



    }

    public class MyTask extends AsyncTask<MyHttpUtils.RequestData, Void, String>{


        @Override
        protected void onPreExecute() {
            if(tasks.isEmpty()){
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
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
            tasks.remove(this);
            if(tasks.isEmpty()){
                pb.setVisibility(View.INVISIBLE);
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem=menu.add("SendData");
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                final MyHttpUtils.RequestData requestData =
                        new MyHttpUtils.RequestData(url, "POST");

                Info_for_signup.put("firstname",fn.getText().toString().trim());
                Info_for_signup.put("lastname",ln.getText().toString().trim());

                for (Map.Entry<String, String> entry : Info_for_signup.entrySet()) {
                    requestData.setParameter(entry.getKey(),entry.getValue());
                }

                new MyTask().execute(requestData);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}



