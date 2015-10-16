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
				<form action="${pageContext.request.contextPath}/youtubeQuery" method="post" role="form">
					<div class="box-body">
						<div class="form-group">
							<label for="searchParam">SearchParam</label> <input
								name="searchParam" type="text" class="form-control"
								id="searchParam" placeholder="Enter Key Word you want to search" />
						</div>
							<div class="form-group">
							<label>PublishBefore"(now~2009):</label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control" name="publishBefore" id="publishBefore"
									data-inputmask="'alias': 'yyyy-mm-dd'" data-mask />
							</div>
						</div>
						<div class="form-group">
							<label>PublishAfter(2009~now):</label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control" name="publishAfter" id="publishAfter"
									data-inputmask="'alias': 'yyyy-mm-dd'" data-mask />
							</div>
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="submit" class="btn btn-primary" >Submit</button>
					</div>
				</form>
				<!-- /.form group -->
			</div>
			<!-- /.box -->
		</div>
  <script type="text/javascript">
  $(function () {
  //Datemask dd/mm/yyyy
  $("#datemask").inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd", clearMaskOnLostFocus: true });
  //Money Euro
  $("[data-mask]").inputmask();
  });
  </script>

	</section>
</div>
