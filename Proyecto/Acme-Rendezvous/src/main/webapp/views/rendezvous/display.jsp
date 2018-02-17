<%--
 * display.jsp
 *
 * Copyright (C) 2015 Universidad de Sevilla
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
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 									Rendezvous											-->
<h1>Rendezvous</h1>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="rendezvous" requestURI="${requestURI}" id="row">

	<spring:message code="rendezvous.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true">
	</display:column>

	<spring:message code="rendezvous.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="true">
	</display:column>

    <spring:message code="rendezvous.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" sortable="true">
	</display:column>

	<spring:message code="rendezvous.picture" var="pictureHeader" />
	<display:column property="picture" title="${pictureHeader}" sortable="true">
	</display:column>

	<spring:message code="rendezvous.locationLatitude" var="locationLatitudeHeader" />
	<display:column property="locationLatitude" title="${locationLatitudeHeader}"
		sortable="true">
	</display:column>

	<spring:message code="rendezvous.locationLongitude" var="locationLongitudeHeader" />
	<display:column property="locationLongitude" title="${locationLongitudeHeader}"
		sortable="true">
	</display:column>
	
	<spring:message code="rendezvous.finalMode" var="finalModeHeader" />
	<display:column property="finalMode" title="${finalModeHeader}" sortable="true">
	</display:column>
	
	<spring:message code="rendezvous.flag" var="flagHeader" />
	<display:column property="flag" title="${flagHeader}" sortable="true">
	</display:column>
	
	<spring:message code="rendezvous.creator" var="creatorHeader" />
    <display:column title="${creatorHeader}" sortable="true">
    <a href="profile/personalData/list.do?actorId=<jstl:out value="${row.creator.id}"/>">
    <jstl:out value="${row.creator.name} ${row.creator.surname}"/></a>
    </display:column> 
    
    <display:column >
	<a  href="user/list.do?rendezvousId=${row.id}"><spring:message code="rendezvous.attendants" /></a>
    </display:column>
	
</display:table>


<!-- 									Announcements											-->
<h1>Announcements</h1>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="rendezvous.announcements" requestURI="${requestURI}" id="row">

	<spring:message code="rendezvous.announcement.title" var="announcementTitleHeader" />
	<display:column property="title" title="${announcementTitleHeader}"
		sortable="true">
	</display:column>

	<spring:message code="rendezvous.announcement.description"
		var="announcementDescriptionHeader" />
	<display:column property="description"
		title="${announcementDescriptionHeader}" sortable="true">
	</display:column>

	<spring:message code="rendezvous.announcement.price" var="announcementPriceHeader" />
	<display:column property="price" title="${announcementPriceHeader}"
		sortable="true">
	</display:column>

	<spring:message code="rendezvous.announcement.number" var="announcementNumberHeader" />
	<display:column property="number" title="${announcementNumberHeader}"
		sortable="true">
	</display:column>
	
</display:table>


<!-- 									Rendezvouses											-->
<h1>Rendezvouses</h1>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="rendezvous.stories" requestURI="${requestURI}" id="row">
	
	<!-- Display -->
		
	<display:column>
		<a href="rendezvous/display.do?rendezvousId=${row.id}">
			<spring:message code="rendezvous.list.display" />
		</a>
	</display:column>

	<spring:message code="rendezvous.name" var="rendezvousNameHeader" />
	<display:column property="name" title="${rendezvousNameHeader}" sortable="true">
	</display:column>

	<spring:message code="rendezvous.description" var="rendezvousDescriptionHeader" />
	<display:column property="description" title="${rendezvousDescriptionHeader}" 
	    sortable="true">
	</display:column>
	
	<spring:message code="rendezvous.moment" var="rendezvousMomentHeader" />
    <spring:message code="rendezvous.moment.format" var="rendezvousMomentFormat" />
	<display:column property="moment" title="${rendezvousMomentHeader}" 
	    titleKey="rendezvous.moment"
		sortable="true" format="{0,date,${rendezvousMomentFormat }}" />

	<spring:message code="rendezvous.creator" var="rendezvousCreatorHeader" />
    <display:column title="${rendezvousCreatorHeader}" sortable="true">
    <a href="profile/personalData/list.do?actorId=<jstl:out value="${row.creator.id}"/>">
    <jstl:out value="${row.creator.name} ${row.creator.surname}"/></a>
    </display:column> 
   	    
   	<display:column >
   	<a  href="user/list.do?rendezvousId=${row.id}"><spring:message code="rendezvous.attendants" /></a>
    </display:column>

	<spring:message code="rendezvous.adultOnly" var="rendezvousAdultOnlyHeader" />
	<display:column property="adultOnly" title="${rendezvousAdultOnlyHeader}" sortable="true" />
			
	<spring:message code="rendezvous.flag" var="rendezvousFlagHeader" />
	<display:column property="flag" title="${rendezvousFlagHeader}" sortable="true" />

</display:table>


<!-- 									Comments											-->
<h1>Comments</h1>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="rendezvous.comments" requestURI="${requestURI}" id="row">
	
	<spring:message code="comment.moment" var="commentMomentHeader" />
    <spring:message code="comment.moment.format" var="commentMomentFormat" />
	<display:column property="moment" title="${commentMomentHeader}" 
	    titleKey="comment.moment"
		sortable="true" format="{0,date,${commentMomentFormat }}" />

	<spring:message code="comment.text" var="commentTextHeader" />
	<display:column property="text" title="${commentTextHeader}"
		sortable="true">
	</display:column>

	<spring:message code="comment.picture" var="commentPictureHeader" />
	<display:column property="picture" title="${commentPictureHeader}"
		sortable="true">
	</display:column>

	<display:column >
   	<a  href="reply/list.do?commentId=${row.id}"><spring:message code="comment.replies" /></a>
    </display:column>
	
</display:table>

<!-- Action links -->

<security:authorize access="hasRole('USER')">
  <div>
	<a href="comment/user/create.do"><spring:message code="comment.create" /></a>
  </div>
</security:authorize>