import manager.LMS;

public class Main {

	public static void main(String[] args) {
		/*
		파일 처리 시작하면 로드
		
		1.학생 관리
		
		ㄴ 가입
		User
		ㄴ ID
		ㄴ pw
		ㄴ 학과 코드
		ㄴ 과목 코드
		Student
		Professor
		
		
		ㄴ 탈퇴
		
		

		2. 수강 신청
		ㄴ 학과 확인 
		ㄴ 과목 조회
		3. 성적 관리
		ㄴ 성적확인
		ㄴ 성적 수정
		
		종료 할때 저장 처리
		*/
		LMS.getinstance().run();
	}

}
