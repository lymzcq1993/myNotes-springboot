package com.hujian.redis;

import com.hujian.redis.entity.User;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;

@SpringBootTest
class SpringtestApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


//    @Autowired
//    private RedisTemplate redisTemplate2;

    @Autowired
    private RedissonClient redisson2;

    @Test
    void contextLoads() throws IOException {
        User u = new User();
        u.setHobby("打球");
        u.setId(2L);
        u.setName("胡健");
//        redisTemplate.opsForHash().put("byteHashT","hj",u);
//        User u2 = (User) redisTemplate.opsForHash().get("byteHashT","hj");
//        System.out.println(u2.getName());
//        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("blom");
//        bloomFilter.tryInit(100000L,0.03);
//        bloomFilter.add("hujian");
//        System.out.println(bloomFilter.contains("hujian"));
//        System.out.println(bloomFilter.contains("jiangmeng"));
//        bloomFilter.delete();
        System.out.println("------------"+redisson2.getConfig().useSingleServer().getAddress());
//        Config config = Config.fromYAML(SpringtestApplication.class.getClass().getResource("/redisson.yaml"));
//        //config.useSingleServer().setAddress("redis://192.168.31.101:6090");
//        RedissonClient redisson = Redisson.create(config);
//        Config config2 = redisson.getConfig();
//        System.out.println(config2.useSingleServer().getAddress());
//        RBucket<Object> hu = redisson.getBucket("hu");
//        hu.set(u);
//        RMap<Object, Object> jiang = redisson.getMap("jiang");
//        jiang.put("wo","niubiu");

//        stringRedisTemplate.opsForValue().set("stringT","胡健");
//        stringRedisTemplate.opsForHash().put("hashT","jm", JSON.toJSONString(u));
//        stringRedisTemplate.opsForList().leftPush("listT","firstL");
//
//        System.out.println(stringRedisTemplate.opsForValue().get("stringT"));
//        User u1 = JSON.parseObject(stringRedisTemplate.opsForHash().get("hashT","jm").toString(),User.class);
//        System.out.println(u1.getName());
    }

}
