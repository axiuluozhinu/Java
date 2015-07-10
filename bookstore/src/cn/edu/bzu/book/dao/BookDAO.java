package cn.edu.bzu.book.dao;

import java.util.List;

import cn.edu.bzu.book.entity.Book;

public interface BookDAO {

	//添加书籍操作
	public int addBook(Book m);
	//查询所有书籍操作
	public List getAllBook();
	//根据ISBN删除书籍
	public int delBookByIsbn(String isbn);
}
