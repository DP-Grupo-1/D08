<%--
 * edit.jsp
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
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasRole('USER')">
<form:form action="rendezvous/user/edit.do" modelAttribute="rendezvous">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="flag" />
	<form:hidden path="creator" />
	<form:hidden path="attendants" />
	<form:hidden path="rendezvouses" />
	<form:hidden path="comments" />
	

    <acme:textbox code="rendezvous.name" path="name"/>
    
    <acme:textbox code="rendezvous.description" path="description"/>
    
    <acme:textbox code="rendezvous.moment" path="moment"/>
    
    <acme:textbox code="rendezvous.picture" path="picture"/>
    
    <acme:textbox code="rendezvous.locationLatitude" path="locationLatitude"/>
    
    <acme:textbox code="rendezvous.locationLongitude" path="locationLongitude"/>
	
	<spring:message code="rendezvous.finalMode" />:
	<input type="checkbox" name="finalMode"
		value="true" />
	<br>
	
	<spring:message code="rendezvous.adultOnly" />:
	<input type="checkbox" name="adultOnly" 
	<jstl:if test="${rendezvous.adultOnly==true}">
	checked
	</jstl:if> 
		value="true" />
	<br>
	
	<jstl:if test="${rendezvous.finalMode==false || rendezvous.flag!=Flag.DELETED}">
		<input type="submit" name="save"
			value="<spring:message code="rendezvous.save"/>" 
			/>&nbsp;
	<input type="submit" name="delete"
			value="<spring:message code="rendezvous.delete"/>" />&nbsp;
    </jstl:if> 

	<input type="button" name="cancel"
		value="<spring:message code="rendezvous.cancel" />"
		onclick="javascript: relativeRedir('rendezvous/user/list.do');" />
	<br />

</form:form>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
<form:form action="rendezvous/administrator/edit.do" modelAttribute="rendezvous">

    <form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="flag" />
	<form:hidden path="creator" />
	<form:hidden path="attendants" />
	<form:hidden path="rendezvouses" />
	<form:hidden path="comments" />
	

    <acme:textbox code="rendezvous.name" path="name"/>
    
    <acme:textbox code="rendezvous.description" path="description"/>
    
    <acme:textbox code="rendezvous.moment" path="moment"/>
    
    <acme:textbox code="rendezvous.picture" path="picture"/>
    
    <acme:textbox code="rendezvous.locationLatitude" path="locationLatitude"/>
    
    <acme:textbox code="rendezvous.locationLongitude" path="locationLongitude"/>
	
	<spring:message code="rendezvous.finalMode" />:
	<input type="checkbox" name="finalMode"
		value="true" />
	<br>
	
	<spring:message code="rendezvous.adultOnly" />:
	<input type="checkbox" name="adultOnly"
		value="true" />
	<br>
	
	<input type="submit" name="save"
		value="<spring:message code="rendezvous.save"/>" />&nbsp;
	<input type="submit" name="delete"
		value="<spring:message code="rendezvous.delete"/>" />&nbsp; 

	<input type="button" name="cancel"
		value="<spring:message code="rendezvous.cancel" />"
		onclick="javascript: relativeRedir('rendezvous/user/list.do');" />
	<br />

</form:form>
</security:authorize>