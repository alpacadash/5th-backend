package board.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import board.dto.PostDto;
import board.dto.PostWithCommentsDto;
import board.dto.request.SearchRequest;
import board.dto.response.PostResponse;
import board.entity.Post;
import board.entity.User;
import board.repository.PostRepository;
import board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	
	@Transactional
    public PostDto getPost(Long pid) {
    	return postRepository.findById(pid)
    							.map(PostDto::from)
    							.orElseThrow(() -> new NoSuchElementException("해당 게시글 존재 x"));
    }
    
	@Transactional
    public PostWithCommentsDto getPostWithComments(Long pid) {
    	return postRepository.findById(pid)
    							.map(PostWithCommentsDto::from)
    							.orElseThrow(() -> new NoSuchElementException("해당 게시글 존재 x"));
    }
	
	@Transactional
    public void registerPost(PostDto postDto) {
		// 로그인 가정 : save 처음 1번만!
//    	User user = userRepository.save(User.of(postDto.getUserDto().getUid(),
//    											postDto.getUserDto().getPassword(),
//    											postDto.getUserDto().getPassword(),
//    											postDto.getUserDto().getEmail(),
//    											postDto.getUserDto().getUserRoleType()));
    	
		User user = userRepository.getReferenceById(postDto.getUserDto().getUid());
		
    	Post post = postDto.toEntity(user);
    	postRepository.save(post);
    }
	
	@Transactional
    public void updatePost(Long pid, PostDto postDto) {
		
		Post post = postRepository.getReferenceById(pid);
		
		post.updateTitleAndContentAndCategoryType(postDto.getTitle(), 
												  postDto.getContent(), 
												  postDto.getCategoryType());
		
    }
	
	@Transactional
    public void deletePost(long pid, String uid) {
		
		// post 존재 + post 작성한 사람만이 삭제
		postRepository.deleteByIdAndUser_Uid(pid, uid);
		
    }
    
    @Transactional
	public List<PostDto> getPosts() {
		return postRepository.findAll().stream()
										.map(PostDto::from)
//										.map(post -> PostDto.from(post))
										.toList();
	}
    
    @Transactional
	public Page<PostDto> getPostsWithPage(Pageable pageable) {
    	return postRepository.findAll(pageable)
    							.map(PostDto::from);
	}
    
    @Transactional
	public List<PostDto> getPostsWithSearch(SearchRequest searchRequest) {
		
    	// searchType, searchValue
    	// isBlank -  공백/탭/개행 등의 의미없는 문자 체크, isEmpty - 길이가 0 체크
    	if(!searchRequest.hasSearch()) {
    		return postRepository.findAll().stream()
    									.map(PostDto::from)
    									.toList();
    	}
    	
    	// 검색 타입, 값 일치 리턴
		return switch (searchRequest.getSearchType()) {
				case "title" -> postRepository.findByTitleContains(searchRequest.getSearchValue()).stream().map(PostDto::from).toList();
				case "content" -> postRepository.findByContentContains(searchRequest.getSearchValue()).stream().map(PostDto::from).toList();
				case "uid" -> postRepository.findByUser_UidContains(searchRequest.getSearchValue()).stream().map(PostDto::from).toList();
				
				default -> throw new IllegalArgumentException("Unexpected value: " + searchRequest.getSearchType());
			};
	}
    
    @Transactional
	public Page<PostDto> getPostsWithSearchAndPage(SearchRequest searchRequest, Pageable pageable) {
    	// searchType, searchValue
    	// isBlank -  공백/탭/개행 등의 의미없는 문자 체크, isEmpty - 길이가 0 체크
    	if(!searchRequest.hasSearch()) {
    		return postRepository.findAll(pageable)
    									.map(PostDto::from);
    	}
    	
    	// 검색 타입, 값 일치 리턴
		return switch (searchRequest.getSearchType()) {
				case "title" -> postRepository.findByTitleContains(searchRequest.getSearchValue(), pageable).map(PostDto::from);
				case "content" -> postRepository.findByContentContains(searchRequest.getSearchValue(), pageable).map(PostDto::from);
				case "uid" -> postRepository.findByUser_UidContains(searchRequest.getSearchValue(), pageable).map(PostDto::from);
				default -> throw new IllegalArgumentException("Unexpected value: " + searchRequest.getSearchType());
			};
	}
	
}