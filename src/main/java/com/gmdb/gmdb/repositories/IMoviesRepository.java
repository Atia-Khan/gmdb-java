package com.gmdb.gmdb.repositories;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gmdb.gmdb.models.Movies;

// @Repository
public interface IMoviesRepository extends JpaRepository<Movies, Long> {

    // Why Long is used instead of long?

    // List<Movies> findAll();

    Movies findById(long id);

    // void update(Movies moviesList);

    // List<Movies> getAllMovies();

}