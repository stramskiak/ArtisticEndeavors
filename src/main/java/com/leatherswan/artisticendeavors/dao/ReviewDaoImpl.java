package com.leatherswan.artisticendeavors.dao;

import com.leatherswan.artisticendeavors.model.Review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.*;

/**
 * 
 */
@Repository("reviewDao")
public class ReviewDaoImpl implements ReviewDao {

	@PersistenceContext
	private EntityManager em;

	static final Logger log = LoggerFactory.getLogger(ReviewDaoImpl.class);

	@Override
	public Review saveReview(Review review) {
		if (review.getId() == null) { // insert
			em.persist(review);
		} else { // update
			em.merge(review);
		}
		em.flush();
		log.info("saved review " + review.getId());
		return review;
	}

	@Override
	public Review findReview(Integer id) {
		return em.find(Review.class, id);
	}

	@Override
	public Review updateReview(Review review) {
		if (review.getId() == null) {
			throw new IllegalArgumentException(
					"Invalid ID for customer update: NULL");
		} else {
			em.merge(review);
		}
		em.flush();
		return review;
	}

	@Override
	public boolean deleteReview(Review review) {
		if (em.getReference(Review.class, review.getId()) != null) {
			em.remove(review);
			em.flush();
			return true;
		} else {
			return false;
//			throw new IllegalArgumentException(
//					"DELETE ERROR: User does not exist: " + review.getUsername());
		}
	}

	public List<Review> listAll() {
		return em.createQuery("FROM Review", Review.class).getResultList();
	}

	@Override
	public List<Review> findReviewsByItemid(String itemid) {
		return em
				.createQuery(
						"SELECT r FROM Review r "
						+ "WHERE r.itemid LIKE :finditemid"
								, Review.class)
				.setParameter("finditemid", itemid)
				.getResultList();
	}

	@Override
	public List<Review> findAllReviews() {
		return em.createQuery("SELECT r FROM Review r", Review.class)
				.getResultList(); // also "FROM User"
	}

	@Override
	public int countAllReviews() {
		return em.createQuery("FROM Review").getResultList().size();
	}

}
