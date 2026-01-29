package com.example.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    // Format chuẩn để dùng API, Log, Database
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    // Lấy thời gian hiện tại dạng chuỗi
    public static String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN));
    }

    // Format LocalDateTime → String
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN));
    }

    // Format LocalDateTime → String với pattern tùy chọn
    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    // Parse String → LocalDateTime
    public static LocalDateTime parse(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DEFAULT_PATTERN));
    }

    // Parse với pattern khác
    public static LocalDateTime parse(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }
}
