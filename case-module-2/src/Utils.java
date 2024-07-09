package src;

import java.util.regex.Pattern;

public class Utils {
    public static boolean isValidDate(String date) {
        return Pattern.matches("\\d{2}/\\d{2}/\\d{4}", date);
    }

    public static boolean isValidMaSinhVien(String maSinhVien) {
        return Pattern.matches("SV-\\d{3}", maSinhVien);
    }

    public static boolean isValidGpa(double gpa) {
        return gpa >= 0 && gpa <= 10;
    }

    public static boolean isValidMonth(String month) {
        return Pattern.matches("\\d{1,2}", month);
    }
}
