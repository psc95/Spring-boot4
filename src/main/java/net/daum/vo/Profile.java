package net.daum.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude="member") //toString()메서드를 호출했을 때 member변수를 제외 시켜서 호출 안되게 한다.
@Entity
@SequenceGenerator( //시퀀스 생성기
		 name="bno_seq3_gename", //시퀀스 제너레이터 이름
		 sequenceName = "bno_seq3", //시퀀스 이름
		 initialValue = 1, //시퀀스 번호 시작값
		 allocationSize = 1 //1씩 증가, 기본값 50
		)
@Table(name="tbl_profile") //tbl_profile 테이블명 지정
@EqualsAndHashCode(of="fname")
public class Profile {
	
	@Id //식별키인 기본키
	@GeneratedValue(
			 strategy = GenerationType.SEQUENCE, //사용할 전략을 시퀀스로 선택
			 generator="bno_seq3_gename" //시퀀스 생성기에서 설정해 놓은 시퀀스 제너레이터 이름
			)
	private int fno; //번호-> 유일한 번호값
	
	private String fname; //회원 프로필 사진 파일명
	private boolean current2; //현재 사용중인 프로필 사진은 true(1), 사용하지 않으면 false(0)
	
	@ManyToOne //다대일 연관관계
	private MemberVO member; //foreign key(외래키 -> 주종관계:Fetch join문을 생성)
	//주인테이블인 tbl_members의 uid2컬럼 회원아이디 값만 저장됨.
}















