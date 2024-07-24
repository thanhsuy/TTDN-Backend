package com.example.SpringBootLearning.dto.respone;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ApiReponse<T> {
    @Builder.Default
    int code = 1000;
    @Builder.Default
    String message = "Sucess";
    T result;
}
