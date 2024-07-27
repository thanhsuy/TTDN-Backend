package com.haui.btl.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ApiResponse<T> {
    @Builder.Default
    int code = 1000;
    @Builder.Default
    String message = "Success";
    T result;
    public ApiResponse(T result) {
        this.result = result;
    }

    public ApiResponse(T result, String message) {
        this.result = result;
        this.message = message;
    }

    public static <T> ApiResponse<T> of(T result) {
        return new ApiResponse<>(result, "Success");
    }

    public static <T> ApiResponse<T> of(T result, String message) {
        return new ApiResponse<>(result, message);
    }
}
