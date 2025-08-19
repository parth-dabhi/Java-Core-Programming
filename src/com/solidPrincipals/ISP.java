/*
 * Interface Segregation Principle (ISP)
 *
 * The Interface Segregation Principle states:
 *
 * “Clients should not be forced to depend on methods they do not use.”
 *
 * In other words, instead of creating fat interfaces with many unrelated methods,
 * we should split them into smaller, role-specific interfaces so that implementing
 * classes only implement what they actually need.
 *
 * This prevents unnecessary dependencies and keeps our system modular, flexible,
 * and maintainable.
 *
 * Concepts
 * --------
 * Large interfaces violate ISP → they make classes implement irrelevant methods.
 * Clients should only depend on the methods that are relevant to them.
 * Smaller, cohesive interfaces → more flexibility.
 * This reduces code bloat, fragility, and unnecessary coupling.
 *
 * Analogy
 * -------
 * Think of ATM services:
 *
 * Some ATMs only allow cash withdrawal (like small town ATMs).
 * Some ATMs allow both deposit and withdrawal.
 * 👉 If we force every ATM class to implement depositCash(), we violate ISP
 * because not every ATM has deposit functionality.
 *
 * Class Diagram
 * -------------
 *    ┌──────────────────┐
 *    │ ATMOperations    │   ❌ (Fat Interface - violation)
 *    │ + withdrawCash() │
 *    │ + depositCash()  │
 *    └──────────────────┘
 *              ▲
 *    ┌─────────┴─────────┐
 *    │                   │
 * ┌─────────────┐   ┌───────────────┐
 * │ WithdrawalATM│   │ DepositATM   │
 * │ withdrawCash │   │ withdrawCash │
 * │ depositCash❌│   │ depositCash  │
 * └─────────────┘   └───────────────┘
 *
 * ✅ Solution after ISP:
 *
 *   ┌────────────────────┐    ┌─────────────────────┐
 *   │ Withdrawable       │    │ Depositable         │
 *   │ + withdrawCash()   │    │ + depositCash()     │
 *   └────────────────────┘    └─────────────────────┘
 *              ▲                       ▲
 *              │                       │
 *    ┌─────────┴─────────┐      ┌─────┴────────────┐
 *    │ WithdrawalATM     │      │ DepositATM       │
 *    └───────────────────┘      └─────────────────┘
 */

package com.solidPrincipals;

interface Withdrawable {
    void withdrawCash(double amount);
}

interface Depositable {
    void depositCash(double amount);
}

// ATM that supports only withdrawal
class WithdrawalATM implements Withdrawable {
    @Override
    public void withdrawCash(double amount) {
        System.out.println("Withdrawing " + amount + " from WithdrawalATM");
    }
}

// ATM that supports both deposit and withdrawal
class DepositAndWithdrawalATM implements Withdrawable, Depositable {
    @Override
    public void withdrawCash(double amount) {
        System.out.println("Withdrawing " + amount + " from DepositATM");
    }

    @Override
    public void depositCash(double amount) {
        System.out.println("Depositing " + amount + " in DepositATM");
    }
}

class ATMService {
    private final Withdrawable withdrawable;
    private final Depositable depositable; // Optional, only if available

    public ATMService(Withdrawable withdrawable) {
        this.withdrawable = withdrawable;
        this.depositable = (withdrawable instanceof Depositable) ? (Depositable) withdrawable : null;
    }

    public void withdraw(double amount) {
        withdrawable.withdrawCash(amount);
    }

    public void deposit(double amount) {
        if (depositable == null) {
            throw new UnsupportedOperationException("Deposit not supported!");
        }
        depositable.depositCash(amount);
    }
}

public class ISP {
    public static void main(String[] args) {
        ATMService withdrawalService = new ATMService(new WithdrawalATM());
        withdrawalService.withdraw(2000);
//         withdrawalService.deposit(1000); // ❌ Unsupported

        ATMService depositService = new ATMService(new DepositAndWithdrawalATM());
        depositService.withdraw(500);
        depositService.deposit(1500); // ✅ Works
    }
}
