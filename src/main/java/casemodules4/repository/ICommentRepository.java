package casemodules4.repository;

import casemodules4.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select * from comment_in_post inner join comment on comments_id_comment = id_comment where post_id_post = :idPost", nativeQuery = true)
    List<Comment> findAllByPostId(@Param("idPost") Long idPost);
}
