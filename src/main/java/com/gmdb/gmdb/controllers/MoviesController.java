package com.gmdb.gmdb.controllers;

import java.util.List;

// import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmdb.gmdb.models.Movies;
import com.gmdb.gmdb.repositories.IMoviesRepository;

@RestController
@RequestMapping("/movies")

public class MoviesController {

    @Autowired
    private IMoviesRepository movierepo;

    public MoviesController(IMoviesRepository movierepo) {
        this.movierepo = movierepo;
    }

    @GetMapping("/showlist")
    public List<Movies> ViewAllMovies() {
        return movierepo.findAll();
    }

    @PostMapping("/addMovies")
    public String addMovies(@RequestBody Movies moviesList) {
        movierepo.save(moviesList);
        return "Movie Saved as " + moviesList.getTitle();
    }

    // 9. As an Admin
    // I can add update the entry for a movie by providing the data listed in Story
    // 1.
    // so that I can correct errors in previously uploaded movie entries.
    //

    // @PutMapping("/updateMovies")
    // public String updateMovies(@RequestBody Movies moviesList) {
    // movierepo.update(moviesList);
    // return "MovieList updated as " + moviesList.getTitle();
    // }

    @DeleteMapping("/deleteMovies")
    public String deleteMovie(@RequestBody Movies moviesList) {
        movierepo.delete(moviesList);
        return "Book Deleted! " + moviesList.getTitle();
    }

    // @GetMapping("/allMovies")
    // public List<Movies> getAllMovies() {
    // return movierepo.getAllMovies();
    // }
}
