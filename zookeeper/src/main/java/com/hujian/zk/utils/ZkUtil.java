package com.hujian.zk.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hujian
 * @since 2022-05-20 13:03
 */
@Component
@Slf4j
public class ZkUtil {

    private static CuratorFramework curatorFramework;
    private static Map<String, InterProcessMutex> mutexMap= new HashMap<>();

    @Autowired
    public void setCuratorFramework(CuratorFramework curatorFramework) {
        ZkUtil.curatorFramework = curatorFramework;
    }

    public static void lock(String path){
        InterProcessMutex processMutex = mutexMap.get(path);
        if(processMutex ==null){
            processMutex = new InterProcessMutex(curatorFramework, path);
            mutexMap.put(path,processMutex);
        }
        try {
            log.info("线程{}准备获取锁",Thread.currentThread().getName());
            processMutex.acquire();
            log.info("线程{}获取到了锁",Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unlock(String path){
        InterProcessMutex processMutex = mutexMap.get(path);
        if (processMutex == null){
            return;
        }
        try {
            log.info("线程{}释放锁",Thread.currentThread().getName());
            processMutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
