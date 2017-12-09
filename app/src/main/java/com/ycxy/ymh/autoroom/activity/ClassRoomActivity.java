package com.ycxy.ymh.autoroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ycxy.ymh.autoroom.R;
import com.ycxy.ymh.autoroom.adapter.ClassRoomAdapter;
import com.ycxy.ymh.autoroom.bean.BuildingBean;
import com.ycxy.ymh.autoroom.bean.FloorBean;
import com.ycxy.ymh.autoroom.bean.Total;
import com.ycxy.ymh.autoroom.util.Constant;
import com.ycxy.ymh.autoroom.util.JSONUtils;
import com.ycxy.ymh.autoroom.util.SPUtil;
import com.ycxy.ymh.autoroom.view.DividerLinearItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Y&MH on 2017-10-13.
 */

public class ClassRoomActivity extends AppCompatActivity {
    private static final int UPDATETIME = 0;
    private RecyclerView recyclerView;
    private List<FloorBean> classroomBeanList;
    private Spinner spinner;
    private int floorNum = 7;
    private List<String> spinnerItem;
    private static final String TAG = "ClassRoomActivity";
    private BuildingBean buildingBean;
    private List<FloorBean> tempList;
    private int buildingPosition;
    private int floorPosition;
    private SwipeRefreshLayout srl_class_room;
    private TextView tv_class_room_time;
    private ImageView iv_back;

    private  ClassRoomAdapter adapter;

    private String updataTime = "";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATETIME:
                    // 更新数据
                    classroomBeanList.removeAll(classroomBeanList);
                    tempList = buildingBean.getFloor().get(floorPosition);
                    classroomBeanList.addAll(tempList);
                    adapter.notifyDataSetChanged();
                    tv_class_room_time.setText(updataTime);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);
        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        String buildingJSON = SPUtil.getInfoFromLocal(this,Constant.BUILDINGJSON);
        buildingPosition = intent.getIntExtra(Constant.BUILDINGPOSITION, 0);
        Total total  = JSONUtils.parseTotalJSON(buildingJSON);
        buildingBean = total.getBuilding().get(buildingPosition);
        classroomBeanList = new ArrayList<>();
        classroomBeanList.addAll(buildingBean.getFloor().get(0));
        floorNum = buildingBean.getFloorTotal();
    }

    public void initView() {
        initSpinnerData();
        spinner = (Spinner) this.findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItem);
        spinner.setAdapter(spinnerAdapter);
        srl_class_room = (SwipeRefreshLayout) this.findViewById(R.id.srl_class_room);
        recyclerView = (RecyclerView) this.findViewById(R.id.rv_class_room);
        tv_class_room_time = (TextView) this.findViewById(R.id.tv_class_room_time);
        iv_back = (ImageView) this.findViewById(R.id.iv_back);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new ClassRoomAdapter(classroomBeanList, this);
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerLinearItemDecoration(this, LinearLayout.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerLinearItemDecoration(this, LinearLayout.VERTICAL));

        // 下拉框选项
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                floorPosition = position;
                classroomBeanList.removeAll(classroomBeanList);
                tempList = buildingBean.getFloor().get(position);
                classroomBeanList.addAll(tempList);
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // xia hua shua xin
        srl_class_room.setProgressBackgroundColorSchemeResource(android.R.color.white);
        srl_class_room.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        srl_class_room.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl_class_room.setRefreshing(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        reFresh();
                    }
                }).start();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initTime();
    }

    public void initSpinnerData() {
        spinnerItem = new ArrayList<>();

        for (int i = 0; i < floorNum; i++) {
            spinnerItem.add((i + 1) + "");
        }
    }

    private void reFresh() {
        String usernameAndPassword = SPUtil.getInfoFromLocal(this, Constant.USERNAMEANDPASSWORD);
        String username = usernameAndPassword.split(",")[0];
        String password = usernameAndPassword.split(",")[1];
        OkHttpUtils
                .post()
                .url(Constant.URLLOGIN)
                .addParams("username", username)
                .addParams("password", password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ClassRoomActivity.this, "网络异常，请稍后再试", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        srl_class_room.setRefreshing(false);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.getString("result");
                            if (result.equals("1")) {
                                // 将数据存储到sp中
                                SPUtil.saveInfoToLocal(ClassRoomActivity.this, response, Constant.BUILDINGJSON);
                                Total total = JSONUtils.parseTotalJSON(response);
                                updataTime = total.getTime();
                                buildingBean = total.getBuilding().get(buildingPosition);
                                Toast.makeText(ClassRoomActivity.this, "数据更新成功", Toast.LENGTH_SHORT).show();
                                handler.sendEmptyMessage(UPDATETIME);
                            } else {
                                String message = jsonObject.getString("message");
                                Toast.makeText(ClassRoomActivity.this, message, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void initTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        updataTime = sdf.format(date);
        tv_class_room_time.setText(updataTime);
    }
}
