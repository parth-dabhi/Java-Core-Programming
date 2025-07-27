/*
=> Thread safety - Telusko
        * Thread safety is a concept in multi-threading which means that a method or class instance can be used by multiple threads at the same time without any problem.
        * Thread safety is achieved by synchronizing the methods or the block of code which should be executed by only one thread at a time.
        * Thread safety is achieved by using "synchronized" keyword.
*/
package com.multiThreding;

class Counter {
    int count;

    // synchronized keyword is used to make the method thread safe.
    // synchronized keyword is used to lock the method so that only one thread can access it at a time.
    // if we do not put "synchronized" keyword, then we do not get exact result!
    public synchronized void increment() {
        count++; // count++ is not thread safe operation, it is not Atomic operation
    }
}

public class threadSafety {

    public static void main(String[] args) {

        Counter c = new Counter();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 1000; i++) {
                    c.increment();
                }
            }
        } );

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 1000; i++) {
                    c.increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Count: " + c.count);
    }
}
