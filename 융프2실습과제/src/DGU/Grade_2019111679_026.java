package DGU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Grade_2019111679_026 extends JFrame {
    JLabel label;
    JButton btn;
    int w, h; // 이미지의 가로와 세로 크기를 저장할 변수

    public Grade_2019111679_026() {
        Scanner sc = new Scanner(System.in); // 스캐너 객체 생성
        
        setTitle("Image button"); // 프레임의 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 동작 설정
        Container c = getContentPane(); // 컨테이너 가져오기
        c.setLayout(new FlowLayout()); // 컨테이너의 레이아웃을 FlowLayout으로 설정
        
        // 사용자로부터 이미지의 가로, 세로 길이를 입력받음
        System.out.print("이미지의 가로 길이를 입력하세요: ");
        w = sc.nextInt();
        System.out.print("이미지의 세로 길이를 입력하세요: ");
        h = sc.nextInt();

        // ImageIcon 객체들 생성
        ImageIcon normal = new ImageIcon("c:\\Q202\\images\\apple.png"); // 기본 아이콘
        ImageIcon rollover = new ImageIcon("c:\\Q202\\images\\apple.png"); // 마우스 오버 아이콘
        ImageIcon pressed = new ImageIcon("c:\\Q202\\images\\appleB.png"); // 클릭 아이콘

        // 이미지 크기 조정 클래스 인스턴스 생성 및 이미지 크기 조정
        transformImageSize trans = new transformImageSize();
        ImageIcon rnormal = trans.transformImageSize(normal, w, h); // 조정된 기본 아이콘
        ImageIcon rpressed = trans.transformImageSize(rollover, w, h); // 조정된 마우스 오버 아이콘
        ImageIcon rrollover = trans.transformImageSize(pressed, w, h); // 조정된 클릭 아이콘

        // JButton 설정
        btn = new JButton("apple", rnormal); // 버튼 생성 및 아이콘 설정
        btn.setPressedIcon(rpressed); // 버튼 누를 때 아이콘 설정
        btn.setRolloverIcon(rrollover); // 마우스 오버 시 아이콘 설정

        // JLabel 설정
        label = new JLabel("Button pressed");
        label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        label.setVisible(false); // 라벨을 처음에는 보이지 않게 설정

        // 컴포넌트들을 컨테이너에 추가
        c.add(btn);
        c.add(label);

        // 마우스 리스너 추가: 버튼 클릭 시 라벨의 보임/숨김 상태 변경
        btn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                label.setVisible(true); // 버튼 누를 때 라벨 보임
            }
            public void mouseReleased(MouseEvent e) {
                label.setVisible(false); // 버튼에서 손 뗄 때 라벨 숨김
            }
        });

        setSize(250, h+100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Grade_2019111679_026();
    }
}
