/*
 * Decorator Design Pattern
 *
 * Definition:
 * The Decorator Pattern is a structural design pattern that allows you to add
 * new responsibilities/behaviors to an object dynamically at runtime without modifying its original code.
 *
 * Every day Real-Life Examples:
 * - Adding milk, sugar, caramel to coffee (Coffee = base object, Add-ons = decorators).
 * - Java I/O Streams: BufferedInputStream decorates FileInputStream.
 * - Spring Security Filters: Adding extra security layers to request processing.
 *
 *
 * UML Structure:
 *
 *           ┌────────────────────┐
 *           │     Dress (I)      │  <---- Interface
 *           └────────────────────┘
 *                     ▲
 *                     │ implements
 *           ┌────────────────────┐
 *           │   BasicDress       │  <---- Concrete Component
 *           └────────────────────┘
 *                     ▲
 *                     │ extends
 *           ┌────────────────────┐
 *           │ DressDecorator     │  <---- Abstract Decorator
 *           └────────────────────┘
 *            ▲         ▲         ▲
 *            │         │         │
 * ┌────────────────┐ ┌────────────────┐ ┌────────────────┐
 * │  CasualDress   │ │  SportyDress   │ │  FancyDress    │  <---- Concrete Decorators
 * └────────────────┘ └────────────────┘ └────────────────┘
 *
 * Here, we can use any combination of decorators to enhance the basic dress functionality
 * in technical ways, like adding features or behaviors dynamically to any object.
 */

package com.designPatterns.structural;

enum PaymentType {
    CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL,
    UPI
}

interface PaymentProcessor {
    void processPayment(PaymentType paymentType, double amount);
}

class BasicPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(PaymentType type, double amount) {
        System.out.println("Processing " + type + " payment of $" + amount);
    }
}

abstract class PaymentProcessorDecorator implements PaymentProcessor {
    protected final PaymentProcessor decoratedProcessor;

    protected PaymentProcessorDecorator(PaymentProcessor decoratedProcessor) {
        this.decoratedProcessor = decoratedProcessor;
    }

    @Override
    public void processPayment(PaymentType type, double amount) {
        decoratedProcessor.processPayment(type, amount);
    }
}

class LoggingPaymentProcessor extends PaymentProcessorDecorator {
    public LoggingPaymentProcessor(PaymentProcessor decoratedProcessor) {
        super(decoratedProcessor);
    }

    @Override
    public void processPayment(PaymentType type, double amount) {
        System.out.println("[LOG] Payment initiated: " + type + " - $" + amount);
        super.processPayment(type, amount);
        System.out.println("[LOG] Payment completed: " + type);
    }
}

class FraudCheckPaymentProcessor extends PaymentProcessorDecorator {
    public FraudCheckPaymentProcessor(PaymentProcessor decoratedProcessor) {
        super(decoratedProcessor);
    }

    @Override
    public void processPayment(PaymentType type, double amount) {
        if (amount > 10000) {
            System.out.println("[FRAUD CHECK] High-value transaction flagged for review!");
        }
        super.processPayment(type, amount);
    }
}

class NotificationPaymentProcessor extends PaymentProcessorDecorator {
    public NotificationPaymentProcessor(PaymentProcessor decoratedProcessor) {
        super(decoratedProcessor);
    }

    @Override
    public void processPayment(PaymentType type, double amount) {
        super.processPayment(type, amount);
        System.out.println("[NOTIFICATION] Payment receipt sent to customer.");
    }
}

// Client Usage (Flexible Chaining)
public class DecoratorDesignPattern {
    public static void main(String[] args) {
        PaymentProcessor processor1 = new NotificationPaymentProcessor(
                new FraudCheckPaymentProcessor(
                        new LoggingPaymentProcessor(
                                new BasicPaymentProcessor()
                        )
                )
        );

        // if you not don't need fraud check, you can just use:

        PaymentProcessor processor2 = new NotificationPaymentProcessor(
                new LoggingPaymentProcessor(
                        new BasicPaymentProcessor()
                )
        );

        processor1.processPayment(PaymentType.UPI, 12000);
        System.out.println("-----");
        processor2.processPayment(PaymentType.CREDIT_CARD, 15000);
    }
}
