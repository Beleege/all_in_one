package com.jd.beleege.all_in_one.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jd.beleege.all_in_one.job.DemoClass;
import com.jd.beleege.all_in_one.job.DemoClass2;
import com.jd.beleege.all_in_one.job.DemoClass3;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Bean工具
 *
 * @author zhangxl
 * @create 2017-04-12
 */
public class BeanUtil {


    public static void copy(Object a , Object b) {
        if(a == null || b == null) {
            return;
        }

        Class<?> c1 = a.getClass();
        Class<?> c2 = b.getClass();

        List<Field> fieldListA = Lists.newArrayList();
        List<Field> fieldListB = Lists.newArrayList();

        for(Class clazz = c1; clazz != null; clazz = clazz.getSuperclass()) {
            fieldListA.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }
        for(Class clazz = c2; clazz != null; clazz = clazz.getSuperclass()) {
            fieldListB.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }

        Map<String, Object> map = Maps.newHashMap();

        try {
            for (Field f : fieldListA) {
                f.setAccessible(true);
                Object v = f.get(a);
                map.put(f.getName(), v);
            }

            for (Field f : fieldListB) {
                f.setAccessible(true);
                String fname = f.getName();
                Object v = map.get(fname);
                if (v == null) {
                    continue;
                }
                f.set(b, v);
            }
        } catch(Exception e) {
            return;
        }
    }

    public static void mergeBean(Object dest, Object orig) {
        if (dest == null) {
            throw new IllegalArgumentException
                    ("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] p = propertyUtilsBean.getPropertyDescriptors(orig.getClass());
        for (int i = 0; i < p.length; i++) {
            String name = p[i].getName();
            if ("class".equals(name)) continue;
            if (propertyUtilsBean.isReadable(orig, name) &&
                    propertyUtilsBean.isWriteable(dest, name)) {
                try {
                    Object value = propertyUtilsBean.getSimpleProperty(orig, name);
                    if (value == null) continue;
                    BeanUtilsBean.getInstance().copyProperty(dest, name, value);
                } catch (Exception e) {
                    // Should not happen
                }
            }

        }


    }

    public static void main(String[] args) throws IllegalAccessException {
        DemoClass o = new DemoClass();
        o.setName("zhangxl");
        o.setAge(26);
        DemoClass3 d3 = new DemoClass3();
        d3.setCode(12);
        d3.setOneProp("zxl");
        d3.setList(Arrays.asList("a","B","c"));
        o.setDc3(d3);

        DemoClass2 o2 = new DemoClass2();
        o2.setAddr("beijing");

        mergeBean(o2, o);

        System.out.printf(String.valueOf(o.getDc3() == o2.getDc3()));
        System.out.println(JSON.toJSONString(o2));
    }
}
