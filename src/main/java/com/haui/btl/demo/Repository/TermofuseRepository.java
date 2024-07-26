package com.haui.btl.demo.Repository;

import com.haui.btl.demo.Entity.Termofuse;
import com.haui.btl.demo.Entity.TermofuseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermofuseRepository extends JpaRepository<Termofuse, TermofuseId> {
}
