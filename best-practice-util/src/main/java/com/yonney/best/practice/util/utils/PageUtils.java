package com.yonney.best.practice.util.utils;

import java.util.List;

/**
 * Created by yonney.yang on 2015/4/8.
 */
public class PageUtils {
    public static <T> List<T> getPageList(List<T> list, int currPage, int limit) {
        Assert.isTrue(currPage > -1, "当前Page不能小于0");
        int fromIndex = currPage * limit;
        int toIndex = limit * (currPage + 1);
        toIndex = toIndex > list.size() ? list.size() : toIndex;
        fromIndex = fromIndex > toIndex ? toIndex : fromIndex;
        return list.subList(fromIndex, toIndex);
    }
}
