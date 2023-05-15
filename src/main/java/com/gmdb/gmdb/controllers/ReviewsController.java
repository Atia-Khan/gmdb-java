package com.gmdb.gmdb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmdb.gmdb.models.Reviews;
import com.gmdb.gmdb.repositories.IReviewsRepository;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    @Autowired
    private IReviewsRepository repo;

    public ReviewsController(IReviewsRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/add")
    public String addReview(@RequestBody Reviews review) {
        this.repo.save(review);
        return "Review Added";
    }

}
