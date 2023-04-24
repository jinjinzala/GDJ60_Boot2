<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
<!-- This snippet uses Font Awesome 5 Free as a dependency. You can download it at fontawesome.io! -->
<body>
  <div class="container">
    <div class="row">
     <c:import url="../temp/header.jsp"></c:import>
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card border-0 shadow rounded-3 my-5">
          <div class="card-body p-4 p-sm-5">
            <h5 class="card-title text-center mb-5 fw-light fs-5">Sign In</h5>
            <form id="contactForm" action="./join" method="post">
              <div class="form-floating mb-3">
                <input type="text" class="form-control" id="name" name ="name"  placeholder="name">
                <label for="name">name</label>
              </div>
              <div class="form-floating mb-3">
                <input type="text" class="form-control" id="userName" name ="userName"  placeholder="userName">
                <label for="userName">userName</label>
              </div>
              <div class="form-floating mb-3">
                <input type="email" class="form-control" id="email" name ="email"  placeholder="name@example.com">
                <label for="email">email</label>
              </div>
                <div class="form-floating mb-3">
                <input type="date" class="form-control" id="birth" name ="birth"  placeholder="birth">
                <label for="birth">생일입력</label>
              </div>
              <div class="form-floating mb-3">
                <input type="password" class="form-control" id="password"  name ="password" placeholder="Password">
                <label for="password">Password</label>
              </div>
 				<div class="form-floating mb-3">
                <input type="password" class="form-control" id="passwordCheck"  name ="passwordCheck" placeholder="Password">
                <label for="passwordCheck">Password</label>
              </div>
              <div class="d-grid">
                <button class="btn btn-primary btn-login text-uppercase fw-bold" type="submit">Sign in</button>
              </div>
              <hr class="my-4">
              <div class="d-grid mb-2">
                <button class="btn btn-google btn-login text-uppercase fw-bold" type="submit">
                  <i class="fab fa-google me-2"></i> Sign in with Google
                </button>
              </div>
              <div class="d-grid">
                <button class="btn btn-facebook btn-login text-uppercase fw-bold" type="submit">
                  <i class="fab fa-facebook-f me-2"></i> Sign in with Facebook
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <c:import url="../temp/footer.jsp"></c:import>
	<script type="text/javascript" src="../js/joinFormCheck.js"></script>
</body>
	
</html>