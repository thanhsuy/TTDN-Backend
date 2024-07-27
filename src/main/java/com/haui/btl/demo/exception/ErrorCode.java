package com.haui.btl.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_NOTFOUND(1001, "Tài khoản không tồn tại", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1002, "Mật khẩu không hợp lệ",HttpStatus.BAD_REQUEST),
    PASSWORD_FORM_INVALID(1003, "Mật khẩu cần ít nhất 1 kí tự đặc biệt, 1 chữ cái viết hoa, 1 chữ cái viết thường, 1 chữ số và 1 kí tự đặc biệt", HttpStatus.BAD_REQUEST),
    PHONENUMER_INVALID(1004, "Số điên thoại không hợp lệ", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1005, "Tai khoan da ton tai", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Khong the xac thuc tai khoan",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "Khong co quyen truy cap", HttpStatus.FORBIDDEN),
    PASSWORC_NOTEQUAL (1008, "Mật khẩu không trùng khớp", HttpStatus.BAD_REQUEST),
    CAR_NOTFOUND(1010, "Không tìm thấy xe", HttpStatus.NOT_FOUND),
    ;
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

}
