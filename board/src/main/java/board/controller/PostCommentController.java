package board.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import board.dto.PostCommentDto;
import board.dto.UserDto;
import board.dto.request.PostCommentRequest;
import board.dto.security.BoardDetails;
import board.entity.constant.UserRoleType;
import board.service.PostCommentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class PostCommentController {
	
	private final PostCommentService postCommentService;
	
	
	@PostMapping
	public String registerNewPostComment(
			PostCommentRequest postCommentRequest,
			@AuthenticationPrincipal BoardDetails boardDetails) {
		
		// 로그인 가정
//		UserDto userDto = UserDto.of("admin", "admin", "admin@board.com", "admin", UserRoleType.ROLE_ADMIN);
		
		postCommentService.registerPostComment(postCommentRequest.toDto(boardDetails.toDto()));
		
		return "redirect:/posts/" + postCommentRequest.getPid();
	}
	
	@PostMapping("/{pcid}/delete")
	public String deletePostComment(
			@PathVariable Long pcid,
			Long pid,
			@AuthenticationPrincipal BoardDetails boardDetails) {
		
		// 로그인 가정
//		UserDto userDto = UserDto.of("admin", "admin", "admin@board.com", "admin", UserRoleType.ROLE_ADMIN);
		
		// 댓글 존재 + 댓글 작성자만이 삭제
		postCommentService.deletePostComment(pcid, pid, boardDetails.getUid());
		
		return "redirect:/posts/" + pid;
	}
}