package com.yonney.best.practice.util.utils;


import com.yonney.best.practice.util.exception.ValidationException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Created by yonney.yang on 2015/4/24.
 */
public class EntityUtils {
    public static final String NOT_NULL_MSG = "%s数据有误，%s不能为空";

    public static void checkDtoPropertyNotNull(Object dto, String... property) {
        try {
            Assert.checkNotNull(dto, String.format(NOT_NULL_MSG, "", "dto"));
            Class clazz = dto.getClass();
            for (int i = 0; i < property.length; i++) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(property[i], clazz);
                Method getMethod = propertyDescriptor.getReadMethod();
                if (propertyDescriptor != null) {
                    Object o = getMethod.invoke(dto);
                    Assert.checkNotNull(o, String.format(NOT_NULL_MSG, clazz.getName(), property[i]));
                }
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }
}
