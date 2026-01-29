package com.example.demo.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class MoneyUtils {

    private static final Locale VIETNAM = new Locale("vi", "VN");

    /**
     * Format BigDecimal sang chuỗi VND (có dấu . và ₫)
     * Ví dụ: 1200000 → "1.200.000 ₫"
     */
    public static String formatVND(BigDecimal amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(VIETNAM);
        return formatter.format(amount);
    }

    /**
     * Format số double sang chuỗi VND
     */
    public static String formatVND(double amount) {
        return formatVND(BigDecimal.valueOf(amount));
    }

    /**
     * Chuyển String → BigDecimal
     * Ví dụ: "1.200.000" → BigDecimal(1200000)
     */
    public static BigDecimal parse(String moneyStr) {
        try {
            String clean = moneyStr.replace(".", "")
                                   .replace(",", "")
                                   .replace("₫", "")
                                   .trim();
            return new BigDecimal(clean);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi parse tiền: " + moneyStr);
        }
    }

    /**
     * Làm tròn tiền về 2 số thập phân
     */
    public static BigDecimal round(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Cộng tiền
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    /**
     * Trừ tiền
     */
    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    /**
     * Nhân tiền
     */
    public static BigDecimal multiply(BigDecimal a, int quantity) {
        return a.multiply(BigDecimal.valueOf(quantity));
    }
}
