package DGU;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Grade_2019111679_009 extends JFrame {
	public Grade_2019111679_009() {
		setTitle("독립 클래스로 리스너");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn = new JButton("Action");
		btn.addActionListener(new MyActionListener()); // Action 리스너 달기
		c.add(btn);
		
		setSize(350, 150);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Grade_2019111679_009();
	}

}

class MyActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if(b.getText().equals("Action"))
			b.setText("액션");
		else
			b.setText("Action");
	}
}