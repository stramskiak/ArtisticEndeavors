package com.leatherswan.artisticendeavors.dao;

import java.util.List;

import com.leatherswan.artisticendeavors.model.Review;

/**
 * Created by aks5816 
 * CRUD = Create (save), Read (find), Update, Delete
 * Plus find*ByUsername, findAll*, countAll*
 */
public interface ReviewDao {
	
	Review saveReview(Review review) ;
	Review findReview(Integer id) ;
	Review updateReview(Review review) ;
	boolean deleteReview(Review review) ;
	List<Review> findReviewsByItemid(String itemid);
    List<Review> findAllReviews();
    int countAllReviews();

}
