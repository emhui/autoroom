package com.ycxy.ymh.autoroom.pager;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ycxy.ymh.autoroom.R;
import com.ycxy.ymh.autoroom.activity.LoginActivity;
import com.ycxy.ymh.autoroom.base.BaseFragment;
import com.ycxy.ymh.autoroom.util.Constant;
import com.ycxy.ymh.autoroom.util.SPUtil;

/**
 * Created by Y&MH on 2017-10-11.
 */

public class PerInfoActivity extends BaseFragment {

    private Button btn_exit;
    private TextView tv_username;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.activity_per_info, null);
        btn_exit = (Button) view.findViewById(R.id.btn_exit);
        tv_username = (TextView) view.findViewById(R.id.tv_username);

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reLogin();
            }
        });
        return view;
    }

    private void reLogin() {
        SPUtil.saveInfoToLocal(mContext, "", Constant.USERNAME);
        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        String username = SPUtil.getInfoFromLocal(mContext, Constant.USERNAME);
        tv_username.setText(username);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
