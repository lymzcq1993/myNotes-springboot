package com.hujian.zk.controller;

import com.hujian.zk.utils.ZkUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hujian
 * @since 2022-05-20 11:50
 */
@Controller
@RestController
@RequestMapping("zk")
public class ZkController {
//    @Autowired
//    private CuratorFramework curatorFramework;

    @GetMapping("/lock")
    public String lock(String path) throws Exception {
        ZkUtil.lock(path);
        Thread.sleep(3000);
        ZkUtil.lock(path);
        Thread.sleep(3000);
        ZkUtil.unlock(path);
        ZkUtil.unlock(path);
        return path;
    }

//    @GetMapping("/add")
//    public String add(String path,String data) throws Exception {
//        Stat stat = curatorFramework.checkExists().forPath(path);
//        if (stat != null){
//            String s = new String(curatorFramework.getData().forPath(path));
//            return s;
//        }
//        else{
//            curatorFramework.create()
//                    .forPath("/add", data.getBytes());
//        }
//        return path;
//    }

}
