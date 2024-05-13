package bookmarket2.model;

import java.util.ArrayList;

public class BookStorage2 {
	
	ArrayList<Book2> bookList = new ArrayList<>();
//	Book2[] bookList;
	
	// with 교수님 방법 0513
	public BookStorage2() {
		
				bookList.add(new Book2(2401, "쉽게 배우는 자바 프로그래밍 2판", "우종정", "한빛아카데미", 20000));
				bookList.add(new Book2(2402, "코딩 자율학습 HTML+CSS+자바스크립트", "김기수", "길벗", 30000));
				bookList.add(new Book2(2403, "Do It! 자료구조와 함께 배우는 알고리즘 입문 - 자바편", "보요시바타", "이지스퍼블리싱", 25000));
				bookList.add(new Book2(2404, "세연이가 만든 가상의 책", "박세연", "세연스퍼블리싱", 100000000));
		
	}
	
	// with 교수님 방법 0513
	public int getNumBooks() {
		return bookList.size();
	}

	// with 교수님 방법 0513
	public String getBookInfo(int index) {
		return bookList.get(index).toString();
	}

	public boolean isValidBook(int bookId) {
		for (Book2 book : bookList) {
			if (bookId == book.getBookId()) {
				return true;
			}
		}
		return false;
	}

	public Book2 getBookById(int bookId) {
		for (Book2 book : bookList) {
			if (book.getBookId() == bookId) {
				return book;
			}
		}
		return null;
		
	}

}
