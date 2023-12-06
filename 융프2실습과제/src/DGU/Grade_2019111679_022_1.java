package DGU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 과일의 가격과, 이름, 이미지를 담은 클래스
class Fruit {
	private String name;
	private int price;
	private ImageIcon image;
	//생성자
	Fruit(String n, int p){
		name = n;
		price = p;
		image = new ImageIcon("c:\\Q202\\images\\"+name+".png");
	}
	//겟 함수
	String getFName() {
		return name;
	}
	int getFPrice() {
		return price;
	}
	ImageIcon getImage() {
		return image;
	}
}
public class Grade_2019111679_022_1 extends JFrame{

	private int w,h;//프레임 크기
	private int size;//개수
	private Container c = getContentPane();//함수에 사용하기 때문에 필드에 선언
	private Panel002 p2;//패널2
	private Panel003 p3;//패널3
	private Fruit FArray[];//입력받을 과일 배열

	
	public Grade_2019111679_022_1() {
		 w=300;
		 h=300;
		//기본 프레임 설정
		setTitle("Interface1: 질의응답");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//개수입력 패널(패널1)을 컨텐츠 팬에 붙이기
		c.add(new Panel001(this),BorderLayout.CENTER);
		
		setSize(w,h);
		setVisible(true);		
	}

	//개수의 겟,셋 함수
	public int getFruitSize() {
		return size;
	}

	public void setFruitSize(int s) {
		this.size = s;
	}
	//과일 배열의 생성, 겟, 셋 함수
	void makeFArray(int size) {
		FArray = new Fruit[size];
	}
	void setFArray(int i,String n, int p) {
		this.FArray[i]=new Fruit(n, p);
	}
	Fruit getFArray(int i) {
		return FArray[i];
	}
	
	//패널을 교체하는 메소드
	public void chagePanel(int p) {
		c.removeAll();//현재 패널 지우기
	
		if (p == 2) {
	        p2 = new Panel002(this);//패널2 객체 생성
	        c.add(p2, BorderLayout.CENTER);//새로운 패널 컨테이너에 붙이기
	    } else if (p == 3) {
	        p3 = new Panel003(this);//패널3 객체 생성
	        c.add(p3, BorderLayout.CENTER);
	    }
		c.revalidate();//컨테이너 업데이트(안해도 되지만, 혹시 모르니 하는게 좋음)
	}

	public static void main(String[] args) {
		new Grade_2019111679_022_1();
	}

}
//개수입력 패널(패널1)
class Panel001 extends JPanel{
	Panel001(Grade_2019111679_022_1 g){
		setLayout(new GridLayout(3,1));
		add(new JLabel("원하는 개수입력하세요"));
		JTextField inputField = new JTextField();
		//패널2에 개수를 전달하는 버튼
		JButton inputButton = new JButton("확인");
		inputButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int size = Integer.parseInt(inputField.getText());//입력받은 문자열을 정수로 변환
				g.setFruitSize(size);//개수를 업데이트하기
				g.chagePanel(2);//2번째 패널로 변경
			}
		});
		add(inputField);
		add(inputButton);	
	}
}
//이름, 가격 입력 패널(패널2)
class Panel002 extends JPanel{	
	private int size;
	Panel002(Grade_2019111679_022_1 g){
		
       setLayout(new BorderLayout());
       size = g.getFruitSize();//업데이트된 개수 가져오기
       // 과일 배열 생성
       g.makeFArray(size);
      
       //이름, 가격 입력 패널
       JPanel scrolledPanel = new JPanel();
       scrolledPanel.setLayout(new GridLayout((size+1),1));//입력할 과일 개수 + 버튼을 배치
       JScrollPane sp = new JScrollPane(scrolledPanel);//스크롤 가능
       
       JPanel inputPanel[] = new JPanel[size];//하나의 과일의 이름, 가격 입력 패널
       JTextField inputNameField[] = new JTextField[size];//이름입력 텍스트필드
       JTextField inputPriceField[] = new JTextField[size];//가격입력 텍스트필드
       JButton inputButton2 = new JButton("버튼 만들기");//다음 패널로 넘어가는 버튼
       
       //개수만큼 입력 패널 생성하기
       for(int i=0;i<size;i++) {
    	   inputPanel[i]= new JPanel(new GridLayout(2,2));
    	   //초기화
    	   inputNameField[i] = new JTextField();
    	   inputPriceField[i] = new JTextField();
    	   //이름, 가격 입력
    	   inputPanel[i].add(new JLabel((i+1)+"번째 과일이름"));
    	   inputPanel[i].add(inputNameField[i]);
    	   inputPanel[i].add(new JLabel((i+1)+"번째 과일가격"));
    	   inputPanel[i].add(inputPriceField[i]);
    	   //하나의 과일을 입력하는 입력패널을 하나의 패널에 추가하기
    	   scrolledPanel.add(inputPanel[i]);
    	   //전부 다 추가되면, 버튼 추가하기
    	   if((size-1)==i) {
    	       scrolledPanel.add(inputButton2);
    	   }
       }
              
       //패널3로 넘어가는 버튼
       inputButton2.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		 //텍스트 필드에서 가져온 문자열을 이름과 가격으로 각각 저장
    		   for(int i=0;i<size;i++) {
    			   String name = inputNameField[i].getText();
    			   int price = Integer.parseInt(inputPriceField[i].getText());
    			   g.setFArray(i,name, price); 
    		   }
    		   g.chagePanel(3);//패널3으로 교체
    	   }
       });
       
       add(sp);//스크롤팬을 패널에 붙이기
	}
}

//라디오 버튼 패널(패널3)
class Panel003 extends JPanel{
	private int size;
	private JRadioButton radioButtons[];
	
	Panel003(Grade_2019111679_022_1 g){
		
		setLayout(new BorderLayout());
		//개수 가져오기
		size = g.getFruitSize();
		// 입력받은 개수 만큼 라디오 버튼 생성		
		radioButtons = new JRadioButton[size];
		
		//패널 3개 생성
		JPanel radioPanel = new JPanel();//라디오 버튼 출력 패널
		JPanel imagePanel = new JPanel();//이미지 출력 패널
		JPanel pricePanel = new JPanel();//가격 출력 패널
		
		//이미지 패널
		JLabel showimage = new JLabel();//이미지 아이콘이 출력될 라벨
		imagePanel.add(showimage);
		//가격패널
		JLabel showPrice = new JLabel("과일을 선택하십시오.");//초기에는 선택된 과일이 없으므로, 선택요구 메세지 출력
		pricePanel.add(showPrice);
	
		//라디오 버튼
		ButtonGroup bg = new ButtonGroup();//라디오 버튼 그룹
		//개수만큼 라디오 버튼 생성
		for(int i=0;i<size;i++) {
			Fruit fruit = g.getFArray(i);//i번째 과일 가져오기
			radioButtons[i]=new JRadioButton(fruit.getFName());//해당과일의 이름을 가져와 라디오 버튼 생성
			bg.add(radioButtons[i]);//버튼 그룹에 추가
			radioPanel.add(radioButtons[i]);//라디오 패널에 추가
						
			// 버튼이 선택 됐을 때의 효과
			radioButtons[i].addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange()==ItemEvent.SELECTED) {//선택됐을때 
						ImageIcon image = imageSize(fruit.getImage());//이미지 사이즈 조절
						showimage.setIcon(image);//이미지 패널에 해당이미지 출력
						showPrice.setText(fruit.getFName()+"의 가격은 "+ fruit.getFPrice()+"원 입니다.");//가격패널에 해당 이름과 가격 출력
					}	
				}
			});
		}
		//라디오 버튼 개수가 많을 때를 대비한 스크롤팬 추가
		JScrollPane js = new JScrollPane(radioPanel);
		js.setPreferredSize(new Dimension(0,50));
		//패널3에 세개의 패널 순서대로 붙이기
		add(js,BorderLayout.NORTH);
		add(imagePanel,BorderLayout.CENTER);
		add(pricePanel,BorderLayout.SOUTH);
	}
	//이미지 크기 조절 함수
	ImageIcon imageSize(ImageIcon i) {
		Image ig = i.getImage();
		ImageIcon scaledImage = new ImageIcon(ig.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
		return scaledImage;
	}
}
