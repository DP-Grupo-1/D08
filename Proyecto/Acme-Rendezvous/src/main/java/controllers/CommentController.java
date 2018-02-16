
package controllers;

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

import security.LoginService;
import security.UserAccount;
import services.CommentService;


import services.RendezvousService;

import controllers.AbstractController;
import domain.Comment;


import domain.Rendezvous;

import domain.User;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	

	@Autowired
	private CommentService			commentService;
	

	
	


	

	//Listing
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer rendezvousId) {
		ModelAndView res;
		Collection<Comment> comments;
		comments = this.commentService.findByRendezvous(rendezvousId);
		
		//POR HACER LA QUERY EN COMMENT REPOSITORY
		
		res = new ModelAndView("comment/list");
		res.addObject("comments", comments);
		res.addObject("requestURI", "comment/list.do");
		return res;
	}

	

	protected ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment comment, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("comment/user/edit");
		result.addObject("comment", comment);
		result.addObject("message", messageCode);
		return result;
	}

}
