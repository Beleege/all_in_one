package com.jd.beleege.all_in_one.job;

/**
 * 实例类
 *
 * @author zhangxl
 * @create 2017-04-12
 */
public class DemoClass {
    private String name;
    private Integer age;
    private DemoClass3 dc3;

    public DemoClass() {}

    public DemoClass(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public DemoClass3 getDc3() {
        return dc3;
    }

    public void setDc3(DemoClass3 dc3) {
        this.dc3 = dc3;
    }
}
