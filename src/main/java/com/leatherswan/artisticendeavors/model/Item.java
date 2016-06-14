package com.leatherswan.artisticendeavors.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.persistence.Transient;



import com.leatherswan.artisticendeavors.enums.GenreType;

/**
 * A class that maintains information on a base item.
 * a POJO (plane old java object) with getters and setters
 * @author (Stramski)
 * @version (01/26/2015)
 */

@Entity
@Table(name = "ITEM")
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "ITEMID")
	private String itemId;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	  @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	    @JoinTable(
	        name="ITEM_ARTIST",
	        joinColumns={@JoinColumn(name="ITEM_ID", referencedColumnName="ID")},
	        inverseJoinColumns={@JoinColumn(name="ARTIST_ID", referencedColumnName="ID")})
	private List<Artist> artists = new LinkedList<Artist>();

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, mappedBy = "item", fetch=FetchType.EAGER)
	private List<Review> reviews = new LinkedList<Review>();

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "GENRE_ID")
	private Genre genre;

	@Column(name = "PRICE")
	private Double price;

//  @Transient
	@Column(name = "IMAGE")
	private String image;

	@Column(name = "DISCOUNT")
	private Double discount;

	public Item() {
	}
	/**
	 * SQL ID
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * BaseContent : title, artist, price, discount
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public void addArtist(Artist artist) {
		artists.add(artist);
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public String getDiscountInteger() {
		final Integer hundred = 100;
		Integer discountInteger = (int) (discount * hundred);
		return String.valueOf(discountInteger);
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/**
	 * Details : isbn, genre
	 */
	public String getItemid() {
		return itemId;
	}

	public void setItemId(String isbn) {
		this.itemId = isbn;
	}

	public GenreType getGenreType() {
		return genre.getGenreType();
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	/**
	 * image
	 */
	public String getImage() {
//			if (getIsbn().contentEquals("angel01blue01")) {
//				image = "/img/" + getIsbn() + ".gif";
//			} else {
		image = "/img/" + image;
//			}
		return image;
	}
	public void setImage(String imagename) {
		image = imagename;
	}

	/**
	 * description
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static class Builder {
		private Item item;

		public Builder() {
			item = new Item();
		}

		public Builder builder() {
			return new Builder();
		}

		public Builder isbn(String isbn) {
			item.setItemId(isbn);
			return this;
		}

		public Builder title(String title) {
			item.setTitle(title);
			return this;
		}

		public Builder description(String description) {
			item.setDescription(description);
			return this;
		}

		public Builder artist(Artist artist) {
			item.addArtist(artist);
			return this;
		}

//		public Builder genre(Genre genre) {
//			book.setGenre(genre);
//			;
//			return this;
//		}

		public Builder price(Double price) {
			item.setPrice(price);
			return this;
		}

		public Builder discount(Double discount) {
			item.setDiscount(discount);
			return this;
		}

		public Item build() {
			// validate ??;
			return item;
		}
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        Item item = (Item) o;

        if (!itemId.equals(item.itemId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return itemId.hashCode();
    }
}
