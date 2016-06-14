package com.leatherswan.artisticendeavors.dao;

import java.util.List;

import com.leatherswan.artisticendeavors.model.ListItem;
import com.leatherswan.artisticendeavors.model.Visitor;

/**
 * Created by aks5816 
 * CRUD = Create (save), Read (find), Update, Delete
 * Plus find*ByUsername, findAll*, countAll*
 */
public interface VisitorDao {
	
	Visitor saveVisitor(Visitor customer) ;
	Visitor findVisitor(Integer id) ;
	Visitor updateVisitor(Visitor customer) ;
	void deleteVisitor(Visitor customer) ;
    Visitor findVisitorByUsername(String username) ;
    List<Visitor> findAllVisitors();
    int countAllVisitors();

    ListItem saveCartItem(ListItem cartItem) ;
    ListItem findCartItem(Integer id) ;
    ListItem updateCartItem(ListItem cartItem) ;
	void deleteCartItem(ListItem cartItem) ;
    List<ListItem> findCartItemsByCustomer(Visitor customer);
    List<ListItem> findAllCartItems();
    int countAllCartItems();
   
}
