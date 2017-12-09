package com.ycxy.ymh.autoroom.activity;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ycxy.ymh.autoroom.R;
import com.ycxy.ymh.autoroom.base.BaseFragment;
import com.ycxy.ymh.autoroom.pager.MainPagerActivity;
import com.ycxy.ymh.autoroom.pager.PerInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private RadioGroup rg;
    private List<BaseFragment> mBaseFragment;
    private RadioButton rg_main_pager;
    private RadioButton rg_main_per;
    /**
     * 选中的Fragment的对应的位置
     */
    private int position;

    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //初始化View
        initView();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        setListener();
    }

    private void setListener() {
        rg.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选中常用框架
        rg.check(R.id.rg_main_pager);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rg_main_pager://常用框架
                    position = 0;
                    break;
                case R.id.rg_main_per://第三方
                    position = 1;
                    break;
                default:
                    position = 0;
                    break;
            }

            //根据位置得到对应的Fragment
            BaseFragment to = getFragment();
            //替换
            switchFrament(mContent, to);

        }
    }


    /**
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to   马上要切换到的Fragment，一会要显示
     */
    private void switchFrament(Fragment from, Fragment to) {
        if (from != to) {
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()) {
                //to没有被添加
                //from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                if (to != null) {
                    ft.add(R.id.frame_main, to).commit();
                }
            } else {
                //to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }

    }

    /**
     * 根据位置得到对应的Fragment
     *
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragment.get(position);
        return fragment;
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new MainPagerActivity());//常用框架Fragment
        mBaseFragment.add(new PerInfoActivity());//第三方Fragment
    }

    private void initView() {
        setContentView(R.layout.activity_main);

        rg = (RadioGroup) this.findViewById(R.id.rg_main);
        rg_main_pager = (RadioButton) this.findViewById(R.id.rg_main_pager);
        rg_main_per = (RadioButton) this.findViewById(R.id.rg_main_per);
        initRadioButton();
    }

    public void initRadioButton() {
        Drawable drawable_Pager = getResources().getDrawable(R.drawable.selector_main_pager);
        drawable_Pager.setBounds(0,0,70,70);
        rg_main_pager.setCompoundDrawables(null,drawable_Pager,null,null);

        Drawable drawable_Per = getResources().getDrawable(R.drawable.selector_main_per);
        drawable_Per.setBounds(0,0,70,70);
        rg_main_per.setCompoundDrawables(null,drawable_Per,null,null);
    }

    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            //MyConfig.clearSharePre(this, "users");
            finish();
            System.exit(0);
        }
    }
}
