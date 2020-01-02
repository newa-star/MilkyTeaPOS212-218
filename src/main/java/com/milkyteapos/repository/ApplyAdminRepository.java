package com.milkyteapos.repository;

import com.milkyteapos.dataobject.ApplyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyAdminRepository extends JpaRepository<ApplyAdmin, Integer> {
    ApplyAdmin save(ApplyAdmin applyAdmin);
    ApplyAdmin findApplyAdminByCode(String code);
    ApplyAdmin deleteByCode(String code);
}
