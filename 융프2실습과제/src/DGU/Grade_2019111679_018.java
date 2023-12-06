package DGU;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Grade_2019111679_018 extends JFrame{
	// 스캐너 호출
	Scanner sc = new Scanner(System.in);
		
	// 이미지 크기 변환 메소드 호출
	// transformImagSize.java 파일이 동일한 DGU 패키지 내에 속해있으므로 별도의 import문이 필요없음
	transformImageSize trans = new transformImageSize();
	int w, h;
	
    // 선택사항 이름
    String name[] = {"사과","배","체리"};
    
    // 선택해제되었을 때 이미지 아이콘
    ImageIcon icon[] = new ImageIcon[name.length];
    
    // 선택되었을 때 이미지 아이콘
    ImageIcon selectedIcon[] = new ImageIcon[name.length];
    
    // 체크박스
    JCheckBox checkBox[] = new JCheckBox[name.length];
    
    // 레이블
    JLabel sumLabel;

    public Grade_2019111679_018(){
        setTitle("combined checkbox");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        
        // 레이블을 크게 3개로 나누어 실행했을때 창의 크기가 변해도 내용의 위치와 형태를 그대로 유지할 수 있게함
        // 첫번째 레이블: 과일 가격 정보
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("사과: 100원, 배: 500원, 체리: 20000원"));
        c.add(panel1);
        
        // 두번째 레이블: 체크박스
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        
        // 이미지의 가로 길이와 세로 길이를 사용자로부터 입력 받음
        System.out.print("이미지의 가로 길이를 입력하세요: ");
        w = sc.nextInt();
        System.out.print("이미지의 세로 길이를 입력하세요: ");
        h = sc.nextInt();
        
        // 선택되지 않았을 때의 이미지 파일
        icon[0] = new ImageIcon("c:\\Q202\\images\\apple.png");
        icon[1] = new ImageIcon("c:\\Q202\\images\\pear.png");
        icon[2] = new ImageIcon("c:\\Q202\\images\\cherry.png");
        for(int i=0; i<3; i++) {
            icon[i] = trans.transformImageSize(icon[i], w, h);
        }
        
        // 선택됐을 때의 이미지 파일
        selectedIcon[0] = new ImageIcon("c:\\Q202\\images\\appleB.png");
        selectedIcon[1] = new ImageIcon("c:\\Q202\\images\\pearB.png");
        selectedIcon[2] = new ImageIcon("c:\\Q202\\images\\cherryB.png");
        for(int i=0; i<3; i++) {
            selectedIcon[i] = trans.transformImageSize(selectedIcon[i], w, h);
        }
        
        // 체크박스마다 설정
        for(int i=0; i<name.length;i++) {
            checkBox[i] = new JCheckBox(name[i], icon[i]);
            checkBox[i].setSelectedIcon(selectedIcon[i]);
            checkBox[i].setBorderPainted(true); 
            
            checkBox[i].setToolTipText(name[i]+"을(를) 선택하시겠습니까?");
            int index = i;
            checkBox[i].addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED)
                        checkBox[index].setToolTipText(name[index] + "을(를) 선택취소하시겠습니까?");
                    else
                        checkBox[index].setToolTipText(name[index] + "을(를) 선택하시겠습니까?");
                        
                    calculatePrice();
                }
            });
            panel2.add(checkBox[i]);
        }
        c.add(panel2);
        
        // 세번째 레이블: 총 가격 정보
        JPanel panel3 = new JPanel();
        sumLabel = new JLabel("현재 0원 입니다.");
        panel3.add(sumLabel);
        c.add(panel3);
        
        // 이미지의 크기에 따라 최대한 유동적으로 창의 크기를 결정함(최대 해상도: 1920x1080)
        if(w*5 > 1920)
        	w = 384; // w*5<1920를 만족하는 최대 w의 크기
        if(h*4 > 1080)
        	h = 270; // h*4<1080을 만족하는 최대 h의 크기
        setSize(w*5,h*4);
        
        setVisible(true);
    }
    
    // 가격 계산 메소드
    public void calculatePrice() {
        int sum = 0;
        for (int i=0; i<checkBox.length; i++) {
            if (checkBox[i].isSelected()) {
                switch (i) {
                    case 0:
                        sum += 100;
                        break;
                    case 1:
                        sum += 500;
                        break;
                    case 2:
                        sum += 20000;
                        break;
                }
            }
        }
        sumLabel.setText("총 " + sum + "원 입니다.");
    }
    
    public static void main(String[] args) {
        new Grade_2019111679_018();
    }
}
