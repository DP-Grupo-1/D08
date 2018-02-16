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

<security:authorize access="hasRole('MANAGER')">
<form:form action="notaAviso/manager/edit.do" modelAttribute="notaAviso">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="trackNumber" />
	<form:hidden path="trip" />
	

	<form:label path="title">
		<spring:message code="notaAviso.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="description">
		<spring:message code="notaAviso.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="gauge">
		<spring:message code="notaAviso.gauge" />:
	</form:label>
	<form:input path="gauge" />
	<form:errors cssClass="error" path="gauge" />
	<br />
	
	<form:label path="displayMoment">
		<spring:message code="notaAviso.displayMoment" />:
	</form:label>
	<form:input path="displayMoment" />
	<form:errors cssClass="error" path="displayMoment" />
	<br />
	

	<input type="submit" name="save"
		value="<spring:message code="notaAviso.save" />" />&nbsp; 
    <jstl:if test="${notaAviso.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="notaAviso.delete" />"
			onclick="return confirm('<spring:message code="notaAviso.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="notaAviso.cancel" />"
		onclick="javascript: relativeRedir('notaAviso/manager/list.do');" />
	<br />

</form:form>
</security:authorize>