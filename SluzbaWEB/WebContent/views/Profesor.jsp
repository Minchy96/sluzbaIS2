<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/SluzbaWEB/prof/getPrijave">Klikni me</a>
<c:if test="${!empty prijave }">
<table border="1">
	<tr> <th>Id</th> <th>Broj indeksa</th> <th>Student</th> <th>Predmet</th> <th>Ocena</th> <th>Bodovi</th> <th>Status</th> <th> </th> </tr>
	<c:forEach var="p" items="${prijave}">
		<tr>
			<form action="/SluzbaWEB/prof/updatePrijava" method="post">
				<th>${p.prijavaid }</th>
				<th>${p.student.brindeksa } </th>
				<th>${p.student.ime } ${p.student.prezime } </th>
				<th>${p.predmet.naziv } </th>
				<th> <input type="text" name="ocena"> </th>
				<th> <input type="text" name="bodovi"> </th>
				<th> 
					<select name="status">
						<option value="Polozen">Polozen</option>
						<option value="Nepolozen">Nepolozen</option>
						<option value="Nije izasao">Nije izasao</option>
					</select>
				</th>
				<th> <input type="submit" value="Izmeni"> </th>
			</form>
		</tr>
	</c:forEach>
</table>
</c:if>

</body>
</html>