package bookmarket2.model;

public class Book2 {
	private int bookId;
	private String title;
	private String author;
	private String publisher;
	private int price;
	
	public Book2(int bookId, String title, String author, String publisher, int price) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return bookId + ", " + title + ", " + author + ", " + publisher + ", " + price + "원";
	}

	public int getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public int getPrice() {
		return price;
	}

}
