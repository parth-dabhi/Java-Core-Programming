/*
    BAKI che ! - jovo

    => Executor Service is a higher level API for managing threads.
    => Executor Service is a framework that simplifies the execution of tasks in async mode.
    => Executor Service is a part of java.util.concurrent package.
    => Executor Service is an interface.
    => Executor Service is used to manage the life cycle of threads.

    => Executor Framework
    1. Executor(Interface)
    2. Executor Service (Interface)
    3. Scheduled Executor Service (Interface)
    4. Executors (Class)
*/

package com.multiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task extends Thread {

    private int number;

    public Task(int number) {
        this.number = number;
    }

    public void run() {
        System.out.println("\nTask " + number + " Started\n");
        for (int i = number * 100; i <= number * 100 + 99; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\nTask " + number + " Done\n");
    }
}

public class ExecutorServiceRunner {

    public static void main(String[] args) {

        System.out.println("\nRadhaKrishna\n");

//      ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Task(1));
        executorService.execute(new Task(2));
        executorService.execute(new Task(3));
        executorService.execute(new Task(4));
        executorService.execute(new Task(5));

        executorService.shutdown();
    }
}
