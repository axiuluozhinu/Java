package cn.edu.bzu.book.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.bzu.book.dao.BookDAO;
import cn.edu.bzu.book.dao.impl.BookDAOImpl;
import cn.edu.bzu.book.entity.Book;

public class BookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//接收addBook.jsp和delBook.jsp请求，调用BookDAOImpl中方法实现不同操作和跳转
	
		String op=request.getParameter("op");
		if(op.equals("add")){
			String isbn=request.getParameter("isbn");
			String bookName=request.getParameter("bookName");
			String author=request.getParameter("author");
			String publisher=request.getParameter("publisher");
			double price=Double.parseDouble(request.getParameter("price"));
			
			Book b=new Book(isbn, bookName, author, publisher, price);
			
			BookDAO bd=new BookDAOImpl();
			if(bd.addBook(b)>0){
				response.sendRedirect("bookList.jsp");
				
			}else{
				response.sendRedirect("addBook.jsp");
			}
		}else{
			String isbn=request.getParameter("isbn");
			BookDAO bd=new BookDAOImpl();
			if(bd.delBookByIsbn(isbn)>0){
				response.sendRedirect("bookList.jsp");
				
			}else{
				response.sendRedirect("error.jsp");
			}
		}

		
		
	}

}
