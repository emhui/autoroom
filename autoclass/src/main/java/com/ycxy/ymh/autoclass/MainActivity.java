package com.ycxy.ymh.autoclass;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ycxy.ymh.utils.Constants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private static final int LAMP_OFF = 1;
    private static final int LAMP_ON = 2;
    private Button btn_close;
    private Button btn_open;
    private Button btn_con;
    private Button btn_sto;
    private TextView tv;
    private ClientThread clientThread = null;
    public static Handler mainHandler;
    private Message MainMsg;
    //消息定义
    static final int RX_DATA_UPDATE_UI = 1;
    static final int RX_RFID_UPDATE_UI = 2;
    final int TX_DATA_UPDATE_UI = 3;
    static final int TIPS_UPDATE_UI = 4;
    //
    byte SendBuf[] = { 'G', 'C', 'R', '2', '0', '0' };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initMainHandler();
    }

    private void initData() {
        clientThread = new ClientThread(Constants.GATEWAYIP, Constants.GATEWAYPORT);//建立客户端线程
        clientThread.start();
    }

    private void initView() {
        btn_close = (Button) this.findViewById(R.id.btn_close);
        btn_open = (Button) this.findViewById(R.id.btn_open);
        btn_con = (Button) this.findViewById(R.id.btn_connect);
        btn_sto = (Button) this.findViewById(R.id.btn_stop);

        tv = (TextView) this.findViewById(R.id.tv);

        btn_close.setOnClickListener(this);
        btn_open.setOnClickListener(this);
        btn_con.setOnClickListener(this);
        btn_sto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                MainMsg = mainHandler.obtainMessage(TX_DATA_UPDATE_UI,
                        LAMP_OFF, 4);
                mainHandler.sendMessage(MainMsg);
                break;
            case R.id.btn_open:
                break;
            case R.id.btn_connect:
                clientThread = new ClientThread(Constants.GATEWAYIP, Constants.GATEWAYPORT);//建立客户端线程
                clientThread.start();
                break;
            case R.id.btn_stop:
                break;
        }
    }

    void initMainHandler() {
        mainHandler = new Handler() {

            //主线程消息处理中心
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case TX_DATA_UPDATE_UI: //msg.arg1保存功能码 arg2保存终端地址
                        switch (msg.arg1) {
                            case LAMP_OFF:  //关灯
                                // GCR200cc\d\r
                                packDataAndSend(ClientThread.ZIGBEE_FUN_CODE_CTRL_LAMP, SendBuf, 6);
                                break;
                            default:
                                break;
                        }
                        break;
                    case TIPS_UPDATE_UI:
                        // 更新ui
                        String str = (String) msg.obj;
                        tv.setText(str);
                        break;

                }
                super.handleMessage(msg);
            }
        };
    }


    void packDataAndSend(int fc, byte data[], int len){
        byte buff[]=new byte[250];
        int dataLen=0;
        int i=0;

        //数据包长度
        buff[0]=(byte) (3+len);

        //功能码
        buff[2]=(byte)fc;

/*        //发送的数据
        for(i=0; i<len; i++){
            buff[3+i]=data[i];
        }*/

        //校验和
      //  buff[1]=CheckSum(buff, (byte)(len+1));

        //发送长度
        dataLen=3+len;

        //增加"\r\n"
        //	buff[dataLen]='\r';
        //	buff[dataLen++]='\n';
        SendData(data, len);
    }

    //通知客户端线程 发送消息
    void SendData(byte buffer[], int len) {
        Log.d(TAG, "SendData: " + buffer.length);
        MainMsg = ClientThread.childHandler.obtainMessage(ClientThread.TX_DATA,
                len, 0, (Object) buffer);
        ClientThread.childHandler.sendMessage(MainMsg);
    }
}
