package edu.kh.admin.main.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.admin.main.model.dto.Board;
import edu.kh.admin.main.model.dto.Member;
import edu.kh.admin.main.model.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;


public interface AdminService{

	/** 관리자 로그인
	 * @param inputMember
	 * @return
	 */
	Member login(Member inputMember);

	/** 탈퇴한 회원 목록 조회
	 * @return
	 */
	List<Member> selectWithdrawnMemberList();

	/** 탈퇴 회원 복구
	 * @param memberNo
	 * @return
	 */
	int restoreMember(int memberNo);

	/** 삭제 게시글 조회
	 * @return
	 */
	List<Board> deleteBoardList();

	/** 삭제 게시글 복구
	 * @param boardNo
	 * @return
	 */
	int restoreBoard(int boardNo);

	/** 회원가입 7일 이내 유저 조회
	 * @return
	 */
	List<Member> getNewMember();

	/** 최대 조회수 게시글 조회
	 * @return
	 */
	Board maxReadCount();

	/** 가장 좋아요 많은 게시글
	 * @return
	 */
	Board maxLikeCount();

	/** 가장 댓글 많은 게시글
	 * @return
	 */
	Board maxCommentCount();

	/** 관리자 계정 목록 조회
	 * @return
	 */
	List<Member> adminAccountList();

	/** 관리자 계정 발급
	 * @param member
	 * @return
	 */
	String createAdminAccount(Member member);
	

}
