package t3_CRUD;

import java.util.*;

public class HoewonService {
	Scanner sc = new Scanner(System.in);
	
	// 전체 조회
	public void getList() {
//		HoewonDAO dao = new HoewonDAO();
		HoewonDAO2 dao = new HoewonDAO2();

		ArrayList<HoewonVO> vos = dao.getList();
		HoewonVO vo = new HoewonVO();

		System.out.println("-----------------------------------------------");
		System.out.println("번호\t성명\t나이\t성별\t주소");
		System.out.println("-----------------------------------------------");
		for (int i = 0; i < vos.size(); i++) {
			vo = vos.get(i);
			System.out.print((i + 1) + "\t");
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getAge() + "\t");
			System.out.print(vo.getGender() + "\t");
			System.out.print(vo.getAddress() + "\n");
			System.out.println();
		}
		System.out.println("-----------------------------------------------");
		System.out.println("\t\t 총 : " + vos.size() + "건");

		dao.connClose();
	}

	// 개별 검색 처리
	public void getSearch(String name) {
		HoewonDAO2 dao = new HoewonDAO2();

		HoewonVO vo = dao.getSearch(name);

		System.out.println(name + "(으)로 검색된 자료 ");
		if (vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx() + "\t");
			System.out.println("성명 : " + vo.getName() + "\t");
			System.out.println("나이 : " + vo.getAge() + "\t");
			System.out.println("성별 : " + vo.getGender() + "\t");
			System.out.println("주소 : " + vo.getAddress() + "\t");
		} else {
			System.out.println("검색한 자료가 없습니다.");
		}
		dao.connClose();
	}

	// 회원 정보 수정
	public void setUpdate(String name) {
		HoewonDAO2 dao = new HoewonDAO2();

		// 있는 자료 중에서 수정할 수 있기 때문에 정보 수정을 하기위해서는 검색 먼저
		HoewonVO vo = dao.getSearch(name);

		System.out.println(name + "(으)로 검색된 자료 ");
		if (vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx() + "\t");
			System.out.println("성명 : " + vo.getName() + "\t");
			System.out.println("나이 : " + vo.getAge() + "\t");
			System.out.println("성별 : " + vo.getGender() + "\t");
			System.out.println("주소 : " + vo.getAddress() + "\t");
			System.out.println("-----------------------------------------------");
			System.out.print("수정할 항목 : 1. 성명 2. 나이 3. 성별 4. 주소 ==> ");
			int choice = sc.nextInt();
			System.out.print("수정할 내용 : ");
			String content = sc.next();
			dao.setUpdate(vo.getIdx(), choice, content); 
			System.out.println("===> 자료가 수정되었습니다.");
			
		} else {
			System.out.println("검색한 자료가 없습니다.");
		}
		dao.connClose();

	}
	
	// 회원 자료 삭제 처리
	public void setDelete(String name) {
		HoewonDAO2 dao = new HoewonDAO2();

		HoewonVO vo = dao.getSearch(name);

		System.out.println(name + "(으)로 검색된 자료 ");
		if (vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx() + "\t");
			System.out.println("성명 : " + vo.getName() + "\t");
			System.out.println("나이 : " + vo.getAge() + "\t");
			System.out.println("성별 : " + vo.getGender() + "\t");
			System.out.println("주소 : " + vo.getAddress() + "\t");
			System.out.println("-----------------------------------------------");
			System.out.print("삭제하시겠습니까?(Y/N) ");
			String choice = sc.next();
			if (choice.toUpperCase().equals("Y")) {
				dao.setDelete(name);
				System.out.println("===> 삭제 성공");
			} else {
				System.out.println("===> 삭제 취소");
			}
		} else {
			System.out.println("검색한 자료가 없습니다.");
		}
		dao.connClose();
		
	}

	// 회원 자료 등록
	public void setInput() {
		HoewonDAO2 dao = new HoewonDAO2();
		
		String name, gender, address;
		int age;
		
		System.out.println("==> 회원 정보 등록하기");
		System.out.print("성명 : ");
		name = sc.next();
		System.out.print("나이 : ");
		age = sc.nextInt();
		System.out.print("성별 : ");
		gender = sc.next();
		System.out.print("주소 : ");
		address = sc.next();
		
		HoewonVO vo = new HoewonVO();
		vo.setName(name);
		vo.setAge(age);
		vo.setGender(gender);
		vo.setAddress(address);
		
		dao.setInput(vo);
		System.out.println("회원 등록 완료");
		
		dao.connClose();
	}
}
