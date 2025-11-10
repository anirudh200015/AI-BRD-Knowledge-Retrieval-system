package com.project.AIPoweredBRD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.AIPoweredBRD.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

}
