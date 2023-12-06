package DGU;

import javax.swing.*;
import java.awt.*;

// GirdLayout으로 지정
public class Grade_2019111679_008 extends JFrame {
	public Grade_2019111679_008() {
		setTitle("Text field"); // 프레임 제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 설정
		
		FlowLayout flow = new FlowLayout(); // FlowLayout 객체 생성
		Container c = getContentPane(); // ContentPane 얻기
		c.setLayout(flow); // FlowLayout으로 설정
		
		JPanel gridPanel = new JPanel(); // 패널 생성
		GridLayout grid = new GridLayout(6, 2); // 6행 2열의 GridLayout 생성
		grid.setVgap(5); // 수직 갭 설정
		gridPanel.setLayout(grid); // 패널의 레이아웃을 GridLayout으로 설정
		gridPanel.setPreferredSize(new Dimension(280, 170)); // 패널의 크기 설정
		
		// 레이블과 텍스트 필드 추가
		gridPanel.add(new JLabel(" 학교"));
		gridPanel.add(new JTextField(""));
		gridPanel.add(new JLabel(" 학과"));
		gridPanel.add(new JTextField(""));
		gridPanel.add(new JLabel(" 학번"));
		gridPanel.add(new JTextField(""));
		gridPanel.add(new JLabel(" 이름"));
		gridPanel.add(new JTextField(""));
		gridPanel.add(new JLabel(" 과목"));
		gridPanel.add(new JTextField(""));
		gridPanel.add(new JLabel(" 메일"));
		gridPanel.add(new JTextField(""));
		
		JTextArea textArea = new JTextArea(4, 25); // 텍스트 크기(줄, 글자 수), 4행 25열의 텍스트 영역 생성
		textArea.setLineWrap(true); // 텍스트가 영역을 벗어나면 자동으로 줄을 바꿈
		JScrollPane scrollPane = new JScrollPane(textArea); // 스크롤 가능한 텍스트 영역 생성
		
		c.add(gridPanel); // ContentPane에 패널 추가
		c.add(scrollPane); // ContentPane에 스크롤 가능한 텍스트 영역 추가
		
		setSize(300, 300); // 프레임 크기 설정
		setVisible(true); // 프레임 보이기
	}

	public static void main(String[] args) {
		new Grade_2019111679_008(); // 프레임 생성
	}

}
