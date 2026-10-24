package ru.aston.hometask_3;

// Импорты для Builder
import ru.aston.hometask_3.model.Notification;
import ru.aston.hometask_3.proxy.RateLimitProxy;
import ru.aston.hometask_3.strategy.EmailStrategy;
// Импорты для Strategy
import ru.aston.hometask_3.strategy.NotificationSender;
import ru.aston.hometask_3.strategy.SendStrategy;
import ru.aston.hometask_3.strategy.SmsStrategy;

// Импорты для Chain
import ru.aston.hometask_3.chain.Handler;
import ru.aston.hometask_3.chain.ValidationHandler;
import ru.aston.hometask_3.decorator.EncryptionDecorator;
import ru.aston.hometask_3.chain.LoggingHandler;
import ru.aston.hometask_3.adapter.LegacySmsAdapter;

public class Main {
     public static void main(String[] args) {
        
        System.out.println("=== ТЕСТ 1) Созд. норм уведомление ===");
        try {
            Notification email = new Notification.Builder()
                .setRecipient("user@example.com")
                .setMessage(" Заказ доставлен!")
                .setType("EMAIL")
                .setPriority(2)
                .build();
            
            System.out.println(" Успешно создано: " + email);
            System.out.println(" Получатель: " + email.getRecipient());
            System.out.println(" Текст: " + email.getMessage());
            
        } catch (Exception e) {
            System.out.println(" Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== ТЕСТ 2) Пустой получатель (должна быть ошибка) ===");
        try {
            Notification bad = new Notification.Builder()
                .setRecipient("")  // empty
                .setMessage("Тест")
                .build();
            
            System.out.println("так нельзя ");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Правильно поймали ошибку: " + e.getMessage());
        }

        System.out.println("\n=== ТЕСТ 3) обязательные поля ===");
        try {
            Notification simple = new Notification.Builder()
                .setRecipient("admin@test.com")
                .setMessage("Простое сооб.")
                // type и priority не указываем — возьмутся значения по умолчанию
                .build();
            
            System.out.println("Создано с обычными значениями: " + simple);
            
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        
        // ТЕСТЫ ДЛЯ STRATEGY 

        
        System.out.println("\n\n=== ТЕСТ 4) Отправка через NotificationSender ===\n");
        
        NotificationSender sender = new NotificationSender();
        
        // почта
        Notification emailNotif = new Notification.Builder()
            .setRecipient("user@example.com")
            .setMessage("Ваш заказ доставлен!")
            .setType("EMAIL")
            .build();
        
        sender.send(emailNotif);
        
        // sms
        Notification smsNotif = new Notification.Builder()
            .setRecipient("+79991234567")
            .setMessage("Код подтверждения: 1234")
            .setType("SMS")
            .build();
        
        sender.send(smsNotif);
        
        // Telegram
        Notification telegramNotif = new Notification.Builder()
            .setRecipient("@username")
            .setMessage("Новое сообщение!")
            .setType("TELEGRAM")
            .build();
        
        sender.send(telegramNotif);

          // Неизвестный тип
        Notification unknownNotif = new Notification.Builder()
            .setRecipient("test")
            .setMessage("Тест")
            .setType("WHATSAPP")
            .build();
        
        sender.send(unknownNotif);
        
        // Добавление новой стратегии 
        System.out.println("\n=== ТЕСТ 5) Добавляем WhatsApp ===");
        sender.addStrategy("WHATSAPP", new ru.aston.hometask_3.strategy.SmsStrategy());
        
        Notification whatsappNotif = new Notification.Builder()
            .setRecipient("+79991234567")
            .setMessage("Привет в WhatsApp!")
            .setType("WHATSAPP")
            .build();
        
        sender.send(whatsappNotif);




         System.out.println("\n=== ТЕСТ: Цепочка обязанностей ===\n");
        
        //  звенья цепи
        Handler validationHandler = new ValidationHandler();
        Handler loggingHandler = new LoggingHandler();
        
        // Соед. их (Валидация к логированию)
        validationHandler.setNext(loggingHandler);
        
        // делаю нормальное уведомление
        Notification notification = new Notification.Builder()
            .setRecipient("director@company.com")
            .setMessage("Важный отчет!!!!!!")
            .setType("EMAIL")
            .build();
            
        System.out.println("Запуск цепочки обработки...");
        //перадача уведомления первому звену
        validationHandler.handle(notification);



        // Импорты для Decorator (добавьте их в начало файла Main.java):
        // import ru.aston.hometask_3.decorator.EncryptionDecorator;
        // import ru.aston.hometask_3.strategy.EmailStrategy;

        System.out.println("\n=== ТЕСТ: Декоратор ===\n");
        
        // 1. Берем обычную стратегию
        SendStrategy baseStrategy = new EmailStrategy();
        
        // 2. "Надеваем" на неё декоратор шифрования (Матрешка!)
        SendStrategy encryptedStrategy = new EncryptionDecorator(baseStrategy);
        
        // 3. Создаем уведомление
        Notification secretNotification = new Notification.Builder()
            .setRecipient("secret@agent.com")
            .setMessage("Встреча в полночь у цирка")
            .setType("EMAIL")
            .build();
            
        System.out.println("Отправляем зашифрованное уведомление:");
        // 4. Вызываем send у декоратора. Он зашифрует текст и передаст его в EmailStrategy
        encryptedStrategy.send(secretNotification);




        // Импорты (добавьте в начало Main.java):
// import ru.aston.hometask_3.adapter.LegacySmsAdapter;

System.out.println("\n=== ТЕСТ: Адаптер ===\n");

// Создаем адаптер (он реализует SendStrategy!)
SendStrategy legacyAdapter = new LegacySmsAdapter();

// Создаем уведомление
Notification legacyNotification = new Notification.Builder()
    .setRecipient("+79991234567")
    .setMessage(" гамарджоба из адаптера!")
    .setType("SMS")
    .build();

// Отправляем через адаптер
System.out.println("Отправляем через старый SMS-шлюз:");
legacyAdapter.send(legacyNotification);






System.out.println("\n=== ТЕСТ 6) Proxy (Rate Limiting) ===\n");
        
        // 1. Создаем реальную стратегию
        SendStrategy emailStrategy = new EmailStrategy();
        
        // 2. "Оборачиваем" её в Proxy (как матрешку или как охранника перед дверью)
        SendStrategy proxyStrategy = new RateLimitProxy(emailStrategy);
        
        // 3. Пытаемся отправить 7 уведомлений (лимит = 5)
        for (int i = 1; i <= 7; i++) {
            System.out.println("\n--- Попытка отправки #" + i + " ---");
            
            Notification proxynotification = new Notification.Builder()
                .setRecipient("user" + i + "@test.com")
                .setMessage("Сообщение #" + i)
                .setType("EMAIL")
                .build();
            
            proxyStrategy.send(proxynotification);
        }
        
        System.out.println("\n===  ВСЕ 6 ПАТТЕРНОВ УСПЕШНО РЕАЛИЗОВАНЫ===");
    }
}
