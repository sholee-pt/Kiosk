package DGU;

import java.io.*;
import java.util.Scanner;

public class Grade_2019111679_012 {
    public static void main(String[] args) {
    	// 사용자로부터 파일명 입력받기
        Scanner sc = new Scanner(System.in);
        System.out.print("파일명 입력: ");
        String fname = sc.nextLine();
        
        // 파일 경로 지정
        String fpath = "c:\\Temp\\" + fname;

        // 파일에 사용자 입력 쓰기
        System.out.println("--------입력 시작(종료하려면 엔터를 두번 누르세요)--------");
        try (FileOutputStream fout = new FileOutputStream(fpath);
             OutputStreamWriter fwrite = new OutputStreamWriter(fout, "UTF-8")) {
            String line;
            while (!(line = sc.nextLine()).isEmpty()) {
                fwrite.write(line + "\r\n");
            } // 파일의 입력을 계속 받다가 공백을 두 번 받으면 Loop을 종료
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------입력 종료-------------------");

        // 파일에서 데이터 읽기
        System.out.println("---------------" + fname + " 출력----------------");
        try (FileInputStream fin = new FileInputStream(fpath);
             InputStreamReader fread = new InputStreamReader(fin, "UTF-8")) {
            int c;
            while ((c = fread.read()) != -1) {
                System.out.print((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n파일이 " + fpath + " 경로에 저장 완료되었습니다.");
        sc.close();
    }
}
