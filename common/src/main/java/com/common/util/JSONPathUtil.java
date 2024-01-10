package com.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

public class JSONPathUtil {

    /**
     * 读取json串中对应key的值
     * @param json
     * @param key
     * @param classT
     * @param <T>
     * @return
     */
    public static <T> T read(String json, String key ,Class<T> classT) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        T t = JSONPath.read(json, key, classT);
        return t;
    }

    public static <T> List<T> readArray(String json, String key ,Class<T> classT) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        String v = JSONPath.read(json, key, String.class);
        List<T> list = JSON.parseArray(v, classT);
        return list;
    }
}
