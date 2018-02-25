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
	
	<spring:message code="rendezvous.adultOnly" var="adultOnlyHeader" />
		<display:column property="adultOnly" title="${adultOnlyHeader}" sortable="true" />
	
	<spring:message code="rendezvous.flag" var="flagHeader" />
	<display:column property="flag" title="${flagHeader}" sortable="true">
	</display:column>
	
	<spring:message code="rendezvous.creator" var="creatorHeader" />
    <display:column title="${creatorHeader}" sortable="true">
    <a href="user/display.do?userId=<jstl:out value="${row.creator.id}"/>">
    <jstl:out value="${row.creator.name} ${row.creator.surname}"/></a>
    </display:column> 
    
    <display:column >
	<a  href="user/list.do?rendezvousId=${row.id}"><spring:message code="rendezvous.attendants" /></a>
    </display:column>
    
    <spring:message code="rendezvous.comments" var="commentsHeader" />
    <display:column>
  		<a href="comment/list.do?rendezvousId=${row.id}">
 			<spring:message code="rendezvous.comments" />
 		</a>	
    </display:column> 
    
    <security:authorize access="hasRole('USER')">
    <jstl:if test="${row.creator.userAccount.username eq pageContext.request.userPrincipal.name}">
	<display:column >
		<a  href="rendezvous/user/rendezvouses.do?rendezvousId=${row.id}"><spring:message code="rendezvous.link" /></a>
	</display:column>
	</jstl:if>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('USER')">
	<jstl:choose> 
	<jstl:when test="${hasUserRSVPd==false}">
	  	<a href="rendezvous/user/attend.do?rendezvousId=${row.id}">
		  	<spring:message code="rendezvous.attend" />
		</a>	
	</jstl:when>
	<jstl:otherwise>
	  	<a href="rendezvous/user/noAttend.do?rendezvousId=${row.id}">
		  	<spring:message code="rendezvous.noAttend" />
		</a>	
	</jstl:otherwise>
	</jstl:choose>
	<br>
</security:authorize> 


<security:authorize access="hasRole('ADMIN')">
         <a href="rendezvous/administrator/edit.do?rendezvousId=${row.id}">
           <spring:message code="rendezvous.edit" />
         </a>
         <br>
</security:authorize>


<security:authorize access="hasRole('USER')">
      <jstl:if test="${row.creator.userAccount.username eq pageContext.request.userPrincipal.name}">
      <jstl:if test="${row.finalMode == false}">
         <a href="rendezvous/user/edit.do?rendezvousId=${row.id}">
           <spring:message code="rendezvous.edit" />
         </a>
         </jstl:if>
       </jstl:if>
</security:authorize>


<!-- 							 Rendezvouses linked										-->
<h1><spring:message code="rendezvous.linked" /></h1>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="rendezvous.rendezvouses" requestURI="${requestURI}" id="row">
	
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
    <a href="profile/user/display.do?userId=<jstl:out value="${row.creator.id}"/>">
    <jstl:out value="${row.creator.name} ${row.creator.surname}"/></a>
    </display:column> 
   	    
   	<display:column >
   	<a  href="user/list.do?rendezvousId=${row.id}"><spring:message code="rendezvous.attendants" /></a>
    </display:column>

	<spring:message code="rendezvous.adultOnly" var="rendezvousAdultOnlyHeader" />
	<display:column property="adultOnly" title="${rendezvousAdultOnlyHeader}" sortable="true" />
			
	<spring:message code="rendezvous.flag" var="rendezvousFlagHeader" />
	<display:column property="flag" title="${rendezvousFlagHeader}" sortable="true" />
	
	<security:authorize access="hasRole('USER')">
	<jstl:if test="${row.creator.userAccount.username eq pageContext.request.userPrincipal.name}">
    <display:column>
	     <a href="rendezvous/user/deleteLink.do?rendezvousId=${row.id}">
	       <spring:message code="rendezvous.deleteLink" />
	     </a>	
    </display:column>
    </jstl:if>
    </security:authorize> 
  
</display:table>
