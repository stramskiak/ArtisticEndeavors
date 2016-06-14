package com.leatherswan.artisticendeavors.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leatherswan.artisticendeavors.model.Artist;
import com.leatherswan.artisticendeavors.model.Item;
import com.leatherswan.artisticendeavors.model.Review;
import com.leatherswan.artisticendeavors.dao.ItemDao;
import com.leatherswan.artisticendeavors.dao.ReviewDao;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private ReviewDao reviewDao;

	public ItemServiceImpl() {
	}
	
	@Override
	public List<Item> findAllItems() {
		return itemDao.findAllItems();
	}

	@Override
	public Item findItemByItemid(String isbn) {
		return itemDao.findItemByItemid(isbn);
	}

	@Override
	public Item createItem(Item item) {
		return itemDao.saveItem(item);
	}

	@Override
	public boolean removeItem(Item item) {
		return itemDao.deleteItem(item);
	}

	@Override
	public boolean updateItem(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Artist> findAllArtists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artist> findArtistsByItemid(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Artist createArtist(Artist artist) {
		// TODO Auto-generated method stub
		return artist;
	}

	@Override
	public boolean removeArtist(Artist artist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateArtist(Artist artist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Review> findAllReviews() {
		return reviewDao.findAllReviews();
	}

	@Override
	public List<Review> findReviewsByItemid(String isbn) {
		return reviewDao.findReviewsByItemid(isbn);
	}

	@Override
	public Review createReview(Review review) {
		reviewDao.saveReview(review);
		return review;
	}

	@Override
	public boolean removeReview(Review review) {
		// TODO Auto-generated method stub
		return reviewDao.deleteReview(review);
	}

	@Override
	public boolean updateReview(Review review) {
		reviewDao.updateReview(review);
		return false;
	}
	

}
