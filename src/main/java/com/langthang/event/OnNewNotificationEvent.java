package com.langthang.event;

import com.langthang.model.dto.response.NotificationDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OnNewNotificationEvent extends ApplicationEvent {
    private final NotificationDTO notification;

    public OnNewNotificationEvent(NotificationDTO notification) {
        super(notification);
        this.notification = notification;
    }
}