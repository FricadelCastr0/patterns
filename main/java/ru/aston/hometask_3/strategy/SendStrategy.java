package ru.aston.hometask_3.strategy;

import ru.aston.hometask_3.model.Notification;

public interface SendStrategy {
        void send(Notification notification);
}
