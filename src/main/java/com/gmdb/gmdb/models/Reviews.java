package com.gmdb.gmdb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private long id;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movies movieId;
    private String reviewText;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users userId;

    public Reviews(String reviewText) {
        // this.id = id;
        // this.movieId = movieId;
        this.reviewText = reviewText;
        // this.userId = userId;
    }

    public Reviews(long id, Movies movie, Users user, String reviewText){
        this.id = id;
        this.movieId = movie;
        this.userId = user;
        this.reviewText = reviewText;
    }

    public Reviews() {
    }
}
