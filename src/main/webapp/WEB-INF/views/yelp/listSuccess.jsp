<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="paginator" uri="/WEB-INF/tlds/Paginator"%>
<link href="${pageContext.request.contextPath}/jsPlugin/introJS/css/introjs.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/jsPlugin/introJS/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jsPlugin/introJS/intro.js"></script>
<div class="content-wrapper">
	<section class="content">
	     Yelp
		<br />
        <c:forEach items="${region.businesses}" var="business">
           	<div class="col-md-6">
              <div class="nav-tabs-custom">
                <ul class="nav nav-tabs ">
                  <li class="active"> <c:out value="${business.id}"/></li>
                </ul>
                <div class="tab-content">
                  <div class="tab-pane active" id="tab_1-1">
                  
                    <div class="embed-responsive embed-responsive-16by9">
                        <a href="${business.url}" target="_blank">
                        <img class="embed-responsive-item" src="${business.image_url}"></img></a>
                      </div>
                  </div><!-- /.tab-pane -->
                </div><!-- /.tab-content -->
              </div><!-- nav-tabs-custom -->
            </div>
        </c:forEach>

	
	</section>
</div>