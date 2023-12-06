package DGU;

import javax.swing.*;
import java.awt.*;

public class transformImageSize {
    // 이미지 아이콘의 크기를 변환하는 메소드
    public ImageIcon transformImageSize(ImageIcon imageIcon, int x, int y) {
        // 입력된 가로 또는 세로 길이가 유효한지 검사
        if(x<=0 || y<=0) {
            // 유효하지 않은 경우 예외 발생
        	throw new IllegalArgumentException("이미지의 가로, 세로 길이는 0보다 커야합니다.");
        }
        
        // 원본 ImageIcon에서 Image 객체를 추출
		Image image = imageIcon.getImage();
        // Image 객체의 크기를 지정된 가로(x), 세로(y) 크기로 조정
        Image scaledInstance = image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        // 크기가 조정된 Image 객체로 새 ImageIcon 생성
        ImageIcon newImageIcon = new ImageIcon(scaledInstance);
        // 새로운 ImageIcon 반환
        return newImageIcon;
    }
}
