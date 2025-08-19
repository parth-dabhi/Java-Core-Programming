/*
 * Liskov Substitution Principle (LSP)
 *
 * Objects of a superclass should be replaceable with objects of its subclasses
 * without breaking the application.
 *
 * In short: Subclasses should behave like their parent classes.
 * If a child class violates expectations of its parent, it breaks LSP.
 *
 * Concepts:
 *
 * - Inheritance should be used for IS-A relationships (not just code reuse).
 * - A subclass must not weaken preconditions (e.g., expecting fewer valid inputs).
 * - A subclass must not strengthen postconditions (e.g., changing return expectations).
 * - Violating LSP leads to surprises in polymorphism (bugs when using child in place of parent).
 *
 * Class Diagram:
 *
 * ❌ Wrong Design (violates LSP)
 *         Bird
 *          |
 *   ----------------
 *   |              |
 * Sparrow        Ostrich
 *
 * ✅ Correct Design
 *         Bird
 *      /      \
 * FlyingBird  NonFlyingBird
 *     |            |
 *  Sparrow       Ostrich
 *
 * Analogy:
 *
 * Think of an e-commerce checkout system:
 * - Credit Card / UPI → allows refunds.
 * - Cash On Delivery (COD) → doesn’t support refunds.
 * If we put all payments under the same PaymentService with a refund() method,
 * COD will break the expectation. That’s an LSP violation.
 *
 * Summary:
 *
 * - Definition: Subtypes must be substitutable for their base types.
 * - Why: Prevents unexpected behavior in polymorphism.
 * - How: Design hierarchies carefully; separate capabilities.
 * - In Practice: Don’t force subclasses to implement methods that don’t make sense for them.
 *
 * Violation signs:
 *
 * - Subclass throws `UnsupportedOperationException`.
 * - Subclass weakens the contract of a method (e.g., accepting fewer inputs).
 * - Subclass changes output meaning unexpectedly.
 *
 * Fix:
 *
 * - Extract interfaces.
 * - Apply composition over inheritance.
 * - Respect contracts (preconditions not stricter, postconditions not weaker).
 */

package com.solidPrincipals;

interface PaymentService {
    void pay(double amount);
}

interface RefundablePaymentService extends PaymentService {
    void refund(double amount);
}

class CreditCardPaymentService implements RefundablePaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Credit Card");
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunded ₹" + amount + " to Credit Card");
    }
}

class CodPaymentService implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " via Cash on Delivery");
    }

    // does not support refunds
}

class PaymentProcessor {
    public void processPayment(PaymentService payment, double amount) {
        payment.pay(amount);
    }

    public void processRefund(RefundablePaymentService payment, double amount) {
        payment.refund(amount);
    }
}

public class LSP {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        PaymentService cod = new CodPaymentService();
        RefundablePaymentService card = new CreditCardPaymentService();

        processor.processPayment(cod, 500);   // Works
        processor.processPayment(card, 1000); // Works
        processor.processRefund(card, 200);   // Works
        // processor.processRefund(cod, 200); // Compile-time error, prevented
    }
}
