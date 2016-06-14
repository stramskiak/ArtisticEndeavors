package com.leatherswan.artisticendeavors.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//implements interface with getter and setter definitions
public class SimpleItemManager implements ItemManagerIF {

	private HashMap<String, Item> mapitems;
	
	/**
	 * 
	 */
	public SimpleItemManager() {
	}

	public SimpleItemManager(final List<Item> newitems) {
		setItems(newitems);
	}

	public void setItems(final List<Item> newitems) {
			mapitems = new HashMap<String, Item>();
			if (!newitems.isEmpty()) {
				for (Item item: newitems) {
					mapitems.put(item.getItemid(), item);
				}
			}
	}

	@Override
	public List<Item> getItems() {
		List<Item> itemList ;
		if (mapitems == null || mapitems.isEmpty()) {
			return null;
			} else {
				itemList = new ArrayList<Item>();
				for (Item item : mapitems.values()) {
					itemList.add(item);
				}
				return itemList;
			}
	}

	@Override
	public Item getItemByItemid(final String isbn) {
		Item oneItem = mapitems.get(isbn);
		return oneItem;
	}

	@Override
	public boolean removeItemByItemid(String isbn) {
        Item item = getItemByItemid(isbn);
        if (item != null) {
            mapitems.remove(item);
            return true;
        }
        return false;
	}

	@Override
	public boolean updateItem(Item item) {
        Item oldItem = getItemByItemid(item.getItemid());
        if (oldItem == null) {
            return false;
        } else {
            mapitems.remove(oldItem);
            mapitems.put(item.getItemid(), item);
      }
        return true;
	}

	@Override
	public boolean addItem(Item item) {
        Item finditem = getItemByItemid(item.getItemid());
        if (finditem == null) {
        	mapitems.put(item.getItemid(), item);
        } else {
          return true;
      }
      return false;
	}

	@Override
	public List<Review> getReviews() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> getReviewsByItemid(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addReview(Review review) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeReviewByItemid(String isbn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateReview(Review review) {
		// TODO Auto-generated method stub
		return false;
	}

}
