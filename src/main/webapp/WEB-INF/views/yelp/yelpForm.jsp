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
					<h3 class="box-title">Yelp Query</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form action="${pageContext.request.contextPath}/yelpAction" method="post" role="form">
					<div class="box-body">
						<div class="form-group">
							<label for="yelpAddress">Place</label> <input
								name="yelpAddress" type="text" class="form-control"
								id="yelpAddress" placeholder="Enter yelpAddress" />
						</div>
						<div class="form-group">
							<label for="yelpSubject">Subject</label> <input
								name="yelpSubject" type="text" class="form-control"
								id="yelpSubject" placeholder="Enter yelpSubject" />
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


	</section>
</div>
<script>

</script>