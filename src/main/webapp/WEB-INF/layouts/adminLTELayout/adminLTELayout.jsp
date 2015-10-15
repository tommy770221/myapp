<%@ page  contentType="text/html; charset=UTF-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="dashboard_title"/></title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- self define paginatorTag.css -->
    <link href="${pageContext.request.contextPath}/css/paginatorTag.css" rel="stylesheet" type="text/css" />
    <!-- Bootstrap 3.3.4 -->
    <link href="${pageContext.request.contextPath}/AdminLTE/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />    
    <!-- FontAwesome 4.3.0 -->
    <link href="${pageContext.request.contextPath}/AdminLTE/dist/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons 2.0.0 -->
    <link href="${pageContext.request.contextPath}/AdminLTE/dist/css/ionicons.min.css" rel="stylesheet" type="text/css" />    
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/AdminLTE/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="${pageContext.request.contextPath}/AdminLTE/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
    <!-- Morris chart -->
    <link href="${pageContext.request.contextPath}/AdminLTE/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
    <!-- jvectormap -->
    <link href="${pageContext.request.contextPath}/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
    <!-- Date Picker -->
    <link href="${pageContext.request.contextPath}/AdminLTE/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
    <!-- Daterange picker -->
    <link href="${pageContext.request.contextPath}/AdminLTE/plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
    <!-- bootstrap wysihtml5 - text editor -->
    <link href="${pageContext.request.contextPath}/AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck for checkboxes and radio inputs -->
    <link href="${pageContext.request.contextPath}/AdminLTE/plugins/iCheck/all.css" rel="stylesheet" type="text/css" /> 
    <!-- Bootstrap Color Picker -->
    <link href="${pageContext.request.contextPath}/AdminLTE/plugins/colorpicker/bootstrap-colorpicker.min.css" rel="stylesheet" type="text/css" />
    <!-- Bootstrap time Picker -->
    <link href="${pageContext.request.contextPath}/AdminLTE/plugins/timepicker/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />
    <!-- Select2 -->
    <link href="${pageContext.request.contextPath}/AdminLTE/plugins/select2/select2.min.css" rel="stylesheet" type="text/css" />
     <!-- jQuery 2.1.4 -->
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- jQuery UI 1.11.2 -->
    <script src="https://code.jquery.com/ui/1.11.2/jquery-ui.min.js" type="text/javascript"></script>

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
  </div>
  
  
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
      $.widget.bridge('uibutton', $.ui.button);
    </script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="${pageContext.request.contextPath}/AdminLTE/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>    
<!--     Morris.js charts -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/morris/morris.min.js" type="text/javascript"></script>
    <!-- Sparkline -->
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
    <!-- jvectormap -->
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
    <!-- jQuery Knob Chart -->
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/knob/jquery.knob.js" type="text/javascript"></script>
<!--     daterangepicker -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!--     datepicker -->
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
    <!-- Bootstrap WYSIHTML5 -->
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
    <!-- Slimscroll -->
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <!-- FastClick -->
    <script src='${pageContext.request.contextPath}/AdminLTE/plugins/fastclick/fastclick.min.js'></script>
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/iCheck/icheck.js" type="text/javascript"></script>
     <!-- InputMask -->
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/input-mask/jquery.inputmask.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/input-mask/jquery.inputmask.date.extensions.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/AdminLTE/plugins/input-mask/jquery.inputmask.extensions.js" type="text/javascript"></script>
   <script>
  var AdminLTEOptions = {
    //Enable sidebar expand on hover effect for sidebar mini
    //This option is forced to true if both the fixed layout and sidebar mini
    //are used together
    sidebarExpandOnHover: true,
    //BoxRefresh Plugin
    enableBoxRefresh: true,
    //Bootstrap.js tooltip
    enableBSToppltip: true
  };
</script>
   
    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath}/AdminLTE/dist/js/app.min.js" type="text/javascript"></script>    
    
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="${pageContext.request.contextPath}/AdminLTE/dist/js/pages/dashboard.js" type="text/javascript"></script>    
    
    <!-- AdminLTE for demo purposes -->
    <script src="${pageContext.request.contextPath}/AdminLTE/dist/js/demo.js" type="text/javascript"></script>
</body>
</html>