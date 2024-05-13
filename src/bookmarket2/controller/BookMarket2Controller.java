package bookmarket2.controller;

import bookmarket2.model.BookStorage2;
import bookmarket2.model.Cart2;
import bookmarket2.view.ConsoleView2;

public class BookMarket2Controller { // 최신 수정 : 240506
	
	BookStorage2 c_bookStorage;
	Cart2 c_cart;
	ConsoleView2 c_view;
	String menuList[] = {
		      "0. 종료", 
		      "1. 도서 정보 보기", 
		      "2. 장바구니 보기", 
		      "3. 장바구니에 도서담기", 
		      "4. 장바구니 도서 삭제"
		   };
	
	public BookMarket2Controller(BookStorage2 bookstorage, Cart2 cart, ConsoleView2 view) {
		this.c_bookStorage = bookstorage;
		this.c_cart = cart;
		this.c_view = view;
	}

	public void start() {
		
		c_view.displayWelcome();
		
		int menu;
		
		do {
			menu = c_view.selectMenuNo(menuList);
			
			switch (menu) {
			case 1:
				viewBookInfo();
				break;
			case 2:
				viewCart();
				break;
			case 3:
				addBookToCart();
				break;
			case 4:
				resetCart();
				break;
			}
			
		} while (menu != 0);
		c_view.showMessage("Seyeon Book Market을 종료 합니다.");
	}

	private void viewCart() { // 0506 수정 deleteItem(bookId)
		c_view.showMessage("\n");
		c_view.showMessage("-----------------장바구니------------------");
		c_view.displayCart(c_cart);
		c_view.showMessage("-----------------------------------------");
		c_view.showMessage("\n");
		if (!c_cart.isEmpty()) {
			if (c_view.askConfirm(">> 장바구니의 책을 삭제하시겠습니까? : ", "yes")) {
				int bookId = c_view.askDeleteBookId(c_cart);
				c_cart.deleteItem(bookId);
				c_view.showMessage(">> 장바구니의 Id : " + bookId + " 책을 삭제하였습니다.");
				c_view.showMessage("\n");
				c_view.showMessage("-----------------장바구니------------------");
				c_view.displayCart(c_cart);
				c_view.showMessage("-----------------------------------------");
				c_view.showMessage("\n");
			}
		}
		
		
		
	}

	private void addBookToCart() {
		c_view.displayBookInfo(c_bookStorage);
		int bookId = c_view.selectBoodId(c_bookStorage);
		c_cart.addItem(c_bookStorage.getBookById(bookId));
		c_view.showMessage(">> 장바구니에 도서를 추가하였습니다.");
		
	}

	private void resetCart() {
		c_view.displayCart(c_cart);
		if (!c_cart.isEmpty()) {
			if (c_view.askConfirm(">> 장바구니를 비우려면 yes를 입력해주세요 : ", "yes")) {
				c_cart.resetCart();
				c_view.showMessage("장바구니를 비웠습니다.");
			}
			else {
				c_view.showMessage("장바구니 비우기가 취소 되었습니다.");
			}
		}
		
	}

	private void viewBookInfo() {
		c_view.displayBookInfo(c_bookStorage);
		
	}

}
