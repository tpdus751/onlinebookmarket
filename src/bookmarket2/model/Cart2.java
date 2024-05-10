package bookmarket2.model;

import java.util.ArrayList;

public class Cart2 {
	
	private int numItems = 0;
	
//	private CartItem2[] itemList = new CartItem2[64];
	private ArrayList<CartItem2> itemList = new ArrayList<>();  // ---> 리스트

	public boolean isEmpty() {
		return numItems == 0;
	}

	public int getNumItems() {
		
		return numItems;
	}

	public String getCartInfo(int index) {
		return itemList.get(index).toString(); // --> 리스트 함수 get()
	}

	public void addItem(Book2 book) {
		CartItem2 item = getCartItem(book);
		if (item == null) {
			itemList.add(numItems++, new CartItem2(book)); // ---> 리스트 함수 add()
		} 
		else {
			item.addQuantity(1);
		}
		
	}

	private CartItem2 getCartItem(Book2 book) {
		for (int i = 0; i < numItems; i++) {
			if (itemList.get(i).getBook() == book) { // ---> 리스트 함수 get()
				return itemList.get(i); // ---> 리스트 함수 get()
			}
		}
		return null;
	}

	public void resetCart() {
		numItems = 0;
	    this.itemList = new ArrayList<>();
		
	}
	
	public boolean isVaildBook(int bookId) { // 0506 수정
		for (CartItem2 cartitem : itemList) {
			if (cartitem.getBookId2() == bookId) {
				return true;
			}
		}
		return false;
	}

	public void deleteItem(int bookId) { // 0506 수정
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getBookId2() == bookId) {
				itemList.remove(i);
				numItems -= 1;
			}
		}
		
	}

}
