package ru.aston.hometask_3.decorator;

import ru.aston.hometask_3.model.Notification;
import ru.aston.hometask_3.strategy.SendStrategy;

/**
 * Базовый класс декоратора
 * Реализует тот же интерфейс SendStrategy, что и обычные стратегии
 * Соблюдает принцип LSP (подстановка Лисков): декоратор можно использовать 
 * везде, где ожидается обычная стратегия
 */
public abstract class SendStrategyDecorator implements SendStrategy {
    
    // Ссылка на оборачиваемый объект
    protected final SendStrategy wrappedStrategy;
    
    public SendStrategyDecorator(SendStrategy wrappedStrategy) {
        this.wrappedStrategy = wrappedStrategy;
    }
    

    @Override
    public void send(Notification notification) {
        wrappedStrategy.send(notification);
    }
}
