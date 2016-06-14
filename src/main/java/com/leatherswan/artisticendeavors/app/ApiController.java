package com.leatherswan.artisticendeavors.app;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leatherswan.artisticendeavors.app.ApiMessage.MsgType;
import com.leatherswan.artisticendeavors.model.Item;
import com.leatherswan.artisticendeavors.model.ListItem;
import com.leatherswan.artisticendeavors.model.SimpleItemManager;
import com.leatherswan.artisticendeavors.service.FavoritesList;

@RequestMapping("/api")
@RestController
public class ApiController {
	
	private SimpleItemManager itemManager;

    @RequestMapping(value="/items", method=RequestMethod.GET)
    public List<Item> getItems() {
        return itemManager.getItems();
    }

    @RequestMapping(value="/items", method=RequestMethod.POST)
    public Object createItem(@RequestBody Item item, HttpServletResponse response) {
        if (itemManager.getItemByItemid(item.getItemid()) != null) {
            return new ApiMessage(MsgType.ERROR, "Item with ItemId=" + item.getItemid() + " already exists.");
        }
        itemManager.addItem(item);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return itemManager.getItemByItemid(item.getItemid());
    }
    
    @RequestMapping(value="/items/{itemid}", method=RequestMethod.GET)
    public Object getItem(@PathVariable("itemid") String itemid) {
        Item item = itemManager.getItemByItemid(itemid);
        if (item != null) {
            return item;
        } else {
            return new ApiMessage(MsgType.ERROR, "Item with Item ID = " + itemid + " does not exist.");
        }
    }
    
    @RequestMapping(value="/items/{itemid}", method=RequestMethod.PUT)
    public Item updateItem(@RequestBody Item item) {
        itemManager.updateItem(item);
        return itemManager.getItemByItemid(item.getItemid());
    }
    
	@RequestMapping(value = "/favorites/{itemid}", method=RequestMethod.PUT)
	public String processItemToFaorites(
			@PathVariable("itemid") String itemid,
			HttpSession session, 
			Model model) {

		FavoritesList favorites = (FavoritesList) session.getAttribute("itemId");
		String username = (String) session.getAttribute("username");

		// Check for valid favorites
		if (favorites == null) {
			favorites = new FavoritesList(username);
			favorites.setUsername(username);
		}
		
		if ( !favorites.itemInFavs(itemid) ) {
			// item is not in favorites list so create and add
			Item item = itemManager.getItemByItemid(itemid);
			ListItem newItem = new ListItem(item);
			favorites.setFavoriteItem(newItem);
		}

		session.setAttribute("favorites", favorites);
		model.addAttribute("favorites", favorites);

		model.addAttribute("favoritesNum", favorites.numFavoritesItems());
		
		return favorites.numFavoritesItems().toString();
	}

	@RequestMapping(value = {"/favoritesNum"}, method=RequestMethod.POST)
	public String postFavoritesNumberOfItems(
			HttpSession session) {
		String favsNum = "0";
		
		FavoritesList favorites = (FavoritesList) session.getAttribute("favorites");
		if (favorites == null) {
			favsNum = "0";
		} else {
			favsNum = favorites.numFavoritesItems().toString();
		}		
		return favsNum;
	}
	@RequestMapping(value = {"/favoritesNum"}, method=RequestMethod.GET)
	public String getFavoritesNumberOfItems(
			HttpSession session) {
		String favsNum = "0";
		
		FavoritesList favorites = (FavoritesList) session.getAttribute("favorites");
		if (favorites == null) {
			favsNum = "0";
		} else {
			favsNum = favorites.numFavoritesItems().toString();
		}		
		return favsNum;
	}

}