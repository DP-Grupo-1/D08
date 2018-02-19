
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReplyRepository;
import domain.Administrator;
import domain.Comment;
import domain.Reply;
import domain.User;

@Service
@Transactional
public class ReplyService {

	//Managed repository ----------------------------
	@Autowired
	public ReplyRepository			replyRepository;

	// --------SupportingServices----------------------------------
	@Autowired
	private UserService				userService;

	@Autowired
	private AdministratorService	administratorService;


	//Simple CRUD methods ------------------------

	public Reply create(Comment comment) {
		final Date moment = new Date();
		final Reply result = new Reply();
		result.setMoment(moment);
		return result;

	}

	public Reply save(Reply reply) {
		Assert.notNull(reply);
		Reply res;
		User user = this.userService.findByPrincipal();
		Assert.notNull(user);

		final Date moment = new Date(System.currentTimeMillis() - 1000);

		Assert.isTrue(reply.getMoment().after(moment));

		res = this.replyRepository.save(reply);

		return res;
	}

	public void delete(final Reply reply) {
		Assert.notNull(reply);
		Assert.isTrue(this.replyRepository.exists(reply.getId()));

		final Administrator administrator = this.administratorService.findByPrincipal();

		Assert.notNull(administrator);

		this.replyRepository.delete(reply);
	}

	public Reply findOne(final int replyID) {
		final Reply res = this.replyRepository.findOne(replyID);

		return res;
	}

	public Collection<Reply> findAll() {
		final Collection<Reply> res = this.replyRepository.findAll();
		Assert.notNull(res);
		return res;
	}

}
