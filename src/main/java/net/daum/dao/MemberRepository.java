package net.daum.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, String> {
	
	@Query("select m.uid2,count(p) from MemberVO m left outer join Profile p "
			+ " on m.uid2 = p.member where m.uid2 = ?1 group by m")
	/* JPQL문 : tbl_members 테이블에는 레코드가 있으나 tbl_profile 테이블에는 레코드가 없는 경우
	 *  left outer join을 한다. 스프링 부트 2.0이상과 하이버네이트 5.2.x 버전 이후 부터는 참조
	 *  관계가 없어도 on조건절을 통한 LEFT OUTER join이 가능하다. JPQL문은 실제 테이블 대신 엔티티빈
	 *  클래스명을 사용하고 실제 컬러명 대신 엔티티빈 클래스의 속성명인 변수명을 사용한다. =>jpa에서 Fetch JOIN문이라고
	 *  한다.(연관관계라고도 한다.)
	 */
	public List<Object[]> getMemberVOWithProfileCount(String id);//회원아이디와 프로필 사진개수
	
	@Query("select m,p from MemberVO m left outer join Profile p on m.uid2=p.member "
			+ " where m.uid2=?1 and p.current2=true")
	//?1은 첫번째로 전달되어지는 인자값, 회원정보와 현재 사용중인 프로필 사진 정보
	public List<Object[]> getMemberVOWithProfile(String uid);
}
