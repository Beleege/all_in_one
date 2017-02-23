package com.jd.beleege.all_in_one.job;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by apple on 2017/2/23.
 */
public class CalculateHBaseRowKey {
    public static void main(String[] args) {
        System.out.println(getHbaseRowkey2("277833707", 50));
        getDateTime(1487122853080l);
    }


    public static String getHbaseRowkey2(String id, int mod) {
        StringBuilder sb = new StringBuilder();

        sb.append(Math.abs(id.hashCode()) % mod).append("_").append(id);

        return sb.toString();
    }

    public static void getDateTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date(time);
        System.out.println(sdf.format(d));
    }
}
