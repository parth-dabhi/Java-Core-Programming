package com.collectionFramework.list.vector;

// A Vector in Java is a part of the java.util package and is one of the legacy classes in Java that implements the List interface.
// It was introduced in JDK 1.0 before collection framework and is synchronized, making it thread-safe.

/*
 * What is a Legacy Class?
 * In the Java world, a legacy class usually means a class that:
 *
 *   - Was part of the early versions of Java (JDK 1.0 / 1.1) and is still supported for backward compatibility.
 *   - Not recommended for new code because modern alternatives exist.
 *   - Retained mainly so that old applications can still run without modification.
 *
 * Examples of Legacy Classes in Java:
 *   - Vector      → replaced by ArrayList (but still available)
 *   - Stack       → replaced by Deque (like ArrayDeque)
 *   - Hashtable   → replaced by HashMap
 *   - Enumeration → replaced by Iterator and ListIterator
 *
 * Why are they called "legacy"?
 *   - They were designed before Java Collections Framework (JCF) was introduced in Java 2 (JDK 1.2).
 *   - When JCF came, more efficient and flexible data structures like ArrayList, HashMap, etc. were introduced.
 *   - But to maintain backward compatibility, Java did not remove old classes.
 *   - So, those pre-Collections Framework classes are called legacy classes.
 *
 * 👉 In short:
 *   Legacy class = An old class from early Java, still available but replaced by better alternatives.
 */

import java.util.Vector;

public class VectorClass {
    public static void main(String[] args) {
        // Create an empty generic vector with an initial capacity of 10.
        Vector<String> v = new Vector<String>(15, 5);
// Adding elements to vector.
        v.add("A"); v.add("B"); v.add("C"); v.add("D"); v.add("E");
        System.out.println("Elements: " +v);
        System.out.println("Size: " +v.size());
        System.out.println("Default Capacity: " +v.capacity());
    }
}
