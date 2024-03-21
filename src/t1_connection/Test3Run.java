package t1_connection;

public class Test3Run {
	public static void main(String[] args) {
		Test3DAO dao = new Test3DAO();
		
		System.out.println("데이터베이스 연결 후 작업 중입니다.");
		
		Test3Service service = new Test3Service();
		service.getTestMethod();
		
		
		// DB 연결 종료
		dao.connClose();
	}
}
