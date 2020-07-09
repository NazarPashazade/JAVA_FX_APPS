package com.balance.util;

import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

public class Notification {

    public static void callNotification(String text, String title) {
        Notifications notifications = Notifications.create()
                .text(text)
                .title(title)
                .position(Pos.TOP_RIGHT)
                .darkStyle()
                .hideAfter(javafx.util.Duration.seconds(5));
        notifications.showInformation();
    }
}
