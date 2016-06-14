/**
 * 
 */
package com.leatherswan.artisticendeavors.service;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leatherswan.artisticendeavors.model.ListItem;

/**
 * @author Anita
 *
 */
public class FavoritesList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(FavoritesList.class);


	private List<ListItem> favoritesItems = null;
	private String username = null;


	public FavoritesList() {
	}
	
	public FavoritesList(String username) {
		this.username = username;
		favoritesItems = new LinkedList<ListItem>();
	}

	/**
	 * @return the name
	 */
	public FavoritesList getFavoritesList() {
		return this;
	}

	public void setFavoritesItems(List<ListItem> favsItems) {
		this.favoritesItems = favsItems;
	}

	/**
	 * Gets the all shopping cart items.
	 *
	 * @return the all shopping cart items
	 */
	public final List<ListItem> getFavoritesItems() {
		return favoritesItems;
	}

	/**
	 * Gets sum of item quantity over all shopping cart items.
	 *
	 * @return the quantity of shopping cart items, 0 if empty or null cart
	 */
	public Integer numFavoritesItems() {
		Integer number = 0;
		if (favoritesItems != null && !favoritesItems.isEmpty()) {
			number = favoritesItems.size();
		}
		return number;
	}

	public ListItem getFavoritesItem(Integer favsItemIndex) {
		return favoritesItems.get(favsItemIndex);
	}

	public ListItem getFavoriteItemByItemId(String favsItemIsbn) {
		ListItem foundItem = null;
		for (ListItem item : favoritesItems) {
			if (item.getItem().getItemid().equals(favsItemIsbn)) {
				foundItem = item;
				break;
			}
		}
		return foundItem;
	}

	/**
	 * Adds the shopping cart item.
	 *
	 * @param newItem the new item
	 */
	public void setFavoriteItem(ListItem favsItem) {
		favoritesItems.add(favsItem);
	}

	/**
	 * Removes the shopping cart item.
	 *
	 * @param removeItemIndex the remove item index
	 */
	public final void removeCartItem(final String itemid) {
		favoritesItems.remove(itemid);
		}

	/**
	 * Find books in shopping cart.
	 *
	 * @param isbn the test item
	 * @return the index of the found item
	 */
	public Boolean itemInFavs(final String isbn) {
		Boolean inFavs = false;
		for (ListItem cartItem : favoritesItems) {
				if (cartItem.getItem().getItemid().contentEquals(isbn)) {
					inFavs = true;
				}
			}
		return inFavs;
	}

	public void setUsername(String username) {
		this.username = username;
		
	}

	public String getUsername() {
		return username;
		
	}

}
