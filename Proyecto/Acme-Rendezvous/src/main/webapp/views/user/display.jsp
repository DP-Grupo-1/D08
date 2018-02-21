<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


	
	
	
	
			<b>	<spring:message	code="user.name" />: </b>
				<jstl:out  value="${user.name}" /> <br>	
			 <form:errors cssClass="error"/> 
			
			
			<b>	<spring:message	code="user.surname" />: </b>
				<jstl:out  value="${user.surname}" /> <br>	
			 <form:errors cssClass="error"/> 
			 
			 <b>	<spring:message	code="user.postalAddress" />: </b>
				<jstl:out  value="${user.postalAddress}" /> <br>	
			 <form:errors cssClass="error"/> 
			 
			 
			  <b>	<spring:message	code="user.phoneNumber" />: </b>
				<jstl:out  value="${user.phoneNumber}" /> <br>	
			 <form:errors cssClass="error"/> 
			 
			  <b>	<spring:message	code="user.email" />: </b>
				<jstl:out  value="${user.email}" /> <br>	
			 <form:errors cssClass="error"/> 
			 