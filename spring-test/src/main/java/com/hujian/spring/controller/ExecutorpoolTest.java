package com.hujian.spring.controller;

import com.hujian.spring.thread.MonitorThreadExecutor;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author hujian
 */
@RestController
@RequestMapping
public class ExecutorpoolTest {
    private MonitorThreadExecutor executor;

    @Autowired
    private MeterRegistry registry;

    @PostConstruct
    void init(){
        executor = new MonitorThreadExecutor("my-test",
                registry, 10, 20, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
    }

    @GetMapping("test")
    public String test(){
        executor.submit(new Runnable() {
            //休眠100ms
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return "ok";
    }
}
