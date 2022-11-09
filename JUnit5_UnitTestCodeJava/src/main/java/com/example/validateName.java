package com.example;
import java.util.regex.Pattern;

public class validateName {
    private String name;
    // Tạo pattern để kiểm tra tên đăng nhập với yêu cầu không bao gồm kí tự đặt biệt, khoảng trắng, độ dài từ 6-12
    Pattern namePattern = Pattern.compile("[a-z0-9_-]{6,12}$");

    public validateName(String name) {
        this.name = name;
    }

    public boolean isValid() {
        if (namePattern.matcher(name).matches()) {
            return true;
        }
        return false;
    }

}
