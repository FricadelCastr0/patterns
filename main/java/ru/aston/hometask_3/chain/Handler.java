package ru.aston.hometask_3.chain;

import ru.aston.hometask_3.model.Notification;

// Абстрактный класс обработчик
public abstract class Handler {
    
    // Ссылка на следующий обработчик в цепочке
    protected Handler nextHandler;
    
    /**
     * Метод для установки следующего обработчика.
     * Возвращает this для удобной цепочки вызовов.
     */
    public Handler setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler; // Возвращает следующий, чтобы можно было строить цепочку
    }
    
    /**
     * Метод обработки уведомления
     * Если есть следующий обработчик — передает ему
     */
    public void handle(Notification notification) {
        if (nextHandler != null) {
            nextHandler.handle(notification);
        }
    }
}
