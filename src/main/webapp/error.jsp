<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page!!!!</title>
<style type="text/css">
    body {
      font-family: serif;
      background: skyblue;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
 h4 {
      margin-bottom: 20px;
      color: red;
      font-size: 24px;
    }

    input[type="submit"] {
      width: 100%;
      padding: 10px;
      background-color: #3498db;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      margin-top: 10px;
      font-size: 16px;
    }
  </style>

</head>
<body>
	<h4>INVALID NAME OR PASSWORD</h4><br>
	<form action="login.html">
		<input type="submit" value="LOGIN AGAIN">
	</form>
</body>
</html>