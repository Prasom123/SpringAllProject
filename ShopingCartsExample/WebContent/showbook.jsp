<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light "
		style="background-color: #00303f8f !important;">
		<a class="navbar-brand" href="#"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a
					class="nav-item nav-link  text-white active" href="Home">Home </a>
				</li>
				<li class="nav-item"><a
					class="nav-item nav-link  text-white active" href="Books">Books
				</a></li>

			</ul>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-item nav-link text-white"
					href="Logout">Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container mt-5">
		<div class="col-md-12">
			<div class="card" style="background-color: #FA3200;">
				<div class="card-body">
					<div class="col-md-12 text-white text-center">LWL Books Store
					</div>
				</div>
			</div>
		</div>
		<div class="row mx-0">
			<c:forEach items="${books}" var="book">
				<div class="col-md-3">
					<div class="card m-2" >
						<img class="card-img-top px-3" src="${book.imageLink}" alt="Card image cap"  height="225px">
						<div class="card-body">
							<p class="card-text">Author : ${book.author}</p>
							<p class="card-text">Title : ${book.title}</p>
							<p class="card-text">Language : ${book.language}</p>
							<div>
							<button class="btn btn-primary">Add to Cart</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>