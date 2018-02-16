
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

import security.LoginService;
import security.UserAccount;
import services.CommentService;
import services.RendezvousService;
import services.ReplyService;
import controllers.AbstractController;
import domain.Comment;
import domain.Rendezvous;
import domain.Reply;
import domain.User;

@Controller
@RequestMapping("/reply/user")
public class ReplyUserController extends AbstractController {

	

	@Autowired
	private ReplyService			replyService;
	
	@Autowired
	private CommentService			commentService;
	
	@Autowired
	private UserService			userService;


	//Creation--------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer commentId) {
		ModelAndView result;
		Reply reply;
		final Comment comment = this.commentService.findOne(commentId);
		reply = this.replyService.create(comment);

		result = this.createEditModelAndView(reply);

		result.addObject("requestURI", "reply/user/create.do?commentId=" + comment.getId());

		return result;
	}



	//Edit----------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int replyId) {
		final ModelAndView res;
		final Reply reply = this.replyService.findOne(replyId);
		res = this.createEditModelAndView(reply);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Reply reply, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(reply);
		else

			try {
				
				if (reply.getId() != 0) {
					this.replyService.save(reply);
					result = new ModelAndView("redirect:/reply/list.do");
				} else {

					final Reply respuesta= this.replyService.save(reply);

					
					final UserAccount useraccount = LoginService.getPrincipal();
					final User user= this.userService.findByUserAccount(user);

					Collection<Reply> replies = user.getReplies();
					replies.add(respuesta);
					user.setReplies(replies);
					

					result = new ModelAndView("redirect:/reply/list.do");
				}
			}

			catch (final Throwable oops) {
				result = this.createEditModelAndView(reply, "reply.comit.error");
			}

		return result;
	}

	//DELETE
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Reply reply, final BindingResult binding) {

		ModelAndView result;

		try {

			this.replyService.delete(reply);
			result = new ModelAndView("redirect:/reply/user/list.do");

		}

		catch (final Throwable oops) {
			result = this.createEditModelAndView(reply, "reply.comit.error");

		}
		return result;
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
