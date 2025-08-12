/*
 * What is the Facade Design Pattern?
 * The Facade Pattern is a structural design pattern that provides a simplified interface to a complex subsystem.
 *
 * 📌 Use Cases in the Real World (Enterprise Grade):
 *   - Wrapping multiple microservices behind a unified gateway (like API Gateway).
 *   - Simplifying complex business logic across services/modules in a Service Layer.
 *   - Providing a unified interface for various subsystems (e.g., Payment, Notification, Order).
 *
 * 🧠 Summary
 * The Facade Pattern helps:
 *   - Abstract away internal service complexities.
 *   - Improve maintainability.
 *   - Make it easier for frontend/mobile/microservices to call a single unified method.
 *
 * In a Spring Boot microservices architecture, this pattern maps closely to API Gateway.
 */

package com.designPatterns.structural;

enum NotificationType {
    EMAIL, SMS, PUSH
}

class InventoryService {
    public boolean checkStock(String productId) {
        System.out.println("Checking stock for product: " + productId);
        return true;
    }
}

class PaymentService {
    public boolean processPayment(String accountId, double amount) {
        System.out.println("Processing payment for account: " + accountId + ", amount: " + amount);
        return true;
    }
}

class NotificationService {
    public void sendNotification(NotificationType type, String to, String message) {
        System.out.printf("Sending %s to %s: %s%n", type, to, message);
    }
}

class ShippingService {
    public void scheduleDelivery(String productId, String address) {
        System.out.println("Scheduling delivery for product: " + productId + " to " + address);
    }
}

class OrderFacade {

    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;
    private final ShippingService shippingService;

    // Dependency Injection constructor (Best Practice)
    public OrderFacade(InventoryService inventoryService,
                       PaymentService paymentService,
                       NotificationService notificationService,
                       ShippingService shippingService) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
        this.shippingService = shippingService;
    }

    public void placeOrder(String productId, String accountId, double amount, String address, String userEmail) {
        if (!inventoryService.checkStock(productId)) {
            throw new IllegalStateException("Product is out of stock!");
        }

        boolean paymentSuccess = paymentService.processPayment(accountId, amount);
        if (!paymentSuccess) {
            throw new IllegalStateException("Payment failed!");
        }

        shippingService.scheduleDelivery(productId, address);
        notificationService.sendNotification(NotificationType.EMAIL, userEmail, "Your order has been placed successfully!");
    }
}

public class FacadeDesignPattern {
    public static void main(String[] args) {
        // Typically injected via Spring in real projects
        InventoryService inventoryService = new InventoryService();
        PaymentService paymentService = new PaymentService();
        NotificationService notificationService = new NotificationService();
        ShippingService shippingService = new ShippingService();

        OrderFacade orderFacade = new OrderFacade(inventoryService, paymentService, notificationService, shippingService);

        // Client just calls one method
        orderFacade.placeOrder(
                "P123",
                "ACC789",
                1500.00,
                "123 Krishna Lane, Vrindavan",
                "parth@example.com"
        );
    }
}
