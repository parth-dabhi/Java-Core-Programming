/*
 * Open/Closed Principle (OCP)
 * Software entities (classes, modules, functions) should be open for extension, but closed for modification.
 *
 * This means:
 *   - You should be able to add new functionality without changing existing code.
 *   - Changes should happen by adding new code, not editing old code.
 *
 * Concepts:
 *   - Open for extension → You can enhance behavior.
 *   - Closed for modification → You don’t touch working, tested code (avoiding regression bugs).
 *   - Achieved using abstraction, inheritance, and interfaces.
 *   - This principle reduces the risk of breaking existing features when adding new ones.
 *
 * Analogy:
 *   Think of a power socket in your home.
 *   You don’t change the socket when you get a new appliance — you just plug in a new adapter that fits.
 *   The socket design stays closed for modification but open for extension (by using adapters).
 */

package com.solidPrincipals;

import java.util.EnumMap;
import java.util.Map;

enum NotificationType {
    EMAIL,
    SMS,
    PUSH // we can add more types without touching controller
}

interface NotificationService {
    void sendNotification(String to, String message);
}

class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String to, String message) {
        System.out.println("📧 Sending EMAIL to " + to + " : " + message);
    }
}

class SmsNotificationService implements NotificationService {
    @Override
    public void sendNotification(String to, String message) {
        System.out.println("📱 Sending SMS to " + to + " : " + message);
    }
}

//OCP compliance → Adding new services doesn’t change controller code
class PushNotificationService implements NotificationService {
    @Override
    public void sendNotification(String to, String message) {
        System.out.println("🔔 Sending PUSH notification to " + to + " : " + message);
    }
}

// High-level class(means Business classes) depends on abstraction, not implementation
class NotificationController {

//    OCP compliance → Adding new services doesn’t change controller code
    private final Map<NotificationType, NotificationService> notificationServices;

    public NotificationController(Map<NotificationType, NotificationService> notificationServices) {
        this.notificationServices = notificationServices;
    }

//    @PostMapping("/{type}")
    public void sendNotification(NotificationType type, String to, String message) {
        NotificationService service = notificationServices.get(type);

        if (service == null) {
            System.out.println("❌ Notification type '" + type + "' not supported!");
            return;
        }

        service.sendNotification(to, message);
    }
}

public class OCP {
    public static void main(String[] args) {

        // Register services using EnumMap
        Map<NotificationType, NotificationService> services = new EnumMap<>(NotificationType.class);
        services.put(NotificationType.EMAIL, new EmailNotificationService());
        services.put(NotificationType.SMS, new SmsNotificationService());
        services.put(NotificationType.PUSH, new PushNotificationService());

        // Create controller
        NotificationController controller = new NotificationController(services);

        // Send notifications
        controller.sendNotification(NotificationType.EMAIL, "abc@example.com", "Hello via Email!");
        controller.sendNotification(NotificationType.SMS, "9876543210", "Hello via SMS!");
        controller.sendNotification(NotificationType.PUSH, "user123", "Hello via Push!");
    }
}
