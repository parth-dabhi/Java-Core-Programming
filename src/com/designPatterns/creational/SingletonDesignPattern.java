/*
 * Creational Design Pattern: Singleton
 *
 * - Only one instance of the class should exist.
 * - Other classes should be able to get the instance of the Singleton class.
 * - Commonly used in Logging, Cache, Session, Drivers.
 *
 * Initialization methods:
 * 1. Eager Initialization
 *    - Instance is created at the time of class loading.
 * 2. Lazy Initialization
 *    - Instance is created when it is requested for the first time.
 * 3. Thread Safe (synchronized whole method)
 *    - Instance is created in a synchronized manner.
 * 4. Thread Safe (synchronized block, not method)
 *    - Instance is created in a synchronized manner but only locks the first time.
 *
 * Best method: 4 (Thread Safe with synchronized block).
 */

package com.designPatterns.creational;

class SingletonEager {
    private static SingletonEager instance = new SingletonEager();

    private SingletonEager(){}

    public static SingletonEager getInstance() {
        return instance;
    }
}

class SingletonLazy {
    private static SingletonLazy instance;

    private SingletonLazy(){}

    public static SingletonLazy getInstance() {
        if(instance == null) {
            instance = new SingletonLazy();
        }

        return instance;
    }
}

class SingletonSynchronizedMethod {
    private static SingletonSynchronizedMethod instance;

    private SingletonSynchronizedMethod(){}

    public static synchronized SingletonSynchronizedMethod getInstance() {
        if(instance == null) {
            instance = new SingletonSynchronizedMethod();
        }
        return instance;
    }
}

class SingletonSynchronized {
    private static SingletonSynchronized instance;

    private SingletonSynchronized(){}

    public static SingletonSynchronized getInstance() {
        if(instance == null) {
            synchronized (SingletonSynchronized.class) { // go below for this explanation
                if(instance == null) {
                    instance = new SingletonSynchronized();
                }
            }
        }
        return instance;
    }
}

/*
    *** NEW LEARNING ***

    synchronized (SomeClass.class) {
        // This is a class-level lock.
    }

    -> This is used to ensure only one thread can access the block at a time for that entire class.

    What can go inside synchronized(...)?
    Expression         Meaning
    -----------        -----------------------------------------------
    this               Locks current object (used in instance methods)
    SomeObject         Locks that specific object
    SomeClass.class    Locks the class (used in static context)

    Note (new): Every class in Java has a '.class' object that represents its Class metadata.
    Example: Class<?> clazz = String.class;
    '.class' is a singleton object shared by all instances of that class.
*/

public class SingletonDesignPattern {

    public static void main(String[] args) {
        SingletonEager eagerInstance = SingletonEager.getInstance();
        SingletonLazy lazyInstance = SingletonLazy.getInstance();
        SingletonSynchronizedMethod synchronizedMethodInstance = SingletonSynchronizedMethod.getInstance();
        SingletonSynchronized synchronizedInstance = SingletonSynchronized.getInstance();

        System.out.println("Eager Instance: " + eagerInstance);
        System.out.println("Lazy Instance: " + lazyInstance);
        System.out.println("Synchronized Method Instance: " + synchronizedMethodInstance);
        System.out.println("Synchronized Instance: " + synchronizedInstance);
    }
}
