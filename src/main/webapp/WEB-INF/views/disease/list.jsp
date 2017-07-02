<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="paginator" uri="/WEB-INF/tlds/Paginator"%>
<link href="${pageContext.request.contextPath}/jsPlugin/introJS/css/introjs.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/jsPlugin/introJS/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jsPlugin/introJS/intro.js"></script>
<div class="content-wrapper">
    <section class="content">
        List  YouTube 10 MaxView Result
        <br />
        <c:forEach items="${diseases}" var="disease">
            <div class="col-md-6">
                <c:out value="${disease.category}"/>
            </div>
        </c:forEach>


    </section>
</div>