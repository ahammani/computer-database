package com.excilys.cdb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/customLogin")
public class LoginController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.GET)
	public String login(
			@RequestParam(value = "auth", required = false) String auth,
			Model model) {

		log.debug("Login");

		if (auth == null) {
			auth = "true";
		}
		model.addAttribute("auth", auth);
		return "customLogin";
	}

	// @RequestMapping(value = "/logout", method = RequestMethod.GET)
	// public String login(Model model) {
	//
	// log.info("Logout");
	//
	// SecurityContextHolder.getContext().getAuthentication()
	// .setAuthenticated(false);
	//
	// return "redirect:/customLogin";
	// }
}
