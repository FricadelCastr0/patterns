package ru.aston.hometask_3.chain;

import ru.aston.hometask_3.model.Notification;

/*
 * Обработчик валидации.(проверяет, что уведомление корректно заполнено)
 */
public class ValidationHandler extends Handler {
    
    @Override
    public void handle(Notification notification) {
        System.out.println(" Проверка валидации...");
        
        // Проверяет, что получатель и текст не пустые
        if (notification.getRecipient() == null || notification.getRecipient().trim().isEmpty()) {
            System.out.println(" Ошибка: получатель пустой! Отмена отправки.");
            return; // Прерывает цепочку
        }
        
        if (notification.getMessage() == null || notification.getMessage().trim().isEmpty()) {
            System.out.println("Ошибка: текст сообщения пустой! Отмена отправки.");
            return; // Прерывает цепочку
        }
        
        System.out.println(" Валидация пройдена");
        
        // Передает следующему обработчику
        super.handle(notification);
    }
}