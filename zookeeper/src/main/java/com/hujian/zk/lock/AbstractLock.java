package com.hujian.zk.lock;

/**
 * @author hujian
 * @since 2022-05-20 12:53
 */
public abstract class AbstractLock {

    /**
     * 加锁，增加重试逻辑
     */
    public void lock() {
        //尝试获取锁
        if(tryLock()){
            System.out.println("---------获取锁---------");
        }else {
            //等待锁 阻塞
            waitLock();
            //重试
            lock();
        }
    }


    //尝试获取锁
    public abstract boolean tryLock() ;

    public abstract boolean unlock() ;

    //等待锁
    public abstract void waitLock() ;
}
