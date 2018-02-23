
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.RendezvousService;
import domain.Rendezvous;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController {


	//Services----------------------------------------------------
	@Autowired
	AdministratorService	administratorService;

	@Autowired
	RendezvousService	rendezvousService;


	//Listing -----------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;

		//Level C-----------------------------------------------------------------------------------------------------
		//1

		//2

		//3
		final Double avgUsersPerRendezvous = this.rendezvousService.avgUsersPerRendezvous();
		final Double stddevUsersPerRendezvous =  this.rendezvousService.stddevUsersPerRendezvous();

		//4
		final Double avgRSVPsPerUser = this.rendezvousService.avgRSVPsPerUser();
		final Double stddevRSVPsPerUser = this.rendezvousService.stddevRSVPsPerUser();

		//5
		final Collection<Rendezvous> top10RendezvousesByRSVPs = this.rendezvousService.top10RendezvousesByRSVPs();


		//Level B

		res = new ModelAndView("dashboard/list");

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


		res.addObject("requestURI", "announcement/list.do");

		return res;
	}

}
