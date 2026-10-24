package ru.aston.hometask_3.adapter;

import ru.aston.hometask_3.model.Notification;
import ru.aston.hometask_3.strategy.SendStrategy;

/**
 * Адаптер для старого SMS-сервиса.
 * Реализует наш стандартный интерфейс SendStrategy,
 * но внутри использует старый LegacySmsService.
 */
public class LegacySmsAdapter implements SendStrategy {
    
    // Ссылка на старый сервис
    private final LegacySmsService legacyService;
    
    public LegacySmsAdapter() {
        this.legacyService = new LegacySmsService();
    }
    
    @Override
    public void send(Notification notification) {
        System.out.println("[Adapter] Преобразование уведомления в формат старого сервиса...");
        
        // Переводим Notification в формат, который понимает старый сервис
        String legacyFormat = notification.getRecipient() + "|" + 
                             notification.getMessage();
        
        // Призываем старый метод
        legacyService.sendSms(legacyFormat);
    }
}