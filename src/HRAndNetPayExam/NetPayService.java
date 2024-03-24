package HRAndNetPayExam;

import java.util.Scanner;

public class NetPayService {
	Scanner scanner = new Scanner(System.in);
	HRManagementDAO dao = new HRManagementDAO();
	HRVO vo;

	public void manageNetPay() {
		while (true) {
			System.out.println("\n----------------------- 급여 관리 메뉴 -----------------------");
			System.out.print("1. 실 수령액 계산   0. 종료 => ");
			int menu = scanner.nextInt();

			if (menu == 0) {
				break; // 내부 루프 탈출, 상위 메뉴로 돌아감
			}

			switch (menu) {
			case 1:
				getNetPayCalculator();
				break;
			default:
				System.out.println("\n유효하지 않은 메뉴입니다. 다시 선택해주세요.");
				break;
			}
		}
	}

	public void getNetPayCalculator() {
		while (true) {
			System.out.println("\n실 수령액 계산이 필요한 직원의 사번을 입력해 주세요.");
			System.out.print("사번 : ");
			String sabun = scanner.next();

			HRVO vo = dao.getSabunSearch(sabun);
			if (vo == null) {
				System.out.println("존재하지 않는 사번입니다.");
			} else {
				System.out.print("\n초과 근무 시간을 입력해 주세요 : ");
				int overTime = scanner.nextInt();

				// 초과 근무 수당 계산
				int extraPay = overTime * 30000;
				// 총액 계산
				int total = extraPay + vo.getBonbong();
				// 공제액 계산
				int deduction = (int) (total * 0.1);
				// 실수령액 계산
				int netPay = total - deduction;

				System.out.println("\n" + sabun + " 사번의 이번 달 급여명세서");
				System.out.println("▶ 직급 : " + vo.getJikkub());
				System.out.println("▶ 본봉 : " + vo.getBonbong() + "원");
				System.out.println("▶ 초과 근무 시간 : " + overTime + "시간");
				System.out.println("▶ 공제액 : " + deduction + "원");
				System.out.println("▶ 실 수령액 : " + netPay + "원");
			}

			System.out.print("\n다른 직원의 실 수령액 계산을 계속 진행하시겠습니까? (Y/N): ");
			String answer = scanner.next();

			if (!answer.toUpperCase().equals("Y")) {
				break;
			}
		}
	}
}