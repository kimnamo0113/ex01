<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<style>
	div.uploadDiv{
		overflow: hidden;
	}
	
	div.item{
		float: left;
		height: 100px;
		position: relative;
		margin: 0px 3px;
	}
	div.item .btnDel{
		position:absolute;
		top:3px;
		right:3px;
		font-weight: bold;
	}
</style>

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
					<form action="modPage" method="post" role="form" enctype="multipart/form-data">
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
							<div class="form-group uploadDiv">
								<c:forEach var="file" items="${board.files }">
									<div class="item">
										<img src="displayFile?filename=${file }">
										<button type="button" class="btnDel">X</button>
									</div>
								</c:forEach>
							</div>
								<div class="form-group">
								<label>Files</label>
								<input type="file" name="imgFiles" class="form-control" multiple="multiple">
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
	<script>
		
		$(document).on("click",".btnDel",function(){
			var src=$(this).prev().attr("src");
			src = src.slice(src.indexOf("filename=")+9);
			alert(src);
			var $hidden = $("<input>").attr("type","hidden").attr("name","files").attr("value",src);
			$(".uploadDiv").append($hidden);
			$(this).parent().remove();
		})
		
		/* $("input[type=file]").change(function(){
			
			var $img = $("<img>").attr("src","displayFile?filename="+obj);
			
		}) */
	</script>

<%@ include file="../include/footer.jsp" %>