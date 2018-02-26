<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="acme"	tagdir="/WEB-INF/tags"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="${requestURI}" modelAttribute="answerQuestions">
<<<<<<< HEAD
	<jstl:forEach items="answerQuestions.questions" var="currentQuestion" varStatus="current">
	 	<jstl:set var="currentQuestion.questionToAnswer" value="${quest}" />
		<jstl:out value="${quest}"></jstl:out>
		<acme:textbox code="currentQuestion.answers" path="questions[${current.index}].answers[${current.index}]"/>
=======
	<form:hidden path="questions" />
	
	<jstl:forEach items="${answerQuestions.questions}" var="currentQuestion" varStatus="current">
	 	<jstl:set var="quest" value="${currentQuestion.questionToAnswer}" />
		<jstl:out value="${quest}"></jstl:out>
		<acme:textbox code="currentQuestion.answers" path="answers[${current.index}].written"/>
>>>>>>> 024f406c6d11d9fb930c95ecaa6b919457bfb467
		<br/>
	</jstl:forEach>
	<input type="submit" name="save"
	value="<spring:message code="question.save" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="question.cancel" />"
		onclick="location.href = ('welcome/index.do');" />
</form:form>