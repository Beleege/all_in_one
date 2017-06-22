package com.jd.beleege.all_in_one.job;

/**
 * 测试类2
 *
 * @author zhangxl
 * @create 2017-04-13
 */
public class DemoClass2 extends DemoClass {
    private String addr;
    private DemoClass3 dc3;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public DemoClass3 getDc3() {
        return dc3;
    }

    @Override
    public void setDc3(DemoClass3 dc3) {
        this.dc3 = dc3;
    }
}
