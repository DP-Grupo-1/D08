
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.RSVP;
import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.userAccount.id=?1")
	User findByUserAccountId(int userAccountId);
	@Query("select r from RSVP r where r.user.id = ?1")
	Collection<RSVP> hasUserRSVP(Integer userId);
}
