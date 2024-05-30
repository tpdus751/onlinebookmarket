package bookmarket2.model;

public class CartItem2 {
	
	Book2 book;
	
	private int quantity;
	
	public CartItem2(Book2 book) {
		this.book = book;
		this.quantity = 1;
	}
	
	@Override
	public String toString() {
		return book.getBookId() + ", " + book.getTitle() + ", " + quantity + "권, " + getPrice() + "원";
	}

	public int getPrice() {
		return quantity * book.getPrice();
	}

	public Book2 getBook() {
		return book;
	}

	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	public int getBookId2() {
		return book.getBookId();
	}
}
