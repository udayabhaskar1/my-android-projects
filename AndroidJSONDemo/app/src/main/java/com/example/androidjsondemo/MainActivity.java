package com.example.androidjsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, AsyncToServer.IServerResponse {

    Button btnGet, btnSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGet = findViewById(R.id.btnGet);
        btnSet = findViewById(R.id.btnSet);
        btnGet.setOnClickListener(this);
        btnSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Command cmd;

        int id = v.getId();
        switch (id) {
            case R.id.btnGet:
                cmd = new Command(this, "get",
                        "http://10.0.2.2:59334/Home/GetPerson", null);
                new AsyncToServer().execute(cmd);
                break;

            case R.id.btnSet:
                JSONObject jsonObj = new JSONObject();

                try {
                    jsonObj.put("name", "James");
                    jsonObj.put("age", "33");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                cmd = new Command(this, "set",
                        "http://10.0.2.2:59334/Home/SetPerson", jsonObj);
                new AsyncToServer().execute(cmd);
                break;
        }
    }

    @Override
    public void onServerResponse(JSONObject jsonObj) {
        if (jsonObj == null)
            return;

        try {
            String context = (String) jsonObj.get("context");

            if (context.compareTo("get") == 0)
            {
                String name = (String)jsonObj.get("name");
                int age = (int) jsonObj.get("age");
                System.out.println("name: " + name + ", age: " + age);
            }
            else if (context.compareTo("set") == 0)
            {
                String status = (String)jsonObj.get("status");
                System.out.println("status:" + status);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
