package com.leatherswan.artisticendeavors.model;

import java.io.Serializable;
import java.util.Date;

public class VisitorManager implements Serializable {

	/**
	 * his userid has name, email, address
	 */
	private static final long serialVersionUID = 1L;

	private Visitor userInfo = null;
	private FavoritesList favs = null;
	
	/**
	 * 
	 */
	public VisitorManager() {
	}

	public VisitorManager(Visitor newUserInfo) {
		userInfo = newUserInfo;
		String username = newUserInfo.getUsername();
		setCart(new FavoritesList(username));
	}

	/**
	 * UserInfo
	 */
//	@Override
	public Visitor getUserInfo() {
		return userInfo;
	}
	
	public void setCustomerInfo(Visitor newUserInfo) {
		userInfo = newUserInfo;
	}
	
	public FavoritesList getCart() {
		return favs;
	}

	public void setCart(FavoritesList cart) {
		this.favs = cart;
	}

	  @Override
	  public String toString() {
	    return "User{" +
	        ", userInfo='" + userInfo + '\'' +
	        '}';
	  }

	  public static class Builder {
	    private Visitor visitor = null;

	    public Builder() {
	      visitor = new Visitor();
	    }

	    public Builder builder() {
	      return new Builder();
	    }

	    public Builder userName(String username) {
	      visitor.setUsername(username);
	      return this;
	    }

	    public Builder firstName(String firstName) {
	      visitor.setFirstName(firstName);
	      return this;
	    }

	    public Builder lastName(String lastName) {
	      visitor.setLastName(lastName);
	      return this;
	    }

	    public Builder activeSince(Date  activeSince) {
	      visitor.setActiveSince(activeSince);
	      return this;
	    }

	    public Visitor build() {
	      //validate ??;
	      return visitor;
	    }
	  }

}
