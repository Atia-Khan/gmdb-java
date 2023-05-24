package com.gmdb.gmdb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

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

    @PostMapping("/update")
    public String updateReview(@RequestBody Reviews reviews){
        this.repo.save(reviews);
        return "Review updated";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        this.repo.deleteById(id);
        return "Deleted";
    }

}
