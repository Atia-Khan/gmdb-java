package com.gmdb.gmdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gmdb.gmdb.models.Users;

@Repository
public interface IUserRepository extends CrudRepository<Users, Long>  {
    
}
