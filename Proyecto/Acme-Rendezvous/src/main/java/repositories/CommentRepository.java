
package repositories;


import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;


import domain.Comment;
import domain.Trip;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("select c from Comment c where t.publicationDate < ?1")
	Collection<Comment> findByRendezvous(Integer rendezvousId);

	
}
