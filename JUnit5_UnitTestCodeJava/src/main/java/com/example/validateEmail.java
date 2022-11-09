package com.example;
import java.util.regex.Pattern;

public class validateEmail {
    private String email;
    //Pattern kiểm tra tên email hợp lệ dạng aaaa@bbbb.ccc
    Pattern emailPattern = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");

    public validateEmail(String email) {
        this.email = email;
    }

    public boolean isValid() {
        if (emailPattern.matcher(email).matches()) {
            return true;
        }
        return false;
    }

}
