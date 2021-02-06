package com.leslie.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ArrayUtils {

    /**
     *  反射将对象集合转化为二维数组
     * @param datas list
     * @return 二维数组
     * @throws Exception exception
     */
    public static String[][] toArrays(List datas) throws Exception{
        // 二维数组的行列
        int row = datas.size();
        Class<?> aClass = null;
        Field[] fields = null;

        // 对象的字段数就是列数
        if (datas!=null&&datas.size()>0){
            Object o = datas.get(0);
            aClass = o.getClass();
            // 字段数
            fields = aClass.getDeclaredFields();
        }

        String[][] arys = new String[row][fields.length];

        for (int i = 0; i < datas.size(); i++) {
            for (int j = 0; j < fields.length; j++) {
                Field field = aClass.getDeclaredField(fields[j].getName());
                field.setAccessible(true);
                Object o = field.get(datas.get(i));
                if (o!=null){
                    arys[i][j] = o.toString();
                }
            }
        }

        return arys;
    }
}
