package com.haui.btl.demo.Repository;

import com.haui.btl.demo.Entity.Additionalfunctions;
import com.haui.btl.demo.Entity.AdditionalfunctionsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdditionalfunctionsRepository extends JpaRepository<Additionalfunctions, AdditionalfunctionsId> {

    Additionalfunctions findByIdcar(int idcar);
}
