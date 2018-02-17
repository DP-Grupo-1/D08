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


<display:table name="comments" id="row" pagesize="5"
	requestURI="${requestURI}" class="displaytag" keepStatus="true">


	<!-- Edit -->
	<security:authorize access="hasRole('USER')">
		<!-- Display -->
		<display:column>
			<a href="comment/user/edit.do?commentId=${row.id}">
				<spring:message code="comment.list.edit" />
			</a>
		</display:column>
	</security:authorize>



	<!-- Attributes -->

	<spring:message code="comment.date" var="dateHeader" />
	<display:column property="date" title="${dateHeader}" sortable="true" />

	<spring:message code="comment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" sortable="true" />
	 

	<spring:message code="comment.picture" var="pictureHeader" />
	<display:column property="picture" title="${pictureHeader}" sortable="true" />



<!-- Display comment -->
	<spring:message code="comment.display" var="nameHeader" />
	<display:column title="${nameHeader}">
			<a href="comment/display.do?commentId=${row.id}">
				${row.name}
			</a>
	</display:column>


<!-- Replies -->
	<spring:message code="comment.reply" var="nameHeader" />
	<display:column title="${nameHeader}">
			<a href="reply/list.do?commentId=${row.id}">
				${row.name}
			</a>
	</display:column>
	 

	
	


</display:table>

<%-- <security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="category/administrator/create.do"><spring:message
				code="category.edit.create" /></a>
	</div>
</security:authorize>  --%>