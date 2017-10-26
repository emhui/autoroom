package com.ycxy.ymh.autoroom.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Y&MH on 2017-10-11.
 * 保存和获取数据
 */

public class SPUtil {
    /**
     * 存储数据
     * @param mContext  上下文
     * @param data  保存的数据
     * @param flag  key值
     *
     */
    public  static void saveInfoToLocal(Context mContext, String data, String flag){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(flag,data);
        editor.commit();
    }

    /**
     * 返回存储的数据
     * @param mContext
     * @param flag  key值
     * @return
     */
    public  static String getInfoFromLocal(Context mContext, String flag){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        String username = sp.getString(flag,"");
        return username;
    }
}
