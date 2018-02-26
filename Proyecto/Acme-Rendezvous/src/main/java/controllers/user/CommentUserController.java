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

<<<<<<< HEAD

=======
>>>>>>> 024f406c6d11d9fb930c95ecaa6b919457bfb467
import services.CommentService;
import services.RendezvousService;

import controllers.AbstractController;
import domain.Comment;
import domain.Rendezvous;
<<<<<<< HEAD

=======
>>>>>>> 024f406c6d11d9fb930c95ecaa6b919457bfb467

@Controller
@RequestMapping("/comment/user")
public class CommentUserController extends AbstractController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private RendezvousService rendezvousService;

<<<<<<< HEAD
	

=======
>>>>>>> 024f406c6d11d9fb930c95ecaa6b919457bfb467
	// Creation--------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer rendezvousId) {
		ModelAndView result;
		Comment comment;
		Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
	
		comment = this.commentService.create(rendezvous);
		

		result = this.createEditModelAndView(comment);
		result.addObject("rendezvousId", rendezvousId);
<<<<<<< HEAD
	
=======
>>>>>>> 024f406c6d11d9fb930c95ecaa6b919457bfb467

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
	public ModelAndView save(@Valid final Comment comment,@RequestParam final Integer rendezvousId, 
=======
	public ModelAndView save(@Valid final Comment comment,
			@RequestParam final Integer rendezvousId,
>>>>>>> 024f406c6d11d9fb930c95ecaa6b919457bfb467
			final BindingResult binding) {

		ModelAndView result;
		Collection<Comment> comments = new ArrayList<Comment>();

<<<<<<< HEAD
//		if (binding.hasErrors())
//			result = this.createEditModelAndView(comment);
//		else
//
//			try {
				System.out.println("jacinto1");
				this.commentService.save(comment);
				System.out.println("jacinto2");
				Rendezvous r = this.rendezvousService.findOne(rendezvousId);
				System.out.println("jacinto3");
				comments.addAll(r.getComments());
				System.out.println("jacinto4");
				comments.add(comment);
				r.setComments(comments);
				
				
				this.rendezvousService.save(r);
				System.out.println("jacinto5");
				result = new ModelAndView("redirect:/rendezvous/user/listRsvps.do");

				
//			}
//
//			catch (final Throwable oops) {
//				result = this.createEditModelAndView(comment,
//						"comment.comit.error");
//			}
=======
		 if (binding.hasErrors())
		 result = this.createEditModelAndView(comment);
	 else
		
		 try {

		this.commentService.save(comment);

		Rendezvous r = this.rendezvousService.findOne(rendezvousId);

		comments.addAll(r.getComments());

		comments.add(comment);

		r.setComments(comments);

		this.rendezvousService.save(r);

		result = new ModelAndView("redirect:/rendezvous/user/listRsvps.do");

		 }
		
		 catch (final Throwable oops) {
		 result = this.createEditModelAndView(comment,
		 "comment.comit.error");
		 }
>>>>>>> 024f406c6d11d9fb930c95ecaa6b919457bfb467

		return result;
	}

	// DELETE
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Comment comment,
			final BindingResult binding) {

		ModelAndView result;

		try {

			this.commentService.delete(comment);
			result = new ModelAndView("redirect:/comment/user/list.do");

		}

		catch (final Throwable oops) {
			result = this
					.createEditModelAndView(comment, "comment.comit.error");

		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment comment,
			final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("comment/user/edit");
		result.addObject("comment", comment);
		result.addObject("message", messageCode);
		return result;
	}

}
