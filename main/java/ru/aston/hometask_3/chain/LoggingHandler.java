package ru.aston.hometask_3.chain;

import ru.aston.hometask_3.model.Notification;

/**
 * Обработчик логирования
 * Записывает в лог информацию об отправке
 */
public class LoggingHandler extends Handler {
    
    @Override
    public void handle(Notification notification) {
        System.out.println(" Логирование: отправка уведомления на " +  notification.getRecipient() + " [" + notification.getType() + "]");
        
        // Передаем следующему обработчику 
        super.handle(notification);
    }
}
