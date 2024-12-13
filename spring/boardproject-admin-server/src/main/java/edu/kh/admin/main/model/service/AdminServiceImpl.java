package edu.kh.admin.main.model.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
