package com.example.administrator.gamenew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.logging.Handler;

import until.HttpTool;

public class LoadingActivity extends AppCompatActivity {

    private ImageView im;
    private TextView text;
    private RelativeLayout rl;
    private int num = 6;
    private int picturenum;
    private boolean code = false;
    private String appurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        initView();
        initData();
        new Thread(task).start();

    }

    private void initView() {
        rl = (RelativeLayout) findViewById(R.id.error);
        im = (ImageView) findViewById(R.id.loding_im);
        text = (TextView) findViewById(R.id.loding_text);
        Random random = new Random();
        picturenum = random.nextInt(3);
        if (picturenum == 0) {
            im.setImageResource(R.drawable.loding1);
        } else if (picturenum == 1) {
            im.setImageResource(R.drawable.loding2);
        } else if (picturenum == 2) {
            im.setImageResource(R.drawable.loding3);
        }
        rl.setVisibility(View.GONE);
    }


    Runnable task = new Runnable() {

        @Override
        public void run() {
            try {
                while (num != 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text.setText(num + "");
                        }
                    });
                    num = num - 1;
                    Thread.sleep(1000);

                }

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Log.i("opp",code+"aaaaaaaaaaaaaaaa");
            if (code == true) {
                Intent intent = new Intent();
                intent.setClass(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rl.setVisibility(View.VISIBLE);
                    }
                });
            }

        }
    };

    private void initData() {

        HttpTool.Gethttp(LoadingActivity.this, "http://kiraisgod.com/login.php?type=0", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {

                    JSONObject obj = new JSONObject(s);
                    code = obj.getBoolean("code");

                    appurl = obj.getString("url");
                } catch (JSONException e) {
                }

            }
        });

    }
}
