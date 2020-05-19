package com.example.sign_up_test;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;

public class Exchange_of_information {
    String url="";
    HashMap<String, String> data=new HashMap<>();
    String feedback=null;
    public Exchange_of_information(HashMap<String, String> data, String url) {
        this.data = data;
        this.url=url;
    }



    public String Send_information()
    {
        final MyHttpUtils.RequestData requestData =
                new MyHttpUtils.RequestData(url, "POST");


        for (Map.Entry<String, String> entry : data.entrySet()) {
            requestData.setParameter(entry.getKey(),entry.getValue());
        }
        new MyTask().execute(requestData);
        return feedback;

    }



    public class MyTask extends AsyncTask<MyHttpUtils.RequestData, Void, String> {


        @Override
        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(MyHttpUtils.RequestData... params) {
            MyHttpUtils.RequestData reqData = params[0];

            return MyHttpUtils.getDataHttpUrlConnection(reqData);
        }


        //    The answer is clear here
        @Override
        protected void onPostExecute(String result) {
            if(result == null) {
                result = "null";

            }
           feedback=result;




        }
    }




}
