<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

 

	<section class="content">
	
	
		<div class="row">
			<div class="col-md-12">
				<div class="box box-primary">
					<!-- box header -->
					<div class="box-header">
						<h3 class="box-title">
							REGISTER BOARD
						</h3>
					</div>
					<form action="register" method="post" role="form" enctype="multipart/form-data">
						<input type="hidden" name="page" value="${cri.page }">
						<input type="hidden" name="searchType" value="${cri.searchType }">
						<input type="hidden" name="keyword" value="${cri.keyword }">
						<!-- box-body -->
						<div class="box-body">
							<div class="form-group">
								<label>Title</label>
								<input type="text" name="title" class="form-control" placeholder="Enter Title">
							</div>
							<div class="form-group">
								<label>Content</label>
								<textarea rows="10" cols="" name="content" class="form-control" placeholder="Enter Content"></textarea>
							</div>
							<div class="form-group">
								<label>Writer</label>
								<input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${Auth.userid }" readonly="readonly">
							</div>
							
							<div class="form-group">
								<label>Files</label>
								<input type="file" name="imgFiles" class="form-control" multiple="multiple">
							</div>
						</div>
						<!-- box-footer -->
						<div class="box-footer">
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
<%@ include file="../include/footer.jsp" %>