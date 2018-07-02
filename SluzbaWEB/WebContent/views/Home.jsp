<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div>

<form action="/SluzbaWEB/home/validacija" method="post">
	Korisnicko ime / broj indeksa:
	<br/>
	<input type="text" name="korisnickoIme" path="korisnickoIme">
	<br/>
	Sifra:
	<br/>
	<input type="text" name="sifra" path="sifra">
	</br>
	<input type="submit" value="Login">
	<br/>
	<a href="/SluzbaWEB/views/AdminLogin.jsp">Admin</a><br/>
	
</form>

</div>

</body>
</html>