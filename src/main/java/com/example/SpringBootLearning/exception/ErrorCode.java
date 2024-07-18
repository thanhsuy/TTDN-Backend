package com.example.SpringBootLearning.exception;

public enum ErrorCode {
    USER_NOTFOUND(1001, "Tài khoản không tồn tại"),
    PASSWORD_INVALID(1002, "Mật khẩu không hợp lệ"),
    PASSWORD_FORM_INVALID(
            1003,
            "Mật khẩu cần ít nhất 1 kí tự đặc biệt, 1 chữ cái viết hoa, 1 chữ cái viết thường, 1 chữ số và 1 kí tự đặc biệt"),
    PHONENUMER_INVALID(1004, "Số điên thoại không hợp lệ"),
    USER_EXISTED(1005, "Tai khoan da ton tai"),
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    ErrorCode() {}

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
