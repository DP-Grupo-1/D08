
package controllers.user;

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
	private CommentService		commentService;

	@Autowired
	private RendezvousService	rendezvousService;
	@Autowired
	private UserService			userService;


	// Creation--------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer rendezvousId) {
		ModelAndView result = null;
		Comment comment;
<<<<<<< HEAD
		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);

=======
		Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		try {
>>>>>>> 1e6661ab45b47c2cc938b9c180f90614b66b7289
		comment = this.commentService.create(rendezvous);

		result = this.createEditModelAndView(comment);
		result.addObject("rendezvousId", rendezvousId);
		}
		
	 catch (final Throwable oops) {
		result = new ModelAndView("redirect:/");
		System.out.println(oops);
	}
		return result;
	}

	// Edit----------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int commentId) {
		final ModelAndView res;
		final Comment comment = this.commentService.findOne(commentId);
		res = this.createEditModelAndView(comment);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
<<<<<<< HEAD
	public ModelAndView save(@Valid Comment comment, @RequestParam final Integer rendezvousId, final BindingResult binding) {

		ModelAndView result;
		final Collection<Comment> comments = new ArrayList<Comment>();
		final User user = this.userService.findByPrincipal();
		comment = this.commentService.reconstruct(comment, binding);
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(comment);
			System.out.println("llega aqui1");
		} else
			try {
				final Comment saved = this.commentService.save(comment);
				//				comments.add(saved);
				//				user.setComments(comments);
				//				this.userService.save(user);

				final Rendezvous r = this.rendezvousService.findOne(rendezvousId);

				comments.addAll(r.getComments());

				//	comments.add(comment);
				comments.add(saved);

				r.setComments(comments);

				this.rendezvousService.save(r);
				//	r.getComments().add(saved);

				result = new ModelAndView("redirect:/rendezvous/user/listRsvps.do");

			}

			catch (final Throwable oops) {
				result = this.createEditModelAndView(comment, "comment.comit.error");
				System.out.println("llega aqui2");

			}
=======
	public ModelAndView save(@RequestParam final Integer rendezvousId,
			@Valid final Comment comment,
			final BindingResult binding) {

		ModelAndView result;
		Collection<Comment> comments = new ArrayList<Comment>();

		if (binding.hasErrors()){
			result = this.createEditModelAndView(comment);
			result.addObject("rendezvousId", rendezvousId);
		}
		else {

			try {

				this.commentService.save(comment);

				Rendezvous r = this.rendezvousService.findOne(rendezvousId);

				comments.addAll(r.getComments());

				comments.add(comment);

				r.setComments(comments);

				this.rendezvousService.save(r);

				result = new ModelAndView(
						"redirect:/rendezvous/user/listRsvps.do");

			}

			catch (final Throwable oops) {
				result = this.createEditModelAndView(comment,
						"comment.commit.error");
			}
		}
>>>>>>> 1e6661ab45b47c2cc938b9c180f90614b66b7289
		return result;
	}

	// DELETE
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Comment comment, final BindingResult binding) {

		ModelAndView result;

		try {

			this.commentService.delete(comment);
			result = new ModelAndView("redirect:/comment/user/list.do");

		}

		catch (final Throwable oops) {
<<<<<<< HEAD
			result = this.createEditModelAndView(comment, "comment.comit.error");
=======
			result = this
					.createEditModelAndView(comment, "comment.commit.error");
>>>>>>> 1e6661ab45b47c2cc938b9c180f90614b66b7289

		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, null);
		return result;
	}

<<<<<<< HEAD
	protected ModelAndView createEditModelAndView(final Comment comment, final String messageCode) {
=======
	protected ModelAndView createEditModelAndView(final Comment comment,
			final String message) {
>>>>>>> 1e6661ab45b47c2cc938b9c180f90614b66b7289
		ModelAndView result;

		result = new ModelAndView("comment/user/edit");
		result.addObject("comment", comment);
		result.addObject("message", message);
		return result;
	}

}
