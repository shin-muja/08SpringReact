package edu.kh.admin.main.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.admin.main.model.dto.Board;
import edu.kh.admin.main.model.dto.Member;

@Mapper
public interface AdminMapper{

	/** 관리자 로그인
	 * @param memberEmail
	 * @return
	 */
	Member login(String memberEmail);

	/** 탈퇴 회원 목록 조회
	 * @return
	 */
	List<Member> selectWithdrawnMemberList();

	/** 탈퇴 회원 복구
	 * @param memberNo
	 * @return
	 */
	int restoreMember(int memberNo);

	/** 삭제한 게시글 목록 정보 가져오기
	 * @return
	 */
	List<Board> deleteBoardList();

	/** 삭제한 게시글 복구
	 * @param boardNo
	 * @return
	 */
	int restoreBoard(int boardNo);
	
	

}
