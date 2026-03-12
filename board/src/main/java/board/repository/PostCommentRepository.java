package board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import board.entity.PostComment;
import board.entity.User;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long>{

	void deleteByIdAndPost_IdAndUser_Uid(Long pcid, Long pid, String uid);

}