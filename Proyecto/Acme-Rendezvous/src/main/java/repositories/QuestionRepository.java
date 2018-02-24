package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	@Query("select q from Question q where q.creator.id = ?1 and q.rendezvous.id = ?2")
	Collection<Question> findAllByPrincipalAndRendezvous(int principalId, int rendezvousId);

	@Query("select q from Question q where q.rendezvous.id = ?1")
	Collection<Question> findAllByRendezvous(int rendezvousId);

}
