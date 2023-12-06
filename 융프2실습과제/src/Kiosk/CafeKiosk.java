package Kiosk;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.*;

// CafeKiosk 클래스: 카페 키오스크의 주요 기능을 구현함
public class CafeKiosk {
    // 필요한 변수들 선언
    private JFrame frame;
    private JList<OrderItem> orderList;
    private DefaultListModel<OrderItem> listModel;
    private JLabel totalLabel;
    private JLabel currentPlaying;
    private Clip clip;
    private int totalPrice = 0;
    private int orderNumber = 0;
    private String currentMusic;
    
    // 생성자: 키오스크 초기화를 위한 메서드 호출
    public CafeKiosk() {
        initializeOrderFile();
        beforeKiosk();
    }

    // 키오스크 시작 전 로딩 화면 구성
    private void beforeKiosk() {
        // 로딩 화면 구성
        JFrame loading = new JFrame("실행중");
        loading.setLayout(new BorderLayout());
        JLabel loadingMsg = new JLabel("Loading... please wait", SwingConstants.CENTER);
        loadingMsg.setFont(new Font("Arial", Font.BOLD, 20));
        
        // 진행 상태를 표시할 JProgressBar 생성 및 설정
        JProgressBar pbar = new JProgressBar();
        pbar.setIndeterminate(true); // 진행 상태가 정해져 있지 않은 상태를 표시

        loading.add(loadingMsg, BorderLayout.CENTER);
        loading.add(pbar, BorderLayout.SOUTH); // 프로그레스 바를 창의 아래쪽에 추가

        loading.setSize(400, 150);
        loading.setLocationRelativeTo(null); // 창이 중앙에 뜨도록 위치 설정
        loading.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loading.setVisible(true);

        // 타이머를 사용하여 몇 초 후에 로딩 창 닫기
        Timer timer = new Timer(1500, new ActionListener() { // 시간 설정
            public void actionPerformed(ActionEvent e) {
                loading.dispose(); // 로딩 창 닫기
                startKiosk(); // 키오스크 시작
            }
        });
        timer.setRepeats(false); // 타이머가 한 번만 실행되도록 설정
        timer.start();
    }
    
    private void startKiosk() {
       // 메인 프레임 설정
        frame = new JFrame("Cafe Kiosk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.WHITE);

        // 메뉴 아이템과 가격 설정
        int[][] prices = {{1700, 2300, 2500, 2700, 2000, 2500},
                {3700, 3500, 3000, 3000, 3200, 3000, 2800},
                {3800, 4500, 4200, 4100, 4000, 3700, 4000},
                {5000, 5500, 5200, 4800, 4600}}; // 커피, 에이드, 버블티, 스무디의 가격 정보
        String[][] items = {{"아메리카노", "카라멜 마끼아또", "바닐라 라떼", "달고나 라떼", "카페 라떼", "카페 모카"},
                {"트로피컬 에이드", "블루 에이드", "딸기 에이드", "블루베리 에이드", "자몽 에이드", "청포도 에이드", "유자 에이드"},
                {"흑당 버블티", "달고나 버블티", "블랙 버블티", "얼그레이 버블티", "타로 버블티", "초콜릿 버블티", "마차 버블티"},
                {"블루베리 요거트 스무디", "딸기 요거트 스무디", "망고 요거트 스무디", "코코넛 커피 스무디", "초코 칩 스무디"}}; // 커피, 에이드, 버블티, 스무디의 메뉴 이름

        // 각 분류별 탭 생성 및 추가
        JTabbedPane tab = new JTabbedPane();
        String[] tabname = {"COFFEE", "ADE", "BUBBLE TEA", "SMOOTHIE"};
        for (int i = 0; i < tabname.length; i++) {
            JPanel categoryPanel = new JPanel(new GridLayout(0,4));
            for (int j = 0; j < items[i].length; j++) {
                createMenuItemPanel(items[i][j], prices[i][j], categoryPanel);
            }
            tab.addTab(tabname[i], new JScrollPane(categoryPanel));
        }
        
        // ETC 탭 추가
        JPanel etcPanel = new JPanel(new FlowLayout());
        tab.addTab("ETC", new JScrollPane(etcPanel));

        // 볼륨 조절 슬라이더 및 관련 컴포넌트 설정
        JSlider volume = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volume.setPaintLabels(true);
        volume.setMajorTickSpacing(50);
        volume.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int value = ((JSlider)e.getSource()).getValue();
                adjustVolume(value);
            }
        });

        JLabel vol = new JLabel("볼륨조절: ");
        vol.setFont(new Font("맑은 고딕", Font.BOLD, 16));

        // 음악 재생, 중지 버튼 및 파일 탐색 버튼 설정
        JButton playButton = new JButton("재생");
        playButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        playButton.setBackground(Color.LIGHT_GRAY);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                play();
            }
        });
        JButton stopButton = new JButton("중지");
        stopButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        stopButton.setBackground(Color.LIGHT_GRAY);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });
        JButton searchButton = new JButton("파일열기");
        searchButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        searchButton.setBackground(Color.LIGHT_GRAY);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchMusic();
            }
        });

        // 현재 재생중인 음악 정보 표시 레이블
        currentPlaying = new JLabel("현재 재생중: 없음");
        currentPlaying.setFont(new Font("맑은 고딕", Font.ITALIC, 20));
        
        // ETC 패널에 음악 관련 컴포넌트 추가
        etcPanel.add(playButton);
        etcPanel.add(stopButton);
        etcPanel.add(vol);
        etcPanel.add(volume);
        etcPanel.add(searchButton);
        etcPanel.add(currentPlaying);
        
        // 동국대 로고 이미지 레이블 설정
        transformImageSize transform = new transformImageSize();
        ImageIcon originalLogo = new ImageIcon("c:\\Q202\\images\\Kiosk\\DGU.png");
        ImageIcon logo = transform.transformImageSize(originalLogo, 300, 100);
        JLabel imageLabel = new JLabel(logo);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 주문 취소 및 초기화 버튼 설정
        listModel = new DefaultListModel<>();
        orderList = new JList<>(listModel);
        orderList.setFixedCellWidth(200); // 셀의 폭을 고정
        orderList.setFixedCellHeight(30); // 셀의 높이를 고정
        JScrollPane orderScrollPane = new JScrollPane(orderList);

        JLabel orderListLabel = new JLabel("주문목록");
        orderListLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        orderListLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 레이블을 가운데 정렬

        // 총 결제 금액 레이블 설정
        totalLabel = new JLabel("결제 금액: 0원");
        totalLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        totalLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // 금액 레이블을 왼쪽 정렬

        // 수량 감소 버튼 생성
        JButton delete = new JButton("주문취소");
        delete.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        delete.setAlignmentX(Component.CENTER_ALIGNMENT);
        delete.setBackground(Color.WHITE);
        
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 선택된 주문 항목 가져오기
                OrderItem selectedItem = orderList.getSelectedValue();
                if (selectedItem != null) {
                    // 선택된 항목의 수량 감소
                    selectedItem.adjustQuantity(-1);
                    // 수량이 0이 되면 목록에서 항목 제거
                    if (selectedItem.getQuantity() <= 0) {
                        listModel.removeElement(selectedItem);
                    }
                    updateTotalPrice(); // 총 금액 업데이트
                    orderList.repaint(); // 주문 목록 UI 갱신
                }
            }
        });
        
        // '전체 삭제' 버튼 생성
        JButton clear = new JButton("초기화");
        clear.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        clear.setAlignmentX(Component.CENTER_ALIGNMENT);
        clear.setBackground(Color.WHITE);
        
        // '초기화' 버튼에 대한 ActionListener 추가
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 주문 목록에서 모든 항목을 삭제
                resetOrderList();
            }
        });
        
        // 결제하기 버튼 및 결제 다이얼로그 설정
        JButton purchase = new JButton("결제하기");
        purchase.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        purchase.setAlignmentX(Component.CENTER_ALIGNMENT); // 버튼을 가운데 정렬
        purchase.setBackground(Color.YELLOW);

        purchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // 주문 목록에 아무것도 없으면 경고 메시지를 표시
                if (listModel.getSize() == 0) {
                    JOptionPane.showMessageDialog(frame, "주문목록에 상품이 없습니다.", "주문오류", JOptionPane.WARNING_MESSAGE);
                    return;
                }
               
               // 결제 방식을 선택할 다이얼로그 생성
               JDialog payment = new JDialog(frame, "결제 방식 선택", true);
               payment.setSize(300, 150);
               payment.setLayout(new GridLayout(0,2));

               // 신용카드 결제 버튼 생성
               JButton card = new JButton("신용카드");
               card.setFont(new Font("맑은 고딕", Font.BOLD, 20));
               card.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       showReceipt(); // 영수증 창을 보여주는 메소드 호출
                       payment.dispose(); // 창 닫기
                       saveOrder(); // 누적 주문 내역을 CSV파일로 저장
                       resetOrderList(); // 결제 후 주문 목록 초기화
                   }
                });

                // 현금결제 버튼 생성
                JButton cash = new JButton("현금결제");
                cash.setFont(new Font("맑은 고딕", Font.BOLD, 20));
                cash.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        showReceipt();
                        payment.dispose();
                        saveOrder();
                        resetOrderList();
                    }
                });

                // 버튼을 다이얼로그에 추가
                payment.add(card);
                payment.add(cash);

                // 다이얼로그를 화면 가운데에 표시
                payment.setLocationRelativeTo(frame);
                payment.setVisible(true);
            }
        });

        // 주문 패널 설정 및 컴포넌트 추가
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderPanel.add(orderListLabel);
        orderPanel.add(orderScrollPane);

        // 버튼을 수평으로 배치하기 위한 새 패널을 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // 버튼 패널의 최대 높이 설정

        // 버튼 크기 조절을 위해 각 버튼을 개별 패널에 넣음
        JPanel deleteButtonPanel = new JPanel();
        deleteButtonPanel.add(delete);
        JPanel clearButtonPanel = new JPanel();
        clearButtonPanel.add(clear);

        // 개별 패널을 버튼 패널에 추가
        buttonPanel.add(deleteButtonPanel);
        buttonPanel.add(clearButtonPanel);

        // 주문 패널에 버튼 패널을 추가
        orderPanel.add(buttonPanel);

        // 총 결제 금액 레이블을 담을 패널 생성
        JPanel totalLabelPanel = new JPanel();
        totalLabelPanel.add(totalLabel);
        totalLabelPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // 총 결제금액 라벨의 최대 높이 설정
        orderPanel.add(totalLabelPanel);

        // 주문 패널에 결제 버튼을 추가
        orderPanel.add(purchase);

        // 프레임에 컴포넌트들 추가
        frame.add(imageLabel, BorderLayout.NORTH);
        frame.add(tab, BorderLayout.CENTER);
        frame.add(orderPanel, BorderLayout.EAST);
        frame.setVisible(true);
    }

    private void createMenuItemPanel(String item, int price, JPanel panel) {        
        // 이미지 크기 조정 도구 인스턴스화
        transformImageSize trans = new transformImageSize();
        // 메뉴 아이템에 해당하는 이미지 로드
        ImageIcon originalIcon = new ImageIcon("C:\\Q202\\images\\Kiosk\\" + item + ".png");
        // 이미지 크기 조정
        ImageIcon icon = trans.transformImageSize(originalIcon, 120, 200);

        // 메뉴 아이템에 대한 이미지 버튼 생성
        JButton imageButton = new JButton(icon);
        imageButton.setBorder(BorderFactory.createEmptyBorder());
        imageButton.setContentAreaFilled(false);
        imageButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 메뉴 이름을 표시하는 레이블
        JLabel menu = new JLabel(item);
        menu.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // 메뉴 가격을 표시하는 레이블
        JLabel won = new JLabel(price + "원");
        won.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        won.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // 이미지 버튼에 액션 리스너 추가: 클릭 시 주문 목록에 항목 추가
        imageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 주문 목록에서 해당 항목 찾기
                OrderItem orderItem = findOrderItemByName(item);
                if (orderItem == null) {
                    // 주문 목록에 없는 새 항목 추가
                    orderItem = new OrderItem(item, price);
                    listModel.addElement(orderItem);
                }
                // 주문 항목 수량 증가
                orderItem.adjustQuantity(1);
                // 총 가격 업데이트
                updateTotalPrice();
                // 주문 목록 UI 갱신
                orderList.repaint();
            }
        });
        // 메뉴 항목을 배치할 패널 설정
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

        // 이미지 버튼, 메뉴 이름, 가격 레이블을 패널에 추가
        itemPanel.add(imageButton);
        itemPanel.add(menu);
        itemPanel.add(won);

        // 완성된 메뉴 항목 패널을 메인 패널에 추가
        panel.add(itemPanel);
    }

    private OrderItem findOrderItemByName(String itemName) {
       for (int i = 0; i < listModel.size(); i++) {
           OrderItem item = listModel.get(i);
           if (item.getName().equals(itemName)) {
               return item;
           }
       }
       return null; // 찾지 못했으면 null 반환
    }

    private void updateTotalPrice() {
        // 총 금액을 0으로 초기화
        totalPrice = 0;

        // 주문 목록에 있는 모든 항목에 대해
        for (int i = 0; i < listModel.size(); i++) {
            OrderItem item = listModel.get(i);
            // 각 항목의 총 가격을 총 금액에 추가
            totalPrice += item.getTotalPrice();
        }
        // 총 결제 금액 레이블 업데이트
        totalLabel.setText("결제 금액: " + totalPrice + "원");
    }

    // 결제 후 영수증을 보여주는 메서드
    private void showReceipt() {
        // 주문 번호 증가
        orderNumber++;

        // 영수증을 보여주기 위한 다이얼로그 생성 및 설정
        JDialog receiptf = new JDialog(frame, "영수증");
        receiptf.setSize(300, 400);
        receiptf.setLayout(new BorderLayout());

        // 영수증 내용을 표시할 텍스트 영역 생성 및 설정
        JTextArea receipt = new JTextArea();
        receipt.setEditable(false);
        receipt.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

        // 영수증 내용을 구성
        StringBuilder text = new StringBuilder();
        text.append("주문(대기)번호: ").append(orderNumber).append("\n");
        text.append("-----------------------------------\n");
        // 주문 목록에 있는 각 항목에 대한 정보 추가
        for (int i = 0; i < listModel.size(); i++) {
            OrderItem item = listModel.get(i);
            text.append(item.toString()).append("\n");
        }
        text.append("-----------------------------------\n");
        text.append("총 결제금액: ").append(totalPrice).append("원\n");
        text.append("\n");
        text.append("이용해주셔서 감사합니다.");
        
        // 구성된 영수증 텍스트를 텍스트 영역에 설정
        receipt.setText(text.toString());
        // 영수증 다이얼로그에 텍스트 영역 추가
        receiptf.add(new JScrollPane(receipt), BorderLayout.CENTER);
        // 다이얼로그 위치 설정 및 표시
        receiptf.setLocationRelativeTo(frame);
        receiptf.setVisible(true);
    }

    private void resetOrderList() {
        // 주문 리스트 모델을 비움. 이는 주문 목록에 있는 모든 항목을 제거함
        listModel.clear();

        // 총 금액을 0으로 초기화. 이는 새 주문을 위해 총 금액을 리셋함
        totalPrice = 0;

        // 총 결제 금액 레이블을 업데이트. 사용자 인터페이스에 총 금액을 0으로 표시함
        totalLabel.setText("결제 금액: " + totalPrice + "원");

        // 주문 목록 UI 갱신. 이는 사용자 인터페이스에 변경사항을 적용하여 반영함
        orderList.repaint();
    }

    private void saveOrder() {
       // 주문 기록을 저장할 파일의 경로 설정
       String fpath = "c:\\Q202\\files\\orders.csv";
   
       // 파일에 새로운 주문을 추가
       try (FileWriter writer = new FileWriter(fpath, true)) {
           // 주문 내역을 CSV 형식으로 파일에 작성
           for (int i = 0; i < listModel.size(); i++) {
               OrderItem item = listModel.get(i);
               // 각 주문 항목에 대한 정보를 파일에 추가
               writer.append(orderNumber + "," + item.getName() + "," + item.getQuantity() + "," + item.getTotalPrice() + "\n");
           }
       } catch (IOException e) {
           // 파일 작성 중 오류 발생 시 사용자에게 오류 메시지 표시
           JOptionPane.showMessageDialog(frame, "저장실패", "오류", JOptionPane.ERROR_MESSAGE);
           e.printStackTrace();
       }
   }

   // 애플리케이션 시작 시 주문 파일을 초기화하는 메서드
    private void initializeOrderFile() {
        // 주문 기록을 저장할 파일의 경로 설정
        String csvpath = "c:\\Q202\\files\\orders.csv";
        File file = new File(csvpath);

        // 파일이 이미 존재하는 경우 삭제. 이는 새 세션에서 새로운 주문 기록을 시작하기 위함임
        if (file.exists()) {
            file.delete();
        }

        // 새 파일을 생성하고 주문 기록의 헤더(주문번호, 상품명, 수량, 가격)를 작성
        try (FileWriter writer = new FileWriter(csvpath)) {
            writer.write("주문번호,상품명,수량,가격\n");
        } catch (IOException e) {
            // 파일 작성 중 발생하는 예외 처리
            e.printStackTrace();
        }
    }
    
    private void play() {
        try {
            // 이미 음악이 재생 중인 경우, 사용자에게 알림
            if (clip != null && clip.isRunning()) {
                JOptionPane.showMessageDialog(frame, "노래가 이미 재생 중입니다.", "알림", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 기존에 재생 중인 클립이 있으면 닫기
            if (clip != null && clip.isOpen()) {
                clip.close();
            }

            // 현재 재생할 음악 파일이 설정되었는지 확인
            if (currentMusic == null || currentMusic.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "재생할 파일을 선택해주세요.", "재생 오류", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 선택된 오디오 파일이 실제로 존재하는지 확인
            File audio = new File(currentMusic);
            if (!audio.exists()) {
                JOptionPane.showMessageDialog(frame, "파일을 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 오디오 스트림을 생성하고 클립을 열어 재생
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audio);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            // 재생 중 발생하는 예외 처리
            e.printStackTrace();
        }
    }
    
    private void stop() {
       clip.stop();
    }

    private void searchMusic() {
       // 사용자로부터 음악 파일명 입력받기
       String musicName = JOptionPane.showInputDialog(frame, "음악파일명(.wav)을 입력하세요.");
       
       // 파일명이 입력되지 않았을 경우 오류 메시지 표시 후 함수 종료
       if (musicName == null || musicName.isEmpty()) {
           JOptionPane.showMessageDialog(frame, "파일명을 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
           return;
       }
   
       // 입력받은 파일명으로 현재 재생 중인 파일 경로 업데이트
       currentMusic = "c:\\Q202\\files\\" + musicName + ".wav";
   
       try {
           // 파일 객체 생성 및 존재 여부 확인
           File audioFile = new File(currentMusic);
           if (audioFile.exists()) {
               // 현재 재생중인 파일명을 UI에 표시
               currentPlaying.setText("현재 재생중: " + musicName + ".wav");
               
               // 이미 열린 클립이 있으면 닫기
               if (clip != null && clip.isOpen()) {
                   clip.close();
               }
   
               // 오디오 스트림 열기 및 클립 시작
               AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
               clip = AudioSystem.getClip();
               clip.open(audioStream);
               clip.start();
           } else {
               // 파일이 존재하지 않으면 오류 메시지 표시
               JOptionPane.showMessageDialog(frame, "파일을 찾을 수 없습니다", "오류", JOptionPane.ERROR_MESSAGE);
           }
       } catch (Exception e) {
           // 재생 중 발생하는 예외 처리
           JOptionPane.showMessageDialog(frame, "재생 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
           e.printStackTrace();
       }
   }

   private void adjustVolume(int volume) {
        // 클립이 null이 아닌 경우 볼륨 조절
        if (clip != null) {
            // 클립의 볼륨 컨트롤을 가져옴
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = gainControl.getMinimum(); // 최소 볼륨 값
            float max = gainControl.getMaximum(); // 최대 볼륨 값

            // 볼륨을 로그 스케일로 조정하여 부드러운 변환을 제공
            float scaledVolume = min + (max - min) * (float)(Math.log(volume + 1) / Math.log(101));

            // 설정된 볼륨이 범위를 벗어나지 않도록 보정
            scaledVolume = Math.max(min, Math.min(max, scaledVolume));

            // 볼륨 설정 적용
            gainControl.setValue(scaledVolume);
        }
    }
    
    public static void main(String[] args) {
       System.out.println("Kiosk project made by 2019111679_LeeSeungHo\nv1.0");
       new CafeKiosk();   
       }
}