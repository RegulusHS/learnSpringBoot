package com.leo;

import net.rubyeye.xmemcached.CASOperation;
import net.rubyeye.xmemcached.Counter;
import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemcachedTests {
    @Autowired
    private MemcachedClient memcachedClient;


    @Test
    public void testGetSet() throws Exception {
        memcachedClient.set("hello", 0, "Hello,xmemcached");
        String value = memcachedClient.get("hello");
        System.out.println("hello=" + value);
        memcachedClient.delete("hello");
    }

    @Test
    public void testMore() throws Exception {
        if(!memcachedClient.set("hello",0,"world")) {
            System.err.println("set error");
        }
        if(!memcachedClient.add("hello",0,"dennis")) {
            System.err.println("Add error,Key is existed");
        }
        if(!memcachedClient.replace("hello",0,"dennis")) {
            System.err.println("replace error");
        }
        memcachedClient.append("hello"," good");
        memcachedClient.prepend("hello","hello");
        String name = memcachedClient.get("hello", new StringTranscoder());
        System.out.println(name);
        memcachedClient.deleteWithNoReply("hello");
    }


    @Test
    public void testIncrDecr() throws Exception {
        memcachedClient.delete("Incr");
        memcachedClient.delete("Decr");
        /**
         * incr()参数:
         *  1.第一个参数指定递增的key名称;
         *  2.第二个参数指定递增的幅度大小;
         *  3.第三个参数指定当key不存在的情况下的初始值.
         * 两个参数的重载方法省略了第三个参数，默认指定为0.
         */
        System.out.println(memcachedClient.incr("Incr", 6, 12));
        System.out.println(memcachedClient.incr("Incr", 3));
        System.out.println(memcachedClient.incr("Incr", 2));
        System.out.println(memcachedClient.decr("Decr", 1, 6));
        System.out.println(memcachedClient.decr("Decr", 2));
    }

    @Test
    public void testCounter() throws Exception {
        /**
         * getCounter()参数:
         *  1.第一个参数为计数器的key
         *  2.第二个参数当key不存在时的默认值
         */
        Counter counter = memcachedClient.getCounter("counter", 10);
        System.out.println("counter=" + counter.get());
        long c1 = counter.incrementAndGet();
        System.out.println("counter=" + c1);
        long c2 = counter.decrementAndGet();
        System.out.println("counter=" + c2);
        long c3 = counter.addAndGet(-10);
        System.out.println("counter=" + c3);
    }

    @Test
    public void testCas() throws Exception {
        memcachedClient.set("cas", 0, 100);
        GetsResponse<Integer> result = memcachedClient.gets("cas");
        System.out.println("result value "+result.getValue());

        long cas = result.getCas();
        //尝试将 a 的值更新为 2
        if(!memcachedClient.cas("cas", 0, 2, cas)) {
            System.out.println("cas error");
        }
        System.out.println("cas value "+memcachedClient.get("cas"));

        memcachedClient.cas("cas", 0, new CASOperation<Integer>() {
           public int getMaxTries() { return 1; }
           public Integer getNewValue(long currentCAS, Integer currentValue) { return 2; }
        });
        System.out.println("cas value "+memcachedClient.get("cas"));

    }

    @Test
    public void testTouch() throws Exception {
        memcachedClient.set("Touch", 2, "Touch Value");
        Thread.sleep(1000);
        memcachedClient.touch("Touch", 6);
        Thread.sleep(2000);
        String value = memcachedClient.get("Touch", 3000);
        System.out.println("Touch=" + value);
    }

}