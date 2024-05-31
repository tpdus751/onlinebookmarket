package bookmarket2.controller;

import bookmarket2.model.Admin;
import bookmarket2.model.BookStorage2;
import bookmarket2.model.Cart2;
import bookmarket2.model.Customer;
import bookmarket2.view.ConsoleView2;

public class BookMarket2Controller { // 최신 수정 : 240506
	
	BookStorage2 c_bookStorage;
	Cart2 c_cart;
	ConsoleView2 c_view;
	Customer c_customer;
	Admin c_admin; // 0530 with 교수님
	
	String[] menuList = {
			"0. 종료",
			"1. 도서 정보 보기",
			"2. 장바구니 보기",
			"3. 장바구니에 도서 추가",
			"4. 장바구니 도서 삭제",
			"5. 장바구니 도서 수량 변경",
			"6. 장바구니 비우기",
			"7. 주문",
			"8. 관리자모드"
		   };
	
	// 0530 with 교수님
	String[] adminMenuList = {
			"0. 종료",
			"1. 도서 정보 추가",
			"2. 도서 정보 삭제",
			"3. 도서 정보 보기",
			"4. 도서 파일 저장"
			
	};
	
	public BookMarket2Controller(BookStorage2 bookstorage, Cart2 cart, ConsoleView2 view, Admin admin) {
		this.c_bookStorage = bookstorage;
		this.c_cart = cart;
		this.c_view = view;
		this.c_admin = admin; // 0530 with 교수님
	}

	public void start() {
		
		c_view.displayWelcome();
		c_view.inputCustomerInfo(c_customer);
		
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
				removeBookFromCart();
				break;
			case 5:
				changeBookQuantityFromCart();
				break;
			case 6:
				resetCart();
				break;
			case 7:
				order();
				break;
			case 8:
				adminMode(); // 0530 with 교수님
				break;
			default :
				c_view.showMessage("잘못된 메뉴 번호입니다.");
			}
			
		} while (menu != 0);
		c_view.showMessage("Seyeon Book Market을 종료 합니다.");
	}
	
	// 0530 with 교수님
	private void adminMode() {
		// 관리자 인증 (id, password 확인)
		if (!authenticateAdmin()) {
			return;
		}
		else {
			c_view.showMessage("로그인 성공 !");
		}
		
		// 관리자 모드 진입 -> 도서 추가, 도서 삭제, 도서 정보 파일 저장
		int menu;
		
		do {
			menu = c_view.selectMenuNo(adminMenuList);
			
			switch (menu) {
			case 1:
				addBook2Storage();
				break;
			case 2: // 고쳐야할지 고민
				deleteBookInStorage();
				break;
			case 3: // 고쳐야할지 고민
				viewBookInfo();
				break;
			case 4:
				saveBookList2File();
				break;
			case 0:
				adminEnd();
				break;
			default :
				c_view.showMessage("잘못된 메뉴 번호입니다.");
			}
			
		} while (menu != 0);
	}
			// 관리자 모드일 때의 메뉴 출력
			// 메뉴 선택하면 해당 기능 실행
	
	
	private void deleteBookInStorage() {
		if (c_bookStorage.isEmpty()) {
			c_view.showMessage("책 창고에 책이 없습니다.");
			return;
		}
		
		
		// 책 창고 보여주기
		viewBookInfo();

		if (!c_bookStorage.isEmpty()) {
					// 도서 ID 입력 받기
			int bookId = c_view.selectBookId(c_bookStorage);
			if (c_view.askConfirm(">> 해당 도서를 삭제하려면 yes를 입력하세요 : ", "yes")) {
			// 해당 도서 ID의 cartItem 삭제
				c_bookStorage.deleteItem(bookId);
				c_view.showMessage(">> 해당 도서를 삭제했습니다.");
			}
		}
		
	}

	// 0530 교수님이랑 한건데 관리자모드 2번 고쳐야할지 고민
	private void deleteBookInCart() {
		// 장바구니 보여주기
		c_view.displayCart(c_cart);
		if (!c_cart.isEmpty()) {
			// 도서 ID 입력 받기
			int bookId = c_view.selectBookId(c_bookStorage);
			if (c_view.askConfirm(">> 해당 도서를 삭제하려면 yes를 입력하세요 : ", "yes")) {
				// 해당 도서 ID의 cartItem 삭제
				c_cart.deleteItem(bookId);
				c_view.showMessage(">> 해당 도서를 삭제했습니다.");
			}
		}
	}

	// 0530 with 교수님
	private void adminEnd() {
		c_view.showMessage("관리자 모드가 종료되었습니다.\n");
		
	}
	
	// 0530 with 교수님
	private void addBook2Storage() {
		c_view.showMessage("새로운 책을 추가합니다");
		
		// 책정보로 Book 인스턴스 만들어서 c_bookStorage에 추가
		c_bookStorage.addBook(c_view.inputString("책 제목 : "),
				c_view.inputString("저자 : "), c_view.inputString("출판사 : "),
				c_view.readNumber("가격 : "));
	}
	
	// 0530 with 교수님
	private void saveBookList2File() {
		if (c_bookStorage.isSaved()) {
			c_view.showMessage("책 정보가 저장된 내용과 같습니다.");
		} else {
			c_bookStorage.saveBookList2File();
			c_view.showMessage("책 정보를 저장하였습니다.");
		}
		
	}

	// 0530 with 교수님
	private boolean authenticateAdmin() {
		// 관리자 인증 (id, password 확인)
		c_view.showMessage("관리자 모드 진입을 위한 관리자 인증");
		String id = c_view.inputString("관리자 ID : ");
		String password = c_view.inputString("관리자 password : ");
		return c_admin.login(id,  password);
		
	}

	private void changeBookQuantityFromCart() {
		if (c_cart.isEmpty()) {
			
		}
		
	}

	private void removeBookFromCart() {
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

	private void order() {
		//배송 정보 추가
		addDeliveryInfo();
		// 구매 정보 보기 : 장바구니 내역, 배송 정보
		displayOrderInfo();
		// 진짜 주문할거니?
		if (c_view.askConfirm("진짜 주문하려면 yes를 입력하세요", "yes")) {
			// 장바구니 비우기
			c_cart.resetCart();
		}
	}

	private void displayOrderInfo() {
		c_view.displayCart(c_cart);
		c_view.displayDelivertInfo(c_customer);
		
	}

	private void addDeliveryInfo() {
		c_view.inputDelivertInfo(c_customer);
		
	}

	private void viewCart() { // 0506 수정 deleteItem(bookId)
		c_view.showMessage("\n");
		c_view.showMessage("-----------------장바구니------------------");
		c_view.displayCart(c_cart);
		c_view.showMessage("-----------------------------------------");
		c_view.showMessage("\n");
		
	}

	private void addBookToCart() {
		c_view.displayBookInfo(c_bookStorage);
		int bookId = c_view.selectBookId(c_bookStorage);
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
