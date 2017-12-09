package com.ycxy.ymh.autoroom.adapter;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.skyfishjy.library.RippleBackground;
import com.ycxy.ymh.autoroom.R;
import com.ycxy.ymh.autoroom.bean.FloorBean;
import com.ycxy.ymh.autoroom.util.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Y&MH on 2017-10-12.
 */

public class ClassRoomAdapter extends RecyclerView.Adapter<ClassRoomAdapter.ViewHolder> {

    private static final String TAG = "ClassRoomAdapter";
    private List<FloorBean> classroomBeanList;
    private Context mContext;


    public ClassRoomAdapter(List<FloorBean> classroomBeanList, Context mContext) {
        this.classroomBeanList = classroomBeanList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // out of memory
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_class_room, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final FloorBean classroomBean = classroomBeanList.get(position);
        final int dark = classroomBean.getLamp();
        final int light = (classroomBean.getLampTotal() - classroomBean.getLamp());
        final int total = classroomBean.getLampTotal();

        holder.tv_class_room_bh.setText(classroomBean.getClassroomName());
        holder.tv_class_room_light.setText(light + "");
        //holder.btn_dark_bulb.setText(dark + "");

        if (light == 0) {
            holder.btn_light_bulb.setClickable(false);
        } else {
            holder.btn_light_bulb.setClickable(true);
        }
        holder.tv_class_room_bh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 进行页面跳转， 传递数据过去
                // 获取位置的数据
                Toast.makeText(mContext, classroomBean.getClassroomName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.btn_light_bulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeBulb(total, classroomBean);
            }
        });


    }

    @Override
    public int getItemCount() {
        return classroomBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_class_room_bh;
        private Button btn_light_bulb;
        //private Button btn_dark_bulb;
        private TextView tv_class_room_light;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_class_room_bh = (TextView) itemView.findViewById(R.id.tv_class_room_bh);
            btn_light_bulb = (Button) itemView.findViewById(R.id.btn_light_bulb);
            //btn_dark_bulb = (Button) itemView.findViewById(R.id.btn_dark_bulb);
            tv_class_room_light = (TextView) itemView.findViewById(R.id.tv_class_room_light);

        }
    }

    public void closeBulb(int total, FloorBean classroomBean) {
        sendCloseCom(Constant.CLOSECLASSROOM + classroomBean.getClassroomId(), total, classroomBean);
        // 发送指令关灯
        sendCMD(Constant.CMDCLOSE);
        this.notifyDataSetChanged();
    }

    public void openBulb(int total, FloorBean classroomBean) {
        classroomBean.setLamp(0);

    }

    public void sendCloseCom(String url, final int total, final FloorBean classroomBean) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mContext, "网络异常，请稍后再试", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.getString("result");
                            if (result.equals("1")) {
                                // 将数据存储到sp中
                                Toast.makeText(mContext, "发送成功 ^_^", Toast.LENGTH_SHORT).show();
                                classroomBean.setLamp(total);
                                ClassRoomAdapter.this.notifyDataSetChanged();
                            } else {
                                Toast.makeText(mContext, "发送成功 *_*", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 通过端口发送指令关灯
     * @param cmd
     */
    private void sendCMD(final String cmd) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                try {
                    socket = new Socket(Constant.LOCALNETIP, Constant.LOCALNETPORT);

                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    OutputStream out = socket.getOutputStream();
                    String msg = cmd;
                    out.write(msg.getBytes(), 0, msg.getBytes().length);
                    String msgRec = input.readUTF();

                    out.close();
                    input.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
