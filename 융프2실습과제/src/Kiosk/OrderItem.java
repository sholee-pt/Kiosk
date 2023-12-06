package Kiosk;

//OrderItem 클래스: 주문 항목의 이름, 수량, 가격을 관리함
public class OrderItem {
	private String name;
	private int quantity;
 	private int price;

 	// 생성자: 주문 항목의 이름과 가격을 설정함
 	public OrderItem(String name, int price) {
 		this.name = name;
 		this.price = price;
 		this.quantity = 0;
 	}

 	// 주문 항목의 수량을 조정하는 메서드
 	public void adjustQuantity(int amount) {
 		this.quantity += amount;
 		if (this.quantity < 0) {
 			this.quantity = 0;
 		}
 	}

 	// 총 가격을 계산하여 반환함
 	public int getTotalPrice() {
 		return this.quantity * this.price;
 	}

 	// 주문 항목의 정보를 문자열로 반환함
 	public String toString() {
 		return name + " x " + quantity + "개 : " + getTotalPrice() + "원";
 	}

 	// 이름을 반환함
 	public String getName() {
 		return name;
 	}

 	// 수량을 반환함
 	public int getQuantity() {
 		return quantity;
 	}
}