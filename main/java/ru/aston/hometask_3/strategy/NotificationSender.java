package ru.aston.hometask_3.strategy;

import ru.aston.hometask_3.model.Notification;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс-отправщик уведомлений.
 * Использует HashMap для выбора стратегии без switch/if-else.
 * Соблюдает принцип OCP: добавление новой стратегии не требует изменения этого кода.
 */
public class NotificationSender {
    
    // HashMap для хранения стратегий
    private final Map<String, SendStrategy> strategies = new HashMap<>();
    
    // Конструктор: регистрируем все доступные стратегии
    public NotificationSender() {
        strategies.put("EMAIL", new EmailStrategy());
        strategies.put("SMS", new SmsStrategy());
        strategies.put("TELEGRAM", new TelegramStrategy());
    }
    
    /**
     * Метод отправки уведомления.
     * @param notification объект уведомления (создан через Builder)
     */
    public void send(Notification notification) {
        String type = notification.getType();
        
        // Получаем стратегию из HashMap по типу
        SendStrategy strategy = strategies.get(type);
        
        // Проверяем, есть ли такая стратегия
        if (strategy != null) {
            strategy.send(notification);
        } else {
            System.out.println("❌ Неизвестный тип уведомления: " + type);
        }
    }
    
    /**
     * Метод для добавления новой стратегии (соблюдает OCP).
     * Например, можно добавить WhatsApp, Push и т.д.
     */
    public void addStrategy(String type, SendStrategy strategy) {
        strategies.put(type, strategy);
    }
}