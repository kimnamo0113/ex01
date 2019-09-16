<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="content">


	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<!-- box header -->
				<div class="box-header">
					<h3 class="box-title">READ PAGE</h3>
				</div>
				<!-- box-body -->
				<form action="register" id="f1" method="post" role="form">
					<!-- box-body -->
					<input type="hidden" name="bno" value="${board.bno }"> <input
						type="hidden" name="page" value="${cri.page }"> <input
						type="hidden" name="searchType" value="${cri.searchType }">
					<input type="hidden" name="keyword" value="${cri.keyword }">
					<div class="box-body">

						<div class="form-group">
							<label>Title</label> <input type="text" name="title"
								value="${board.title} " class="form-control"
								placeholder="Enter Title" disabled>
						</div>
						<div class="form-group">
							<label>Content</label>
							<textarea rows="10" cols="" name="content" class="form-control"
								placeholder="Enter Content" disabled>${board.content}</textarea>
						</div>
						<div class="form-group">
							<label>Writer</label> <input type="text" name="writer"
								value="${board.writer} " class="form-control"
								placeholder="Enter Writer" disabled>
						</div>
						<div class="form-group">
							<c:forEach var="file" items="${board.files }">
								<img src="displayFile?filename=${file }">
							</c:forEach>
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
				<div class="box-footer"></div>
			</div>
		</div>
	</div>
	<script>
		$(".box-footer button").eq(0).click(function() {
							var check = confirm("수정하시겠습니까?");
							if (check == true) {
								location.href = "modPage?bno=${board.bno}&page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword }";
							}
						})
		$(".box-footer button").eq(1).click(function() {
			var check = confirm("삭제하시겠습니까?");
			if (check == true) {
				$("#f1").attr("action", "removePage");
				$("#f1").attr("method", "post");
				$("#f1").submit();
				/* location.href="remove?bno=${board.bno}"; */
			}
		})
		$(".box-footer button").eq(2).click(function() {
							location.href = "list?page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword }";
						})
	</script>
	<div class="box box-success">
		<div class="box-header">
			<h3 class="box-title">ADD NEW REPLY</h3>
		</div>
		<div class="box-body">
			<label>Writer</label> 
			<input type="text" placeholder="user id" id="newReplyWriter" class="form-control">
			<label>Reply Text</label> 
			<input type="text" placeholder="text" id="newReplyText" class="form-control">
		</div>
		<div class="box-footer">
			<button type="button" class="btn btn-primary" id="btnReplyAdd">ADD REPLY</button>
		</div>
		
		
		
	</div>
	<ul class="timeline">
		<li class="time-label" id="btnReplyList">
			<span class="bg-green">Replies List : [<span id="replycnt">${board.replycnt }</span>]</span>
		</li>
	</ul>
	<div class="text-center">
		<ul id="pagination" class="pagination pagination-sm no-margin">
		</ul>
	</div>
	
	<div id="modifyModal" class="modal modal-primary fade" role="dialog">
			<div class='modal-dialog'>
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title"></h4><!-- rno번호 넣음 -->
					</div>
					<div class="modal-body" data-rno=""><!-- rno번호 넣음 -->
						<p><input type="text" id="replytext" class="form-control"></p><!-- input에 text넣음 -->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" id="btnReplySaveMod">Modify</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>						
					</div>
				</div>
			</div>
		</div>
	
	
	<script id="template" type="text/x-handlebars-template">
		{{#each.}}
		
		<li class="replyLi" data-rno="{{rno}}">
		<i class="fa fa-comments bg-blue"></i>
		<div class="timeline-item">
			<span class="time">
				<i class="fa fa-clock-o"></i>{{tempdate regdate}}
			</span>
			<h3 class="timeline-header">
				<strong>{{rno}}</strong> - {{replyer}}
			</h3>
			<div class="timeline-body">{{replytext}}</div>
			<div class="timeline-footer">
				<a class="btn btn-primary btn-xs btnReplyModify" data-rno="{{rno}}" data-toggle="modal" data-target="#modifyModal">Mdify</a>
				<a class="btn btn-danger btn-xs btnReplyDelete" data-rno="{{rno}}">Delete</a>
			</div>
		</div>
		</li>
		{{/each}}
	</script>

	
	<script type="text/javascript">
		$("#btnReplyList").click(function() {
			getListAll(1)
		})
		var bno = ${board.bno};
		var currentPage = 1;
		
		Handlebars.registerHelper("tempdate",function(time){
			var date = new Date(time);
  			var year = date.getFullYear();
			var month = date.getMonth()+1;
			var day = date.getDate();
			return year + "-" + month + "-" + day; 
		})

		$(document).on("click",".btnReplyModify",function(){
			var rno = $(this).attr("data-rno");
			
			$(".modal-title").text(rno);
			$(".modal-body").attr("data-rno",rno);
			var content = $(this).parents(".replyLi").find(".timeline-body").text();
			$("#replytext").val(content);
			
		})
		$("#btnReplySaveMod").click(function() {
			
			var replytext=$("#replytext").val();
			var rno=$(".modal-title").text();
			
			var json = {replytext:replytext};
			var data = JSON.stringify(json)
			
			$.ajax({
				url:"/ex01/replies/"+rno,
				type:"put",
				data:data,
				dataType:"text",
				headers:{
					"Content-Type":"application/json"
				},
				success:function(res){
					console.log(res);
					if(res=='success'){
						alert("수정되었습니당.");
						getListAll(currentPage);
						$("#modifyModal").modal("hide");
					}
				}
				
			})
		})
		
		
		
		$(document).on("click",".btnReplyDelete",function(){
			var rno=$(this).attr("data-rno");
			$.ajax({
				url:"/ex01/replies/"+rno,
				type:"delete",
				dataType:"text",
				success:function(res){
					console.log(res);
					if(res=='success'){
						alert("삭제되었습니다.");
						getListAll(1);
						var cnt=$("#replycnt").text();
						$("#replycnt").text(Number(cnt)-1);
					}
				}
			
			
				
			})
		})
		
		$("#btnReplyAdd").click(function() {
			
			var replyer = $("#newReplyWriter").val();
			var replytext = $("#newReplyText").val();
			
			//@RequestBody
			//var str = "{bno:bno,replyer:replyer,replytext:replytext}";
			var json = {bno:bno,replyer:replyer,replytext:replytext};
			var data = JSON.stringify(json)
			
			$.ajax({
				url:"/ex01/replies",
				type:"post",
				headers:{
					"Content-Type":"application/json"
				},
				//get방식일때 쓴거
				data:data,
				dataType:"text",
				success:function(res){
					console.log(res);
					getListAll(1);
					var cnt=$("#replycnt").text();
					$("#replycnt").text(Number(cnt)+1);
				}
				
			})
			
		})
		
		function getListAll(page){
				
			$.ajax({
				url:"${pageContext.request.contextPath}/replies/"+bno+"/"+page,
				type:"get",
				dataType:"json",
				success:function(res){
					console.log(res);
					
					$(".replyLi").remove();
					
					/* $(res.list).each(function(i,obj) {
						var $li = $("<li>").addClass("replyLi");
						var $divItem = $("<div>").addClass("item");
						var $spanRno = $("<span>").addClass("rno").text(obj.rno);
						var $spanWriter = $("<span>").addClass("writer").text(obj.replyer);
						var $spanContent = $("<span>").addClass("content").text(obj.replytext);
						var $divWrap = $("<div>").addClass("btnWrap");
						var $btnMod = $("<button>").addClass("modify").text("수정").attr("data-rno",obj.rno);
						var $btnDel = $("<button>").addClass("delete").text("삭제").attr("data-rno",obj.rno);
						
						$divWrap.append($btnMod).append($btnDel);
						$divItem.append($spanRno).append(" : ").append($spanWriter).append("<br>").append($spanContent).append($divWrap);
						$li.append($divItem);
						$("#replies").append($li);
						
					}) */
					
					var source=$("#template").html();
					var fn = Handlebars.compile(source);
					var str = fn(res.list);
					$(".timeline").append(str);
					
					printPaging(res);
				}
			})
		}

		function printPaging(res){
			$(".pagination").empty();
			for(var i=res.pageMaker.startPage; i<=res.pageMaker.endPage; i++){
				var $li = $("<li>");
				var $a = $("<a>").text(i).attr("href","#");
				
				if(res.pageMaker.cri.page == i){
					$li.addClass("active");
				}
				
				$li.append($a);
				$(".pagination").append($li);
			}
		}

		
			
		
	</script>	
</section>

<%@ include file="../include/footer.jsp"%>