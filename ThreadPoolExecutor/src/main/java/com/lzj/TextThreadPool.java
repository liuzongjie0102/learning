package com.lzj;

import java.util.concurrent.*;

public class TextThreadPool {

    /**
     * 自定义线程池 捕获线程异常提交位置
     * @param args
     */
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        ThreadPoolExecutor pool = new TraceThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        for (int a=0; a < 5; a++){
            Future fu =  pool.submit(new MyRunnable(100, a));
            try {
                fu.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


    public static void test2(){
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int a=0; a < 5; a++){
            pool.submit(new MyRunnable(100, a));
        }
    }

}
