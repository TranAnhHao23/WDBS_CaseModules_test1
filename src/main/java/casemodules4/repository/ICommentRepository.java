package casemodules4.repository;

import casemodules4.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select * from comment where post_fk = :idPost", nativeQuery = true)
    List<Comment> findAllByPostId(@Param("idPost") Long idPost);

//    @Modifying
//    @Query(value = "insert into comment (post_id_post, comment_id_comment) values (:idPost, :idComment)", nativeQuery = true)
//    void saveCommentInPost(@Param("idPost")Long idPost,@Param("idComment")Long idComment);

    @Modifying
    @Query(value = "insert into comment (content, user_id_user, post_fk) values (:content, :idComment, :idPost)",nativeQuery = true)
    void saveCommentInPost(String content, Long idComment, Long idPost);
}
