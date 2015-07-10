<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@page import="cn.edu.bzu.book.dao.BookDAO"%>
<%@page import="cn.edu.bzu.book.dao.impl.BookDAOImpl"%>
<%@page import="cn.edu.bzu.book.entity.Book"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<LINK href="/medicines/css/style1.css" type=text/css rel=stylesheet>
<LINK href="/medicines/css/style2.css" type=text/css rel=stylesheet>
</head>
<body>
<center>
<!-- 用jsp标准动作和EL、JSTL实现零脚本的书籍信息列表功能 -->
<table border="1">
<tr>
<td>ISBN</td>
<td>书名</td>
<td>作者</td>
<td>出版社</td>
<td>价格</td>
<td>操作</td>
</tr>
<%
BookDAO bd=new BookDAOImpl();
List<Book> allBook=bd.getAllBook();
for(Book b:allBook){%>
<tr>
<td><%=b.getIsbn() %></td>
<td><%=b.getBookName() %></td>
<td><%=b.getAuthor() %></td>
<td><%=b.getPublisher()%></td>
<td><%=b.getPrice() %></td>
<td><a href="bookServlet?op=del&isbn=<%=b.getIsbn() %>">删除</a></td>
</tr>
<%
}

 %>
 <tr>
 
 <td colspan="6"><a href="addBook.jsp">添加</a></td>
 </tr>
 </table>
</center>
</body>
</html>