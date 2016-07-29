package com.best.practice.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yonney on 16/7/27.
 */
public class ReflectUtils {

    /**
     * get the field of object
     *
     * @param obj       which object you want to find filed
     * @param fieldName the field name you want to find
     * @return the field you want to  find
     * @throws Throwable
     * @throws NoSuchFieldException
     */
    public static Field getField(Object obj, String fieldName) throws NoSuchFieldException {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Field dest;
        while (!hasField(fields, fieldName) && !clazz.getName().equalsIgnoreCase("java.lang.Object")) {
            clazz = clazz.getSuperclass();
            fields = clazz.getDeclaredFields();
        }
        if (hasField(fields, fieldName)) {
            dest = clazz.getDeclaredField(fieldName);
        } else {
            throw new NoSuchFieldException("clazz don't have this field");
        }
        return dest;
    }

    /**
     * Judges whether or not there looking for field
     *
     * @param fields    the fields which you want to find
     * @param fieldName the field name you want to find
     * @return if the field in field return true else return false
     */
    private static boolean hasField(Field[] fields, String fieldName) {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }


    public static void reflect(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            //get the static type of the field
            Class<?> fieldType = field.getType();
            //if it's String,
            if (fieldType == String.class) {
                // save/use field
            }
            //if it's String[],
            else if (fieldType == String[].class) {
                // save/use field
            }
            //if it's List or a subtype of List,
            else if (List.class.isAssignableFrom(fieldType)) {
                //get the type as generic
                ParameterizedType fieldGenericType = (ParameterizedType) field.getGenericType();
                //get it's first type parameter
//                Class<?> fieldTypeParameterType = (Class<?>) fieldGenericType.getActualTypeArguments()[0];
                Type type = fieldGenericType.getActualTypeArguments()[0];
                Class<?> fieldTypeParameterClazz;
                if (type instanceof ParameterizedType) {
                    fieldTypeParameterClazz = (Class<?>) ((ParameterizedType) type).getRawType();
                } else if (type instanceof TypeVariable) {
                    fieldTypeParameterClazz = (Class<?>) type;
                } else {
                    fieldTypeParameterClazz = (Class<?>) type;
                }
                //if the type parameter is String,
                if (fieldTypeParameterClazz == String.class) {
                    // save/use field
                }
            }
        }
    }


    public static void getGenericTypeOfList(Field field) {
        Class<?> fieldType = field.getType();
        if (List.class.isAssignableFrom(fieldType)) {
            Type type = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
            Class<?> fieldTypeParameterClazz;
            if (type instanceof ParameterizedType) {
                fieldTypeParameterClazz = (Class<?>) ((ParameterizedType) type).getRawType();
            } else {
                fieldTypeParameterClazz = (Class<?>) type;
            }
            if (fieldTypeParameterClazz == String.class) {
            }
        }
    }

    public static List<Field> getAnnotationField(Class<?> clazz){
        List<Field> AICheckFields = new ArrayList<Field>();
        for(Field field : clazz.getDeclaredFields()){
            if(field.isAnnotationPresent(Override.class)){
                AICheckFields.add(field);
            }
        }
        return AICheckFields;
    }

}
