/**
 * 
 */
package com.leatherswan.artisticendeavors.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Anita
 *
 */
@Entity
@Table(name="LISTITEM")
public class ListItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="VISITOR_ID")
     private Integer visitorId;

    @Transient
	private Item item = null;
    
    @Column(name="ITEM_ID")
    private String itemId = null;
    
    @Transient
	private double cost = 0.0;
	
	public ListItem() {
	}

	public ListItem(Item item) {
		this.item = item;
		this.itemId = item.getItemid();
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
		this.itemId = item.getItemid();
	}


	public Integer getVisitor() {
		return visitorId;
	}

	public void setVisitor(Integer visitor) {
		this.visitorId = visitor;
	}

}
