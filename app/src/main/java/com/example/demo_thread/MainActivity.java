package com.example.demo_thread;

import android.os.Handler;
import android.os.Message;
import android.os.MessageQueue;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Handler mHanlder;
    private TextView mTvCountUp, mTvCountDown;
    private static final int mMessageTime = 1;
    private static final int mMessageI = 2;
    private Button mButtonDown, mButtonUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvCountDown = findViewById(R.id.text_view_count_down);
        mTvCountUp = findViewById(R.id.text_view_count_up);
        mButtonUp = findViewById(R.id.btn_up);
        mButtonDown = findViewById(R.id.btn_down);
        mButtonDown.setOnClickListener(this);
        mButtonUp.setOnClickListener(this);
        doHanlder();

    }

    public void doCownDown() {
        Thread thread = new Thread() {
            @Override
            public void run() {

                int time = 10;
                do {
                    time--;
                    Message message = new Message();
                    message.what = mMessageTime;
                    message.arg1 = time;
                    mHanlder.sendMessage(message);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (time > 0);
            }
        };
        thread.start();
    }

    public void doCountUp() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                super.run();
                int i = 0;
                do {
                    Message message1 = new Message();
                    message1.what = mMessageI;
                    message1.arg1 = i;
                    mHanlder.sendMessage(message1);
                    i++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (i < 10);
            }
        };
        thread1.start();
    }

    public void doHanlder() {
        mHanlder = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case mMessageI:
                        mTvCountUp.setText(String.valueOf(msg.arg1));
                        break;
                    case mMessageTime:
                        mTvCountDown.setText(String.valueOf(msg.arg1));
                        break;
                }
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_down:
                doCownDown();
                break;
            case R.id.btn_up:
                doCountUp();
                break;
        }
    }
}
