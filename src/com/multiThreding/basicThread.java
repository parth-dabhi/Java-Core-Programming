//                                              "જય શ્રી ગણેશ"

/*
    Ques : Threds in Java

    Ans :
        * Thread is a lightweight sub-process, smallest unit of processing.
        * Threads are independent, if exception occurs in one thread, it doesn't affect other threads.
        * Threads share a common memory area.
        * Threads are used to utilize the CPU time efficiently.
        * Threads are used to perform multiple tasks at a time.
        * Threads are used to perform background tasks.
        * Threads are used to perform complex tasks in a program.
        * Threads are used to perform time consuming tasks so that the application doesn't get stuck.
        * Threads are used to perform non-blocking tasks.
        * Threads are used to perform multiple operations simultaneously.

    => Two ways to create a thread in Java
        1. By extending Thread class
        2. By implementing Runnable interface

    =>  States of a Thread
        1. New
        2. Runnable
        3. Running
        4. Blocked / Waiting
        5. Dead / Terminated

    => New : When a thread is created, it is in New state.
    => Runnable : When a thread is ready to run, it is moved to Runnable state.
    => Running : When a thread is executing, it is in Running state.
    => Blocked / Waiting : When a thread is waiting for some resources, it is in Blocked / Waiting state.
    => Dead / Terminated : When a thread completes its execution, it is in Dead / Terminated state.

    =>  Placing Priority Requests for Threads
        * Thread class provides methods to change the priority of a thread.
        * setPriority(int priority) : This method is used to set the priority of a thread.
        * getPriority() : This method is used to get the priority of a thread.

        -> Range of priority : 1 to 10 , 1 is the lowest priority and 10 is the highest priority.
        -> By default, the priority of a thread is 5.
    => Placing Priority for Threads it just a request to JVM, it may or may not be considered by JVM.

    => Thread safety
        * Thread safety is a concept in multi-threading which means that a method or class instance can be used by multiple threads at the same time without any problem.
        * Thread safety is achieved by synchronizing the methods or the block of code which should be executed by only one thread at a time.
        * Thread safety is achieved by using "synchronized" keyword.
*/

package com.multiThreding;

// Extending Thread class
class Task1 extends Thread {

    @Override
    public void run() {
        System.out.println("\nTask1 Started\n");
        for (int i = 101; i <= 199; i++) {
            System.out.println("Task1 " + i);
        }
        // Yield method is used to pause the execution of the current thread and give the chance to other threads of the same priority.\
        // Yield method is a static method of Thread class.
        Thread.yield();

        System.out.println("\nTask1 Done\n");
    }

}

// implementing Runnable interface
class Task2 implements Runnable {

    // implementing run() method of Runnable interface
    public void run() {
        System.out.println("\nTask2 Started\n");
        for (int i = 201; i <= 299; i++) {
            System.out.println("Task2 " + i);
        }
        System.out.println("\nTask2 Done\n");
    }
}

public class basicThread {

    public static void main(String[] args) {

        // Sleep for at lest 10 seconds
        try {
            Thread.sleep(10000); // Static method of Thread class
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("RadhaKrishna");

        // Task1 - 101 to 199
        System.out.println("\nTask1 Kickoff\n");
        Task1 task1 = new Task1();
        task1.start(); // start() internally calls run() method of Task1 class which is overridden.
        task1.setPriority(1);

        // Task2 - 201 to 299
        System.out.println("\nTask2 Kickoff\n");
        Task2 task2 = new Task2(); // Task2 class implements Runnable interface.
        Thread task2Thread = new Thread(task2); // Thread class constructor takes Runnable interface object as argument.
        task2Thread.start(); // start() internally calls run() method of Task2 class which is implemented.
        task2Thread.setPriority(10);

        // extecute task3 after task1 and task2 are done. (Joining task1 and task2 threads), join() throws InterruptedException
        try {
            task1.join();
            task2Thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Task3 - 301 to 399
        System.out.println("\nTask3 Kickoff\n");
        for (int i = 301; i <= 399; i++) {
            System.out.println("Task3 " + i);
        }
        System.out.println("\nTask3 Done\n");

        System.out.println("\nMain Done\n");
    }
}
