package ru.aston.hometask_3.strategy;

import ru.aston.hometask_3.model.Notification;

/**
 * Стратегия отправки через SMS.
 */
public class SmsStrategy implements SendStrategy {
    
    @Override
    public void send(Notification notification) {
        System.out.println("📱 Отправка SMS на " + notification.getRecipient() + 
                          ": " + notification.getMessage());
    }
}