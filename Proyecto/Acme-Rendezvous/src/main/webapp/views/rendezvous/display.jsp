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

<html>

<head>
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="shortcut icon" href="favicon.ico"/> 
	<title>Display Husts</title>
</head>

<body>

		<div style="position: relative; width: 500px; height: 250px;">			
		
	
		
			
		
			
	
			<b>	<spring:message	code="notaAviso.trackNumber" />: </b>
				<jstl:out  value="${notaAviso.trackNumber}" /> <br>	
			 <form:errors cssClass="error"/> 
			 
			<b>	<spring:message	code="notaAviso.title" />: </b>
				<jstl:out  value="${notaAviso.title}" /> <br>	
				
			<b>	<spring:message	code="notaAviso.description" />: </b>
				<jstl:out  value="${notaAviso.description}" /> <br>	
				

				<jstl:if test="${notaAviso.gauge==1}">
					<jstl:set var = "gaugeColor" value = "lime" />
				</jstl:if>
				
				<jstl:if test="${notaAviso.gauge==2}">
				<jstl:set var = "gaugeColor" value = "tomato" />
				</jstl:if>
				
				<jstl:if test="${notaAviso.gauge==3}">
				<jstl:set var = "gaugeColor" value = "burlywood" />
				</jstl:if>
				

				
					<b>	<spring:message	code="notaAviso.gauge" />: </b>
					
				<span style="color: ${gaugeColor}">
					<jstl:out value="${word}" /> 
				</span> 
				
				<br>
				
		
			<b>	<spring:message	code="notaAviso.displayMoment" />: </b>
				<jstl:out  value="${notaAviso.displayMoment}" /> <br>	
		
				
			
		
		</div>
	
</body>
</html>