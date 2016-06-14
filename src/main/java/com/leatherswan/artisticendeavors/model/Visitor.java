package com.leatherswan.artisticendeavors.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NamedQueries({
	@NamedQuery(name = "Visitor.findAll", 
               query = "SELECT v from Visitor v")
    , @NamedQuery(name = "Visitor.findByVisitorFirstAndLast ",
                 query = "SELECT v FROM Visitor v "
                         + "WHERE v.firstName = :firstName "
                         + "AND   v.lastName = :lastName ")
	}) 
@Entity
@Table(name="VISITOR")
public class Visitor implements Serializable {

	/**
	 * his userid has name, email, address
	 */
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="USERNAME")
	 @Size(min = 2, max = 20, message = "Username must be between 4 and 20 characters, no spaces")
	private String username;

    @Column(name="PASSWORD")
	@Size(min = 2, max = 20, message = "Password must be between 4 and 20 characters, no spaces")
	private String password;

    @Column(name="EMAIL")
	@Pattern(regexp = ".*@.*", message = "eMail must be valid")
	private String email;

    @Column(name="FIRSTNAME")
	private String firstName;
    
    @Column(name="LASTNAME")
	private String lastName;
    
    @Temporal(TemporalType.DATE)
    @Column(name="ACTIVE_SINCE")
	private Date activeSince;

    @Transient
	SimpleDateFormat printDate = new SimpleDateFormat("yyyy-MM-dd");

    @Transient
	private List<ListItem> cartItems;
    
    @Transient
	private FavoritesList cart = null;

	/**
		 * 
		 */
	public Visitor() {
	}

	public Visitor(String newUsername, String newPassword) {
		username = newUsername;
		password = newPassword;
//		cartItems = new LinkedList<CartItem>();
	}

	  public Integer getId() {
		    return id;
		  }

		  public void setId(Integer id) {
		    this.id = id;
		  }

	/**
	 * Username
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Password
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * eMail
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getActiveSince() {
		return activeSince;
	}

	public void setActiveSince(Date activeSince) {
		this.activeSince = activeSince;
	}

	public List<ListItem> getCartItems() {
		if(cart.getFavsItems() != null) {
			return cart.getFavsItems();
		} else {
			return null;
		}
	}

	public void setCartItems(List<ListItem> cartItems) {
		cart.setFavsItems(cartItems);
	}

	public void addCartItem(ListItem newItem) {
		if (cart==null) {
			cart = new FavoritesList(username);
		}
		cart.setFavsItem(newItem);
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username='" + username + '\'' + ", firstName='"
				+ firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", activeSince=" + activeSince + '}';
	}

	public static class Builder {
		private Visitor user = null;

		public Builder() {
			user = new Visitor();
		}

		public Builder builder() {
			return new Builder();
		}

//	    public Builder id(Integer id) {
//	        user.setId(id);
//	        return this;
//	      }
//
		public Builder username(String username) {
			user.setUsername(username);
			return this;
		}

		public Builder password(String password) {
			user.setPassword(password);
			return this;
		}

		public Builder firstName(String firstName) {
			user.setFirstName(firstName);
			return this;
		}

		public Builder lastName(String lastName) {
			user.setLastName(lastName);
			return this;
		}

		public Builder activeSince(Date activeSince) {
			user.setActiveSince(activeSince);
			return this;
		}

//		public Builder cartItems(List<CartItem> cartItems) {
//			user.setCartItems(cartItems);
//			return this;
//		}

		public Visitor build() {
			// validate ??;
			return user;
		}
	}
}
