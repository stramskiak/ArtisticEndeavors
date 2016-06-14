package com.leatherswan.artisticendeavors.dao;

import java.util.LinkedList;
import java.util.List;

import com.leatherswan.artisticendeavors.enums.GenreType;
import com.leatherswan.artisticendeavors.model.Artist;
import com.leatherswan.artisticendeavors.model.Item;
import com.leatherswan.artisticendeavors.model.Genre;
import com.leatherswan.artisticendeavors.model.Review;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 * simple single-table instance.
 */
@Repository("itemDao")
public class ItemDaoImpl implements ItemDao {

	/** The session entity manager */
	@PersistenceContext
	private EntityManager em ;
	

	/** The Constant log. */
	static final Logger log = LoggerFactory.getLogger(ItemDaoImpl.class);

	/**
	 * First list of all items from database.
	 *
	 * @return the list
	 */
	@Override
	public List<Item> listAllItems() {
		return findAllItems();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#saveItem(com.leatherswan.artisticendeavors.model.Item)
	 */
	@Override
	public Item saveItem(Item item) {
		em.persist(item);
		em.flush();
		log.info("saved item: " + item.getTitle());
		return this.findItemByItemItemid(item);
	}
	

	/* (non-Javadoc)
 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findItem(com.leatherswan.artisticendeavors.model.Item)
 */
	@Override
	public Item findItemByItemItemid(Item item) {
		return findItemByItemid(item.getItemid());
	}


	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#updateItem(com.leatherswan.artisticendeavors.model.Item)
	 */
	@Override
	public void updateItemByItemItemid(Item item) {

		Item itemUpdate = null;
		if (item == null || item.getItemid() == null) {
			throw new IllegalArgumentException(
					"Invalid ItemId for item update: NULL");

		} else { // get the DAO item to be updated

			itemUpdate = findItemByItemid(item.getItemid());

			if (item.getTitle() != null) {
				itemUpdate.setTitle(item.getTitle());
			}
			if (item.getDescription() != null) {
				itemUpdate.setDescription(item.getDescription());
			}
			if (item.getPrice() >= 0.0) {
				itemUpdate.setPrice(item.getPrice());
			}
			if (item.getDiscount() >= 0.0) {
				itemUpdate.setDiscount(item.getDiscount());
			}
			if (item.getArtists() != null) {
				itemUpdate.setArtists(item.getArtists());
			}
			if (item.getGenre() != null) {
				itemUpdate.setGenre(item.getGenre());
			}

			this.saveItem(itemUpdate);
		}
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#deleteItem(com.leatherswan.artisticendeavors.model.Item)
	 */
	@Override
	public boolean deleteItem(Item item) {
		boolean success = false;
		if (em.getReference(Item.class, item.getId()) != null) {
			em.remove(item);
			em.flush();
			success = true;
		} else {
			throw new IllegalArgumentException("Item isbn does not exist: "
					+ item.getItemid());
		}
		return success;
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findItemByIsbn(java.lang.String)
	 */
	@Override
	public Item findItemByItemid(String itemid) {
		Item foundItem = null;
		if (itemid != null) {
			foundItem = em
					.createQuery(
							"SELECT b FROM Item b WHERE b.itemid LIKE :finditemid",
							Item.class).setParameter("findidemid", itemid)
					.getSingleResult();
		}
		return foundItem;
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findItemsPerGenre()
	 */
	@Override
	public List<Object[]> findItemsPerGenre() {
		return em.createQuery(
						"SELECT b.genre.genreType,  "
						+ "COUNT(b.genre.genreType) AS itemsPerGenre "
						+ "FROM Item b "
						+ "GROUP BY b.genre.genreType "
						+ "ORDER BY itemsPerGenre DESC", Object[].class)
				.getResultList();
		}

	
	
	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findAllItems()
	 */
	@Override
	public List<Item> findAllItems() {
//		return em.createQuery("Select b FROM Item b", Item.class).getResultList();
		return em.createQuery("FROM Item b", Item.class).getResultList();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#countItems()
	 */
	@Override
	public int countItems() {
		return em.createQuery("Select b FROM Item b", Item.class).getResultList().size();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#saveAuthor(com.leatherswan.artisticendeavors.model.Author)
	 */
	@Override
	public Artist saveArtist(Artist artist) {
		if (artist.getId() == null) { // insert
			em.persist(artist);
		} else { // update
			em.merge(artist);
		}
		em.flush();
		// return the dao saved artist
		return findArtistByArtistLastnameFirstname(artist);
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findAuthor(com.leatherswan.artisticendeavors.model.Author)
	 */
	@Override
	public Artist findArtistByArtistLastnameFirstname(Artist artist) 
			throws IllegalArgumentException  {
		return em
				.createQuery(
						"SELECT a FROM Artist a WHERE a.firstname, a.lastname LIKE :findfirst, :findlast"
						,Artist.class)
				.setParameter("findfirst", artist.getFirstname())
				.setParameter("findlast", artist.getLastname())
				.getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findAuthorByLastname(java.lang.String)
	 */
	@Override
	public Artist findArtistByLastname(String lastname) {
		return em
				.createQuery(
						"SELECT a FROM Author a WHERE a.lastname LIKE :findartist",
						Artist.class)
				.setParameter("findartist", lastname)
				.getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#updateAuthor(com.leatherswan.artisticendeavors.model.Author)
	 */
	@Override
	public Artist updateArtist(Artist artist)
			 throws IllegalArgumentException {

		if (artist.getId() == null) {
			throw new IllegalArgumentException(
					"Invalid ID for artist update: NULL");
		} else {
			em.merge(artist);
		}
		em.flush();
		return findArtistByArtistLastnameFirstname(artist);
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#deleteArtist(com.leatherswan.artisticendeavors.model.Author)
	 */
	@Override
	public void deleteArtist(Artist artist) 
	throws IllegalArgumentException {
		
		if(artist.getId() == null) {
			throw new IllegalArgumentException(
					"DELETE ERROR: Artist does not exist in DAO table: "
							+ artist.getLastname());
		}

		if (em.getReference(Item.class, artist.getId()) != null) {
			em.remove(artist);
			em.flush();
		} 
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findAllArtists()
	 */
	@Override
	public List<Artist> findAllArtists() {

		return em.createQuery("SELECT a FROM Artist a", Artist.class)
				.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#countArtists()
	 */
	@Override
	public int countArtists() {

		return em.createQuery("FROM Artist").getResultList().size();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#saveGenre(com.leatherswan.artisticendeavors.model.Genre)
	 */
	@Override
	public GenreType saveGenreType(GenreType genreType) {

		Genre saveGenre = new Genre();
		saveGenre.setGenreType(genreType);
		if (findGenreByType(genreType) == null) { // insert
			em.persist(saveGenre);
		} else { // update
			em.merge(saveGenre);
		}
		em.flush();
		log.info("saved genre type : " + genreType);
		return findGenreByType(genreType);
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findGenreByType(java.lang.String)
	 */
	@Override
	public GenreType findGenreByType(GenreType genreType) {

		Genre genre = em.createQuery(
						"SELECT g FROM Genre g WHERE g.genreType = :findGenre",
						Genre.class).setParameter("findGenre", genreType)
				.getSingleResult();
		return genre.getGenreType();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#updateGenre(com.leatherswan.artisticendeavors.model.Genre)
	 */
	@Override
	public GenreType updateGenreType(GenreType genreType) {
		Genre saveGenre = new Genre();
		saveGenre.setGenreType(genreType);

		if (findGenreByType(genreType) == null) {
			throw new IllegalArgumentException(
					"Invalid genre for update: NULL");
		} else {
			em.merge(saveGenre);
		}
		em.flush();
		return findGenreByType(genreType);
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#deleteGenre(com.leatherswan.artisticendeavors.model.Genre)
	 */
	@Override
	public void deleteGenreType(GenreType genre) {

		if (em.find(Genre.class, genre) != null) {
			em.remove(genre);
			em.flush();
		} else {
			throw new IllegalArgumentException(
					"GenreType for delete does not exist: " + genre);
		}
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findAllGenres()
	 */
	@Override
	public List<GenreType> findAllGenreTypes() {

		return em.createQuery("FROM Genre", GenreType.class).getResultList();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#countGenres()
	 */
	@Override
	public int countGenreTypes() {

		return em.createQuery("FROM Genre").getResultList().size();
	}

	/**
	 * Gets the reviews.
	 *
	 * @return the reviews
	 */
	@Override
	public List<Review> getReviews() {
		return em.createQuery("SELECT r FROM Review r", Review.class)
				.getResultList();
	}

	/**
	 * Gets the review by isbn.
	 *
	 * @param isbn the isbn
	 * @return the review by isbn
	 */
	@Override
	public List<Review> getReviewByItemid(String isbn) {
		return em
				.createQuery(
						"SELECT r FROM Review r WHERE b.isbn LIKE :findisbn",
						Review.class).setParameter("findisbn", isbn)
				.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#saveReview(com.leatherswan.artisticendeavors.model.Review)
	 */
	@Override
	public Review saveReview(Review review) {
		if (review.getId() == null) { // insert
			em.persist(review);
		} else { // update
			em.merge(review);
		}
		em.flush();
		log.info("saved review from " + review.getUsername());
		return findReview(review);
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findReview(com.leatherswan.artisticendeavors.model.Review)
	 */
	@Override
	public Review findReview(Review review) {
		return em
				.createQuery(
						"SELECT r FROM Review r WHERE r.username = :findUser AND r.item = :findItem",
						Review.class)
				.setParameter("findUser", review.getUsername())
				.setParameter("findIsbn", review.getItemid()).getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#updateReview(com.leatherswan.artisticendeavors.model.Review)
	 */
	@Override
	public Review updateReview(Review review) {

		if (review.getId() == null) {
			throw new IllegalArgumentException(
					"Invalid ID for genre update: NULL");
		} else {
			em.merge(review);
		}
		em.flush();
		return findReview(review);
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#deleteReview(com.leatherswan.artisticendeavors.model.Review)
	 */
	@Override
	public void deleteReview(Review review) {

		if (em.find(Review.class, review) != null) {
			em.remove(review);
			em.flush();
		} else {
			throw new IllegalArgumentException(
					"Review for delete does not exist: " + review.getId());
		}
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findAllReviews()
	 */
	@Override
	public List<Review> findAllReviews() {
		return em.createQuery("FROM Review", Review.class).getResultList();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#countReview()
	 */
	@Override
	public int countReview() {
		return em.createQuery("FROM Review", Review.class).getResultList()
				.size();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findItemsByGenre(com.leatherswan.artisticendeavors.model.Genre)
	 */
	@Override
	public List<Item> findItemsByGenreType(GenreType genreType) {
		List<Item> foundGenres = null;
		if (genreType != null) {
			foundGenres = em
				.createQuery(
						"SELECT b FROM Item b left join b.genre g "
						+ "WHERE g.genreType = :findGenre",
						Item.class)
				.setParameter("findGenre", genreType)
                .setHint("org.hibernate.cacheable", true)
				.getResultList();
		}
		return foundGenres;
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findReviewsByIsbn(java.lang.String)
	 */
	@Override
	public List<Review> findReviewsByItemid(String itemid) {
		List<Review> foundReviews = null;
		if (itemid != null) {
			foundReviews = em
					.createQuery(
							"SELECT r FROM Review r WHERE r.itemid LIKE :finditemid",
							Review.class).setParameter("finditemid", itemid)
					.getResultList();
		}
		return foundReviews;
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#searchItemByCriteria(com.leatherswan.artisticendeavors.model.Item)
	 */
	@Override
	public List<Item> searchItemByItemCriteria(Item item) {
		Session session = (Session) em.getDelegate();
		Example example = Example.create(item).enableLike(MatchMode.ANYWHERE).ignoreCase();
		Criteria criteria = session.createCriteria(Item.class).add(example);
		List<Item> items = criteria.list();
		return items;
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#searchItemByCriteria(com.leatherswan.artisticendeavors.model.Author)
	 */
	@Override
	public List<Item> searchItemByArtistCriteria(Artist artist) {
		List<Item> items = new LinkedList<Item>();
		Session session = (Session) em.getDelegate();
		Example example = Example.create(artist).enableLike(MatchMode.ANYWHERE).ignoreCase();
		Criteria criteria = session.createCriteria(Artist.class).add(example);
		List<Artist> artists = criteria.list();
		for(Artist each : artists) {
			for(Item artistItem : findItemsByArtistLastname(each.getLastname())) {
				items.add(artistItem);
			}
		}
		return items;
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findItemsByAuthorLastname(java.lang.String)
	 */
	@Override
	public List<Item> findItemsByArtistLastname(String artistLastname) {
		return em
				.createQuery(
						"SELECT b FROM Item b left join b.artists a "
						+ "WHERE a.lastname LIKE :findArtistLastname",
						Item.class)
				.setParameter("findArtistLastname", artistLastname)
				.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.leatherswan.artisticendeavors.dao.ItemDao#findAuthorsByItemIsbn(java.lang.String)
	 */
	@Override
	public List<Artist> findArtistsByItemItemid(String isbn) {
		Integer itemId = findItemByItemid(isbn).getId();
		return em
				.createQuery(
						"SELECT a FROM Author a join a.items b "
						+ "WHERE b.id = :finditemid",
						Artist.class)
				.setParameter("finditemid", itemId)
				.getResultList();
	}


	@Override
	public List<Item> findItemsByGenre(String genre) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public GenreType findGenreByName(String genreName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Statistics getHibernateStatistics() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void printEhcacheStatistics() {
		// TODO Auto-generated method stub
		
	}


}
