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
					<form action="register" id="f1" method="post" role="form">
						<!-- box-body -->
						<input type="hidden" name="bno" value="${board.bno }">
						
						
						<div class="box-body">
							
							<div class="form-group">
								<label>Title</label>
								<input type="text" name="title" value="${board.title} " class="form-control" placeholder="Enter Title" disabled>
							</div>
							<div class="form-group">
								<label>Content</label>
								<textarea rows="10" cols="" name="content" class="form-control" placeholder="Enter Content" disabled>${board.content}</textarea>
							</div>
							<div class="form-group">
								<label>Writer</label>
								<input type="text" name="writer" value="${board.writer} " class="form-control" placeholder="Enter Writer" disabled>
							</div>
						</div>
						<!-- box-footer -->
						<div class="box-footer">
							<button type="button" class="btn btn-warning">MODIFY</button>
							<button type="button" class="btn btn-danger">REMOVE</button>
							<button type="button" class="btn btn-info">GO LIST</button>
						</div>
					</form>
					<!-- box-footer -->
					<div class="box-footer">
					</div>
				</div>
			</div>
		</div>
	</section>
	<script>
	
	
		$(".box-footer button").eq(0).click(function(){
			var check=confirm("수정하시겠습니까?");
			if(check==true){
				location.href="mod?bno=${board.bno}";
			}
		})
		$(".box-footer button").eq(1).click(function(){
			var check=confirm("삭제하시겠습니까?");
			if(check==true){
				$("#f1").attr("action","remove");
				$("#f1").attr("method","post");
				$("#f1").submit();
				/* location.href="remove?bno=${board.bno}"; */
			}
		})
		$(".box-footer button").eq(2).click(function(){
			location.href="listAll";
		})
	
	</script>
<%@ include file="../include/footer.jsp" %>