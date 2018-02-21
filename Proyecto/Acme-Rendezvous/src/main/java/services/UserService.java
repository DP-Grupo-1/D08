
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.RSVP;
import domain.Reply;
import domain.User;

@Service
@Transactional
public class UserService {

	//Managed repository----------------------------------------------
	@Autowired
	private UserRepository	userRepository;


	//CRUD methods-------------------------------------------------------
	public User create() {
		final User res = new User();
		final Collection<Comment> comments = new ArrayList<>();
		final Collection<RSVP> rsvps = new ArrayList<>();
		final Collection<Reply> replies = new ArrayList<>();
		final UserAccount userAccount = new UserAccount();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.USER);

		Collection<Authority> authorities;

		authorities = userAccount.getAuthorities();
		authorities.add(authority);
		userAccount.setAuthorities(authorities);
		res.setComments(comments);
		res.setRsvps(rsvps);
		res.setReplies(replies);
		res.setUserAccount(userAccount);
		return res;
	}
	public User save(final User user) {
		Assert.notNull(user);
		final User res;

		if (user.getId() != 0) {
			final User logged = this.findByPrincipal();
			Assert.isTrue(logged.getId() == user.getId()); //Si se va a modificar, quien lo vaya a hacer tiene que tener el mismo id que su explorer
		}
		res = this.userRepository.save(user);
		return res;

	}
	public User findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		final User res;
		res = this.userRepository.findByUserAccountId(userAccount.getId());
		return res;
	}
	public User findByPrincipal() {
		final User res;
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);
		return res;
	}
	public Collection<User> findAll() {
		Collection<User> res;
		res = this.userRepository.findAll();
		return res;
	}
	public User findOne(final int userId) {
		Assert.notNull(userId);
		User res;
		res = this.userRepository.findOne(userId);
		Assert.notNull(res);
		return res;

	}
	public Boolean hasUserRSVP(final Integer rendezvousId) {
		Boolean res = false;
		final User logged = this.findByPrincipal();

		Assert.notNull(logged);
		final Integer userId = logged.getId();
		final Collection<RSVP> rsvps = this.userRepository.hasUserRSVP(userId);

		for (final RSVP r : rsvps)
			if (r.getRendezvous().getId() == rendezvousId)
				res = true;
		return res;
	}
}
