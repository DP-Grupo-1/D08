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

import repositories.AnnouncementRepository;
import security.LoginService;
import security.UserAccount;
import services.AnnouncementService;
import services.UserService;
import domain.Announcement;
import domain.RSVP;
import domain.User;

@Controller
@RequestMapping("/announcement/user")
public class AnnouncementUserController extends AbstractController {

	//Repository ----------------------------------------------------------
	@Autowired
	private AnnouncementRepository	announcementRepository;

	//Services ----------------------------------------------------------
	@Autowired
	private AnnouncementService		announcementService;

	@Autowired
	private UserService				userService;


	// Create ------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer rendezvousId) {
		ModelAndView result;

		Announcement announcement = this.announcementService.create();

		result = this.createEditModelAndView(announcement);
		result.addObject("rendezvousId", rendezvousId);

		return result;
	}

	//Listing -----------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;

		UserAccount userAcc = LoginService.getPrincipal();
		User u = this.userService.findByUserAccount(userAcc);

		//Display a stream of announcements that have been posted to the rendezvouses that he or she's RSVPd
		Collection<Integer> rendezvousesIds = new ArrayList<Integer>();

		for (RSVP r : u.getRsvps()) {
			rendezvousesIds.add(r.getRendezvous().getId());
		}

		Collection<Announcement> announcements = new ArrayList<Announcement>();

		for (Integer i : rendezvousesIds) {
			announcements.addAll(this.announcementRepository.findAnnouncementsOfMyRSVP(i));
		}

		res = new ModelAndView("announcement/list");
		res.addObject("announcements", announcements);
		res.addObject("requestURI", "announcement/user/list.do");

		return res;
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
