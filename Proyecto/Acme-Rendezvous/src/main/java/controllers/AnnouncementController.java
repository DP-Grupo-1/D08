/* CustomerController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html */

package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.AnnouncementService;
import services.UserService;
import domain.Announcement;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/announcement/user")
public class AnnouncementController extends AbstractController {

	//Services ----------------------------------------------------------
	@Autowired
	private AnnouncementService	announcementService;

	@Autowired
	private UserService			userService;


	// Create ------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer rendezvousId) {
		ModelAndView result;

		Announcement announcement = this.announcementService.create();

		result = this.createEditModelAndView(announcement);
		result.addObject("rendezvousId", rendezvousId);

		return result;
	}

	//Save --------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Announcement announcement, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().toString());
			result = this.createEditModelAndView(announcement);
		} else
			try {
				this.announcementService.save(announcement);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(announcement, "announcement.commit.error");
			}

		return result;
	}

	//Delete ------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Announcement announcement, final BindingResult binding) {
		ModelAndView result;

		try {
			this.announcementService.delete(announcement);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(announcement, "announcement.commit.error");
		}
		return result;
	}

	//Listing -----------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;

		UserAccount userAcc = LoginService.getPrincipal();
		User user = this.userService.findByUserAccount(userAcc);

		Collection<Announcement> announcements = new ArrayList<Announcement>();

		for (Rendezvous r : user.getRendezvouses()) {
			for (Announcement a : r.getAnnouncements()) {
				announcements.add(a);
			}
		}

		res = new ModelAndView("announcement/list");
		res.addObject("announcements", announcements);
		res.addObject("requestURI", "announcement/list.do");

		return res;
	}

	protected ModelAndView createEditModelAndView(final Announcement announcement) {
		ModelAndView result;

		result = this.createEditModelAndView(announcement, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Announcement announcement, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("announcement/user/edit");
		result.addObject("announcement", announcement);
		result.addObject("message", messageCode);
		return result;
	}
}