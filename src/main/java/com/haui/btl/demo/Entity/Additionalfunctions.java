package com.haui.btl.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AdditionalfunctionsId.class)
public class Additionalfunctions {
    @Id
    private int idcar;

    @Id
    private String namefunctions;

    // Other fields, getters, setters, etc.
}
