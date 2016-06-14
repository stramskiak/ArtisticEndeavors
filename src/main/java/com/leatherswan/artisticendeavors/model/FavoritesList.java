/**
 * 
 */
package com.leatherswan.artisticendeavors.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		
	private static double TAX = 0.08;


	private List<ListItem> favsItems;
	private String username = null;


	public FavoritesList() {
		favsItems = new LinkedList<ListItem>();
	}

	public FavoritesList(String username) {
		this.username = username;
		favsItems = new LinkedList<ListItem>();
	}

	/**
	 * @return the name
	 */
	public FavoritesList getShoppingCart() {
		return this;
	}

	public void setFavsItems(List<ListItem> favsItems) {
		this.favsItems = favsItems;
	}

	/**
	 * Gets the all shopping cart items.
	 *
	 * @return the all shopping cart items
	 */
	public final List<ListItem> getFavsItems() {
		return favsItems;
	}

	/**
	 * Gets sum of item quantity over all shopping cart items.
	 *
	 * @return the quantity of shopping cart items, 0 if empty or null cart
	 */
	public Integer numFavsItems() {
		Integer number = 0;
		if (favsItems != null && !favsItems.isEmpty()) {
			number = favsItems.size();
		}
		return number;
	}

	public ListItem getFavsItem(Integer favsItemIndex) {
		return favsItems.get(favsItemIndex);
	}

	public ListItem getFavsItemByItemid(String favsItemItemid) {
		ListItem foundItem = null;
		for (ListItem item : favsItems) {
			if (item.getItem().getItemid().equals(favsItemItemid)) {
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
	 * @param iQuantity the i quantity
	 * @param iDiscount the i discount
	 */
	public void setFavsItem(ListItem favsItem) {
		favsItems.add(favsItem);
	}

	/**
	 * Removes the shopping cart item.
	 *
	 * @param removeItemIndex the remove item index
	 */
	public final void removeFavsItem(final String itemid) {
		favsItems.remove(itemid);
		}

	/**
	 * Find books in shopping cart.
	 *
	 * @param itemid the test item
	 * @return the index of the found item
	 */
	public Boolean itemInFavs(final String itemid) {
		Boolean inFavs = false;
		for (ListItem favsItem : favsItems) {
				if (favsItem.getItem().getItemid().contentEquals(itemid)) {
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
