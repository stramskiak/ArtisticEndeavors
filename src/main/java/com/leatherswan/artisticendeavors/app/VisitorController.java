package com.leatherswan.artisticendeavors.app;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.leatherswan.artisticendeavors.enums.CurrentViewForReturn;
import com.leatherswan.artisticendeavors.model.Visitor;
import com.leatherswan.artisticendeavors.model.FavoritesList;

@Controller
public class VisitorController {
	
	private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

	Visitor user;
	
	
// modelattribute on method is made available to all handlers within controller
//	same effect could be gained via superclass with said variables
	
	@ModelAttribute
	public void addUser(HttpSession session, Model model) {
		String username = (String)session.getAttribute("username");
		model.addAttribute("username" , username);
	}

	@RequestMapping(value = "/userLogin", method=RequestMethod.GET)
	public ModelAndView displayLoginForm(
			@ModelAttribute @Valid Visitor user,
			Errors errors,
			HttpSession session, 
			Model model) {
//		passes a new instance, unpopulated, userInfo. 
//		Could be populated before return with, for example, a stored username or address
		
		session.setAttribute("view", CurrentViewForReturn.login);

		return new ModelAndView("loginView", "user", new Visitor());
		
	}
	
    @RequestMapping(value = "/userLogin", method=RequestMethod.POST)
	public String processLogin(
			@ModelAttribute @Valid Visitor user,
			Errors errors,
			HttpSession session, 
			Model model) {

    	if (errors.hasErrors()) { return "loginView"; }
				
//		userManager accepts userInfo, creates addresses, creditCards and favorites
//    	constructor fills username in all from userInfo

		session.setAttribute("username", user.getUsername());
		model.addAttribute("user", user);
//		user = new User(user);
		session.setAttribute("user", user);
		
		Integer favoritesNum = 0;
		FavoritesList userCart = new FavoritesList();
		FavoritesList sessionCart = (FavoritesList)session.getAttribute("favorites");
		if (sessionCart != null) {
			if (sessionCart.getFavsItems() != null) {
				userCart.setFavsItems(sessionCart.getFavsItems());
				favoritesNum = userCart.numFavsItems();
				}
		}
		
		session.setAttribute("favorites", userCart);
		model.addAttribute("favoritesNum", favoritesNum);

		return "accountView";
		
	}

	@RequestMapping(value = "/view_account", method=RequestMethod.GET)
	public String displayAccountForm(
			HttpSession session, 
			Model model) {

		session.setAttribute("view", CurrentViewForReturn.account);

		Visitor user = (Visitor) session.getAttribute("user");

		model.addAttribute("user", user);
    	
		return "accountView";
		
	}
	
    ////////////////////////////LogOut/////////////////////////////

    @RequestMapping(value = "/logout", method=RequestMethod.GET)
	public String processLogout(HttpSession session) {
		
		session.removeAttribute("username");
		session.removeAttribute("user");
		session.removeAttribute("favorites");
		session.invalidate();
		
		return "redirect:";
		
	}


} //EOF

