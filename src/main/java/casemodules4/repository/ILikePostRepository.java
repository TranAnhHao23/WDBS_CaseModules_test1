package casemodules4.repository;

import casemodules4.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ILikePostRepository extends JpaRepository<LikePost, Long> {

    Integer countByPost_IdPost(Long idPost);

    @Query(value = "select * from like_post where id_post = :idPost and id_like = :idLike",nativeQuery = true)
    LikePost findLikeByPostIdAndLikeId(@Param("idPost") Long idPost,@Param("idLike") Long idLike);

}
