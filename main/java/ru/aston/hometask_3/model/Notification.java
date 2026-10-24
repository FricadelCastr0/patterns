package ru.aston.hometask_3.model;

public class Notification {
    // 1) Поля final. После создания через Builder их нельзя изменить.
    private final String recipient;
    private final String message;
    private final String type;      // Например: "EMAIL", "SMS", "TELEGRAM"
    private final int priority;     // 1 - низкий, 2 - средний, 3 - высокий

    // 2) Приватный конструктор. Вызывается ТОЛЬКО из Builder.
    private Notification(Builder builder) {
        this.recipient = builder.recipient;
        this.message = builder.message;
        this.type = builder.type;
        this.priority = builder.priority;
    }

    // 3) Только геттеры (сеттеров нет, так как поля final)
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getType() { return type; }
    public int getPriority() { return priority; }

    // Метод для удобного вывода в консоль (KISS для проверки)
    @Override
    public String toString() {
        return "Notification{" +
                "recipient='" + recipient + '\'' +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", priority=" + priority +
                '}';
    }

    // 4) Статический внутренний класс Builder
 
    public static class Builder {
        private String recipient;
        private String message;
        private String type = "EMAIL"; // Значение по умолчанию
        private int priority = 1;      // Значение по умолчанию

        // Каждый метод возвращает this, позволяя выстраивать цепочку вызовов
        public Builder setRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setPriority(int priority) {
            this.priority = priority;
            return this;
        }

        // 5) Финальный метод сборки с валидацией
        public Notification build() {
            // Проверка на корректность данных перед созданием объекта
            if (recipient == null || recipient.trim().isEmpty()) {
                throw new IllegalArgumentException("Получатель (recipient) не может быть пустым!");
            }
            if (message == null || message.trim().isEmpty()) {
                throw new IllegalArgumentException("Текст сообщения (message) не может быть пустым!");
            }

            // Если всё в порядке, создает и возвращает объект
            return new Notification(this);
        }
    }
}
