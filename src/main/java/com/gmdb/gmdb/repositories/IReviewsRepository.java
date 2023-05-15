package com.gmdb.gmdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gmdb.gmdb.models.Reviews;

@Repository
public interface IReviewsRepository extends CrudRepository<Reviews, Long> {

}
