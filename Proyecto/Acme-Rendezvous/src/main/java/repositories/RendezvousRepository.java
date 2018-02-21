/*
 * ExamRepository.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.RSVP;
import domain.Rendezvous;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {

//	//Requisito 4.2: Lista de reuniones o quedadas a las que el usuario va a asistir
//	//o ya ha asistido.
//	@Query("select r.rendezvous from RSVP r where r.user.id = ?1")
//	Collection<Rendezvous> findByUserId(int userId);
//
//	//Requisito 6.3 punto 1: La media y la desviación estándar de reuniones creadas por usuario.
//	@Query("select avg(r.rendezvouses.size where ), stddev(u.rendezvouses.size) from User u")
//	Double[] avgStddevRendezvousPerUser();
//
//	//Requisito 6.3 punto 2: Ratio de usuarios que han creado al menos una reunión.
//	@Query("select sum(case when u.rendezvouses.size > 0 then 1 else 0 end) / count(*) *1.0 from User u")
//	Double ratioUserWithRendezvous();
//
//	//Requisito 6.3 punto 3: La media y la desviación estándar de usuarios por reunión.
//	@Query("select avg(r.attendants.size), stddev(r.attendants.size) from Rendezvous r")
//	Double[] avgStddevUsersPerRendezvous();
//
//	//Requisito 6.3 punto 4: La media y la desviación estándar de reuniones que son RSVPd 
//	//por usuario.
//	@Query("select avg(u.rsvps.size), stddev(u.rsvps.size) from User u")
//	Double[] avgStddevRSVPsPerUser();
//
//	//Requisito 6.3 punto 5: Top 10 de reuniones en las que más usuarios han RSPVd.
//	@Query("select r from Rendezvous r order by count(r.rsvps) DESC LIMIT 10")
//	Collection<Rendezvous> top10RendezvousesByRSVPs();
//
//	//Requisito 17.2 punto 2: Las reuniones cuyo número de anuncios está por encima del 75% 
//	//de la media del número de anuncios por reunión.
//	@Query("select r from Rendezvous r where r.announcements.size > (0.75*avg(r.announcements.size));")
//	Collection<Rendezvous> above75AverageOfAnnouncementsPerRendezvous();

//	@Query("select r1 from Rendezvous r1 join r1.rendezvouses r2 where r2.id = ?1")
//	Collection<Rendezvous> findRendezvousParents(int rendezvousId);
//
//	@Query("select r1 from RSVP r1 join r1.rendezvouses r2 where r2.id = ?1")
//	Collection<RSVP> findRSVPs(int rendezvousId);

}
