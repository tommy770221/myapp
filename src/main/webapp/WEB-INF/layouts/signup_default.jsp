<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
	<head>
		<!-- Force latest IE rendering engine or ChromeFrame if installed -->
		<!--[if IE]>
				<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<![endif]-->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

		<title>Jrsys</title>

		<!-- CSS -->
		<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/styles/font-awesome.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/styles/carousel.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/styles/style.css" rel="stylesheet">

		<!-- JS -->
		<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"><!-- need for FF3 --></script>
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"><!-- need for FF3 --></script>
		<!--[if lt IE 9]>
                        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
                 <![endif]-->
	</head>	
	<body class="register-page">
		<tiles:insertAttribute name="body"/>
	</body>
</html>
