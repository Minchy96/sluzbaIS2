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
	<c:set var="today" value="<%=new Date()%>" />
	<a href="/SluzbaWEB/stud/getPredmeti" >Klikni me</a>
	
	<form action="/SluzbaWEB/stud/savePrijava" modelAttribute="prijava"  method="post">
		Datum prijave: <input type="date" name="datumprijave" path="datumprijave" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${today }"/>'/> </br>
		Ispitni rok:
		<select name="ispitnirok" path="ispitnirok">
				<option value="Januarsko-februarski">Januarsko-februarski</option>
				<option value="Aprilski">Aprilski</option>
				<option value="Junski">Junski</option>
				<option value="Julski">Julski</option>
				<option value="Septembarski">Seprembarski</option>
				<option value="Oktobarski">Oktobarski</option>
		</select> <br/>
		Koji put izlazite:
		<select name="godinastudija" path="godinastudija">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
		</select> <br/>
		Izaberite predmete za prijavu:
		<%-- <table border="1">
			<th> </th> <th>Id</th> <th>Naslov</th> <th>Tip</th> <th>Semestar</th>
			<c:forEach  var="p" items="${predmeti }">
				<tr> <input type="checkbox" value="${p} name="predmet" path="predmet"/> </tr>
				<tr> ${p.predmetid }</tr>
				<tr> ${p.naziv }</tr>
				<tr> ${p.tip } </tr>
				<tr> ${p.semestar }</tr>
			</c:forEach>
		</table> </br> --%>
		
		<select name="predmet" >
	  				<c:forEach var="p" items="${predmeti }">
	  					<option value="${p.predmetid }">${p.naziv}</option>
	  				</c:forEach>
	  	</select>
		
		<input type="submit" value="Sacuvaj prijavu">
		
	</form>

</body>
</html>