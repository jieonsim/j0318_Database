package HRAndNetPayExam;

import java.util.ArrayList;
import java.util.Scanner;

public class SalaryService {
	Scanner scanner = new Scanner(System.in);
	HRManagementDAO dao = new HRManagementDAO();
	HRVO vo;

	public void manageSalary() {
		while (true) {
			System.out.println("\n---------------- 본봉 관리 메뉴 ----------------");
			System.out.print("1. 등록   2. 조회   3. 수정   4. 삭제   0. 종료 => ");
			int menu = scanner.nextInt();

			if (menu == 0) {
				break; // 내부 루프 탈출, 상위 메뉴로 돌아감
			}

			switch (menu) {
			case 1:
				setBasicSalaryInput();
				break;
			case 2:
				getBasicSalaryList();
				break;
			case 3:
				setBasicSalaryUpdate();
				break;
			case 4:
				setBasicSalaryDelete();
				break;
			default:
				System.out.println("\n유효하지 않은 메뉴입니다. 다시 선택해주세요.");
				break;
			}
		}
	}

	// 본봉 신규 등록
	private void setBasicSalaryInput() {
		while (true) {
			System.out.print("\n본봉 등록을 원하는 직급을 입력해 주세요 : ");
			String jikkub = scanner.next();

			vo = dao.getJikkubSearch(jikkub);
			if (vo != null) {
				System.out.println("이미 존재하는 직급입니다. 다시 입력해 주세요.");
				continue;
			}

			System.out.print(jikkub + " 직급의 본봉을 입력해 주세요 : ");
			int bonbong = scanner.nextInt();

			vo = new HRVO();
			vo.setJikkub(jikkub);
			vo.setBonbong(bonbong);

			int result = dao.setBasicSalaryInput(vo);
			if (result != 0) {
				System.out.println("\n" + jikkub + " 직급의 본봉 등록이 완료되었습니다.");
			} else {
				System.out.println("\n" + jikkub + " 직급의 본봉 등록이 실패하였습니다.");
			}

			System.out.print("다른 직급의 본봉 등록을 계속 진행하시겠습니까? (Y/N) => ");
			String answer = scanner.next();

			if (!answer.toUpperCase().equals("Y")) {
				break;
			}
		}
	}

	// 본봉 조회
	private void getBasicSalaryList() {
		ArrayList<HRVO> vos = dao.getBasicSalaryList();
		System.out.println("\n\t\t직급별 본봉 리스트");
		System.out.println("============================");
		System.out.println("\t번호\t\t직급\t\t    본봉");
		System.out.println("-------------------------------------------------");

		for (int i = 0; i < vos.size(); i++) {
			vo = vos.get(i);
			System.out.print("\t" + (i + 1) + "\t\t");
			System.out.print(vo.getJikkub() + "\t\t");
			System.out.print(vo.getBonbong() + "\t\n");
		}
		System.out.println("-------------------------------------------------");
		System.out.println("\t\t총 직급 수 : " + vos.size() + "건");
		System.out.println("============================");
	}

	// 본봉 수정
	private void setBasicSalaryUpdate() {
		while (true) {
			System.out.print("\n본봉 수정을 진행할 직급을 입력해 주세요 : ");
			String jikkub = scanner.next();

			vo = dao.getJikkubSearch(jikkub);
			if (vo != null) {
				System.out.print(jikkub + " 직급의 새로운 본봉을 입력해 주세요 : ");
				int bonbong = scanner.nextInt();

				vo = new HRVO();
				vo.setJikkub(jikkub);
				vo.setBonbong(bonbong);

				int result = dao.setBasicSalaryUpdate(vo);

				if (result != 0) {
					System.out.println("\n" + jikkub + " 직급의 본봉 수정이 완료되었습니다.");
				} else {
					System.out.println("\n" + jikkub + " 직급의 본봉이 수정되지 않았습니다.");
				}

				System.out.print("다른 직급의 본봉 등록을 계속 진행하시겠습니까? (Y/N) => ");
				String answer = scanner.next();

				if (!answer.toUpperCase().equals("Y")) {
					break;
				}
			} else {
				System.out.println("존재하지 않는 직급입니다. 다시 입력해 주세요.");
				break;
			}
		}
	}

	// 본봉 삭제
	private void setBasicSalaryDelete() {
		while (true) {
			System.out.print("\n삭제할 직급을 입력해 주세요 : ");
			String jikkub = scanner.next();

			vo = dao.getJikkubSearch(jikkub);
			if (vo != null) {
				System.out.print("정말로 삭제하시겠습니까 ? (Y/N) => ");
				String answer = scanner.next();

				if (answer.toUpperCase().equals("Y")) {
					int result = dao.setBasicSalaryDelete(vo.getJikkub());

					if (result != 0) {
						System.out.println("\n" + jikkub + " 직급의 삭제가 완료되었습니다.");
					} else {
						System.out.println("\n" + jikkub + " 직급의 삭제가 처리되지 않았습니다.");
					}

					System.out.print("다른 직급의 본봉도 계속 삭제하시겠습니까? (Y/N) => ");
					answer = scanner.next();

					if (!answer.toUpperCase().equals("Y")) {
						break;
					}
				}
			} else {
				System.out.println("존재하지 않는 직급입니다. 다시 입력해 주세요.");
				break;
			}
		}
	}
}
