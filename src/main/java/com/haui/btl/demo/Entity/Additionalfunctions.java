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
public class Additionalfunctions {
    @Id
    @Column(nullable = false)
    Integer idcar;

    @Id
    @Column(nullable = false, length = 200)
    String namefunctions;
}