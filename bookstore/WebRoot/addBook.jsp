<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<LINK href="/medicines/css/style1.css" type=text/css rel=stylesheet>
<LINK href="/medicines/css/style2.css" type=text/css rel=stylesheet>
</head>
<body>
<center>
<!-- addBook.jsp -->

<form action="bookServlet?op=add" method="post">
<table border="1">
<tr>
<td>ISBN</td>
<td><input type="text" name="isbn"></td>
</tr>
<tr>
<td>������</td>
<td><input type="text" name="bookName"></td>
</tr>
<tr>
<td>���ߣ�</td>
<td><input type="text" name="author"></td>
</tr>
<tr>
<td>�����磺</td>
<td><input type="text" name="publisher"></td>
</tr>
<tr>
<td>�۸�</td>
<td><input type="text" name="price"></td>
</tr>
<tr>
<td><input type="submit" value="���"></td>
<td><input type="reset" value="ȡ��"></td>
</tr>

</table>

</form>
</center>
</body>
</html>