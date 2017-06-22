package com.jd.beleege.all_in_one;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by apple on 2017/2/22.
 */
public class One {

    private static final ExecutorService pools = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
//        int sum = 10;
//        CountDownLatch begin = new CountDownLatch(1);
//        CountDownLatch end = new CountDownLatch(10);
//
//        for(int i=1; i<=sum; i++) {
//            pools.execute(new Player(i, begin, end));
//        }
//
//        System.out.println("Race start at " + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
//        begin.countDown();
//
//        try {
//            end.await();
//        } catch(Throwable e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("Race end at " + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
//        }
//
//        pools.shutdown();
        Map<Integer, String> map = Maps.newTreeMap();
        map.put(2, "a");
        map.put(1, "b");
        map.put(3, "c");
        map.put(4, "d");

        System.out.println(JSON.toJSONString(map.keySet()));
    }


    private static class Player implements Runnable {
        private int id = 0;
        private CountDownLatch begin = null;
        private CountDownLatch end = null;

        public Player(int i, CountDownLatch begin, CountDownLatch end) {
            this.id = i;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                begin.await();
                System.out.println("Play[" + id + "] start at " + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
                Thread.sleep((long)(Math.random()*5000));
                System.out.println("Play[" + id + "] arrived at " + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
            } catch(Throwable e) {
                e.printStackTrace();
            } finally {
                end.countDown();
            }
        }
    }
}

