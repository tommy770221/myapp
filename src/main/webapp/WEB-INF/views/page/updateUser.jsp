<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="content-wrapper">
	<section class="content">
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">Quick Example</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form action="${pageContext.request.contextPath}/updateUser.action" method="post" role="form">
					<div class="box-body">
					    <div class="form-group">
							<input
								name="user.email" type="hidden" class="form-control"
								id="userEmail" placeholder="Enter userName" value="${userEmail}">
						</div>
						<div class="form-group">
							<label for="userName">User Name</label> <input
								name="user.username" type="text" class="form-control"
								id="userName" placeholder="Enter userName" value="${userFind.username}">
						</div>
						<div class="form-group">
							<label> <input type="radio" name="user.sex" class="minimal" value="male" id="userMale" />
								男
							</label> 
							<label> <input type="radio" name="user.sex" class="minimal" value="female" id="userFemale"/>
							              女
							</label> 
							<label> <input type="radio" name="user.sex" class="minimal" value="secret" id="userSecret"
								 />
							    secret
							</label>
						</div>
						<div class="form-group">
							<label>Birthday:</label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control" name="user.birthday"
									data-inputmask="'alias': 'yyyy-mm-dd'" data-mask value="${userFind.birthDateToString()}"/>
							</div>
						</div>
						<!-- /.form group -->
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="submit" class="btn btn-primary" >Submit</button>
					</div>
				</form>
			</div>
			<!-- /.box -->
		</div>


	</section>
</div>
<script>
$(function () {
	
	//Datemask dd/mm/yyyy
    $("#datemask").inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd", clearMaskOnLostFocus: true });
    //Money Euro
    $("[data-mask]").inputmask();
	
	//iCheck for checkbox and radio inputs
	$('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
		checkboxClass : 'icheckbox_minimal-blue',
		radioClass : 'iradio_minimal-blue'
	});
	//Red color scheme for iCheck
	$('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red')
			.iCheck({
				checkboxClass : 'icheckbox_minimal-red',
				radioClass : 'iradio_minimal-red'
			});
	//Flat red color scheme for iCheck
	$('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
		checkboxClass : 'icheckbox_flat-green',
		radioClass : 'iradio_flat-green'
	});
	
	var userSex='<c:out value="${userFind.sex}"/>';
	if(userSex =='male'){
		$('#userMale').iCheck('check'); 
	}else if(userSex =='female'){
		$('#userFemale').iCheck('check'); 
	}else if(userSex =='secret'){
		$('#userSecret').iCheck('check'); 
	}else{
		$('#userSecret').iCheck('check'); 
	}
	
});
</script>