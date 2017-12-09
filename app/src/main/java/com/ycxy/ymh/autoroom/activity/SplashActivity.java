package com.ycxy.ymh.autoroom.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ycxy.ymh.autoroom.R;
import com.ycxy.ymh.autoroom.util.Constant;
import com.ycxy.ymh.autoroom.util.SPUtil;
import com.ycxy.ymh.autoroom.util.TextUtil;

public class SplashActivity extends AppCompatActivity {

    private static final int SLPAHTIME = 0;
    private TextView tv_splash_time;
    private int time = 1;
    private boolean isLogin = false;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SLPAHTIME:
                    --time;
                    if (time < 0) {
                        if (isLogin) {
                            startMainActivity();
                        } else {
                            startLoginActivity();
                        }
                    } else {
                        tv_splash_time.setText(time + "");
                    }
                    handler.removeMessages(SLPAHTIME);
                    handler.sendEmptyMessageDelayed(SLPAHTIME, 1000);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tv_splash_time = (TextView) this.findViewById(R.id.tv_splash_time);

        isLogin();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                handler.sendEmptyMessageDelayed(SLPAHTIME, 1000);
            }
        }).start();
    }

    private void isLogin() {
        // 获取本地的账号密码
        String str = SPUtil.getInfoFromLocal(this, Constant.USERNAME);
        if (TextUtil.isEmpty(str)) {
            return;
        } else {
            isLogin = true;
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();

    }
}
