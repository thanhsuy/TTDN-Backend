package com.haui.btl.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@IdClass(TermofuseId.class)
public class Termofuse {
    @Id
    @Column(nullable = false)
    int idcar;

    @Id
    @Column(nullable = false, length = 100)
    String nameterms;
}
