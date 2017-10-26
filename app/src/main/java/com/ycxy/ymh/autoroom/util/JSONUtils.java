package com.ycxy.ymh.autoroom.util;

import com.alibaba.fastjson.JSON;
import com.ycxy.ymh.autoroom.bean.Total;

/**
 * Created by Y&MH on 2017-10-13.
 */

public class JSONUtils {
    public static Total parseTotalJSON(String response) {
        Total total = JSON.parseObject(response, Total.class);
        return total;
    }
}
