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

	<a href="/SluzbaWEB/admin/getDepartmani">Unos profesora</a>
	<a href="/SluzbaWEB/admin/getProfesori">Unos predmeta</a>
	<a href="/SluzbaWEB/admin/getDepartmani">Unos studenta</a>
	

		<form action="/SluzbaWEB/admin/saveProfesor" method="post" modelAttribute="profesor" >
			Ime: <input type="text" name="ime" path="ime"/> <br/>
			Prezime: <input type="text" name="prezime" path="prezime"/> <br/>
			Datum rodjenja: <input type="date" name="datumrodjenja" path="datumrodjenja" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${today }"/>'/> <br/>
			Adresa: <input type="text" name="adresa" path="adresa"/> <br/>
			Zvanje: <input type="text" name="zvanje" path="zvanje"/> <br/>
			Korisnicko ime: <input type="text" name="korisnickoime" path="korisnickoime"/> <br/>
			Pristupna lozinka: <input type="text" name="lozinka" path="lozinka"/> <br/>
			
			<select name="departman" path="departman">
	  				<c:forEach var="k" items="${departmani }">
	  					<option value="${k.departmanid}">${k.naziv}</option>
	  				</c:forEach>
	  		</select>
			
			<input type="submit" value="Sacuvaj"/>
		</form>

	

		<form action="/SluzbaWEB/admin/savePredmet" method="post" modelAttribute="predmet">
			Naziv predmeta: <input type="text" name="naziv" path="naziv"/> <br/>
			Tip predmeta: <input type="text" name="tip" path="tip"/> <br/>
			Esbp: <input type="text" name="esbp" path="esbp"/> <br/>
			Godina sluzanja: <input type="text" name="godinaslusanja" path="godinaslusanja"/> <br/>
			
			<select name="profesor" path="profesor">
	  				<c:forEach var="p" items="${profesori }">
	  					<option value="${p.profesorid}">${p.ime} ${p.prezime}</option>
	  				</c:forEach>
	  		</select>
			
			<input type="submit" value="Sacuvaj"/>
		</form>  


		<form action="/SluzbaWEB/admin/saveStudent" method="post" modelAttribute="student">
			Ime: <input type="text" name="ime" path="ime"/> <br/>
			Prezime: <input type="text" name="prezime" path="prezime"/> <br/>
			Datum rodjenja: <input type="date" name="datumrodjenja" path="datumrodjenja" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${today }"/>'/> <br/>
			Adresa: <input type="text" name="adresa" path="adresa"/> <br/>
			Godina studija:
			<select name="godinastudija" path="godinastudija">
				<option value="1">Prva</option>
				<option value="2">Druga</option>
				<option value="3">Treca</option>
				<option value="4">Cetvrta</option>
			</select> <br/>
			Jedinstveni broj indeksa: <input type="text" name="brindeksa" path="brindeksa"/> <br/>
			Pristupna lozinka: <input type="text" name="lozinka" path="lozinka"/> <br/>
			Izaberite departman:
			<select name="departman" path="departman">
	  				<c:forEach var="k" items="${departmani }">
	  					<option value="${k.departmanid}">${k.naziv}</option>
	  				</c:forEach>
	  		</select> <br/>
			
			<input type="submit" value="Sacuvaj"/>
		</form>

	
</body>
</html>