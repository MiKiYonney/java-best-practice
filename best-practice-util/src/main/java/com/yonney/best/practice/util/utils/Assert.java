package com.yonney.best.practice.util.utils;


import com.yonney.best.practice.util.exception.ValidationException;

/**
 * 断言工具类
 */
public class Assert {
    public static void isTrue(boolean ex, String message) {
        if (!ex) {
            throw new ValidationException(message);
        }
    }

    public static void isTrue(boolean ex) {
        if (!ex) {
            throw new ValidationException();
        }
    }

    public static void checkNotNull(Object obj) {
        if (obj == null) {
            throw new ValidationException(obj + "不能为空");
        }
    }

    public static void checkNull(Object obj) {
        if (obj != null) {
            throw new ValidationException(obj + "不为空");
        }
    }

    public static void checkNull(Object obj, String message) {
        if (obj != null) {
            throw new ValidationException(message);
        }
    }

    public static void checkNotNull(Object obj, String message) {
        if (obj == null) {
            throw new ValidationException(message);
        }
    }
}
