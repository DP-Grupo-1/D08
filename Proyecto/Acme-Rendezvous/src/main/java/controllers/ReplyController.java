
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
import domain.Reply;


import domain.Rendezvous;

import domain.User;

@Controller
@RequestMapping("/reply")
public class ReplyController extends AbstractController {

	

	@Autowired
	private ReplyService			replyService;
	

	
	


	

	//Listing
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer commentId) {
		ModelAndView res;
		Collection<Reply> replies;
		replies = this.replyService.findByCommentId(commentId);
		
		//POR HACER LA QUERY EN COMMENT REPOSITORY
		
		res = new ModelAndView("reply/list");
		res.addObject("replies", replies);
		res.addObject("requestURI", "reply/list.do");
		return res;
	}

	

	protected ModelAndView createEditModelAndView(final Reply reply) {
		ModelAndView result;

		result = this.createEditModelAndView(reply, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Reply reply, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("reply/user/edit");
		result.addObject("reply", reply);
		result.addObject("message", messageCode);
		return result;
	}

}