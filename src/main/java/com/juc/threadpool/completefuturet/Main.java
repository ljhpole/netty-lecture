package com.juc.threadpool.completefuturet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws Exception {
        List<CompletableFuture<Double>> cfList = new ArrayList<>();
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://finance.sina.com.cn/code/");
        });
        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://money.163.com/code/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://finance.sina.com.cn/price/");
        });
        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://money.163.com/price/");
        });
        CompletableFuture<Double> cfFetchFromqq = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://money.qq.com/price/");
        });
        cfList.add(cfFetchFromSina);
        cfList.add(cfFetchFrom163);
        cfList.add(cfFetchFromqq);
        // 用anyOf合并为一个新的CompletableFuture:
        // CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(cfFetchFrom163, cfFetchFromSina);
        //CompletableFuture<Void> cfFetch = CompletableFuture.allOf(cfList.get(0),cfList.get(1));
CompletableFuture.allOf(cfList.toArray(new CompletableFuture[cfList.size()]));
        cfList.forEach(Main::accept);
        // 最终结果:
//        cfFetch.thenAccept((result) -> {
//            System.out.println("price: " + result);
//        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static String queryCode(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code, String url) {
        System.out.println("query price from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }

    private static void accept(CompletableFuture<Double> k)  {
        try {
            System.out.println(k.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

