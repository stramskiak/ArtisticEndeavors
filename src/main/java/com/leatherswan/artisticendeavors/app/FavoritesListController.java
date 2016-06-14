package com.leatherswan.artisticendeavors.app;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.leatherswan.artisticendeavors.enums.CurrentViewForReturn;
import com.leatherswan.artisticendeavors.model.Item;
import com.leatherswan.artisticendeavors.model.ListItem;
import com.leatherswan.artisticendeavors.model.FavoritesList;
import com.leatherswan.artisticendeavors.model.SimpleItemManager;

@Controller
public class FavoritesListController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	private SimpleItemManager itemManager;

	@ModelAttribute
	public void addUser(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
	}

	@ModelAttribute
	public void addFavsNum(HttpSession session, Model model) {
		FavoritesList favorites = (FavoritesList) session.getAttribute("favs");
		Integer favsNum = 0;
		if (favorites != null) {
			favsNum = favorites.numFavsItems();
		}
		model.addAttribute("favsNum", favsNum);

	}

	@RequestMapping(value = "/gotofavs", method = RequestMethod.GET)
	public String displayFavoritesList(HttpSession session, Model model) {

		session.setAttribute("view", CurrentViewForReturn.favoriteslist);

		FavoritesList favs = (FavoritesList) session.getAttribute("favs");

		if (favs == null) {
			logger.warn("Favorites List for (" + 
					(String) session.getAttribute("username") + 
					") is empty, re-login to initiate new cart.");
			return "redirect:/";
		}

		model.addAttribute("favsNum", favs.numFavsItems());
		model.addAttribute("favs", favs);
		model.addAttribute("favsItems", favs.getFavsItems());

		return "favsView";

	}

	@RequestMapping(value = "/addtofavs", method = RequestMethod.GET)
	public String processItemToFavs(
			@RequestParam(value = "itemid", required = true) String itemid,
			HttpSession session, 
			Model model) {

		FavoritesList favs = (FavoritesList) session.getAttribute("favs");
		String username = (String) session.getAttribute("username");

		// Check for valid cart
		if (favs == null) {
			logger.warn("Shopping Cart for (" + username + ") has timed out, "
					+ "user must login again to initiate new cart.");
//			return "redirect:/";
			if(username != null) {
			favs = new FavoritesList();
			favs.setUsername(username);
			}
		}
		
		if ( !favs.itemInFavs(itemid) ) {
			// item is not in session cart, create and add
			Item item = itemManager.getItemByItemid(itemid);
			ListItem newItem = new ListItem(item);
			favs.setFavsItem(newItem);
			
			 logger.info("Shopping Cart (" + username + ") added item: "
			 + favs.getFavsItemByItemid(itemid).getItem().getTitle());
		}

		session.setAttribute("favs", favs);
		model.addAttribute("favs", favs);

		model.addAttribute("favsNum", favs.numFavsItems());
		
		CurrentViewForReturn currentViewForReturn = 
				(CurrentViewForReturn) session.getAttribute("view");

		// switch ((CurrentViewForReturn) session.getAttribute("view")) {
		switch (currentViewForReturn) {
		case favoriteslist:
			return "redirect:/gotofavs";
		case detail:
			// return "redirect:/details?id=" + id;
			return "redirect:/detail?itemid=" + itemid;
		default:
			return "redirect:/";
		}

	}

}
