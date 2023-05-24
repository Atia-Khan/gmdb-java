package com.gmdb.gmdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmdb.gmdb.models.Users;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {

    Users findById(long i);

}
