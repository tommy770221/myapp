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
        <c:forEach items="${youtubeResult.items}" var="item">
           	<div class="col-md-6">
              <div class="nav-tabs-custom">
                <ul class="nav nav-tabs ">
                  <li class="active"> <c:out value="${item.snippet.title}"/></li>
                </ul>
                <div class="tab-content">
                  <div class="tab-pane active" id="tab_1-1">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${item.id.videoId}" frameborder="0" allowfullscreen=""></iframe>
                      </div>
                  </div><!-- /.tab-pane -->
                </div><!-- /.tab-content -->
              </div><!-- nav-tabs-custom -->
            </div>
        </c:forEach>

	
	</section>
</div>