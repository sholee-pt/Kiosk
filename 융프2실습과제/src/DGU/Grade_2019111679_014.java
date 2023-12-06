package DGU;

import java.io.*;
import java.util.Scanner;

public class Grade_2019111679_014 {
    public static void main(String[] args) {
        try {
            // 사용자로부터 파일명 입력 받기
            Scanner sc = new Scanner(System.in);
            System.out.print("파일명 입력: ");
            String fname = sc.nextLine();

            // 파일 경로 지정
            String fpath = "c:\\Temp\\" + fname;

            // 파일 경로에 해당하는 파일 객체 생성
            File file = new File(fpath);

            // 파일에 사용자 입력 쓰기
            System.out.println("--------입력 시작(종료하려면 엔터를 두번 누르세요)--------");
            FileWriter fwriter = new FileWriter(file);
            String enter;
            while (!(enter = sc.nextLine()).isEmpty()) {
                fwriter.write(enter + "\r\n");
            } // 파일의 입력을 계속 받다가 공백을 두 번 받으면 Loop을 종료
            fwriter.close();
            System.out.println("-------------------입력 종료-------------------");

            // 파일에서 데이터 읽기
            FileReader freader = new FileReader(file);
            BufferedReader br = new BufferedReader(freader);
            String line;
            System.out.println("---------------" + fname + " 출력----------------");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            sc.close();
            System.out.println("\n파일이 " + fpath + " 경로에 저장 완료되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
