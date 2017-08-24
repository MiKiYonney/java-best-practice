package com.yonney.best.practice.util.utils;

import org.apache.commons.collections.CollectionUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yonney.yang on 2015/4/24.
 */
public class CompareUtils {


    /**
     * 比较两个list里面的中实体对应的值是否相同
     *
     * @param sourceList
     * @param targetList
     * @param ignoreFieldNames
     * @return
     */
    public static boolean compareListObjectValue(List<?> sourceList, List<?> targetList, List<String> ignoreFieldNames) {
        if (CollectionUtils.isEmpty(sourceList) && CollectionUtils.isEmpty(targetList)) return true;
        if (CollectionUtils.isEmpty(sourceList) && CollectionUtils.isNotEmpty(targetList)) return false;
        if (CollectionUtils.isEmpty(targetList) && CollectionUtils.isNotEmpty(sourceList)) return false;
        if (sourceList.size() != targetList.size()) return false;

        for (int i = 0; i < sourceList.size(); i++) {
            if (!compareObjectValue(sourceList.get(i), targetList.get(i), ignoreFieldNames)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较两个对象中相同字段对应的值是否相等，
     *
     * @param source
     * @param target
     * @param ignoreFieldNames 忽略比较的字段名
     * @return
     */
    public static boolean compareObjectValue(Object source, Object target, List<String> ignoreFieldNames) {
        if (source == null && target == null) return true;
        if (source == null && target != null) return false;
        if (source != null && target == null) return false;
        Class<?> srcClass = source.getClass();
        Field[] fields = srcClass.getDeclaredFields();
        for (Field field : fields) {
            String nameKey = field.getName();
            if (ignoreFieldNames != null && ListsUtils.containsIgnoreCase(ignoreFieldNames, nameKey)) {
                continue;
            }
            if (target instanceof Map) {
                HashMap<String, String> tarMap = (HashMap) target;
                String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey).toString();
                if (tarMap.get(nameKey) == null) {
                    return false;
                }
                if (!tarMap.get(nameKey).equals(srcValue)) {
                    return false;
                }
            } else {
                String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey).toString();
                String tarValue = getClassValue(target, nameKey) == null ? "" : getClassValue(target, nameKey).toString();
                if (!srcValue.equals(tarValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Object getClassValueByFieldName(Object obj, String fieldName) {
        try {
            Class<?> clazz = obj.getClass();
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
            Method getMethod = pd.getReadMethod();//获得get方法
            if (pd != null) {
                return getMethod.invoke(obj);
            }
        } catch (InvocationTargetException e) {
        } catch (IntrospectionException e) {
        } catch (IllegalAccessException e) {
        }
        return null;
    }

    public static Object getClassValue(Object obj, String fieldName) {
        if (obj == null) return null;
        try {
            Class beanClass = obj.getClass();
            Method[] ms = beanClass.getMethods();
            for (int i = 0; i < ms.length; i++) {
                // 非get方法不取
                if (!ms[i].getName().startsWith("get")) {
                    continue;
                }
                Object objValue;
                try {
                    objValue = ms[i].invoke(obj, new Object[]{});
                } catch (Exception e) {
                    continue;
                }
                if (objValue == null) {
                    continue;
                }
                if (ms[i].getName().toUpperCase().equals(fieldName.toUpperCase())
                        || ms[i].getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
                    return objValue;
                } /*else if (fieldName.toUpperCase().equals("SID")
                        && (ms[i].getName().toUpperCase().equals("ID") || ms[i].getName().substring(3).toUpperCase()
                        .equals("ID"))) {
                    return objValue;
                }*/
            }
        } catch (Exception e) {
        }
        return null;
    }
}
