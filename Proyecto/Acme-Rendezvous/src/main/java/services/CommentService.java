
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Administrator;
import domain.Comment;
import domain.Rendezvous;
import domain.Reply;
import domain.User;

@Service
@Transactional
public class CommentService {

	//Managed repository ----------------------------
	@Autowired
	public CommentRepository		commentRepository;

	// --------SupportingServices----------------------------------
	@Autowired
	private UserService				userService;

	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private ReplyService			replyService;
	@Autowired
	private RendezvousService		rendezvousService;


	//Simple CRUD methods ------------------------

	public Comment create(final Rendezvous rendezvous) {
		Collection<Reply> replies = new ArrayList<Reply>();
		final Date moment = new Date();
		final Comment result = new Comment();
		
		
		
		result.setReplies(replies);
		result.setMoment(moment);
		return result;

	}

	public Comment save(final Comment comment) {
		Assert.notNull(comment);
		Comment res;
		final User user = this.userService.findByPrincipal();
		Assert.notNull(user);
		Collection<Comment> comments = user.getComments();
		System.out.println("llego aqui save2");

//		Assert.isTrue(comment.getMoment().b(moment));
		
		if(comment.getId() == 0){
			System.out.println("llego aqui save3");
			res = this.commentRepository.save(comment);
			System.out.println("llego aqui save4");
			comments.add(res);
			user.setComments(comments);
			//userService.save(user);
			System.out.println("llego aqui save5");
		}
		else{
		res = this.commentRepository.save(comment);
		}
		return res;
	}

	public void delete(final Comment comment) {
		
		Assert.notNull(comment);
		final Administrator administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);
		this.quitarCommentReply(comment);
		System.out.println("llega aqui 4");
		this.commentRepository.delete(comment);
	}

	private void quitarCommentReply(final Comment comment) {
		final Collection<Reply> replies = comment.getReplies();
		if (!replies.isEmpty())
			System.out.println("llega aqui1");
		for (final Reply r : replies) {
			System.out.println("llega aqui2");
			this.userService.findByReplyId(r.getId()).getReplies().remove(r);
		}
		this.rendezvousService.findByCommentId(comment.getId()).getComments().remove(comment);
		this.userService.findByCommentId(comment.getId()).getComments().remove(comment);
	}
	public Comment findOne(final int commentID) {
		final Comment res = this.commentRepository.findOne(commentID);

		return res;
	}

	public Collection<Comment> findAll() {
		final Collection<Comment> res = this.commentRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Collection<Reply> findByCommentId(final Integer commentId) {
		final Collection<Reply> replies = new ArrayList<Reply>();
		final Comment comment = this.commentRepository.findOne(commentId);
		Assert.notNull(comment);
		replies.addAll(comment.getReplies());
		return replies;
	}

}
