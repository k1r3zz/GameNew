package com.example.administrator.gamenew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


import com.android.volley.Response;

import until.HttpTool;

public class JTAcativeActivity extends AppCompatActivity {

    private TextView tj_act_tv, act_text, act_add, act_pay;
    private String id;
    private String Url;
    private String text, add, pay, title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jtacative);

        initView();
        initData();
    }

    public void initView() {
        Intent intent = this.getIntent();
        id = intent.getStringExtra("id");
        tj_act_tv = (TextView) findViewById(R.id.tj_act_tv);
        act_text = (TextView) findViewById(R.id.act_text);
        act_add = (TextView) findViewById(R.id.act_add);
        act_pay = (TextView) findViewById(R.id.act_pay);

        findViewById(R.id.wv_back).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    public void initData() {
        Url = "http://www.jx3tong.com/?m=api&c=daily&a=daily_detail&daily_id=" + id;
        HttpTool.Gethttp(JTAcativeActivity.this, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject obj = new JSONObject(s);
                    String detail = obj.getString("detail");
                    JSONObject obj2 = new JSONObject(detail);
                    title = obj2.getString("title");
                    text = obj2.getString("intro");
                    add = obj2.getString("join_method");
                    pay = obj2.getString("rewards");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tj_act_tv.setText(title);
                            act_text.setText(text);
                            act_add.setText(add);
                            act_pay.setText(pay);
                        }
                    });

                } catch (JSONException e) {

                }


            }
        });
    }
}
