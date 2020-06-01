<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/1
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <%--<script type="text/javascript" src="../../h5/js/lib/jquery.min.js" charset="utf-8"></script>--%>
    <script type="text/javascript" src="http://how2j.cn/study/jquery.min.js" charset="utf-8"></script>
</head>
<body>
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
        <c:forEach items="${page.list}" var="c" varStatus="st">
            <tr>
                <td>${c.id}</td>
                <td>${c.name}</td>
                <td><a href="categories/${c.id}">编辑</a></td>
                <td><a class="delete" href="categories/${c.id}">删除</a></td>
            </tr>
        </c:forEach>

    </table>
    <br>
    <div>
        <a href="?start=0">[首 页]</a>
        <a href="?start=${page.pageNum-1}">[上一页]</a>
        <a href="?start=${page.pageNum+1}">[下一页]</a>
        <a href="?start=${page.pages-1}">[末 页]</a>
    </div>
    <br>
    <form action="categories" method="post">
        name: <input name="name"> <br>
        <button type="submit">提交</button>

    </form>

    <form id="formdelete" action="" method="POST">
        <input type="hidden" name="_method" value="DELETE">
    </form>
</div>

</body>
</html>

<script type="text/javascript">
    /*将post method 改变为delete*/
    $(function () {
        $(".delete").click(function () {
            var href = $(this).attr("href");
            $("#formdelete").attr("action", href).submit();
            return false;
        })
    })
</script>