package com.gmdb.gmdb.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gmdb.gmdb.models.Movies;

@Repository
public interface IMoviesRepository extends CrudRepository<Movies, Long>{
    
//Why Long is used instead of long?
   
    List<Movies> findAll();


    Movies findById(long id);  

}