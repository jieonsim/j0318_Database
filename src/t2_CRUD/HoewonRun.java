package t2_CRUD;

public class HoewonRun {
	public static void main(String[] args) {
//		HoewonDAO dao = new HoewonDAO();
//		HoewonDAO2 dao2 = new HoewonDAO2();
		HoewonDAO3 dao3 = new HoewonDAO3();
		
		// hoewon 자료 전체 조회
//		dao.getList();
//		dao2.getList();
		dao3.getList();
		
		dao3.connClose();
	}
}
