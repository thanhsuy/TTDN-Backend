package com.haui.btl.demo.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@IdClass(TermofuseId.class)  // Chỉ định lớp ID composite
public class Termofuse {

    @Id
    @Column(nullable = false)
    Integer id;  // Ví dụ về cột khóa chính

    @Id
    @Column(nullable = false, length = 100)
    String termType;  // Ví dụ về cột khóa chính

    // Các thuộc tính khác nếu có
}
