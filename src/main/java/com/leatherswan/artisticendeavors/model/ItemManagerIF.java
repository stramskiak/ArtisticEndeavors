package com.leatherswan.artisticendeavors.model;

import java.util.List;
// interface with an accessor method declaration
// required to enable DI (Dependency Injection)
public interface ItemManagerIF {

	public List<Item> getItems();
	public Item getItemByItemid(String itemid);
	public boolean addItem(Item item);
	public boolean removeItemByItemid(String itemid);
	public boolean updateItem(Item item);
	
	public List<Review> getReviews();
	public List<Review> getReviewsByItemid(String itemid);
	public boolean addReview(Review review);
	public boolean removeReviewByItemid(String itemid); //should be by user and itemid
	public boolean updateReview(Review review);
	
}
