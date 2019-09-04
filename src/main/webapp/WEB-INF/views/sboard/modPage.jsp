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
							READ PAGE
						</h3>
					</div>
					<!-- box-body -->
					<form action="modPage" method="post" role="form">
						<!-- box-body -->
						<div class="box-body">
							<input type="hidden" name="bno" value="${board.bno}">
							<input type="hidden" name="page" value="${cri.page}">
							<input type="hidden" name="searchType" value="${cri.searchType }">
							<input type="hidden" name="keyword" value="${cri.keyword }">
							<div class="form-group">
								<label>Title</label>
								<input type="text" name="title" value="${board.title}" class="form-control" placeholder="Enter Title">
							</div>
							<div class="form-group">
								<label>Content</label>
								<textarea rows="10" cols="" name="content" class="form-control" placeholder="Enter Content">${board.content}</textarea>
							</div>
							<div class="form-group">
								<label>Writer</label>
								<input type="text" name="writer" value="${board.writer}" class="form-control" placeholder="Enter Writer" disabled>
							</div>
						</div>
						<!-- box-footer -->
						<div class="box-footer">
							<button type="submit" class="btn btn-warning">MODIFYy</button>
						</div>
					</form>
					<!-- box-footer -->
					<div class="box-footer">
					</div>
				</div>
			</div>
		</div>
	</section>

<%@ include file="../include/footer.jsp" %>