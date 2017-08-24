package com.yonney.best.practice.util.utils;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yonney.yang on 2015/4/24.
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        MAPPER.getDeserializationConfig().set(
                DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    /**
     * 将对象转换为JSON格式
     *
     * @param model
     * @return
     * @throws IOException
     */
    public static String toStr(Object model) throws IOException {
        return MAPPER.writeValueAsString(model);
    }

    /**
     * 将JSON字符串转换为指定类实例
     *
     * @param <T>
     * @param content
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> T fromStr(String content, Class<T> clazz) throws IOException {
        return MAPPER.readValue(content, clazz);
    }

    /**
     * 将JSON字符串转换为Map
     *
     * @param content
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromStrToMap(String content) throws IOException {
        return fromStr(content, Map.class);
    }

    /**
     * 对json串进行反序列化，得到列表对象
     *
     * @param content
     * @param clazz
     * @return
     * @throws IOException
     * @throws org.json.JSONException
     */
    public static <T> List<T> fromArray(String content, Class<T> clazz) {
        List<T> resultList = new ArrayList<T>();
        JSONArray array = null;
        try {
            array = new JSONArray(content);
        } catch (JSONException e) {
        } finally {
            for (int i = 0; array != null && i < array.length(); i++) {
                T t = null;
                try {
                    t = fromStr(array.getString(i), clazz);
                } catch (Exception e) {
                }
                resultList.add(t);
            }
        }
        return resultList;
    }
}
