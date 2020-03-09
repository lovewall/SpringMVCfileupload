<%--
  Created by IntelliJ IDEA.
  User: lovewall
  Date: 2020/3/8
  Time: 10:42 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="user/fileupload1" enctype="multipart/form-data" method="post">
    <input type="file" name="upload"/><br/>
    <input type="submit" value="上传"><br/>
</form>
</body>
</html>
