package t5_CRUD;

import java.util.*;

public class SungjukRun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		SungjukService service = new SungjukService();
		
		int choice = 0;
		boolean run = true;
		
		while(run) {
			System.out.println("\n\t** 성  적  표 **");
			System.out.print("메뉴 선택 | 1. 성적 입력 2. 전체 조회 3. 개별 조회 4. 수정 5. 삭제 0. 종료 ==> ");
			choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					service.setSungjukInput();
					break;
				case 2:
					service.getSungjukList();
					break;
				case 3:
					service.getSungjukSearch();
					break;
				case 4:
					service.setSungjukUpdate();
					break;
				case 5:
					service.setSungjukDelete();
					break;
				default:
					run = false;
			}			
		}
		System.out.println("============================================================");
		System.out.println("종료되었습니다.");
		sc.close();
	}
}
