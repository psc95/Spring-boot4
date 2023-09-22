package net.daum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.java.Log;
import net.daum.dao.MemberRepository;
import net.daum.dao.ProfileRepository;
import net.daum.vo.MemberVO;
import net.daum.vo.Profile;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log //Lombok 라이브러리로 로그 기록을 사용할 때 이용한다.
@Commit //데이터 베이스에 커밋 하는 용도로 활용
public class ProfileTests {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private ProfileRepository profileRepo;
	
	//20명 회원 자료 추가
	//@Test
	public void testInsertMember() {
		IntStream.range(1,21).forEach(i->{//1부터 20까지의 정수 숫자 생성
			MemberVO member = new MemberVO();
			
			member.setUid2("user"+i);  //회원아이디 저장
			member.setUpw("pw"+i);  //회원비번
			member.setUname("사용자"+i); //회원이름 저장
			
			System.out.println(" \n=================> tbl_members테이블 회원 20명 저장하는 JPA");
			this.memberRepo.save(member); //20명 회원 저장
		});
	}//testInsertMember()
	
	//특정 회원에 프로필 사진 추가
	//@Test
	public void testInsertProfile() {
		MemberVO member = new MemberVO();
		member.setUid2("user1");
		
		for(int i=1; i<5; i++) {
			Profile profile01 = new Profile();
			profile01.setFname("face"+i+".jpg"); //4개의 프로필 사진 파일 명 저장
			
			if(i==1) {
				profile01.setCurrent2(true); //첫번째 회원프로필 사진은 현재 사용중인걸로 처리
			}
			profile01.setMember(member);
			
			System.out.println("\n =============> 프로필 사진 추가 저장");
			this.profileRepo.save(profile01);
		}
	}//testInsertProfile()
	
	//user1 아이디 정보와 프로필사진 개수 => Fetch join01
	//@Test
	public void testFetchJoin01() {
		
		System.out.println(" \n==============> user1 회원아이디 정보와 프로필 사진 개수");
		List<Object[]> result = this.memberRepo.getMemberVOWithProfileCount("user1");
		result.forEach(arr->System.out.println(Arrays.toString(arr)));
		//Arrays.toString()메서드는 배열을 컬렉션 List로 변환
	}//testFetchJoin01()
	
	//user1 회원정보와 현재 사용중인 프로필 사진정보 =>Fetch Join02
	@Test
	public void testFetchJoin02() {
		
		System.out.println(" \n ===========> user1회원정보와 현재 사용중인 프로필 사진 정보");
		List<Object[]> result = this.memberRepo.getMemberVOWithProfile("user1");
		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
	}//testFetchJoin02()
}







