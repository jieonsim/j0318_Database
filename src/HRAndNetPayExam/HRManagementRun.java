package HRAndNetPayExam;

import java.util.Scanner;

public class HRManagementRun {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SalaryService salaryService = new SalaryService();
		EmployeeService hrService = new EmployeeService();
		NetPayService netPayService = new NetPayService();
		
		while (true) {
			System.out.println("\n======== 인사 /  급여 관리 프로그램 ========");
			System.out.print("1. 본봉 관리   2. 인사 관리   3. 급여 관리   0. 종료 => ");
			int option = scanner.nextInt();

			switch (option) {
			case 1:
				salaryService.manageSalary();
				break;
			case 2:
				hrService.manageHR();
				break;
			case 3:
				netPayService.manageNetPay();
				break;
			case 0:
				System.out.println("\n프로그램을 종료합니다. 이용해 주셔서 감사합니다.");
				scanner.close();
				return;
			default:
				System.out.println("\n유효하지 않은 옵션입니다. 다시 선택해 주세요.");
			}
		}
	}
}
