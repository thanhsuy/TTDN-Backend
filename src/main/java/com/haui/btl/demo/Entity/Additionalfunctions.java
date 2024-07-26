package com.haui.btl.demo.Entity;

import com.haui.btl.demo.Entity.AdditionalfunctionsId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AdditionalfunctionsId.class)
public class Additionalfunctions {
    @Id
    private int idCar;

    @Id
    private String nameTerms;

    // Other fields, getters, setters, etc.
}
