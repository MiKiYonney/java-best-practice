package com.best.practice.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yonney.yang on 2015/4/24.
 */
public class ListsUtils {
    /**
     * 根据某个字段排序List
     *
     * @param list
     * @param compareFieldName
     * @param <T>
     */
    public static <T> void sortList(List<T> list, final String compareFieldName) {
        if (list != null) {
            Collections.sort(list, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    Object value1 = CompareUtils.getClassValueByFieldName(o1, compareFieldName);
                    Object value2 = CompareUtils.getClassValueByFieldName(o2, compareFieldName);
                    if (!(value1 instanceof Number) || !(value2 instanceof Number)) {
                        throw new RuntimeException("compareFieldName value is not number, can't compare");
                    }
                    return (Integer) value1 > (Integer) value2 ? -1 : 1;
                }
            });
        }
    }

    /**
     * list里面是否包含o,忽略大小写
     *
     * @param lists
     * @param o
     * @return
     */
    public static boolean containsIgnoreCase(final List<String> lists, final String o) {
        if (o == null) {
            for (String list : lists)
                if (list == null)
                    return true;
        } else {
            for (String list : lists)
                if (o.toLowerCase().equals(list.toLowerCase()))
                    return true;
        }
        return false;
    }

}
