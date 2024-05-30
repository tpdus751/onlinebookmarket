package bookmarket2;


import java.io.IOException;

import bookmarket2.controller.BookMarket2Controller;
import bookmarket2.model.Admin;
import bookmarket2.model.BookStorage2;
import bookmarket2.model.Cart2;
import bookmarket2.view.ConsoleView2;

public class BookMarket2 {

	
	
	public static void main(String[] args) throws IOException {
		
		
		// 모델 생성
		BookStorage2 bookstorage = new BookStorage2();
		Cart2 cart = new Cart2();

		
		// 뷰 생성
		ConsoleView2 view = new ConsoleView2();
		
		Admin admin = new Admin(null, null);
		
		// 컨트롤러 생성
		BookMarket2Controller controller = new BookMarket2Controller(bookstorage, cart, view, admin);
		
		
		
		
		
		
		controller.start();
		

	}

	private static void start() {
		
		
	}

}
