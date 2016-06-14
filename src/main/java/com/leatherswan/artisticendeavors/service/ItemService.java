package com.leatherswan.artisticendeavors.service;

import java.util.List;

import com.leatherswan.artisticendeavors.model.Artist;
import com.leatherswan.artisticendeavors.model.Item;
import com.leatherswan.artisticendeavors.model.Review;

public interface ItemService {

	List<Item> findAllItems();
	Item findItemByItemid(String itemid);
	Item createItem(Item item);
	boolean removeItem(Item item);
	boolean updateItem(Item item);
	
	List<Artist> findAllArtists();
	List<Artist> findArtistsByItemid(String item);
	Artist createArtist(Artist artist);
	boolean removeArtist(Artist artist);
	boolean updateArtist(Artist artist);

	List<Review> findAllReviews();
	List<Review> findReviewsByItemid(String item);
	Review createReview(Review review);
	boolean removeReview(Review review);
	boolean updateReview(Review review);
	
}
