<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--分页显示--%>
<div align="center">

</div>

<div style="width:500px;margin:20px auto;text-align: center">
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>编辑</td>
            <td>删除</td>
        </tr>
        <%--通过page.getList遍历当前页面的Category对象。
        注：page.getList会返回一个泛型是Category的集合。--%>
        <c:forEach items="${page.list}" var="c" varStatus="st">
            <tr>
                <td>${c.id}</td>
                <td>${c.name}</td>
                <td><a href="editCategory?id=${c.id}">编辑</a></td>
                <td><a href="deleteCategory?id=${c.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <div>
        <%--在分页的时候通过page.pageNum获取当前页面，page.pages获取总页面数。--%>
        <a href="?start=1">[首 页]</a>
        <a href="?start=${page.pageNum-1}">[上一页]</a>
        <a href="?start=${page.pageNum+1}">[下一页]</a>
        <a href="?start=${page.pages}">[末 页]</a>
    </div>
    <br>
    <form action="addCategory" method="post">
        name: <input name="name"> <br>
        <button type="submit">提交</button>
    </form>
</div>
</body>
</html>
