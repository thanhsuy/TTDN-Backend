package com.haui.btl.demo.Entity;

import java.io.Serializable;
import java.util.Objects;

public class AdditionalfunctionsId implements Serializable {

    private Integer idcar;
    private String namefunctions;

    // Getter và Setter
    public Integer getIdcar() {
        return idcar;
    }

    public void setIdcar(Integer idcar) {
        this.idcar = idcar;
    }

    public String getNamefunctions() {
        return namefunctions;
    }

    public void setNamefunctions(String namefunctions) {
        this.namefunctions = namefunctions;
    }

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
