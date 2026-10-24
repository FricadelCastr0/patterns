package ru.aston.hometask_3.decorator;

import ru.aston.hometask_3.model.Notification;
import ru.aston.hometask_3.strategy.SendStrategy;

/**
 * Декоратор шифрования
 * Добавляет функциональность шифрования перед отправкой
 */
public class EncryptionDecorator extends SendStrategyDecorator {
    
    public EncryptionDecorator(SendStrategy wrappedStrategy) {
        super(wrappedStrategy);
    }
    
    @Override
    public void send(Notification notification) {
        System.out.println("[Decorator] Шифрование сообщения...");
        
        // Создаем НОВОЕ уведомление с зашифрованным текстом, используя Builder
        // видно как паттерны работают вместе и сохраняет неизменяемость объекта
        Notification encryptedNotification = new Notification.Builder()
                .setRecipient(notification.getRecipient())
                .setMessage("[ENCRYPTED] " + notification.getMessage())
                .setType(notification.getType())
                .setPriority(notification.getPriority())
                .build();

        System.out.println("   Исходный текст: " + notification.getMessage());
        System.out.println("   Зашифрованный текст: " + encryptedNotification.getMessage());
        
        // Передает зашифрованное уведомление дальше (в след.обертку или стратегию)
        wrappedStrategy.send(encryptedNotification);
    }
}
