package cn.edu.bzu.book.dao;

import java.util.List;

import cn.edu.bzu.book.entity.Book;

public interface BookDAO {

	//����鼮����
	public int addBook(Book m);
	//��ѯ�����鼮����
	public List getAllBook();
	//����ISBNɾ���鼮
	public int delBookByIsbn(String isbn);
}
