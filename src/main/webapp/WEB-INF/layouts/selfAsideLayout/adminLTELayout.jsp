<%@ page  contentType="text/html; charset=UTF-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="dashboard_title"/></title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="${pageContext.request.contextPath}/AdminLTE/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />    
    <!-- FontAwesome 4.3.0 -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons 2.0.0 -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />    
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/AdminLTE/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="${pageContext.request.contextPath}/AdminLTE/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
  
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="skin-blue sidebar-mini">
  <div class="wrapper">
        <tiles:insertAttribute name="header" ignore="true" />
        <tiles:insertAttribute name="sidebar" ignore="true" />
        <tiles:insertAttribute name="body" ignore="true" />
        <tiles:insertAttribute name="footer" ignore="true" />
         <div class="control-sidebar-bg"></div>
  </div>
  
   <!-- jQuery 2.1.4 -->
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="${pageContext.request.contextPath}/AdminLTE/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>    
    <!-- FastClick -->
    <script src='${pageContext.request.contextPath}/AdminLTE/plugins/fastclick/fastclick.min.js'></script>
    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath}/AdminLTE/dist/js/app.min.js" type="text/javascript"></script>    
    <!-- AdminLTE for demo purposes -->
    <script src="${pageContext.request.contextPath}/AdminLTE/dist/js/demo.js" type="text/javascript"></script>
    </body>
</html>