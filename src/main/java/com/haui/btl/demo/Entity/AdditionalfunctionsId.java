package com.haui.btl.demo.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalfunctionsId implements Serializable {
    Integer idcar;
    String namefunctions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalfunctionsId that = (AdditionalfunctionsId) o;
        return Objects.equals(idcar, that.idcar) &&
                Objects.equals(namefunctions, that.namefunctions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcar, namefunctions);
    }
}
