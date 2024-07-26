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
public class Termofuse {
    @Id
    @Column(nullable = false)
    int idCar;

    @Id
    @Column(nullable = false, length = 100)
    String nameTerms;
}
