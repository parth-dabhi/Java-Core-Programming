/*
 * ===========================
 * Proxy Design Pattern Intro
 * ===========================
 *
 * What is a Proxy?
 * ----------------
 * A proxy is someone or something that acts on behalf of another.
 *
 * Proxy Class:
 *  - A class that wraps another class to control access.
 *
 * 💼 Real-Life Example
 * --------------------
 * Imagine this:
 *   🧑‍💼 You are a manager and very busy.
 *   👩‍💼 Your assistant handles small tasks like scheduling meetings, replying to emails, or printing reports.
 *   Here, your assistant is a proxy for you.
 *   People interact with the assistant instead of bothering you directly.
 *
 * 💻 In Programming
 * -----------------
 * A proxy class/object is a middleman:
 *   - It controls access to the real object.
 *   - It may add extra behavior like:
 *       • Access control
 *       • Logging
 *       • Lazy loading
 *       • Validation
 *       • Security check
 *
 * Example:
 *   - Voting:   "I sent my friend as a proxy to vote for me."
 *   - Business: "He attended the meeting as a proxy for the CEO."
 *
 * 🔧 Structure
 * ------------
 *   ┌─────────┐    ┌──────┐    ┌──────────────────────┐
 *   │ Client  │ →  │Proxy │ →  │RealService(Subject)  │
 *   └─────────┘    └──────┘    └──────────────────────┘
 *
 * Theory
 * ------
 * Structural design pattern.
 * Used when you want to control access.
 *   e.g., In Databases, you may want to allow the 'delete' query only for certain users like admin.
 *
 * Proxy Design Pattern - Implementation
 * -------------------------------------
 * In general, we have a class which is executing an interface's method, which executes all commands.
 * To control this, we add a Proxy class which 'implements the same interface and writes the conditions'
 * for 'admin' user before proceeding to the actual executor.
 */

// Secure Report System

package com.designPatterns.structural;

enum UserRole {
    ADMIN,
    USER,
    MANAGER,
    GUEST;
}

interface ReportGenerator {
    void generateReport();
}

class RealReportGenerator implements ReportGenerator {
    @Override
    public void generateReport() {
        System.out.println("Generating secure financial report...");
        // Simulate expensive operation like DB access
    }
}

class ReportGeneratorProxy implements ReportGenerator {
    private final UserRole role;
    private RealReportGenerator realReportGenerator;

    public ReportGeneratorProxy(UserRole role) {
        this.role = role;
    }

    @Override
    public void generateReport(){
        if (role.equals(UserRole.ADMIN)) {
            if (realReportGenerator == null) {
                realReportGenerator = new RealReportGenerator(); // lazy initialization - Important
            }
            realReportGenerator.generateReport();
        } else {
            throw new SecurityException("Access Denied: Only ADMIN users can generate reports.");
        }
    }
}

public class ProxyDesignPattern {
    public static void main(String[] args) {
        try {
            ReportGenerator adminProxy = new ReportGeneratorProxy(UserRole.ADMIN);
            adminProxy.generateReport();

            ReportGenerator userProxy = new ReportGeneratorProxy(UserRole.USER);
            userProxy.generateReport(); // should throw error
        } catch (SecurityException e) {
            System.err.println("⚠️ " + e.getMessage());
        }
    }
}
