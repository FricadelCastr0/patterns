package ru.aston.hometask_3.proxy;

import ru.aston.hometask_3.model.Notification;
import ru.aston.hometask_3.strategy.SendStrategy;

public class RateLimitProxy implements SendStrategy {
    
    private final SendStrategy realStrategy;
    private int sendCount = 0; 
    private static final int MAX_LIMIT = 5;
    
    public RateLimitProxy(SendStrategy realStrategy) {
        this.realStrategy = realStrategy;
    }
    
    @Override
    public void send(Notification notification) {
        if (sendCount >= MAX_LIMIT) {
            System.out.println(" [Proxy] Лимит исчерпан! Отправка заблокирована.");
            return; // Блокирует доступ к реальному объекту
        }
        
        sendCount++;
        System.out.println(" [Proxy] Лимит не превышен (отправлено: " + sendCount + "). Разрешаю.");
        
        // Передает управление дальше
        realStrategy.send(notification);
    }
}