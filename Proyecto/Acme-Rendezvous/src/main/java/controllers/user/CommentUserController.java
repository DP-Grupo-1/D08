
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
import services.UserService;
import controllers.AbstractController;
import domain.Comment;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/comment/user")
public class CommentUserController extends AbstractController {

	

	@Autowired
	private CommentService			commentService;
	
	@Autowired
	private RendezvousService			rendezvousService;
	
	@Autowired
	private UserService			userService;


	//Creation--------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer rendezvousId) {
		ModelAndView result;
		Comment comment;
		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		comment = this.commentService.create(rendezvous);

		result = this.createEditModelAndView(comment);

		result.addObject("requestURI", "comment/user/create.do?rendezvousId=" + rendezvous.getId());

		return result;
	}

//	//Listing
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView list() {
//		ModelAndView res;
//		Collection<NotaAviso> notaAvisos;
//		final Manager manager = this.managerService.findByPrincipal();
//		Assert.notNull(manager);
//		notaAvisos = manager.getNotaAvisos();
//		Assert.notNull(notaAvisos);
//
//		res = new ModelAndView("notaAviso/manager/list");
//		res.addObject("notaAvisos", notaAvisos);
//		res.addObject("requestURI", "notaAviso/manager/list.do");
//		return res;
//	}

	//Edit----------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int commentId) {
		final ModelAndView res;
		final Comment comment = this.commentService.findOne(commentId);
		res = this.createEditModelAndView(comment);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Comment comment, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(comment);
		else

			try {
				
				if (comment.getId() != 0) {
					this.commentService.save(comment);
					result = new ModelAndView("redirect:/comment/list.do");
				} else {

					final Comment commentario = this.commentService.save(comment);

					
					final UserAccount useraccount = LoginService.getPrincipal();
					final User user= this.userService.findByUserAccount(useraccount);

					Collection<Comment> comments = user.getComments();
					comments.add(commentario);
					user.setComments(comments);
					

					result = new ModelAndView("redirect:/comment/list.do");
				}
			}

			catch (final Throwable oops) {
				result = this.createEditModelAndView(comment, "comment.comit.error");
			}

		return result;
	}

	//DELETE
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Comment comment, final BindingResult binding) {

		ModelAndView result;

		try {

			this.commentService.delete(comment);
			result = new ModelAndView("redirect:/comment/user/list.do");

		}

		catch (final Throwable oops) {
			result = this.createEditModelAndView(comment, "comment.comit.error");

		}
		return result;
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
