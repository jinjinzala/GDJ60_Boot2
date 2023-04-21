<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Modern Business - Start Bootstrap Template</title>
<!-- css favicon -->
<c:import url="../temp/style.jsp"></c:import>
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="/css/styles.css" rel="stylesheet" />
<!-- css favicon end-->
</head>
</head>
<body class="d-flex flex-column h-100">
	<main class="flex-shrink-0">
		<!-- Navigation-->
		<c:import url="../temp/header.jsp"></c:import>
		<!-- Header-->

		<section class="bg-light py-5">
			<div class="container px-5 my-5">
				<div class="text-center mb-5">
					<h1 class="fw-bolder">${board}List</h1>
					<p class="lead fw-normal text-muted mb-0">게시판 리스트 페이지입니다</p>
				</div>
				<div>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>글번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성날짜</th>
								<th>조회수</th>
								<c:if test="${board eq 'qna'}">
								<!-- <th>ref</th> -->
								<th>step</th>
								<th>depth</th>
								</c:if>	
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="boardVo">
								<tr>

									<td>${boardVo.num}</td>
									<td><a href="./detail?num=${boardVo.num}">${boardVo.title}</a></td>
									<td>${boardVo.writer}</td>
									<td>${boardVo.regDate}</td>
									<td>${boardVo.hit}</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
<a href="./add">add</a>
				<!-- <nav aria-label="Page navigation example">
					<ul class="pagination">
						<li class="page-item"><a class="page-link" href="#">맨앞으로</a></li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#">맨끝으로</a></li>
					</ul>
				</nav> -->
					<div class="row">
			<nav aria-label="Page navigation example">
			  <ul class="pagination">
			  	<li class="page-item">
			      <a class="page-link" href="#" aria-label="Previous" data-board-page="1">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			  
			  
			    <li class="page-item ${pager.before?'disabled':''}">
			      <a class="page-link" href="#" aria-label="Previous" data-board-page="${pager.startNum-1}">
			        <span aria-hidden="true">&lsaquo;</span>
			      </a>
			    </li>
			    
			    <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
			    <li class="page-item"><a class="page-link" href="#" data-board-page="${i}"  >${i}</a></li>
			    </c:forEach>
			    
			    <li class="page-item ${pager.after eq false ? 'disabled':''}"><%-- ${pager.after eq false ? 'disabled':''} --%>
	
			      <a class="page-link" href="#"  aria-label="Next" data-board-page="${pager.lastNum+1}">
			      
			        <span aria-hidden="true">&rsaquo;</span>
			      </a>
			    </li>
			    
			    <li class="page-item"><%-- ${pager.after eq false ? 'disabled':''} --%>
	
			      <a class="page-link" href="#"  aria-label="Next" data-board-page="${pager.totalPage}">
			      
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			</nav>
		</div>
		<div class="row">
	<div class="col-auto">
	  <form class="row g-3" action="./list"  id="searchForm">
		<input type="hidden" name="page" value="1" id="page">
		<div class="col-auto">
		  <label for="kind" class="visually-hidden">Kind</label>
		  <select class="form-select" name="kind" id="kind" aria-label="Default select example">
			<option value="title" ${pager.kind eq 'title'? 'selected':''}>Title</option>
			<option value="contents" ${pager.kind eq 'contents'? 'selected':''}>Contents</option>
			<option value="writer" ${pager.kind eq 'writer'? 'selected':''}>Writer</option>
		  </select>
		</div>
		<div class="col-auto">
		  <label for="search" class="visually-hidden">Search</label>
		  <input type="text" class="form-control" value="${pager.search}" name="search" id="search" placeholder="검색어를 입력하세요">
		</div>
		<div class="col-auto">
		  <button type="submit" class="btn btn-warning mb-3">검색</button>
		</div>
	  </form>
	</div>
  </div>
</div>
		</section>
	</main>
	
	<!-- Footer 적용 -->
	<c:import url="../temp/footer.jsp"></c:import>
	<script src="/js/pageing.js"></script>
	<!-- Footer 끝 -->
</body>
</html>