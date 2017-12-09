package com.ycxy.ymh.tcp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ycxy.ymh.utils.Constants;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int CONNFAILED = 1;
    private static final int RECSUCCESS = 2;
    private static final int RECFAILED = 3;
    //    private static final int CONNFAILED = 1;
    private EditText et_msg;
    private EditText et_ip;
    private EditText et_port;
    private Button btn_send;
    private Button btn_conn;
    private Button btn_close;
    private TextView tv_rec;

    //Socket socket = null;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CONNFAILED :
                    tv_rec.setText("连接错误！");
                    break;
                case RECSUCCESS :
                    tv_rec.setText((String)msg.obj);
                    break;

                case RECFAILED :
                    tv_rec.setText("数据错误！");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        et_msg = (EditText) this.findViewById(R.id.et_msg);
        et_ip = (EditText) this.findViewById(R.id.et_ip);
        et_port = (EditText) this.findViewById(R.id.et_port);
        btn_send = (Button) this.findViewById(R.id.btn_send);
        btn_conn = (Button) this.findViewById(R.id.btn_conn);
        btn_close = (Button) this.findViewById(R.id.btn_close);
        tv_rec = (TextView) this.findViewById(R.id.tv_rec);

        btn_conn.setOnClickListener(this);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conn(false);
            }

        });

    }

    private void conn(final boolean flag) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                String msg = et_msg.getText().toString();
                Log.d(TAG, "run: " + msg);
                try {
                    if (!flag) {
                        socket = new Socket(Constants.GATEWAYIP, Constants.GATEWAYPORT);
                    } else {
                        String ip = et_ip.getText().toString();
                        int port = Integer.parseInt(et_port.getText().toString());
                        socket = new Socket(ip, port);
                    }


                            /*PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                            out.println(msg);
                            out.flush();*/
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    // DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    OutputStream out = socket.getOutputStream();
                    out.write(msg.getBytes(),0,msg.getBytes().length);

/*                            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            String msgRec = br.readLine();*/
                    String msgRec = input.readUTF();

                    if (msgRec != null) {
                        Message recMsg = Message.obtain();
                        recMsg.what = RECSUCCESS;
                        recMsg.obj = msgRec;
                        handler.sendMessage(recMsg);
                    } else {
                        handler.sendEmptyMessage(RECFAILED);
                    }

                    out.close();
                    input.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(CONNFAILED);
                    //Toast.makeText(MainActivity.this,"连接失败",Toast.LENGTH_SHORT).show();
                }

            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_conn:
                conn(true);
                break;
            case R.id.btn_send:
                break;
            case R.id.btn_close:
                break;

        }
    }

/*    private void conn() {
        try {
            socket = new Socket(Constants.GATEWAYIP, Constants.GATEWAYPORT);
            PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);
            out.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msgRec = br.readLine();

            if (msgRec != null) {
                tv_rec.setText(msgRec);
            } else {
                tv_rec.setText("数据错误！");
            }

            out.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            tv_rec.setText("连接失败！");
        }
    }*/
}
