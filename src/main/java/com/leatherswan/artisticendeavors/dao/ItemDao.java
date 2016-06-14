package com.leatherswan.artisticendeavors.dao;

import java.util.List;

import com.leatherswan.artisticendeavors.enums.GenreType;
import com.leatherswan.artisticendeavors.model.Artist;
import com.leatherswan.artisticendeavors.model.Item;
import com.leatherswan.artisticendeavors.model.Review;

/*
  Created by stramskiak on 17/04/15, CRUD = Create (save), Read (list, find),
  Update, Delete
 */
/**
 * The Interface ItemDao.
 */
//public interface ItemDao extends BaseRepository<Item, Long> {
public interface ItemDao {

	  // CRUD
	/**
	 * Save item.
	 * Item is merged in DAO item table if item.id exists 
	 * else item is persisted (saved) in DAO item table
	 *
	 * @param item the item 
	 * @return the item from DAO item table
	 */
	Item saveItem(Item item);

	/**
	 * Find item.
	 * find a item from DAO item table by item.isbn
	 *
	 * @param item the item
	 * @return the item
	 */
	Item findItemByItemItemid(Item item);

	  // Queries
	  List<Item> findItemsByGenre(String genre);

	  GenreType findGenreByName(String genreName) ;

	/**
	 * Update item.
	 * Find DAO item using item.isbn then compare and update content.
	 * Any elements of item which have content over-writes the DAO item elements.
	 *
	 * @param item - the item containing isbn (required) and item elements for update
	 */
	void updateItemByItemItemid(Item item);

	/**
	 * Delete item. 
	 * Uses item.isbn to delete matching DAO item.
	 *
	 * @param item the item
	 * @return 
	 */
	boolean deleteItem(Item item);

	/**
	 * Find all items.
	 * Returns List of item objects in DAO item table.
	 *
	 * @return the list
	 */
	List<Item> findAllItems();

	/**
	 * Count items.
	 * Returns count of item objects in DAO item table.
	 *
	 * @return the int
	 */
	int countItems();

	/**
	 * Save artist.
	 * Artist is merged in DAO artist table if artist.id exists, 
	 * else artist is persisted (saved) in DAO artist table 
	 *
	 * @param artist the artist
	 * @return the artist from DAO artist table
	 */
	Artist saveArtist(Artist artist);

	/**
	 * Find artist.
	 * find artist from DAO artist table 
	 * by matching artist.lastname and artist.firstname
	 *
	 * @param artist the artist
	 * @return the artist
	 */
	Artist findArtistByArtistLastnameFirstname(Artist artist);
	
	/**
	 * Find artist by lastname.
	 * find artist from DAO artist table by matching artist.lastname
	 *
	 * @param lastname the lastname
	 * @return the artist
	 */
	Artist findArtistByLastname(String lastname);

	/**
	 * Update artist.
	 * Valid DAO assigned artist.id is required
	 * Exception is thrown if artist.id is null
	 *
	 * @param artist the artist
	 * @return the artist
	 */
	Artist updateArtist(Artist artist) throws IllegalArgumentException;

	/**
	 * Delete artist.
	 * artist is deleted from DAO artist table using artist.id
	 * Exception is thrown if artist.id is null
	 *
	 * @param artist the artist
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	void deleteArtist(Artist artist) throws IllegalArgumentException;

	/**
	 * Find all artist.
	 *
	 * @return the list
	 */
	List<Artist> findAllArtists();

	/**
	 * Count artist.
	 *
	 * @return the int
	 */
	int countArtists();

	/**
	 * Save review.
	 *
	 * @param genre the genre
	 * @return the review
	 */
	Review saveReview(Review genre);

	/**
	 * Find review.
	 *
	 * @param genre the genre
	 * @return the review
	 */
	Review findReview(Review genre);

	/**
	 * Update review.
	 *
	 * @param genre the genre
	 * @return the review
	 */
	Review updateReview(Review genre);

	/**
	 * Delete review.
	 *
	 * @param genre the genre
	 */
	void deleteReview(Review genre);

	/**
	 * Find all reviews.
	 *
	 * @return the list
	 */
	List<Review> findAllReviews();

	/**
	 * Count review.
	 *
	 * @return the int
	 */
	int countReview();

	/**
	 * Find reviews by isbn.
	 *
	 * @param isbn the isbn
	 * @return the list
	 */
	List<Review> findReviewsByItemid(String isbn);

	/**
	 * Save genre.
	 *
	 * @param genre the genre
	 * @return the genre
	 */
	GenreType saveGenreType(GenreType genreType);

	/**
	 * Find genre by type.
	 * Ensures genre is recorded in tables.
	 *
	 * @param genreType the enum genre type
	 * @return the DAO genre
	 */
	GenreType findGenreByType(GenreType genreType);

	/**
	 * Update genre.
	 *
	 * @param genre the genre
	 * @return the genre
	 */
	GenreType updateGenreType(GenreType genreType);

	/**
	 * Delete genre.
	 *
	 * @param genre the genre
	 */
	void deleteGenreType(GenreType genreType);

	/**
	 * Find all genres.
	 *
	 * @return the list
	 */
	List<GenreType> findAllGenreTypes();

	/**
	 * Count genres.
	 *
	 * @return the int
	 */
	int countGenreTypes();

	/**
	 * Find item by isbn.
	 *
	 * @param isbn the isbn
	 * @return the item
	 */
	Item findItemByItemid(String isbn);

	/**
	 * Find items by genre.
	 *
	 * @param genre the genre
	 * @return the list
	 */
	List<Item> findItemsByGenreType(GenreType genre);

	/**
	 * Find items per genre.
	 *
	 * @return the list
	 */
	List<Object[]> findItemsPerGenre();

	/**
	 * Search item by criteria.
	 * criteria search for item based on partial item value
	 *
	 * @param item the item
	 * @return the list
	 */
	List<Item> searchItemByItemCriteria(Item item);

	/**
	 * Search item by criteria.
	 * criteria search for item based on partial artist value
	 *
	 * @param artist the artist
	 * @return the list
	 */
	List<Item> searchItemByArtistCriteria(Artist artist);

	/**
	 * Find items by artist lastname.
	 *
	 * @param string the string
	 * @return the list
	 */
	List<Item> findItemsByArtistLastname(String string);

	/**
	 * Find artist by item isbn.
	 *
	 * @param string the string
	 * @return the list
	 */
	List<Artist> findArtistsByItemItemid(String string);

	List<Review> getReviewByItemid(String isbn);

	List<Review> getReviews();

	List<Item> listAllItems();


	         // print
	    org.hibernate.stat.Statistics getHibernateStatistics() ;


	  void  printEhcacheStatistics() ;

}
