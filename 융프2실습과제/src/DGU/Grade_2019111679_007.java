package DGU;

import javax.swing.*;
import java.awt.*;

public class Grade_2019111679_007 extends JFrame {
	public Grade_2019111679_007() {
		// JFrame 생성 및 설정
		setTitle("버튼"); // 프레임 제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 설정
		
		// ContentPane 설정
		Container contentPane = getContentPane(); // ContentPane 얻기
		contentPane.setBackground(Color.YELLOW); // 배경색 설정(노란색)
		contentPane.setLayout(new FlowLayout()); // 레이아웃 매니저 설정

		// 버튼 추가
		contentPane.add(new JButton("OK"));
		contentPane.add(new JButton("Cancel"));
		contentPane.add(new JButton("Ignore"));
		
		setSize(300, 150); // 프레임 크기 설정
		setVisible(true); // 프레임 보이기
	}

	public static void main(String[] args) {
		new Grade_2019111679_007(); // 프레임 생성
	}

}
