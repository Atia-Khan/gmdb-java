package com.gmdb.gmdb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JoinColumn(name = "id")
    private long movieId;
    private String reviewText;
    @JoinColumn(name = "id")
    private long userId;

    public Reviews(String reviewText) {
        // this.id = id;
        // this.movieId = movieId;
        this.reviewText = reviewText;
        // this.userId = userId;
    }

    public Reviews() {
    }
}
