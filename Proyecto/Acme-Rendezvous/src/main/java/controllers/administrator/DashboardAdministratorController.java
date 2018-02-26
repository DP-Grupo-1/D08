
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.AnnouncementService;
import services.RendezvousService;
import domain.Rendezvous;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController {

	//Services----------------------------------------------------
	@Autowired
	AdministratorService	administratorService;

	@Autowired
	RendezvousService		rendezvousService;

	@Autowired
	AnnouncementService		announcementService;


	//Listing -----------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;

		//Level C-----------------------------------------------------------------------------------------------------
		//1
		final Double avgRendezvousPerUser = this.rendezvousService.avgRendezvousPerUser();
		//2
		final Double ratioCreators = this.rendezvousService.ratioCreators();
		final Double ratioUsersSinRendezvous = this.rendezvousService.ratioUsersSinRendezvous();

		//3
		final Double avgUsersPerRendezvous = this.rendezvousService.avgUsersPerRendezvous();
		final Double stddevUsersPerRendezvous = this.rendezvousService.stddevUsersPerRendezvous();

		//4
		final Double avgRSVPsPerUser = this.rendezvousService.avgRSVPsPerUser();
		final Double stddevRSVPsPerUser = this.rendezvousService.stddevRSVPsPerUser();

		//5
		final Collection<Rendezvous> top10RendezvousesByRSVPs = this.rendezvousService.top10RendezvousesByRSVPs();

		//Level B
		final Double avgOfAnnouncementsPerRendezvous = this.announcementService.avgOfAnnouncementsPerRendezvous();

		res = new ModelAndView("dashboard/list");

		//1
		res.addObject("avgRendezvousPerUser", avgRendezvousPerUser);

		//2
		res.addObject("ratioCreators", ratioCreators);
		res.addObject("ratioUsersSinRendezvous", ratioUsersSinRendezvous);

		//3.1
		res.addObject("avgUsersPerRendezvous", avgUsersPerRendezvous);

		//3.2
		res.addObject("stddevUsersPerRendezvous", stddevUsersPerRendezvous);

		//4.1
		res.addObject("avgRSVPsPerUser", avgRSVPsPerUser);

		//4.2
		res.addObject("stddevRSVPsPerUser", stddevRSVPsPerUser);

		//5
		res.addObject("top10RendezvousesByRSVPs", top10RendezvousesByRSVPs);

		//B
		res.addObject("avgOfAnnouncementsPerRendezvous", avgOfAnnouncementsPerRendezvous);

		res.addObject("requestURI", "announcement/list.do");

		return res;
	}

}
