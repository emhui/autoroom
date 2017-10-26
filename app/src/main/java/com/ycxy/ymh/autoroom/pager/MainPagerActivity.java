package com.ycxy.ymh.autoroom.pager;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ycxy.ymh.autoroom.R;
import com.ycxy.ymh.autoroom.activity.LoginActivity;
import com.ycxy.ymh.autoroom.adapter.BuildingAdapter;
import com.ycxy.ymh.autoroom.base.BaseFragment;
import com.ycxy.ymh.autoroom.bean.BuildingBean;
import com.ycxy.ymh.autoroom.bean.Total;
import com.ycxy.ymh.autoroom.util.Constant;
import com.ycxy.ymh.autoroom.util.JSONUtils;
import com.ycxy.ymh.autoroom.util.SPUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Y&MH on 2017-10-11.
 */

public class MainPagerActivity extends BaseFragment {

    private RecyclerView rv_building;
    private List<BuildingBean> buildingList;
    private RadioButton rg_main_pager;
    private RadioButton rg_main_per;
    private String username;
    private String password;
    private static final String TAG = "MainPagerActivity";



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.activity_main_pager,null);

        rv_building = (RecyclerView) view.findViewById(R.id.rv_building);
        rg_main_per = (RadioButton) view.findViewById(R.id.rg_main_per);
        rg_main_pager = (RadioButton) view.findViewById(R.id.rg_main_pager);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,2);
        rv_building.setLayoutManager(gridLayoutManager);

        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        initList();
    }

    public void initList(){
        buildingList = new ArrayList<>();
        String response = SPUtil.getInfoFromLocal(mContext, Constant.BUILDINGJSON);
        Total total = JSONUtils.parseTotalJSON(response);
        buildingList = total.getBuilding();

        BuildingAdapter adapter = new BuildingAdapter(buildingList,mContext);
        rv_building.setAdapter(adapter);



    }



    @Override
    public void onStart() {
        Log.d(TAG, "onStart: onStart ~");
        super.onStart();
        // 1. 获取账号和密码
        String usernameAndPassword = SPUtil.getInfoFromLocal(mContext,Constant.USERNAMEANDPASSWORD);
        username = usernameAndPassword.split(",")[0];
        password = usernameAndPassword.split(",")[1];
        // 2. 重新请求数据，并保存
        rePost();
    }


    public void rePost(){
        Log.d(TAG, "rePost: 重新加载 。。。");
        OkHttpUtils
                .post()
                .url(Constant.URLLOGIN)
                .addParams("username", username)
                .addParams("password", password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "onError: ");
                        Toast.makeText(mContext,"网络异常，请稍后再试",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.getString("result");
                            if (result.equals("1")) {
                                SPUtil.saveInfoToLocal(mContext,response,Constant.BUILDINGJSON);
                                Total total = JSONUtils.parseTotalJSON(response);
                                buildingList = total.getBuilding();
                            } else {
                                String message = jsonObject.getString("message");
                                Toast.makeText(mContext, message,Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
