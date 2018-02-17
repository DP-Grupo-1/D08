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

import domain.Rendezvous;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {

	//Requisito 4.2: Lista de reuniones o quedadas a las que el usuario va a asistir o ya ha asistido.
	@Query("select r from Rendezvous r join r.rsvps rs where rs.user.id = ?1")
	Collection<Rendezvous> findByUserId(int userId);

	//Requisito 6.3 punto 1: La media y la desviaci�n est�ndar de reuniones por usuario.
	@Query("select avg(u.rendezvouses.size), stddev(u.rendezvouses.size) from User u")
	Double[] avgStddevRendezvousPerUser();

	//Requisito 6.3 punto 2: Ratio de usuarios que han creado al menos una reuni�n.
	@Query("select sum(case when u.rendezvouses.size > 0 then 1 else 0 end) / count(*) *1.0 from User u")
	Double ratioUserWithRendezvous();

	//Requisito 6.3 punto 3: La media y la desviaci�n est�ndar de usuarios por reuni�n.
	@Query("select avg(r.attendants.size), stddev(r.attendants.size) from Rendezvous r")
	Double[] avgStddevUsersPerRendezvous();

	//Requisito 6.3 punto 4: La media y la desviaci�n est�ndar de reuniones que son RSVPd por usuario.
	@Query("select avg(u.rsvps.size), stddev(u.rsvps.size) from User u")
	Double[] avgStddevRSVPsPerUser();

	//Requisito 6.3 punto 5: Top 10 de reuniones en las que m�s usuarios han RSPVd.
	@Query("select r from Rendezvous r order by count(r.rsvps) DESC LIMIT 10")
	Collection<Rendezvous> top10RendezvousesByRSVPs();
}