<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<security:authorize access="hasRole('ADMIN')">

<table>
	<tr> <td><spring:message code="dashboard.1.1" />:</td> <td></td>	</tr>
	<tr> <td><spring:message code="dashboard.1.2" /> <td></td>	</tr>
	<tr> <td><spring:message code="dashboard.2" />: <td></td>	</tr>
	<tr> <td><spring:message code="dashboard.3.1" />: </td> <td> <jstl:out value="${avgUsersPerRendezvous}"/> </td> </tr>
	<tr> <td><spring:message code="dashboard.3.2" />: </td> <td> <jstl:out value="${stddevUsersPerRendezvous}"/> </td> </tr>
	<tr> <td><spring:message code="dashboard.4.1" />: </td> <td> <jstl:out value="${avgRSVPsPerUser}"/> </td>	</tr>
	<tr> <td><spring:message code="dashboard.4.2" />: </td> <td> <jstl:out value="${stddevRSVPsPerUser}"/> </td>	</tr>
	<tr> <td><spring:message code="dashboard.5" />:	</td>
											<td> <jstl:forEach var="x" items="${top10RendezvousesByRSVPs}" varStatus="status">
												<a href="rendezvous/display.do?rendezvousId=${x.id}"><jstl:out value="${x.name}" /></a><jstl:if test="${not status.last}">,</jstl:if> 
											</jstl:forEach> </td>
	</tr>											
</table>
</security:authorize>