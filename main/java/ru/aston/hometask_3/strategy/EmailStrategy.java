package ru.aston.hometask_3.strategy;

import ru.aston.hometask_3.model.Notification;

public class EmailStrategy implements SendStrategy {
    
    @Override
    public void send(Notification notification) {
        System.out.println("📧 Отправка Email на " + notification.getRecipient() + 
                          ": " + notification.getMessage());
    }
}