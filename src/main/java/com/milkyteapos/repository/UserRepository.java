package com.milkyteapos.repository;

import com.milkyteapos.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User save(User user);
    User findById(int id);
    User findByCode(String code);
    User findByUserName(String userName);
}