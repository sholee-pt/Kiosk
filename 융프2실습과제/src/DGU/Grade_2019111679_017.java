package DGU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Grade_2019111679_017 extends JFrame {
    // 과일을 위한 체크박스 배열 선언
    JCheckBox [] fruits = new JCheckBox[3];
    // 과일의 이름을 위한 문자열 배열 선언
    String [] names = {"사과","배","체리"};
    // 총 가격을 표시할 레이블 선언
    JLabel sumLabel;
    
    public Grade_2019111679_017() {
        setTitle("pricing by radiobutton"); // 창의 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 시 프로그램 종료 설정
        Container c = getContentPane(); // 컨테이너 가져오기
        c.setLayout(new FlowLayout()); // 레이아웃 설정
        // 가격 정보 레이블 추가
        c.add(new JLabel("사과: 100원, 배: 500원, 체리: 20000원"));
        
        // 아이템 리스너 인스턴스 생성
        MyItemListener listener = new MyItemListener();
        // 과일 체크박스 초기화 및 컨테이너에 추가
        for(int i=0; i<fruits.length; i++) {
            fruits[i] = new JCheckBox(names[i]); // 체크박스 생성
            fruits[i].setBorderPainted(true); // 테두리 설정
            c.add(fruits[i]); // 체크박스를 컨테이너에 추가
            fruits[i].addItemListener(listener); // 리스너 추가
        }
        // 총액 레이블 초기화 및 컨테이너에 추가
        sumLabel = new JLabel("현재 0원 입니다.");
        c.add(sumLabel);
        setSize(250,150); // 창 크기 설정
        setVisible(true); // 창 보이기 설정
    }
    
    // 체크박스의 상태 변화를 처리하는 내부 클래스
    class MyItemListener implements ItemListener {
        private int sum = 0; // 가격의 합계를 저장할 변수

        // 체크박스 상태 변화 이벤트 처리
        public void itemStateChanged(ItemEvent e) {
            // 체크박스가 선택된 경우
            if(e.getStateChange() == ItemEvent.SELECTED) {
                if(e.getItem() == fruits[0]) // 사과 선택 시
                    sum += 100;
                else if(e.getItem() == fruits[1]) // 배 선택 시
                    sum += 500;
                else // 체리 선택 시
                    sum += 20000;
            }
            // 체크박스 선택 해제 시
            else {
                if(e.getItem() == fruits[0]) // 사과 해제 시
                    sum -= 100;
                else if(e.getItem() == fruits[1]) // 배 해제 시
                    sum -= 500;
                else // 체리 해제 시
                    sum -= 20000;
            }
            // 총액 레이블 업데이트
            sumLabel.setText("총 " + sum + "원 입니다.");
        }
    }
    
    public static void main(String[] args) {
        new Grade_2019111679_017();
    }
}
