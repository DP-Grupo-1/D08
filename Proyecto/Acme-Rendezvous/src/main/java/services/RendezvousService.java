
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RendezvousRepository;
import domain.Administrator;
import domain.Announcement;
import domain.Comment;
import domain.Flag;
import domain.Question;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class RendezvousService {

	// Managed repository -----------------------------------------

	@Autowired
	private RendezvousRepository	rendezvousRepository;

	// Supporting services ------------------------------------------

	@Autowired
	private UserService				userService;

	@Autowired
	private AdministratorService	administratorService;
	
	@Autowired 
	private QuestionService questionService;
	
	@Autowired 
	private AnnouncementService announcementService;



	// Simple CRUD methods ------------------------------------------

	public Rendezvous create() {

		final Rendezvous result = new Rendezvous();
		final User user = this.userService.findByPrincipal();
		final Collection<User> attendants = new ArrayList<User>();
		final Collection<Rendezvous> rendezvouses = new ArrayList<Rendezvous>();
		final Collection<Comment> comments = new ArrayList<Comment>();

		result.setAttendants(attendants);
		result.setRendezvouses(rendezvouses);
		result.setComments(comments);
		result.setFinalMode(false);
		result.setAdultOnly(false);
		result.setCreator(user);
		result.setFlag(Flag.ACTIVE);

		return result;
	}

	public Rendezvous save(final Rendezvous rendezvous) {

		Assert.notNull(rendezvous);
		Rendezvous result;

		final User user = this.userService.findByPrincipal();
		Assert.notNull(user);

		//		Assert.isTrue(rendezvous.getFinalMode() == false);
		Assert.isTrue(rendezvous.getFlag() != Flag.DELETED);

		if (rendezvous.getId() == 0) {

			result = this.rendezvousRepository.save(rendezvous);

			//			result.setCreator(user);
			result.getAttendants().add(user);

			this.findByCreatorId(user.getId()).add(result);

		} else

			result = this.rendezvousRepository.save(rendezvous);

		return result;
	}
	
	public Rendezvous onlySave(final Rendezvous rendezvous) {
		Rendezvous saved;
		saved = this.rendezvousRepository.save(rendezvous);
		return saved;
	}
	
	public void onlyDelete(final Rendezvous rendezvous) {
		
		this.rendezvousRepository.delete(rendezvous);
	}
	
	

	public void deleteByUser(final Rendezvous rendezvous) {
		
		Assert.notNull(rendezvous);
		Assert.notNull(this.findOne(rendezvous.getId()));

		final User user = this.userService.findByPrincipal();
		Assert.notNull(user);

		Assert.isTrue(rendezvous.getFinalMode() == false);
		Assert.isTrue(rendezvous.getFlag() != Flag.DELETED);
		rendezvous.setFlag(Flag.DELETED);
		this.onlySave(rendezvous);
	}

	public void deleteByAdmin(final Rendezvous rendezvous) {
		System.out.println("llego aqui2");
		Assert.notNull(rendezvous);
		System.out.println("llego aqui3");
		Collection<Question> questions = questionService.findAllByrendezvous(rendezvous.getId());
		System.out.println("llego aqui4");
		Collection<Announcement> announcements = rendezvous.getAnnouncements();
		System.out.println("llego aqui5");
		Collection<User> attendants = rendezvous.getAttendants();
		System.out.println("llego aqui6");
		Assert.notNull(this.findOne(rendezvous.getId()));
		System.out.println("llego aqui7");

		final Administrator admin = this.administratorService.findByPrincipal();
		Assert.notNull(admin);

		System.out.println("llego aqui8");
		final Collection<Rendezvous> rendezvouses = this.findRendezvousParents(rendezvous.getId());
		System.out.println("llego aqui9");
		if(!rendezvouses.isEmpty()){
			System.out.println("llego aqui10");
		for (final Rendezvous r : rendezvouses)
			
			r.getRendezvouses().remove(rendezvous);
		}
		System.out.println("llego aqui11");
		if(!questions.isEmpty()){
			System.out.println("llego aqui111");
		for(Question q: questions){
			
				this.questionService.deleteByAdmin(q);
			}
		}
		System.out.println("llego aqui12");
		if(!announcements.isEmpty()){
			for(Announcement a: announcements){
				
					this.announcementService.delete(a);
				}
			}
		System.out.println("llego aqui13");
		if(!attendants.isEmpty()){
			for(User u: attendants){
				
					u.getAttendances().remove(rendezvous);
					
				}
			}
		
		
		System.out.println("llego aqui14");
		this.rendezvousRepository.delete(rendezvous);
		System.out.println("llego aqui15");
	}

	public Collection<Rendezvous> findAll() {
		final Collection<Rendezvous> result = this.rendezvousRepository.findAll();

		for (final Rendezvous r : result){
			if (r.getMoment().before(new Date()) && r.getFlag() == Flag.ACTIVE){
				r.setFlag(Flag.PASSED);
				this.onlySave(r);
				result.add(r);
			}}
		return result;
	}

	public Rendezvous findOne(final int rendezvousId) {
		final Rendezvous res = this.rendezvousRepository.findOne(rendezvousId);
		if (res.getMoment().before(new Date()) && res.getFlag() == Flag.ACTIVE){
			res.setFlag(Flag.PASSED);
			this.onlySave(res);
		}
		return res;
	}
	
//	public Rendezvous findOneOnly(final int rendezvousId) {
//		final Rendezvous res = this.rendezvousRepository.findOne(rendezvousId);
//		return res;
//	}

	// Other business methods ----------------------------------

	public Collection<Rendezvous> findByUserId(final int userId) {
		Collection<Rendezvous> result;
		result = this.rendezvousRepository.findByUserId(userId);

		for (final Rendezvous r : result){
			if (r.getMoment().before(new Date()) && r.getFlag() == Flag.ACTIVE){
				r.setFlag(Flag.PASSED);
				this.onlySave(r);
				result.add(r);
			}}
		return result;
	}
	public Collection<Rendezvous> findByCreatorId(final int userId) {
		final Collection<Rendezvous> res = this.rendezvousRepository.findByCreatorId(userId);
		for (final Rendezvous r : res){
			if (r.getMoment().before(new Date()) && r.getFlag() == Flag.ACTIVE){
				r.setFlag(Flag.PASSED);
				this.onlySave(r);
				res.add(r);
			}}
		return res;
	}

	//--------------------------------------------- DASHBOARD ---------------------------------------------------------

	//1
	public Double avgRendezvousPerUser() {
		final Double result = this.rendezvousRepository.avgRendezvousPerUser();
		return result;
	}
	//
	//	public Double ratioUserWithRendezvous() {
	//		final Double result = this.rendezvousRepository.ratioUserWithRendezvous();
	//		return result;
	//	}

	//3.1
	public Double avgUsersPerRendezvous() {
		final Double result = this.rendezvousRepository.avgUsersPerRendezvous();
		return result;
	}

	//3.2
	public Double stddevUsersPerRendezvous() {
		final Double result = this.rendezvousRepository.stddevUsersPerRendezvous();
		return result;
	}

	//4.1
	public Double avgRSVPsPerUser() {
		final Double result = this.rendezvousRepository.avgRSVPsPerUser();
		return result;
	}

	//4.2
	public Double stddevRSVPsPerUser() {
		final Double result = this.rendezvousRepository.stddevRSVPsPerUser();
		return result;
	}

	//5
	public Collection<Rendezvous> top10RendezvousesByRSVPs() {
		final Collection<Rendezvous> top10RendezvousesByRSVPs = this.rendezvousRepository.top10RendezvousesByRSVPs();

		final Collection<Rendezvous> finalTop10RendezvousesByRSVPs = new ArrayList<Rendezvous>();

		for (final Rendezvous r : top10RendezvousesByRSVPs) {
			finalTop10RendezvousesByRSVPs.add(r);
			if (finalTop10RendezvousesByRSVPs.size() >= 10)
				break;
		}

		return finalTop10RendezvousesByRSVPs;
	}

	//	private Collection<Rendezvous> above75AverageOfAnnouncementsPerRendezvous() {
	//		final Collection<Rendezvous> result;
	//		result = this.rendezvousRepository.above75AverageOfAnnouncementsPerRendezvous();
	//		return result;
	//	}

	//	private Collection<Rendezvous> LinkedGreaterAveragePlus10() {
	//		final Collection<Rendezvous> result;
	//		result = this.rendezvousRepository.linkedGreaterAveragePlus10();
	//		return result;
	//	}

	private Collection<Rendezvous> findRendezvousParents(final int id) {
		final Collection<Rendezvous> result;
		result = this.rendezvousRepository.findRendezvousParents(id);
		return result;
	}

	//COMMENT

	public Collection<Comment> findByRendezvous(final Integer rendezvousId) {
		final Collection<Comment> comments = new ArrayList<Comment>();
		final Rendezvous rendezvous = this.rendezvousRepository.findOne(rendezvousId);
		Assert.notNull(rendezvous);
		comments.addAll(rendezvous.getComments());
		return comments;
	}

	public Rendezvous findByCommentId(final Integer commentId) {
		Assert.notNull(commentId);
		final Rendezvous res = this.rendezvousRepository.findByCommentId(commentId);
		return res;
	}

}
