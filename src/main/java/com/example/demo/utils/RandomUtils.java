package com.example.demo.utils;

import java.security.SecureRandom;
import java.util.Random;

public class RandomUtils {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Random random = new Random();

    /**
     * Tạo mã ngẫu nhiên chỉ chữ
     * VD: ABCDE
     */
    public static String randomString(int length) {
        return generate(ALPHABET, length);
    }

    /**
     * Tạo mã ngẫu nhiên chữ + số
     * VD: X8A9Q1
     */
    public static String randomAlphaNumeric(int length) {
        return generate(ALPHANUMERIC, length);
    }

    /**
     * Tạo mã đơn hàng kiểu: ORD-2024-AX9QZ1
     */
    public static String randomOrderCode() {
        return "ORD-" + System.currentTimeMillis() + "-" + randomAlphaNumeric(6);
    }

    /**
     * Tạo OTP 6 số
     * VD: 482019
     */
    public static int randomOTP() {
        return secureRandom.nextInt(900000) + 100000; // từ 100000 → 999999
    }

    /**
     * Random boolean
     */
    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

    /**
     * Random số trong khoảng [min, max]
     */
    public static int randomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    // Hàm hỗ trợ chung
    private static String generate(String base, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(base.charAt(secureRandom.nextInt(base.length())));
        }
        return sb.toString();
    }
}
