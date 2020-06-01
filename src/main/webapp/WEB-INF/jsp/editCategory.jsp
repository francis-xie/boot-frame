<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="margin:0px auto; width:500px">
    <form action="updateCategory" method="post">
        name: <input name="name" value="${c.name}"> <br>
        <input name="id" type="hidden" value="${c.id}">
        <button type="submit">提交</button>
    </form>
</div>
</body>
</html>
