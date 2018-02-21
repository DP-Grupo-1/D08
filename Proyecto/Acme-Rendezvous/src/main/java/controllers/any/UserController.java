
package controllers.any;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserController {

	//Services------------------------------------------------------------------
	@Autowired
	UserService	userService;


	//List--------------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		final Collection<User> users = this.userService.findAll();
		res = new ModelAndView("user/list");
		res.addObject("requestURI", "user/list.do");
		res.addObject("users", users);
		return res;
	}

	//Edit----------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		final ModelAndView res;
		final User user = this.userService.findByPrincipal();
		Assert.notNull(user);
		res = this.createEditModelAndView(user);
		return res;
	}

	private ModelAndView createEditModelAndView(final User user) {
		ModelAndView result;

		result = this.createEditModelAndView(user, null);

		return result;
	}

	private ModelAndView createEditModelAndView(final User user, final String message) {
		ModelAndView result;

		result = new ModelAndView("user/edit");
		result.addObject("user", user);
		result.addObject("message", message);
		result.addObject("requestURI", "user/edit.do");

		return result;
	}
}
