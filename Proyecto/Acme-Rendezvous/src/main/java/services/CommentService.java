
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


	//Simple CRUD methods ------------------------

	public Comment create(Rendezvous rendezvous) {
		final Date moment = new Date();
		final Comment result = new Comment();
		result.setMoment(moment);
		return result;

	}

	public Comment save(Comment comment) {
		Assert.notNull(comment);
		Comment res;
		User user = this.userService.findByPrincipal();
		Assert.notNull(user);

		final Date moment = new Date(System.currentTimeMillis() - 1000);

		Assert.isTrue(comment.getMoment().after(moment));

		res = this.commentRepository.save(comment);

		return res;
	}

	public void delete(final Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(this.commentRepository.exists(comment.getId()));
		final Administrator administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);
		comment.getReplies().removeAll(comment.getReplies());

		this.commentRepository.delete(comment);
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

	public Collection<Reply> findByCommentId(Integer commentId){
		Collection<Reply> replies = new ArrayList<Reply>();
		Comment comment= commentRepository.findOne(commentId);
		Assert.notNull(comment);
		replies.addAll(comment.getReplies());
		return replies;
	}

}
