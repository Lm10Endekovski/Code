<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
href="/Student/css/stil.css">
<script src="../js/skripta.js"></script>
</head>
<body>
<div id="subjectDiv">
<form method="POST" action ="/Student/Dame">
	
	
	<label for="izberiPredmet" class="a">IBERETE PREDMET:</label>		
	<select id="izberiPredmet" name="selectbox"> 
	<option>Vizuelno Programiranje</option>
	<option>Web Dizajn</option>
	<option>Operativni sistemi</option>
	<option>Objektno programiranje</option>
	</select><br><br>
	<label for="vnesiKrediti" class="a">KREDITI:</label>		
	<select id="vnesiKrediti" name="selectbox2"> 
	<option>4</option>
	<option>5</option>
	<option>6</option>
</select><br><br>
	<label for="addsemestar" class="a">VNESETE SEMESTAR:</label>
	<input type="text" name="vnesSemestar" id="addsemestar"><br><br>
	
	<label for="addsifra" class="a">SIFRA:</label>
	<input type="text" name="vnesSifra" id="addsifra"><br><br><br>
	
	<input type="submit" onclick="promptF()" name="predmet" value="OK"  class="OK"><br>
	<input style="display: none; visibility: hidden;" class="hInput" type="text" name="hiddenInput" id="hiddenEmail" value="">
	

	</form>
	
	</div>
</body>
</html>
