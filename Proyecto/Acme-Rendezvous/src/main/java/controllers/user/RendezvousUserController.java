
package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;

@Controller
@RequestMapping("/rendezvous/user")
public class RendezvousUserController extends AbstractController {

	// Services ---------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private UserService			userService;


	//Listing ----------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {														//Listeo de viajes
		ModelAndView result;
		Collection<Trip> trips;
		final Manager logged = this.managerService.findByPrincipal();

		trips = logged.getTrips();

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/manager/list.do");

		return result;
	}

	// create----------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result = null;

		try {
			final Manager manager = this.managerService.findByPrincipal();
			Assert.notNull(manager);
			final Trip trip = this.tripService.create();

			final Stage stage = this.stageService.create(trip);

			result = this.createEditModelAndView(trip);
			result.addObject("trip", trip);
			result.addObject("stage", stage);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/");
		}

		return result;
	}

	//Edition----------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tripId) {

		ModelAndView result;
		Trip trip;

		trip = this.tripService.findOne(tripId);
		Assert.notNull(trip);
		result = this.createEditModelAndView(trip);
		result.addObject("trip", trip);
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Trip trip, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(trip);
		else
			try {
				final Trip saved = this.tripService.save(trip);
				result = new ModelAndView("redirect:../display.do?tripId=" + saved.getId());
			} catch (final Throwable error) {
				result = this.createEditModelAndView(trip, "trip.comit.error");

			}

		return result;

	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Trip trip, final BindingResult binding) {

		ModelAndView result;

		try {

			this.tripService.delete(trip);
			result = new ModelAndView("redirect:../../welcome/index.do");

		}

		catch (final Throwable oops) {
			result = this.createEditModelAndView(trip, "trip.comit.error");

		}
		return result;
	}

	@RequestMapping(value = "/cancellationReason", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int tripId) {

		ModelAndView result;
		Trip trip;

		trip = this.tripService.findOne(tripId);
		Assert.notNull(trip);
		result = new ModelAndView("trip/manager/cancellationReason");
		result.addObject("trip", trip);
		result.addObject("tripId", tripId);

		return result;

	}

	@RequestMapping(value = "/cancellationReason", method = RequestMethod.POST, params = "cancelTrip")
	public ModelAndView cancelTrip(@Valid final Trip trip, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(trip);
		else
			try {
				this.tripService.cancel(trip);

				result = new ModelAndView("redirect:../display.do?tripId=" + trip.getId());
			} catch (final Throwable error) {
				result = this.createEditModelAndView(trip, "trip.comit.error");

			}

		return result;
	}

	// Ancillary methods -----------------------------------------
	protected ModelAndView createEditModelAndView(final Trip trip) {
		ModelAndView result;
		result = this.createEditModelAndView(trip, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Trip trip, final String message) {
		ModelAndView result;
		final Collection<LegalText> legalTexts = this.legalTextService.findAllFinal();
		final Collection<Category> categories = this.categoryService.findAll();
		final Collection<Ranger> rangers = this.rangerService.findAll();
		result = new ModelAndView("trip/manager/edit");
		result.addObject("trip", trip);
		result.addObject("rangers", rangers);
		result.addObject("categories", categories);
		result.addObject("legalTexts", legalTexts);
		result.addObject("message", message);
		return result;
	}
}
