package ru.aston.hometask_3.adapter;

/**
 * Старый SMS-сервис (legacy). ( левая библиотека беез возможности редактирования)
 * У неё неудобный интерфейс: принимает строку в формате "ТЕЛЕФОН|СООБЩЕНИЕ".
 */
public class LegacySmsService {
    
    /**
     * Отправка SMS через старый сервис.
     * @param data Строка в формате "ТЕЛЕФОН|СООБЩЕНИЕ"
     */
    public void sendSms(String data) {
        System.out.println(" [Legacy SMS Gateway] Отправка через старый шлюз...");
        System.out.println("   Данные в странном формате: " + data);
        
        // Симуляция отправки
        String[] parts = data.split("\\|");
        if (parts.length == 2) {
            System.out.println(" SMS отправлено на " + parts[0] + 
                             ": " + parts[1]);
        } else {
            System.out.println(" Ошибка формата данных!");
        }
    }
}