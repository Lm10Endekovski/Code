<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
href="/Student/css/stil.css">
</head>
<body>
<div id="addStudent">
<form method="POST" action ="/Student/Dame">
	
	
	<label for="ime" id="ime123">IME:</label>		
	<input type="text" name="imeStudent" id="addIme"  class="labeli"><br><br>
	<label for="prezime" id="prez123">PREZIME:</label>		
	<input type="text" name="prezimeStudent" id="addprezime" class="labeli"><br><br>
	<label for="model" id="indeks123">INDEKS:</label>
	<input type="text" name="indeksStudent" id="addindeks" class="labeli"><br>
	
	<input type="submit" name="addStudent" value="OK" class="OK" >
	</form>
	
	</div>
</body>
</html>
