package DGU;

import java.util.Scanner;

public class Grade_2019111679_004 {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int grade;
    	do {
    		System.out.print("이수한 학년 수를 입력하세요: ");
    		grade = sc.nextInt();
    		if(grade < 1 || grade > 4) {
    			System.out.println("학년 수는 1에서 4 사이어야 합니다. 다시 입력하세요.");
    		}
    	} while (grade < 1 || grade > 4);    	
        double score[][] = new double[grade][2]; // 이수한 년간 2학기의 평점을 저장하는 배열

        // 각 학기별 학점 입력 받기
        for (int year = 0; year < score.length; year++) {
            for (int term = 0; term < score[year].length; term++) {
                do {
                    System.out.print((year + 1) + "학년 " + (term + 1) + "학기 평점을 입력하세요: ");
                    score[year][term] = sc.nextDouble();
                    if (score[year][term] < 0 || score[year][term] > 4.5) {
                        System.out.println("평점은 0에서 4.5 사이어야 합니다. 다시 입력하세요.");
                    }
                } while (score[year][term] < 0 || score[year][term] > 4.5);
            }
        }
        sc.close();

        double sum = 0;
        for (int year = 0; year < score.length; year++) {
            for (int term = 0; term < score[year].length; term++) {
                sum += score[year][term];
            }
        }

        int n = score.length; // 배열의 행 개수, 3
        int m = score[0].length; // 배열의 열 개수, 2

        System.out.println("이전 " + grade + "년간 전체 평점 평균은 " + sum / (n * m) + "입니다.");
    }
}
