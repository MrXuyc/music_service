package com.sunshine.basepro.dao;

import com.sunshine.basepro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository extends JpaRepository<User,Integer> {
}
