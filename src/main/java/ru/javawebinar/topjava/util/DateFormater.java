package ru.javawebinar.topjava.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateFormater {
    private final DateTimeFormatter sdf = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss").toFormatter();
    public String format(LocalDateTime date) {
        return sdf.format(date);
    }
}
