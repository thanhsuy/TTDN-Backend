package com.haui.btl.demo.Entity;

import java.io.Serializable;
import java.util.Objects;

public class TermofuseId implements Serializable {

    private Integer id;
    private String termType;

    // Getter và Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TermofuseId that = (TermofuseId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(termType, that.termType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, termType);
    }
}
