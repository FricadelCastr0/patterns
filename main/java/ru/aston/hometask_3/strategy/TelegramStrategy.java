package ru.aston.hometask_3.strategy;

import ru.aston.hometask_3.model.Notification;

/**
 * Стратегия отправки через Telegram.
 */
public class TelegramStrategy implements SendStrategy {
    
    @Override
    public void send(Notification notification) {
        System.out.println("️ Отправка Telegram на " + notification.getRecipient() + 
                          ": " + notification.getMessage());
    }
}