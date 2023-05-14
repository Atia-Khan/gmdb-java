package com.gmdb.gmdb;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMoviesRepository extends CrudRepository<Movies, Long>{
    
//Why Long is used instead of long?
   
    List<Movies> findAll();


    Movies findById(long id);  

}