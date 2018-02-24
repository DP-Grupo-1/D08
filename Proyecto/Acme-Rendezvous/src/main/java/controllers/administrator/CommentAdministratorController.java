
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import domain.Comment;

@Controller
@RequestMapping("/comment/administrator")
public class CommentAdministratorController {

	@Autowired
	private CommentService	commentService;


	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(final Comment comment) {
		ModelAndView res;
		Assert.notNull(comment);
		//		try {
		this.commentService.quitarCommentReply(comment);
		this.commentService.delete(comment);
		res = new ModelAndView("redirect:../../rendezvous/list.do");

		//		} catch (final Throwable error) {
		//			res = new ModelAndView("redirect:comment/administrator/list.do");
		//
		//		}

		return res;
	}
}
