/*
 * Bridge Design Pattern Example
 *
 * Definition:
 * Bridge separates abstraction from implementation so both can change independently.
 *
 * Use case:
 * When you have multiple abstraction and implementation variations and want to avoid combinational explosion.
 *
 * Analogy:
 * Imagine a remote control (Abstraction) and a TV brand (Implementation):
 *
 *   - You can have different types of remotes: BasicRemote, AdvancedRemote.
 *   - You can have different TV brands: SonyTV, SamsungTV.
 *
 * Without the Bridge pattern, you’d create classes like:
 *   - BasicSonyRemote
 *   - BasicSamsungRemote
 *   - AdvancedSonyRemote
 *   - AdvancedSamsungRemote
 *
 * That’s 4 classes for just 2 remotes × 2 brands.
 *
 * With Bridge:
 *   - Remotes work with a TV interface,
 *   - TVs have their own implementations,
 *   - You can mix and match at runtime.
 *
* Advantages:
 *   - Reduces class explosion:
 *       In the Payment Gateway example, we can have multiple payment methods (UPI, CreditCard) and multiple banks (HDFC, ICICI) without creating a new class for each combination.
 *       With Bridge, we can have:
 *         - UPI with HDFC
 *         - UPI with ICICI
 *         - CreditCard with HDFC
 *         - CreditCard with ICICI
 *       This avoids creating 4 separate classes for each combination.
 *   - Increases flexibility
 *   - Promotes loose coupling
 */

/*
Example Scenario:
Enterprise payment system where `PaymentGateway` is the abstraction and `BankAPI` is the implementation.
Different payment types (UPI, CreditCard) can work with different banks (HDFC, ICICI) without tightly coupling.

- This allows us to add new payment methods or banks without modifying existing code, adhering to the Open/Closed Principle.
- This is particularly useful in large-scale applications where new payment methods or banks may be added frequently.
*/

package com.designPatterns.structural;

interface BankAPI {
    void transfer(String fromAccount, String toAccount, double amount);
}

class HDFCBankAPI implements BankAPI {
    @Override
    public void transfer(String fromAccount, String toAccount, double amount) {
        System.out.println("[HDFC] Transferring ₹" + amount + " from " + fromAccount + " to " + toAccount);
        // Enterprise-grade: Integrate with HDFC REST API here
    }
}

class ICICIBankAPI implements BankAPI {
    @Override
    public void transfer(String fromAccount, String toAccount, double amount) {
        System.out.println("[ICICI] Transferring ₹" + amount + " from " + fromAccount + " to " + toAccount);
        // Enterprise-grade: Integrate with ICICI SOAP/REST API here
    }
}

abstract class PaymentGateway {
    protected BankAPI bankAPI; // Bridge

    public PaymentGateway(BankAPI bankAPI) {
        this.bankAPI = bankAPI;
    }

    public abstract void pay(String fromAccount, String toAccount, double amount);
}

class UPIPayment extends PaymentGateway {
    public UPIPayment(BankAPI bankAPI) {
        super(bankAPI);
    }

    @Override
    public void pay(String fromAccount, String toAccount, double amount) {
        System.out.println("Initiating UPI Payment...");
        bankAPI.transfer(fromAccount, toAccount, amount);
    }
}

class CreditCardPayment extends PaymentGateway {
    public CreditCardPayment(BankAPI bankAPI) {
        super(bankAPI);
    }

    @Override
    public void pay(String fromAccount, String toAccount, double amount) {
        System.out.println("Processing Credit Card Payment...");
        bankAPI.transfer(fromAccount, toAccount, amount);
    }
}

public class BridgeDesignPattern {
    public static void main(String[] args) {
        PaymentGateway upiPaymentUsingHdfcBank = new UPIPayment(new HDFCBankAPI());
        upiPaymentUsingHdfcBank.pay("ACC123", "ACC456", 5000);

        PaymentGateway creditCardPaymentUsingIciciBank = new CreditCardPayment(new ICICIBankAPI());
        creditCardPaymentUsingIciciBank.pay("ACC789", "ACC012", 10000);
    }
}
