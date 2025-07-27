//                                              "જય શ્રી ગણેશ"

/*
    Ques : Callable and Future in Java
*/

package com.multiThreding;

import java.util.concurrent.Callable;

class CallableTask implements Callable<Integer> {

    private int number;

    public CallableTask(int number) {
        this.number = number;
    }

    public Integer call() throws Exception {
        System.out.println("\nTask " + number + " Started\n");
        int sum = 0;
        for (int i = number * 100; i <= number * 100 + 99; i++) {
            sum += i;
        }
        System.out.println("\nTask " + number + " Done\n");
        return sum;
    }
}

public class CallableRunner {

    public static void main(String[] args) {

        System.out.println("\nRadhaKrishna\n");
    }
}
