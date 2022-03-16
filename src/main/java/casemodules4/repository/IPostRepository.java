package casemodules4.repository;

import casemodules4.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IPostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByContentContaining(String hashtag);

    List<Post> findAllByUserPostIdUser(Long id);
}
