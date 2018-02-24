
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

	@Query("select a from Announcement a where a.rendezvous.id=?1")
	Collection<Announcement> findAnnouncementsByRendezvousId(int rendezvousId);


	//The average of announcements per rendezvous
	@Query("select count(a)*1.0 / (select count(r)*1.0 from Rendezvous r) from Announcement a")
	Double avgOfAnnouncementsPerRendezvous();

	//The standard deviation of announcements per rendezvous
	//@Query("select sqrt(sum(r.announcements.size * r.announcements.size) / count(r.announcements.size) - (avg(r.announcements.size) * avg(r.announcements.size))) from Rendezvous r")


	//The rendezvouses that are linked to a number of rendezvouses that is greater than the average plus 10%
}
