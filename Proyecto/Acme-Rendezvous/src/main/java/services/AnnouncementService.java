
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import security.LoginService;
import security.UserAccount;
import domain.Announcement;

@Service
@Transactional
public class AnnouncementService {

	//Managed Repository-----------------------------------------------
	@Autowired
	AnnouncementRepository	announcementRepository;

	//Suporting services-----------------------------------------------
	@Autowired
	UserService				userService;

	@Autowired
	AdministratorService	administratorService;


	//CRUD methods
	public Announcement create() {
		final Date moment = new Date();
		final Announcement result = new Announcement();
		result.setMoment(moment);
		return result;

	}

	public Announcement save(final Announcement announcement) {
		UserAccount userAcc = LoginService.getPrincipal();
		Assert.notNull(userAcc);
		Assert.notNull(this.userService.findByUserAccount(userAcc));
		Assert.notNull(announcement);

		Announcement res = this.announcementRepository.save(announcement);

		return res;
	}

	public void delete(final Announcement announcement) {

		Assert.notNull(announcement);

		UserAccount userAcc = LoginService.getPrincipal();
		Assert.notNull(userAcc);
		Assert.notNull(this.administratorService.findByUserAccount(userAcc));

		Assert.isTrue(this.announcementRepository.exists(announcement.getId()));

		this.announcementRepository.delete(announcement);
	}

	public Collection<Announcement> findAll() {
		Collection<Announcement> res;
		res = this.announcementRepository.findAll();
		return res;
	}

	public Announcement findOne(final int announcementId) {
		Assert.notNull(announcementId);
		Announcement res;
		res = this.announcementRepository.findOne(announcementId);
		Assert.notNull(res);
		return res;
	}

}
