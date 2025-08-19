/*
 * Single Responsibility Principle (SRP)
 *
 * 1. Introduction
 * The Single Responsibility Principle (SRP) is the first of the SOLID principles.
 * It states:
 *   A class should do only one thing and do it well.
 * If a class has more than one responsibility, it becomes harder to maintain, test, and extend.
 * If you put multiple concerns in one class (e.g., business logic + database code + logging),
 * changing one thing might break others.
 *
 * Analogy
 * Think of a restaurant:
 *   - Chef cooks food 🍲
 *   - Waiter serves food 🍽️
 *   - Cashier handles payments 💰
 *
 * If one person does all three, service becomes slow and mistakes happen.
 * Similarly, in programming, each class should do one job.
 *
 * Summary
 * Definition: A class should have only one reason to change.
 * Why: Improves maintainability, reduces bugs, makes testing easier.
 * How: Keep classes focused on one responsibility, and delegate other concerns.
 * In Practice: Split data, persistence, and presentation into separate classes.
 */

// Example without SRP
// This class handles invoice creation, saving to database, and printing.

//class InvoiceWithoutSRP {
//    private String id;
//    private double amount;
//
//    public InvoiceWithoutSRP(String id, double amount) {
//        this.id = id;
//        this.amount = amount;
//    }
//
//    public double calculateTax() {
//        return amount * 0.18; // GST 18%
//    }
//
//    public double getTotalAmount() {
//        return amount + calculateTax();
//    }
//
//    public void saveToDatabase() {
//        System.out.println("Saving invoice " + id + " to database...");
//        // EntityManager.persist(this);
//    }
//
//    public void printInvoice() {
//        System.out.println("Invoice ID: " + id);
//        System.out.println("Total Amount (with Tax): " + getTotalAmount());
//    }
//}

package com.solidPrincipals;

import lombok.Getter;

class Invoice {
    @Getter
    private final String id;
    private final double amount;

    public Invoice(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public double calculateTax() {
        return amount * 0.18; // GST 18%
    }

    public double getTotalAmount() {
        return amount + calculateTax();
    }
}

class InvoiceRepository {

    // In real-world, this would use JPA/Hibernate
    public void save(Invoice invoice) {
        System.out.println("Saving invoice " + invoice.getId() + " to database...");
        // EntityManager.persist(invoice);
    }
}

class InvoicePrinter {

    public void print(Invoice invoice) {
        System.out.println("Invoice ID: " + invoice.getId());
        System.out.println("Total Amount (with Tax): " + invoice.getTotalAmount());
    }
}

class InvoiceService {

    private final InvoiceRepository repository;
    private final InvoicePrinter printer;

    public InvoiceService(InvoiceRepository repository, InvoicePrinter printer) {
        this.repository = repository;
        this.printer = printer;
    }

    public void processInvoice(Invoice invoice) {
        repository.save(invoice);   // Save to DB
        printer.print(invoice);     // Print Invoice
    }
}

public class SRP {
    public static void main(String[] args) {
        Invoice invoice = new Invoice("INV001", 1000.0);
        InvoiceRepository repo = new InvoiceRepository();
        InvoicePrinter printer = new InvoicePrinter();

        InvoiceService service = new InvoiceService(repo, printer); // Dependency Injection
        service.processInvoice(invoice);
    }
}
