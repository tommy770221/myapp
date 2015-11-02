<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
      <!-- Control Sidebar -->      
      <aside class="control-sidebar control-sidebar-dark">                
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
          <li><a href="#control-sidebar-home-tab" data-toggle="tab" onclick="onlineUsers();"><i class="fa  fa-users"></i></a></li>
          
          <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
          <!-- Home tab content -->
          <div class="tab-pane" id="control-sidebar-home-tab">
			


          </div><!-- /.tab-pane -->
                 
        </div>
      </aside><!-- /.control-sidebar -->
      
<script type="text/javascript">
function onlineUsers(){
	var userName=" ${sessionScope.userName} ";
	$.ajax({
			url : "userOnline",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8;',
			type : "POST",
			dataType : 'text',
			success : function(msg) {
			    $("#control-sidebar-home-tab .user-panel").remove();
				var jsonObject=$.parseJSON(msg);
				console.log(msg);
				console.log(jsonObject);
				for(var i=0;i<jsonObject.length;i++){
				$("#control-sidebar-home-tab").append("<div class=\"user-panel\"><div class=\"pull-left image\"><img src=\"${pageContext.request.contextPath}/AdminLTE/dist/img/user2-160x160.jpg\" class=\"img-circle\" alt=\"User Image\" /></div>"+
						"<div class=\"pull-left info\">"+
				"<a href=\"#\"a href=\"#\"><p>"+userName+"</p></a>"+
				"<a href=\"#\"><i class=\"fa fa-circle text-success\" style=\"color: #18EC1B;\"></i> Online</a>"+
			    "</div>"+
		        "</div>");
				}
				
			},

			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
			}
		});
	}
</script>