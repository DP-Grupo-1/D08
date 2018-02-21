<%--
 * header.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<link type="text/css" href="styles/menu.css" rel="stylesheet" />
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/menu.js"></script>

<div>
	<img src="images/logo.png" alt="Acme-Rendezvous Co., Inc." />
</div>

<div id="menu">
	<ul class="menu">
		<security:authorize access="hasRole('ADMIN')">
		<li><a href="#" class="parent"><span><spring:message	code="master.page.administrator" /></span></a>
			<div><ul>
				<li><a href="administrator/action-1.do"><span><spring:message code="master.page.administrator.action.1" /></span></a>
				<li><a href="administrator/action-2.do"><span><spring:message code="master.page.administrator.action.2" /></span></a></li>
			</ul></div>
		</li>
		</security:authorize>
		
		<security:authorize access="hasRole('USER')">
			<li><a href="#" class="parent"><span><spring:message code="master.page.user" /></span></a>
				<div><ul>
					<li><a href="rendezvous/user/list.do"><span><spring:message code="master.page.user.action1" /></span></a></li>
					<li><a href="rsvp/user/list.do"><span><spring:message code="master.page.user.action2" /></span></a></li>
					<li><a href="question/user/list.do"><span><spring:message code="master.page.user.action3" /></span></a></li>
				</ul></div>
			</li>
		</security:authorize>
		
		
		<security:authorize access="isAnonymous()">
		
			<li><a href="#" class="parent"><span><spring:message code="master.page.common" /></span></a>
				<div><ul>
					<li><a href="user/list.do"><span><spring:message code="master.page.common1" /></span></a></li>
					<li><a href="rendezvous/list.do"><span><spring:message code="master.page.common2" /></span></a></li>
					<li><a href="announcement/list.do"><span><spring:message code="master.page.common3" /></span></a></li>
					<li><a href="j_spring_security_logout"><span><spring:message code="master.page.logout" /></span></a></li>
				</ul></div>
			</li>
			
			<li><a href="security/login.do"><span><spring:message code="master.page.login" /></span></a></li>
		</security:authorize>
		
		
		<security:authorize access="isAuthenticated()">
		<li><a href="#" class="parent"><span><spring:message code="master.page.profile" />(<security:authentication property="principal.username" />)</span></a>
			<div><ul>
				<li><a href="profile/action-1.do"><span><spring:message code="master.page.profile.action.1" /></span></a></li>
				<li><a href="profile/action-2.do"><span><spring:message code="master.page.profile.action.2" /></span></a></li>
				<li><a href="profile/action-3.do"><span><spring:message code="master.page.profile.action.3" /></span></a></li>
				<li><a href="j_spring_security_logout"><span><spring:message code="master.page.logout" /></span></a></li>
			</ul></div>
		</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

