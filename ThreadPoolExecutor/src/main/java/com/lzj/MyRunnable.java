package com.lzj;

public class MyRunnable implements Runnable {
    
    int a;
    int b;

    public MyRunnable(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void run() {
//        Object a = null;
//        a.equals("b");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a / b);
    }
}
