
package controllers.any;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	//Display-------------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int userId) {
		Assert.notNull(userId);
		ModelAndView res;
		final User user = this.userService.findOne(userId);
		res = new ModelAndView("user/display");
		res.addObject("user", user);
		res.addObject("requestURI", "user/display.do");
		return res;
	}
	//Create-----------------------------------------------------------------
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		final User user = this.userService.create();
		res = this.createEditModelAndView(user);

		return res;
	}
	//Edit----------------------------------------------------------------------------
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final User user, final BindingResult binding) {
		Assert.notNull(user);
		ModelAndView res;
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(user);
		} else
			try {
				Md5PasswordEncoder encoder;
				String password;

				encoder = new Md5PasswordEncoder();
				password = encoder.encodePassword(user.getUserAccount().getPassword(), null);
				user.getUserAccount().setPassword(password);
				this.userService.save(user);
				res = new ModelAndView("redirect:../welcome/index.do");
			} catch (final Throwable error) {
				res = this.createEditModelAndView(user, "user.error");
			}
		return res;

	}
	private ModelAndView createEditModelAndView(final User user) {
		ModelAndView result;

		result = this.createEditModelAndView(user, null);

		return result;
	}

	private ModelAndView createEditModelAndView(final User user, final String message) {
		ModelAndView result;

		result = new ModelAndView("user/register");
		result.addObject("user", user);
		result.addObject("message", message);

		return result;
	}
}
