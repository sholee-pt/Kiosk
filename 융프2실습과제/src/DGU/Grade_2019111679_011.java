package DGU;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Grade_2019111679_011 extends JFrame {
	public Grade_2019111679_011() {
		setTitle("익명 클래스로 리스너");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn = new JButton("Action");
		c.add(btn);
		
		// 익명 클래스로 Action 리스너 작성
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				if(b.getText().equals("Action"))
					b.setText("액션");
				else
					b.setText("Action");
					setTitle(b.getText()); // G_2019111679_011의 멤버나 JFrame의 멤버를 호출할 수 있음
			}
		});
		setSize(350, 150);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Grade_2019111679_011();
	}

}
