package com.leatherswan.artisticendeavors.app;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.leatherswan.artisticendeavors.enums.CurrentViewForReturn;
import com.leatherswan.artisticendeavors.model.*;
import com.leatherswan.artisticendeavors.service.ItemServiceImpl;
import com.leatherswan.artisticendeavors.dao.ItemDaoImpl;


/**
 * Handles requests for the application home page.
 * @Autowired (or @Resource(name="beanId")) 
 * - Notifies the framework that this property is to be wired to a bean
 * @RequestParam - Asks the framework to extract a request parameter. 
 *   RequestParam defined as a Map will get them all.
 * @ResponseBody - Tells the server that the return 
 *   object (not view, ModelAndView, etc.) from method 
 *   should be inserted directly into the body of the Response object. 
 *   Overrides all default behavior, invokes Jackson data-bind functions 
 *   to write our object out in JSON format. 
 *   object must have getters and setters for all needed data. 
 * JSON and AJAX allows communication with a web page 
 * without completely reloading it
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

//	@Autowired
	@Resource(name="itemDao")
	private ItemDaoImpl itemDao;
	
	private SimpleItemManager itemManager;
	
    @RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
	public String home(
			@ModelAttribute Visitor user, 
			Model model,
			HttpServletRequest requestParam, 
			HttpServletResponse responseParam,
			HttpSession sessionParam,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam() Map<String, String> params
			) {

		HttpSession session = requestParam.getSession();

		 logger.info("New HttpSession, ID = " + session.getId());

		if (itemManager == null) {
			itemDao = new ItemDaoImpl();
			itemManager = new SimpleItemManager(itemDao.listAllItems());
		} else {
			if (itemManager.getItems().size()<1) {
				itemManager.setItems(itemDao.findAllItems());
				}
		}
		
		session.setAttribute("items", itemManager.getItems());
		model.addAttribute("items", itemManager.getItems());
	

		session.setAttribute("view", CurrentViewForReturn.home);
		return "home";
	}

	@RequestMapping(value = "/see_details", method = RequestMethod.GET)
	public String details(@RequestParam String itemid, HttpSession session,
			Model model) {

		// if item is found display detail, else redirect home

		Item item = itemManager.getItemByItemid(itemid);
		if (item == null) {
			logger.info("Item detail error (" + itemid + ")");
			return "redirect:";
		}
		model.addAttribute("item", item);
		session.setAttribute("view", CurrentViewForReturn.detail);

		return "detailView";

	}


	@ResponseBody
	@JsonView(Review.Content.class)
	@RequestMapping(value = "/reviews", method = RequestMethod.POST)
	public List<Review> getAjaxReview(
			@RequestParam String itemid,
			HttpSession session) {
		logger.info("Review ISBN = " + itemid);

		ItemServiceImpl items = 
				(ItemServiceImpl) session.getAttribute("items");
		if (items == null) {
			items = new ItemServiceImpl();
			Review newReview = newItemReview(itemid, "george3", "Great Item!");
			items.createReview(newReview);
			newReview = newItemReview(itemid, "helpMe", "Great Item!");
			items.createReview(newReview);
		}
		
//		List<Review> isbnReviews = new ArrayList<Review>();
//		for (Review findReview : items.getReviews()) {
//			if (findReview..getIsbn().equalsIgnoreCase(isbn)) {
//				isbnReviews.add(findReview);
//				isbnReviews = items.getReviewsByIsbn(isbn);
//				}
//		}

		return items.findReviewsByItemid(itemid);
	}

	@ResponseBody
	@RequestMapping(value = "/add_review", method = RequestMethod.POST)
	public List<Review> setAjaxReview(
			@RequestParam String itemid,
			@RequestParam String username,
			@RequestParam String reviewtext,
			HttpSession session) {
		logger.info("Add Review ISBN = " + itemid);
		ItemServiceImpl items = 
				(ItemServiceImpl) session.getAttribute("reviews");
		if (items == null) {
			items = new ItemServiceImpl();
		}
		Review newReview = newItemReview(itemid, username, reviewtext);
		items.createReview(newReview);
//		Map<String, String> set = new HashMap<String,String>();
		session.setAttribute("items", items);
		
//		List<Review> isbnReviews = new ArrayList<Review>();
//		for (Review review : items.getReviews()) {
//			if (review.getIsbn().contentEquals(isbn)) {
//				isbnReviews.add(review);
//			}
//		}

		return items.findReviewsByItemid(itemid);
	}
	
	public Review newItemReview(
			String itemid, String username, String reviewText) {	
//		Timestamp date = new Timestamp( System.currentTimeMillis() );
		String rightNow = new Date().toString();
		 logger.info("Review Date = " + rightNow);

		Review newReview = new Review();
//		Date date = new Date();
//		newReview.setItemId(isbn);
        newReview.setDateOfReview(rightNow);
//		newReview.setDate(date);
		newReview.setUsername(username);
		newReview.setText(reviewText);

		return newReview;
		
	}

}