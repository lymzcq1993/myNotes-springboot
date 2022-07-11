package com.hujian.spring.thread;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义监控线程池
 * @author hujian
 */
public class MonitorThreadExecutor extends ThreadPoolExecutor {
    /**
     * 记录业务类型
     */
    private String businessType;
    private MeterRegistry registry;

    private LRUCache<String,Long> timeWaitCache;
    private LRUCache<String,Long> timeExecutorCache;

    public MonitorThreadExecutor(String businessType
                                 ,MeterRegistry registry
                                 ,int corePoolSize, int maximumPoolSize, long keepAliveTime
            , TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.businessType = businessType;
        timeWaitCache = CacheUtil.newLRUCache(1000,1000 * 60 *10);
        timeExecutorCache  = CacheUtil.newLRUCache(1000,1000 * 60 *10);
    }

    /**
     * 记录线程的执行时间
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        //记录任务的所需时间
        long currentTimeMillis = System.currentTimeMillis();
        DistributionSummary summary = DistributionSummary.builder("businessType"+this.businessType+"-execute")
                .publishPercentiles(0.5d,0.75d,0.99d)
                .register(registry);
        summary.record(currentTimeMillis - timeExecutorCache.get(this.businessType+r.toString()));
        //移除缓存
        timeExecutorCache.remove(this.businessType+r.toString());
        super.afterExecute(r, t);
    }


    /**
     * 真正任务开始前
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        //记录开始执行前的时间
        long currentTimeMillis = System.currentTimeMillis();
        DistributionSummary summary = DistributionSummary.builder("businessType"+this.businessType+"-wait")
                .publishPercentiles(0.5d,0.75d,0.99d)
                .register(registry);
        summary.record(currentTimeMillis - timeWaitCache.get(this.businessType+r.toString()));
        //移除缓存
        timeWaitCache.remove(this.businessType+r.toString());
        timeExecutorCache.put(this.businessType+r.toString(), currentTimeMillis);
        super.beforeExecute(t, r);
    }

    /**
     * 提交任务的时候记录
     * @param command
     */
    @Override
    public void execute(Runnable command) {
        //记录等待的时间
        timeWaitCache.put(this.businessType+command.toString(),System.currentTimeMillis());
        super.execute(command);
    }
}
