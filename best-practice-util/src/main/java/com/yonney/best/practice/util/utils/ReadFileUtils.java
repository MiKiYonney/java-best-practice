package com.yonney.best.practice.util.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yonney.yang
 * Date: 14-11-18
 * Time: 下午6:04
 * To change this template use File | Settings | File Templates.
 */
public class ReadFileUtils {
    public static final String BASE_JSON_PATH = "config/json/%s.json";

    public static Map<String, Object> getJsonMap(String fileName) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(fileName)) {
            try {
                String jsonStr = IOUtils.toString(new ClassPathResource(String.format(BASE_JSON_PATH, fileName), ReadFileUtils.class.getClassLoader()).getInputStream(), "UTF-8");
                result = JsonUtils.fromStr(jsonStr, HashMap.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String, Object> result = ReadFileUtils.getJsonMap("CompetitiveIntelligenceByMonth");
        System.out.println("end");
    }
}