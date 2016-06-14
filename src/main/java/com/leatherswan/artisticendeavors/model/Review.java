package com.leatherswan.artisticendeavors.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="REVIEW")
public class Review implements Serializable {
	
    private static final long serialVersionUID = 1L;

    public interface Content {};
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	  private Integer id;
    
    @ManyToOne
    private Item item;

    @Column(name = "USERNAME")
    private String username;
    
    @Column(name = "ITEM_ID")
    private String item_id;
    
    @Column(name = "TEXT")
    private String text;
    
    @Column(name = "DATE_OF_REVIEW")
    private String dateOfReview;
    
//	@JsonView(Content.class)
//    @JoinColumn(name="ITEM_ID")
//    public Item getItem() {
//        return item;
//    }
    public void setItem(Item item) {
        this.item = item;
    }
//	@JsonView(Content.class)
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
//	@JsonView(Content.class)
	public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
//	@JsonView(Content.class)
	public String getDateOfReview() {
		return dateOfReview;
	}
	public void setDateOfReview(String date) {
		this.dateOfReview = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItemid() {
		return item_id;
	}
	public void setItemid(String item_id) {
		this.item_id = item_id;
	}
	public static class Builder {
		private Review review;

		public Builder() {
			review = new Review();
		}

		public Builder builder() {
			return new Builder();
		}

		public Builder isbn(String isbn) {
			review.setItemid(isbn);
			return this;
		}

		public Builder text(String text) {
			review.setText(text);
			return this;
		}

		public Builder username(String username) {
			review.setUsername(username);
			return this;
		}

		public Builder item(Item item) {
			review.setItem(item);
			review.setItemid(item.getItemid());
			return this;
		}

		public Builder dateOfReview(String dor) {
			review.setDateOfReview(dor);
			return this;
		}

		public Review build() {
			// validate ??;
			return review;
		}
	}
}
