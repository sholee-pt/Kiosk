package DGU;

import java.io.*;
import java.util.Scanner;

public class Grade_2019111679_015 {
    public static void main(String[] args) throws IOException {
    	// 사용자로부터 파일명 입력 받기
        Scanner sc = new Scanner(System.in);
        System.out.print("파일명 입력: ");
        String fname = sc.nextLine();

        // 파일 경로 지정
        String fpath = "c:\\Temp\\" + fname;
        
        // getString() 메소드에서 문자열을 받아와 word에 넣고 파일에 입력
    	String word = getString();
    	FileWriter fw = new FileWriter(fpath);
    	fw.write(word);
    	fw.close();
    	
    	// 파일에서 데이터 읽기
    	System.out.println("---------------" + fname + " 출력----------------");
    	FileReader fr = new FileReader(fpath);
    	int i;
    	while ((i = fr.read()) != -1) {
    		System.out.print((char)i);
    	}
    	fr.close();
    	sc.close();
    	System.out.println("\n파일이 " + fpath + " 경로에 저장 완료되었습니다.");
    }
    
    // 문자열을 입력받아서 반환하는 메소드
    public static String getString() {
        Scanner scan = new Scanner(System.in);
        StringBuilder input = new StringBuilder();
        System.out.println("--------입력 시작(종료하려면 엔터를 두번 누르세요)--------");
        while (true) {
            String line = scan.nextLine();
            if (line.isEmpty()) {
                break;
            } // 파일의 입력을 계속 받다가 공백을 두 번 받으면 Loop을 종료
            input.append(line).append("\n");
        }
        System.out.println("-------------------입력 종료-------------------");
        scan.close();
        return input.toString(); // 입력받은 문자 반환
    }
}