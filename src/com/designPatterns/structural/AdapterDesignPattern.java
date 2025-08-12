/*
 * 1️⃣ What is the Adapter Design Pattern?
 * The Adapter Pattern is a structural design pattern that allows incompatible interfaces
 * to work together without modifying their existing code.
 *
 * 💡 Think of it as a “translator” between two systems that speak different “languages.”
 *
 * When to Use:
 * - When you have a legacy system and want to integrate it with a new system without rewriting the old one.
 * - When two components/classes have different method signatures but logically should work together.
 *
 * Full Video Explanation: https://www.youtube.com/watch?v=eR22JuwTa54
 *
 * Analogy:
 * Imagine a hospital that has its own way of processing patient information,
 * but it needs to work with various medical insurance companies that have different formats and protocols.
 * The Adapter Pattern acts as a bridge, allowing the hospital's system to communicate
 * with each insurance company without changing the hospital's existing code.
 */

package com.designPatterns.structural;

//Basic Java Example
//Let’s say we have an old payment system and a new payment gateway with different method names.

interface OldPaymentProcessor {
    void payInRupees(double amount);
}

// New third-party payment gateway
class NewPaymentGateway {
    public void makePayment(double amountInUSD) {
        System.out.println("Payment of $" + amountInUSD + " made using New Gateway");
    }
}

// Adapter to bridge old system with new system
class PaymentAdapter implements OldPaymentProcessor {
    private final NewPaymentGateway newGateway; // Composition to hold the new gateway

    public PaymentAdapter(NewPaymentGateway newGateway) {
        this.newGateway = newGateway;
    }

    @Override
    public void payInRupees(double amount) {
        double amountInUSD = amount / 83; // Convert INR to USD
        newGateway.makePayment(amountInUSD);
    }
}

public class AdapterDesignPattern {
    public static void main(String[] args) {
        OldPaymentProcessor payment = new PaymentAdapter(new NewPaymentGateway()); // now we are using new PaymentGateway through the adapter
        payment.payInRupees(8300); // Will internally convert to USD
    }
}
