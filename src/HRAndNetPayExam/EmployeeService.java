package HRAndNetPayExam;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeService {
	Scanner scanner = new Scanner(System.in);
	HRManagementDAO dao = new HRManagementDAO();
	HRVO vo;

	public void manageHR() {
		while (true) {
			System.out.println("\n----------------------- 인사 관리 메뉴 -----------------------");
			System.out.print("1. 등록   2. 전체 조회   3. 직원 검색   4. 수정   5. 삭제   0. 종료 => ");
			int menu = scanner.nextInt();

			if (menu == 0) {
				break; // 내부 루프 탈출, 상위 메뉴로 돌아감
			}

			switch (menu) {
			case 1:
				setEmployeeInput();
				break;
			case 2:
				getEmployeeFullList();
				break;
			case 3:
				getEmployeeSearch();
				break;
			case 4:
				setEmployeeUpdate();
				break;
			case 5:
				setEmployeeDelete();
				break;
			default:
				System.out.println("\n유효하지 않은 메뉴입니다. 다시 선택해주세요.");
				break;
			}
		}
	}

	// 직원 신규 등록
	private void setEmployeeInput() {
		while (true) {
			System.out.println("\n신규 등록하려는 직원의 정보를 입력해 주세요.");

			System.out.print("사번 : ");
			String sabun = scanner.next();

			vo = dao.getSabunSearch(sabun);
			if (vo != null) {
				System.out.println("이미 존재하는 사번입니다. 다시 입력해 주세요.");
				continue;
			}

			System.out.print("부서명 : ");
			String buseo = scanner.next();

			System.out.print("이름 : ");
			String name = scanner.next();

			System.out.print("직급 : ");
			String jikkub = scanner.next();

			System.out.print("나이 : ");
			int age = scanner.nextInt();

			System.out.print("입사일 : ");
			String ipsail = scanner.next();

			System.out.print("성별 : ");
			String gender = scanner.next();

			System.out.print("주소 : ");
			String address = scanner.next();

			vo = new HRVO();
			vo.setSabun(sabun);
			vo.setBuseo(buseo);
			vo.setName(name);
			vo.setJikkub(jikkub);
			vo.setAge(age);
			vo.setIpsail(ipsail);
			vo.setGender(gender);
			vo.setAddress(address);

			int result = dao.setEmployeeInput(vo);
			if (result != 0) {
				System.out.println("\n" + name + " 님의 인사 등록이 완료되었습니다.");
			} else {
				System.out.println("\n" + name + " 님의 인사 등록이 실패하였습니다.");
			}

			System.out.print("다른 직원의 인사 등록을 계속 진행하시겠습니까? (Y/N) => ");
			String answer = scanner.next();

			if (!answer.toUpperCase().equals("Y")) {
				break;
			}
		}
	}

	// 전 직원 인사 정보 조회
	private void getEmployeeFullList() {
		ArrayList<HRVO> vos = dao.getEmployeeFullList();
		System.out.println("\n\t\t전 직원 인사 정보");
		System.out.println("=================================================");
		System.out.println(" 번호\t     사번\t   부서명\t   이름\t\t직급\t나이\t   입사일\t\t성별\t주소");
		System.out.println("--------------------------------------------------------------------------------------");

		for (int i = 0; i < vos.size(); i++) {
			vo = vos.get(i);
			System.out.print(" " + vo.getIdx() + "\t");
			System.out.print(vo.getSabun() + "\t");
			System.out.print("  " + vo.getBuseo() + "\t");
			System.out.print(" " + vo.getName() + "\t");
			System.out.print(vo.getJikkub() + "\t");
			System.out.print(vo.getAge() + "\t");
			System.out.print(vo.getIpsail().toString().substring(0, 10) + "\t");
			System.out.print(" " + vo.getGender() + "\t");
			System.out.print(" " + vo.getAddress() + "\n");
		}
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("\t\t\t총 직원 수 : " + vos.size() + "명");
		System.out.println("=================================================");
	}

	// 사번으로 검색하여 직원별 정보 조회
	private void getEmployeeSearch() {
		while (true) {
			System.out.print("\n검색할 직원의 사번을 입력하세요 : ");
			String sabun = scanner.next();

			vo = dao.getSabunSearch(sabun);

			if (vo != null) {
				System.out.println("\n해당 사번의 인사 정보는 아래와 같습니다.");
				System.out.println("▶ 사번 : " + vo.getSabun() + "\t");
				System.out.println("▶ 부서명 : " + vo.getBuseo() + "\t");
				System.out.println("▶ 이름 : " + vo.getName() + "\t");
				System.out.println("▶ 직급 : " + vo.getJikkub() + "\t");
				System.out.println("▶ 나이 : " + vo.getAge() + "\t");
				System.out.println("▶ 입사일 : " + vo.getIpsail().toString().substring(0, 10) + "\t");
				System.out.println("▶ 성별 : " + vo.getGender() + "\t");
				System.out.println("▶ 주소 : " + vo.getAddress() + "\t");
			} else {
				System.out.println("존재하지 않는 사번입니다.");
			}

			System.out.print("\n다른 직원의 검색을 계속 진행하시겠습니까? (Y/N) => ");
			String answer = scanner.next();

			if (!answer.toUpperCase().equals("Y")) {
				break;
			}
		}
	}

	// 직원 정보 수정하기
	private void setEmployeeUpdate() {
		while (true) {
			System.out.print("\n정보를 수정할 직원의 사번을 입력해 주세요 : ");
			String sabun = scanner.next();

			vo = dao.getSabunSearch(sabun);
			if (vo != null) {
				System.out.println("\n수정을 원하는 정보를 선택해 주세요.");
				System.out.print("1. 부서명  2. 이름  3. 직급  4. 나이  5. 입사일  6. 성별  7. 주소 0. 종료=>");
				int choice = scanner.nextInt();

				if (choice == 0) {
					break;
				}

				System.out.print("수정할 내용을 입력해 주세요 : ");

				switch (choice) {
				case 1:
					vo.setBuseo(scanner.next());
					break;
				case 2:
					vo.setName(scanner.next());
					break;
				case 3:
					vo.setJikkub(scanner.next());
					break;
				case 4:
					vo.setAge(scanner.nextInt());
					break;
				case 5:
					vo.setIpsail(scanner.next());
					break;
				case 6:
					vo.setGender(scanner.next());
					break;
				case 7:
					vo.setAddress(scanner.next());
					break;
				default:
					System.out.println("유효하지 않은 번호입니다. 다시 선택해 주세요.");
					break;
				}
				int result = dao.setEmployeeUpdate(vo);

				if (result != 0) {
					System.out.println("\n" + sabun + " 사번의 정보 수정이 완료되었습니다.");
				} else {
					System.out.println("정보 수정이 실패하였습니다.");
				}

				System.out.print("다른 직원의 정보 수정을 계속 진행하시겠습니까? (Y/N) => ");
				String answer = scanner.next();

				if (!answer.toUpperCase().equals("Y")) {
					break;
				}
			} else {
				System.out.println("존재하지 않는 사번입니다. 직원 신규 등록을 진행해 주세요.");
				break;
			}
		}
	}
	
	// 직원 삭제
	private void setEmployeeDelete() {
	    while(true) {
	        System.out.print("\n삭제할 직원의 사번을 입력해 주세요 : ");
	        String sabun = scanner.next();
	        
	        vo = dao.getSabunSearch(sabun);
	        if (vo != null) {
	            System.out.print("정말로 삭제하시겠습니까 ? (Y/N) => ");
	            String answer = scanner.next();

	            if (answer.toUpperCase().equals("Y")) {
	                int result = dao.setEmployeeDelete(vo.getSabun());

	                if (result != 0) {
	                    System.out.println("\n" + sabun + " 사번의 삭제가 완료되었습니다.");
	                } else {
	                    System.out.println("\n" + sabun + " 사번의 삭제가 처리되지 않았습니다.");
	                }
	            } 
	        } else {
	            System.out.println("\n존재하지 않는 사번입니다. 신규 등록을 진행해 주세요.");
	        }

	        System.out.print("다른 직원의 정보도 계속 삭제하시겠습니까? (Y/N) => ");
	        String answer = scanner.next();

	        if (!answer.toUpperCase().equals("Y")) {
	            break;
	        }
	    }
	}
}