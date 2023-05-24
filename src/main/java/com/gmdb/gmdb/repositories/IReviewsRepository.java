package com.gmdb.gmdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmdb.gmdb.models.Reviews;

@Repository
public interface IReviewsRepository extends JpaRepository<Reviews, Long> {

}
