package edu.kh.admin.main.model.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.admin.common.util.Utility;
import edu.kh.admin.main.model.dto.Board;
import edu.kh.admin.main.model.dto.Member;
import edu.kh.admin.main.model.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AdminServiceImpl implements AdminService{

	private final AdminMapper mapper;
	private final BCryptPasswordEncoder bcrypt;
	
	// 관리자 로그인
	@Override
	public Member login(Member inputMember) {
		Member loginMember = mapper.login(inputMember.getMemberEmail());
		
		if( loginMember == null ) return null;
		
		if( !bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw() )) {
			return null;
		}
		
		loginMember.setMemberPw(null);
		return loginMember;
	}
	
	// ---------------------------------------------- 복구 ------------------------------------------
	// 탈퇴 회원 목록
	@Override
	public List<Member> selectWithdrawnMemberList() {
		// TODO Auto-generated method stub
		return mapper.selectWithdrawnMemberList();
	}
	
	// 탈퇴 회원 복구
	@Override
	public int restoreMember(int memberNo) {
		// TODO Auto-generated method stub
		return mapper.restoreMember(memberNo);
	}
	
	// 삭제한 게시글 목록 조회
	@Override
	public List<Board> deleteBoardList() {
		return mapper.deleteBoardList();
	}
	
	// 삭제한 게시글 복구
	@Override
	public int restoreBoard(int boardNo) {
		// TODO Auto-generated method stub
		return mapper.restoreBoard(boardNo);
	}
	
	// 7일 이내 유저 조회
	@Override
	public List<Member> getNewMember() {
		return mapper.getNewMember();
	}
	
	// 최대 조회수 게시글
	@Override
	public Board maxReadCount() {
		
		return mapper.maxReadCount();
	}
	
	// 가장 좋아요 많은 게시글
	@Override
	public Board maxLikeCount() {
		// TODO Auto-generated method stub
		return mapper.maxLikeCount();
	}
	
	// 가장 댓글 많은 게시글
	@Override
	public Board maxCommentCount() {
		// TODO Auto-generated method stub
		return mapper.maxCommentCount();
	}
	
	// 관리자 계정 목록 조회
	@Override
	public List<Member> adminAccountList() {
		// TODO Auto-generated method stub
		return mapper.adminAccountList();
	}
	
	// 관리자 계정 발급
	@Override
	public String createAdminAccount(Member member) {

		// 1. 영어(대소문자), 숫자 6자리 난수로 만든 비밀번호를 암호화 한 값 구하기
		String rawPw = Utility.generatePassword(); // 평문 비번
		String encPw = bcrypt.encode(rawPw); // 암호화된 비번
		
		// 2. member에 암호화된 비밀번호 세팅
		member.setMemberPw(encPw);
		log.debug("member : " + member);
		// 3. DB에 암호화된 비밀번호가 세팅된 member를 전달하여 계정 발급(INSERT)
		int result = mapper.createAdminAccout(member);
		log.debug("member : " + member);
		
		// 4. 계정 발급 정상처리 되었다면, 발급된 (평문) 비밀번호 리턴하기
		if( result > 0 ) {
			return rawPw;
		} else {
			return null;
		}
	}
}
