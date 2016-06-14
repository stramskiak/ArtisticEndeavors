package com.leatherswan.artisticendeavors.dao;

import com.leatherswan.artisticendeavors.model.ListItem;
import com.leatherswan.artisticendeavors.model.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.*;

/**
 * 
 */
@Repository("visitorDao")
public class VisitorDaoTemplate implements VisitorDao {

	@PersistenceContext
	private EntityManager em;

	static final Logger log = LoggerFactory.getLogger(VisitorDaoTemplate.class);

	@Override
	public Visitor saveVisitor(Visitor customer) {
		if (customer.getId() == null) { // insert
			em.persist(customer);
		} else { // update
			em.merge(customer);
		}
		em.flush();
		log.info("saved customer " + customer.getId());
		return customer;
	}

	@Override
	public Visitor findVisitor(Integer id) {
		return em.find(Visitor.class, id);
	}

	@Override
	public Visitor updateVisitor(Visitor customer) {
		if (customer.getId() == null) {
			throw new IllegalArgumentException(
					"Invalid ID for customer update: NULL");
		} else {
			em.merge(customer);
		}
		em.flush();
		return customer;
	}

	@Override
	public void deleteVisitor(Visitor customer) {
		if (em.getReference(Visitor.class, customer.getId()) != null) {
			em.remove(customer);
			em.flush();
		} else {
			throw new IllegalArgumentException(
					"DELETE ERROR: User does not exist: " + customer.getUsername());
		}
	}

	public List<Visitor> listAll() {
		return em.createQuery("FROM Customer", Visitor.class).getResultList();
	}

	@Override
	public Visitor findVisitorByUsername(String customername) {
		return em
				.createQuery(
						"SELECT c FROM Customer c "
						+ "WHERE c.username LIKE :findcustomer"
								, Visitor.class)
				.setParameter("findcustomer", customername)
				.getSingleResult();
	}

	@Override
	public List<Visitor> findAllVisitors() {
		return em.createQuery("SELECT c FROM Customer c", Visitor.class)
				.getResultList(); // also "FROM User"
	}

	@Override
	public int countAllVisitors() {
		return em.createQuery("FROM Customer").getResultList().size();
	}

	@Override
	public ListItem saveCartItem(ListItem cartItem) {
		if (em.contains(cartItem)) { // insert
			em.merge(cartItem);
		} else { // update
			em.persist(cartItem);
		}
		log.info("saved cartItem " + cartItem.getItem().getItemid());
		em.flush();
		return cartItem;
	}

	@Override
	public ListItem findCartItem(Integer id) {
		return em.find(ListItem.class, id);
	}

	@Override
	public ListItem updateCartItem(ListItem cartItem) {
		if (em.contains(cartItem)) { // insert
			em.merge(cartItem);
		} else { // update
			em.persist(cartItem);
		}
		log.info("saved cartItem " + cartItem.getItem().getItemid());
		em.flush();
		return cartItem;
	}

	@Override
	public void deleteCartItem(ListItem cartItem) {
		if (em.find(ListItem.class, cartItem) != null) {
			em.remove(cartItem);
			em.flush();
		} else {
			throw new IllegalArgumentException(
					"cartItem for delete does not exist: "
							+ cartItem.getItem().getItemid());
		}
	}

	@Override
	public List<ListItem> findCartItemsByCustomer(Visitor customer) {
		return em
				.createQuery(
				"FROM CartItem s "
						+ "WHERE s.customer = :findcustomer"
						, ListItem.class)
				.setParameter("findcustomer", customer)
				.getResultList();
	}

	@Override
	public List<ListItem> findAllCartItems() {
		return em.createQuery("FROM CartItem s ", ListItem.class)
				.getResultList();
	}

	@Override
	public int countAllCartItems() {
		return em
				.createQuery("SELECT s FROM CartItem s", ListItem.class)
				.getResultList().size();
	}
}
