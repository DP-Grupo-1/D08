/* CustomerController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;
import domain.Announcement;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController extends AbstractController {

	//Services ----------------------------------------------------------
	@Autowired
	private AnnouncementService	announcementService;


	//Listing -----------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;

		Collection<Announcement> announcements = this.announcementService.findAll();

		res = new ModelAndView("announcement/list");
		res.addObject("announcements", announcements);
		res.addObject("requestURI", "announcement/list.do");

		return res;
	}

}
