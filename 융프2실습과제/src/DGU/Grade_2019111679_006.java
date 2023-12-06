package DGU;

import java.util.Scanner;
import javax.swing.*;

public class Grade_2019111679_006 extends JFrame {
	public Grade_2019111679_006() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("만들고자 하는 프레임의 가로 해상도를 입력하세요: ");
		int width = sc.nextInt();
		System.out.print("만들고자 하는 프레임의 세로 해상도를 입력하세요: ");
		int length = sc.nextInt();
		
		setTitle(width + "x" + length + " 스윙 프레임 만들기");
		setSize(width,length); // 프레임 크기 width x length
		setVisible(true); // 프레임 출력
		
		sc.close();
	}

	public static void main(String[] args) {
		new Grade_2019111679_006();
	}

}
