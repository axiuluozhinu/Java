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
<!-- ��jsp��׼������EL��JSTLʵ����ű����鼮��Ϣ�б��� -->
<table border="1">
<tr>
<td>ISBN</td>
<td>����</td>
<td>����</td>
<td>������</td>
<td>�۸�</td>
<td>����</td>
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
<td><a href="bookServlet?op=del&isbn=<%=b.getIsbn() %>">ɾ��</a></td>
</tr>
<%
}

 %>
 <tr>
 
 <td colspan="6"><a href="addBook.jsp">���</a></td>
 </tr>
 </table>
</center>
</body>
</html>