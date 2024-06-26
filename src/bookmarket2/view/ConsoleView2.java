package bookmarket2.view;

import java.util.Scanner;

import bookmarket2.model.BookStorage2;
import bookmarket2.model.Cart2;
import bookmarket2.model.Customer;

public class ConsoleView2 {

	public void displayWelcome() {
		String welcome = ("*****************************************\n"
                + "*    Welcome to seyeon Book Market      *\n"
                + "*****************************************");
      System.out.println(welcome);
	
		
	}

	public int selectMenuNo(String[] menuList) {
		Scanner input = new Scanner(System.in);
		displayMenu(menuList);
		
		int menu;
		do {
			System.out.print(">> 메뉴 입력 : ");
			menu = inputNumberWithVaildation();
			if (menu < 0  || menu >= menuList.length) {
				System.out.println("0 부터 " + (menuList.length-1) + " 까지의 숫자를 입력하세요.");
			}
			
		} while (menu < 0 || menu >= menuList.length);
		
		return menu;
		
	}
	
	private int inputNumberWithVaildation() {
		Scanner input = new Scanner(System.in);
		try {
			int number = input.nextInt();
			return number;
		} catch (Exception e) {
			System.out.println(">> 숫자를 입력하세요 : ");
			return inputNumberWithVaildation();
		}
	}
	
	private void displayMenu(String[] menuList) {
		System.out.println("=========================================");
		for (int i = 0; i < menuList.length; i++) {
			System.out.println(menuList[i]);
		}
		System.out.println("=========================================");
	}

	public void displayBookInfo(BookStorage2 c_bookStorage) {
		for (int i = 0; i < c_bookStorage.getNumBooks(); i++) {
			String bookInfo = c_bookStorage.getBookInfo(i);
			System.out.println(bookInfo);
		}
		
	}

	public void displayCart(Cart2 v_cart) {
		if (v_cart.isEmpty() == true) {
			System.out.println("장바구니가 비어 있습니다.");
		} else {
			for (int i = 0; i < v_cart.getNumItems(); i++) {
				System.out.println(v_cart.getCartInfo(i));
			}
		}
		
		
	}

	public int selectBookId(BookStorage2 bookStorage) {
		Scanner input = new Scanner(System.in);
		int bookId;
		boolean result;
		do {
			System.out.print(">> 도서의 ID를 입력하세요. : ");
			bookId = inputNumberWithVaildation();
			result = bookStorage.isValidBook(bookId);
			if (!result) {
				System.out.println("잘못되거나 없는 도서의 ID 입니다.");
			}
		} while (!result);
		
		return bookId;
		
	}

	public void showMessage(String string) {
		System.out.println(string);
		
	}

	public boolean askConfirm(String message, String yes) {
		System.out.print(message);
		Scanner input = new Scanner(System.in);
		if (input.nextLine().equals(yes)) {
			return true;
		}
		return false;
		
	}

	public int askDeleteBookId(Cart2 v_cart) { // 0506 수정
		Scanner input = new Scanner(System.in);
		int bookId;
		boolean result;
		do {
			System.out.print(">> 장바구니에서 삭제할 책의 Id를 입력하세요 : ");
			bookId = inputNumberWithVaildation();
			result = v_cart.isVaildBook(bookId);
			if (!result) { 
				System.out.println(">> 장바구니에 존재하지 않는 책의 Id 입니다.");
			}
			
		} while (!result);
		
		return bookId;
	}

	public void inputCustomerInfo(Customer c_customer) {
		if (c_customer == null) {
            c_customer = new Customer("", "", "", ""); // 기본 생성자로 초기화
        }
		Scanner input = new Scanner(System.in);
		System.out.println("온라인 서점을 이용하시려면 이름과 전화번호를 입력하세요.");
		System.out.print(">> 이름 : ");
		c_customer.setName(input.nextLine());
		System.out.print(">> 전화번호 : ");
		c_customer.setPhone(input.nextLine());
		
	}

	public void displayDelivertInfo(Customer c_customer) {
		// TODO Auto-generated method stub
		
	}

	public void inputDelivertInfo(Customer c_customer) {
		// TODO Auto-generated method stub
		
	}

	public String inputString(String message) {
		Scanner input = new Scanner(System.in);
		System.out.print(message);
		return input.nextLine();
	}
	
	public int readNumber(String message) {
		if (message != null) {
			System.out.println(message);
		}
		Scanner input = new Scanner(System.in);
		try {
			int number = input.nextInt();
			return number;
		} catch (Exception e) {
			System.out.print("숫자를 입력하세요 :");
			return readNumber(message);
		}
	}

}
