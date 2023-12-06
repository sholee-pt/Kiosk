package DGU;

import java.util.Scanner;

public class Grade_2019111679_001 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 학생 수 입력
        System.out.print("학생 수를 입력하세요: ");
        int students = sc.nextInt();

        // 국어와 영어 성적을 저장할 배열 생성
        int[] korean = new int[students];
        int[] english = new int[students];

        // 각 학생의 국어와 영어 성적 입력받기
        for (int i = 0; i < students; i++) {
        	System.out.println("------------------------");
        	System.out.println("홍길동" + (i+1) + "의 점수를 입력하세요.");
            System.out.print("국어: ");
            korean[i] = sc.nextInt();

            System.out.print("영어: ");
            english[i] = sc.nextInt();
        }
        sc.close();

        // 각 학생의 성적과 학점 출력
        System.out.println();
        System.out.println("< 학점 계산 결과 >");
        for (int i = 0; i < students; i++) {
            int kscore = korean[i];
            int escore = english[i];

            String kgrade = calculate(kscore);
            String egrade = calculate(escore);

            System.out.println("------------------------");
            System.out.println("홍길동" + (i+1) + "의 학점은 다음과 같습니다.");
            System.out.println("국어: " + kgrade);
            System.out.println("영어: " + egrade);
        }
    }

    // 주어진 기준에 따라 학점을 계산하는 함수
    public static String calculate(int score) {
        if (score >= 0 && score <= 100) {
        	if (score >= 95) {
        		return "A+";
        	} else if (score >= 90) {
        		return "A";
        	} else if (score >= 85) {
        		return "B+";
        	} else if (score >= 80) {
        		return "B";
        	} else if (score >= 75) {
        		return "C+";
        	} else if (score >= 70) {
        		return "C";
        	} else if (score >= 65) {
        		return "D+";
        	} else if (score >= 60) {
        		return "D";
        	} else {
        		return "F";
        	}
        } else {
        	return "점수를 제대로 다시 입력하세요.";
        }
    }}
