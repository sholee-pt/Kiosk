package DGU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Grade_2019111679_023 extends JFrame {
	private JTextField tf = new JTextField(10); // 사용자 입력을 받는 텍스트 필드
	private Vector<String> v = new Vector<String>(); // 리스트에 표시될 아이템을 저장하는 벡터
	private JList<String> nameList = new JList<String>(v); // 아이템을 표시하는 리스트
	
	public Grade_2019111679_023() {
		setTitle("list change"); // 프레임의 타이틀 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 누를 시 프로그램 종료
		Container c = getContentPane();
		c.setLayout(new FlowLayout()); // 레이아웃 설정
		
		c.add(new JLabel("이름 입력 후 <Enter> 키")); // 레이블 추가
		c.add(tf); // 텍스트 필드 추가
		
		// 초기 아이템 설정
		v.add("사과");
		v.add("배");
		v.add("체리");
		nameList.setVisibleRowCount(5); // 리스트의 보이는 행 수 설정
		nameList.setFixedCellWidth(100); // 리스트의 셀 너비 설정
		c.add(new JScrollPane(nameList)); // 리스트에 스크롤 패인 추가
		
		JButton delete = new JButton("삭제"); // 삭제 버튼 추가
		c.add(delete); // 삭제 버튼을 컨테이너에 추가

		setSize(300,300); // 프레임의 크기 설정
		setVisible(true); // 프레임을 보이게 설정
		
		// 텍스트 필드에 액션 리스너 추가
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource();
				v.add(t.getText()); // 텍스트 필드의 텍스트를 벡터에 추가
				t.setText(""); // 텍스트 필드 초기화
				nameList.setListData(v); // 리스트 데이터 업데이트
			}
		});

		// 삭제 버튼에 액션 리스너 추가
		delete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (nameList.getSelectedIndex() >= 0) { // 선택된 항목이 있는지 확인
		            v.remove(nameList.getSelectedIndex()); // 선택된 항목을 벡터에서 제거
		            nameList.setListData(v); // 리스트 뷰 업데이트
		        }
		    }
		});
	}
	
	public static void main(String[] args) {
		new Grade_2019111679_023();
	}
}
