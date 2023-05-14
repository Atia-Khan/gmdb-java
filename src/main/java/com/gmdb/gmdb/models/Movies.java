package com.gmdb.gmdb.models;
// import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Movies {
    // 1. As a user
    //    I can GET a list of movies from GMDB that includes Movie ID | Movie Title | Year Released | Genre | Runtime
    //    so that I can see the list of available movies.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long id;
    private String title;
    private int movieYear;
    private String genre;
    private String movieRuntime;

    public Movies() {} //why it is used?

    public Movies( String title, int movieYear, String genre, String movieRuntime){
            
                    this.title = title;
                    this.movieYear = movieYear;
                    this.genre = genre;
                    this.movieRuntime = movieRuntime;
    }

    // @Override
    // public String toString(){
    //     return String.format("Movies[id=%d, title='%s', movieYear=%d, genre='%s', movieRuntime= '%s' ]", id, title, movieYear, genre, movieRuntime);
    // }

    // public String getTitle() {
    //     return title;
    // }

    // public long getId() {
    //     return id;
    // }

    

    // public int getMovieYear() {
    //     return movieYear;
    // }

  

    // public String getGenre() {
    //     return genre;
    // }

    // // public void setGenre(String genre) {
    // //     this.genre = genre;
    // // }

    // public String getMovieRuntime() {
    //     return movieRuntime;
    // }

    // public void setMovieRuntime(String movieRuntime) {
    //     this.movieRuntime = movieRuntime;
    // }

    
}