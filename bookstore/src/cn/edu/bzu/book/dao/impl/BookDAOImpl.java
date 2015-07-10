package cn.edu.bzu.book.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.bzu.book.dao.BookDAO;
import cn.edu.bzu.book.entity.Book;
import cn.edu.bzu.book.util.ConnectionManager;

public class BookDAOImpl extends ConnectionManager implements BookDAO {

	//���÷��������壬ʵ������鼮����
	public int addBook(Book b) {
		int n=0;
		conn=getConnection();
		String sql="insert into tb_book values(?,?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getIsbn());
			pstmt.setString(2, b.getBookName());
			pstmt.setString(3,b.getAuthor());
			pstmt.setString(4,b.getPublisher());
			pstmt.setDouble(5,b.getPrice());
			n=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return n;
	}
	//���÷��������壬ʵ�ֲ�ѯ�鼮����
	public List<Book> getAllBook() {
		List<Book> allBook=new ArrayList<Book>();
		conn=getConnection();
		String sql="select * from tb_book";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				String isbn=rs.getString("isbn");
				String bookName=rs.getString("bookName");
				String author=rs.getString("author");
				String publisher=rs.getString("publisher");
				double  price=rs.getDouble("price");
				Book b=new Book(isbn, bookName, author, publisher, price);
				allBook.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return allBook;
	}
	//���÷�����ʵ�ָ���isbnɾ��ָ���鼮
	public int delBookByIsbn(String isbn) {
		int n=0;
		conn=getConnection();
		String sql="delete from tb_book where isbn=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			
			n=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return n;
	}

}
